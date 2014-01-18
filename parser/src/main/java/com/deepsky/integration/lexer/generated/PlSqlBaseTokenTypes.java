package com.deepsky.integration.lexer.generated;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.deepsky.integration.plsql.PlSqlTokenType;

public interface PlSqlBaseTokenTypes {

		IElementType DOLLAR = new PlSqlTokenType(43, "DOLLAR");
		IElementType LT = new PlSqlTokenType(10, "LT");
		IElementType QUOTED_STR = new PlSqlTokenType(24, "QUOTED_STR");
		IElementType STORAGE_SIZE = new PlSqlTokenType(9, "STORAGE_SIZE");
		IElementType DOUBLEDOT = new PlSqlTokenType(40, "DOUBLEDOT");
		IElementType ANY_CHARACTER = new PlSqlTokenType(22, "ANY_CHARACTER");
		IElementType BAD_CHARACTER = new PlSqlTokenType(6, "BAD_CHARACTER");
		IElementType ASTERISK = new PlSqlTokenType(31, "ASTERISK");
		IElementType ML_COMMENT = new PlSqlTokenType(54, "ML_COMMENT");
		IElementType THEN_COND_CMPL = new PlSqlTokenType(56, "THEN_COND_CMPL");
		IElementType COMMA = new PlSqlTokenType(30, "COMMA");
		IElementType IDENTIFIER = new PlSqlTokenType(23, "IDENTIFIER");
		IElementType BAD_QUOTED_STRING = new PlSqlTokenType(20, "BAD_QUOTED_STRING");
		IElementType ELSE_COND_CMPL = new PlSqlTokenType(57, "ELSE_COND_CMPL");
		IElementType CUSTOM_TOKEN = new PlSqlTokenType(14, "CUSTOM_TOKEN");
		IElementType AT_PREFIXED = new PlSqlTokenType(26, "AT_PREFIXED");
		IElementType PLUS = new PlSqlTokenType(34, "PLUS");
		IElementType TABLE_NAME_SPEC = new PlSqlTokenType(17, "TABLE_NAME_SPEC");
		IElementType CLOSE_PAREN = new PlSqlTokenType(33, "CLOSE_PAREN");
		IElementType END_COND_CMPL = new PlSqlTokenType(58, "END_COND_CMPL");
		IElementType PASS_BY_NAME = new PlSqlTokenType(46, "PASS_BY_NAME");
		IElementType BAD_ML_COMMENT = new PlSqlTokenType(5, "BAD_ML_COMMENT");
		IElementType EQ = new PlSqlTokenType(38, "EQ");
		IElementType DOT = new PlSqlTokenType(29, "DOT");
		IElementType ASSIGNMENT_EQ = new PlSqlTokenType(45, "ASSIGNMENT_EQ");
		IElementType ERROR_COND_CMPL = new PlSqlTokenType(59, "ERROR_COND_CMPL");
		IElementType DIVIDE = new PlSqlTokenType(36, "DIVIDE");
		IElementType DOUBLE_QUOTED_STRING = new PlSqlTokenType(25, "DOUBLE_QUOTED_STRING");
		IElementType QUOTED_STR_START = new PlSqlTokenType(18, "QUOTED_STR_START");
		IElementType START_LABEL = new PlSqlTokenType(42, "START_LABEL");
		IElementType GE = new PlSqlTokenType(12, "GE");
		IElementType CONCAT = new PlSqlTokenType(41, "CONCAT");
		IElementType BAD_NUMBER_LITERAL = new PlSqlTokenType(8, "BAD_NUMBER_LITERAL");
		IElementType N = new PlSqlTokenType(50, "N");
		IElementType C_MARKER = new PlSqlTokenType(4, "C_MARKER");
		IElementType BAD_CHAR_LITERAL = new PlSqlTokenType(7, "BAD_CHAR_LITERAL");
		IElementType NUMBER = new PlSqlTokenType(49, "NUMBER");
		IElementType COLON_OLD = new PlSqlTokenType(16, "COLON_OLD");
		IElementType OPEN_PAREN = new PlSqlTokenType(32, "OPEN_PAREN");
		IElementType MINUS = new PlSqlTokenType(35, "MINUS");
		IElementType SEMI = new PlSqlTokenType(27, "SEMI");
		IElementType PERCENTAGE = new PlSqlTokenType(39, "PERCENTAGE");
		IElementType NOT_EQ = new PlSqlTokenType(13, "NOT_EQ");
		IElementType VERTBAR = new PlSqlTokenType(47, "VERTBAR");
		IElementType PLSQL_ENV_VAR = new PlSqlTokenType(21, "PLSQL_ENV_VAR");
		IElementType COLON = new PlSqlTokenType(28, "COLON");
		IElementType WS = new PlSqlTokenType(51, "WS");
		IElementType END_LABEL = new PlSqlTokenType(44, "END_LABEL");
		IElementType SL_COMMENT = new PlSqlTokenType(53, "SL_COMMENT");
		IElementType QUOTED_STR_END = new PlSqlTokenType(19, "QUOTED_STR_END");
		IElementType GT = new PlSqlTokenType(48, "GT");
		IElementType IF_COND_CMPL = new PlSqlTokenType(55, "IF_COND_CMPL");
		IElementType COLON_NEW = new PlSqlTokenType(15, "COLON_NEW");
		IElementType LE = new PlSqlTokenType(11, "LE");
		IElementType LF = new PlSqlTokenType(52, "LF");
		IElementType BACKSLASH = new PlSqlTokenType(37, "BACKSLASH");
		IElementType KEYWORD_USING = new PlSqlTokenType(453, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(724, "ERROR_INDEX");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(602, "REFERENCING");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(611, "NESTED");
		IElementType KEYWORD_STORE = new PlSqlTokenType(612, "STORE");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(711, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(654, "EXTERNAL");
		IElementType KEYWORD_PRIVILEGES = new PlSqlTokenType(825, "PRIVILEGES");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(860, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(633, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(755, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(920, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(910, "MISSING");
		IElementType KEYWORD_OVER = new PlSqlTokenType(810, "OVER");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(467, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(742, "TIME");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(889, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(463, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(432, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(586, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(846, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(729, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(493, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(640, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(871, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(733, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(804, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(632, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(592, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(471, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(651, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(714, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(601, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(418, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(891, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(771, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(808, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(470, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(630, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(778, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(869, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(582, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(795, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(546, "NOGUARANTEE");
		IElementType KEYWORD_DEBUG = new PlSqlTokenType(827, "DEBUG");
		IElementType KEYWORD_GLOBALLY = new PlSqlTokenType(520, "GLOBALLY");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(656, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(859, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(908, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(619, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(558, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(715, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(787, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(845, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(584, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(793, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(842, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(490, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(708, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(650, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(752, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(849, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(806, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(430, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(588, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(683, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(604, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(560, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(425, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(434, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(492, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(788, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(855, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(428, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(743, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(897, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(413, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(680, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(415, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(554, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(770, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(801, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(518, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(474, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(865, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(807, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(901, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(466, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(480, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(443, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(483, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(754, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(500, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(562, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(628, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(887, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(642, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(730, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(441, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(746, "DAY");
		IElementType KEYWORD_FAST = new PlSqlTokenType(700, "FAST");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(837, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(600, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(739, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(580, "CONNECT");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(815, "TIMEZONE_HOUR");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(550, "NOLOGGING");
		IElementType KEYWORD_SET = new PlSqlTokenType(473, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(476, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(906, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(591, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(439, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(652, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(800, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(446, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(777, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(460, "DISABLE");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(798, "TRIM");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(444, "TYPES");
		IElementType KEYWORD_ALL = new PlSqlTokenType(629, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(638, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(903, "NODISCARDFILE");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(691, "PRECISION");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(713, "CONSTANT");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(872, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(542, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(786, "AT");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(589, "AUDIT");
		IElementType KEYWORD_AS = new PlSqlTokenType(515, "AS");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(671, "CELL_FLASH_CACHE");
		IElementType KEYWORD_NEVER = new PlSqlTokenType(697, "NEVER");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(839, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(419, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(499, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(776, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(883, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(799, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(916, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(551, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(575, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(412, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(886, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(597, "OF");
		IElementType KEYWORD_REWRITE = new PlSqlTokenType(696, "REWRITE");
		IElementType KEYWORD_RANK = new PlSqlTokenType(802, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(738, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(841, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(557, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(457, "ON");
		IElementType KEYWORD_REFRESH = new PlSqlTokenType(698, "REFRESH");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(417, "PURGE");
		IElementType KEYWORD_785 = new PlSqlTokenType(785, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(658, "LIMIT");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(866, "FETCH");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(576, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(568, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(514, "FINAL");
		IElementType KEYWORD_OR = new PlSqlTokenType(507, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(933, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(583, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(462, "ROW");
		IElementType KEYWORD_EXCLUDING = new PlSqlTokenType(692, "EXCLUDING");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(448, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(888, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(578, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(893, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(831, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(728, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(745, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(885, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(587, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(456, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(623, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(645, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(794, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(861, "MERGE");
		IElementType KEYWORD_EXTERNALLY = new PlSqlTokenType(519, "EXTERNALLY");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(464, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(626, "PCTTHRESHOLD");
		IElementType KEYWORD_BUILD = new PlSqlTokenType(699, "BUILD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(494, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(853, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(547, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(927, "ZONED");
		IElementType KEYWORD_HIERARCHY = new PlSqlTokenType(826, "HIERARCHY");
		IElementType KEYWORD_NULL = new PlSqlTokenType(454, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(567, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(720, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(783, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(939, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(717, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(904, "DISCARDFILE");
		IElementType KEYWORD_ACCOUNT = new PlSqlTokenType(527, "ACCOUNT");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(424, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(599, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(817, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(858, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(726, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(747, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(779, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(937, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(737, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(622, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(851, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(426, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(705, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(731, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(594, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(661, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(643, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(495, "SPOOL");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(465, "PRIMARY");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(605, "WHEN");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(615, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(675, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(488, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(620, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(863, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(572, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(571, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(634, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(835, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(820, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(751, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(534, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(725, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(610, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(559, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(834, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(563, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(641, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(501, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(905, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(678, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(487, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(814, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(564, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(552, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(716, "WHILE");
		IElementType KEYWORD_ID = new PlSqlTokenType(693, "ID");
		IElementType KEYWORD_REAL = new PlSqlTokenType(749, "REAL");
		IElementType KEYWORD_MATERIALIZED = new PlSqlTokenType(509, "MATERIALIZED");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(539, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(823, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(544, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(556, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(637, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(647, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(792, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(458, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(538, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(923, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(685, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(838, "INTO");
		IElementType KEYWORD_RESOURCE = new PlSqlTokenType(829, "RESOURCE");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(676, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(709, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(914, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(621, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(435, "DATABASE");
		IElementType KEYWORD_INDEXTYPE = new PlSqlTokenType(830, "INDEXTYPE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(472, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(541, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(718, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(931, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(862, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(686, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(856, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(666, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(427, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(438, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(595, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(667, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(663, "FREELISTS");
		IElementType KEYWORD_REDUCED = new PlSqlTokenType(690, "REDUCED");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(740, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(744, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(772, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(687, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(759, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(561, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(918, "WHITESPACE");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(513, "UNDER");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(420, "CONSTRAINTS");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(769, "CHARSET");
		IElementType KEYWORD_LOG = new PlSqlTokenType(510, "LOG");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(919, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(503, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(707, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(618, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(812, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(447, "SYSTEM");
		IElementType KEYWORD_DECODE = new PlSqlTokenType(797, "DECODE");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(819, "LEADING");
		IElementType KEYWORD_703 = new PlSqlTokenType(703, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(753, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(900, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(662, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(712, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(608, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(677, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(569, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(873, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(535, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(763, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(890, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(498, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(523, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(624, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(929, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(741, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(765, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(704, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(616, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(824, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(710, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(481, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(773, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(422, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(757, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(679, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(734, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(670, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(809, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(758, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(836, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(414, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(911, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(508, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(928, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(639, "NOPARALLEL");
		IElementType KEYWORD_ADMIN = new PlSqlTokenType(828, "ADMIN");
		IElementType KEYWORD_STRING = new PlSqlTokenType(896, "STRING");
		IElementType KEYWORD_UNUSABLE = new PlSqlTokenType(844, "UNUSABLE");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(491, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(477, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(840, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(668, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(565, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(478, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(821, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(847, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(429, "SYNONYM");
		IElementType KEYWORD_BECOME = new PlSqlTokenType(832, "BECOME");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(876, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(517, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(581, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(935, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(646, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(764, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(486, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(672, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(811, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(924, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(875, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(909, "FIELDS");
		IElementType KEYWORD_DEMAND = new PlSqlTokenType(702, "DEMAND");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(882, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(423, "INDEX");
		IElementType KEYWORD_STA = new PlSqlTokenType(496, "STA");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(606, "BITMAP");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(816, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(789, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(570, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(445, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(451, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(537, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(796, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(648, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(479, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(727, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(673, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(735, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(664, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(818, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(536, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(532, "TEMPFILE");
		IElementType KEYWORD_COMPLETE = new PlSqlTokenType(701, "COMPLETE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(879, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(934, "DATE_FORMAT");
		IElementType KEYWORD_REBUILD = new PlSqlTokenType(843, "REBUILD");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(681, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(723, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(877, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(915, "LOBFILE");
		IElementType KEYWORD_PROFILE = new PlSqlTokenType(524, "PROFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(442, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(627, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(767, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(555, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(930, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(631, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(880, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(748, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(452, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(766, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(531, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(938, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(617, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(684, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(706, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(750, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(579, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(813, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(870, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(912, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(659, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(590, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(636, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(768, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(784, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(881, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(665, "GROUPS");
		IElementType KEYWORD_UNLOCK = new PlSqlTokenType(529, "UNLOCK");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(416, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(790, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(506, "CREATE");
		IElementType KEYWORD_WITHOUT = new PlSqlTokenType(689, "WITHOUT");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(484, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(459, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(682, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(669, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(607, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(497, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(455, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(782, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(775, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(925, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(921, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(898, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(644, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(635, "INITRANS");
		IElementType KEYWORD_QUERY = new PlSqlTokenType(695, "QUERY");
		IElementType KEYWORD_LINK = new PlSqlTokenType(436, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(732, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(895, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(553, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(791, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(878, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(867, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(761, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(902, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(780, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(489, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(833, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(598, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(722, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(774, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(566, "END");
		IElementType KEYWORD_EXPIRE = new PlSqlTokenType(526, "EXPIRE");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(437, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(721, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(468, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(574, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(613, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(543, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(530, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(926, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(874, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(913, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(852, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(655, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(433, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(917, "TERMINATED");
		IElementType KEYWORD_OLD = new PlSqlTokenType(603, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(660, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(593, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(864, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(533, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(760, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(505, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(461, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(936, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(892, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(475, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(907, "SKIP");
		IElementType KEYWORD_PASSWORD = new PlSqlTokenType(525, "PASSWORD");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(609, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(421, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(653, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(521, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(884, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(736, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(822, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(614, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(649, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(932, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(511, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(894, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(549, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(781, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(674, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(450, "DEFAULT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(803, "DENSE_RANK");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(540, "MANAGEMENT");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(899, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(657, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(512, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(585, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(516, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(805, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(868, "SHARE");
		IElementType KEYWORD_QUOTA = new PlSqlTokenType(522, "QUOTA");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(577, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(850, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(922, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(719, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(440, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(469, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(528, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(854, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(482, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(756, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(502, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(762, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(573, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(848, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(485, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(596, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(545, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(625, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(548, "GROUP");
		IElementType KEYWORD_PREBUILT = new PlSqlTokenType(688, "PREBUILT");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(857, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(431, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(694, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(449, "STORAGE");

	TokenSet KEYWORDS = TokenSet.create(
		KEYWORD_USING,
		KEYWORD_ERROR_INDEX,
		KEYWORD_REFERENCING,
		KEYWORD_NESTED,
		KEYWORD_STORE,
		KEYWORD_FIPSFLAG,
		KEYWORD_EXTERNAL,
		KEYWORD_PRIVILEGES,
		KEYWORD_WAIT,
		KEYWORD_PCTFREE,
		KEYWORD_FLOAT,
		KEYWORD_LRTRIM,
		KEYWORD_MISSING,
		KEYWORD_OVER,
		KEYWORD_REFERENCES,
		KEYWORD_TIME,
		KEYWORD_CHARACTERSET,
		KEYWORD_MOVEMENT,
		KEYWORD_ROLE,
		KEYWORD_LOGON,
		KEYWORD_RIGHT,
		KEYWORD_ELSE,
		KEYWORD_HOST,
		KEYWORD_MONITORING,
		KEYWORD_SAVEPOINT,
		KEYWORD_NUMBER,
		KEYWORD_EXTRACT,
		KEYWORD_NOCOMPRESS,
		KEYWORD_DIASSOCIATE,
		KEYWORD_SYSDATE,
		KEYWORD_NOVALIDATE,
		KEYWORD_SUBTYPE,
		KEYWORD_EACH,
		KEYWORD_VIEW,
		KEYWORD_BIG,
		KEYWORD_SERIALLY_REUSABLE,
		KEYWORD_NEXTVAL,
		KEYWORD_UNIQUE,
		KEYWORD_DIRECT_LOAD,
		KEYWORD_RAISE,
		KEYWORD_EXCLUSIVE,
		KEYWORD_BEFORE,
		KEYWORD_SQLERRM,
		KEYWORD_NOGUARANTEE,
		KEYWORD_DEBUG,
		KEYWORD_GLOBALLY,
		KEYWORD_INSTANCES,
		KEYWORD_NOWAIT,
		KEYWORD_PREPROCESSOR,
		KEYWORD_PCTVERSION,
		KEYWORD_WRITE,
		KEYWORD_LOOP,
		KEYWORD_CURRENT,
		KEYWORD_LEFT,
		KEYWORD_SHUTDOWN,
		KEYWORD_MEMBER,
		KEYWORD_SID,
		KEYWORD_DEFINE,
		KEYWORD_RESTRICT_REFERENCES,
		KEYWORD_PARTITIONS,
		KEYWORD_INTEGER,
		KEYWORD_JOIN,
		KEYWORD_HOUR,
		KEYWORD_OPERATOR,
		KEYWORD_ANALYZE,
		KEYWORD_REF,
		KEYWORD_NEW,
		KEYWORD_INCLUDING,
		KEYWORD_SEQUENCE,
		KEYWORD_LIBRARY,
		KEYWORD_REM,
		KEYWORD_EXISTS,
		KEYWORD_HAVING,
		KEYWORD_PUBLIC,
		KEYWORD_ZONE,
		KEYWORD_SIZES,
		KEYWORD_BODY,
		KEYWORD_VISIBLE,
		KEYWORD_DROP,
		KEYWORD_NORMAL,
		KEYWORD_EXCEPTION,
		KEYWORD_LEAD,
		KEYWORD_BY,
		KEYWORD_LONG,
		KEYWORD_CLOSE,
		KEYWORD_ANY,
		KEYWORD_NOBADFILE,
		KEYWORD_KEY,
		KEYWORD_EXECUTE,
		KEYWORD_PACKAGES,
		KEYWORD_OSERROR,
		KEYWORD_DOUBLE,
		KEYWORD_REPHEADER,
		KEYWORD_AND,
		KEYWORD_COMPRESS,
		KEYWORD_DELIMITED,
		KEYWORD_OVERFLOW,
		KEYWORD_AUTONOMOUS_TRANSACTION,
		KEYWORD_COLUMN,
		KEYWORD_DAY,
		KEYWORD_FAST,
		KEYWORD_COLLECT,
		KEYWORD_UPDATE,
		KEYWORD_RAW,
		KEYWORD_CONNECT,
		KEYWORD_TIMEZONE_HOUR,
		KEYWORD_NOLOGGING,
		KEYWORD_SET,
		KEYWORD_VAR,
		KEYWORD_DATA_CACHE,
		KEYWORD_DDL,
		KEYWORD_STATISTICS,
		KEYWORD_ORGANIZATION,
		KEYWORD_LAG,
		KEYWORD_INDEXTYPES,
		KEYWORD_NAME,
		KEYWORD_DISABLE,
		KEYWORD_TRIM,
		KEYWORD_TYPES,
		KEYWORD_ALL,
		KEYWORD_PARALLEL,
		KEYWORD_NODISCARDFILE,
		KEYWORD_PRECISION,
		KEYWORD_CONSTANT,
		KEYWORD_ORACLE_LOADER,
		KEYWORD_UNIFORM,
		KEYWORD_AT,
		KEYWORD_AUDIT,
		KEYWORD_AS,
		KEYWORD_CELL_FLASH_CACHE,
		KEYWORD_NEVER,
		KEYWORD_FLUSH,
		KEYWORD_CASCADE,
		KEYWORD_OFF,
		KEYWORD_JAVA,
		KEYWORD_DISABLED,
		KEYWORD_MULTISET,
		KEYWORD_ENCLOSED,
		KEYWORD_NO,
		KEYWORD_NOCACHE,
		KEYWORD_PACKAGE,
		KEYWORD_FIXED,
		KEYWORD_OF,
		KEYWORD_REWRITE,
		KEYWORD_RANK,
		KEYWORD_BYTE,
		KEYWORD_RESET,
		KEYWORD_ONLY,
		KEYWORD_ON,
		KEYWORD_REFRESH,
		KEYWORD_PURGE,
		KEYWORD_785,
		KEYWORD_LIMIT,
		KEYWORD_FETCH,
		KEYWORD_INCREMENT,
		KEYWORD_COALESCE,
		KEYWORD_FINAL,
		KEYWORD_OR,
		KEYWORD_VARRAWC,
		KEYWORD_STARTUP,
		KEYWORD_ROW,
		KEYWORD_EXCLUDING,
		KEYWORD_MANAGED,
		KEYWORD_NEWLINE,
		KEYWORD_NOORDER,
		KEYWORD_ENDIAN,
		KEYWORD_SESSION,
		KEYWORD_THEN,
		KEYWORD_MONTH,
		KEYWORD_RECORDS,
		KEYWORD_LOGOFF,
		KEYWORD_COMMENT,
		KEYWORD_CREATION,
		KEYWORD_INTERVAL,
		KEYWORD_SQLCODE,
		KEYWORD_MERGE,
		KEYWORD_EXTERNALLY,
		KEYWORD_CONSTRAINT,
		KEYWORD_PCTTHRESHOLD,
		KEYWORD_BUILD,
		KEYWORD_QUIT,
		KEYWORD_ROWNUM,
		KEYWORD_AUTOEXTEND,
		KEYWORD_ZONED,
		KEYWORD_HIERARCHY,
		KEYWORD_NULL,
		KEYWORD_BACKUP,
		KEYWORD_ROWCOUNT,
		KEYWORD_TRUE,
		KEYWORD_LDTRIM,
		KEYWORD_SQL,
		KEYWORD_DISCARDFILE,
		KEYWORD_ACCOUNT,
		KEYWORD_FORCE,
		KEYWORD_INSERT,
		KEYWORD_TIMEZONE_REGION,
		KEYWORD_LAST,
		KEYWORD_COUNT,
		KEYWORD_SECOND,
		KEYWORD_SAVE,
		KEYWORD_LOCATION,
		KEYWORD_CHAR,
		KEYWORD_SEGMENT,
		KEYWORD_WHERE,
		KEYWORD_TYPE,
		KEYWORD_AUTHID,
		KEYWORD_PRIOR,
		KEYWORD_REVOKE,
		KEYWORD_MAXEXTENTS,
		KEYWORD_PARTITION,
		KEYWORD_SPOOL,
		KEYWORD_PRIMARY,
		KEYWORD_WHEN,
		KEYWORD_VALUE,
		KEYWORD_ACTION,
		KEYWORD_NONE,
		KEYWORD_FREEPOOLS,
		KEYWORD_RETURNING,
		KEYWORD_CYCLE,
		KEYWORD_MINVALUE,
		KEYWORD_PCTUSED,
		KEYWORD_MINUS,
		KEYWORD_TRAILING,
		KEYWORD_INT,
		KEYWORD_DATAFILE,
		KEYWORD_ERROR_CODE,
		KEYWORD_ROWS,
		KEYWORD_PERMANENT,
		KEYWORD_INTERSECT,
		KEYWORD_DATAFILES,
		KEYWORD_NOMONITORING,
		KEYWORD_SERVEROUTPUT,
		KEYWORD_READSIZE,
		KEYWORD_NOSORT,
		KEYWORD_ROLLBACK,
		KEYWORD_FROM,
		KEYWORD_ADD,
		KEYWORD_ONLINE,
		KEYWORD_WHILE,
		KEYWORD_ID,
		KEYWORD_REAL,
		KEYWORD_MATERIALIZED,
		KEYWORD_EXTENT,
		KEYWORD_IF,
		KEYWORD_RETENTION,
		KEYWORD_READ,
		KEYWORD_COMPUTE,
		KEYWORD_LESS,
		KEYWORD_BETWEEN,
		KEYWORD_IS,
		KEYWORD_REUSE,
		KEYWORD_RTRIM,
		KEYWORD_ROWTYPE,
		KEYWORD_INTO,
		KEYWORD_RESOURCE,
		KEYWORD_MODIFY,
		KEYWORD_INTERFACE,
		KEYWORD_CONCAT,
		KEYWORD_IN,
		KEYWORD_DATABASE,
		KEYWORD_INDEXTYPE,
		KEYWORD_SYSTIMESTAMP,
		KEYWORD_LOCAL,
		KEYWORD_FOUND,
		KEYWORD_VARRAW,
		KEYWORD_MATCHED,
		KEYWORD_VARRAY,
		KEYWORD_NULLS,
		KEYWORD_OPTIMAL,
		KEYWORD_VALIDATE,
		KEYWORD_ASSOCIATE,
		KEYWORD_SCHEMA,
		KEYWORD_BUFFER_POOL,
		KEYWORD_FREELISTS,
		KEYWORD_REDUCED,
		KEYWORD_BOOLEAN,
		KEYWORD_YEAR,
		KEYWORD_PIPELINED,
		KEYWORD_OPTION,
		KEYWORD_NVARCHAR,
		KEYWORD_CONTENTS,
		KEYWORD_WHITESPACE,
		KEYWORD_UNDER,
		KEYWORD_CONSTRAINTS,
		KEYWORD_CHARSET,
		KEYWORD_LOG,
		KEYWORD_OPTIONALLY,
		KEYWORD_DECLARE,
		KEYWORD_PRAGMA,
		KEYWORD_READS,
		KEYWORD_DBTIMEZONE,
		KEYWORD_SYSTEM,
		KEYWORD_DECODE,
		KEYWORD_LEADING,
		KEYWORD_703,
		KEYWORD_PLS_INTEGER,
		KEYWORD_LOAD,
		KEYWORD_PCTINCREASE,
		KEYWORD_EXCEPTION_INIT,
		KEYWORD_DESC,
		KEYWORD_SORT,
		KEYWORD_MINIMUM,
		KEYWORD_ORACLE_DATAPUMP,
		KEYWORD_NEXT,
		KEYWORD_CLOB,
		KEYWORD_DATA,
		KEYWORD_REPFOOTER,
		KEYWORD_UNLIMITED,
		KEYWORD_DEFERRED,
		KEYWORD_ORACLE_NUMBER,
		KEYWORD_DATE,
		KEYWORD_BFILE,
		KEYWORD_TIMESTAMP,
		KEYWORD_LOB,
		KEYWORD_ELSIF,
		KEYWORD_BUILTIN,
		KEYWORD_WHENEVER,
		KEYWORD_PARALLEL_ENABLE,
		KEYWORD_PROCEDURE,
		KEYWORD_VARCHAR,
		KEYWORD_REVERSE,
		KEYWORD_BINARY_INTEGER,
		KEYWORD_FLASH_CACHE,
		KEYWORD_CURRVAL,
		KEYWORD_VARCHAR2,
		KEYWORD_BULK,
		KEYWORD_ALTER,
		KEYWORD_FIELD,
		KEYWORD_REPLACE,
		KEYWORD_ORACLE_DATE,
		KEYWORD_NOPARALLEL,
		KEYWORD_ADMIN,
		KEYWORD_STRING,
		KEYWORD_UNUSABLE,
		KEYWORD_PROMPT,
		KEYWORD_VARIABLE,
		KEYWORD_SHARED_POOL,
		KEYWORD_KEEP,
		KEYWORD_TO,
		KEYWORD_COL,
		KEYWORD_BOTH,
		KEYWORD_INNER,
		KEYWORD_SYNONYM,
		KEYWORD_BECOME,
		KEYWORD_NOLOGFILE,
		KEYWORD_IDENTIFIED,
		KEYWORD_AFTER,
		KEYWORD_TIMEZONE,
		KEYWORD_VALUES,
		KEYWORD_NCLOB,
		KEYWORD_COMMIT,
		KEYWORD_ENCRYPT,
		KEYWORD_SESSIONTIMEZONE,
		KEYWORD_LDRTRIM,
		KEYWORD_PARAMETERS,
		KEYWORD_FIELDS,
		KEYWORD_DEMAND,
		KEYWORD_ENABLED,
		KEYWORD_INDEX,
		KEYWORD_STA,
		KEYWORD_BITMAP,
		KEYWORD_TIMEZONE_MINUTE,
		KEYWORD_SELECT,
		KEYWORD_MAXVALUE,
		KEYWORD_INDEXES,
		KEYWORD_COST,
		KEYWORD_SIZE,
		KEYWORD_CAST,
		KEYWORD_THAN,
		KEYWORD_EXEC,
		KEYWORD_CASE,
		KEYWORD_FOREIGN,
		KEYWORD_NATURAL,
		KEYWORD_FREELIST,
		KEYWORD_TIMEZONE_ABBR,
		KEYWORD_MAXSIZE,
		KEYWORD_TEMPFILE,
		KEYWORD_COMPLETE,
		KEYWORD_COMPATIBLE,
		KEYWORD_DATE_FORMAT,
		KEYWORD_REBUILD,
		KEYWORD_NOVISIBLE,
		KEYWORD_BULK_EXCEPTIONS,
		KEYWORD_LOGFILE,
		KEYWORD_LOBFILE,
		KEYWORD_PROFILE,
		KEYWORD_FUNCTIONS,
		KEYWORD_FILESYSTEM_LIKE_LOGGING,
		KEYWORD_NOCOPY,
		KEYWORD_IMMEDIATE,
		KEYWORD_COUNTED,
		KEYWORD_OPERATIONS,
		KEYWORD_LATEST,
		KEYWORD_SMALLINT,
		KEYWORD_SELECTIVITY,
		KEYWORD_OUT,
		KEYWORD_SMALLFILE,
		KEYWORD_AGGREGATE,
		KEYWORD_CHUNK,
		KEYWORD_CURSOR,
		KEYWORD_WRAPPED,
		KEYWORD_NUMERIC,
		KEYWORD_FOR,
		KEYWORD_DISTINCT,
		KEYWORD_OPEN,
		KEYWORD_ARE,
		KEYWORD_INITIAL,
		KEYWORD_NOAUDIT,
		KEYWORD_MAXTRANS,
		KEYWORD_ANY_CS,
		KEYWORD_FALSE,
		KEYWORD_COMPRESSION,
		KEYWORD_GROUPS,
		KEYWORD_UNLOCK,
		KEYWORD_TABLE,
		KEYWORD_LIKE,
		KEYWORD_CREATE,
		KEYWORD_WITHOUT,
		KEYWORD_EXIT,
		KEYWORD_NOT,
		KEYWORD_RECORD,
		KEYWORD_RECYCLE,
		KEYWORD_ASC,
		KEYWORD_START,
		KEYWORD_TRUNCATE,
		KEYWORD_INDICES,
		KEYWORD_LANGUAGE,
		KEYWORD_POSITION,
		KEYWORD_NOTRIM,
		KEYWORD_BYTES,
		KEYWORD_RANGE,
		KEYWORD_INITRANS,
		KEYWORD_QUERY,
		KEYWORD_LINK,
		KEYWORD_GOTO,
		KEYWORD_NOCHECK,
		KEYWORD_OFFLINE,
		KEYWORD_ESCAPE,
		KEYWORD_VERSION,
		KEYWORD_MODE,
		KEYWORD_CHARACTER,
		KEYWORD_BADFILE,
		KEYWORD_EXCEPTIONS,
		KEYWORD_DEF,
		KEYWORD_UNION,
		KEYWORD_DELETE,
		KEYWORD_BULK_ROWCOUNT,
		KEYWORD_DETERMINISTIC,
		KEYWORD_END,
		KEYWORD_EXPIRE,
		KEYWORD_TRIGGER,
		KEYWORD_ISOPEN,
		KEYWORD_RELY,
		KEYWORD_CACHE,
		KEYWORD_RETURN,
		KEYWORD_DICTIONARY,
		KEYWORD_BIGFILE,
		KEYWORD_UNSIGNED,
		KEYWORD_ACCESS,
		KEYWORD_TRANSFORMS,
		KEYWORD_CURRENT_TIMESTAMP,
		KEYWORD_DEGREE,
		KEYWORD_DIRECTORY,
		KEYWORD_TERMINATED,
		KEYWORD_OLD,
		KEYWORD_MINEXTENTS,
		KEYWORD_GRANT,
		KEYWORD_TRANSACTION,
		KEYWORD_UNDO,
		KEYWORD_NVARCHAR2,
		KEYWORD_RENAME,
		KEYWORD_ENABLE,
		KEYWORD_MASK,
		KEYWORD_LITTLE,
		KEYWORD_SHOW,
		KEYWORD_SKIP,
		KEYWORD_PASSWORD,
		KEYWORD_PRESERVE,
		KEYWORD_FUNCTION,
		KEYWORD_HEAP,
		KEYWORD_TABLESPACE,
		KEYWORD_ENCRYPTION,
		KEYWORD_POSITIVE,
		KEYWORD_WORK,
		KEYWORD_LOCATOR,
		KEYWORD_HASH,
		KEYWORD_VARCHARC,
		KEYWORD_GLOBAL,
		KEYWORD_MARK,
		KEYWORD_LOGGING,
		KEYWORD_FORALL,
		KEYWORD_RESTRICT,
		KEYWORD_DEFAULT,
		KEYWORD_DENSE_RANK,
		KEYWORD_MANAGEMENT,
		KEYWORD_CHARACTERS,
		KEYWORD_REJECT,
		KEYWORD_TEMPORARY,
		KEYWORD_SERVERERROR,
		KEYWORD_OBJECT,
		KEYWORD_MINUTE,
		KEYWORD_SHARE,
		KEYWORD_QUOTA,
		KEYWORD_ORDER,
		KEYWORD_FULL,
		KEYWORD_LTRIM,
		KEYWORD_NOTFOUND,
		KEYWORD_WITH,
		KEYWORD_CHECK,
		KEYWORD_LOCK,
		KEYWORD_THE,
		KEYWORD_SQLERROR,
		KEYWORD_DECIMAL,
		KEYWORD_BEGIN,
		KEYWORD_BLOB,
		KEYWORD_NOCYCLE,
		KEYWORD_OUTER,
		KEYWORD_CONTINUE,
		KEYWORD_INSTEAD,
		KEYWORD_GUARANTEE,
		KEYWORD_CLUSTER,
		KEYWORD_GROUP,
		KEYWORD_PREBUILT,
		KEYWORD_FIRST,
		KEYWORD_USER,
		KEYWORD_ROWID,
		KEYWORD_STORAGE,
		new PlSqlTokenType(9999, "$$$END_OF_ARRAY_$$$")
		);
}
