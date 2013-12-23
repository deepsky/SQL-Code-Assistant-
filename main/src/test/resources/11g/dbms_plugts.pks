PACKAGE dbms_plugts IS

  TS_EXP_BEGIN   CONSTANT binary_integer := 1;
  TS_EXP_END     CONSTANT binary_integer := 2;

  /**********************************************
  **   Routines called directly by EXPORT      **
  **********************************************/

  --++
  -- Definition:  This procedure constructs the beginImport call in an
  --              anonymous PL/SQL block.
  --
  -- Inputs:      None
  --
  -- Outputs:     None
  --++
  PROCEDURE beginExport;

  --++
  -- Definition:  This procedure verifies tablespaces are read-only during
  --              export. It is called for each tablespace specified
  --
  -- Inputs:      tsname = tablespace name to verify
  --
  -- Outputs:     None
  --
  -- Possible Exceptions:
  --              ts_not_found
  --              ts_not_read_only
  --++
  PROCEDURE beginExpTablespace (
        tsname  IN varchar2);

  --++
  -- Definition:  This procedure verifies objects are self-contained in the
  --              tablespaces specified.
  --
  -- Inputs:      incl_constraints = 1 if include constraints, 0 otherwise
  --              incl_triggers    = 1 if include triggers, 0 otherwise
  --              incl_grants      = 1 if include grants, 0 otherwise
  --              full_closure     = TRUE if both IN and OUT pointers are
  --                                 considered violations
  --                                 (should be TRUE for TSPITR)
  --
  -- Outputs:     None
  --
  -- Possible Exceptions:
  --              ORA-29341 (not_self_contained)
  --++
  PROCEDURE checkPluggable (
        incl_constraints        IN number,
        incl_triggers           IN number,
        incl_grants             IN number,
        full_check              IN number,
        do_check                IN number DEFAULT 1);

  --++
  -- Definition:  This function returns the next line of a block that has been
  --              previously selected for retrieval via selectBlock.
  --
  -- Inputs:      None
  --
  -- Outputs:     None
  --
  -- Returns:     A string to be appended to the export file.
  --++
  FUNCTION getLine
    RETURN varchar2;

  --++
  -- Definition:  This procedures selects a particular PL/SQL anonymous block
  --              for retrieval.
  --
  -- Inputs:      blockID = the ID to pick a PL/SQL anonymous block
  --                        dbms_plugts.TS_EXP_BEGIN at the beginning of export
  --                        dbms_plugts.TS_EXP_END at the end of export
  -- Outputs:     None
  --++
  PROCEDURE selectBlock (
        blockID         IN binary_integer);

  /**********************************************
  **   Routines called directly by IMPORT      **
  **********************************************/

  --++
  -- Definition:  The procedure informs the dbms_Plugts package about the
  --              location of the new datafiles. If the file can not be found,
  --              an error will be signaled, possible at a later point.
  --
  -- Inputs:      filename = file name (including path)
  --
  -- Outputs:     None
  --++
  PROCEDURE newDatafile (
        filename        IN varchar2);

  --++
  -- Definition:  This procedure informs the dbms_plugts package about
  --              tablespace name to be included in the job.
  --
  -- Inputs:      tsname - Tablespace name
  --
  -- Outputs:     None
  --++
  PROCEDURE newTablespace (
        tsname          IN varchar2);

  --++
  -- Definition:   This procedure adds a user to the import job.
  --
  -- Inputs:       usrname - user name
  --
  -- Outputs:      None
  --++
  PROCEDURE pluggableUser (
        usrname         IN varchar2);

  --++
  -- Definition:  This procedure informs the plugts package about remap_user
  --              information.
  --
  -- Inputs:      from_user - a user in FROM_USER list
  --              to_user   - the corresponding user in TO_USER list
  --
  -- Outputs:     None
  --++
  PROCEDURE mapUser (
        from_user       IN varchar2,
        to_user         IN varchar2);

  --++
  -- Definition:  This procedure informs the plugts package about
  --              REMAP_TABLESPACE information.
  --
  -- Inputs:      from_ts - a tablespace name to be remapped
  --              to_ts   - the new corresponding tablespace to be created
  --
  -- Outputs:     None
  --++
  PROCEDURE mapTs (
        from_ts         IN varchar2,
        to_ts           IN varchar2);

  /*******************************************************************
  **  Routines called automatically via the PL/SQL anonymous block  **
  *******************************************************************/
  --++
  -- Definition:  This procedure informs the plugts package about the target
  --              tablespaces and it's owner. It checks to make sure the
  --              tablespace name does not conflict with any existing
  --              tablespaces already in the database. It verifies the block
  --              size is the same as that in the target database. If all this
  --              succeeds, it begins importing metadata for the tablespace.
  --              This procedure call appears in the export file.
  --
  --              The parameter list includes all columns for ts$, except those
  --              that will be discarded (online$, undofile#, undoblock#,
  --              ownerinstance, backupowner).  The spares are included so that
  --              the interface does not have to be changed even when these
  --              spares are used in the future.
  --
  --              Three extra parameters are added for transporting migrated
  --              tablespaces. seg_fno, seg_bno and seg_blks represent the
  --              dictionary information held in SEG$ for any tablespace which
  --              was migrated from dictionary managed to locally managed. The
  --              file# and block# give the location of bitmap space header for
  --              the migrated tablespace and the blocks parameter represents
  --              the size of the space header in blocks.
  --
  -- Inputs:      tsname          - tablespace name
  --              tsID            - tablespace ID in original database
  --              owner           - owner of tablespace
  --              n_files         - number of datafiles in the tablespace
  --              contents        - contents column of ts$ (TEMP/PERMANENT)
  --              blkSize         - size of block in bytes
  --              inc_num         - incarnation number of extent
  --              clean_SCN       - tablespace clean SCN,
  --              dflminext       - default minimum number of extents
  --              dflmaxext       - default maximum number of extents
  --              dflinit         - default initial extent size
  --              dflincr         - default initial extent size
  --              dflminlen       - default minimum extent size
  --              dflextpct       - default percent extent size increase
  --              dflogging       - default logging attribute
  --              affstrength     - Affinity strength
  --              bitmapped       - If bitmapped
  --              dbID            - database ID
  --              directallowed   - allowed
  --              flags           - flags
  --              creation_SCN    - tablespace creation SCN
  --              groupname       - Group name
  --              spare1          - spare1 in ts$
  --              spare2          - spare2 in ts$
  --              spare3          - spare3 in ts$
  --              spare4          - spare4 in ts$
  --              seg_fno         - file# for space_hdr in seg$
  --              seg_bno         - block# for space_hdr in seg$
  --              seg_blks        - blocks, size of space_hdr in seg$
  --
  -- Outputs:     None
  --++
  PROCEDURE beginImpTablespace (
        tsname          IN varchar2,
        tsID            IN number,
        owner           IN varchar2,
        n_files         IN binary_integer,
        contents        IN binary_integer,
        blkSize         IN binary_integer,
        inc_num         IN binary_integer,
        clean_SCN       IN number,
        dflminext       IN number,
        dflmaxext       IN number,
        dflinit         IN number,
        dflincr         IN number,
        dflminlen       IN number,
        dflextpct       IN binary_integer,
        dflogging       IN binary_integer,
        affstrength     IN number,
        bitmapped       IN number,
        dbID            IN number,
        directallowed   IN number,
        flags           IN binary_integer,
        creation_SCN    IN number,
        groupname       IN varchar2,
        spare1          IN number,
        spare2          IN number,
        spare3          IN varchar2,
        spare4          IN date,
        seg_fno         IN number DEFAULT 0,
        seg_bno         IN number DEFAULT 0,
        seg_blks        IN number DEFAULT 0);

  --++
  -- Definition:  This procedure checks to see that the user name in the
  --              pluggable set matches that entered by the DBA via the import
  --              USERS command line option. Make sure that, after the user
  --              mappings, the required user is already in the database. This
  --              procedure call appears in the export file.
  --
  -- Inputs:      username - user name
  --
  -- Outputs:     None
  --++
  PROCEDURE checkUser (
        username        IN varchar2);

  --++
  -- Definition:  This procedure passes the information about the pluggable set
  --              to the PL/SQL package. Among them is the release version of
  --              the Oracle executable that created the pluggable set, which
  --              is used for checking compatibility.  This procedure call
  --              appears in the export file.
  --
  -- Inputs:      clone_oracle_version - release version of Oracle executable
  --                                     that created the pluggable set
  --              charsetID            - character set ID
  --              ncharsetID           - nchar set ID, in varchar2 format
  --                                     (May be NULL if generated by 8.1.5)
  --              platformID           - platform ID
  --              platformName         - platform name
  --              highest_data_objnum  - highest data object # in pluggable set
  --              highest_lob_sequence - highest LOB seq # in pluggable set
  --              n_ts                 - number of tablespace to be plugged in
  --              has_clobs            - if tablespaces have CLOB data
  --              has_nchars           - if tablespaces have nchar data
  --              char_smeantics_on    - if tablespaces have char semantic data
  --
  -- Outputs:     None
  --++
  PROCEDURE beginImport (
        clone_oracle_version    IN varchar2,
        charsetID               IN binary_integer,
        ncharsetID              IN varchar2,
        srcplatformID           IN binary_integer,
        srcplatformName         IN varchar2,
        highest_data_objnum     IN number,
        highest_lob_sequence    IN number,
        n_ts                    IN number,
        has_clobs               IN number DEFAULT 1,
        has_nchars              IN number DEFAULT 1,
        char_semantics_on       IN number DEFAULT 1);

  --++
  -- Definition:  This procedure checks and adjusts the version for each
  --              compatibility type. This procedure is in the export file.
  --
  -- Inputs:      compID - compatibility type name
  --              compRL - release level
  --
  -- Outputs:     None
  --++
  PROCEDURE checkCompType (
        compID          IN varchar2,
        compRL          IN varchar2);

  --++
  -- Definition:  This procedure calls statically linked C routines to
  --              associate the datafile with the tablespace and validates file
  --              headers. This procedure appears in the export file.
  --
  --              The parameter list includes all columns in file$, except
  --              those that will be discarded (status$, ownerinstance).
  --
  -- Inputs:      name             - file name (excluding path)
  --              databaseID       - database ID
  --              absolute_fno     - absolute file number
  --              curFileBlks      - size of file in blocks
  --              tablespace_ID    - tablespace ID in original database
  --              relative_fno     - relative file number
  --              maxextend        - maximum file size
  --              inc              - increment amount
  --              creation_SCN     - file creation SCN
  --              checkpoint_SCN   - file checkpoint SCN
  --              reset_SCN        - file reset SCN
  --              spare1           - spare1 in file$
  --              spare2           - spare2 in file$
  --              spare3           - spare3 in file$
  --              spare4           - spare4 in file$
  --
  -- Outputs:     None
  --++
  PROCEDURE checkDatafile (
        name            IN varchar2,
        databaseID      IN number,
        absolute_fno    IN binary_integer,
        curFileBlks     IN number,
        tablespace_ID   IN number,
        relative_fno    IN binary_integer,
        maxextend       IN number,
        inc             IN number,
        creation_SCN    IN number,
        checkpoint_SCN  IN number,
        reset_SCN       IN number,
        spare1          IN number,
        spare2          IN number,
        spare3          IN varchar2,
        spare4          IN date);

  --++
  -- Definition:  This procedure wraps up the tablespace check. This procedure
  --              call appears in the export file.
  --
  -- Inputs:      None
  --
  -- Outputs:     None
  --++
  PROCEDURE endImpTablespace;

  --++
  -- Definition:  This procedure calls a statically linked C routine to
  --              atomically plug-in the pluggable set. This procedure call
  --              appears in the export file.
  --
  -- Inputs:      None
  --
  -- Outputs:     None
  --++
  PROCEDURE commitPluggable;

  --++
  -- Definition:  This procedure reclaims a segment by calling the statically
  --              linked C routine kcp_plg_reclaim_segment.  This procedure
  --              call appears in the export file.
  --
  -- Inputs:      The parameters match seg$ columns exactly. See seg$
  --              description.
  --
  -- Outputs:     NOne
  --++
  PROCEDURE reclaimTempSegment (
        file_no         IN binary_integer,
        block_no        IN binary_integer,
        type_no         IN binary_integer,
        ts_no           IN binary_integer,
        blocks          IN binary_integer,
        extents         IN binary_integer,
        iniexts         IN binary_integer,
        minexts         IN binary_integer,
        maxexts         IN binary_integer,
        extsize         IN binary_integer,
        extpct          IN binary_integer,
        user_no         IN binary_integer,
        lists           IN binary_integer,
        groups          IN binary_integer,
        bitmapranges    IN number,
        cachehint       IN binary_integer,
        scanhint        IN binary_integer,
        hwmincr         IN binary_integer,
        spare1          IN binary_integer,
        spare2          IN binary_integer);

  --++
  -- Definition:  This procedure does any final cleanup to end the import job.
  --
  -- Inputs:      None
  --
  -- Outputs:     None
  --++
  PROCEDURE endImport;

  --++
  -- Definition:  This procedure gets the db char set properties of the
  --              tablespaces in sys.tts_tbs$
  --
  -- Inputs:      None
  --
  -- Outputs:     has_clobs       - tablespaces have clobs columns
  --              has_nchars      - tablespaces have nchars columns
  --              char_semantics  - has character semantics columns
  --++
  PROCEDURE get_db_char_properties (
        has_clobs       OUT binary_integer,
        has_nchars      OUT binary_integer,
        char_semantics  OUT binary_integer);

  /*******************************************************************
  **               Possible Exceptions                              **
  *******************************************************************/
  ts_not_found                  EXCEPTION;
  PRAGMA exception_init         (ts_not_found, -29304);
  ts_not_found_num              NUMBER := -29304;

  ts_not_read_only              EXCEPTION;
  PRAGMA exception_init         (ts_not_read_only, -29335);
  ts_not_read_only_num          NUMBER := -29335;

  internal_error                EXCEPTION;
  PRAGMA exception_init         (internal_error, -29336);
  internal_error_num            NUMBER := -29336;

  datafile_not_ready            EXCEPTION;
  PRAGMA exception_init         (datafile_not_ready, -29338);
  datafile_not_ready_num        NUMBER := -29338;

  blocksize_mismatch            EXCEPTION;
  PRAGMA exception_init         (blocksize_mismatch, -29339);
  blocksize_mismatch_num        NUMBER := -29339;

  exportfile_corrupted          EXCEPTION;
  PRAGMA exception_init         (exportfile_corrupted, -29340);
  exportfile_corrupted_num      NUMBER := -29340;

  not_self_contained            EXCEPTION;
  PRAGMA exception_init         (not_self_contained, -29341);
  not_self_contained_num        NUMBER := -29341;

  user_not_found                EXCEPTION;
  PRAGMA exception_init         (user_not_found, -29342);
  user_not_found_num            NUMBER := -29342;

  mapped_user_not_found         EXCEPTION;
  PRAGMA exception_init         (mapped_user_not_found, -29343);
  mapped_user_not_found_num     NUMBER := -29343;

  user_not_in_list              EXCEPTION;
  PRAGMA exception_init         (user_not_in_list, -29344);
  user_not_in_list_num          NUMBER := -29344;

  different_char_set            EXCEPTION;
  PRAGMA exception_init         (different_char_set, -29345);
  different_char_set_num        NUMBER := -29345;

  invalid_ts_list               EXCEPTION;
  PRAGMA exception_init         (invalid_ts_list, -29346);
  invalid_ts_list_num           NUMBER := -29346;

  ts_not_in_list                EXCEPTION;
  PRAGMA exception_init         (ts_not_in_list, -29347);
  ts_not_in_list_num            NUMBER := -29347;

  datafiles_missing             EXCEPTION;
  PRAGMA exception_init         (datafiles_missing, -29348);
  datafiles_missing_num         NUMBER := -29348;

  ts_name_conflict              EXCEPTION;
  PRAGMA exception_init         (ts_name_conflict, -29349);
  ts_name_conflict_num          NUMBER := -29349;

  sys_or_tmp_ts                 EXCEPTION;
  PRAGMA exception_init         (sys_or_tmp_ts, -29351);
  sys_or_tmp_ts_num             NUMBER := -29351;

  ts_list_overflow              EXCEPTION;
  PRAGMA exception_init         (ts_list_overflow, -29353);
  ts_list_overflow_num          NUMBER := -29353;

  ts_failure_list               EXCEPTION;
  PRAGMA exception_init         (ts_failure_list, -39185);
  ts_failure_list_num           NUMBER := -39185;

  ts_list_empty                 EXCEPTION;
  PRAGMA exception_init         (ts_list_empty, -39186);
  ts_list_empty_num             NUMBER := -39186;

  not_self_contained_list       EXCEPTION;
  PRAGMA exception_init         (not_self_contained_list, -39187);
  not_self_contained_list_num   NUMBER := -39187;

  /******************************************************************
  **             Interface for testing, etc.                       **
  ******************************************************************/
  PROCEDURE init;

  --++
  -- Description:  Initialize global variables used for debugging trace
  --               messages
  --
  -- Inputs:       debug_flags: Trace/debug flags from /TRACE param or
  --                            trace/debug event, possibly including global
  --                            trace/debug flags
  --
  -- Outputs:      None
  --++
  PROCEDURE SetDebug (
    debug_flags IN BINARY_INTEGER);
  --++
  -- Description: This procedure will send a message to the trace file using
  --              KUPF$FILE.TRACE.
  --
  -- Inputs:
  --      msg                     - message to print
  --
  -- Outputs:
  --      None
  --+
  PROCEDURE SendTraceMsg (
    msg         IN VARCHAR2);

  /*******************************************************************
  **               Interface for trusted callouts                   **
  *******************************************************************/
  -- begin export
  PROCEDURE kcp_bexp(
        vsn             OUT varchar2,           -- Oracle server version
        dobj_half       OUT binary_integer,     -- half of data obj#
        dobj_odd        OUT binary_integer);    -- lowest bit of data obj#

  -- get char, nchar ID and name
  PROCEDURE kcp_getchar(
        cid             OUT binary_integer,     -- char ID
        ncid            OUT binary_integer);    -- nchar ID

  -- check if char, nchar set match (signal error is not)
  PROCEDURE kcp_chkchar(
        cid             IN binary_integer,      -- char ID
        ncid            IN binary_integer,      -- nchar ID
        chknc           IN binary_integer,      -- chech nchar (1 or 0)
        has_clobs       IN binary_integer,
        has_nchars      IN binary_integer,
        char_semantics_on IN binary_integer);

  -- allocate datafile and tablespace structures
  PROCEDURE kcp_aldfts(
        n_dfiles        IN binary_integer,      -- number of datafiles
        n_ts            IN binary_integer);     -- number of tablespaces

  -- read file header
  PROCEDURE kcp_rdfh(
        fname           IN varchar2);

  -- convert sb4 to ub4
  FUNCTION sb4_to_ub4 (
        b               IN binary_integer)
    RETURN number;

  -- new tablespace
  PROCEDURE kcp_newts(
        tsname          IN varchar2,            -- tablespace name
        tsid            IN binary_integer,      -- ts ID
        n_files         IN binary_integer,      -- # of datafiles in ts
        blksz           IN binary_integer,      -- block size
        inc_num         IN binary_integer,      -- inc #
        cleanSCN        IN number,              -- cleanSCN
        dflminext       IN binary_integer,      -- dflminext in ts$
        dflmaxext       IN binary_integer,      -- dflmaxext in ts$
        dflinit         IN binary_integer,      -- dflinit in ts$
        dflincr         IN binary_integer,      -- dflincr in ts$
        dflminlen       IN binary_integer,      -- dflminlen in ts$
        dflextpct       IN binary_integer,      -- dflextpct in ts$
        dflogging       IN binary_integer,      -- dflogging in ts$
        bitmapped       IN binary_integer,      -- bitmapped in ts$
        dbID            IN binary_integer,      -- db ID
        crtSCN          IN number,              -- creation SCN
        contents        IN binary_integer,      -- contents$ in ts$
        flags           IN binary_integer,      -- flags in ts$
        seg_fno         IN binary_integer,      -- file# in seg$
        seg_bno         IN binary_integer,      -- block# in seg$
        seg_blks        IN binary_integer);     -- blocks in seg$

  -- Plug in datafile
  PROCEDURE kcp_plgdf(
        dbID            IN binary_integer,      -- database ID
        afn             IN binary_integer,      -- absolute file #
        fileBlks        IN binary_integer,      -- size of file in blocks
        tsID            IN binary_integer,      -- tablespace ID
        rfn             IN binary_integer,      -- relative file #
        maxextend       IN binary_integer,
        inc             IN binary_integer,
        crtSCN          IN number,              -- creation SCN
        cptSCN          IN number,              -- checkpoint SCN
        rstSCN          IN number,              -- reset SCN
        spare1          IN binary_integer);     -- spare1 in file$

  -- Commit Pluggable
  PROCEDURE kcp_cmt (
        data_objn       IN binary_integer);     -- data object number

  -- Initialize kernel data structures
  PROCEDURE kcp_init;

  -- adjust compatibility level
  PROCEDURE kcp_acomp (
        compID          IN varchar2,            -- compatibility type
        compRL          IN varchar2);           -- release level

  -- get current compatible setting
  --
  PROCEDURE kcp_getcomp (szcomp  OUT varchar2);           -- compatible setting

  -- get file header infomation according to file number
  PROCEDURE kcp_getfh (
        afn             IN  binary_integer,     -- absolute file number
        dbID            OUT binary_integer,     -- database ID
        ckpt_SCN        OUT varchar2,           -- checkpoint SCN
        reset_SCN       OUT varchar2,           -- reset log SCN
        hdr_afn         OUT binary_integer);    -- file# from header

  -- verification checks needed for cross platform transport
  PROCEDURE kcp_chkxPlatform(
        srcplatformID   IN binary_integer,
        srcplatformName IN varchar2,
        tgtplatformID   IN binary_integer,
        tgtplatformName IN varchar2,
        src_rls_version IN varchar2);

  -- fix up seg$ to reclaim a temp segment
  PROCEDURE kcp_plg_reclaim_segment(
        file_no         IN binary_integer,
        block_no        IN binary_integer,
        type_no         IN binary_integer,
        ts_no           IN binary_integer,
        blocks          IN binary_integer,
        extents         IN binary_integer,
        iniexts         IN binary_integer,
        minexts         IN binary_integer,
        maxexts         IN binary_integer,
        extpct          IN binary_integer,
        user_no         IN binary_integer,
        lists           IN binary_integer,
        groups          IN binary_integer,
        bitmapranges    IN binary_integer,
        cachehint       IN binary_integer,
        scanhint        IN binary_integer,
        hwmincr         IN binary_integer,
        spare1          IN binary_integer,
        spare2          IN binary_integer);

  -- compute whether a plug into a specified db char and nchar set is
  -- compatible with current db.
  PROCEDURE kcp_check_tts_char_set_compat(
        has_clobs               IN binary_integer,
        has_nchars              IN binary_integer,
        char_semantics_on       IN binary_integer,
        target_charset_name     IN varchar2,
        target_ncharset_name    IN varchar2);

END dbms_plugts;