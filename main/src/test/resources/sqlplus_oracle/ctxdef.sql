Rem
Rem $Header: ctxdef.sql 04-aug-2004.22:17:56 mfaisal Exp $
Rem
Rem ctxdef.sql
Rem
Rem Copyright (c) 2002, 2004, Oracle. All rights reserved.  
Rem
Rem    NAME
Rem      ctxdef.sql - CTX DEFault objects
Rem
Rem    DESCRIPTION
Rem      <short description of component this file declares/defines>
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    mfaisal     08/04/04 - keyview html export release 8.0.0 
Rem    surman      09/24/03 - 3152625: Up MAX_INDEX_MEMORY to 1G 
Rem    daliao      01/13/03 - set KMEAN as default clustering
Rem    daliao      10/25/02 - add DEFAULT_CLUSTERING
Rem    gshank      09/30/02 - Remove ext theme api
Rem    ehuang      09/27/02 - remove set statements
Rem    gshank      08/31/02 - Theme lexing
Rem    gshank      08/20/02 - Theme lexing
Rem    ehuang      07/11/02 - ehuang_component_upgrade_020626
Rem    ehuang      07/02/02 - Created - component upgrade
Rem     ehuang     05/21/02 - add mail_filter_config_file parameter.
Rem     gkaminag   06/06/02 - keycomp_ok has moved.
Rem     ehuang     04/20/02 - add mail filter.
Rem     daliao     01/29/02 - add DEFAULT_CLASSIFIER
Rem     gkaminag   02/06/02 - handle key compression not available.
Rem     wclin      10/11/01 -
Rem     wclin      09/26/01 - new objects for xpath index
Rem     ehuang     09/29/01 - uritype indexing.
Rem     gkaminag   09/20/00 - use path section group by default for xmltyp
Rem     gkaminag   07/24/00 - auto_xml_section_group->path_section_group
Rem     salpha     06/26/00 - ctxrule implementation
Rem     ehuang     06/16/00 - add xmltype_datatype
Rem     gkaminag   06/12/00 - add auto_xml_section preference
Rem     gkaminag   04/20/00 - DIRECT_DATASTORE preference
Rem     ehuang     04/11/00 - change in datastore roles
Rem     gkaminag   03/29/00 - better storage defaults
Rem     ehuang     03/08/00 - add new system parameters
Rem     gkaminag   02/22/00 - ctxcat implementation
Rem     gkaminag   10/13/99 - bug 1032894
Rem     gkaminag   04/22/99 - add default auto section group
Rem     ehuang     04/15/99 - add system parameter CTX_DOC_KEY_TYPE
Rem     ehuang     10/21/98 - add function datastore preference
Rem     gkaminag   09/24/98 - add basic lexer preference
Rem     ehuang     09/16/98 - bug 726897
Rem     ehuang     09/14/98 - bug 726898
Rem     ehuang     09/02/98 - default pref change
Rem     jachen     09/04/98 - command out Chinese lexer
Rem     gkaminag   08/11/98 - add system parameter LOG_DIRECTORY
Rem     jachen     07/23/98 - add new Chinese lexer
Rem     ymatsuda   06/19/98 - remove HANZI_INDEXING attribute
Rem     ymatsuda   06/04/98 - remove kanji index mode
Rem     ehuang     06/03/98 - change DEFAULT_EIGINE to DEFAULT_STORAGE
Rem     gkaminag   06/03/98 - default for inso filter
Rem     gkaminag   05/19/98 - system parameters
Rem     gkaminag   05/15/98 - rename default html section group
Rem     gkaminag   05/11/98 - add default html section group
Rem     dyu        04/15/98 - Remove duplicate section group creation
Rem     jliu       04/09/98 - add async_datax
Rem     gkaminag   04/03/98 - no more html filter
Rem     gkaminag   04/02/98 - add default section group
Rem     gkaminag   04/03/98 - no more html filter
Rem     gkaminag   04/02/98 - add default section group
Rem     gkaminag   03/24/98 - soundex
Rem     gkaminag   03/23/98 - preference renaming
Rem     gkaminag   03/18/98 - null values not allowed
Rem     dyu        03/18/98 - Fix merge error
Rem     gkaminag   03/12/98 - object name change
Rem     ehuang     03/10/98 - new pref system change
Rem     ehuang     02/25/98 - stoplist pref change
Rem     jlee       03/08/98 - attributes for korean lexer
Rem     ehuang     02/25/98 - stpolist pref change
Rem     ehuang     01/16/98 - rename datastore name for 8.1
Rem     dyu        01/15/98 - Fix Template name
Rem     gkaminag   12/15/97 - add new data types
Rem     gkaminag   12/08/97 - datastore conversion
Rem     dyu        12/08/97 - remove thai lexer
Rem     cbhavsar   11/19/97 - Adding Portuguese stemmer
Rem     dyu        09/26/97 - Update spanish stopword
Rem     gshank     09/24/97 - Change mised case attribute name
Rem     gshank     09/12/97 - Case sensitivity
Rem     jliu       07/29/97 -  url_ftp merge
Rem     cbhavsar   07/23/97 -  Support for German composites
Rem     gkaminag   07/31/97 -  remove thai lexer
Rem     ehuang     07/24/97 -  Bug 517274
Rem     ikourtid   06/26/97 -  make stat. scoring alg. default
Rem     gshank     07/15/97 -  Fuzzy languages
Rem     gkaminag   07/10/97 -  startjoin should be </
Rem     jliu       07/10/97 -  Remove merge comments
Rem     gkaminag   07/09/97 -  fix diff
Rem     jliu       07/09/97 -  Add FTP_PROXY Preference Attribute
Rem     ehuang     06/26/97 -  add new stop lists preferences
Rem     gkaminag   06/24/97 -  add HTML defaults
Rem     cbhavsar   06/15/97 -  Statistical scoring
Rem     gkaminag   06/11/97 -  add keep_tag attribute to HTML filter
Rem     gkaminag   06/06/97 -  add new master-detail object
Rem     syang      06/06/97 -  startjoin/endjoin support
Rem     kkasemsa   05/15/97 -  Add Thai lexer
Rem     gkaminag   04/17/97 -  add SECTION_GROUP attribute
Rem     gkaminag   04/08/97 -  Forgot engine_nop
Rem     gkaminag   04/04/97 -  use new functions
Rem     ehuang     04/02/97 -  fix typo
Rem     jachen     01/15/97 -  put Chinese lexer behind Theme lexer
Rem     jachen     01/04/97 -  add Chinese lexer
Rem     mfaisal    12/19/96 -  Adding base letter support
Rem     atisdale   12/19/96 -  add defaults to loader objects
Rem     mfaisal    11/11/96 -  Multiple UDF with Autorec
Rem     dyu        11/07/96 -  fix drdfwda for reset_db error
Rem     cbhavsar   10/16/96 -  Theme Lexer
Rem     atisdale   10/02/96 -  typo
Rem     gkaminag   10/01/96 -  add loader objects
Rem     droberts   09/26/96 -  Adding proxy attributes for URL data store
Rem     ehuang     09/17/96 -  add class, object and pref to support CTXLSRV
Rem     bkang      08/08/96 -  add Korean lexer
Rem     droberts   08/15/96 -  Adding URL attributes
Rem     ymatsuda   08/07/96 -  vgram mode
Rem     droberts   08/06/96 -  deleting SERVER, URLPATH attributes
Rem     gkaminag   08/01/96 -  add user-defined filters
Rem     sbedarka   08/01/96 -  fix bug 383489
Rem     ymatsuda   08/01/96 -  big stoplist
Rem     ymatsuda   07/17/96 -  add SQE attributes
Rem     droberts   07/17/96 -  Changing name of url validate procedure
Rem     droberts   07/08/96 -  Added DEFAULT_URL preference, URL tile attribute
Rem     wkeese     04/29/96 -  better stoplist 
Rem     rnori      04/12/96 -  Remove ON_SWITCH attribute from STOPLIST object 
Rem     mbhavsar   03/29/96 -  add validation procedure for ENGINE NOP object 
Rem     ymatsuda   03/27/96 -  HTML code conversion 
Rem     rnori      03/20/96 -  Add Validation Procedures to objects 
Rem     ymatsuda   03/18/96 -  v-gram fuzzy match 
Rem     wkeese     03/14/96 -  add pdf and xvf 
Rem     gshank     02/26/96 -  Change BINARY attribute of datastore preference 
Rem     wkeese     02/21/96 -  fix filter preferences 
Rem     wkeese     02/14/96 -  list of values for attributes 
Rem     wkeese     02/05/96 -  change default OSFILE preference 
Rem     gshank     01/30/96 -  Add stemmer preference 
Rem     wkeese     01/23/96 -  add HTML 
Rem     wkeese     01/11/96 -  lower index_memory until 64K bug fix 
Rem     wkeese     01/11/96 -  put _ in no_soundex 
Rem     wkeese     01/10/96 -  default policy 
Rem     wkeese     01/06/96 -  DR->CTX 
Rem     wkeese     12/09/95 -  stoplist stuff 
Rem     wkeese     12/06/95 -  remove unneeded wordlist parameters 
Rem     ymatsuda   12/05/95 -  add Japanese lexer 
Rem     wkeese     11/30/95 -  remove unneeded preferences 
Rem     wkeese     11/28/95 -  default preferences 
Rem     jxwang     11/02/95 -  keep only one stop list object 
Rem     jxwang     10/31/95 -  Add sequence number to stopwords 
Rem     jxwang     10/18/95 -  Adding stop word preference
Rem     jxwang     10/05/95 -  Adding user defined stop list 
Rem     wkeese     08/04/95 -  add soundex preference 
Rem     wkeese     05/09/95 -  transfer from old environment 
Rem     wkeese     05/07/95 -  pretty up error messages 
Rem     jhyde      05/02/95 -  Add Engine preferences.
Rem     qtran      04/29/95 -  change polname to policy_name
Rem     qtran      04/27/95 -  add a template policies and stoplist settings
Rem     wkeese     04/24/95 -  fix stoplist preferences
Rem     wkeese     04/23/95 -  create/drop index/policy API change
Rem     wkeese     04/22/95 -  create_policy api change
Rem     wkeese     04/22/95 -  comments
Rem     qtran      04/20/95 -  add prefrence attribute for the engine, stopword
Rem     jhyde      04/20/95 -  Add MasterSoft blaster filter.
Rem     wkeese     04/14/95 -  fix osfile attribute
Rem     wkeese     04/11/95 -  datastore
Rem     qtran      03/14/95 -  add object attribute definitions
Rem     qtran      01/04/95 -  merge dr_dict into CTX_DDL
Rem     qtran      12/30/94 -  explicit reserved name for template policy
Rem     qtran      11/18/94 -  No reason specified
Rem     mkremer    11/11/94 -  shorten the preference name
Rem     qtran      11/10/94 -  to add a number of template policies
Rem     qtran      10/14/94 -  Creation

