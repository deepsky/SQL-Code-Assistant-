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
		IElementType KEYWORD_USING = new PlSqlTokenType(405, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(661, "ERROR_INDEX");
		IElementType KEYWORD_STORE = new PlSqlTokenType(549, "STORE");
		IElementType KEYWORD_NESTED = new PlSqlTokenType(548, "NESTED");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(538, "REFERENCING");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(637, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(591, "EXTERNAL");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(785, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(569, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(692, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(841, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(831, "MISSING");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(419, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(677, "TIME");
		IElementType KEYWORD_OVER = new PlSqlTokenType(745, "OVER");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(810, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(415, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(384, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(522, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(771, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(665, "ELSE");
		IElementType KEYWORD_HOST = new PlSqlTokenType(445, "HOST");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(576, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(792, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(668, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(739, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(568, "NOCOMPRESS");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(528, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(423, "SYSDATE");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(587, "NOVALIDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(640, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(537, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(370, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(812, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(709, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(743, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(422, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(566, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(646, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(791, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(518, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(730, "SQLERRM");
		IElementType KEYWORD_NOGUARANTEE = new PlSqlTokenType(479, "NOGUARANTEE");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(593, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(784, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(829, "PREPROCESSOR");
		IElementType KEYWORD_PCTVERSION = new PlSqlTokenType(556, "PCTVERSION");
		IElementType KEYWORD_WRITE = new PlSqlTokenType(491, "WRITE");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(641, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(723, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(770, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(520, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(728, "MEMBER");
		IElementType KEYWORD_SID = new PlSqlTokenType(769, "SID");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(442, "DEFINE");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(634, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(586, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(688, "INTEGER");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(774, "JOIN");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(741, "HOUR");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(382, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(524, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(624, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(541, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(493, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(377, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(386, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(444, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(724, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(780, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(380, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(678, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(818, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(365, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(618, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(367, "DROP");
		IElementType KEYWORD_NORMAL = new PlSqlTokenType(487, "NORMAL");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(708, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(736, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(511, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(426, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(652, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(742, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(822, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(418, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(432, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(395, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(435, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(690, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(452, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(495, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(564, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(808, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(584, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(666, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(393, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(681, "DAY");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(763, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(536, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(674, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(515, "CONNECT");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(483, "NOLOGGING");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(750, "TIMEZONE_HOUR");
		IElementType KEYWORD_SET = new PlSqlTokenType(425, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(428, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(827, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(527, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(391, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(588, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(735, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(398, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(715, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(412, "DISABLE");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(396, "TYPES");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(733, "TRIM");
		IElementType KEYWORD_ALL = new PlSqlTokenType(565, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(574, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(824, "NODISCARDFILE");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(639, "CONSTANT");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(691, "PRECISION");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(793, "ORACLE_LOADER");
		IElementType KEYWORD_UNIFORM = new PlSqlTokenType(475, "UNIFORM");
		IElementType KEYWORD_AT = new PlSqlTokenType(722, "AT");
		IElementType KEYWORD_AS = new PlSqlTokenType(540, "AS");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(525, "AUDIT");
		IElementType KEYWORD_CELL_FLASH_CACHE = new PlSqlTokenType(609, "CELL_FLASH_CACHE");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(766, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(371, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(451, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(714, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(804, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(734, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(837, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(484, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(509, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(364, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(807, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(533, "OF");
		IElementType KEYWORD_RANK = new PlSqlTokenType(737, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(673, "BYTE");
		IElementType KEYWORD_RESET = new PlSqlTokenType(768, "RESET");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(490, "ONLY");
		IElementType KEYWORD_ON = new PlSqlTokenType(409, "ON");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(369, "PURGE");
		IElementType KEYWORD_721 = new PlSqlTokenType(721, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(595, "LIMIT");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(510, "INCREMENT");
		IElementType KEYWORD_COALESCE = new PlSqlTokenType(502, "COALESCE");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(621, "FINAL");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(653, "FETCH");
		IElementType KEYWORD_OR = new PlSqlTokenType(458, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(854, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(519, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(414, "ROW");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(400, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(809, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(513, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(814, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(765, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(664, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(680, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(806, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(523, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(408, "COMMENT");
		IElementType KEYWORD_CREATION = new PlSqlTokenType(560, "CREATION");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(580, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(729, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(649, "MERGE");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(416, "CONSTRAINT");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(589, "PCTTHRESHOLD");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(446, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(778, "ROWNUM");
		IElementType KEYWORD_AUTOEXTEND = new PlSqlTokenType(480, "AUTOEXTEND");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(848, "ZONED");
		IElementType KEYWORD_NULL = new PlSqlTokenType(406, "NULL");
		IElementType KEYWORD_BACKUP = new PlSqlTokenType(501, "BACKUP");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(657, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(719, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(860, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(654, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(825, "DISCARDFILE");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(376, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(535, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(752, "TIMEZONE_REGION");
		IElementType KEYWORD_LAST = new PlSqlTokenType(783, "LAST");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(663, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(682, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(716, "SAVE");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(858, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(672, "CHAR");
		IElementType KEYWORD_SEGMENT = new PlSqlTokenType(559, "SEGMENT");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(776, "WHERE");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(378, "TYPE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(631, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(667, "PRIOR");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(530, "REVOKE");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(599, "MAXEXTENTS");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(578, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(447, "SPOOL");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(542, "WHEN");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(417, "PRIMARY");
		IElementType KEYWORD_VALUE = new PlSqlTokenType(552, "VALUE");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(613, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(440, "NONE");
		IElementType KEYWORD_FREEPOOLS = new PlSqlTokenType(557, "FREEPOOLS");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(787, "RETURNING");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(506, "CYCLE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(505, "MINVALUE");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(570, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(761, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(755, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(687, "INT");
		IElementType KEYWORD_DATAFILE = new PlSqlTokenType(467, "DATAFILE");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(662, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(547, "ROWS");
		IElementType KEYWORD_PERMANENT = new PlSqlTokenType(492, "PERMANENT");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(760, "INTERSECT");
		IElementType KEYWORD_DATAFILES = new PlSqlTokenType(496, "DATAFILES");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(577, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(453, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(826, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(616, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(439, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(749, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(497, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(485, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(642, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(684, "REAL");
		IElementType KEYWORD_EXTENT = new PlSqlTokenType(472, "EXTENT");
		IElementType KEYWORD_IF = new PlSqlTokenType(644, "IF");
		IElementType KEYWORD_RETENTION = new PlSqlTokenType(477, "RETENTION");
		IElementType KEYWORD_READ = new PlSqlTokenType(489, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(573, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(582, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(727, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(410, "IS");
		IElementType KEYWORD_REUSE = new PlSqlTokenType(471, "REUSE");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(844, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(626, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(764, "INTO");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(614, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(635, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(835, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(558, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(387, "DATABASE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(424, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(474, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(655, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(852, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(786, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(627, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(781, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(604, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(379, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(390, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(531, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(605, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(601, "FREELISTS");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(675, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(679, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(710, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(628, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(696, "NVARCHAR");
		IElementType KEYWORD_CONTENTS = new PlSqlTokenType(494, "CONTENTS");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(839, "WHITESPACE");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(372, "CONSTRAINTS");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(620, "UNDER");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(707, "CHARSET");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(840, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(455, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(633, "PRAGMA");
		IElementType KEYWORD_READS = new PlSqlTokenType(555, "READS");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(747, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(399, "SYSTEM");
		IElementType KEYWORD_DECODE = new PlSqlTokenType(732, "DECODE");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(754, "LEADING");
		IElementType KEYWORD_629 = new PlSqlTokenType(629, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(689, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(821, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(600, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(638, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(545, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(615, "SORT");
		IElementType KEYWORD_MINIMUM = new PlSqlTokenType(503, "MINIMUM");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(794, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(468, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(701, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(811, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(450, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(596, "UNLIMITED");
		IElementType KEYWORD_DEFERRED = new PlSqlTokenType(561, "DEFERRED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(850, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(676, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(703, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(630, "TIMESTAMP");
		IElementType KEYWORD_LOB = new PlSqlTokenType(553, "LOB");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(758, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(636, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(433, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(711, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(374, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(694, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(617, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(669, "BINARY_INTEGER");
		IElementType KEYWORD_FLASH_CACHE = new PlSqlTokenType(608, "FLASH_CACHE");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(744, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(695, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(762, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(366, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(832, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(459, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(849, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(575, "NOPARALLEL");
		IElementType KEYWORD_STRING = new PlSqlTokenType(817, "STRING");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(443, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(429, "VARIABLE");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(767, "SHARED_POOL");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(606, "KEEP");
		IElementType KEYWORD_TO = new PlSqlTokenType(499, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(430, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(756, "BOTH");
		IElementType KEYWORD_INNER = new PlSqlTokenType(772, "INNER");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(381, "SYNONYM");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(797, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(516, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(517, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(856, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(581, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(702, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(438, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(610, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(746, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(845, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(796, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(830, "FIELDS");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(803, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(375, "INDEX");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(543, "BITMAP");
		IElementType KEYWORD_STA = new PlSqlTokenType(448, "STA");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(751, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(648, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(504, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(397, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(403, "COST");
		IElementType KEYWORD_SIZE = new PlSqlTokenType(470, "SIZE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(731, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(583, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(431, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(647, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(611, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(670, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(602, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(753, "TIMEZONE_ABBR");
		IElementType KEYWORD_MAXSIZE = new PlSqlTokenType(469, "MAXSIZE");
		IElementType KEYWORD_TEMPFILE = new PlSqlTokenType(465, "TEMPFILE");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(800, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(855, "DATE_FORMAT");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(619, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(660, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(798, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(836, "LOBFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(394, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(563, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(705, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(488, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(851, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(567, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(801, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(683, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(404, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(704, "OUT");
		IElementType KEYWORD_SMALLFILE = new PlSqlTokenType(463, "SMALLFILE");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(859, "AGGREGATE");
		IElementType KEYWORD_CHUNK = new PlSqlTokenType(554, "CHUNK");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(625, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(632, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(685, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(514, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(748, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(651, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(833, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(597, "INITIAL");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(526, "NOAUDIT");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(572, "MAXTRANS");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(706, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(720, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(802, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(603, "GROUPS");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(368, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(725, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(457, "CREATE");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(436, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(411, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(623, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(607, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(544, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(449, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(407, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(718, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(713, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(846, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(842, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(819, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(579, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(571, "INITRANS");
		IElementType KEYWORD_LINK = new PlSqlTokenType(388, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(645, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(816, "NOCHECK");
		IElementType KEYWORD_OFFLINE = new PlSqlTokenType(486, "OFFLINE");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(726, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(799, "VERSION");
		IElementType KEYWORD_MODE = new PlSqlTokenType(789, "MODE");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(698, "CHARACTER");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(823, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(717, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(441, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(759, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(534, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(659, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(712, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(500, "END");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(389, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(658, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(420, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(508, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(550, "RETURN");
		IElementType KEYWORD_DICTIONARY = new PlSqlTokenType(476, "DICTIONARY");
		IElementType KEYWORD_BIGFILE = new PlSqlTokenType(462, "BIGFILE");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(847, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(795, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(834, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(777, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(592, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(385, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(838, "TERMINATED");
		IElementType KEYWORD_OLD = new PlSqlTokenType(539, "OLD");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(598, "MINEXTENTS");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(529, "GRANT");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(788, "TRANSACTION");
		IElementType KEYWORD_UNDO = new PlSqlTokenType(466, "UNDO");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(697, "NVARCHAR2");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(498, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(413, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(857, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(813, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(427, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(828, "SKIP");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(546, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(373, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(590, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(464, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(805, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(671, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(757, "WORK");
		IElementType KEYWORD_LOCATOR = new PlSqlTokenType(551, "LOCATOR");
		IElementType KEYWORD_HASH = new PlSqlTokenType(585, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(853, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(460, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(815, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(482, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(643, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(612, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(402, "DEFAULT");
		IElementType KEYWORD_MANAGEMENT = new PlSqlTokenType(473, "MANAGEMENT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(738, "DENSE_RANK");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(820, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(594, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(461, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(521, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(622, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(740, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(790, "SHARE");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(512, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(775, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(843, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(656, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(392, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(421, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(650, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(779, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(434, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(693, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(454, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(700, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(507, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(773, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(437, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(532, "INSTEAD");
		IElementType KEYWORD_GUARANTEE = new PlSqlTokenType(478, "GUARANTEE");
		IElementType KEYWORD_CLUSTER = new PlSqlTokenType(562, "CLUSTER");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(481, "GROUP");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(782, "FIRST");
		IElementType KEYWORD_USER = new PlSqlTokenType(383, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(699, "ROWID");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(401, "STORAGE");

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
		KEYWORD_721,
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
		KEYWORD_DECODE,
		KEYWORD_LEADING,
		KEYWORD_629,
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
