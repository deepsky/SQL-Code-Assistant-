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

package com.deepsky.lang.plsql.resolver.factory;

import com.deepsky.integration.plsql.PlSqlElementType;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.ContextPathProvider;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.FileElement;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class ContextPathManager {


    private static ContextPathProvider.CtxPath getPath(ASTNode current, int ctxType) {
        // todo -- is context path caching really needed?
//        if (current.cachedCtxPath == null) {
            ContextPathProvider.CtxPath parentPath = getParentCtxPath(current);

            String seqString = ContextPathUtil.encodeSeqNum(getIDInParentCtx(current));
/*
            current.cachedCtxPath = new ContextPathUtil.CtxPathImpl(
                    parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, seqString + "$"
                    )
            );
//        }
        return current.cachedCtxPath;
*/

        return new ContextPathUtil.CtxPathImpl(
                parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, seqString + "$"
                )
        );
    }


    private static ContextPathProvider.CtxPath getPath(ASTNode current, int ctxType, String name) {
//        if (current.cachedCtxPath == null) {
            ContextPathProvider.CtxPath parentPath = getParentCtxPath(current);

            String seqString = ContextPathUtil.encodeSeqNum(getIDInParentCtx(current));
/*
            current.cachedCtxPath = new ContextPathUtil.CtxPathImpl(
                    parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, seqString + "$" + name
                    )
            );
//        }
        return current.cachedCtxPath;
*/

        return new ContextPathUtil.CtxPathImpl(
                parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, seqString + "$" + name
                )
        );
    }

    private static ContextPathProvider.CtxPath getPathNoSequence(ASTNode current, int ctxType) {
//        if (current.cachedCtxPath == null) {
            ContextPathProvider.CtxPath parentPath = getParentCtxPath(current);
/*
            current.cachedCtxPath = new ContextPathUtil.CtxPathImpl(
                    parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, "..$"
                    )
            );
//        }
        return current.cachedCtxPath;
*/
        return new ContextPathUtil.CtxPathImpl(
                parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, "..$"
                )
        );
    }

    private static ContextPathProvider.CtxPath getPathNoSequence(ASTNode current, int ctxType, String name) {
//        if (current.cachedCtxPath == null) {
            ContextPathProvider.CtxPath parentPath = getParentCtxPath(current);
/*
            current.cachedCtxPath = new ContextPathUtil.CtxPathImpl(
                    parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, "..$" + name
                    )
            );
//        }
        return current.cachedCtxPath;
*/

        return new ContextPathUtil.CtxPathImpl(
                parentPath.getPath() + ContextPathUtil.encodeCtx(ctxType, "..$" + name
                )
        );
    }


    private static int getIDInParentCtx(ASTNode current) {
        ASTNode parent = current.getTreeParent();
        int deep = 0;
        while (parent != null) {
            IElementType ietype = parent.getElementType();
            if (ietype == PlSqlTokenTypes.FILE || contextAwareElements().contains(ietype)) {

                int[] id = new int[1];
                if(find1(id, parent, current)){
                    return id[0];
                }
/*
                ASTNode child = parent.getFirstChildNode();
                while (child != null) {
                    if (child == current) {
                        return id;
                    } else if (contextAwareElements().contains(child.getElementType())) {
                        id++;
                    }

                    child = child.getTreeNext();
                }
*/
                break;
            } else {
                deep++;
                parent = parent.getTreeParent();
            }
        }

        // should not happen!!!
        return -1;
    }


    private static boolean find1(int[] id, ASTNode parent, ASTNode current){
        ASTNode child = parent.getFirstChildNode();

        while (child != null) {
            if (child instanceof LeafElement) {
                // skip to the next children
            } else if (child == current) {
                return true;
            } else if (contextAwareElements().contains(child.getElementType())) {
                id[0] = id[0]+1;
            } else {
                if(find1(id, child, current)){
                    return true;
                }
            }

            child = child.getTreeNext();
        }

        return false;
    }


    private static ContextPathProvider.CtxPath getParentCtxPath(ASTNode current) {
        ASTNode parent = current.getTreeParent();
        if (parent instanceof FileElement) {
            return ((PlSqlElement) parent.getPsi()).getCtxPath1();
        } else {
            return ((ContextPathProvider) parent).getCtxPath1();
        }
    }

    public static ContextPathProvider.CtxPath getCtxPath(ASTNode current1) {
        ASTNode current = current1;
        IElementType ietype = current.getElementType();

        while (ietype != PlSqlTokenTypes.FILE) {
            int ttype = PlSqlElementType.mapTo_TokenType(ietype);

            ContextPathProvider.CtxPath ctx = findPath(ttype, current);
            if (ctx != null) {
                return ctx;
            }

            current = current.getTreeParent();
            ietype = current.getElementType();
        }

        // special case: PsiFile
        return ((PlSqlElement) current.getPsi()).getCtxPath1();
//        return ((PlSqlFile) current.getPsi()).getCtxPath1();
    }


    private static ContextPathProvider.CtxPath findPath(int ttype, ASTNode node) {
        Idx key = new Idx(ttype, -1);
        int index = Arrays.binarySearch(SEARCH_ARRAY, key, new ComparatorImpl());
        try {
            return index >= 0 ? wrappers[SEARCH_ARRAY[index].pos].getCtxPath(node) : null;
        } catch (SyntaxTreeCorruptedException e) {
            return null;
        }
    }


    static abstract class ElemFactoryWrapper {
        PlSqlElementType elementType;

        public ElemFactoryWrapper(PlSqlElementType elementType) {
            this.elementType = elementType;
        }

        public abstract ContextPathProvider.CtxPath getCtxPath(ASTNode node);
    }

    public static TokenSet contextAwareElements() {
        return ctxAware;
    }


    private static ElemFactoryWrapper[] wrappers = new ElemFactoryWrapper[0];
    private static Idx[] SEARCH_ARRAY = new Idx[0];
    private static TokenSet ctxAware = TokenSet.create(PlSqlTokenTypes.FILE);

    static {
        List<ElemFactoryWrapper> list = new ArrayList<ElemFactoryWrapper>();

        // Start initializion
        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CURSOR_DECLARATION) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.CURSOR_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.CURSOR_DECL, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CREATE_TRIGGER) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TRIGGER_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                String triggerName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.CREATE_TRIGGER, triggerName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.PACKAGE_SPEC) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.PACKAGE_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String packageName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.PACKAGE_SPEC, packageName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.PACKAGE_BODY) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.PACKAGE_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String packageName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.PACKAGE_BODY, packageName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.PARAMETER_SPEC) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.PARAMETER_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.ARGUMENT, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.PLSQL_BLOCK) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.PLSQL_BLOCK);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.VARIABLE_DECLARATION) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.VARIABLE_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.VARIABLE_DECL, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CREATE_SYNONYM) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.SYNONYM_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.SYNONYM, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CREATE_SEQUENCE) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                String sequenceName = ((CreateSequence) current.getPsi()).getSequenceName();
                return getPath( current, ContextPath.SEQUENCE, sequenceName.toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.TABLE_DEF) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String tableName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.TABLE_DEF, tableName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CREATE_TEMP_TABLE) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TABLE_NAME_DDL);
                if (child == null) throw new SyntaxTreeCorruptedException();
                String tableName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.TABLE_DEF, tableName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.COLUMN_DEF) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.COLUMN_NAME_DDL);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String columnName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.COLUMN_DEF, columnName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.FUNCTION_BODY) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String name = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.FUNCTION_BODY, name);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.FUNCTION_SPEC) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String name = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.FUNCTION_SPEC, name);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.PROCEDURE_BODY) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String name = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.PROCEDURE_BODY, name);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.PROCEDURE_SPEC) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
                if (child == null)
                    throw new SyntaxTreeCorruptedException();
                String name = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.PROCEDURE_SPEC, name);
            }
        });


        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.SELECT_EXPRESSION_UNION) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.SELECT_EXPR_UNION);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.SELECT_EXPRESSION) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.SELECT_EXPR);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.SUBQUERY) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.SUBQUERY);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.INSERT_COMMAND) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.INSERT_STMT);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.MERGE_COMMAND) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.MERGE_STMT);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.UPDATE_COMMAND) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.UPDATE_STMT);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.DELETE_COMMAND) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.DELETE_STMT);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CREATE_VIEW) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.VIEW_NAME_DDL);
                if (child == null) throw new SyntaxTreeCorruptedException();
                String viewName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.VIEW_DEF, viewName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CREATE_VIEW_COLUMN_DEF) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.VIEW_NAME_DDL);
                if (child == null) throw new SyntaxTreeCorruptedException();
                String viewName = StringUtils.discloseDoubleQuotes(child.getText()).toLowerCase();
                return getPath( current, ContextPath.VIEW_DEF, viewName);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.VARRAY_COLLECTION) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TYPE_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.VARRAY_TYPE, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.TABLE_COLLECTION) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TYPE_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.COLLECTION_TYPE, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.OBJECT_TYPE_DEF) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TYPE_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.OBJECT_TYPE, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.RECORD_TYPE_DECL) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TYPE_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.RECORD_TYPE, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.RECORD_ITEM) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.RECORD_ITEM_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.RECORD_ITEM, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.REF_CURSOR) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                ASTNode child = current.findChildByType(PLSqlTypesAdopted.TYPE_NAME);
                if (child == null) throw new SyntaxTreeCorruptedException();
                return getPath( current, ContextPath.REF_CURSOR, child.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.LOOP_STATEMENT) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPath( current, ContextPath.LOOP_STMT);
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.NUM_LOOP_INDEX) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPathNoSequence( current, ContextPath.LOOP_INDEX, current.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CURSOR_LOOP_INDEX) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPathNoSequence( current, ContextPath.LOOP_INDEX, current.getText().toLowerCase());
            }
        });

        list.add(new ElemFactoryWrapper(PLSqlTypesAdopted.CURSOR_LOOP_SPEC) {
            public ContextPathProvider.CtxPath getCtxPath(ASTNode current) {
                return getPathNoSequence( current, ContextPath.CURSOR_LOOP_SPEC); //, current.getText().toLowerCase());
            }
        });


        // Complete section ----------------------------------------------------------------
        List<Idx> search_array = new ArrayList<Idx>();
        int pos = 0;
        for (ElemFactoryWrapper w : list) {
            search_array.add(new Idx(PlSqlElementType.mapTo_TokenType(w.elementType), pos));
            ctxAware = TokenSet.orSet(ctxAware, TokenSet.create(w.elementType));
            pos++;
        }

//        Collections.sort(search_array, new Comparator<Idx>(){
//            public int compare(Idx o1, Idx o2) {
//                return o1.ttype - o2.ttype;
//            }
//        });

        SEARCH_ARRAY = search_array.toArray(new Idx[search_array.size()]);
        Arrays.sort(SEARCH_ARRAY, new ComparatorImpl());

        wrappers = list.toArray(new ElemFactoryWrapper[list.size()]);
        // ----------------------------------------------------------------
    }


    static private class ComparatorImpl implements Comparator<Idx> {
        public int compare(Idx o1, Idx o2) {
            return o1.ttype - o2.ttype;
        }
    }

    static private class Idx {
        public int ttype;
        public int pos;

        public Idx(int ttype, int pos) {
            this.ttype = ttype;
            this.pos = pos;
        }
    }
}
