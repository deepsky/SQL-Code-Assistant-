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

package com.deepsky.navigation;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.cache.Cache;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.struct.*;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbObjectContributorImpl implements DbObjectContributor {

    int updateCounter = -1;
    
    Cache cache;
    PlSqlElementCache namesCache = new PlSqlElementCache();

    public String[] getNames(Project project, boolean includeNonProjectItems) {
        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        if(ocache.isReady()){
            int _updateCounter = ocache.getUpdateCounter();
            if(_updateCounter != updateCounter){
                cache = ocache.getCache(ocache.getCurrentUser());
                String[] types = cache.listTypes();
                updateCounter = _updateCounter;
                namesCache.clean();
                // populate names cache
                for(String type: types){
                    loadNames(cache, type);
                }
            } else {
                // nothing changed
            }
        } else {
            namesCache.clean();
            updateCounter = -1;
        }
        return namesCache.getNames();
    }

    public NavigationItem[] getItemsByName(String name, String pattern, Project project) {
        return namesCache.findItem(project, name);
    }


    private void loadNames(Cache cache, String type){
        if(DbObject.FUNCTION_BODY.equals(type)){
            for(String func: cache.getNames(type)){
                DbObject dbObject = cache.get(func, type);
                if(dbObject != null){
                    namesCache.add(dbObject);
                }
            }
        } else if(DbObject.PROCEDURE_BODY.equals(type)){
            for(String proc: cache.getNames(type)){
                DbObject dbObject = cache.get(proc, type);
                if(dbObject != null){
                    namesCache.add(dbObject);
                }
            }
        } else if(DbObject.PACKAGE.equals(type)){
            // load declarations of Variables, Types
            List<String> names = cache.getNames(type);
            for(String pkgName: names){
                DbObject dbObject = cache.get(pkgName, type);
                namesCache.add(pkgName, type);
                loadNames((PackageDescriptor)dbObject, namesCache);
            }
        } else if(DbObject.PACKAGE_BODY.equals(type)){
            // load declarations of Variables, Types, Functions, Procedures
            List<String> names = cache.getNames(type);
            for(String pkgName: names){
                DbObject dbObject = cache.get(pkgName, type);
                namesCache.add(pkgName, type);
                loadNames((PackageBodyDescriptor)dbObject, namesCache);
            }
        } else if(DbObject.SEQUENCE.equals(type)){
            for(String seq: cache.getNames(type)){
                namesCache.add(seq, type);
            }
        } else if(DbObject.SYNONYM.equals(type)){
            for(String synonym: cache.getNames(type)){
                namesCache.add(synonym, type);
            }
        } else if(DbObject.TABLE.equals(type)){
            for(String table: cache.getNames(type)){
                namesCache.add(table, type);
            }
        } else if(DbObject.TRIGGER.equals(type)){
            for(String trigger: cache.getNames(type)){
                namesCache.add(trigger, type);
            }
        } else if(DbObject.TYPE.equals(type)){
            for(String udt: cache.getNames(type)){
                namesCache.add(udt, type);
            }
        } else if(DbObject.VIEW.equals(type)){
            for(String view: cache.getNames(type)){
                namesCache.add(view, type);
            }
        }
    }


    private void loadNames(PackageDescriptor pdesc, PlSqlElementCache names){
        for(DbObject dbo: pdesc.getObjects()){
            if(dbo instanceof VariableDescriptor){
                namesCache.add(dbo);
            } else if(dbo instanceof UserDefinedTypeDescriptor){
                namesCache.add(dbo);
            } else if(dbo instanceof FunctionDescriptor){
                namesCache.add(dbo);
            } else if(dbo instanceof ProcedureDescriptor){
                namesCache.add(dbo);
            }
        }
    }

    private void loadNames(PackageBodyDescriptor pdesc, PlSqlElementCache names){
        for(DbObject dbo: pdesc.getObjects()){
            if(dbo instanceof VariableDescriptor){
                namesCache.add(dbo);
            } else if(dbo instanceof UserDefinedTypeDescriptor){
                namesCache.add(dbo);
            } else if(dbo instanceof FunctionDescriptor){
                namesCache.add(dbo);
            } else if(dbo instanceof ProcedureDescriptor){
                namesCache.add(dbo);
            }
        }
    }

   class PlSqlElementCache {
        Map<String, List<String>> name2uidlist = new HashMap<String, List<String>>();

        public void add(String _name, String type){
            String encoded = DbObjectUtil.createUID(_name, type);
            String name = _name.toLowerCase();
            List<String> types = name2uidlist.get(name);
            if(types == null){
                types = new ArrayList<String>();
                name2uidlist.put(name, types);
            }

            types.add(encoded);
        }

        public void add(DbObject dbo){
            String encoded = DbObjectUtil.dbo2UID(dbo);
            String name = dbo.getName().toLowerCase();
            List<String> types = name2uidlist.get(name);
            if(types == null){
                types = new ArrayList<String>();
                name2uidlist.put(name, types);
            }

            types.add(encoded);
        }

        public NavigationItem[] findItem(Project project, String name){
            List<String> types = name2uidlist.get(name.toLowerCase());
            if(types != null){
                List<NavigationItem> out = new ArrayList<NavigationItem>();
                for (String encoded : types) {
                    DbObject target = null;
                    String pkgName = DbObjectUtil.extractPackageName(encoded);
                    if(pkgName != null){
                        String pkgType = DbObjectUtil.extractPackageType(encoded);
                        DbObject pkg = cache.get(pkgName, pkgType);
                        if(pkg instanceof PackageDescriptor){
                            String objName = DbObjectUtil.extractObjectName(encoded);
                            for(DbObject dbo : ((PackageDescriptor) pkg).findObjectByName(objName)){
                                if(DbObjectUtil.dbo2UID(dbo).equals(encoded)){
                                    target = dbo;
                                    break;
                                }
                            }
                        }
                    } else {
                        String objName = DbObjectUtil.extractObjectName(encoded);
                        String objType = DbObjectUtil.extractObjectType(encoded);
                        target = cache.get(objName, objType);
                    }

                    if(target != null){
                        PlSqlElement elem = SqlScriptManager.mapToPsiTree(project, target);
                        if(elem instanceof NavigationItem){
                            out.add((NavigationItem) elem);
                        }
                    }
                }
                return out.toArray(new NavigationItem[out.size()]);
            } else {
                return new NavigationItem[0];
            }
        }

        public String[] getNames(){
            return name2uidlist.keySet().toArray(new String[name2uidlist.keySet().size()]);
        }

        public void clean() {
            name2uidlist.clear();
        }
    }

}
