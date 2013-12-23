package dbms_utility is

$if utl_ident.is_oracle_server <> TRUE and
    utl_ident.is_timesten <> TRUE $then
  $error 'dbms_utility is not supported in this environment' $end
$end

  ------------
  --  OVERVIEW
  --
  --  This package provides various utility routines.

  ----------------------------
  --  PL/SQL TABLES
  --
  type uncl_array IS table of VARCHAR2(227) index by BINARY_INTEGER;
  --  Lists of "USER"."NAME"."COLUMN"@LINK should be stored here

  type name_array IS table of VARCHAR2(30) index by BINARY_INTEGER;
  --  Lists of NAME should be stored here

  type lname_array IS table of VARCHAR2(4000) index by BINARY_INTEGER;
  --  Lists of Long NAME should be stored here, it includes fully
  --  qualified attribute names.

  type maxname_array IS table of VARCHAR2(32767) index by BINARY_INTEGER;
  --  Lists of large VARCHAR2s should be stored here

  type dblink_array IS table of VARCHAR2(128) index by BINARY_INTEGER;
  --  Lists of database links should be stored here

  TYPE index_table_type IS TABLE OF BINARY_INTEGER INDEX BY BINARY_INTEGER;
  --  order in which objects should be generated is returned here

  TYPE number_array IS TABLE OF NUMBER INDEX BY BINARY_INTEGER;
  --  order in which objects should be generated is returned here for users.

  TYPE instance_record IS RECORD (
       inst_number   NUMBER,
       inst_name     VARCHAR2(60));

  TYPE instance_table IS TABLE OF instance_record INDEX BY BINARY_INTEGER;
  -- list of active instance number and instance name
  -- the starting index of instance_table is 1
  -- instance_table is dense

$if utl_ident.is_oracle_server $then
  TYPE anydata_array IS TABLE OF AnyData INDEX BY BINARY_INTEGER;
  -- array of anydata
$else
  /* AnyData type is not supported */
$end

  SUBTYPE maxraw IS RAW(32767);

  ----------------------------
  --  PROCEDURES AND FUNCTIONS
  --
  procedure compile_schema(schema varchar2, compile_all boolean default TRUE,
                           reuse_settings boolean default FALSE);
  --  Compile all procedures, functions, packages and triggers in the specified
  --  schema.  After calling this procedure you should select from view
  --  ALL_OBJECTS for items with status of 'INVALID' to see if all objects
  --  were successfully compiled.  You may use the command "SHOW ERRORS
  --  <type> <schema>.<name>" to see the errors assocated with 'INVALID'
  --  objects.
  --  Input arguments:
  --    schema
  --      Name of the schema.
  --    compile_all
  --      This is a boolean flag that indicates whether we should compile all
  --      schema objects or not, regardless of whether the object is currently
  --      flagged as valid or not. The default is to support the previous
  --      compile_schema() behaviour and compile ALL objects.
  --    reuse_settings
  --      This is a boolean flag that indicates whether the session settings in
  --      the objects should be reused, or whether the current session settings
  --      should be picked up instead.
  --  Exceptions:
  --    ORA-20000: Insufficient privileges for some object in this schema.
  --    ORA-20001: Cannot recompile SYS objects.

   /*
    * NAME:
    *   validate
    *
    * PARAMETERS:
    *   object_id  (IN) - ID number of object to be validated. This is
    *                     the same as the value of the OBJECT_ID column
    *                     from ALL_OBJECTS.
    *
    * DESCRIPTION:
    *   This procedure validates a database object if it is in status
    *   4, 5, or 6 using the same mechanism that is used for automatic
    *   re-validation.
    *
    * EXCEPTIONS:
    *   None. No errors are raised if the object doesn't exist or is
    *   already valid or is an object that cannot be validated.
    */
  procedure validate(object_id number);

   /*
    * NAME:
    *   validate
    *
    * PARAMETERS:
    *   owner     (IN) - name of the user who owns the object. Same as
    *                    the OWNER field in ALL_OBJECTS
    *   objname   (IN) - name of the object to be validated. Same as the
    *                    OBJECT_NAME field in ALL_OBJECTS
    *   namespace (IN) - namespace of the object. Same as the namespace
    *                    field in obj$
    *   edition   (IN) - Edition that object resides in. Same as edition_name
    *                    field in ALL_OBJECTS.
    *
    * DESCRIPTION:
    *   This procedure validates a database object using the same
    *   mechanism that is used for automatic re-validation.
    *
    * EXCEPTIONS:
    *   None. No errors are raised if the object doesn't exist or is
    *   already valid or is an object that cannot be validated.
    */

  procedure validate(owner varchar2, objname varchar2, namespace number,
                     edition varchar2 :=
$if utl_ident.is_oracle_server $then
                       sys_context('USERENV', 'CURRENT_EDITION_NAME'));
