PACKAGE dbms_logmnr IS

  --------------------
  -- OVERVIEW
  --
  --   This package contains the procedures used by LogMiner ad-hoc query
  --   interface that allows for redo log stream analysis.
  --   There are three procedures and two functions available to the user:
  --   dbms_logmnr.add_logfile()    : to register logfiles to be analyzed
  --   dbms_logmnr.remove_logfile() : to remove logfiles from being analyzed
  --   dbms_logmnr.start_logmnr()   : to provide window of analysis and
  --                                  meta-data information
  --   dbms_logmnr.end_logmnr()     : to end the analysis session
  --   dbms_logmnr.column_present() : whether a particular column value
  --                                  is presnet in a redo record
  --   dbms_logmnr.mine_value()     : extract data value from a redo record
  --

  ---------------------------
  --  PROCEDURE INFORMATION:
  --  #1 dbms_logmnr.add_logfile():
  --     DESCRIPTION:
  --       Registers a redo log file with LogMiner. Multiple redo logs can be
  --       registered by calling the procedure repeatedly. The redo logs
  --       do not need to be registered in any particular order.
  --       Both archived and online redo logs can be mined.  If a successful
  --       call to the procedure is made a call to start_logmnr() must be
  --       made before selecting from v$logmnr_contents.
  --
  --     CONSTANTS:
  --       dbms_logmnr.NEW:  Starts a new list. Any file registered prior to
  --         this call is discarded.
  --       dbms_logmnr.ADDFILE:  Adds the redo log to the existing
  --         list. LogMiner checks to make sure that the redo log is from
  --         the same database (DB_ID and RESETLOGS_SCN) incarnation as the
  --         ones previously added.
  --
  --     EXCEPTIONS:
  --       ORA: 1284   Redo log file specified can not be opened.
  --       ORA: 1285   Error reading the header of the redo log file
  --       ORA: 1286   Redo log file specified is from a database with a
  --                   different DB_ID
  --       ORA: 1287   Redo log file specified is from a database with
  --                   different incarnation
  --       ORA: 1289   Redo log file specified is a duplicate of a previously
  --                   specified redo log. LogMiner matches redo logs by the
  --                   log sequence number. Thus two redo logs with different
  --                   names but with the same log sequence# (for instance
  --                   the online counterpart of an archived redo log has
  --                   a different name, but attempting to register it with
  --                   LogMiner after registering the archived counterpart
  --                   will return this exception).
  --
  --  #2 dbms_logmnr.remove_logfile()
  --     DESCRIPTION:
  --       Unregisters a redo log file from LogMiner. Multiple redo logs can be
  --       unregistered by calling the procedure repeatedly. The redo logs
  --       do not need to be unregistered in any particular order.  If a
  --       successful call to the procedure is made a call to start_logmnr()
  --       must be made before selecting from v$logmnr_contents.
  --
  --     EXCEPTIONS:
  --       ORA: 1290   Attempt was made to remove a redo log that has not been
  --                   registered with LogMiner
  --
  --  #3 dbms_logmnr.start_logmnr()
  --     DESCRIPTION:
  --       Loads the data dictionary used by LogMiner to translate internal
  --       schema object identifiers to names. The redo stream does not
  --       contain names of schema objects and columns. The data dictionary
  --       extract can be provided in three ways:
  --         (i) use Oracle's online catalog. This is only valid when the
  --         mining of redo logs is done in the same system that generated
  --         them.
  --         (ii) use data dictionary extract taken to a flat file.
  --         (See description of dbms_logmnr_d.build())
  --         (iii) use data dictionary extracted in the redo stream. This
  --         option can ONLY be used when LogMiner is being run on an open
  --         database, and the source and the mining database instances are
  --         Oracle9i or higher.
  --       The user can also restrict the analysis inside an SCN range or a
  --       time range. If both SCN range and time range are specified, only
  --       the SCN range is used.
  --       The user needs to be mindful of the fact that use of time range
  --       can be imprecise.  If a start_time or start_scn is specified, it
  --       must be contained in a redo log added by a previous call to
  --       dbms_logmnr.add_logfile().  If a start_time and start_scn is not
  --       specified, LogMiner will set it based on the earliest added redo
  --       log.  If a end_time or end_scn is specified and it is beyond the
  --       latest added redo log, LogMiner will overwrite the end_time and
  --       and end_scn with information from the latest added redo log.  When
  --       the CONTINOUS_MINE option is in use the semantics of
  --       start and end time/scn ranges may be different.
  --       See additional documentation below.
  --
  --       CONSTANTS (used in options parameter)
  --       dbms_logmnr.NO_DICT_RESET_ONSELECT:  (will be deprecated soon)
  --       dbms_logmnr.COMMITED_DATA_ONLY: Groups DMLs belonging to the
  --         same transaction. Transactions are shown in their commit order.
  --         Internal redo records (those related to index operations, space
  --         management etc) are filtered out. So are rolled back
  --         transactions, rollback to savepoints and in-flight transactions.
  --       dbms_logmnr.SKIP_CORRUPTION: Usually LogMiner returns an error
  --         on encountering corrupt redo records. With this option set
  --         LogMiner will skip the corrupted redo records and continue
  --         mining. LogMiner can not handle a redo log that has a corrupt
  --         header.
  --       dbms_logmnr.DDL_DICT_TRACKING: LogMiner will apply the DDL
  --         statements encountered in the redo stream to its internal
  --         dictionary. Only available with Oracle9i redo logs and later.
  --         Mining database needs to be open.
  --       dbms_logmnr.DICT_FROM_ONLINE_CATALOG: Use the online data
  --         dictionary for SQL reconstruction. Mining database must be the
  --         same one that generated the redo logs. User should expect to
  --         see "Dictionary Version Mismatch" in SQL_REDO if the current
  --         object version is newer than the ones encountered in the redo
  --         stream.
  --       dbms_logmnr.DICT_FROM_REDO_LOGS: Use the dictionary extract logged
  --         in the redo stream.
  --       dbms_logmnr.NO_SQL_DELIMITER: By default, the SQL_REDO and SQL_UNDO
  --         statements are delimited with a ';'. However, this is
  --         inconvenient for applications that want to open a cursor and
  --         execute the reconstructed statements. With this option set,
  --         the SQL_DELIMITER is not placed at the end of reconstructed
  --         statements.
  --       dbms_logmnr.NO_ROWID_IN_STMT: By default, the SQL_REDO and SQL_UNDO
  --         statements for UPDATE and DELETE operations contain a 'ROWID = '
  --         in the where clause.  However, this is inconvenient for
  --         applications that want to re-execute the SQL statement.  With
  --         this option set, 'ROWID' is not placed at the end of reconstructed
  --         statements.  Note: The onus is on the user to ensure that
  --         supplemental logging was enabled in the source database at the
  --         appropriate level and that no duplicate rows exist in tables of
  --         interest.  LogMiner Adhoc Query does NOT make any quarantee
  --         regarding uniqueness of logical row identifiers.
  --       dbms_logmnr.PRINT_PRETTY_SQL: Useful for basic report for
  --         analysis. With large number of columns the reconstructed
  --         SQL statements become visually busy. With this option set
  --         LogMiner formats the reconstructed SQL statements for ease
  --         of reading. The reconstructed SQL statements look as follow:
  --            insert into "SCOTT"."EMP" values
  --              EMPNO: 101010,
  --              ENAME: "Valued Employee",
  --              SAL:   101010,
  --              DEPT:  NULL;
  --             update "SCOTT"."EMP"
  --              set
  --              "EMPNO" = 101011 and
  --              "SAL"   = 101011
  --              where
  --              "EMPNO" = 101010 and
  --              "SAL"   = 101010 and
  --              ROWID   = AABBCEXFGHA;
  --       dbms_logmnr.CONTINUOUS_MINE: Need to mine in the same instance
  --         that is generating the redo logs. The user needs to register
  --         only one archived log file. LogMiner will automatically add
  --         and mine subsequent archived redo logs, and eventually
  --         mine online logfiles.
  --
  --    EXCEPTIONS:
  --      ORA: 1281     startScn or endSCN parameter specified is not a valid
  --                    SCN or endScn is greater then startScn
  --      ORA: 1282     startTime parameter is greater than year 2110 or
  --                    endTime parameter is greater than year 2110 or
  --                    startTime parameter is less then year 1988
  --      ORA: 1283     The value specified in the Options parameter is not a
  --                    NUMBER or is not a known LogMiner Adhoc option
  --      ORA: 1284     The dictionary file specified in the DictFileName
  --                    parameter has a full path length greater then 256 or
  --                    cannot be opened
  --      ORA: 1285     DictFileName parameter is not a valid VARCHAR2
  --      ORA: 1291     Redo files are missing which are needed to satisfy
  --                    the user's requested SCN/time range.
  --                    The user can specify ALLOW_MISSING_LOGS option.
  --                    Missing logs are not allowed under any circumstance
  --                    when DDL tracking is in use
  --      ORA: 1292     No log file has been registered with LogMiner
  --      ORA: 1293     Mounted database required for options specified
  --                    (CONTINIOUS_MINE)
  --      ORA: 1294     Error while processing the data dictionary extract
  --      ORA: 1295     DB_ID of the data dictionary does not match that of
  --                    the redo logs
  --      ORA: 1296     Character set specified in the data dictionary does
  --                    not match (and is incompatible with) that of the
  --                    mining database
  --      ORA: 1297     Redo version mismatch between the dictionary and
  --                    the registered redo logs
  --      ORA: 1298     More than one dictionary source was specified or
  --                    DDL_DICT_TRACKING was requested with
  --                    DICT_FROM_ONLINE_CATALOG
  --      ORA: 1299     Dictionary is from a different database incarnation
  --      ORA: 1300     Writable database required for options specified
  --                    (DDL_DICT_TRACKING, DICT_FROM_REDO_LOGS,
  --                     DICT_FROM_ONLINE_CATALOG)
  --      ORA: 1371     A logfile containing the dictionary dump to redo logs
  --                    is missing
  --      ORA: 1286     Options specified require start time or start SCN
  --
  --  #4 dbms_logmnr.end_logmnr()
  --     DESCRIPTION:
  --       Ends the LogMiner session. Releases all PGA memory allocated
  --       to stage internal data structures etc.
  --
  --     EXCEPTIONS:
  --       ORA: 1307    No LogMiner session is currently active.
  --                    Attempt to end_logmnr() without calling
  --                    add_logfile() or start_logmnr()
  --
  --  #5 dbms_logmnr.mine_value()
  --     DESCRIPTION:
  --       This facilitates query by data value. For instance, the user
  --       can formulate a query that says "Show me all updates to
  --       SCOTT.EMP where the SAL column is updated to twice its
  --       original value"
  --       select sql_redo from v$logmnr_contents where
  --           operation = 'UPDATE" and
  --           owner_name = 'SCOTT' and seg_name = 'EMP' and
  --         dbms_logmnr.mine_value(redo_value, 'SCOTT.EMP.SAL') >
  --         2* dbms_logmnr.mine_value(undo_value, 'SCOTT.EMP.SAL');
  --      The function returns NULL if the column does not exist in
  --      the redo record or if the column value is actually null.
  --      To decipher between the two different null possibilities
  --      use dbms_logmnr.column_present() function.
  --
  --      PARAMETERS:
  --        sql_redo_undo:  which column in v$logmnr_contents to
  --        extract data value from
  --        column_name:    fully qualified column name of the
  --        column that needs to be extracted
  --
  --      EXCEPTIONS:
  --      ORA 1302:     Specified table or column does not exist
  --
  --  #6 dbms_logmnr.column_present()
  --     DESCRIPTION:
  --       Can be used to decipher null returns from mine_value function
  --       The query described above can be rewritten to filter out
  --       redo records that do not contain update to the 'SAL'
  --       columns
  --         select sql_redo from v$logmnr_contents where
  --           operation = 'UPDATE"
  --           owner_name = 'SCOTT' and seg_name = 'EMP' and
  --           dbms_logmnr.mine_value(redo_value, 'SCOTT.EMP.SAL') >
  --           2* dbms_logmnr.mine_value(undo_value, 'SCOTT.EMP.SAL') and
  --           dbms_logmnr.column_present(redo_value, 'SCOTT.EMP.SAL');
  --
  --      PARAMETERS:
  --        sql_redo_undo:  which column in v$logmnr_contents to
  --        extract data value from
  --        column_name:    fully qualified column name of the
  --        column that needs to be extracted
  --
  --      EXCEPTIONS:
  --      ORA 1302:     Specified table or column does not exist
  --
  ---------------------------------

