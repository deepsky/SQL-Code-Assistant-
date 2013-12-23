package com.deepsky.actions;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.common.PluginKeys2;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.SqlDomainIndex;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.services.dataImport.ImportDTO;
import com.deepsky.uiForms.ImportStep1Dialog;
import com.deepsky.uiForms.ImportStep2Dialog;
import com.deepsky.uiForms.ImportStep3Dialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.GuiUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ImportDataAction extends AnAction {

    private static final Logger log = Logger.getInstance("#ImportDataAction");
    boolean lastEnabled = false;

    public ImportDataAction() {
        super("ImportData");
        getTemplatePresentation().setEnabled(true);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {

        // Make sure connection is established
        ConnectionManager cMan = PluginKeys.CONNECTION_MANAGER.getData(e.getProject());
        if (cMan.getDbUrl() != null) {
            ImportDTO dto = new ImportDTO();

            try {
                chooseFileToImport(e.getProject(),dto,  "ImportData Step1");

                populateDtoWithTables(dto, cMan.getDbUrl(), PluginKeys2.SQL_INDEX_MAN.getData(e.getProject()));
                chooseTableBeingLoaded(e.getProject(), dto, "ImportData Step2");

                assignTableColumns(e.getProject(), dto, "ImportData Step3");
                runImport(dto);
            } catch (DataImportException e1) {
                if(e1.getMessage() != null)
                    Messages.showErrorDialog(e1.getMessage(), "Data Import Error");
            }
        } else {
            Messages.showErrorDialog("Connect to the database before running data import.",
                    "Data Import Error");
        }
    }

    private void runImport(ImportDTO dto) throws DataImportException {
        //To change body of created methods use File | Settings | File Templates.
    }


    private void populateDtoWithTables(ImportDTO dto, DbUrl dbUrl, IndexManager data) throws DataImportException {
        SqlDomainIndex dIndex = data.getIndex(dbUrl.getDbUID());
        if (dIndex == null) {
            throw new DataImportException("Internal Error: cannot identify connection");
        }

        final IndexTree itree = dIndex.getIndex(dbUrl.getUser());
        if (itree == null) {
            throw new DataImportException("Internal Error: cannot identify connection");
        }


        final Map<String, TableDescriptor> tableList = new HashMap<String, TableDescriptor>();
        itree.iterateTopNodes(new IndexEntriesWalkerInterruptable() {
            @Override
            public boolean process(String ctxPath, String value) {
                if (ContextPathUtil.extractTopObjectType(ctxPath) == ContextPath.TABLE_DEF) {
                    final String tableName = ContextPathUtil.extractTopObjectName(ctxPath);

                    final List<String> columns = new ArrayList<String>();
                    itree.iterateOverChildren(ctxPath, new IndexEntriesWalkerInterruptable() {
                        @Override
                        public boolean process(String ctxPath, String value) {
                            return columns.add(ContextPathUtil.extractLastCtxName(ctxPath));
                        }
                    });
                    tableList.put(tableName.toUpperCase(), new TableDescriptorEx() {
                        @Override
                        public String[] getColumnNames() {
                            return columns.toArray(new String[0]);
                        }

                        @Override
                        public Type getColumnType(String column) {
                            return null;  //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public boolean isColumnNullable(String column) {
                            return false;  //To change body of implemented methods use File | Settings | File Templates.
                        }

                        @Override
                        public String getName() {
                            return tableName;
                        }
                    });
                }
                return true;
            }
        });

        dto.setTableList(tableList);
    }


    public static void chooseFileToImport(Project project, ImportDTO importDTO, String title) throws DataImportException  {
        final ImportStep1Dialog dlg = new ImportStep1Dialog(project, importDTO, title);
        try {
            final Runnable doRun = new Runnable() {
                public void run() {
                    dlg.setVisible(true);
                }
            };
            GuiUtils.runOrInvokeAndWait(doRun);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }

        if( dlg.isCancelPressed()){
            throw new DataImportException(null);
        }
    }

    public static void chooseTableBeingLoaded(Project project, ImportDTO importDTO, String title) throws DataImportException {
        final ImportStep2Dialog dlg = new ImportStep2Dialog(project, importDTO, title);
        try {
            final Runnable doRun = new Runnable() {
                public void run() {
                    dlg.setVisible(true);
                }
            };
            GuiUtils.runOrInvokeAndWait(doRun);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        if( dlg.isCancelPressed()){
            throw new DataImportException(null);
        }
    }


    public static void assignTableColumns(Project project, ImportDTO importDTO, String title) throws DataImportException {
        final ImportStep3Dialog dlg = new ImportStep3Dialog(project, importDTO, title);
        try {
            final Runnable doRun = new Runnable() {
                public void run() {
                    dlg.setVisible(true);
                }
            };
            GuiUtils.runOrInvokeAndWait(doRun);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }

        if( dlg.isCancelPressed()){
            throw new DataImportException(null);
        }

    }


    private abstract class TableDescriptorEx implements TableDescriptor {

        @Override
        public ColumnDescriptor getColumnDescriptor(String column) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public int getType() {
            return REGULAR;
        }

        @Override
        public String getTypeName() {
            return TABLE;
        }

        @Override
        public Date getLastDDLTime() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void setLastDDLTime(Date lastDDL) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Nullable
        @Override
        public SqlScriptLocator getLocator() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void setLocator(SqlScriptLocator url) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public boolean isValid() {
            return true;
        }

        @NotNull
        @Override
        public DbObject getRootContext() {
            return null;
        }
    }

    private static class DataImportException extends Exception {
        public DataImportException(String error){
            super(error);
        }
    }
}