$elsif utl_ident.is_timesten $then
  -- no edition support for TimesTen, default it to null
                       null);
$end

  inv_not_exist_or_no_priv exception;
  pragma exception_init(inv_not_exist_or_no_priv, -24237);

  inv_malformed_settings exception;
  pragma exception_init(inv_malformed_settings, -24238);

  inv_restricted_object exception;
  pragma exception_init(inv_restricted_object, -24239);

  /*
   * Option flags supported by invalidate.
   *   inv_error_on_restrictions - The invalidate routine imposes various
   *   restrictions on the objects that can be invalidated. For example,
   *   the object specified by p_object_id cannot be a table. By default,
   *   invalidate quietly returns on these conditions (and does not raise
   *   an exception). If the caller sets this flag, the exception
   *   inv_restricted_object is raised.
   */
  inv_error_on_restrictions constant pls_integer := 1;

   /*
    * NAME:
    *   invalidate
    *
    * PARAMETERS:
    *   p_object_id (IN)
    *     ID number of object to be invalidated. This is the same as the
    *     value of the OBJECT_ID column from ALL_OBJECTS.
    *
    *     If the object_id argument is null or invalid then the exception
    *     inv_not_exist_or_no_priv is raised.
    *
    *     The caller of this procedure must have create privileges on the
    *     object being invalidated else inv_not_exist_or_no_priv exception
    *     is raised.
    *
    *   p_plsql_object_settings (IN)
    *     This optional parameter is ignored if the object specified by
    *     p_object_id is not a PL/SQL object.
    *
    *     If no value is specified for this parameter then the PL/SQL
    *     compiler settings are left unchanged, that is, equivalent to
    *     REUSE SETTINGS.
    *
    *     If a value is provided, it must specify the values of the PL/SQL
    *     compiler settings separated by one or more spaces. Each setting
    *     can be specified only once else inv_malformed_settings exception
    *     will be raised. The setting values are changed only for the object
    *     specified by p_object_id and do not affect dependent objects that
    *     may be invalidated.
    *
    *     The setting names and values are case insensitive.
    *
    *     If a setting is omitted and REUSE SETTINGS is specified, then if a
    *     value was specified for the compiler setting in an earlier
    *     compilation of this library unit, Oracle Database uses that earlier
    *     value. If a setting is omitted and REUSE SETTINGS was not specified
    *     then the database will obtain the value for that setting from the
    *     session environment.
    *
    *     Note that an empty, non-null, string can be passed in.  This will
    *     cause all compiler settings to be read from the session environment.
    *
    *   p_option_flags (IN)
    *     Option flags supported (see note above). This parameter is optional
    *     and defaults to zero (no flags).
    *
    * DESCRIPTION:
    *   This procedure invalidates a database object and (optionally) modifies
    *   its PL/SQL compiler parameter settings. It also invalidates any
    *   objects that (directly or indirectly) depend on the object being
    *   invalidated.
    *
    *   The object type (object_type column from ALL_OBJECTS) of the object
    *   specified by p_object_id must be a PROCEDURE, FUNCTION, PACKAGE,
    *   PACKAGE BODY, TRIGGER, TYPE, TYPE BODY, LIBRARY, VIEW, OPERATOR,
    *   SYNONYM, or JAVA CLASS. If the object is not one of these types
    *   and the flag inv_error_on_restrictions is specified in p_option_flags
    *   then the exception inv_restricted_object is raised, else no action
    *   is taken.
    *
    *   If the object specified by p_object_id is the package specification
    *   of STANDARD, DBMS_STANDARD, or specification or body of DBMS_UTILITY
    *   and the flag inv_error_on_restrictions is specified in p_option_flags
    *   then the exception inv_restricted_object is raised, else no action
    *   is taken.
    *
    *   If the object specified by p_object_id is an object type specification
    *   and the type is a system generated type, an earlier type version or
    *   there exist tables which depend on the type and the flag
    *   inv_error_on_restrictions is specified in p_option_flags then the
    *   exception inv_restricted_object is raised, else no action is taken.
    *
    * EXAMPLES:
    *
    *   dbms_utility.invalidate(1232,
    *                           'PLSQL_OPTIMIZE_LEVEL = 2 REUSE SETTINGS');
    *
    *   Assume that the object_id 1232 refers to the procedure remove_emp
    *   in the hr schema. Then the above call will mark the remove_emp
    *   procedure invalid and change it's  PLSQL_OPTIMIZE_LEVEL compiler
    *   setting to 2. The values of other compiler settings will remain
    *   unchanged since REUSE SETTINGS is specified.
    *
    *   Objects that depend on hr.remove_emp will also get marked invalid.
    *   Their compiler parameters will not be changed.
    *
    *   dbms_utility.invalidate(40775,
    *                           'plsql_code_type = native');
    *
    *   Assume that the object_id 40775 refers to the type body
    *   leaf_category_typ in the oe schema. Then the above call will mark
    *   the type body invalid and change it's  PLSQL_CODE_TYPE compiler
    *   setting to NATIVE. The values of other compiler settings will be
    *   picked up from the current session environment since REUSE SETTINGS
    *   has not been specified.
    *
    *   Since no objects can depend on bodies, there are no cascaded
    *   invalidations.
    *
    *   dbms_utility.invalidate(40796);
    *
    *   Assume that the object_id 40796 refers to the view oc_orders in
    *   the oe schema. Then the above call will mark the oc_orders view
    *   invalid.
    *
    *   Objects that depend on oe.oc_orders will also get marked invalid.
    *
    * EXCEPTIONS:
    *   This procedure can raise various exceptions. See detailed description
    *   above.
    */
  procedure invalidate(p_object_id  number,
                       p_plsql_object_settings varchar2 default NULL,
                       p_option_flags pls_integer default 0);