rem cleanout tables
delete from dr$preference_value;
delete from dr$preference;
delete from dr$stoplist;
delete from dr$stopword;
delete from dr$section;
delete from dr$section_group;
delete from dr$parameter;
delete from dr$index_set_index;
delete from dr$index_set;
commit;

REM =========================================================================
PROMPT Create default preferences
REM =========================================================================
begin

  -------------------------------------------------------------------------
  -- DATASTORE PREFS
  -------------------------------------------------------------------------
  ctx_ddl.create_preference('CTXSYS.DEFAULT_DATASTORE','DIRECT_DATASTORE');
  ctx_ddl.create_preference('CTXSYS.DIRECT_DATASTORE','DIRECT_DATASTORE');

  /* no more Master-Detail default preferences in 8.1 */
  CTX_DDL.create_preference('CTXSYS.FILE_DATASTORE','FILE_DATASTORE');

  CTX_DDL.create_preference('CTXSYS.URL_DATASTORE', 'URL_DATASTORE');
  CTX_DDL.set_attribute('CTXSYS.URL_DATASTORE', 'TIMEOUT',         '30');
  CTX_DDL.set_attribute('CTXSYS.URL_DATASTORE', 'MAXTHREADS',       '8');
  CTX_DDL.set_attribute('CTXSYS.URL_DATASTORE', 'URLSIZE',        '256');
  CTX_DDL.set_attribute('CTXSYS.URL_DATASTORE', 'MAXURLS',        '256');
  CTX_DDL.set_attribute('CTXSYS.URL_DATASTORE', 'MAXDOCSIZE', '2097152');

  -------------------------------------------------------------------------
  -- MISC DATASTORE PREFS
  -------------------------------------------------------------------------
  ctx_ddl.create_preference('CTXSYS.ASYNCH_DATAX','ASYNCH_DATAX');
  ctx_ddl.create_preference('CTXSYS.SYNCH_DATAX','SYNCH_DATAX');
  ctx_ddl.create_preference('CTXSYS.CHAR_DATATYPE','CHAR_DATATYPE');
  ctx_ddl.create_preference('CTXSYS.RAW_DATATYPE','CHAR_DATATYPE');
  ctx_ddl.create_preference('CTXSYS.LONG_DATATYPE','LONG_DATATYPE');
  ctx_ddl.create_preference('CTXSYS.LOB_DATATYPE','LOB_DATATYPE');
  ctx_ddl.create_preference('CTXSYS.NONE_DATATYPE','NONE_DATATYPE');
  ctx_ddl.create_preference('CTXSYS.XMLTYPE_DATATYPE', 'XMLTYPE_DATATYPE');
  ctx_ddl.create_preference('CTXSYS.URITYPE_DATATYPE', 'URITYPE_DATATYPE');

  -------------------------------------------------------------------------
  -- FILTER PREFS
  -------------------------------------------------------------------------

  CTX_DDL.create_preference('CTXSYS.NULL_FILTER','NULL_FILTER');
  CTX_DDL.create_preference('CTXSYS.INSO_FILTER','INSO_FILTER');
  CTX_DDL.create_preference('CTXSYS.MAIL_FILTER','MAIL_FILTER');
  CTX_DDL.create_preference('CTXSYS.AUTO_FILTER','AUTO_FILTER');

  -------------------------------------------------------------------------
  -- ENGINE PREFS
  -------------------------------------------------------------------------

  CTX_DDL.create_preference('CTXSYS.DEFAULT_STORAGE','BASIC_STORAGE');
  ctx_ddl.set_attribute('CTXSYS.default_storage', 'r_table_clause',
                        'lob (data) store as (cache)');

  if (driutl.keycomp_ok) then
    ctx_ddl.set_attribute('CTXSYS.default_storage', 'i_index_clause', 
                        'compress 2');
  end if;

  -------------------------------------------------------------------------
  -- LEXER PREFS (most lexers are language-specific)
  -------------------------------------------------------------------------

  CTX_DDL.create_preference('CTXSYS.BASIC_LEXER', 'BASIC_LEXER');
  CTX_DDL.create_preference('CTXSYS.NULL_LEXER', 'NULL_LEXER');

  -------------------------------------------------------------------------
  -- WORDLIST PREFS
  -------------------------------------------------------------------------

  CTX_DDL.create_preference('CTXSYS.BASIC_WORDLIST', 'BASIC_WORDLIST');

  -------------------------------------------------------------------------
  -- Default classifier
  -------------------------------------------------------------------------
  CTX_DDL.create_preference('CTXSYS.DEFAULT_CLASSIFIER', 'RULE_CLASSIFIER');

  -------------------------------------------------------------------------
  -- Default clustering
  -------------------------------------------------------------------------
  CTX_DDL.create_preference('CTXSYS.DEFAULT_CLUSTERING', 'KMEAN_CLUSTERING');
  
