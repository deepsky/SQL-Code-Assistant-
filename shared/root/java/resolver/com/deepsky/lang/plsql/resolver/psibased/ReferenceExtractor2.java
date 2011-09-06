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

package com.deepsky.lang.plsql.resolver.psibased;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.RefHolder;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.NameFragmentCallback;
import com.deepsky.lang.plsql.resolver.utils.PsiElementUtil;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.utils.StringUtils;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReferenceExtractor2 extends AbstractReferenceExtractor {

    Writer logger;
    List<RefHolder> out = new ArrayList<RefHolder>();
    public Set<ResolveDescriptor> uniqueRefs = new HashSet<ResolveDescriptor>();

    ResolveHelper resolver;

    public int successed = 0, failed = 0;
    public int refCounter = 0;

    public ReferenceExtractor2(Writer logger, AbstractSchema sindex) {
        super(sindex);
        this.logger = logger;
        this.resolver = sindex.getResolveHelper();
    }

    public int getRefCounter(){
        return refCounter;
    }

    public int getRefFailed(){
        return failed;
    }

    public int getRefSucceded(){
        return successed;
    }

    public long getPureResolveTime(){
        return resolver.getSpentTime();
    }

    public long getExtractingTime(){
        return getTreeProcessTime() - resolver.getSpentTime();
    }


    public List<RefHolder> processScript(String content, String fileName) {
        out.clear();
        super.processContent(content, fileName);
        return out;
    }


    // ObjectReference
    // TypeNameReference
    // CallableCompositeName
    // SequenceExpr
    // ColumnSpec
    // VColumnDefinition
    // TriggerColumnNameRef
    // ParameterReference

    // SequenceRef
    // TableRef

    @Override
    protected void visitObjectReference(ObjectReference node) {
        refCounter++;
        String path = node.getCtxPath1().getPath();

        int refType = node.isPlSqlVarRef() ? ContextPath.PLSQL_NAME_REF : ContextPath.GENERIC_NAME_REF;
        println(ContextPathUtil.searchTarget2Text(refType) + " " + node.getText() + " [path] " + path);
        RefHolder ref = new RefHolder(refType, path, node.getText());
        ResolveDescriptor rhlp = resolver.resolveReference(ref);
        if (rhlp == null) {
            System.out.println("Cannot resolve: " + ref);
            failed++;
        } else {
            successed++;
            uniqueRefs.add(rhlp);
        }
    }

    public void visitSpecFunctionCall(SpecFunctionCall function) {
        // todo -- implement me
    }

    @Override
    protected void visitFunctionCall(FunctionCall elem) {
        refCounter++;

        ResolveDescriptor hlp = ResolveUtil.resolve(elem, new NameFragmentCallback() {
//            public ResolveDescriptor resolve(ResolveDescriptor hlp, CompositeElementExt node) {
            public ResolveDescriptor resolve(ResolveDescriptor hlp, PlSqlElement node) {
                if (node.getNode().getElementType() == PlSqlElementTypes.NAME_FRAGMENT) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                } else if (node.getNode().getElementType() == PlSqlElementTypes.EXEC_NAME_REF) {
                    ExecNameRef execName = (ExecNameRef) node; //(ExecNameRef) node.getPsi();
                    CallArgument[] args = execName.getCallArgumentList();
                    return resolver.resolveCallable(hlp,
                            ContextPath.FUNC_CALL, node.getCtxPath1().getPath(), node.getText(), args);
                }
                return null;
            }
        });

        if (hlp == null) {
            failed++;
            System.out.println("Cannot resolve Func call: " + elem.getFunctionName() + " Path: " + elem.getCtxPath1().getPath());
        } else {
            uniqueRefs.add(hlp);
            successed++;
        }
/*

        String path = node.getCtxPath1().getPath();
        println(ContextPathUtil.searchTarget2Text(ContextPath.FUNC_CALL) + " " + node.getFunctionName() + " [path] " + path);
        RefHolder rholder = new RefHolder(ContextPath.FUNC_CALL, path, node.getFunctionName());

        try {
            CallArgument[] args = node.getCallArgumentList();
            ResolveHelper rhlp = resolver.resolveCallable(
                    ContextPath.FUNC_CALL, path, node.getCompositeName().getText(), args
            );
            if (rhlp == null) {
                // not resolved?
                failed++;
                System.out.println("Cannot resolve: " + rholder);
            } else {
                successed++;
                uniqueRefs.add(rhlp);
            }

        } catch (ValidationException e) {
            failed++;
            System.out.println("Cannot resolve: " + rholder);
        } catch (Throwable e) {
            failed++;
            System.out.println("Cannot resolve: " + rholder);
        }
*/
    }

    @Override
    protected void visitProcedureCall(ProcedureCall elem) {
        refCounter++;

        ResolveDescriptor hlp = ResolveUtil.resolve(elem, new NameFragmentCallback() {
//            public ResolveDescriptor resolve(ResolveDescriptor hlp, CompositeElementExt node) {
            public ResolveDescriptor resolve(ResolveDescriptor hlp, PlSqlElement node) {
                if (node.getNode().getElementType() == PlSqlElementTypes.NAME_FRAGMENT) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                } else if (node.getNode().getElementType() == PlSqlElementTypes.EXEC_NAME_REF) {
                    ExecNameRef execName = (ExecNameRef) node;//(ExecNameRef) node.getPsi();
                    CallArgument[] args = execName.getCallArgumentList();
                    return resolver.resolveCallable(hlp,
                            ContextPath.PROC_CALL, node.getCtxPath1().getPath(), node.getText(), args);
                }
                return null;
            }
        });

        if (hlp == null) {
            failed++;
            System.out.println("Cannot resolve Proc call: " + elem.getFunctionName() + " Path: " + elem.getCtxPath1().getPath());
        } else {
            uniqueRefs.add(hlp);
            successed++;
        }