$if utl_ident.is_oracle_server $then
  procedure analyze_schema(schema varchar2, method varchar2,
    estimate_rows number default null,
    estimate_percent number default null, method_opt varchar2 default null);
$else
  /* analyze_schema is not supported */
$end
  --  Analyze all the tables, clusters and indexes in a schema.
  --  Input arguments:
  --    schema
  --      Name of the schema.
  --    method, estimate_rows, estimate_percent, method_opt
  --      See the descriptions above in sql_ddl.analyze.object.
  --  Exceptions:
  --    ORA-20000: Insufficient privileges for some object in this schema.

$if utl_ident.is_oracle_server $then
  procedure analyze_database(method varchar2,
    estimate_rows number default null,
    estimate_percent number default null, method_opt varchar2 default null);
$else
  /* analyze_database is not supported */
$end
  --  Analyze all the tables, clusters and indexes in a database.
  --  Input arguments:
  --    method, estimate_rows, estimate_percent, method_opt
  --      See the descriptions above in sql_ddl.analyze.object.
  --  Exceptions:
  --    ORA-20000: Insufficient privileges for some object in this database.

  function format_error_stack return varchar2;
    pragma interface (C, format_error_stack);               -- 1 (see psdicd.c)
  --  Format the current error stack.  This can be used in exception
  --    handlers to look at the full error stack.
  --  Output arguments:
  --    format_error_stack
  --      Returns the error stack.  May be up to 2000 bytes.
  function format_call_stack return varchar2;
    pragma restrict_references(format_call_stack,WNDS);
    pragma interface (C, format_call_stack);                -- 2 (see psdicd.c)
  --  Format the current call stack.  This can be used an any stored
  --    procedure or trigger to access the call stack.  This can be
  --    useful for debugging.
  --  Output arguments:
  --    format_call_stack
  --      Returns the call stack.  May be up to 2000 bytes.