end;
/

          
begin
  ctx_ddl.create_section_group('CTXSYS.null_section_group', 
                               'null_section_group');
  ctx_ddl.create_section_group('CTXSYS.html_section_group', 
                               'html_section_group');
  ctx_ddl.create_section_group('CTXSYS.auto_section_group', 
                               'auto_section_group');
  ctx_ddl.create_section_group('CTXSYS.path_section_group', 
                               'path_section_group');
  ctx_ddl.create_section_group('CTXSYS.ctxxpath_section_group', 
                               'ctxxpath_section_group');
end;
/

begin
    CTX_DDL.create_stoplist('CTXSYS.EMPTY_STOPLIST');
end;
/

begin
    CTX_DDL.create_index_set('CTXSYS.EMPTY_INDEX_SET');
end;
/

REM =========================================================================
PROMPT System Parameters
REM =========================================================================

insert into dr$parameter (par_name, par_value)
values ('MAX_INDEX_MEMORY',     '1073741824');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_INDEX_MEMORY', '12582912');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_DATASTORE',    'CTXSYS.DEFAULT_DATASTORE');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_FILTER_TEXT',       'CTXSYS.NULL_FILTER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_FILTER_BINARY',       'CTXSYS.AUTO_FILTER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_FILTER_FILE',       'CTXSYS.AUTO_FILTER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_SECTION_HTML', 'CTXSYS.HTML_SECTION_GROUP');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_SECTION_TEXT', 'CTXSYS.NULL_SECTION_GROUP');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_LEXER',        'CTXSYS.DEFAULT_LEXER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_STOPLIST',     'CTXSYS.DEFAULT_STOPLIST');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_WORDLIST',     'CTXSYS.DEFAULT_WORDLIST');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_STORAGE',      'CTXSYS.DEFAULT_STORAGE');

