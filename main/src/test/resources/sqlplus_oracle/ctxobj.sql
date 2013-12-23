PROMPT Removing old object definitions...
delete from dr$class;
delete from dr$object;
delete from dr$object_attribute;
delete from dr$object_attribute_lov;

PROMPT Creating new object definitions...

insert into dr$class values
  (1, 'DATASTORE', 'Data store Class', 'N');

insert into dr$object values
  (1, 1, 'DIRECT_DATASTORE', 'Documents are stored in the column', 'N');

insert into dr$object values
  (1, 2, 'DETAIL_DATASTORE', 'Documents are split into multiple lines', 'N');

insert into dr$object_attribute values
  (10201, 1, 2, 1, 
   'BINARY', 'Newline convention in detail data',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (10202, 1, 2, 2, 
   'DETAIL_OWNER', '',
   'N', 'Y', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10203, 1, 2, 3, 
   'DETAIL_TABLE', 'Name of the detail table',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, null, 'N');

insert into dr$object_attribute values
  (10204, 1, 2, 4, 
   'DETAIL_KEY', 'Name of the detail foreign key column(s)',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, null, 'N');

insert into dr$object_attribute values
  (10205, 1, 2, 5, 
   'DETAIL_LINENO', 'Name of the detail line number column',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, null, 'N');

insert into dr$object_attribute values
  (10206, 1, 2, 6, 
   'DETAIL_TEXT', 'Name of the detail text column',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, null, 'N');

insert into dr$object_attribute values
  (10207, 1, 2, 7, 
   'DETAIL_TEXT_SIZE', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10208, 1, 2, 8, 
   'DETAIL_TEXT_TYPE', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10209, 1, 2, 9, 
   'DETAIL_TEXT_OBJ', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object values
  (1, 3, 'FILE_DATASTORE', 'Documents are stored in OS files, column is file name', 'N');

insert into dr$object_attribute values
  (10301, 1, 3, 1, 
   'PATH', 'Search path to find files in operating system',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (1, 4, 'URL_DATASTORE', 'Documents are web pages, column is URL', 'N');

insert into dr$object_attribute values
  (10404, 1, 4, 4, 
   'TIMEOUT', 'Timeout in seconds',
   'N', 'N', 'Y', 'I', 
   '30', 1, 3600, 'N');

insert into dr$object_attribute values
  (10405, 1, 4, 5, 
   'MAXTHREADS', 'Maximum number of threads',
   'N', 'N', 'Y', 'I', 
   '8', 1, 1024, 'N');

insert into dr$object_attribute values
  (10406, 1, 4, 6, 
   'URLSIZE', 'Maximum size of URL buffer',
   'N', 'N', 'Y', 'I', 
   '256', 32, 65535, 'N');

insert into dr$object_attribute values
  (10407, 1, 4, 7, 
   'MAXURLS', 'Maximum size of URL buffer',
   'N', 'N', 'Y', 'I', 
   '256', 32, 65535, 'N');

insert into dr$object_attribute values
  (10408, 1, 4, 8, 
   'MAXDOCSIZE', 'Maximum amount of document to get',
   'N', 'N', 'Y', 'I', 
   '2097152', 256, 2147483647, 'N');

insert into dr$object_attribute values
  (10409, 1, 4, 9, 
   'HTTP_PROXY', 'HTTP proxy server to use',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10410, 1, 4, 10, 
   'FTP_PROXY', 'FTP proxy server to use',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10411, 1, 4, 11, 
   'NO_PROXY', 'Do not use proxy for this domain',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (1, 5, 'USER_DATASTORE', 'Documents are stored in the column', 'N');

insert into dr$object_attribute values
  (10501, 1, 5, 1, 
   'PROCEDURE', 'PL/SQL proc name in the form FN(in rowid, in out clob)',
   'Y', 'N', 'Y', 'P', 
   'REQUIRED', null, 65, 'N');

insert into dr$object_attribute values
  (10502, 1, 5, 2, 
   'OUTPUT_TYPE', 'datatype of output',
   'N', 'N', 'Y', 'I', 
   'CLOB', null, null, 'Y');

insert into dr$object_attribute_lov values
  (10502, 'CLOB', 1, 'CLOB');

insert into dr$object_attribute_lov values
  (10502, 'BLOB', 2, 'BLOB');

insert into dr$object_attribute_lov values
  (10502, 'VARCHAR2', 3, 'VARCHAR2');

insert into dr$object_attribute_lov values
  (10502, 'CLOB_LOC', 4, 'permanent clob locator');

insert into dr$object_attribute_lov values
  (10502, 'BLOB_LOC', 5, 'permanent blob locator');

insert into dr$object values
  (1, 6, 'NESTED_DATASTORE', 'Documents are stored in a column in the nested table', 'N');

insert into dr$object_attribute values
  (10601, 1, 6, 1, 
   'NESTED_COLUMN', 'name of the nested table column',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, 65, 'N');

insert into dr$object_attribute values
  (10602, 1, 6, 2, 
   'NESTED_TYPE', 'name of the type of the nested table',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, 65, 'N');

insert into dr$object_attribute values
  (10603, 1, 6, 3, 
   'NESTED_LINENO', 'name of the column which orders the lines',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, 65, 'N');

insert into dr$object_attribute values
  (10604, 1, 6, 4, 
   'NESTED_TEXT', 'name of the column which contains the text of line',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, 65, 'N');

insert into dr$object_attribute values
  (10605, 1, 6, 5, 
   'NESTED_TEXT_TYPE', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10606, 1, 6, 6, 
   'NESTED_TEXT_SIZE', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10607, 1, 6, 7, 
   'NESTED_TEXT_OBJ', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10608, 1, 6, 8, 
   'BINARY', 'controls automatic newline behaviour',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object values
  (1, 7, 'MULTI_COLUMN_DATASTORE', 'Documents are stored in multiple columns', 'N');

insert into dr$object_attribute values
  (10701, 1, 7, 1, 
   'COLUMNS', 'list of column names, as in a select list',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, 500, 'N');

insert into dr$object_attribute values
  (10702, 1, 7, 2, 
   'FILTER', 'comma separated list of formats corresponding to the columns',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (10703, 1, 7, 3, 
   'DELIMITER', 'controls in-between column values tagging behaviour',
   'N', 'N', 'Y', 'I', 
   'COLUMN_NAME_TAG', null, null, 'Y');

insert into dr$object_attribute_lov values
  (10703, 'COLUMN_NAME_TAG', 1, 'COLUMN_NAME_TAG');

insert into dr$object_attribute_lov values
  (10703, 'NEWLINE', 2, 'NEWLINE');

insert into dr$class values
  (2, 'DATATYPE', '', 'Y');

insert into dr$object values
  (2, 1, 'LONG_DATATYPE', '', 'Y');

insert into dr$object values
  (2, 2, 'CHAR_DATATYPE', '', 'Y');

insert into dr$object values
  (2, 3, 'LOB_DATATYPE', '', 'Y');

insert into dr$object values
  (2, 4, 'NONE_DATATYPE', '', 'Y');

insert into dr$object values
  (2, 5, 'XMLTYPE_DATATYPE', '', 'Y');

insert into dr$object values
  (2, 6, 'URITYPE_DATATYPE', '', 'Y');

insert into dr$class values
  (3, 'DATAX', '', 'Y');

insert into dr$object values
  (3, 1, 'SYNCH_DATAX', '', 'Y');

insert into dr$object values
  (3, 2, 'ASYNCH_DATAX', '', 'Y');

insert into dr$class values
  (4, 'FILTER', 'Filter Class', 'N');

insert into dr$object values
  (4, 1, 'NULL_FILTER', 'Null filter', 'N');

insert into dr$object values
  (4, 2, 'USER_FILTER', 'User-defined filter', 'N');

insert into dr$object_attribute values
  (40201, 4, 2, 1, 
   'COMMAND', 'Command line to execute filter',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, null, 'N');

insert into dr$object values
  (4, 4, 'CHARSET_FILTER', 'character set converting filter', 'N');

insert into dr$object_attribute values
  (40401, 4, 4, 1, 
   'CHARSET', 'source character set',
   'Y', 'N', 'Y', 'S', 
   'REQUIRED', null, null, 'N');

insert into dr$object values
  (4, 5, 'INSO_FILTER', 'filter for binary document formats', 'N');

insert into dr$object_attribute values
  (40502, 4, 5, 2, 
   'TIMEOUT', 'Polling interval in seconds to terminate by force',
   'N', 'N', 'Y', 'I', 
   '120', 0, 42949672, 'N');

insert into dr$object_attribute values
  (40503, 4, 5, 3, 
   'TIMEOUT_TYPE', 'Time-out type',
   'N', 'N', 'Y', 'I', 
   'HEURISTIC', null, null, 'Y');

insert into dr$object_attribute_lov values
  (40503, 'HEURISTIC', 1, 'Heuristic');

insert into dr$object_attribute_lov values
  (40503, 'FIXED', 2, 'Fixed');

insert into dr$object_attribute values
  (40504, 4, 5, 4, 
   'OUTPUT_FORMATTING', 'formatted output',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object values
  (4, 6, 'PROCEDURE_FILTER', 'Procedure filter', 'N');

insert into dr$object_attribute values
  (40601, 4, 6, 1, 
   'PROCEDURE', 'name of the stored procedure',
   'Y', 'N', 'Y', 'P', 
   'REQUIRED', null, null, 'N');

insert into dr$object_attribute values
  (40602, 4, 6, 2, 
   'INPUT_TYPE', 'type of the input argument of filter stored procedure',
   'N', 'N', 'Y', 'I', 
   'BLOB', null, null, 'Y');

insert into dr$object_attribute_lov values
  (40602, 'BLOB', 1, 'BLOB');

insert into dr$object_attribute_lov values
  (40602, 'CLOB', 2, 'CLOB');

insert into dr$object_attribute_lov values
  (40602, 'VARCHAR2', 3, 'VARCHAR2');

insert into dr$object_attribute_lov values
  (40602, 'FILE', 4, 'FILE');

insert into dr$object_attribute values
  (40603, 4, 6, 3, 
   'OUTPUT_TYPE', 'type of output argument of filter stored procedure',
   'N', 'N', 'Y', 'I', 
   'CLOB', null, null, 'Y');

insert into dr$object_attribute_lov values
  (40603, 'CLOB', 1, 'CLOB');

insert into dr$object_attribute_lov values
  (40603, 'VARCHAR2', 2, 'VARCHAR2');

insert into dr$object_attribute_lov values
  (40603, 'FILE', 3, 'FILE');

insert into dr$object_attribute values
  (40604, 4, 6, 4, 
   'ROWID_PARAMETER', 'include rowid in procedure parameter list',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (40605, 4, 6, 5, 
   'FORMAT_PARAMETER', 'include format in procedure parameter list',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (40606, 4, 6, 6, 
   'CHARSET_PARAMETER', 'include charset in procedure parameter list',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object values
  (4, 7, 'MAIL_FILTER', 'filter for RFC-822/RFC-2045 mail messages', 'N');

insert into dr$object_attribute values
  (40701, 4, 7, 1, 
   'INDEX_FIELDS', 'colon-separated list of fields to preserve',
   'N', 'N', 'Y', 'S', 
   'SUBJECT:CONTENT-DESCRIPTION', null, null, 'N');

insert into dr$object_attribute values
  (40702, 4, 7, 2, 
   'INSO_TIMEOUT', 'Polling interval in seconds to terminate by force',
   'N', 'N', 'Y', 'I', 
   '60', 0, 42949672, 'N');

insert into dr$object_attribute values
  (40703, 4, 7, 3, 
   'INSO_OUTPUT_FORMATTING', 'formatted output',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (40704, 4, 7, 4, 
   'PART_FIELD_STYLE', 'output of index fields of parts of multipart mails',
   'N', 'N', 'Y', 'I', 
   'IGNORE', null, null, 'Y');

insert into dr$object_attribute_lov values
  (40704, 'IGNORE', 0, 'Eliminate');

insert into dr$object_attribute_lov values
  (40704, 'TAG', 1, 'Transform to <field>content</field>');

insert into dr$object_attribute_lov values
  (40704, 'FIELD', 2, 'Transform to field: content');

insert into dr$object_attribute_lov values
  (40704, 'TEXT', 3, 'Transform to content');

insert into dr$object_attribute values
  (40705, 4, 7, 5, 
   'AUTO_FILTER_TIMEOUT', 'Polling interval in seconds to terminate by force',
   'N', 'N', 'Y', 'I', 
   '60', 0, 42949672, 'N');

insert into dr$object_attribute values
  (40706, 4, 7, 6, 
   'AUTO_FILTER_OUTPUT_FORMATTING', 'formatted output',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object values
  (4, 8, 'AUTO_FILTER', 'filter for binary document formats', 'N');

insert into dr$object_attribute values
  (40802, 4, 8, 2, 
   'TIMEOUT', 'Polling interval in seconds to terminate by force',
   'N', 'N', 'Y', 'I', 
   '120', 0, 42949672, 'N');

insert into dr$object_attribute values
  (40803, 4, 8, 3, 
   'TIMEOUT_TYPE', 'Time-out type',
   'N', 'N', 'Y', 'I', 
   'HEURISTIC', null, null, 'Y');

insert into dr$object_attribute_lov values
  (40803, 'HEURISTIC', 1, 'Heuristic');

insert into dr$object_attribute_lov values
  (40803, 'FIXED', 2, 'Fixed');

insert into dr$object_attribute values
  (40804, 4, 8, 4, 
   'OUTPUT_FORMATTING', 'formatted output',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$class values
  (5, 'SECTION_GROUP', 'Section Group', 'N');

insert into dr$object values
  (5, 1, 'NULL_SECTION_GROUP', 'null section group', 'N');

insert into dr$object_attribute values
  (50103, 5, 1, 3, 
   'SPECIAL', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (5, 2, 'BASIC_SECTION_GROUP', 'basic section group', 'N');

insert into dr$object_attribute values
  (50201, 5, 2, 1, 
   'ZONE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50202, 5, 2, 2, 
   'FIELD', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50203, 5, 2, 3, 
   'SPECIAL', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50207, 5, 2, 7, 
   'MDATA', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (5, 3, 'HTML_SECTION_GROUP', 'html section group', 'N');

insert into dr$object_attribute values
  (50301, 5, 3, 1, 
   'ZONE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50302, 5, 3, 2, 
   'FIELD', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50303, 5, 3, 3, 
   'SPECIAL', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50307, 5, 3, 7, 
   'MDATA', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (5, 5, 'XML_SECTION_GROUP', 'xml section group', 'N');

insert into dr$object_attribute values
  (50501, 5, 5, 1, 
   'ZONE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50502, 5, 5, 2, 
   'FIELD', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50503, 5, 5, 3, 
   'SPECIAL', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50505, 5, 5, 5, 
   'ATTR', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50507, 5, 5, 7, 
   'MDATA', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (5, 6, 'NEWS_SECTION_GROUP', 'news section group', 'N');

insert into dr$object_attribute values
  (50601, 5, 6, 1, 
   'ZONE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50602, 5, 6, 2, 
   'FIELD', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50603, 5, 6, 3, 
   'SPECIAL', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (50607, 5, 6, 7, 
   'MDATA', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (5, 7, 'AUTO_SECTION_GROUP', 'auto section group', 'N');

insert into dr$object_attribute values
  (50704, 5, 7, 4, 
   'STOP', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (5, 8, 'PATH_SECTION_GROUP', 'path section group', 'N');

insert into dr$object values
  (5, 9, 'CTXXPATH_SECTION_GROUP', 'special section group for ctxxpath indexes only', 'Y');

insert into dr$class values
  (6, 'LEXER', 'Lexer Class', 'N');

insert into dr$object values
  (6, 1, 'BASIC_LEXER', 'Lexer for alphabetic languages', 'N');

insert into dr$object_attribute values
  (60101, 6, 1, 1, 
   'PUNCTUATIONS', 'Characters which end a sentence',
   'N', 'N', 'Y', 'S', 
   '.?!', null, null, 'N');

insert into dr$object_attribute values
  (60102, 6, 1, 2, 
   'PRINTJOINS', 'Characters which join words together',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (60103, 6, 1, 3, 
   'SKIPJOINS', 'Non-printing join characters',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (60104, 6, 1, 4, 
   'NUMJOIN', 'Decimal point',
   'N', 'N', 'Y', 'S', 
   'NLS numeric decimal character', null, 1, 'N');

insert into dr$object_attribute values
  (60105, 6, 1, 5, 
   'NUMGROUP', 'Character used every 3 digits for readability',
   'N', 'N', 'Y', 'S', 
   'NLS numeric group seperator', null, 1, 'N');

insert into dr$object_attribute values
  (60106, 6, 1, 6, 
   'CONTINUATION', 'Character which splits a word from one line to the next',
   'N', 'N', 'Y', 'S', 
   '-\', null, null, 'N');

insert into dr$object_attribute values
  (60107, 6, 1, 7, 
   'BASE_LETTER', 'Base-letter conversion',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60108, 6, 1, 8, 
   'STARTJOINS', 'Characters which can only come at the start of a word',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (60109, 6, 1, 9, 
   'ENDJOINS', 'Characters which can only come at the end of a word',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (60110, 6, 1, 10, 
   'COMPOSITE', 'Language for composite lexing',
   'N', 'N', 'Y', 'I', 
   'DEFAULT', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60110, 'DEFAULT', 0, 'Default');

insert into dr$object_attribute_lov values
  (60110, 'GERMAN', 1, 'German');

insert into dr$object_attribute_lov values
  (60110, 'DUTCH', 2, 'Dutch');

insert into dr$object_attribute values
  (60111, 6, 1, 11, 
   'MIXED_CASE', 'Preserve mixed-case',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60112, 6, 1, 12, 
   'INDEX_TEXT', 'Text keyword indexing',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60113, 6, 1, 13, 
   'INDEX_THEMES', 'Theme indexing',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60114, 6, 1, 14, 
   'ALTERNATE_SPELLING', 'Language for alternate spelling',
   'N', 'N', 'Y', 'I', 
   'NONE', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60114, 'NONE', 0, 'None');

insert into dr$object_attribute_lov values
  (60114, 'GERMAN', 1, 'German');

insert into dr$object_attribute_lov values
  (60114, 'DANISH', 2, 'Danish');

insert into dr$object_attribute_lov values
  (60114, 'SWEDISH', 3, 'Swedish');

insert into dr$object_attribute values
  (60115, 6, 1, 15, 
   'WHITESPACE', 'Whitespace characters used for EOS/EOP',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (60116, 6, 1, 16, 
   'NEWLINE', 'Newline character used for EOS/EOP',
   'N', 'N', 'Y', 'I', 
   'NEWLINE', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60116, 'NEWLINE', 1, 'newline');

insert into dr$object_attribute_lov values
  (60116, 'CARRIAGE_RETURN', 2, 'carriage return');

insert into dr$object_attribute values
  (60117, 6, 1, 17, 
   'THEME_LANGUAGE', 'lexicon to use for theme generation',
   'N', 'N', 'Y', 'I', 
   'AUTO', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60117, 'AUTO', 0, 'From environment setting');

insert into dr$object_attribute_lov values
  (60117, 'ENGLISH', 13, 'English');

insert into dr$object_attribute_lov values
  (60117, 'FRENCH', 16, 'French');

insert into dr$object_attribute_lov values
  (60117, 'AMERICAN', 1, 'American English');

insert into dr$object_attribute_lov values
  (60117, 'ARABIC', 2, 'Arabic');

insert into dr$object_attribute_lov values
  (60117, 'BENGALI', 3, 'Bengali');

insert into dr$object_attribute_lov values
  (60117, 'BRAZILIAN_PORTUGESE', 4, 'Brazilian Portugese');

insert into dr$object_attribute_lov values
  (60117, 'BULGARIAN', 5, 'Bulgarian');

insert into dr$object_attribute_lov values
  (60117, 'CANADIAN_FRENCH', 6, 'Canadian French');

insert into dr$object_attribute_lov values
  (60117, 'CATALAN', 7, 'Catalan');

insert into dr$object_attribute_lov values
  (60117, 'CROATIAN', 8, 'Croatian');

insert into dr$object_attribute_lov values
  (60117, 'CZECH', 9, 'Czech');

insert into dr$object_attribute_lov values
  (60117, 'DANISH', 10, 'Danish');

insert into dr$object_attribute_lov values
  (60117, 'DUTCH', 11, 'Dutch');

insert into dr$object_attribute_lov values
  (60117, 'EGYPTIAN', 12, 'Egyptian');

insert into dr$object_attribute_lov values
  (60117, 'ESTONIAN', 14, 'Estonian');

insert into dr$object_attribute_lov values
  (60117, 'FINNISH', 15, 'Finnish');

insert into dr$object_attribute_lov values
  (60117, 'GERMAN_DIN', 17, 'German Din');

insert into dr$object_attribute_lov values
  (60117, 'GERMAN', 18, 'German');

insert into dr$object_attribute_lov values
  (60117, 'GREEK', 19, 'Greek');

insert into dr$object_attribute_lov values
  (60117, 'HEBREW', 20, 'Hebrew');

insert into dr$object_attribute_lov values
  (60117, 'HUNGARIAN', 21, 'Hungarian');

insert into dr$object_attribute_lov values
  (60117, 'ICELANDIC', 22, 'Icelandic');

insert into dr$object_attribute_lov values
  (60117, 'INDONESIAN', 23, 'Indonesian');

insert into dr$object_attribute_lov values
  (60117, 'ITALIAN', 24, 'Italian');

insert into dr$object_attribute_lov values
  (60117, 'JAPANESE', 25, 'Japanese');

insert into dr$object_attribute_lov values
  (60117, 'KOREAN', 26, 'Korean');

insert into dr$object_attribute_lov values
  (60117, 'LATIN_AMERICAN_SPANISH', 27, 'Latin American Spanish');

insert into dr$object_attribute_lov values
  (60117, 'LATVIAN', 28, 'Latvian');

insert into dr$object_attribute_lov values
  (60117, 'LITHUANIAN', 29, 'Lithuanian');

insert into dr$object_attribute_lov values
  (60117, 'MALAY', 30, 'Malay');

insert into dr$object_attribute_lov values
  (60117, 'MEXICAN_SPANISH', 31, 'Mexican Spanish');

insert into dr$object_attribute_lov values
  (60117, 'NORWEGIAN', 32, 'Norwegian');

insert into dr$object_attribute_lov values
  (60117, 'POLISH', 33, 'Polish');

insert into dr$object_attribute_lov values
  (60117, 'PORTUGESE', 34, 'Portugese');

insert into dr$object_attribute_lov values
  (60117, 'ROMANIAN', 35, 'Romanian');

insert into dr$object_attribute_lov values
  (60117, 'RUSSIAN', 36, 'Russian');

insert into dr$object_attribute_lov values
  (60117, 'SIMPLIFIED_CHINESE', 37, 'Simplified Chinese');

insert into dr$object_attribute_lov values
  (60117, 'SLOVAK', 38, 'Slovak');

insert into dr$object_attribute_lov values
  (60117, 'SLOVENIAN', 39, 'Slovenian');

insert into dr$object_attribute_lov values
  (60117, 'SPANISH', 40, 'Spanish');

insert into dr$object_attribute_lov values
  (60117, 'SWEDISH', 41, 'Swedish');

insert into dr$object_attribute_lov values
  (60117, 'THAI', 42, 'Thai');

insert into dr$object_attribute_lov values
  (60117, 'TRADITIONAL_CHINESE', 43, 'Traditional Chinese');

insert into dr$object_attribute_lov values
  (60117, 'TURKISH', 44, 'Turkish');

insert into dr$object_attribute_lov values
  (60117, 'UKRANIAN', 45, 'Ukranian');

insert into dr$object_attribute_lov values
  (60117, 'VIETNAMESE', 46, 'Vietnamese');

insert into dr$object_attribute values
  (60118, 6, 1, 18, 
   'PROVE_THEMES', 'Prove themes during theme indexing',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60119, 6, 1, 19, 
   'BASE_LETTER_TYPE', 'Type of base_letter',
   'N', 'N', 'Y', 'I', 
   'GENERIC', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60119, 'GENERIC', 0, 'Works in all languages');

insert into dr$object_attribute_lov values
  (60119, 'SPECIFIC', 1, 'NLS_LANG specific');

insert into dr$object_attribute values
  (60120, 6, 1, 20, 
   'INDEX_STEMS', 'Language for indexing stemmer',
   'N', 'N', 'Y', 'I', 
   'NONE', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60120, 'NONE', 0, 'Do not index stems');

insert into dr$object_attribute_lov values
  (60120, 'ENGLISH', 1, 'English (inflectional)');

insert into dr$object_attribute_lov values
  (60120, 'DERIVATIONAL', 2, 'English (derivational)');

insert into dr$object_attribute_lov values
  (60120, 'DUTCH', 3, 'Dutch');

insert into dr$object_attribute_lov values
  (60120, 'FRENCH', 4, 'French');

insert into dr$object_attribute_lov values
  (60120, 'GERMAN', 5, 'German');

insert into dr$object_attribute_lov values
  (60120, 'ITALIAN', 6, 'Italian');

insert into dr$object_attribute_lov values
  (60120, 'SPANISH', 7, 'Spanish');

insert into dr$object_attribute values
  (60121, 6, 1, 21, 
   'OVERRIDE_BASE_LETTER', 'Alternate Spelling override Base Letter for umlauts',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60122, 6, 1, 22, 
   'NEW_GERMAN_SPELLING', 'Convert words to new German Spelling',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object values
  (6, 2, 'JAPANESE_VGRAM_LEXER', 'V-gram lexer for Japanese', 'N');

insert into dr$object_attribute values
  (60207, 6, 2, 7, 
   'MIXED_CASE_ASCII7', 'Preserve case of 7-bit ASCII characters',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60209, 6, 2, 9, 
   'DELIMITER', 'Special treatment for the delimiter characters of Japanese text',
   'N', 'N', 'Y', 'I', 
   'NONE', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60209, 'NONE', 0, 'Default');

insert into dr$object_attribute_lov values
  (60209, 'ALL', 1, 'Special delimiter handle(#2195868)');

insert into dr$object values
  (6, 3, 'KOREAN_LEXER', 'Dictionary-based lexer for Korean', 'Y');

insert into dr$object_attribute values
  (60301, 6, 3, 1, 
   'VERB', 'index verb',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60302, 6, 3, 2, 
   'ADJECTIVE', 'index include adjective',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60303, 6, 3, 3, 
   'ADVERB', 'index include adverb',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60304, 6, 3, 4, 
   'ONECHAR', 'index one character',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60305, 6, 3, 5, 
   'NUMBER', 'index number',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60306, 6, 3, 6, 
   'UDIC', 'index user dictionary',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60307, 6, 3, 7, 
   'XDIC', 'index x-user dictionary',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60308, 6, 3, 8, 
   'COMPOSITE', 'index composite',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60309, 6, 3, 9, 
   'MORPHEME', 'morphological analysis',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60310, 6, 3, 10, 
   'TOUPPER', 'convert english to uppercase',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60311, 6, 3, 11, 
   'TOHANGEUL', 'convert hanja to hangeul',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60312, 6, 3, 12, 
   'SEGMENTATION', 'split a word into each segment',
   'N', 'Y', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object values
  (6, 4, 'CHINESE_VGRAM_LEXER', 'V-GRAM lexer for Chinese', 'N');

insert into dr$object_attribute values
  (60407, 6, 4, 7, 
   'MIXED_CASE_ASCII7', 'Preserve case of 7-bit ASCII characters',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object values
  (6, 5, 'CHINESE_LEXER', 'Chinese lexer', 'N');

insert into dr$object_attribute values
  (60507, 6, 5, 7, 
   'MIXED_CASE_ASCII7', 'Preserve case of 7-bit ASCII characters',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object values
  (6, 6, 'MULTI_LEXER', 'Multi-language lexer', 'N');

insert into dr$object_attribute values
  (60601, 6, 6, 1, 
   'SUB_LEXER', '',
   'N', 'Y', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (60602, 6, 6, 2, 
   'SUB_LEXER_ATTR', '',
   'N', 'Y', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (6, 7, 'KOREAN_MORPH_LEXER', 'Korean Morphological lexer', 'N');

insert into dr$object_attribute values
  (60701, 6, 7, 1, 
   'VERB_ADJECTIVE', 'index verbs and adjectives',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60702, 6, 7, 2, 
   'ONE_CHAR_WORD', 'index single characters',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60703, 6, 7, 3, 
   'NUMBER', 'index numbers',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60704, 6, 7, 4, 
   'USER_DIC', 'index words in user dictionary',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60705, 6, 7, 5, 
   'STOP_DIC', 'index words in x-user dictionary',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60706, 6, 7, 6, 
   'MORPHEME', 'perform morphological analysis',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60707, 6, 7, 7, 
   'COMPOSITE', 'define indexing style of composite nouns',
   'N', 'N', 'Y', 'I', 
   'COMPONENT_WORD', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60707, 'COMPOSITE_ONLY', 0, 'index only composite nouns');

insert into dr$object_attribute_lov values
  (60707, 'COMPONENT_WORD', 1, 'index single nouns');

insert into dr$object_attribute_lov values
  (60707, 'NGRAM', 2, 'use n-gram indexing style');

insert into dr$object_attribute values
  (60708, 6, 7, 8, 
   'TO_UPPER', 'convert english words to uppercase',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (60709, 6, 7, 9, 
   'HANJA', 'index hanja itself without converting to hangeul',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60710, 6, 7, 10, 
   'LONG_WORD', 'index words with original length greater than 16',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60711, 6, 7, 11, 
   'JAPANESE', 'index japanese character in current character set.',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60712, 6, 7, 12, 
   'ENGLISH', 'index alphanumeric string that starts with alphabet',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object values
  (6, 8, 'JAPANESE_LEXER', 'Japanese lexer', 'N');

insert into dr$object_attribute values
  (60807, 6, 8, 7, 
   'MIXED_CASE_ASCII7', 'Preserve case of 7-bit ASCII characters',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (60809, 6, 8, 9, 
   'DELIMITER', 'Special treatment for the delimiter characters of Japanese text',
   'N', 'N', 'Y', 'I', 
   'NONE', null, null, 'Y');

insert into dr$object_attribute_lov values
  (60809, 'NONE', 0, 'Default');

insert into dr$object_attribute_lov values
  (60809, 'ALL', 1, 'Special delimiter handle(#2195868)');

insert into dr$object values
  (6, 9, 'NULL_LEXER', 'special lexer for use in ctxxpath indexes only', 'Y');

insert into dr$object values
  (6, 10, 'USER_LEXER', 'user-defined lexer', 'N');

insert into dr$object_attribute values
  (61001, 6, 10, 1, 
   'INDEX_PROCEDURE', 'name of the indexing stored procedure',
   'Y', 'N', 'Y', 'P', 
   'REQUIRED', null, null, 'N');

insert into dr$object_attribute values
  (61002, 6, 10, 2, 
   'INPUT_TYPE', 'datatype of the input arguments of indexing stored procedure',
   'N', 'N', 'Y', 'I', 
   'CLOB', null, null, 'Y');

insert into dr$object_attribute_lov values
  (61002, 'CLOB', 1, 'CLOB');

insert into dr$object_attribute_lov values
  (61002, 'VARCHAR2', 2, 'VARCHAR2');

insert into dr$object_attribute values
  (61003, 6, 10, 3, 
   'QUERY_PROCEDURE', 'name of the query stored procedure',
   'Y', 'N', 'Y', 'P', 
   'REQUIRED', null, null, 'N');

insert into dr$object values
  (6, 11, 'WORLD_LEXER', 'Unicode world lexer', 'N');

insert into dr$object_attribute values
  (61107, 6, 11, 7, 
   'MIXED_CASE', 'Preserve mixed-case',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$class values
  (7, 'WORDLIST', 'Word List Class', 'N');

insert into dr$object values
  (7, 1, 'BASIC_WORDLIST', 'basic wordlist', 'N');

insert into dr$object_attribute values
  (70101, 7, 1, 1, 
   'STEMMER', 'Language for stemmer',
   'N', 'N', 'Y', 'I', 
   'ENGLISH', null, null, 'Y');

insert into dr$object_attribute_lov values
  (70101, 'ENGLISH', 1, 'English (inflectional)');

insert into dr$object_attribute_lov values
  (70101, 'DERIVATIONAL', 2, 'English (derivational)');

insert into dr$object_attribute_lov values
  (70101, 'DUTCH', 3, 'Dutch');

insert into dr$object_attribute_lov values
  (70101, 'FRENCH', 4, 'French');

insert into dr$object_attribute_lov values
  (70101, 'GERMAN', 5, 'German');

insert into dr$object_attribute_lov values
  (70101, 'ITALIAN', 6, 'Italian');

insert into dr$object_attribute_lov values
  (70101, 'SPANISH', 7, 'Spanish');

insert into dr$object_attribute_lov values
  (70101, 'NULL', 8, 'Do not stem');

insert into dr$object_attribute_lov values
  (70101, 'AUTO', 9, 'From environment setting');

insert into dr$object_attribute_lov values
  (70101, 'JAPANESE', 10, 'Japanese');

insert into dr$object_attribute values
  (70102, 7, 1, 2, 
   'FUZZY_MATCH', 'Fuzzy matching type',
   'N', 'N', 'Y', 'I', 
   'GENERIC', null, null, 'Y');

insert into dr$object_attribute_lov values
  (70102, 'GENERIC', 1, 'Generic');

insert into dr$object_attribute_lov values
  (70102, 'JAPANESE_VGRAM', 2, 'Japanese V-GRAM');

insert into dr$object_attribute_lov values
  (70102, 'KOREAN', 3, 'Korean');

insert into dr$object_attribute_lov values
  (70102, 'CHINESE_VGRAM', 4, 'Chinese V-GRAM');

insert into dr$object_attribute_lov values
  (70102, 'ENGLISH', 5, 'English');

insert into dr$object_attribute_lov values
  (70102, 'DUTCH', 6, 'Dutch');

insert into dr$object_attribute_lov values
  (70102, 'FRENCH', 7, 'French');

insert into dr$object_attribute_lov values
  (70102, 'GERMAN', 8, 'German');

insert into dr$object_attribute_lov values
  (70102, 'ITALIAN', 9, 'Italian');

insert into dr$object_attribute_lov values
  (70102, 'SPANISH', 10, 'Spanish');

insert into dr$object_attribute_lov values
  (70102, 'OCR', 11, 'OCR');

insert into dr$object_attribute_lov values
  (70102, 'AUTO', 12, 'From environment setting');

insert into dr$object_attribute_lov values
  (70102, 'JAPANESE', 13, 'Japanese');

insert into dr$object_attribute values
  (70103, 7, 1, 3, 
   'FUZZY_SCORE', 'Do not return fuzzy matches below this score',
   'N', 'N', 'Y', 'I', 
   '60', 0, 80, 'N');

insert into dr$object_attribute values
  (70104, 7, 1, 4, 
   'FUZZY_NUMRESULTS', 'Return only this many fuzzy match results',
   'N', 'N', 'Y', 'I', 
   '100', 0, 5000, 'N');

insert into dr$object_attribute values
  (70105, 7, 1, 5, 
   'SUBSTRING_INDEX', 'Create substring index for fast left wildcard search',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (70106, 7, 1, 6, 
   'WILDCARD_MAXTERMS', 'Maximum number of terms allowed in wildcard query',
   'N', 'N', 'Y', 'I', 
   '5000', 1, 15000, 'N');

insert into dr$object_attribute values
  (70107, 7, 1, 7, 
   'PREFIX_INDEX', 'Index prefixes of tokens for faster prefix search',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (70108, 7, 1, 8, 
   'PREFIX_MIN_LENGTH', 'Minimum length of prefix when prefix index is on',
   'N', 'N', 'Y', 'I', 
   '1', 1, 64, 'N');

insert into dr$object_attribute values
  (70109, 7, 1, 9, 
   'PREFIX_MAX_LENGTH', 'Maximum length of prefix when prefix index is on',
   'N', 'N', 'Y', 'I', 
   '64', 1, 64, 'N');

insert into dr$class values
  (8, 'STOPLIST', 'Stop List Class', 'N');

insert into dr$object values
  (8, 1, 'BASIC_STOPLIST', 'basic stoplist', 'N');

insert into dr$object_attribute values
  (80101, 8, 1, 1, 
   'STOP_CLASS', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (80102, 8, 1, 2, 
   'STOP_WORD', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (80103, 8, 1, 3, 
   'STOP_THEME', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object values
  (8, 3, 'MULTI_STOPLIST', 'multi-language stoplist', 'N');

insert into dr$object_attribute values
  (80301, 8, 3, 1, 
   'STOP_CLASS', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (80302, 8, 3, 2, 
   'STOP_WORD', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, null, 'N');

insert into dr$class values
  (9, 'STORAGE', 'Storage Class', 'N');

insert into dr$object values
  (9, 1, 'BASIC_STORAGE', 'text-index storage', 'N');

insert into dr$object_attribute values
  (90101, 9, 1, 1, 
   'I_TABLE_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90102, 9, 1, 2, 
   'K_TABLE_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90103, 9, 1, 3, 
   'R_TABLE_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90104, 9, 1, 4, 
   'N_TABLE_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90105, 9, 1, 5, 
   'I_INDEX_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90106, 9, 1, 6, 
   'P_TABLE_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90107, 9, 1, 7, 
   'I_ROWID_INDEX_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (90108, 9, 1, 8, 
   'PART_SUB_STORAGE_ATTR', '',
   'N', 'Y', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$class values
  (10, 'INDEX_SET', 'Index Set', 'N');

insert into dr$object values
  (10, 1, 'BASIC_INDEX_SET', 'basic index set', 'N');

insert into dr$object_attribute values
  (100101, 10, 1, 1, 
   'NUM_COLUMNS', '',
   'N', 'Y', 'Y', 'I', 
   'NONE', null, null, 'N');

insert into dr$object_attribute values
  (100102, 10, 1, 2, 
   'COLUMN', '',
   'N', 'Y', 'Y', 'S', 
   'NONE', null, 256, 'N');

insert into dr$object_attribute values
  (100103, 10, 1, 3, 
   'COLUMN_LIST', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$object_attribute values
  (100104, 10, 1, 4, 
   'STORAGE_CLAUSE', '',
   'N', 'N', 'Y', 'S', 
   'NONE', null, 500, 'N');

insert into dr$class values
  (12, 'CLASSIFIER', 'classification preferences', 'N');

insert into dr$object values
  (12, 1, 'RULE_CLASSIFIER', 'rule based classifier', 'N');

insert into dr$object_attribute values
  (120101, 12, 1, 1, 
   'THRESHOLD', 'Minimum confidence level (in percentage) for rule generation for all classes',
   'N', 'N', 'Y', 'I', 
   '50', 1, 99, 'N');

insert into dr$object_attribute values
  (120102, 12, 1, 2, 
   'MAX_TERMS', 'Maximum number of terms in one class',
   'N', 'N', 'Y', 'I', 
   '100', 20, 2000, 'N');

insert into dr$object_attribute values
  (120103, 12, 1, 3, 
   'MEMORY_SIZE', 'Typical memory size in MB',
   'N', 'N', 'Y', 'I', 
   '500', 10, 4000, 'N');

insert into dr$object_attribute values
  (120104, 12, 1, 4, 
   'NT_THRESHOLD', 'minimum term occurring frequency (in the fraction of total number of documents)',
   'N', 'N', 'Y', 'F', 
   '0.001', 0, 0.90, 'N');

insert into dr$object_attribute values
  (120105, 12, 1, 5, 
   'TERM_THRESHOLD', 'Threshold value (in percentage) for term selection in one class',
   'N', 'N', 'Y', 'I', 
   '10', 0, 100, 'N');

insert into dr$object_attribute values
  (120106, 12, 1, 6, 
   'TREENUM', 'Number of trees built for one class',
   'N', 'N', 'Y', 'I', 
   '1', 1, 10, 'N');

insert into dr$object_attribute values
  (120107, 12, 1, 7, 
   'PRUNE_LEVEL', 'Specify how much to prune a tree, larger value (in percentage) means prune more',
   'N', 'N', 'Y', 'I', 
   '75', 0, 100, 'N');

insert into dr$object values
  (12, 2, 'SVM_CLASSIFIER', 'support vector machine classifier', 'N');

insert into dr$object_attribute values
  (120201, 12, 2, 1, 
   'MAX_DOCTERMS', 'Maximun number of distinct terms representing one document',
   'N', 'N', 'Y', 'I', 
   '50', 10, 8192, 'N');

insert into dr$object_attribute values
  (120202, 12, 2, 2, 
   'MAX_FEATURES', 'Maximun number of distinct features used in text mining',
   'N', 'N', 'Y', 'I', 
   '3000', 1, 100000, 'N');

insert into dr$object_attribute values
  (120203, 12, 2, 3, 
   'THEME_ON', 'Theme feature',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (120204, 12, 2, 4, 
   'TOKEN_ON', 'Token feature',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (120205, 12, 2, 5, 
   'STEM_ON', 'Stemmed Token feature',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (120206, 12, 2, 6, 
   'MEMORY_SIZE', 'Typical memory size in MB',
   'N', 'N', 'Y', 'I', 
   '500', 10, 4000, 'N');

insert into dr$object_attribute values
  (120207, 12, 2, 7, 
   'SECTION_WEIGHT', 'the multiplier of term occurrence within field section',
   'N', 'N', 'Y', 'I', 
   '2', 0, 100, 'N');

insert into dr$class values
  (99, 'CLUSTERING', 'clustering preferences', 'N');

insert into dr$object values
  (99, 1, 'KMEAN_CLUSTERING', 'K-MEAN clustering', 'N');

insert into dr$object_attribute values
  (990101, 99, 1, 1, 
   'MAX_DOCTERMS', 'Maximun number of distinct terms representing one document',
   'N', 'N', 'Y', 'I', 
   '50', 10, 8192, 'N');

insert into dr$object_attribute values
  (990102, 99, 1, 2, 
   'MAX_FEATURES', 'Maximun number of distinct features used in text mining',
   'N', 'N', 'Y', 'I', 
   '3000', 1, 500000, 'N');

insert into dr$object_attribute values
  (990103, 99, 1, 3, 
   'THEME_ON', 'Theme feature',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (990104, 99, 1, 4, 
   'TOKEN_ON', 'Token feature',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (990105, 99, 1, 5, 
   'STEM_ON', 'Stemmed Token feature',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (990106, 99, 1, 6, 
   'MEMORY_SIZE', 'Typical memory size in MB',
   'N', 'N', 'Y', 'I', 
   '500', 10, 4000, 'N');

insert into dr$object_attribute values
  (990107, 99, 1, 7, 
   'SECTION_WEIGHT', 'the multiplier of term occurrence within field section',
   'N', 'N', 'Y', 'I', 
   '2', 0, 100, 'N');

insert into dr$object_attribute values
  (990108, 99, 1, 8, 
   'CLUSTER_NUM', 'The maximum number of clusters to be generated',
   'N', 'N', 'Y', 'I', 
   '200', 2, 20000, 'N');

insert into dr$object values
  (99, 2, 'TEXTK_CLUSTERING', 'TEXTK clustering', 'N');

insert into dr$object_attribute values
  (990201, 99, 2, 1, 
   'MAX_DOCTERMS', 'Maximun number of distinct terms representing one document',
   'N', 'N', 'Y', 'I', 
   '50', 10, 8192, 'N');

insert into dr$object_attribute values
  (990202, 99, 2, 2, 
   'MAX_FEATURES', 'Maximun number of distinct features used in text mining',
   'N', 'N', 'Y', 'I', 
   '3000', 1, 500000, 'N');

insert into dr$object_attribute values
  (990203, 99, 2, 3, 
   'THEME_ON', 'Theme feature',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (990204, 99, 2, 4, 
   'TOKEN_ON', 'Token feature',
   'N', 'N', 'Y', 'B', 
   'TRUE', null, null, 'N');

insert into dr$object_attribute values
  (990205, 99, 2, 5, 
   'STEM_ON', 'Stemmed Token feature',
   'N', 'N', 'Y', 'B', 
   'FALSE', null, null, 'N');

insert into dr$object_attribute values
  (990206, 99, 2, 6, 
   'MEMORY_SIZE', 'Typical memory size in MB',
   'N', 'N', 'Y', 'I', 
   '500', 10, 4000, 'N');

insert into dr$object_attribute values
  (990207, 99, 2, 7, 
   'SECTION_WEIGHT', 'the multiplier of term occurrence within field section',
   'N', 'N', 'Y', 'I', 
   '2', 0, 100, 'N');

insert into dr$object_attribute values
  (990208, 99, 2, 8, 
   'CLUSTER_NUM', 'The maximum number of clusters to be generated',
   'N', 'N', 'Y', 'I', 
   '200', 2, 20000, 'N');

insert into dr$object_attribute values
  (990209, 99, 2, 9, 
   'MIN_SIMILARITY', 'The minimum similarity score for each leaf cluster',
   'N', 'N', 'Y', 'F', 
   '0.2', 0.01, 0.99, 'N');

insert into dr$object_attribute values
  (990210, 99, 2, 10, 
   'HIERARCHY_DEPTH', 'The maximum depth of hierarchy',
   'N', 'N', 'Y', 'I', 
   '1', 1, 20, 'N');

commit;