$if utl_ident.is_oracle_server $then
  function is_cluster_database return boolean;
$else
  /* is_cluster_database is not supported */
$end
  --  Find out if this database is running in cluster database mode.
  --  Output arguments:
  --    is_cluster_database
  --      TRUE if this instance was started in cluster database mode,
  --      FALSE otherwise.

  function get_time return number;
  --  Find out the current elapsed time in 100th's of a second.
  --  Output:
  --      The returned elapsed time is the number of 100th's
  --      of a second from some arbitrary epoch.
  --  Related Function(s): "get_cpu_time" [See below].

$if utl_ident.is_oracle_server $then
  function get_parameter_value(parnam in     varchar2,
                               intval in out binary_integer,
                               strval in out varchar2,
                               listno in     binary_integer default 1)
    return binary_integer;
$else
  /* get_parameter_value is not supported */
$end
  --  Gets value of specified init.ora parameter.
  --  Input arguments:
  --    parnam
  --      Parameter name
  --    listno
  --      List item number. If we are retrieving the parameter values for
  --      a parameter that can be specified multiple times to accumulate
  --      values (Eg rollback_segments) then this can be used to get each
  --      individual parameter. Eg, if we have the following :
  --
  --          rollback_segments = rbs1
  --          rollback_segments = rbs2
  --
  --      then use a value of 1 to get "rbs1" and a value of 2 to get "rbs2".
  --
  --  Output arguments:
  --    intval
  --      Value of an integer parameter or value length of a string parameter
  --    strval
  --      Value of a string parameter
  --  Returns:
  --    partyp
  --      Parameter type
  --        0 if parameter is an integer/boolean parameter
  --        1 if parameter is a  string/file parameter
  --  Notes
  --    1. Certain parameters can store values much larger than can be
  --       returned by this function. When this function is requested to
  --       retrieve the setting for such parameters and "unsupported parameter"
  --       exception will be raised. The "shared_pool_size" parameter is one
  --       such parameter.
  -- Example usage:
  -- DECLARE
  --   parnam VARCHAR2(256);
  --   intval BINARY_INTEGER;
  --   strval VARCHAR2(256);
  --   partyp BINARY_INTEGER;
  -- BEGIN
  --   partyp := dbms_utility.get_parameter_value('max_dump_file_size',
  --                                               intval, strval);
  --   dbms_output.put('parameter value is: ');
  --   IF partyp = 1 THEN
  --     dbms_output.put_line(strval);
  --   ELSE
  --     dbms_output.put_line(intval);
  --   END IF;
  --   IF partyp = 1 THEN
  --     dbms_output.put('parameter value length is: ');
  --     dbms_output.put_line(intval);
  --   END IF;
  --   dbms_output.put('parameter type is: ');
  --   IF partyp = 1 THEN
  --     dbms_output.put_line('string');
  --   ELSE
  --     dbms_output.put_line('integer');
  --   END IF;
  -- END;
  procedure name_resolve(name in varchar2, context in number,
    schema out varchar2, part1 out varchar2, part2 out varchar2,
    dblink out varchar2, part1_type out number, object_number out number);
  --  Resolve the given name.  Do synonym translation if necessary.  Do
  --    authorization checking.
  --  Input arguments:
  --    name
  --      The name of the object.  This can be of the form [[a.]b.]c[@d]
  --      where a,b,c are SQL identifier and d is a dblink.  No syntax
  --      checking is performed on the dblink.  If a dblink is specified,
  --      of the name resolves to something with a dblink, then object
  --      is not resolved, but the schema, part1, part2 and dblink out
  --      arguments are filled in.  a,b and c may be delimted identifiers,
  --      and may contain NLS characters (single and multi-byte).
  --    context
  --      Must be an integer between 0 and 9.
  --      0 -- table
  --      1 -- pl/sql (for 2 part names)
  --      2 -- sequences
  --      3 -- trigger
  --      4 -- Java Source
  --      5 -- Java resource
  --      6 -- Java class
  --      7 -- type
  --      8 -- Java shared data
  --      9 -- index
  --  Output arguments:
  --    schema
  --      The schema of the object.  If no schema is specified in 'name'
  --      then the schema is determined by resolving the name.
  --    part1
  --      The first part of the name.  The type of this name is specified
  --      part1_type (synonym, procedure or package).
  --    part2
  --      If this is non-null, then this is a procedure name within the
  --      package indicated by part1.
  --    dblink
  --      If this is non-null then a database link was either specified
  --      as part of 'name' or 'name' was a synonym which resolved to
  --      something with a database link.  In this later case, part1_type
  --      will indicate a synonym.
  --    part1_type
  --      The type of part1 is
  --        5 - synonym
  --        7 - procedure (top level)
  --        8 - function (top level)
  --        9 - package
  --      If a synonym, it means that 'name' is a synonym that translats
  --      to something with a database link.  In this case, if further
  --      name translation is desired, then you must call the
  --      dbms_utility.name_resolve procedure on this remote node.
  --    object_number
  --      If non-null then 'name' was successfully resolved and this is the
  --      object number which it resolved to.
  --  Exceptions:
  --    All errors are handled by raising exceptions.  A wide variety of
  --    exceptions are possible, based on the various syntax error that
  --    are possible when specifying object names.
  procedure name_tokenize( name    in  varchar2,
                           a       out varchar2,
                           b       out varchar2,
                           c       out varchar2,
                           dblink  out varchar2,
                           nextpos out binary_integer);
  --  Call the parser to parse the given name as "a [. b [. c ]][@ dblink ]".
  --  Strip doublequotes, or convert to uppercase if there are no quotes.
  --    Ignore comments of all sorts.  Do no semantic analysis.  Leave any
  --      missing values as null.
  --  For each of a,b,c,dblink, tell where the following token starts
  --    in anext,bnext,cnext,dnext respectively.

  PROCEDURE comma_to_table( list   IN  VARCHAR2,
                            tablen OUT BINARY_INTEGER,
                            tab    OUT uncl_array);
  --  Convert a comma-separated list of names into a PL/SQL table of names
  --  This uses name_tokenize to figure out what are names and what are commas
  --  See name_tokenize for the expected syntax of the names.

  PROCEDURE comma_to_table( list   IN  VARCHAR2,
                            tablen OUT BINARY_INTEGER,
                            tab    OUT lname_array);
  --  Convert a comma-separated list of names into a PL/SQL table of names
  --  This is an overloaded version for supporting fully-qualified attribute
  --  names of the form "a [. b ]*".

  PROCEDURE table_to_comma( tab    IN  uncl_array,
                            tablen OUT BINARY_INTEGER,
                            list   OUT VARCHAR2);
  --  Convert a PL/SQL table of names into a comma-separated list of names

  PROCEDURE table_to_comma( tab    IN  lname_array,
                            tablen OUT BINARY_INTEGER,
                            list   OUT VARCHAR2);
  --  Convert a PL/SQL table of names into a comma-separated list of names
  --  This is an overloaded version for supporting fully-qualified attribute
  --  names.

