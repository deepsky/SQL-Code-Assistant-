package com.deepsky.integration.lexer.generated;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.deepsky.integration.plsql.PlSqlTokenType;

public interface PlSqlBaseTokenTypes {

		IElementType DOLLAR = new PlSqlTokenType(42, "DOLLAR");
		IElementType LT = new PlSqlTokenType(9, "LT");
		IElementType QUOTED_STR = new PlSqlTokenType(23, "QUOTED_STR");
		IElementType STORAGE_SIZE = new PlSqlTokenType(8, "STORAGE_SIZE");
		IElementType DOUBLEDOT = new PlSqlTokenType(39, "DOUBLEDOT");
		IElementType ANY_CHARACTER = new PlSqlTokenType(21, "ANY_CHARACTER");
		IElementType BAD_CHARACTER = new PlSqlTokenType(5, "BAD_CHARACTER");
		IElementType ASTERISK = new PlSqlTokenType(30, "ASTERISK");
		IElementType ML_COMMENT = new PlSqlTokenType(53, "ML_COMMENT");
		IElementType THEN_COND_CMPL = new PlSqlTokenType(55, "THEN_COND_CMPL");
		IElementType COMMA = new PlSqlTokenType(29, "COMMA");
		IElementType IDENTIFIER = new PlSqlTokenType(22, "IDENTIFIER");
		IElementType BAD_QUOTED_STRING = new PlSqlTokenType(19, "BAD_QUOTED_STRING");
		IElementType ELSE_COND_CMPL = new PlSqlTokenType(56, "ELSE_COND_CMPL");
		IElementType CUSTOM_TOKEN = new PlSqlTokenType(13, "CUSTOM_TOKEN");
		IElementType AT_PREFIXED = new PlSqlTokenType(25, "AT_PREFIXED");
		IElementType PLUS = new PlSqlTokenType(33, "PLUS");
		IElementType TABLE_NAME_SPEC = new PlSqlTokenType(16, "TABLE_NAME_SPEC");
		IElementType CLOSE_PAREN = new PlSqlTokenType(32, "CLOSE_PAREN");
		IElementType END_COND_CMPL = new PlSqlTokenType(57, "END_COND_CMPL");
		IElementType PASS_BY_NAME = new PlSqlTokenType(45, "PASS_BY_NAME");
		IElementType BAD_ML_COMMENT = new PlSqlTokenType(4, "BAD_ML_COMMENT");
		IElementType EQ = new PlSqlTokenType(37, "EQ");
		IElementType DOT = new PlSqlTokenType(28, "DOT");
		IElementType ASSIGNMENT_EQ = new PlSqlTokenType(44, "ASSIGNMENT_EQ");
		IElementType ERROR_COND_CMPL = new PlSqlTokenType(58, "ERROR_COND_CMPL");
		IElementType DIVIDE = new PlSqlTokenType(35, "DIVIDE");
		IElementType DOUBLE_QUOTED_STRING = new PlSqlTokenType(24, "DOUBLE_QUOTED_STRING");
		IElementType QUOTED_STR_START = new PlSqlTokenType(17, "QUOTED_STR_START");
		IElementType START_LABEL = new PlSqlTokenType(41, "START_LABEL");
		IElementType GE = new PlSqlTokenType(11, "GE");
		IElementType CONCAT = new PlSqlTokenType(40, "CONCAT");
		IElementType BAD_NUMBER_LITERAL = new PlSqlTokenType(7, "BAD_NUMBER_LITERAL");
		IElementType N = new PlSqlTokenType(49, "N");
		IElementType BAD_CHAR_LITERAL = new PlSqlTokenType(6, "BAD_CHAR_LITERAL");
		IElementType NUMBER = new PlSqlTokenType(48, "NUMBER");
		IElementType COLON_OLD = new PlSqlTokenType(15, "COLON_OLD");
		IElementType OPEN_PAREN = new PlSqlTokenType(31, "OPEN_PAREN");
		IElementType MINUS = new PlSqlTokenType(34, "MINUS");
		IElementType SEMI = new PlSqlTokenType(26, "SEMI");
		IElementType PERCENTAGE = new PlSqlTokenType(38, "PERCENTAGE");
		IElementType NOT_EQ = new PlSqlTokenType(12, "NOT_EQ");
		IElementType VERTBAR = new PlSqlTokenType(46, "VERTBAR");
		IElementType COLON = new PlSqlTokenType(27, "COLON");
		IElementType PLSQL_ENV_VAR = new PlSqlTokenType(20, "PLSQL_ENV_VAR");
		IElementType WS = new PlSqlTokenType(50, "WS");
		IElementType END_LABEL = new PlSqlTokenType(43, "END_LABEL");
		IElementType SL_COMMENT = new PlSqlTokenType(52, "SL_COMMENT");
		IElementType QUOTED_STR_END = new PlSqlTokenType(18, "QUOTED_STR_END");
		IElementType GT = new PlSqlTokenType(47, "GT");
		IElementType IF_COND_CMPL = new PlSqlTokenType(54, "IF_COND_CMPL");
		IElementType COLON_NEW = new PlSqlTokenType(14, "COLON_NEW");
		IElementType LE = new PlSqlTokenType(10, "LE");
		IElementType BACKSLASH = new PlSqlTokenType(36, "BACKSLASH");
		IElementType LF = new PlSqlTokenType(51, "LF");
		IElementType KEYWORD_USING = new PlSqlTokenType(452, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(723, "ERROR_INDEX");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(601, "REFERENCING");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(610, "NESTED");
		IElementType KEYWORD_STORE = new PlSqlTokenType(611, "STORE");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(710, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(653, "EXTERNAL");
		IElementType KEYWORD_PRIVILEGES = new PlSqlTokenType(824, "PRIVILEGES");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(859, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(632, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(754, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(919, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(909, "MISSING");
		IElementType KEYWORD_OVER = new PlSqlTokenType(809, "OVER");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(466, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(741, "TIME");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(888, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(462, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(431, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(585, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(845, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(728, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(492, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(639, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(870, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(732, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(803, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(631, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(591, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(470, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(650, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(713, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(600, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(417, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(890, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(770, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(807, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(469, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(629, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(777, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(868, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(581, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(794, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(545, "NOGUARANTEE");
		IElementType KEYWORD_DEBUG = new PlSqlTokenType(826, "DEBUG");
		IElementType KEYWORD_GLOBALLY = new PlSqlTokenType(519, "GLOBALLY");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(655, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(858, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(907, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(618, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(557, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(714, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(786, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(844, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(583, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(792, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(841, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(489, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(707, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(649, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(751, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(848, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(805, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(429, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(587, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(682, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(603, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(559, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(424, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(433, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(491, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(787, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(854, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(427, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(742, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(896, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(412, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(679, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(414, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(553, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(769, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(800, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(517, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(473, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(864, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(806, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(900, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(465, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(479, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(442, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(482, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(753, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(499, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(561, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(627, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(886, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(641, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(729, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(440, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(745, "DAY");
		IElementType KEYWORD_FAST = new PlSqlTokenType(699, "FAST");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(836, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(599, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(738, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(579, "CONNECT");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(814, "TIMEZONE_HOUR");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(549, "NOLOGGING");
		IElementType KEYWORD_SET = new PlSqlTokenType(472, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(475, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(905, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(590, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(438, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(651, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(799, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(445, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(776, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(459, "DISABLE");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(797, "TRIM");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(443, "TYPES");
		IElementType KEYWORD_ALL = new PlSqlTokenType(628, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(637, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(902, "NODISCARDFILE");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(690, "PRECISION");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(712, "CONSTANT");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(871, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(541, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(785, "AT");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(588, "AUDIT");
		IElementType KEYWORD_AS = new PlSqlTokenType(514, "AS");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(670, "CELL_FLASH_CACHE");
		IElementType KEYWORD_NEVER = new PlSqlTokenType(696, "NEVER");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(838, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(418, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(498, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(775, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(882, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(798, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(915, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(550, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(574, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(411, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(885, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(596, "OF");
		IElementType KEYWORD_REWRITE = new PlSqlTokenType(695, "REWRITE");
		IElementType KEYWORD_RANK = new PlSqlTokenType(801, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(737, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(840, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(556, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(456, "ON");
		IElementType KEYWORD_REFRESH = new PlSqlTokenType(697, "REFRESH");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(416, "PURGE");
		IElementType KEYWORD_784 = new PlSqlTokenType(784, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(657, "LIMIT");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(865, "FETCH");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(575, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(567, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(513, "FINAL");
		IElementType KEYWORD_OR = new PlSqlTokenType(506, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(932, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(582, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(461, "ROW");
		IElementType KEYWORD_EXCLUDING = new PlSqlTokenType(691, "EXCLUDING");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(447, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(887, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(577, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(892, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(830, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(727, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(744, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(884, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(586, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(455, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(622, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(644, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(793, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(860, "MERGE");
		IElementType KEYWORD_EXTERNALLY = new PlSqlTokenType(518, "EXTERNALLY");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(463, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(625, "PCTTHRESHOLD");
		IElementType KEYWORD_BUILD = new PlSqlTokenType(698, "BUILD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(493, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(852, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(546, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(926, "ZONED");
		IElementType KEYWORD_HIERARCHY = new PlSqlTokenType(825, "HIERARCHY");
		IElementType KEYWORD_NULL = new PlSqlTokenType(453, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(566, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(719, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(782, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(938, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(716, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(903, "DISCARDFILE");
		IElementType KEYWORD_ACCOUNT = new PlSqlTokenType(526, "ACCOUNT");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(423, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(598, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(816, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(857, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(725, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(746, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(778, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(936, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(736, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(621, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(850, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(425, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(704, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(730, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(593, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(660, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(642, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(494, "SPOOL");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(464, "PRIMARY");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(604, "WHEN");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(614, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(674, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(487, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(619, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(862, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(571, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(570, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(633, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(834, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(819, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(750, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(533, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(724, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(609, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(558, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(833, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(562, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(640, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(500, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(904, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(677, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(486, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(813, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(563, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(551, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(715, "WHILE");
		IElementType KEYWORD_ID = new PlSqlTokenType(692, "ID");
		IElementType KEYWORD_REAL = new PlSqlTokenType(748, "REAL");
		IElementType KEYWORD_MATERIALIZED = new PlSqlTokenType(508, "MATERIALIZED");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(538, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(822, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(543, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(555, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(636, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(646, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(791, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(457, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(537, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(922, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(684, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(837, "INTO");
		IElementType KEYWORD_RESOURCE = new PlSqlTokenType(828, "RESOURCE");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(675, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(708, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(913, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(620, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(434, "DATABASE");
		IElementType KEYWORD_INDEXTYPE = new PlSqlTokenType(829, "INDEXTYPE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(471, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(540, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(717, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(930, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(861, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(685, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(855, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(665, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(426, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(437, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(594, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(666, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(662, "FREELISTS");
		IElementType KEYWORD_REDUCED = new PlSqlTokenType(689, "REDUCED");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(739, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(743, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(771, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(686, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(758, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(560, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(917, "WHITESPACE");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(512, "UNDER");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(419, "CONSTRAINTS");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(768, "CHARSET");
		IElementType KEYWORD_LOG = new PlSqlTokenType(509, "LOG");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(918, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(502, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(706, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(617, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(811, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(446, "SYSTEM");
		IElementType KEYWORD_DECODE = new PlSqlTokenType(796, "DECODE");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(818, "LEADING");
		IElementType KEYWORD_702 = new PlSqlTokenType(702, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(752, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(899, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(661, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(711, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(607, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(676, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(568, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(872, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(534, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(762, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(889, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(497, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(522, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(623, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(928, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(740, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(764, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(703, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(615, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(823, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(709, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(480, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(772, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(421, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(756, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(678, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(733, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(669, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(808, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(757, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(835, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(413, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(910, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(507, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(927, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(638, "NOPARALLEL");
		IElementType KEYWORD_ADMIN = new PlSqlTokenType(827, "ADMIN");
		IElementType KEYWORD_STRING = new PlSqlTokenType(895, "STRING");
		IElementType KEYWORD_UNUSABLE = new PlSqlTokenType(843, "UNUSABLE");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(490, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(476, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(839, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(667, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(564, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(477, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(820, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(846, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(428, "SYNONYM");
		IElementType KEYWORD_BECOME = new PlSqlTokenType(831, "BECOME");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(875, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(516, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(580, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(934, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(645, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(763, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(485, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(671, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(810, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(923, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(874, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(908, "FIELDS");
		IElementType KEYWORD_DEMAND = new PlSqlTokenType(701, "DEMAND");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(881, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(422, "INDEX");
		IElementType KEYWORD_STA = new PlSqlTokenType(495, "STA");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(605, "BITMAP");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(815, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(788, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(569, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(444, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(450, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(536, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(795, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(647, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(478, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(726, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(672, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(734, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(663, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(817, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(535, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(531, "TEMPFILE");
		IElementType KEYWORD_COMPLETE = new PlSqlTokenType(700, "COMPLETE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(878, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(933, "DATE_FORMAT");
		IElementType KEYWORD_REBUILD = new PlSqlTokenType(842, "REBUILD");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(680, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(722, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(876, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(914, "LOBFILE");
		IElementType KEYWORD_PROFILE = new PlSqlTokenType(523, "PROFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(441, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(626, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(766, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(554, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(929, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(630, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(879, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(747, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(451, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(765, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(530, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(937, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(616, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(683, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(705, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(749, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(578, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(812, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(869, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(911, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(658, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(589, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(635, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(767, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(783, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(880, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(664, "GROUPS");
		IElementType KEYWORD_UNLOCK = new PlSqlTokenType(528, "UNLOCK");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(415, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(789, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(505, "CREATE");
		IElementType KEYWORD_WITHOUT = new PlSqlTokenType(688, "WITHOUT");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(483, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(458, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(681, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(668, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(606, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(496, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(454, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(781, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(774, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(924, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(920, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(897, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(643, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(634, "INITRANS");
		IElementType KEYWORD_QUERY = new PlSqlTokenType(694, "QUERY");
		IElementType KEYWORD_LINK = new PlSqlTokenType(435, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(731, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(894, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(552, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(790, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(877, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(866, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(760, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(901, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(779, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(488, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(832, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(597, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(721, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(773, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(565, "END");
		IElementType KEYWORD_EXPIRE = new PlSqlTokenType(525, "EXPIRE");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(436, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(720, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(467, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(573, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(612, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(542, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(529, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(925, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(873, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(912, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(851, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(654, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(432, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(916, "TERMINATED");
		IElementType KEYWORD_OLD = new PlSqlTokenType(602, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(659, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(592, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(863, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(532, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(759, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(504, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(460, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(935, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(891, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(474, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(906, "SKIP");
		IElementType KEYWORD_PASSWORD = new PlSqlTokenType(524, "PASSWORD");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(608, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(420, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(652, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(520, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(883, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(735, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(821, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(613, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(648, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(931, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(510, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(893, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(548, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(780, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(673, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(449, "DEFAULT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(802, "DENSE_RANK");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(539, "MANAGEMENT");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(898, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(656, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(511, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(584, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(515, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(804, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(867, "SHARE");
		IElementType KEYWORD_QUOTA = new PlSqlTokenType(521, "QUOTA");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(576, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(849, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(921, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(718, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(439, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(468, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(527, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(853, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(481, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(755, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(501, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(761, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(572, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(847, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(484, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(595, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(544, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(624, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(547, "GROUP");
		IElementType KEYWORD_PREBUILT = new PlSqlTokenType(687, "PREBUILT");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(856, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(430, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(693, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(448, "STORAGE");

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
		KEYWORD_784,
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
		KEYWORD_702,
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
