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
		IElementType KEYWORD_USING = new PlSqlTokenType(458, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(728, "ERROR_INDEX");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(607, "REFERENCING");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(616, "NESTED");
		IElementType KEYWORD_STORE = new PlSqlTokenType(617, "STORE");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(715, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(659, "EXTERNAL");
		IElementType KEYWORD_PRIVILEGES = new PlSqlTokenType(831, "PRIVILEGES");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(866, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(638, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(759, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(926, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(916, "MISSING");
		IElementType KEYWORD_OVER = new PlSqlTokenType(816, "OVER");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(472, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(746, "TIME");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(895, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(468, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(437, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(591, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(852, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(733, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(498, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(645, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(877, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(737, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(810, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(637, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(597, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(476, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(656, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(718, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(606, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(423, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(897, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(775, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(814, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(475, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(635, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(784, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(875, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(587, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(801, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(551, "NOGUARANTEE");
		IElementType KEYWORD_DEBUG = new PlSqlTokenType(833, "DEBUG");
		IElementType KEYWORD_GLOBALLY = new PlSqlTokenType(525, "GLOBALLY");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(661, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(865, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(914, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(624, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(563, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(719, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(793, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(851, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(589, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(799, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(848, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(495, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(712, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(655, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(756, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(860, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(812, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(435, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(593, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(688, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(609, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(565, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(430, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(439, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(497, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(794, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(861, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(433, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(747, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(903, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(418, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(685, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(420, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(559, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(774, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(807, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(523, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(479, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(871, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(813, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(907, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(471, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(485, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(448, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(488, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(758, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(505, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(567, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(633, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(893, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(647, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(734, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(446, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(750, "DAY");
		IElementType KEYWORD_FAST = new PlSqlTokenType(704, "FAST");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(843, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(605, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(743, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(585, "CONNECT");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(821, "TIMEZONE_HOUR");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(555, "NOLOGGING");
		IElementType KEYWORD_SET = new PlSqlTokenType(478, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(481, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(912, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(596, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(444, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(657, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(806, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(451, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(783, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(465, "DISABLE");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(804, "TRIM");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(449, "TYPES");
		IElementType KEYWORD_RESULT_CACHE = new PlSqlTokenType(779, "RESULT_CACHE");
		IElementType KEYWORD_ALL = new PlSqlTokenType(634, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(643, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(909, "NODISCARDFILE");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(696, "PRECISION");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(717, "CONSTANT");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(878, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(547, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(792, "AT");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(594, "AUDIT");
		IElementType KEYWORD_AS = new PlSqlTokenType(520, "AS");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(676, "CELL_FLASH_CACHE");
		IElementType KEYWORD_NEVER = new PlSqlTokenType(701, "NEVER");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(845, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(424, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(504, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(782, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(889, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(805, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(922, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(556, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(580, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(417, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(892, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(602, "OF");
		IElementType KEYWORD_REWRITE = new PlSqlTokenType(700, "REWRITE");
		IElementType KEYWORD_RANK = new PlSqlTokenType(808, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(742, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(847, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(562, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(462, "ON");
		IElementType KEYWORD_REFRESH = new PlSqlTokenType(702, "REFRESH");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(422, "PURGE");
		IElementType KEYWORD_791 = new PlSqlTokenType(791, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(663, "LIMIT");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(872, "FETCH");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(581, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(573, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(519, "FINAL");
		IElementType KEYWORD_OR = new PlSqlTokenType(512, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(939, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(588, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(467, "ROW");
		IElementType KEYWORD_EXCLUDING = new PlSqlTokenType(697, "EXCLUDING");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(453, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(894, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(583, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(899, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(837, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(732, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(749, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(891, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(592, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(461, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(628, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(650, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(800, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(867, "MERGE");
		IElementType KEYWORD_EXTERNALLY = new PlSqlTokenType(524, "EXTERNALLY");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(469, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(631, "PCTTHRESHOLD");
		IElementType KEYWORD_BUILD = new PlSqlTokenType(703, "BUILD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(499, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(857, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(552, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(933, "ZONED");
		IElementType KEYWORD_HIERARCHY = new PlSqlTokenType(832, "HIERARCHY");
		IElementType KEYWORD_NULL = new PlSqlTokenType(459, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(572, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(724, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(789, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(945, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(721, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(910, "DISCARDFILE");
		IElementType KEYWORD_ACCOUNT = new PlSqlTokenType(532, "ACCOUNT");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(429, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(604, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(823, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(864, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(730, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(751, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(785, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(943, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(741, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(627, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(855, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(431, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(709, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(735, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(599, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(666, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(648, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(500, "SPOOL");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(470, "PRIMARY");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(610, "WHEN");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(620, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(680, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(493, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(625, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(869, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(577, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(576, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(639, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(841, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(826, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(755, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(539, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(729, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(615, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(564, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(840, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(568, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(646, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(506, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(911, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(683, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(492, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(820, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(569, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(557, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(720, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(753, "REAL");
		IElementType KEYWORD_MATERIALIZED = new PlSqlTokenType(514, "MATERIALIZED");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(544, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(829, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(549, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(561, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(642, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(652, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(798, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(463, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(543, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(929, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(690, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(844, "INTO");
		IElementType KEYWORD_RESOURCE = new PlSqlTokenType(835, "RESOURCE");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(681, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(713, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(920, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(626, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(440, "DATABASE");
		IElementType KEYWORD_INDEXTYPE = new PlSqlTokenType(836, "INDEXTYPE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(477, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(546, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(722, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(937, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(868, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(691, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(862, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(671, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(432, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(443, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(600, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(672, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(668, "FREELISTS");
		IElementType KEYWORD_REDUCED = new PlSqlTokenType(695, "REDUCED");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(744, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(748, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(776, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(692, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(763, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(566, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(924, "WHITESPACE");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(518, "UNDER");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(425, "CONSTRAINTS");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(773, "CHARSET");
		IElementType KEYWORD_LOG = new PlSqlTokenType(515, "LOG");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(925, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(508, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(711, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(623, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(818, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(452, "SYSTEM");
		IElementType KEYWORD_DECODE = new PlSqlTokenType(803, "DECODE");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(825, "LEADING");
		IElementType KEYWORD_707 = new PlSqlTokenType(707, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(757, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(906, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(667, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(716, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(613, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(682, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(574, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(879, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(540, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(767, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(896, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(503, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(528, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(629, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(935, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(745, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(769, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(708, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(621, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(830, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(714, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(486, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(777, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(427, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(761, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(684, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(738, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(675, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(815, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(762, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(842, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(419, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(917, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(513, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(934, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(644, "NOPARALLEL");
		IElementType KEYWORD_ADMIN = new PlSqlTokenType(834, "ADMIN");
		IElementType KEYWORD_STRING = new PlSqlTokenType(902, "STRING");
		IElementType KEYWORD_UNUSABLE = new PlSqlTokenType(850, "UNUSABLE");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(496, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(482, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(846, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(673, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(570, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(483, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(827, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(853, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(434, "SYNONYM");
		IElementType KEYWORD_BECOME = new PlSqlTokenType(838, "BECOME");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(882, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(522, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(586, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(941, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(651, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(768, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(491, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(677, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(817, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(930, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(881, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(915, "FIELDS");
		IElementType KEYWORD_DEMAND = new PlSqlTokenType(706, "DEMAND");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(888, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(428, "INDEX");
		IElementType KEYWORD_STA = new PlSqlTokenType(501, "STA");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(611, "BITMAP");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(822, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(795, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(575, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(450, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(456, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(542, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(802, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(653, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(484, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(731, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(678, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(739, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(669, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(824, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(541, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(537, "TEMPFILE");
		IElementType KEYWORD_COMPLETE = new PlSqlTokenType(705, "COMPLETE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(885, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(940, "DATE_FORMAT");
		IElementType KEYWORD_REBUILD = new PlSqlTokenType(849, "REBUILD");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(686, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(727, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(883, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(921, "LOBFILE");
		IElementType KEYWORD_PROFILE = new PlSqlTokenType(529, "PROFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(447, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(632, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(771, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(560, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(936, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(636, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(886, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(752, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(457, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(770, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(536, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(944, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(622, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(689, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(710, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(754, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(584, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(819, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(876, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(918, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(664, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(595, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(641, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(772, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(790, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(887, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(670, "GROUPS");
		IElementType KEYWORD_UNLOCK = new PlSqlTokenType(534, "UNLOCK");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(421, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(796, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(511, "CREATE");
		IElementType KEYWORD_WITHOUT = new PlSqlTokenType(694, "WITHOUT");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(489, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(464, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(687, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(674, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(612, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(502, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(460, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(788, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(781, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(931, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(927, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(904, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(649, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(640, "INITRANS");
		IElementType KEYWORD_QUERY = new PlSqlTokenType(699, "QUERY");
		IElementType KEYWORD_LINK = new PlSqlTokenType(441, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(736, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(901, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(558, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(797, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(884, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(873, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(765, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(908, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(786, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(494, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(839, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(603, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(726, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(778, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(571, "END");
		IElementType KEYWORD_EXPIRE = new PlSqlTokenType(531, "EXPIRE");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(442, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(725, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(473, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(579, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(618, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(548, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(535, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(932, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(880, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(919, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(856, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(660, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(438, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(923, "TERMINATED");
		IElementType KEYWORD_RELIES_ON = new PlSqlTokenType(780, "RELIES_ON");
		IElementType KEYWORD_OLD = new PlSqlTokenType(608, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(665, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(598, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(870, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(538, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(764, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(510, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(466, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(942, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(898, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(480, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(913, "SKIP");
		IElementType KEYWORD_PASSWORD = new PlSqlTokenType(530, "PASSWORD");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(614, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(426, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(658, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(526, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(890, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(740, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(828, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(619, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(654, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(938, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(516, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(900, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(554, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(787, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(679, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(455, "DEFAULT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(809, "DENSE_RANK");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(545, "MANAGEMENT");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(905, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(662, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(517, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(590, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(521, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(811, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(874, "SHARE");
		IElementType KEYWORD_QUOTA = new PlSqlTokenType(527, "QUOTA");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(582, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(854, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(928, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(723, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(445, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(474, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(533, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(858, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(487, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(760, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(507, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(766, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(578, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(859, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(490, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(601, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(550, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(630, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(553, "GROUP");
		IElementType KEYWORD_PREBUILT = new PlSqlTokenType(693, "PREBUILT");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(863, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(436, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(698, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(454, "STORAGE");

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
		KEYWORD_BYTE,
		KEYWORD_RESET,
		KEYWORD_ONLY,
		KEYWORD_ON,
		KEYWORD_REFRESH,
		KEYWORD_PURGE,
		KEYWORD_791,
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
		KEYWORD_707,
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