$if utl_ident.is_oracle_server $then
  FUNCTION port_string RETURN VARCHAR2;
  PRAGMA RESTRICT_REFERENCES(port_string, WNDS, RNDS, WNPS, RNPS);
$else
  /* port_string is not supported */
$end
  --  Return a string that uniquely identifies the port (operating system)
  --  and the two task protocol version of Oracle.  EG "VAX/VMX-7.1.0.0"
  --  "SVR4-be-8.1.0" (SVR4-b(ig)e(ndian)-8.1.0)
  --  maximum length is port specific.

  PROCEDURE db_version(version       OUT VARCHAR2,
                       compatibility OUT VARCHAR2);
  -- Return version information for the database:
  -- version -> A string which represents the internal software version
  --            of the database (e.g., 7.1.0.0.0). The length of this string
  --            is variable and is determined by the database version.
  -- compatibility -> The compatibility setting of the database determined by
  --                  the "compatible" init.ora parameter. If the parameter
  --                  is not specified in the init.ora file, NULL is returned.

$if utl_ident.is_oracle_server $then
  function make_data_block_address(file number, block number) return number;
  PRAGMA RESTRICT_REFERENCES(make_data_block_address, WNDS, RNDS, WNPS, RNPS);
$else
  /* make_data_block_address is not supported */