/*

        String path = node.getCtxPath1().getPath();
        println(ContextPathUtil.searchTarget2Text(ContextPath.PROC_CALL) + " " + node.getFunctionName() + " [path] " + path);
        RefHolder rholder = new RefHolder(ContextPath.PROC_CALL, path, node.getFunctionName());

        try {
            CallArgument[] args = node.getCallArgumentList();
            ResolveHelper rhlp = resolver.resolveCallable(
                    ContextPath.PROC_CALL, path, node.getCompositeName().getText(), args
            );

            if (rhlp == null) {
                // not resolved?
                failed++;
                System.out.println("Cannot resolve: " + rholder);
            } else {
                uniqueRefs.add(rhlp);
                successed++;
            }
        } catch (ValidationException e) {
            failed++;
            System.out.println("Cannot resolve: " + rholder);
        } catch (Throwable e) {
            failed++;
            System.out.println("Cannot resolve: " + rholder);
        }
*/
    }


    protected void visitCollectionMethodCall2(CollectionMethodCall2 elem) {
        refCounter++;
        ResolveDescriptor hlp = ResolveUtil.resolve(elem, new NameFragmentCallback() {
//            public ResolveDescriptor resolve(ResolveDescriptor hlp, CompositeElementExt node) {
            public ResolveDescriptor resolve(ResolveDescriptor hlp, PlSqlElement node) {
                if (node.getNode().getElementType() == PlSqlElementTypes.NAME_FRAGMENT) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                } else if (node.getNode().getElementType() == PlSqlElementTypes.EXEC_NAME_REF) {
                    ExecNameRef execName = (ExecNameRef) node;//(ExecNameRef) node.getPsi();
                    CallArgument[] args = execName.getCallArgumentList();
                    return resolver.resolveCallable(hlp,
                            ContextPath.PROC_CALL, node.getCtxPath1().getPath(), node.getText(), args);
                }
                return null;
            }
        });

        if (hlp == null) {
            failed++;
            System.out.println("Cannot resolve Coll2 Method call: " + elem.getText() + " Path: " + elem.getCtxPath1().getPath());
        } else {
            uniqueRefs.add(hlp);
            successed++;
        }
    }


    protected void visitCollectionMethodCall(CollectionMethodCall elem) {
        refCounter++;

        ResolveDescriptor hlp = ResolveUtil.resolve(elem, new NameFragmentCallback() {
//            public ResolveDescriptor resolve(ResolveDescriptor hlp, CompositeElementExt node) {
            public ResolveDescriptor resolve(ResolveDescriptor hlp, PlSqlElement node) {
                if (node.getNode().getElementType() == PlSqlElementTypes.NAME_FRAGMENT) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                } else if (node.getNode().getElementType() == PlSqlElementTypes.EXEC_NAME_REF) {
                    ExecNameRef execName = (ExecNameRef) node; //(ExecNameRef) node.getPsi();
                    CallArgument[] args = execName.getCallArgumentList();
                    return resolver.resolveCallable(hlp,
                            ContextPath.FUNC_CALL, node.getCtxPath1().getPath(), node.getText(), args);
                } else if (node.getNode().getElementType() == PlSqlElementTypes.C_RECORD_ITEM_REF) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                }
                return null;
            }
        });

        if (hlp == null) {
            failed++;
            System.out.println("Cannot resolve Coll Method call: " + elem.getText() + " Path: " + elem.getCtxPath1().getPath());
        } else {
            uniqueRefs.add(hlp);
            successed++;
        }
    }




    @Override
    protected void visitTableRef(TableRef table) {
        refCounter++;
        String path = table.getCtxPath1().getPath();
        String text = table.getText();
        println(ContextPathUtil.searchTarget2Text(ContextPath.TABLE_REF) + " " + text + " [path] " + path);

        RefHolder ref = new RefHolder(ContextPath.TABLE_REF, path, StringUtils.discloseDoubleQuotes(text));
        ResolveDescriptor rhlp = resolver.resolveReference(ref);
        if (rhlp == null) {
            System.out.println("Cannot resolve table: " + ref);
            failed++;
        } else {
            successed++;
            uniqueRefs.add(rhlp);
        }
    }


    protected void visitTypeNameReference(TypeNameReference node) {
        refCounter++;
        String path = node.getCtxPath1().getPath();
        Type t = node.getType();
        UserDefinedType udt = (UserDefinedType) t;
        println(ContextPathUtil.searchTarget2Text(ContextPath.TYPE_REF) + " " + udt.typeName() + " [path] " + path);
        RefHolder ref = new RefHolder(ContextPath.TYPE_REF, path, udt.typeName());
        ResolveDescriptor rhlp = resolver.resolveReference(ref);
        if (rhlp == null) {
            System.out.println("Cannot resolve: " + ref);
            failed++;
        } else {
            successed++;
            uniqueRefs.add(rhlp);
            
        }
    }


    protected void visitSequenceRef(SequenceRef node) {
        refCounter++;

        RefHolder ref = new RefHolder(ContextPath.SEQUENCE_REF, "", node.getText());
        ResolveDescriptor rhlp = resolver.resolveReference(ref);
        if (rhlp == null) {
            System.out.println("Cannot resolve: " + ref);
            failed++;
        } else {
            successed++;
            uniqueRefs.add(rhlp);
        }
    }

    @Override
    protected void visitColumnNameRef(ColumnNameRef node) {
        refCounter++;

        ResolveDescriptor rhlp = ResolveUtil.resolveColumnNameRef(resolver, node);
        if (rhlp == null) {
            System.out.println("Cannot resolve ColumnNameRef " + node.getText());
            failed++;
        } else {
            successed++;
            uniqueRefs.add(rhlp);
        }
    }

    @Override
    protected void visitColumnSpec(ColumnSpec node) {
        refCounter++;

        String path = node.getCtxPath1().getPath();
        println(ContextPathUtil.searchTarget2Text(ContextPath.GENERIC_NAME_REF) + " " + node.getText() + " [path] " + path);
        RefHolder ref = new RefHolder(ContextPath.GENERIC_NAME_REF, path, node.getText());
        ResolveDescriptor rhlp = resolver.resolveReference(ref);
        if (rhlp == null) {
            System.out.println("Cannot resolve: " + ref);
            failed++;
        } else {
            successed++;
            uniqueRefs.add(rhlp);
        }

    }

    protected void visitElement(PsiElement element) {
        PsiElement child = element.getFirstChild();
        while (child != null) {
            IElementType itype = child.getNode().getElementType();
            if (!PsiElementUtil.toSkip.contains(itype)) {
                try {
                    child.accept(visitor);
                } catch (Throwable e) {
                    System.err.println("Resolving issue: " + e.getMessage());
                }
            }
            child = child.getNextSibling();
        }
    }


    private void println(String s) {
        try {
            logger.append(s).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
