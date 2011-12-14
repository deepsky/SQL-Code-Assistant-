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
		IElementType KEYWORD_USING = new PlSqlTokenType(402, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(658, "ERROR_INDEX");
		IElementType KEYWORD_STORE = new PlSqlTokenType(546, "STORE");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(545, "NESTED");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(534, "REFERENCING");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(634, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(588, "EXTERNAL");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(781, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(566, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(689, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(818, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(816, "MISSING");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(417, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(674, "TIME");
		IElementType KEYWORD_OVER = new PlSqlTokenType(741, "OVER");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(796, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(414, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(381, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(518, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(764, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(662, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(441, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(573, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(788, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(665, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(735, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(565, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(524, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(408, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(584, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(637, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(533, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(367, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(797, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(706, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(739, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(539, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(563, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(643, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(787, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(514, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(727, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(475, "NOGUARANTEE");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(590, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(780, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(837, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(553, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(487, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(638, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(720, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(763, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(516, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(725, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(762, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(438, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(631, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(583, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(685, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(767, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(737, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(379, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(520, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(621, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(537, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(489, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(374, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(383, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(440, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(721, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(776, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(377, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(675, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(803, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(362, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(615, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(364, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(483, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(705, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(732, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(507, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(422, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(649, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(738, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(806, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(416, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(428, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(392, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(431, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(687, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(448, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(491, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(561, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(794, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(581, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(663, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(390, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(678, "DAY");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(756, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(532, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(671, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(511, "CONNECT");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(479, "NOLOGGING");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(746, "TIMEZONE_HOUR");
		IElementType KEYWORD_SET = new PlSqlTokenType(421, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(424, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(814, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(523, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(388, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(585, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(731, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(395, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(709, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(411, "DISABLE");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(393, "TYPES");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(729, "TRIM");
		IElementType KEYWORD_ALL = new PlSqlTokenType(562, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(571, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(808, "NODISCARDFILE");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(636, "CONSTANT");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(688, "PRECISION");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(851, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(471, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(719, "AT");
		IElementType KEYWORD_AS = new PlSqlTokenType(536, "AS");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(521, "AUDIT");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(606, "CELL_FLASH_CACHE");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(759, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(368, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(444, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(708, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(840, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(730, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(824, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(480, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(505, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(361, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(795, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(529, "OF");
		IElementType KEYWORD_RANK = new PlSqlTokenType(733, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(670, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(761, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(486, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(406, "ON");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(366, "PURGE");
		IElementType KEYWORD_718 = new PlSqlTokenType(718, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(592, "LIMIT");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(506, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(498, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(618, "FINAL");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(650, "FETCH");
		IElementType KEYWORD_OR = new PlSqlTokenType(454, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(828, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(515, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(413, "ROW");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(397, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(793, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(509, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(799, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(758, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(661, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(677, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(790, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(519, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(405, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(557, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(577, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(726, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(646, "MERGE");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(419, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(586, "PCTTHRESHOLD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(442, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(774, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(476, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(832, "ZONED");
		IElementType KEYWORD_NULL = new PlSqlTokenType(403, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(497, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(654, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(716, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(822, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(651, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(809, "DISCARDFILE");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(373, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(531, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(748, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(779, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(660, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(679, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(713, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(834, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(669, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(556, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(769, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(375, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(628, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(664, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(526, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(596, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(575, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(443, "SPOOL");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(538, "WHEN");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(415, "PRIMARY");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(549, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(610, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(436, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(554, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(783, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(502, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(501, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(567, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(754, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(771, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(684, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(463, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(659, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(544, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(488, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(753, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(492, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(574, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(449, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(812, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(613, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(435, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(745, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(493, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(481, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(639, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(681, "REAL");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(468, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(641, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(473, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(485, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(570, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(579, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(724, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(407, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(467, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(821, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(623, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(757, "INTO");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(611, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(632, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(835, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(555, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(384, "DATABASE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(409, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(470, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(652, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(826, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(782, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(624, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(777, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(601, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(376, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(387, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(527, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(602, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(598, "FREELISTS");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(672, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(676, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(710, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(625, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(693, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(490, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(847, "WHITESPACE");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(369, "CONSTRAINTS");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(617, "UNDER");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(704, "CHARSET");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(848, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(451, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(630, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(552, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(743, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(396, "SYSTEM");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(770, "LEADING");
		IElementType KEYWORD_626 = new PlSqlTokenType(626, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(686, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(805, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(597, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(635, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(542, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(612, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(499, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(852, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(464, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(698, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(844, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(447, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(593, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(558, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(829, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(673, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(700, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(627, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(550, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(751, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(633, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(429, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(711, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(371, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(691, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(614, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(666, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(605, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(740, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(692, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(755, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(363, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(817, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(455, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(830, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(572, "NOPARALLEL");
		IElementType KEYWORD_STRING = new PlSqlTokenType(802, "STRING");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(439, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(425, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(760, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(603, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(495, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(426, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(772, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(765, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(378, "SYNONYM");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(810, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(512, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(513, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(849, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(578, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(699, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(434, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(607, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(742, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(856, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(791, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(815, "FIELDS");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(839, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(372, "INDEX");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(540, "BITMAP");
		IElementType KEYWORD_STA = new PlSqlTokenType(445, "STA");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(747, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(645, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(500, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(394, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(400, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(466, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(728, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(580, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(427, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(644, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(608, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(667, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(599, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(749, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(465, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(461, "TEMPFILE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(843, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(825, "DATE_FORMAT");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(616, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(657, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(811, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(836, "LOBFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(391, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(560, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(702, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(484, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(831, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(564, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(853, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(680, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(401, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(701, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(459, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(789, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(551, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(622, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(629, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(682, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(510, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(744, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(648, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(855, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(594, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(522, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(569, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(703, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(717, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(838, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(600, "GROUPS");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(365, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(722, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(453, "CREATE");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(432, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(410, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(620, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(604, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(541, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(446, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(404, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(715, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(707, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(823, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(819, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(804, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(576, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(568, "INITRANS");
		IElementType KEYWORD_LINK = new PlSqlTokenType(385, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(642, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(801, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(482, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(723, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(842, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(785, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(695, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(807, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(714, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(437, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(752, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(530, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(656, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(712, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(496, "END");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(386, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(655, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(418, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(504, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(547, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(472, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(458, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(833, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(792, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(850, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(773, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(589, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(382, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(846, "TERMINATED");
		IElementType KEYWORD_OLD = new PlSqlTokenType(535, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(595, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(525, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(784, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(462, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(694, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(494, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(412, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(845, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(798, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(423, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(813, "SKIP");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(543, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(370, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(587, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(460, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(841, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(668, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(750, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(548, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(582, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(827, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(456, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(800, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(478, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(640, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(609, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(399, "DEFAULT");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(469, "MANAGEMENT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(734, "DENSE_RANK");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(854, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(591, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(457, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(517, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(619, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(736, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(786, "SHARE");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(508, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(768, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(820, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(653, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(389, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(420, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(647, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(775, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(430, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(690, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(450, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(697, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(503, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(766, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(433, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(528, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(474, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(559, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(477, "GROUP");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(778, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(380, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(696, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(398, "STORAGE");

	TokenSet KEYWORDS = TokenSet.create(
		KEYWORD_USING,
		KEYWORD_ERROR_INDEX,
		//KEYWORD_STORE,
		KEYWORD_NESTED,
		//KEYWORD_REFERENCING,
		KEYWORD_FIPSFLAG,
		KEYWORD_EXTERNAL,
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
		//KEYWORD_NOLOGGING,
		KEYWORD_TIMEZONE_HOUR,
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
		KEYWORD_AS,
		KEYWORD_AUDIT,
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
		KEYWORD_718,
		//KEYWORD_LIMIT,
		//KEYWORD_INCREMENT,
		KEYWORD_COALESCE,
		KEYWORD_FINAL,
		KEYWORD_FETCH,
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
		KEYWORD_CONSTRAINTS,
		KEYWORD_UNDER,
		//KEYWORD_CHARSET,
		//KEYWORD_OPTIONALLY,
		KEYWORD_DECLARE,
		KEYWORD_PRAGMA,
		KEYWORD_READS,
		KEYWORD_DBTIMEZONE,
		KEYWORD_SYSTEM,
		KEYWORD_LEADING,
		KEYWORD_626,
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