$end
  --  Creates a data block address given a file# and a block#.  A data block
  --  address is the internal structure used to identify a block in the
  --  database.  This is function useful when accessing certain fixed tables
  --  that contain data block addresses.
  --  Input arguments:
  --    file  - the file that contains the block
  --    block - the offset of the block within the file in terms of block
  --            increments
  --  Output arguments:
  --    dba   - the data block address

$if utl_ident.is_oracle_server $then
  function data_block_address_file(dba number) return number;
  PRAGMA RESTRICT_REFERENCES(data_block_address_file, WNDS, RNDS, WNPS, RNPS);
$else
  /* data_block_address_file is not supported */
$end
  --  Get the file number part of a data block address
  --  Input arguments:
  --    dba   - a data block address
  --  Output Arguments:
  --    file  - the file that contains the block

$if utl_ident.is_oracle_server $then
  function data_block_address_block(dba number) return number;
  PRAGMA RESTRICT_REFERENCES(data_block_address_block, WNDS, RNDS, WNPS, RNPS);
$else
  /* data_block_address_block is not supported */
$end
  --  Get the block number part of a data block address
  --  Input arguments:
  --    dba   - a data block address
  --  Output Arguments:
  --    block  - the block offset of the block
  function get_hash_value(name varchar2, base number, hash_size number)
    return number;
  PRAGMA RESTRICT_REFERENCES(get_hash_value, WNDS, RNDS, WNPS, RNPS);
  --  Compute a hash value for the given string
  --  Input arguments:
  --    name  - The string to be hashed.
  --    base  - A base value for the returned hash value to start at.
  --    hash_size -  The desired size of the hash table.
  --  Returns:
  --    A hash value based on the input string.
  --    For example, to get a hash value on a string where the hash value
  --    should be between 1000 and 3047, use 1000 as the base value and
  --    2048 as the hash_size value.  Using a power of 2 for the hash_size
  --    parameter works best.
  --  Exceptions:
  --    ORA-29261 will be raised if hash_size is 0 or if hash_size or
  --    base are null

  function get_sql_hash(name IN varchar2, hash OUT raw,
                        pre10ihash OUT number)
    return number;
  PRAGMA RESTRICT_REFERENCES(get_sql_hash, WNDS, RNDS, WNPS, RNPS);
  --  Compute a hash value for the given string using md5 algo
  --  Input arguments:
  --    name  - The string to be hashed.
  --    hash  - An optional field to store all 16 bytes of returned
  --            hash value.
  --    pre10ihash - An optional field to store the pre 10i database
  --                 version hash value.
  --  Returns:
  --    A hash value (last 4 bytes)  based on the input string.
  --    The md5 hash algorithm computes a 16 byte hash value, but
  --    we only return the last 4 bytes so that we can return an
  --    actual number.  One could use an optional RAW parameter to
  --    get all 16 bytes and to store the pre 10i hash value of 4
  --    4 bytes in the pre10ihash optional parameter.

