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
		IElementType KEYWORD_USING = new PlSqlTokenType(460, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(730, "ERROR_INDEX");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(609, "REFERENCING");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(618, "NESTED");
		IElementType KEYWORD_STORE = new PlSqlTokenType(619, "STORE");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(717, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(661, "EXTERNAL");
		IElementType KEYWORD_PRIVILEGES = new PlSqlTokenType(833, "PRIVILEGES");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(868, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(640, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(761, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(929, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(919, "MISSING");
		IElementType KEYWORD_OVER = new PlSqlTokenType(818, "OVER");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(474, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(748, "TIME");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(898, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(470, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(439, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(593, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(854, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(735, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(500, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(647, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(880, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(739, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(812, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(639, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(599, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(478, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(658, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(720, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(608, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(425, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(900, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(777, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(816, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(477, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(637, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(786, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(878, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(589, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(803, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(553, "NOGUARANTEE");
		IElementType KEYWORD_DEBUG = new PlSqlTokenType(835, "DEBUG");
		IElementType KEYWORD_GLOBALLY = new PlSqlTokenType(527, "GLOBALLY");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(663, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(867, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(917, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(626, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(565, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(721, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(795, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(853, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(591, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(801, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(850, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(497, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(714, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(657, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(758, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(862, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(814, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(437, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(595, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(690, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(611, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(567, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(432, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(441, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(499, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(796, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(863, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(435, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(749, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(906, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(420, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(687, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(422, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(561, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(776, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(809, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(525, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(481, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(874, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(815, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(910, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(473, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(487, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(450, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(490, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(760, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(507, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(569, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(635, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(896, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(649, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(736, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(448, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(752, "DAY");
		IElementType KEYWORD_FAST = new PlSqlTokenType(706, "FAST");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(845, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(607, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(745, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(587, "CONNECT");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(823, "TIMEZONE_HOUR");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(557, "NOLOGGING");
		IElementType KEYWORD_SET = new PlSqlTokenType(480, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(483, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(915, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(598, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(446, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(659, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(808, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(453, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(785, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(467, "DISABLE");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(806, "TRIM");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(451, "TYPES");
		IElementType KEYWORD_RESULT_CACHE = new PlSqlTokenType(781, "RESULT_CACHE");
		IElementType KEYWORD_ALL = new PlSqlTokenType(636, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(645, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(912, "NODISCARDFILE");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(698, "PRECISION");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(719, "CONSTANT");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(881, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(549, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(794, "AT");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(596, "AUDIT");
		IElementType KEYWORD_AS = new PlSqlTokenType(522, "AS");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(678, "CELL_FLASH_CACHE");
		IElementType KEYWORD_NEVER = new PlSqlTokenType(703, "NEVER");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(847, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(426, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(506, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(784, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(892, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(807, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(925, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(558, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(582, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(419, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(895, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(604, "OF");
		IElementType KEYWORD_REWRITE = new PlSqlTokenType(702, "REWRITE");
		IElementType KEYWORD_RANK = new PlSqlTokenType(810, "RANK");
		IElementType KEYWORD_ERRORS = new PlSqlTokenType(869, "ERRORS");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(744, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(849, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(564, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(464, "ON");
		IElementType KEYWORD_REFRESH = new PlSqlTokenType(704, "REFRESH");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(424, "PURGE");
		IElementType KEYWORD_793 = new PlSqlTokenType(793, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(665, "LIMIT");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(875, "FETCH");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(583, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(575, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(521, "FINAL");
		IElementType KEYWORD_OR = new PlSqlTokenType(514, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(942, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(590, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(469, "ROW");
		IElementType KEYWORD_EXCLUDING = new PlSqlTokenType(699, "EXCLUDING");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(455, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(897, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(585, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(902, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(839, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(734, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(751, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(894, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(594, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(463, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(630, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(652, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(802, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(870, "MERGE");
		IElementType KEYWORD_EXTERNALLY = new PlSqlTokenType(526, "EXTERNALLY");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(471, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(633, "PCTTHRESHOLD");
		IElementType KEYWORD_BUILD = new PlSqlTokenType(705, "BUILD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(501, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(859, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(554, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(936, "ZONED");
		IElementType KEYWORD_HIERARCHY = new PlSqlTokenType(834, "HIERARCHY");
		IElementType KEYWORD_NULL = new PlSqlTokenType(461, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(574, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(726, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(791, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(948, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(723, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(913, "DISCARDFILE");
		IElementType KEYWORD_ACCOUNT = new PlSqlTokenType(534, "ACCOUNT");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(431, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(606, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(825, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(866, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(732, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(753, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(787, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(946, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(743, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(629, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(857, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(433, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(711, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(737, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(601, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(668, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(650, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(502, "SPOOL");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(472, "PRIMARY");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(612, "WHEN");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(622, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(682, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(495, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(627, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(872, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(579, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(578, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(641, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(843, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(828, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(757, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(541, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(731, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(617, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(566, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(842, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(570, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(648, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(508, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(914, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(685, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(494, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(822, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(571, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(559, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(722, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(755, "REAL");
		IElementType KEYWORD_MATERIALIZED = new PlSqlTokenType(516, "MATERIALIZED");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(546, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(831, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(551, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(563, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(644, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(654, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(800, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(465, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(545, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(932, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(692, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(846, "INTO");
		IElementType KEYWORD_RESOURCE = new PlSqlTokenType(837, "RESOURCE");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(683, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(715, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(923, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(628, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(442, "DATABASE");
		IElementType KEYWORD_INDEXTYPE = new PlSqlTokenType(838, "INDEXTYPE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(479, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(548, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(724, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(940, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(871, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(693, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(864, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(673, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(434, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(445, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(602, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(674, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(670, "FREELISTS");
		IElementType KEYWORD_REDUCED = new PlSqlTokenType(697, "REDUCED");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(746, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(750, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(778, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(694, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(765, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(568, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(927, "WHITESPACE");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(520, "UNDER");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(427, "CONSTRAINTS");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(775, "CHARSET");
		IElementType KEYWORD_LOG = new PlSqlTokenType(517, "LOG");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(928, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(510, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(713, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(625, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(820, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(454, "SYSTEM");
		IElementType KEYWORD_DECODE = new PlSqlTokenType(805, "DECODE");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(827, "LEADING");
		IElementType KEYWORD_709 = new PlSqlTokenType(709, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(759, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(909, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(669, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(718, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(615, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(684, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(576, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(882, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(542, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(769, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(899, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(505, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(530, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(631, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(938, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(747, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(771, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(710, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(623, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(832, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(716, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(488, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(779, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(429, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(763, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(686, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(740, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(677, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(817, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(764, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(844, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(421, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(920, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(515, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(937, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(646, "NOPARALLEL");
		IElementType KEYWORD_ADMIN = new PlSqlTokenType(836, "ADMIN");
		IElementType KEYWORD_STRING = new PlSqlTokenType(905, "STRING");
		IElementType KEYWORD_UNUSABLE = new PlSqlTokenType(852, "UNUSABLE");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(498, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(484, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(848, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(675, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(572, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(485, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(829, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(855, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(436, "SYNONYM");
		IElementType KEYWORD_BECOME = new PlSqlTokenType(840, "BECOME");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(885, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(524, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(588, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(944, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(653, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(770, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(493, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(679, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(819, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(933, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(884, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(918, "FIELDS");
		IElementType KEYWORD_DEMAND = new PlSqlTokenType(708, "DEMAND");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(891, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(430, "INDEX");
		IElementType KEYWORD_STA = new PlSqlTokenType(503, "STA");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(613, "BITMAP");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(824, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(797, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(577, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(452, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(458, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(544, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(804, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(655, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(486, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(733, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(680, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(741, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(671, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(826, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(543, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(539, "TEMPFILE");
		IElementType KEYWORD_COMPLETE = new PlSqlTokenType(707, "COMPLETE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(888, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(943, "DATE_FORMAT");
		IElementType KEYWORD_REBUILD = new PlSqlTokenType(851, "REBUILD");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(688, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(729, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(886, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(924, "LOBFILE");
		IElementType KEYWORD_PROFILE = new PlSqlTokenType(531, "PROFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(449, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(634, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(773, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(562, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(939, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(638, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(889, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(754, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(459, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(772, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(538, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(947, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(624, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(691, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(712, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(756, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(586, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(821, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(879, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(921, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(666, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(597, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(643, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(774, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(792, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(890, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(672, "GROUPS");
		IElementType KEYWORD_UNLOCK = new PlSqlTokenType(536, "UNLOCK");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(423, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(798, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(513, "CREATE");
		IElementType KEYWORD_WITHOUT = new PlSqlTokenType(696, "WITHOUT");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(491, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(466, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(689, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(676, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(614, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(504, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(462, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(790, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(783, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(934, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(930, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(907, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(651, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(642, "INITRANS");
		IElementType KEYWORD_QUERY = new PlSqlTokenType(701, "QUERY");
		IElementType KEYWORD_LINK = new PlSqlTokenType(443, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(738, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(904, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(560, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(799, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(887, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(876, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(767, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(911, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(788, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(496, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(841, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(605, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(728, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(780, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(573, "END");
		IElementType KEYWORD_EXPIRE = new PlSqlTokenType(533, "EXPIRE");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(444, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(727, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(475, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(581, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(620, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(550, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(537, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(935, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(883, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(922, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(858, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(662, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(440, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(926, "TERMINATED");
		IElementType KEYWORD_RELIES_ON = new PlSqlTokenType(782, "RELIES_ON");
		IElementType KEYWORD_OLD = new PlSqlTokenType(610, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(667, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(600, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(873, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(540, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(766, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(512, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(468, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(945, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(901, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(482, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(916, "SKIP");
		IElementType KEYWORD_PASSWORD = new PlSqlTokenType(532, "PASSWORD");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(616, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(428, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(660, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(528, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(893, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(742, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(830, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(621, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(656, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(941, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(518, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(903, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(556, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(789, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(681, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(457, "DEFAULT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(811, "DENSE_RANK");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(547, "MANAGEMENT");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(908, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(664, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(519, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(592, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(523, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(813, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(877, "SHARE");
		IElementType KEYWORD_QUOTA = new PlSqlTokenType(529, "QUOTA");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(584, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(856, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(931, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(725, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(447, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(476, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(535, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(860, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(489, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(762, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(509, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(768, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(580, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(861, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(492, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(603, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(552, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(632, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(555, "GROUP");
		IElementType KEYWORD_PREBUILT = new PlSqlTokenType(695, "PREBUILT");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(865, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(438, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(700, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(456, "STORAGE");

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
		KEYWORD_RESULT_CACHE,
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
		KEYWORD_ERRORS,
		KEYWORD_BYTE,
		KEYWORD_RESET,
		KEYWORD_ONLY,
		KEYWORD_ON,
		KEYWORD_REFRESH,
		KEYWORD_PURGE,
		KEYWORD_793,
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
		KEYWORD_709,
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
		KEYWORD_RELIES_ON,
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