-----------------------------------
-- SUBTYPES and related CONSTANTS
--

--
-- Constants for add_archivelog options flag

NEW                       CONSTANT BINARY_INTEGER := 1;
REMOVEFILE                CONSTANT BINARY_INTEGER := 2;
ADDFILE                   CONSTANT BINARY_INTEGER := 3;

--
-- Constants for start_logmnr options flag
NO_DICT_RESET_ONSELECT    CONSTANT BINARY_INTEGER := 1;
COMMITTED_DATA_ONLY       CONSTANT BINARY_INTEGER := 2;
SKIP_CORRUPTION           CONSTANT BINARY_INTEGER := 4;
DDL_DICT_TRACKING         CONSTANT BINARY_INTEGER := 8;
DICT_FROM_ONLINE_CATALOG  CONSTANT BINARY_INTEGER := 16;
DICT_FROM_REDO_LOGS       CONSTANT BINARY_INTEGER := 32;
NO_SQL_DELIMITER          CONSTANT BINARY_INTEGER := 64;
PRINT_PRETTY_SQL          CONSTANT BINARY_INTEGER := 512;
CONTINUOUS_MINE           CONSTANT BINARY_INTEGER := 1024;
NO_ROWID_IN_STMT          CONSTANT BINARY_INTEGER := 2048;
--
SUBTYPE Length            IS BINARY_INTEGER;
SUBTYPE ThreadId          IS BINARY_INTEGER;