$if utl_ident.is_oracle_server $then
  function sqlid_to_sqlhash(sql_id varchar2)
    return number;
  PRAGMA RESTRICT_REFERENCES(sqlid_to_sqlhash, WNDS, RNDS, WNPS, RNPS);
$else
  /* sqlid_to_sqlhash is not supported */
$end
  --  This routine will convert a sql_id into a hash value
  --  Input arguments:
  --    sql_id - SQL ID of a sql statement.  Must be VARCHAR2(13).
  --  Returns:
  --    A hash value converted from the sql_id.

$if utl_ident.is_oracle_server $then
procedure analyze_part_object
   (schema in varchar2 default null,
    object_name in varchar2 default null,
    object_type in char default 'T',
    command_type in char default 'E',
    command_opt in varchar2 default null,
    sample_clause in varchar2 default 'sample 5 percent');
$else
  /* analyze_part_object is not supported */
$end
  --  Equivalent to SQL "ANALYZE TABLE|INDEX [<schema>.]<object_name>
  --    PARTITION <pname> [<command_type>] [<command_opt>] [<sample_clause>]
  --  for each partition of the object, run in parallel using job queues.
  --  The package will submit a job for each partition
  --  It is the users responsibilty to control the number of concurrent
  --  jobs by setting the INIT parameter JOB_QUEUE_PROCESSES correctly
  --  There is minimal error checking for correct syntax.  Any error will be
  --  reported in SNP trace files.
  --  Input arguments:
  --  schema
  --    schema of the object_name
  --  object_name
  --    name of object to be analyzed, must be partitioned
  --  object_type
  --    type of object, must be T(able) or I(ndex)
  --  command_type
  --    must be one of the following
  --      - C(omput statistics)
  --      - E(stimate statistics)
  --      - D(elete statistics)
  --      - V(alidate structure)
  --  command_opt
  --    Other options for the command type.
  --    For C, E it can be FOR table, FOR all LOCAL indexes, FOR all columns or
  --    combination of some of the 'for' options of analyze statistics (table)
  --    For V, it can be 'CASCADE' when object_type is T
  --  sample_clause
  --    Specifies the sample clause to use when command_type is 'E'

$if utl_ident.is_oracle_server $then
procedure exec_ddl_statement(parse_string in varchar2);
  -- Will execute the DDL statement in parse_string
  --  parse_string
  --    DDL statement to be executed
$else
  /* exec_ddl_statement is not supported */
$end

$if utl_ident.is_oracle_server $then
function current_instance return number;
$else
  /* current_instance is not supported */
$end
  -- Return the current connected instance number
  -- Return NULL when connected instance is down

$if utl_ident.is_oracle_server $then
procedure  active_instances(instance_table   OUT instance_table,
                            instance_count   OUT number);
$else
  /* active_instances is not supported */
$end
  -- instance_table contains a list of the active instance numbers and names
  -- When no instance is up ( or non-OPS setting), the list is empty
  -- instance_count is  the number of active instances, 0 under non-ops setting

procedure get_dependency (type   IN VARCHAR2,
                          schema IN VARCHAR2,
                          name   IN VARCHAR2);
 -- This procedure will show all the dependencies on the object passed in.
 -- The inputs are
 --   type: The type of the object, for example if the object is a table
 --         give the type as 'TABLE'.
 --   schema: The schema name of the object.
 --   name: The name of the object.

$if utl_ident.is_oracle_server $then
procedure create_alter_type_error_table ( schema_name IN VARCHAR2,
                                          table_name  IN VARCHAR2);
$else
  /* create_alter_type_error_table is not supported */
$end
 -- This procedure will create an error table to be used in the EXCEPTION
 -- clause of ALTER TYPE statement. An error will be returned if the table
 -- already exists.
 -- The inputs are:
 --   schema: The schema name
 --   table: The name of the table to be created.