insert into dr$parameter (par_name, par_value)
values ('LOG_DIRECTORY',        null);

insert into dr$parameter (par_name, par_value)
values ('CTX_DOC_KEY_TYPE',        'PRIMARY_KEY');

insert into dr$parameter(par_name, par_value)
values('FILE_ACCESS_ROLE',   NULL);

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXCAT_LEXER',        'CTXSYS.DEFAULT_LEXER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXCAT_STOPLIST',     'CTXSYS.DEFAULT_STOPLIST');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXCAT_WORDLIST',     'CTXSYS.DEFAULT_WORDLIST');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXCAT_STORAGE',      'CTXSYS.DEFAULT_STORAGE');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXCAT_INDEX_SET',     'CTXSYS.EMPTY_INDEX_SET');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXRULE_LEXER',        'CTXSYS.DEFAULT_LEXER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXRULE_STOPLIST',     'CTXSYS.DEFAULT_STOPLIST');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXRULE_WORDLIST',     'CTXSYS.DEFAULT_WORDLIST');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXRULE_STORAGE',      'CTXSYS.DEFAULT_STORAGE');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_SECTION_XML',          'CTXSYS.PATH_SECTION_GROUP');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CTXXPATH_STORAGE',     'CTXSYS.DEFAULT_STORAGE');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CLASSIFIER',     'CTXSYS.DEFAULT_CLASSIFIER');

insert into dr$parameter (par_name, par_value)
values ('DEFAULT_CLUSTERING',     'CTXSYS.DEFAULT_CLUSTERING');

insert into dr$parameter (par_name, par_value)
values ('MAIL_FILTER_CONFIG_FILE','drmailfl.txt');

commit;