--
-- Constants for STATUS column of v$logmnr_contents
-- NOTE: Make sure that new ones match the values defined
-- in the krvfsri struct in krv0.h
VALID_SQL                 CONSTANT BINARY_INTEGER := 0;
INVALID_SQL               CONSTANT BINARY_INTEGER := 2;
UNGUARANTEED_SQL          CONSTANT BINARY_INTEGER := 3;
CORRUPTED_BLK_IN_REDO     CONSTANT BINARY_INTEGER := 4;
ASSEMBLY_REQUIRED_SQL     CONSTANT BINARY_INTEGER := 5;
HOLE_IN_LOGSTREAM         CONSTANT BINARY_INTEGER := 1291;

-- Workaround for the lack of constrained subtypes

LogFileNameTemplate          VARCHAR2(256);
SUBTYPE LogFileName          IS LogFileNameTemplate%TYPE;
LogFileDescTemplate          VARCHAR2(256);
SUBTYPE LogFileDescription   IS LogFileDescTemplate%TYPE;


-------------
-- PROCEDURES
--

---------------------------------------------------------------------------
---------------------------------------------------------------------------
-- Initialize LOGMINER
--
-- Supplies LOGMINER with the list of filenames and SCNs required
-- to initialize the tool.  Once this procedure completes, the server is ready
-- to process selects against the v$logmnr_contents fixed view.
--
---------------------------------------------------------------------------

PROCEDURE start_logmnr(
     startScn           IN  NUMBER default 0 ,
     endScn 		IN  NUMBER default 0,
     startTime      	IN  DATE default '',
     endTime        	IN  DATE default '',
     DictFileName    	IN  VARCHAR2 default '',
     Options		IN  BINARY_INTEGER default 0 );

PROCEDURE add_logfile(
     LogFileName    	IN  VARCHAR2,
     Options		IN  BINARY_INTEGER default ADDFILE );

PROCEDURE end_logmnr;

FUNCTION column_present(
     sql_redo_undo      IN  NUMBER default 0,
     column_name        IN  VARCHAR2 default '') RETURN BINARY_INTEGER;

FUNCTION mine_value(
     sql_redo_undo      IN  NUMBER default 0,
     column_name        IN  VARCHAR2 default '') RETURN VARCHAR2;

PROCEDURE remove_logfile(
     LogFileName    	IN  VARCHAR2);

---------------------------------------------------------------------------

pragma TIMESTAMP('1998-05-05:11:25:00');

END;