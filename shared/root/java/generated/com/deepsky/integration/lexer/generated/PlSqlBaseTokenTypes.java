package com.deepsky.integration.lexer.generated;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.deepsky.integration.PlSqlTokenType;

public interface PlSqlBaseTokenTypes {

		IElementType DOLLAR = new PlSqlTokenType(39, "DOLLAR");
		IElementType LT = new PlSqlTokenType(9, "LT");
		IElementType QUOTED_STR = new PlSqlTokenType(20, "QUOTED_STR");
		IElementType STORAGE_SIZE = new PlSqlTokenType(8, "STORAGE_SIZE");
		IElementType DOUBLEDOT = new PlSqlTokenType(36, "DOUBLEDOT");
		IElementType ANY_CHARACTER = new PlSqlTokenType(18, "ANY_CHARACTER");
		IElementType BAD_CHARACTER = new PlSqlTokenType(5, "BAD_CHARACTER");
		IElementType ASTERISK = new PlSqlTokenType(27, "ASTERISK");
		IElementType ML_COMMENT = new PlSqlTokenType(50, "ML_COMMENT");
		IElementType THEN_COND_CMPL = new PlSqlTokenType(52, "THEN_COND_CMPL");
		IElementType COMMA = new PlSqlTokenType(26, "COMMA");
		IElementType IDENTIFIER = new PlSqlTokenType(19, "IDENTIFIER");
		IElementType ELSE_COND_CMPL = new PlSqlTokenType(53, "ELSE_COND_CMPL");
		IElementType CUSTOM_TOKEN = new PlSqlTokenType(13, "CUSTOM_TOKEN");
		IElementType AT_PREFIXED = new PlSqlTokenType(22, "AT_PREFIXED");
		IElementType PLUS = new PlSqlTokenType(30, "PLUS");
		IElementType TABLE_NAME_SPEC = new PlSqlTokenType(16, "TABLE_NAME_SPEC");
		IElementType CLOSE_PAREN = new PlSqlTokenType(29, "CLOSE_PAREN");
		IElementType END_COND_CMPL = new PlSqlTokenType(54, "END_COND_CMPL");
		IElementType PASS_BY_NAME = new PlSqlTokenType(42, "PASS_BY_NAME");
		IElementType BAD_ML_COMMENT = new PlSqlTokenType(4, "BAD_ML_COMMENT");
		IElementType EQ = new PlSqlTokenType(34, "EQ");
		IElementType DOT = new PlSqlTokenType(25, "DOT");
		IElementType ASSIGNMENT_EQ = new PlSqlTokenType(41, "ASSIGNMENT_EQ");
		IElementType ERROR_COND_CMPL = new PlSqlTokenType(55, "ERROR_COND_CMPL");
		IElementType DIVIDE = new PlSqlTokenType(32, "DIVIDE");
		IElementType DOUBLE_QUOTED_STRING = new PlSqlTokenType(21, "DOUBLE_QUOTED_STRING");
		IElementType START_LABEL = new PlSqlTokenType(38, "START_LABEL");
		IElementType GE = new PlSqlTokenType(11, "GE");
		IElementType CONCAT = new PlSqlTokenType(37, "CONCAT");
		IElementType BAD_NUMBER_LITERAL = new PlSqlTokenType(7, "BAD_NUMBER_LITERAL");
		IElementType N = new PlSqlTokenType(46, "N");
		IElementType BAD_CHAR_LITERAL = new PlSqlTokenType(6, "BAD_CHAR_LITERAL");
		IElementType NUMBER = new PlSqlTokenType(45, "NUMBER");
		IElementType COLON_OLD = new PlSqlTokenType(15, "COLON_OLD");
		IElementType OPEN_PAREN = new PlSqlTokenType(28, "OPEN_PAREN");
		IElementType MINUS = new PlSqlTokenType(31, "MINUS");
		IElementType SEMI = new PlSqlTokenType(23, "SEMI");
		IElementType PERCENTAGE = new PlSqlTokenType(35, "PERCENTAGE");
		IElementType NOT_EQ = new PlSqlTokenType(12, "NOT_EQ");
		IElementType VERTBAR = new PlSqlTokenType(43, "VERTBAR");
		IElementType COLON = new PlSqlTokenType(24, "COLON");
		IElementType PLSQL_ENV_VAR = new PlSqlTokenType(17, "PLSQL_ENV_VAR");
		IElementType WS = new PlSqlTokenType(47, "WS");
		IElementType END_LABEL = new PlSqlTokenType(40, "END_LABEL");
		IElementType SL_COMMENT = new PlSqlTokenType(49, "SL_COMMENT");
		IElementType GT = new PlSqlTokenType(44, "GT");
		IElementType IF_COND_CMPL = new PlSqlTokenType(51, "IF_COND_CMPL");
		IElementType COLON_NEW = new PlSqlTokenType(14, "COLON_NEW");
		IElementType LE = new PlSqlTokenType(10, "LE");
		IElementType BACKSLASH = new PlSqlTokenType(33, "BACKSLASH");
		IElementType LF = new PlSqlTokenType(48, "LF");
		IElementType KEYWORD_USING = new PlSqlTokenType(426, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(680, "ERROR_INDEX");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(573, "REFERENCING");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(582, "NESTED");
		IElementType KEYWORD_STORE = new PlSqlTokenType(583, "STORE");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(667, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(625, "EXTERNAL");
		IElementType KEYWORD_PRIVILEGES = new PlSqlTokenType(783, "PRIVILEGES");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(810, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(604, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(712, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(870, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(860, "MISSING");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(440, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(698, "TIME");
		IElementType KEYWORD_OVER = new PlSqlTokenType(768, "OVER");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(839, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(436, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(405, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(557, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(796, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(685, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(466, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(611, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(821, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(689, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(762, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(603, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(563, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(444, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(622, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(670, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(572, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(391, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(841, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(729, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(766, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(443, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(601, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(736, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(819, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(553, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(753, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(516, "NOGUARANTEE");
		IElementType KEYWORD_GLOBALLY = new PlSqlTokenType(490, "GLOBALLY");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(627, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(809, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(858, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(590, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(528, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(671, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(745, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(795, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(555, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(751, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(794, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(463, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(664, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(621, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(708, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(799, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(764, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(403, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(559, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(654, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(575, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(530, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(398, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(407, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(465, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(746, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(805, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(401, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(699, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(847, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(386, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(651, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(388, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(524, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(728, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(759, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(488, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(447, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(815, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(765, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(851, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(439, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(453, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(416, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(456, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(710, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(473, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(532, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(599, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(837, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(613, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(686, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(414, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(702, "DAY");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(788, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(571, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(695, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(551, "CONNECT");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(773, "TIMEZONE_HOUR");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(520, "NOLOGGING");
		IElementType KEYWORD_SET = new PlSqlTokenType(446, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(449, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(856, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(562, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(412, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(623, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(758, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(419, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(735, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(433, "DISABLE");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(417, "TYPES");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(756, "TRIM");
		IElementType KEYWORD_ALL = new PlSqlTokenType(600, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(609, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(853, "NODISCARDFILE");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(669, "CONSTANT");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(711, "PRECISION");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(822, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(512, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(744, "AT");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(560, "AUDIT");
		IElementType KEYWORD_AS = new PlSqlTokenType(485, "AS");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(642, "CELL_FLASH_CACHE");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(791, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(392, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(472, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(734, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(833, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(757, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(866, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(521, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(546, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(385, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(836, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(568, "OF");
		IElementType KEYWORD_RANK = new PlSqlTokenType(760, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(694, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(793, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(527, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(430, "ON");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(390, "PURGE");
		IElementType KEYWORD_743 = new PlSqlTokenType(743, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(629, "LIMIT");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(816, "FETCH");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(547, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(539, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(484, "FINAL");
		IElementType KEYWORD_OR = new PlSqlTokenType(479, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(883, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(554, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(435, "ROW");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(421, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(838, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(549, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(843, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(790, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(684, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(701, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(835, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(558, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(429, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(594, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(616, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(752, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(811, "MERGE");
		IElementType KEYWORD_EXTERNALLY = new PlSqlTokenType(489, "EXTERNALLY");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(437, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(597, "PCTTHRESHOLD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(467, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(803, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(517, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(877, "ZONED");
		IElementType KEYWORD_NULL = new PlSqlTokenType(427, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(538, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(676, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(741, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(889, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(673, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(854, "DISCARDFILE");
		IElementType KEYWORD_ACCOUNT = new PlSqlTokenType(497, "ACCOUNT");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(397, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(570, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(775, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(808, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(682, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(703, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(737, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(887, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(693, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(593, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(801, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(399, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(661, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(687, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(565, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(632, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(614, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(468, "SPOOL");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(576, "WHEN");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(438, "PRIMARY");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(586, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(646, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(461, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(591, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(813, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(543, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(542, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(605, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(786, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(778, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(707, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(504, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(681, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(581, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(529, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(785, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(533, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(612, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(474, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(855, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(649, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(460, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(772, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(534, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(522, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(672, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(705, "REAL");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(509, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(781, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(514, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(526, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(608, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(618, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(750, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(431, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(508, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(873, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(656, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(789, "INTO");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(647, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(665, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(864, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(592, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(408, "DATABASE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(445, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(511, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(674, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(881, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(812, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(657, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(806, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(637, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(400, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(411, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(566, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(638, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(634, "FREELISTS");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(696, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(700, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(730, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(658, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(716, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(531, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(868, "WHITESPACE");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(483, "UNDER");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(393, "CONSTRAINTS");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(727, "CHARSET");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(869, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(476, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(663, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(589, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(770, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(420, "SYSTEM");
		IElementType KEYWORD_DECODE = new PlSqlTokenType(755, "DECODE");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(777, "LEADING");
		IElementType KEYWORD_659 = new PlSqlTokenType(659, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(709, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(850, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(633, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(668, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(579, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(648, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(540, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(823, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(505, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(721, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(840, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(471, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(493, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(595, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(879, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(697, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(723, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(660, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(587, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(782, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(666, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(454, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(731, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(395, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(714, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(650, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(690, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(641, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(767, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(715, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(787, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(387, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(861, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(480, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(878, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(610, "NOPARALLEL");
		IElementType KEYWORD_STRING = new PlSqlTokenType(846, "STRING");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(464, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(450, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(792, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(639, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(536, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(451, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(779, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(797, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(402, "SYNONYM");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(826, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(487, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(552, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(885, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(617, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(722, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(459, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(643, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(769, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(874, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(825, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(859, "FIELDS");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(832, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(396, "INDEX");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(577, "BITMAP");
		IElementType KEYWORD_STA = new PlSqlTokenType(469, "STA");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(774, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(747, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(541, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(418, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(424, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(507, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(754, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(619, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(452, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(683, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(644, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(691, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(635, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(776, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(506, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(502, "TEMPFILE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(829, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(884, "DATE_FORMAT");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(652, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(679, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(827, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(865, "LOBFILE");
		IElementType KEYWORD_PROFILE = new PlSqlTokenType(494, "PROFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(415, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(598, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(725, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(525, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(880, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(602, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(830, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(704, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(425, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(724, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(501, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(888, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(588, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(655, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(662, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(706, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(550, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(771, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(820, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(862, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(630, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(561, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(607, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(726, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(742, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(831, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(636, "GROUPS");
		IElementType KEYWORD_UNLOCK = new PlSqlTokenType(499, "UNLOCK");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(389, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(748, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(478, "CREATE");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(457, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(432, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(653, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(640, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(578, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(470, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(428, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(740, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(733, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(875, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(871, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(848, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(615, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(606, "INITRANS");
		IElementType KEYWORD_LINK = new PlSqlTokenType(409, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(688, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(845, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(523, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(749, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(828, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(817, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(718, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(852, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(738, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(462, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(784, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(569, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(678, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(732, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(537, "END");
		IElementType KEYWORD_EXPIRE = new PlSqlTokenType(496, "EXPIRE");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(410, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(677, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(441, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(545, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(584, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(513, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(500, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(876, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(824, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(863, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(802, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(626, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(406, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(867, "TERMINATED");
		IElementType KEYWORD_OLD = new PlSqlTokenType(574, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(631, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(564, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(814, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(503, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(717, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(535, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(434, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(886, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(842, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(448, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(857, "SKIP");
		IElementType KEYWORD_PASSWORD = new PlSqlTokenType(495, "PASSWORD");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(580, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(394, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(624, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(491, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(834, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(692, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(780, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(585, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(620, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(882, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(481, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(844, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(519, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(739, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(645, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(423, "DEFAULT");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(510, "MANAGEMENT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(761, "DENSE_RANK");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(849, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(628, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(482, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(556, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(486, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(763, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(818, "SHARE");
		IElementType KEYWORD_QUOTA = new PlSqlTokenType(492, "QUOTA");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(548, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(800, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(872, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(675, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(413, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(442, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(498, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(804, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(455, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(713, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(475, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(720, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(544, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(798, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(458, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(567, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(515, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(596, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(518, "GROUP");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(807, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(404, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(719, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(422, "STORAGE");

	TokenSet KEYWORDS = TokenSet.create(
		KEYWORD_USING,
		KEYWORD_ERROR_INDEX,
		//KEYWORD_REFERENCING,
		KEYWORD_NESTED,
		//KEYWORD_STORE,
		KEYWORD_FIPSFLAG,
		KEYWORD_EXTERNAL,
		KEYWORD_PRIVILEGES,
		//KEYWORD_WAIT,
		KEYWORD_PCTFREE,
		KEYWORD_FLOAT,
		//KEYWORD_LRTRIM,
		//KEYWORD_MISSING,
		KEYWORD_REFERENCES,
		//KEYWORD_TIME,
		//KEYWORD_OVER,
		//KEYWORD_CHARACTERSET,
		//KEYWORD_MOVEMENT,
		//KEYWORD_ROLE,
		//KEYWORD_LOGON,
		KEYWORD_RIGHT,
		KEYWORD_ELSE,
		//KEYWORD_HOST,
		//KEYWORD_MONITORING,
		KEYWORD_SAVEPOINT,
		//KEYWORD_NUMBER,
		KEYWORD_EXTRACT,
		//KEYWORD_NOCOMPRESS,
		KEYWORD_DIASSOCIATE,
		KEYWORD_SYSDATE,
		//KEYWORD_NOVALIDATE,
		KEYWORD_SUBTYPE,
		KEYWORD_EACH,
		KEYWORD_VIEW,
		KEYWORD_BIG,
		//KEYWORD_SERIALLY_REUSABLE,
		//KEYWORD_NEXTVAL,
		KEYWORD_UNIQUE,
		KEYWORD_DIRECT_LOAD,
		KEYWORD_RAISE,
		KEYWORD_EXCLUSIVE,
		KEYWORD_BEFORE,
		KEYWORD_SQLERRM,
		//KEYWORD_NOGUARANTEE,
		KEYWORD_GLOBALLY,
		//KEYWORD_INSTANCES,
		//KEYWORD_NOWAIT,
		KEYWORD_PREPROCESSOR,
		KEYWORD_PCTVERSION,
		KEYWORD_WRITE,
		KEYWORD_LOOP,
		KEYWORD_CURRENT,
		KEYWORD_LEFT,
		//KEYWORD_SHUTDOWN,
		KEYWORD_MEMBER,
		//KEYWORD_SID,
		//KEYWORD_DEFINE,
		KEYWORD_RESTRICT_REFERENCES,
		KEYWORD_PARTITIONS,
		//KEYWORD_INTEGER,
		KEYWORD_JOIN,
		KEYWORD_HOUR,
		KEYWORD_OPERATOR,
		KEYWORD_ANALYZE,
		KEYWORD_REF,
		//KEYWORD_NEW,
		//KEYWORD_INCLUDING,
		KEYWORD_SEQUENCE,
		//KEYWORD_LIBRARY,
		//KEYWORD_REM,
		KEYWORD_EXISTS,
		KEYWORD_HAVING,
		//KEYWORD_PUBLIC,
		KEYWORD_ZONE,
		//KEYWORD_SIZES,
		KEYWORD_BODY,
		KEYWORD_VISIBLE,
		KEYWORD_DROP,
		KEYWORD_NORMAL,
		KEYWORD_EXCEPTION,
		//KEYWORD_LEAD,
		KEYWORD_BY,
		//KEYWORD_LONG,
		KEYWORD_CLOSE,
		KEYWORD_ANY,
		//KEYWORD_NOBADFILE,
		KEYWORD_KEY,
		KEYWORD_EXECUTE,
		KEYWORD_PACKAGES,
		KEYWORD_OSERROR,
		//KEYWORD_DOUBLE,
		//KEYWORD_REPHEADER,
		KEYWORD_AND,
		//KEYWORD_COMPRESS,
		//KEYWORD_DELIMITED,
		//KEYWORD_OVERFLOW,
		KEYWORD_AUTONOMOUS_TRANSACTION,
		//KEYWORD_COLUMN,
		KEYWORD_DAY,
		//KEYWORD_COLLECT,
		KEYWORD_UPDATE,
		//KEYWORD_RAW,
		KEYWORD_CONNECT,
		KEYWORD_TIMEZONE_HOUR,
		//KEYWORD_NOLOGGING,
		KEYWORD_SET,
		//KEYWORD_VAR,
		//KEYWORD_DATA_CACHE,
		KEYWORD_DDL,
		//KEYWORD_STATISTICS,
		//KEYWORD_ORGANIZATION,
		//KEYWORD_LAG,
		KEYWORD_INDEXTYPES,
		//KEYWORD_NAME,
		KEYWORD_DISABLE,
		KEYWORD_TYPES,
		//KEYWORD_TRIM,
		KEYWORD_ALL,
		//KEYWORD_PARALLEL,
		//KEYWORD_NODISCARDFILE,
		KEYWORD_CONSTANT,
		//KEYWORD_PRECISION,
		//KEYWORD_ORACLE_LOADER,
		//KEYWORD_UNIFORM,
		KEYWORD_AT,
		KEYWORD_AUDIT,
		KEYWORD_AS,
		KEYWORD_CELL_FLASH_CACHE,
		KEYWORD_FLUSH,
		KEYWORD_CASCADE,
		//KEYWORD_OFF,
		//KEYWORD_JAVA,
		//KEYWORD_DISABLED,
		//KEYWORD_MULTISET,
		//KEYWORD_ENCLOSED,
		KEYWORD_NO,
		//KEYWORD_NOCACHE,
		KEYWORD_PACKAGE,
		//KEYWORD_FIXED,
		KEYWORD_OF,
		//KEYWORD_RANK,
		KEYWORD_BYTE,
		KEYWORD_RESET,
		KEYWORD_ONLY,
		KEYWORD_ON,
		KEYWORD_PURGE,
		KEYWORD_743,
		//KEYWORD_LIMIT,
		KEYWORD_FETCH,
		//KEYWORD_INCREMENT,
		KEYWORD_COALESCE,
		KEYWORD_FINAL,
		KEYWORD_OR,
		//KEYWORD_VARRAWC,
		KEYWORD_STARTUP,
		KEYWORD_ROW,
		KEYWORD_MANAGED,
		//KEYWORD_NEWLINE,
		//KEYWORD_NOORDER,
		KEYWORD_ENDIAN,
		KEYWORD_SESSION,
		KEYWORD_THEN,
		KEYWORD_MONTH,
		KEYWORD_RECORDS,
		//KEYWORD_LOGOFF,
		KEYWORD_COMMENT,
		KEYWORD_CREATION,
		KEYWORD_INTERVAL,
		KEYWORD_SQLCODE,
		KEYWORD_MERGE,
		KEYWORD_EXTERNALLY,
		KEYWORD_CONSTRAINT,
		KEYWORD_PCTTHRESHOLD,
		KEYWORD_QUIT,
		KEYWORD_ROWNUM,
		KEYWORD_AUTOEXTEND,
		//KEYWORD_ZONED,
		KEYWORD_NULL,
		KEYWORD_BACKUP,
		//KEYWORD_ROWCOUNT,
		KEYWORD_TRUE,
		//KEYWORD_LDTRIM,
		//KEYWORD_SQL,
		//KEYWORD_DISCARDFILE,
		KEYWORD_ACCOUNT,
		KEYWORD_FORCE,
		KEYWORD_INSERT,
		KEYWORD_TIMEZONE_REGION,
		//KEYWORD_LAST,
		//KEYWORD_COUNT,
		KEYWORD_SECOND,
		KEYWORD_SAVE,
		//KEYWORD_LOCATION,
		//KEYWORD_CHAR,
		KEYWORD_SEGMENT,
		KEYWORD_WHERE,
		//KEYWORD_TYPE,
		KEYWORD_AUTHID,
		KEYWORD_PRIOR,
		KEYWORD_REVOKE,
		KEYWORD_MAXEXTENTS,
		KEYWORD_PARTITION,
		//KEYWORD_SPOOL,
		KEYWORD_WHEN,
		KEYWORD_PRIMARY,
		//KEYWORD_VALUE,
		//KEYWORD_ACTION,
		//KEYWORD_NONE,
		KEYWORD_FREEPOOLS,
		KEYWORD_RETURNING,
		//KEYWORD_CYCLE,
		//KEYWORD_MINVALUE,
		KEYWORD_PCTUSED,
		KEYWORD_MINUS,
		KEYWORD_TRAILING,
		//KEYWORD_INT,
		KEYWORD_DATAFILE,
		KEYWORD_ERROR_CODE,
		//KEYWORD_ROWS,
		KEYWORD_PERMANENT,
		KEYWORD_INTERSECT,
		KEYWORD_DATAFILES,
		//KEYWORD_NOMONITORING,
		//KEYWORD_SERVEROUTPUT,
		//KEYWORD_READSIZE,
		//KEYWORD_NOSORT,
		KEYWORD_ROLLBACK,
		KEYWORD_FROM,
		KEYWORD_ADD,
		//KEYWORD_ONLINE,
		KEYWORD_WHILE,
		//KEYWORD_REAL,
		KEYWORD_EXTENT,
		KEYWORD_IF,
		//KEYWORD_RETENTION,
		//KEYWORD_READ,
		KEYWORD_COMPUTE,
		KEYWORD_LESS,
		KEYWORD_BETWEEN,
		KEYWORD_IS,
		//KEYWORD_REUSE,
		//KEYWORD_RTRIM,
		KEYWORD_ROWTYPE,
		KEYWORD_INTO,
		KEYWORD_MODIFY,
		//KEYWORD_INTERFACE,
		//KEYWORD_CONCAT,
		KEYWORD_IN,
		//KEYWORD_DATABASE,
		KEYWORD_SYSTIMESTAMP,
		//KEYWORD_LOCAL,
		KEYWORD_FOUND,
		//KEYWORD_VARRAW,
		//KEYWORD_MATCHED,
		KEYWORD_VARRAY,
		//KEYWORD_NULLS,
		//KEYWORD_OPTIMAL,
		//KEYWORD_VALIDATE,
		KEYWORD_ASSOCIATE,
		//KEYWORD_SCHEMA,
		KEYWORD_BUFFER_POOL,
		//KEYWORD_FREELISTS,
		//KEYWORD_BOOLEAN,
		KEYWORD_YEAR,
		//KEYWORD_PIPELINED,
		//KEYWORD_OPTION,
		//KEYWORD_NVARCHAR,
		KEYWORD_CONTENTS,
		KEYWORD_WHITESPACE,
		KEYWORD_UNDER,
		KEYWORD_CONSTRAINTS,
		//KEYWORD_CHARSET,
		//KEYWORD_OPTIONALLY,
		KEYWORD_DECLARE,
		KEYWORD_PRAGMA,
		KEYWORD_READS,
		KEYWORD_DBTIMEZONE,
		KEYWORD_SYSTEM,
		KEYWORD_DECODE,
		KEYWORD_LEADING,
		KEYWORD_659,
		KEYWORD_PLS_INTEGER,
		//KEYWORD_LOAD,
		KEYWORD_PCTINCREASE,
		KEYWORD_EXCEPTION_INIT,
		KEYWORD_DESC,
		KEYWORD_SORT,
		KEYWORD_MINIMUM,
		//KEYWORD_ORACLE_DATAPUMP,
		//KEYWORD_NEXT,
		//KEYWORD_CLOB,
		//KEYWORD_DATA,
		//KEYWORD_REPFOOTER,
		KEYWORD_UNLIMITED,
		KEYWORD_DEFERRED,
		//KEYWORD_ORACLE_NUMBER,
		//KEYWORD_DATE,
		KEYWORD_BFILE,
		//KEYWORD_TIMESTAMP,
		KEYWORD_LOB,
		KEYWORD_ELSIF,
		//KEYWORD_BUILTIN,
		//KEYWORD_WHENEVER,
		//KEYWORD_PARALLEL_ENABLE,
		KEYWORD_PROCEDURE,
		//KEYWORD_VARCHAR,
		KEYWORD_REVERSE,
		KEYWORD_BINARY_INTEGER,
		KEYWORD_FLASH_CACHE,
		//KEYWORD_CURRVAL,
		//KEYWORD_VARCHAR2,
		//KEYWORD_BULK,
		KEYWORD_ALTER,
		//KEYWORD_FIELD,
		KEYWORD_REPLACE,
		//KEYWORD_ORACLE_DATE,
		//KEYWORD_NOPARALLEL,
		KEYWORD_STRING,
		//KEYWORD_PROMPT,
		KEYWORD_VARIABLE,
		//KEYWORD_SHARED_POOL,
		//KEYWORD_KEEP,
		KEYWORD_TO,
		//KEYWORD_COL,
		KEYWORD_BOTH,
		KEYWORD_INNER,
		KEYWORD_SYNONYM,
		//KEYWORD_NOLOGFILE,
		KEYWORD_IDENTIFIED,
		KEYWORD_AFTER,
		KEYWORD_TIMEZONE,
		KEYWORD_VALUES,
		KEYWORD_NCLOB,
		KEYWORD_COMMIT,
		//KEYWORD_ENCRYPT,
		KEYWORD_SESSIONTIMEZONE,
		//KEYWORD_LDRTRIM,
		//KEYWORD_PARAMETERS,
		//KEYWORD_FIELDS,
		//KEYWORD_ENABLED,
		//KEYWORD_INDEX,
		KEYWORD_BITMAP,
		KEYWORD_STA,
		KEYWORD_TIMEZONE_MINUTE,
		KEYWORD_SELECT,
		KEYWORD_MAXVALUE,
		KEYWORD_INDEXES,
		KEYWORD_COST,
		//KEYWORD_SIZE,
		KEYWORD_CAST,
		KEYWORD_THAN,
		//KEYWORD_EXEC,
		KEYWORD_CASE,
		KEYWORD_FOREIGN,
		//KEYWORD_NATURAL,
		//KEYWORD_FREELIST,
		KEYWORD_TIMEZONE_ABBR,
		//KEYWORD_MAXSIZE,
		//KEYWORD_TEMPFILE,
		//KEYWORD_COMPATIBLE,
		KEYWORD_DATE_FORMAT,
		//KEYWORD_NOVISIBLE,
		//KEYWORD_BULK_EXCEPTIONS,
		//KEYWORD_LOGFILE,
		//KEYWORD_LOBFILE,
		KEYWORD_PROFILE,
		KEYWORD_FUNCTIONS,
		KEYWORD_FILESYSTEM_LIKE_LOGGING,
		//KEYWORD_NOCOPY,
		KEYWORD_IMMEDIATE,
		KEYWORD_COUNTED,
		//KEYWORD_OPERATIONS,
		//KEYWORD_LATEST,
		KEYWORD_SMALLINT,
		KEYWORD_SELECTIVITY,
		KEYWORD_OUT,
		//KEYWORD_SMALLFILE,
		KEYWORD_AGGREGATE,
		KEYWORD_CHUNK,
		KEYWORD_CURSOR,
		//KEYWORD_WRAPPED,
		//KEYWORD_NUMERIC,
		KEYWORD_FOR,
		KEYWORD_DISTINCT,
		//KEYWORD_OPEN,
		KEYWORD_ARE,
		//KEYWORD_INITIAL,
		//KEYWORD_NOAUDIT,
		KEYWORD_MAXTRANS,
		KEYWORD_ANY_CS,
		KEYWORD_FALSE,
		//KEYWORD_COMPRESSION,
		//KEYWORD_GROUPS,
		KEYWORD_UNLOCK,
		KEYWORD_TABLE,
		KEYWORD_LIKE,
		KEYWORD_CREATE,
		KEYWORD_EXIT,
		KEYWORD_NOT,
		KEYWORD_RECORD,
		KEYWORD_RECYCLE,
		KEYWORD_ASC,
		KEYWORD_START,
		//KEYWORD_TRUNCATE,
		KEYWORD_INDICES,
		//KEYWORD_LANGUAGE,
		//KEYWORD_POSITION,
		//KEYWORD_NOTRIM,
		//KEYWORD_BYTES,
		KEYWORD_RANGE,
		KEYWORD_INITRANS,
		KEYWORD_LINK,
		KEYWORD_GOTO,
		//KEYWORD_NOCHECK,
		KEYWORD_OFFLINE,
		KEYWORD_ESCAPE,
		//KEYWORD_VERSION,
		//KEYWORD_MODE,
		//KEYWORD_CHARACTER,
		//KEYWORD_BADFILE,
		KEYWORD_EXCEPTIONS,
		//KEYWORD_DEF,
		KEYWORD_UNION,
		KEYWORD_DELETE,
		//KEYWORD_BULK_ROWCOUNT,
		KEYWORD_DETERMINISTIC,
		KEYWORD_END,
		KEYWORD_EXPIRE,
		KEYWORD_TRIGGER,
		//KEYWORD_ISOPEN,
		KEYWORD_RELY,
		//KEYWORD_CACHE,
		KEYWORD_RETURN,
		//KEYWORD_DICTIONARY,
		//KEYWORD_BIGFILE,
		KEYWORD_UNSIGNED,
		KEYWORD_ACCESS,
		//KEYWORD_TRANSFORMS,
		KEYWORD_CURRENT_TIMESTAMP,
		KEYWORD_DEGREE,
		KEYWORD_DIRECTORY,
		//KEYWORD_TERMINATED,
		//KEYWORD_OLD,
		//KEYWORD_MINEXTENTS,
		KEYWORD_GRANT,
		//KEYWORD_TRANSACTION,
		KEYWORD_UNDO,
		//KEYWORD_NVARCHAR2,
		KEYWORD_RENAME,
		KEYWORD_ENABLE,
		//KEYWORD_MASK,
		//KEYWORD_LITTLE,
		//KEYWORD_SHOW,
		KEYWORD_SKIP,
		KEYWORD_PASSWORD,
		KEYWORD_PRESERVE,
		KEYWORD_FUNCTION,
		//KEYWORD_HEAP,
		KEYWORD_TABLESPACE,
		//KEYWORD_ENCRYPTION,
		//KEYWORD_POSITIVE,
		//KEYWORD_WORK,
		KEYWORD_LOCATOR,
		//KEYWORD_HASH,
		//KEYWORD_VARCHARC,
		KEYWORD_GLOBAL,
		//KEYWORD_MARK,
		//KEYWORD_LOGGING,
		KEYWORD_FORALL,
		KEYWORD_RESTRICT,
		KEYWORD_DEFAULT,
		KEYWORD_MANAGEMENT,
		//KEYWORD_DENSE_RANK,
		//KEYWORD_CHARACTERS,
		KEYWORD_REJECT,
		//KEYWORD_TEMPORARY,
		KEYWORD_SERVERERROR,
		KEYWORD_OBJECT,
		KEYWORD_MINUTE,
		//KEYWORD_SHARE,
		KEYWORD_QUOTA,
		KEYWORD_ORDER,
		//KEYWORD_FULL,
		//KEYWORD_LTRIM,
		//KEYWORD_NOTFOUND,
		KEYWORD_WITH,
		KEYWORD_CHECK,
		KEYWORD_LOCK,
		//KEYWORD_THE,
		//KEYWORD_SQLERROR,
		//KEYWORD_DECIMAL,
		KEYWORD_BEGIN,
		//KEYWORD_BLOB,
		//KEYWORD_NOCYCLE,
		KEYWORD_OUTER,
		//KEYWORD_CONTINUE,
		KEYWORD_INSTEAD,
		//KEYWORD_GUARANTEE,
		KEYWORD_CLUSTER,
		KEYWORD_GROUP,
		//KEYWORD_FIRST,
		//KEYWORD_USER,
		KEYWORD_ROWID,
		KEYWORD_STORAGE,
		new PlSqlTokenType(9999, "$$$END_OF_ARRAY_$$$")
		);
}
