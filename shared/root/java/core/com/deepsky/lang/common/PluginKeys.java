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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.common;

import com.deepsky.database.CacheManager;
import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ObjectCache;
import com.deepsky.lang.conf.PluginSettingsBean;
import com.deepsky.view.query_pane.QueryResultWindow;


public final class PluginKeys {

    public static final PluginKey<CacheManager> CACHE_MANAGER = PluginKey.create(SharedConstants.CACHE_MANAGER);
    public static final PluginKey<ConnectionManager> CONNECTION_MANAGER = PluginKey.create(SharedConstants.CONNECTION_MANAGER);
    public static final PluginKey<ObjectCache> OBJECT_CACHE = PluginKey.create(SharedConstants.OBJECT_CACHE);

    public static final PluginKey<QueryResultWindow> QR_WINDOW = PluginKey.create(SharedConstants.QR_WINDOW);
    public static final PluginKey<PluginSettingsBean> PLUGIN_SETTINGS = PluginKey.create(SharedConstants.PLUGIN_SETTINGS);
//    public static final PluginKey<SqlSearchParameters> SEARCH_PARAMS = PluginKey.create(SharedConstants.SEARCH_PARAMS);
}
