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

package com.deepsky.utils.persistence;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersistenceManager {

    Logger log = Logger.getLogger(this.getClass());

    // example of format: 0000000004UPDATE.....0

    final static String format = "{0,number,0000000000}";
    final static String UPDATE_MSG = "UPDATE.....";
    final static String CHKT_STRT_MSG = "CHKOUTSTRT.";
    final static String CHKT_END_MSG = "CHKOUTEND..";

    Persistable p;
    private File targetDir;

    String[] lastCheckout = new String[0];
    List<String> updates = new ArrayList<String>();

    private int limit = 100;
    long sequence = 0;
    boolean appendMode = true;

    int logNumber = 0;
    File activeLog;

    String baseLogName;
    String LOG_0 = "log.0.txt";
    String LOG_1 = "log.1.txt";

    public void init() throws Exception {
        if (targetDir == null || !targetDir.exists()) {
            throw new Exception("Check targetDir property");
        }

        if(baseLogName != null){
            LOG_0 = baseLogName + ".0";
            LOG_1 = baseLogName + ".1";    
        } else {
            // leave default log names
        }

        restoreState();
    }

    public void setPersistableObject(Persistable p) {
        this.p = p;
    }

    public void cleanupState() {
        activeLog = deleteLogs();
        lastCheckout = new String[0];
        updates = new ArrayList<String>();
        sequence = 0;
        appendMode = true;
    }

    public void saveUpdate(String update) {
        updates.add(update);
        if (updates.size() > limit) {
            lastCheckout = p.getFullState();
            updates.clear();
            saveCheckout(lastCheckout);
        } else {
            saveIncrementUpdate(update);
        }
    }

    public void saveUpdate(List<String> batchUpdate) {
        updates.addAll(batchUpdate);
        if (updates.size() > limit) {
            lastCheckout = p.getFullState();
            updates.clear();
            saveCheckout(lastCheckout);
        } else {
            saveBatchUpdate(batchUpdate);
        }
    }

    private void saveIncrementUpdate(String update) {
        try {
            Writer out = new FileWriter(activeLog, appendMode);
            MessageFormat mf = new MessageFormat(format + "{1}{2}");
            String header = mf.format(new Object[]{new Long(++sequence), UPDATE_MSG, logNumber});

            out.write(header + update + "\n");
            out.close();

            appendMode = true;

        } catch (IOException e) {
            log.error("Could not save increment update!", e);
        }
    }

    private void saveBatchUpdate(List<String> batchUpdate) {
        try {
            Writer out = new FileWriter(activeLog, appendMode);
            MessageFormat mf = new MessageFormat(format + "{1}{2}");

            for(String s: batchUpdate){
                String header = mf.format(new Object[]{new Long(++sequence), UPDATE_MSG, logNumber});
                out.write(header + s + "\n");
            }
            out.close();

            appendMode = true;

        } catch (IOException e) {
            log.error("Could not save increment update!", e);
        }
    }

    private void saveCheckout(String[] lastCheckout) {
        try {
            Writer out = new FileWriter(activeLog, appendMode);
            MessageFormat mf = new MessageFormat(format + "{1}{2}");
            String header = mf.format(new Object[]{new Long(++sequence), CHKT_STRT_MSG, logNumber});
            out.write(header + "\n");

            for (String s : lastCheckout) {
                out.write(s + "\n");
            }
            String tail = mf.format(new Object[]{new Long(++sequence), CHKT_END_MSG, logNumber});
            out.write(tail + "\n");
            out.close();

            // switch the log
            switchLogs();

        } catch (IOException e) {
            log.error("Could not save increment update!", e);
        }
    }

    private File deleteLogs() {
        new File(targetDir, LOG_0).delete();
        new File(targetDir, LOG_1).delete();

        logNumber = 0;
        return new File(targetDir, LOG_0);
    }

    private void restoreState() {

        List<EncapsulatedCommand> comms = new ArrayList<EncapsulatedCommand>();
        boolean log0 = parseLog(new File(targetDir, LOG_0), comms);
        boolean log1 = parseLog(new File(targetDir, LOG_1), comms);

        if (!log0 && !log1) {
            cleanupState();
        } else {
            // sort in the reverse order
            Collections.sort(comms, new Comparator<EncapsulatedCommand>() {
                public int compare(EncapsulatedCommand o1, EncapsulatedCommand o2) {
                    return new Long(o2.seq).compareTo(o1.seq);
                }
            });

            //
            boolean first = false;
            lastCheckout = null;
            updates.clear();
            for (int i = 0; i < comms.size() && lastCheckout == null; i++) {
                EncapsulatedCommand ec = comms.get(i);
                if (!first) {
                    sequence = ec.seq;
                    logNumber = ec.logNumber;
                    first = true;
                }
                switch (ec.message) {
                    case UPDATE:
                        updates.add((String) ec.object);
                        break;
                    case CHKOUT_END:
                        lastCheckout = (String[]) ec.object;
                        break;
                }
            }

            if (lastCheckout == null) {
                lastCheckout = new String[0];
            }
            Collections.reverse(this.updates);
            activeLog = (logNumber == 0) ? new File(targetDir, LOG_0) : new File(targetDir, LOG_1);
        }
    }

    private void switchLogs() {
        if (activeLog.getName().equals(LOG_0)) {
            // active log will be LOG_1
            activeLog = new File(targetDir, LOG_1);
            logNumber = 1;
        } else {
            // active log will be LOG_0
            activeLog = new File(targetDir, LOG_0);
            logNumber = 0;
        }
        appendMode = false;
    }


    private boolean parseLog(File file, List<EncapsulatedCommand> comms) {
        Message lastMessage = Message.UPDATE;
        List<String> chkList = new ArrayList<String>();
        MessageFormat mf = new MessageFormat(format);

        int _logNumber = 0;
        long _seq;

        BufferedReader r = null;
        if (file != null && file.exists()) {
            // parse it
            try {
                r = new BufferedReader(new FileReader(file));
                String s;
                while ((s = r.readLine()) != null) {

                    if (s.length() >= 22) {
                        String seq = s.substring(0, 10);
                        if (s.substring(10, 21).equals(UPDATE_MSG)) {
                            // ...
                            _seq = ((Long) mf.parse(seq, new ParsePosition(0))[0]);
                            _logNumber = Integer.parseInt(s.substring(21, 22));
                            comms.add(new EncapsulatedCommand(
                                    _seq,
                                    _logNumber,
                                    Message.UPDATE,
                                    s.substring(22)));
                        } else if (s.substring(10, 21).equals(CHKT_STRT_MSG)) {
                            // ...
                            lastMessage = Message.CHKOUT_START;
                            chkList.clear();
                        } else if (s.substring(10, 21).equals(CHKT_END_MSG)) {
                            // ...
                            _seq = ((Long) mf.parse(seq, new ParsePosition(0))[0]);
                            _logNumber = Integer.parseInt(s.substring(21, 22));
                            comms.add(new EncapsulatedCommand(
                                    _seq,
                                    _logNumber,
                                    Message.CHKOUT_END,
                                    chkList.toArray(new String[0])));
                        } else if (lastMessage == Message.CHKOUT_START) {
                            // checkout data
                            chkList.add(s);
                        } else {
                            // very strange !!!!
                            // todo
                        }
                    } else if (lastMessage == Message.CHKOUT_START) {
                        // checkout data
                        chkList.add(s);
                    }
                }

                return true; //new LogHelper(luSequence, lastMessage, lcSequence, chk, updates, file);

            } catch (FileNotFoundException e) {
                log.error("Could not parser log file: " + file.toString(), e);
            } catch (IOException e) {
                log.error("Could not parser log file: " + file.toString(), e);
            } finally {
                if (r != null) {
                    try {
                        r.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        return false;
    }


    class EncapsulatedCommand {
        long seq;
        Message message;
        int logNumber;
        Object object;

        public EncapsulatedCommand(long seq, int logNumber, Message message, Object object) {
            this.seq = seq;
            this.logNumber = logNumber;
            this.message = message;
            this.object = object;
        }
    }

    enum Message {
        UPDATE,
        CHKOUT_START,
        CHKOUT_END
    }

    /**
     * Get the last saved full state (last checkout)
     *
     * @return
     */
    public String[] getFullState() {
        return lastCheckout;
    }

    /**
     * Get list of updates since the last checkout
     *
     * @return list of updates
     */
    public String[] getListOfUpdates() {
        return updates.toArray(new String[0]);
    }

    public File getTargetDir() {
        return targetDir;
    }

    public void setTargetDir(File targetDir) {
        this.targetDir = targetDir;
    }

    public String getBaseLogName(){
        return baseLogName;
    }

    public void setBaseLogName(String baseLogName){
        this.baseLogName = baseLogName;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
