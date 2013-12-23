/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.plsql.psi.utils;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.psi.spices.CompilableObject;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
//import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.TokenType;
import com.intellij.psi.impl.source.tree.CompositeElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class PlSqlUtil {


    public static void iterateOver(@NotNull ASTNode node, @NotNull ASTNodeProcessor processor){
        ASTNode curr = node.getFirstChildNode();
        while(curr != null){
            processor.process(curr);
            iterateOver(curr, processor);
            curr = curr.getTreeNext();
        }
    }

    public static boolean iterateOver(@NotNull ASTNode node, @NotNull ASTNodeProcessorBreakable processor){
        ASTNode curr = node.getFirstChildNode();
        while(curr != null){
            processor.process(curr);
            if(!iterateOver(curr, processor)){
                return false;
            }
            curr = curr.getTreeNext();
        }
        return true;
    }

    public static interface ASTNodeProcessor {
        void process(ASTNode node);
    }

    public static interface ASTNodeProcessorBreakable {
        boolean process(ASTNode node);
    }

    public static boolean moveToOffset(@NotNull PsiFile plSqlFile, int offset) {
        Project project = plSqlFile.getProject();

        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        OpenFileDescriptor desc = new OpenFileDescriptor(project, plSqlFile.getVirtualFile(), offset);
        return fileEditorManager.openTextEditor(desc, true) != null;
    }


    public static void verifyDbOriginatedFilesInEditor(Project project, AbstractSchema schema) {
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
            if (v instanceof DbDumpedSqlFile) {
                DbUrl dbUrl = ((DbDumpedSqlFile) v).getDbUrl();
                if (dbUrl.equals(schema.getDbUrl())) {
                    // validate object timestamp
                    DbDumpedSqlFile ff = (DbDumpedSqlFile) v;
                    String fileName = ff.getOriginFileName();
                    long timestamp = v.getModificationStamp();
                    String _timestamp = schema.getIndexTree().getFileAttribute(fileName, IndexTree.TIMESTAMP_ATTR);
                    if (_timestamp != null) {
                        try {
                            long ts = Long.parseLong(_timestamp);
                            if(ts != timestamp){
                                // reload text in the editor
                                Document doc = FileDocumentManager.getInstance().getDocument(v);
                                //String text = schema.getSourceText(ff.getEncodedFilePathCtx());
                                try {
                                    VirtualFile vf = schema.getSourceFile(ff.getEncodedFilePathCtx());
                                    byte[] content = vf.contentsToByteArray();
                                    String text = new String(content, vf.getCharset());

//                                    String text = (String) schema.getSourceFile(ff.getEncodedFilePathCtx()).getContent();
                                    emulateInsertion(doc, project, text);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (NumberFormatException e) {
                        }
                    } else {
                        // looks like object was deleted, close the editor 
                        fileEditorManager.closeFile(v);
                    }
                }
            }
        }
    }

    public static void closeEditorsForSchema(Project project, AbstractSchema schema) {
        final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        for (VirtualFile v : fileEditorManager.getOpenFiles()) {
            if(checkUrl(v, schema.getDbUrl())){
                fileEditorManager.closeFile(v);
            }
        }
    }


    private static boolean checkUrl(VirtualFile file, DbUrl dbUrl1) {
        if (file instanceof DbDumpedSqlFile) {
            // SQL file from DbSchemaIndex
            DbUrl dbUrl = ((DbDumpedSqlFile) file).getDbUrl();
            if (dbUrl1.equals(dbUrl)) {
                return true;
            }
        } else if (file instanceof FSSqlFile) {
            // SQL file from FSIndex
            if (dbUrl1.equals(IndexManager.FS_URL)) {
                return true;
            }
        } else if (file instanceof SysSqlFile) {
            // SQL file from Sys const
            DbUrl dbUrl = ((SysSqlFile) file).getDbUrl();
            if (dbUrl1.equals(dbUrl)) {
                return true;
            }

        } else {
            // SQL file from FSIndex
            if (dbUrl1.equals(IndexManager.FS_URL)) {
                return true;
            }
        }

        return false;
    }


    private static void emulateInsertion(final Document doc, final Project project, final String text) {

        ApplicationManager.getApplication().runWriteAction(new Runnable(){
            public void run() {
                Editor[] editors = EditorFactory.getInstance().getEditors(doc, project);

                final Editor editor = editors[0];
                final Document document = editor.getDocument();
                //final String lookupString = item.getLookupString();

                int curetOffset = editor.getCaretModel().getOffset();
                if( text.length() < curetOffset){
                    curetOffset = text.length()-1;
                }
                document.setText(text);//insertString(offset, lookupString);
                editor.getCaretModel().moveToOffset(curetOffset);
                PsiDocumentManager.getInstance(project).commitDocument(document);
            }
        });
    }


    public static String completeCreateScript(CompilableObject co) {

        final StringBuilder sb = new StringBuilder();
        final String stopMarker = co.getObjectType();

        try {
            iterateLeafsFromBeggining(co.getNode(), new HandleLeafElement() {
                public void handle(ASTNode elem) {
                    if (elem.getText().equalsIgnoreCase(stopMarker)) {
                        throw new BreakIteration();
                    } else if (elem.getText().equalsIgnoreCase("CREATE")) {
                        sb.append("CREATE");
                    } else if (elem.getText().equalsIgnoreCase("OR")) {
                        sb.append("OR");
                    } else if (elem.getText().equalsIgnoreCase("REPLACE")) {
                        sb.append("REPLACE");
                    }
                }
            });
        } catch (BreakIteration ignored) {
        }

        String prefix = "";
        if (sb.length() == 0) {
            prefix = "CREATE OR REPLACE ";
        }

        final String[] endingSemi = {""};
        try {
            iterateLeafsFromEnd(co.getNode(), new HandleLeafElement() {
                public void handle(ASTNode elem) {
                    if (elem.getElementType() == TokenType.WHITE_SPACE) {
                    } else if (elem.getElementType() == PlSqlTokenTypes.WS) {
                    } else if (elem.getElementType() == PlSqlTokenTypes.ML_COMMENT) {
                    } else if (elem.getElementType() == PlSqlTokenTypes.BAD_ML_COMMENT) {
                    } else if (elem.getElementType() == PlSqlTokenTypes.SEMI) {
                        // appending of SEMI no need
                        throw new BreakIteration();
                    } else {
                        endingSemi[0] = ";\n";
                        throw new BreakIteration();
                    }
                }
            });
        } catch (BreakIteration ignored) {
        }

        return prefix + co.getText() + endingSemi[0];
    }

    public static void iterateLeafsFromBeggining(ASTNode node, HandleLeafElement elem) {
        ASTNode current = node.getFirstChildNode();
        do {
            if(current.getFirstChildNode() != null){
                iterateLeafsFromBeggining(current, elem);
            } else {
                // handle leaf
                elem.handle( current);
            }

/*
            if (current instanceof CompositeElement) {
                iterateLeafsFromBeggining(current, elem);
            } else if (current instanceof LeafElement) {
                // handle leaf
                elem.handle((LeafElement) current);
            } else {
                throw new ConfigurationException("Not supported type: " + current.getClass());
            }
*/

            current = current.getTreeNext();
        } while (current != null);
    }

    public static void iterateLeafsFromEnd(ASTNode node, HandleLeafElement elem) {
        ASTNode current = node.getLastChildNode();
        do {
            if(current.getFirstChildNode() != null){
                iterateLeafsFromEnd(current, elem);
            } else {
                // handle leaf
                elem.handle( current);
            }

/*
            if (current instanceof CompositeElement) {
                iterateLeafsFromEnd(current, elem);
            } else if (current instanceof LeafElement) {
                // handle leaf
                elem.handle((LeafElement) current);
            } else {
                throw new ConfigurationException("Not supported type: " + current.getClass());
            }
*/

            current = current.getTreePrev();
        } while (current != null);
    }

    public static interface HandleLeafElement {
        void handle(ASTNode elem);
    }

    private static class BreakIteration extends Error {
    }
}