procedure canonicalize(name           IN   VARCHAR2,
                       canon_name     OUT  VARCHAR2,
                       canon_len      IN   BINARY_INTEGER);
  -- canonicalize the given string
  -- if name is NULL, canon_name becomes NULL
  -- if name is not a dotted name,
  --    if name begins and ends with a double quote, remove both
  --    otherwise, convert to upper case with NLS_UPPER
  --    Note that this case does not include a name with special
  --    characters, e.g., space, but is not doubly quoted.
  -- if name is a dotted name, e.g., a."b".c,
  --    for each component in the dotted name,
  --      if the component begins and ends with a double quote,
  --        no transformation will be done in this component
  --      else
  --        this component will be capitalized with NLS_UPPER and
  --        a begin and end double quotes will be applied to the
  --        capitalized form of this component.
  --    each canonicalized component will be concatenated together in
  --    the input position, separated by ".".
  -- return the first canon_len bytes in canon_name
  --
  -- Any other character after a[.b]* will be ignored.
  --
  -- NOTES:
  --   1. It does not handle cases like 'A B';
  --   2. It handles a single reserved/key word, e.g., 'table';
  --   3. It strips off white spaces for a single identifier, e.g., ' table '
  --      becomes TABLE.
  --
  -- Examples:
  --   a becomes A
  --   "a" becomes a
  --   "a".b becomes "a"."B"
  --   "a".b,c.f becomes "a"."B", ",c.f" is ignored.
  --

FUNCTION is_bit_set(r                 IN  RAW,
                 n                 IN  NUMBER)
  RETURN NUMBER;
  PRAGMA RESTRICT_REFERENCES(is_bit_set, RNPS, WNPS, RNDS, WNDS);
  --  Return 1 if bit n in raw r is set.  Bits are numbered high to low
  --  with the lowest bit being bit number 1.  This is a utility to assist
  --  the view DBA_PENDING_TRANSACTION.

$if utl_ident.is_oracle_server $then
procedure get_tz_transitions(regionid number,transitions OUT maxraw);
$else
  /* get_tz_transitions is not supported */
$end


  --  Get timezeone transitions from the timezone.dat file
  --  Input arguments:
  --    regionid
  --      Number corresponding to the region
  --  Output arguments:
  --    transitions
  --      The raw bytes from the timezone.dat file

  --
  -- The following two functions provide the pre-825066-fix behavior of
  -- SYS_CONTEXT('USERENV', 'CURRENT_SCHEMA') and
  -- SYS_CONTEXT('USERENV', 'CURRENT_USER')
  --                  These functions are temporarily provided for
  -- *** Warning ***: backward compatibility and will be removed in
  --                  near future.
  --
$if utl_ident.is_oracle_server $then
  function old_current_schema return varchar2;
$else
  /* old_current_schema is not supported */
$end

$if utl_ident.is_oracle_server $then
  function old_current_user   return varchar2;
$else
  /* old_current_user is not supported */
$end

  function get_endianness return number;
    pragma interface (C, get_endianness);                   -- 3 (see psdicd.c)

  function format_error_backtrace return varchar2;
    pragma interface (C, format_error_backtrace);           -- 4 (see psdicd.c)
  --  Format the backtrace from the point of the current error
  --  to the exception handler where the error has been caught.
  --  NULL string is returned if no error is currently being
  --  handled.

 function get_cpu_time return number;
  --  Find out the current CPU time in 100th's of a second.
  --
  --  Output:
  --    The returned CPU time is the number of 100th's
  --    of a second from some arbitrary epoch.
  --
  --  Related Function(s):
  --    "get_time" [See above].
  --
  --  Usage Example:
  --     ..
  --     start_cpu_time NUMBER;
  --     end_cpu_time   NUMBER;
  --     ..
  --   BEGIN
  --
  --     start_cpu_time := dbms_utility.GET_CPU_TIME;
  --
  --     ... -- some work that needs to be timed
  --
  --     end_cpu_time := dbms_utility.GET_CPU_TIME;
  --
  --     dbms_output.put_line('CPU Time (in seconds)= '
  --                          || ((end_cpu_time - start_cpu_time)/100));
  --

end;