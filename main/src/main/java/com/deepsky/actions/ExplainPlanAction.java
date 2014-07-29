package com.deepsky.actions;

import com.deepsky.database.DBException;
import com.deepsky.database.exec.SQLUpdateStatistics;
import com.deepsky.database.exec.impl.RespMessageTable;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.common.ResolveProvider;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.QueryResultPanel;
import com.deepsky.view.query_pane.QueryResultWindow;
import com.deepsky.view.query_pane.markup.SqlStatementMarker;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class ExplainPlanAction extends AnAction {

    private static final Logger log = Logger.getInstance("#ExplainPlanAction");
    boolean lastEnabled = false;

    public ExplainPlanAction() {
        super("ShowExecPlan");
        getTemplatePresentation().setEnabled(true);
    }

    public void actionPerformed(AnActionEvent event) {

        PsiFile _psi = event.getData(LangDataKeys.PSI_FILE);
        Editor e = event.getData(LangDataKeys.EDITOR);

        if (e != null && _psi != null && _psi instanceof PlSqlFile && _psi.getTextLength() > 0) {
            PlSqlFile psi = (PlSqlFile) _psi;
            SelectionModel select = e.getSelectionModel();

            if (select.getSelectedText() == null) {
                Messages.showErrorDialog("SQL Code not selected. Select a script fragment and run Show Execution Plan again.",
                        "Show Execution Plan Error");
                return;
            }

            PsiElement start = psi.findElementAt(select.getSelectionStart());
            PsiElement end = psi.findElementAt(select.getSelectionEnd() - 1);

            int spos = (start != null) ? start.getTextOffset() : 0;
            int epos = (end != null) ? end.getTextOffset() + end.getTextLength() : psi.getTextLength();

            ASTNode ancestor = null;
            if (select.getSelectedText().length() > 0) {
                String text = psi.getText().substring(spos, epos).trim();
                try {
                    MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
                    ASTNode root = generator.parse(text);
                    ancestor = root.findChildByType(
                            RespMessageTable.getExecutableStmts()
                    );
                    if (ancestor == null) {
                        Messages.showErrorDialog(
                                "SQL statement was not recognized, please check the syntax.",
                                "SQL Statement Syntax Error");
                    } else if (ancestor.getElementType() == PlSqlElementTypes.ANONYM_PLSQL_BLOCK) {
                        // should be check for the particular command
                        ASTNode plsqlBlk = ancestor.findChildByType(PlSqlElementTypes.PLSQL_BLOCK);
                        if (plsqlBlk != null) {
                            ancestor = plsqlBlk;
                        } else {
                            ancestor = null;
                        }
                    } else {
                        // statement recognized!
                    }

                } catch (Throwable e1) {
                    // error
                }
            }

            if (ancestor != null) {
                int _st = spos + ancestor.getTextRange().getStartOffset();
                int _end = spos + ancestor.getTextRange().getEndOffset();
                Project project = event.getData(LangDataKeys.PROJECT);

                try {
                    executeStatement(project, ancestor, psi, _st, _end);
                } catch (DBException e1) {
                    Messages.showErrorDialog(e1.getMessage(), "SQL Query Error");
                    return;
                } catch (NotSupportedException e1) {
//                        Messages.showErrorDialog(e1.getMessage(), "SQL query error");
                    return;
                }

            } else {
                String text = select.getSelectedText();
                log.info("#actionPerformed [NOT IDENTIFIED] selected text: " + text);
            }
        }
    }

    private void executeStatement(Project project, ASTNode statement, final PlSqlFile psi, final int _st, final int _end)  throws DBException {
        final QueryResultWindow qrwn = PluginKeys.QR_WINDOW.getData(project);
        try {
            ((ResolveProvider) statement.getPsi().getContainingFile()).setResolver(psi.getResolver());
            SqlScriptRunService.getInstance(project).runExplainPlan(
                    statement.getText(),
                    new SqlScriptRunner.DMLResultListener() {
                        public void handleDMLResult(SQLUpdateStatistics result) {
                            SqlStatementMarker marker = psi.getModel().addMarker(_st, _end);
                            QueryResultPanel resultPane = qrwn.createResultPanel(
                                    QueryResultPanel.DML_QUERY_RESULT, marker, Icons.DML_RESULT, null /* ToolTip text */
                            );
                            resultPane.init(result);
                            // show content pane
                            qrwn.showContent(marker, false, 1500);
                        }
                    });
        } catch (SqlScriptRunner.AlreadyStartedException e) {
            Messages.showErrorDialog(
                    project,
                    "Cannot run SQL until the SQL statement running at the moment will be finished",
                    "Running Execution Plan Failed");
        }
    }
}

