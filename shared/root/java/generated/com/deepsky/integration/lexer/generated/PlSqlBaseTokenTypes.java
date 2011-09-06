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
		IElementType KEYWORD_USING = new PlSqlTokenType(396, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(610, "ERROR_INDEX");
		IElementType KEYWORD_STORE = new PlSqlTokenType(521, "STORE");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(486, "REFERENCING");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(586, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(534, "EXTERNAL");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(736, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(512, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(641, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(773, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(771, "MISSING");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(411, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(626, "TIME");
		IElementType KEYWORD_OVER = new PlSqlTokenType(694, "OVER");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(751, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(408, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(375, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(469, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(718, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(614, "ELSE");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(516, "MONITORING");
		IElementType KEYWORD_HOST = new PlSqlTokenType(435, "HOST");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(743, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(617, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(688, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(511, "NOCOMPRESS");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(529, "NOVALIDATE");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(475, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(402, "SYSDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(589, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(485, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(361, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(752, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(658, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(692, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(491, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(509, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(595, "RAISE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(742, "EXCLUSIVE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(465, "BEFORE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(680, "SQLERRM");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(536, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(735, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(792, "PREPROCESSOR");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(590, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(673, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(717, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(467, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(678, "MEMBER");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(432, "DEFINE");
		IElementType KEYWORD_SID = new PlSqlTokenType(716, "SID");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(583, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(528, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(637, "INTEGER");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(690, "HOUR");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(721, "JOIN");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(373, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(471, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(569, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(489, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(531, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(368, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(377, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(434, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(674, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(731, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(371, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(627, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(758, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(356, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(562, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(358, "DROP");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(657, "EXCEPTION");
		IElementType KEYWORD_LEAD = new PlSqlTokenType(685, "LEAD");
		IElementType KEYWORD_BY = new PlSqlTokenType(457, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(416, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(601, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(691, "ANY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(761, "NOBADFILE");
		IElementType KEYWORD_KEY = new PlSqlTokenType(410, "KEY");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(422, "EXECUTE");
		IElementType KEYWORD_PACKAGES = new PlSqlTokenType(386, "PACKAGES");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(425, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(639, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(442, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(672, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(507, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(749, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(526, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(615, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(384, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(630, "DAY");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(709, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(484, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(623, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(461, "CONNECT");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(505, "NOLOGGING");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(699, "TIMEZONE_HOUR");
		IElementType KEYWORD_SET = new PlSqlTokenType(415, "SET");
		IElementType KEYWORD_VAR = new PlSqlTokenType(418, "VAR");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(769, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(474, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(382, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(530, "ORGANIZATION");
		IElementType KEYWORD_LAG = new PlSqlTokenType(684, "LAG");
		IElementType KEYWORD_INDEXTYPES = new PlSqlTokenType(389, "INDEXTYPES");
		IElementType KEYWORD_NAME = new PlSqlTokenType(661, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(405, "DISABLE");
		IElementType KEYWORD_TYPES = new PlSqlTokenType(387, "TYPES");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(682, "TRIM");
		IElementType KEYWORD_ALL = new PlSqlTokenType(508, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(502, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(763, "NODISCARDFILE");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(588, "CONSTANT");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(640, "PRECISION");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(806, "ORACLE_LOADER");
		IElementType KEYWORD_AT = new PlSqlTokenType(671, "AT");
		IElementType KEYWORD_AS = new PlSqlTokenType(488, "AS");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(472, "AUDIT");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(713, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(362, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(438, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(660, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(795, "DISABLED");
		IElementType KEYWORD_MULTISET = new PlSqlTokenType(683, "MULTISET");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(779, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(555, "NO");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(455, "NOCACHE");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(355, "PACKAGE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(750, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(481, "OF");
		IElementType KEYWORD_RANK = new PlSqlTokenType(686, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(622, "BYTE");
		IElementType KEYWORD_ON = new PlSqlTokenType(400, "ON");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(576, "ONLY");
		IElementType KEYWORD_RESET = new PlSqlTokenType(715, "RESET");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(360, "PURGE");
		IElementType KEYWORD_670 = new PlSqlTokenType(670, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(538, "LIMIT");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(456, "INCREMENT");
		IElementType KEYWORD_FINAL = new PlSqlTokenType(566, "FINAL");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(602, "FETCH");
		IElementType KEYWORD_OR = new PlSqlTokenType(448, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(783, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(466, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(407, "ROW");
		IElementType KEYWORD_MANAGED = new PlSqlTokenType(391, "MANAGED");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(748, "NEWLINE");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(459, "NOORDER");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(754, "ENDIAN");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(712, "SESSION");
		IElementType KEYWORD_THEN = new PlSqlTokenType(613, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(629, "MONTH");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(745, "RECORDS");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(470, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(399, "COMMENT");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(520, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(679, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(598, "MERGE");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(532, "PCTTHRESHOLD");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(413, "CONSTRAINT");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(436, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(728, "ROWNUM");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(787, "ZONED");
		IElementType KEYWORD_NULL = new PlSqlTokenType(397, "NULL");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(606, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(668, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(777, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(603, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(764, "DISCARDFILE");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(367, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(483, "INSERT");
		IElementType KEYWORD_TIMEZONE_REGION = new PlSqlTokenType(701, "TIMEZONE_REGION");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(612, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(631, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(665, "SAVE");
		IElementType KEYWORD_LAST = new PlSqlTokenType(734, "LAST");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(789, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(621, "CHAR");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(369, "TYPE");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(723, "WHERE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(579, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(616, "PRIOR");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(543, "MAXEXTENTS");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(478, "REVOKE");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(518, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(437, "SPOOL");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(490, "WHEN");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(409, "PRIMARY");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(556, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(430, "NONE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(451, "MINVALUE");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(452, "CYCLE");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(738, "RETURNING");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(513, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(707, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(725, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(636, "INT");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(611, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(498, "ROWS");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(706, "INTERSECT");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(517, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(443, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(767, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(560, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(429, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(698, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(557, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(500, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(591, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(633, "REAL");
		IElementType KEYWORD_IF = new PlSqlTokenType(593, "IF");
		IElementType KEYWORD_READ = new PlSqlTokenType(575, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(501, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(524, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(677, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(401, "IS");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(776, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(572, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(710, "INTO");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(558, "MODIFY");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(584, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(790, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(522, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(378, "DATABASE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(403, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(564, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(604, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(781, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(737, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(573, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(732, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(548, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(370, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(381, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(479, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(549, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(545, "FREELISTS");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(624, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(628, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(662, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(574, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(645, "NVARCHAR");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(802, "WHITESPACE");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(363, "CONSTRAINTS");
		IElementType KEYWORD_UNDER = new PlSqlTokenType(565, "UNDER");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(656, "CHARSET");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(803, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(445, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(582, "PRAGMA");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(696, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(390, "SYSTEM");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(724, "LEADING");
		IElementType KEYWORD_577 = new PlSqlTokenType(577, "VIEW_COLUMN_DEF_$INTERNAL$"); // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(638, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(760, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(544, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(587, "EXCEPTION_INIT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(494, "DESC");
		IElementType KEYWORD_SORT = new PlSqlTokenType(559, "SORT");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(807, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(541, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(650, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(799, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(441, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(539, "UNLIMITED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(784, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(625, "DATE");
		IElementType KEYWORD_BFILE = new PlSqlTokenType(652, "BFILE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(578, "TIMESTAMP");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(704, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(585, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(423, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(663, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(365, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(643, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(561, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(618, "BINARY_INTEGER");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(693, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(644, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(708, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(357, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(772, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(449, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(785, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(503, "NOPARALLEL");
		IElementType KEYWORD_STRING = new PlSqlTokenType(757, "STRING");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(433, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(419, "VARIABLE");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(550, "KEEP");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(714, "SHARED_POOL");
		IElementType KEYWORD_TO = new PlSqlTokenType(462, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(420, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(726, "BOTH");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(372, "SYNONYM");
		IElementType KEYWORD_INNER = new PlSqlTokenType(719, "INNER");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(765, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(463, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(464, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(804, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(523, "VALUES");
		IElementType KEYWORD_NCLOB = new PlSqlTokenType(651, "NCLOB");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(428, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(552, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(695, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(811, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(746, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(770, "FIELDS");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(794, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(366, "INDEX");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(492, "BITMAP");
		IElementType KEYWORD_STA = new PlSqlTokenType(439, "STA");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(700, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(597, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(450, "MAXVALUE");
		IElementType KEYWORD_INDEXES = new PlSqlTokenType(388, "INDEXES");
		IElementType KEYWORD_COST = new PlSqlTokenType(394, "COST");
		IElementType KEYWORD_CAST = new PlSqlTokenType(681, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(525, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(421, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(596, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(553, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(619, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(546, "FREELIST");
		IElementType KEYWORD_TIMEZONE_ABBR = new PlSqlTokenType(702, "TIMEZONE_ABBR");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(798, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(780, "DATE_FORMAT");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(563, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(609, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(766, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(791, "LOBFILE");
		IElementType KEYWORD_FUNCTIONS = new PlSqlTokenType(385, "FUNCTIONS");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(506, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(654, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(711, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(786, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(510, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(808, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(632, "SMALLINT");
		IElementType KEYWORD_SELECTIVITY = new PlSqlTokenType(395, "SELECTIVITY");
		IElementType KEYWORD_OUT = new PlSqlTokenType(653, "OUT");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(744, "AGGREGATE");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(570, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(580, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(634, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(460, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(697, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(600, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(810, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(540, "INITIAL");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(515, "MAXTRANS");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(473, "NOAUDIT");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(655, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(669, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(793, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(547, "GROUPS");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(359, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(675, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(447, "CREATE");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(426, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(404, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(568, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(551, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(493, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(440, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(398, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(667, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(659, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(778, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(774, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(759, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(519, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(514, "INITRANS");
		IElementType KEYWORD_LINK = new PlSqlTokenType(379, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(594, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(756, "NOCHECK");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(676, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(797, "VERSION");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(647, "CHARACTER");
		IElementType KEYWORD_MODE = new PlSqlTokenType(740, "MODE");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(762, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(666, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(431, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(705, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(482, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(608, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(664, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(581, "END");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(380, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(607, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(412, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(454, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(571, "RETURN");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(788, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(747, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(805, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(727, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(535, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(376, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(801, "TERMINATED");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(542, "MINEXTENTS");
		IElementType KEYWORD_OLD = new PlSqlTokenType(487, "OLD");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(476, "GRANT");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(646, "NVARCHAR2");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(739, "TRANSACTION");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(477, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(406, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(800, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(753, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(417, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(768, "SKIP");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(497, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(364, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(533, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(499, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(796, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(620, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(703, "WORK");
		IElementType KEYWORD_HASH = new PlSqlTokenType(527, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(782, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(495, "GLOBAL");
		IElementType KEYWORD_MARK = new PlSqlTokenType(755, "MARK");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(504, "LOGGING");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(592, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(554, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(393, "DEFAULT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(687, "DENSE_RANK");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(809, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(537, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(496, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(468, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(567, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(689, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(741, "SHARE");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(458, "ORDER");
		IElementType KEYWORD_FULL = new PlSqlTokenType(722, "FULL");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(775, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(605, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(383, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(414, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(599, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(729, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(424, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(642, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(444, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(649, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(453, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(720, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(427, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(480, "INSTEAD");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(730, "GROUP");
		IElementType KEYWORD_USER = new PlSqlTokenType(374, "USER");
		IElementType KEYWORD_ROWID = new PlSqlTokenType(648, "ROWID");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(733, "FIRST");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(392, "STORAGE");

	TokenSet KEYWORDS = TokenSet.create(
		KEYWORD_USING,
		KEYWORD_ERROR_INDEX,
		KEYWORD_STORE,
		KEYWORD_REFERENCING,
		KEYWORD_FIPSFLAG,
		KEYWORD_EXTERNAL,
		KEYWORD_WAIT,
		KEYWORD_PCTFREE,
		KEYWORD_FLOAT,
		KEYWORD_LRTRIM,
		KEYWORD_MISSING,
		KEYWORD_REFERENCES,
		KEYWORD_TIME,
		KEYWORD_OVER,
		KEYWORD_CHARACTERSET,
		KEYWORD_MOVEMENT,
		KEYWORD_ROLE,
		KEYWORD_LOGON,
		KEYWORD_RIGHT,
		KEYWORD_ELSE,
		KEYWORD_MONITORING,
		KEYWORD_HOST,
		KEYWORD_SAVEPOINT,
		KEYWORD_NUMBER,
		KEYWORD_EXTRACT,
		KEYWORD_NOCOMPRESS,
		KEYWORD_NOVALIDATE,
		KEYWORD_DIASSOCIATE,
		KEYWORD_SYSDATE,
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
		KEYWORD_INSTANCES,
		KEYWORD_NOWAIT,
		KEYWORD_PREPROCESSOR,
		KEYWORD_LOOP,
		KEYWORD_CURRENT,
		KEYWORD_LEFT,
		KEYWORD_SHUTDOWN,
		KEYWORD_MEMBER,
		KEYWORD_DEFINE,
		KEYWORD_SID,
		KEYWORD_RESTRICT_REFERENCES,
		KEYWORD_PARTITIONS,
		KEYWORD_INTEGER,
		KEYWORD_HOUR,
		KEYWORD_JOIN,
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
		KEYWORD_COLLECT,
		KEYWORD_UPDATE,
		KEYWORD_RAW,
		KEYWORD_CONNECT,
		KEYWORD_NOLOGGING,
		KEYWORD_TIMEZONE_HOUR,
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
		KEYWORD_TYPES,
		KEYWORD_TRIM,
		KEYWORD_ALL,
		KEYWORD_PARALLEL,
		KEYWORD_NODISCARDFILE,
		KEYWORD_CONSTANT,
		KEYWORD_PRECISION,
		KEYWORD_ORACLE_LOADER,
		KEYWORD_AT,
		KEYWORD_AS,
		KEYWORD_AUDIT,
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
		KEYWORD_RANK,
		KEYWORD_BYTE,
		KEYWORD_ON,
		KEYWORD_ONLY,
		KEYWORD_RESET,
		KEYWORD_PURGE,
		KEYWORD_670,
		KEYWORD_LIMIT,
		KEYWORD_INCREMENT,
		KEYWORD_FINAL,
		KEYWORD_FETCH,
		KEYWORD_OR,
		KEYWORD_VARRAWC,
		KEYWORD_STARTUP,
		KEYWORD_ROW,
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
		KEYWORD_INTERVAL,
		KEYWORD_SQLCODE,
		KEYWORD_MERGE,
		KEYWORD_PCTTHRESHOLD,
		KEYWORD_CONSTRAINT,
		KEYWORD_QUIT,
		KEYWORD_ROWNUM,
		KEYWORD_ZONED,
		KEYWORD_NULL,
		KEYWORD_ROWCOUNT,
		KEYWORD_TRUE,
		KEYWORD_LDTRIM,
		KEYWORD_SQL,
		KEYWORD_DISCARDFILE,
		KEYWORD_FORCE,
		KEYWORD_INSERT,
		KEYWORD_TIMEZONE_REGION,
		KEYWORD_COUNT,
		KEYWORD_SECOND,
		KEYWORD_SAVE,
		KEYWORD_LAST,
		KEYWORD_LOCATION,
		KEYWORD_CHAR,
		KEYWORD_TYPE,
		KEYWORD_WHERE,
		KEYWORD_AUTHID,
		KEYWORD_PRIOR,
		KEYWORD_MAXEXTENTS,
		KEYWORD_REVOKE,
		KEYWORD_PARTITION,
		KEYWORD_SPOOL,
		KEYWORD_WHEN,
		KEYWORD_PRIMARY,
		KEYWORD_ACTION,
		KEYWORD_NONE,
		KEYWORD_MINVALUE,
		KEYWORD_CYCLE,
		KEYWORD_RETURNING,
		KEYWORD_PCTUSED,
		KEYWORD_MINUS,
		KEYWORD_TRAILING,
		KEYWORD_INT,
		KEYWORD_ERROR_CODE,
		KEYWORD_ROWS,
		KEYWORD_INTERSECT,
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
		KEYWORD_IF,
		KEYWORD_READ,
		KEYWORD_COMPUTE,
		KEYWORD_LESS,
		KEYWORD_BETWEEN,
		KEYWORD_IS,
		KEYWORD_RTRIM,
		KEYWORD_ROWTYPE,
		KEYWORD_INTO,
		KEYWORD_MODIFY,
		KEYWORD_INTERFACE,
		KEYWORD_CONCAT,
		KEYWORD_IN,
		KEYWORD_DATABASE,
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
		KEYWORD_BOOLEAN,
		KEYWORD_YEAR,
		KEYWORD_PIPELINED,
		KEYWORD_OPTION,
		KEYWORD_NVARCHAR,
		KEYWORD_WHITESPACE,
		KEYWORD_CONSTRAINTS,
		KEYWORD_UNDER,
		KEYWORD_CHARSET,
		KEYWORD_OPTIONALLY,
		KEYWORD_DECLARE,
		KEYWORD_PRAGMA,
		KEYWORD_DBTIMEZONE,
		KEYWORD_SYSTEM,
		KEYWORD_LEADING,
		KEYWORD_577,
		KEYWORD_PLS_INTEGER,
		KEYWORD_LOAD,
		KEYWORD_PCTINCREASE,
		KEYWORD_EXCEPTION_INIT,
		KEYWORD_DESC,
		KEYWORD_SORT,
		KEYWORD_ORACLE_DATAPUMP,
		KEYWORD_NEXT,
		KEYWORD_CLOB,
		KEYWORD_DATA,
		KEYWORD_REPFOOTER,
		KEYWORD_UNLIMITED,
		KEYWORD_ORACLE_NUMBER,
		KEYWORD_DATE,
		KEYWORD_BFILE,
		KEYWORD_TIMESTAMP,
		KEYWORD_ELSIF,
		KEYWORD_BUILTIN,
		KEYWORD_WHENEVER,
		KEYWORD_PARALLEL_ENABLE,
		KEYWORD_PROCEDURE,
		KEYWORD_VARCHAR,
		KEYWORD_REVERSE,
		KEYWORD_BINARY_INTEGER,
		KEYWORD_CURRVAL,
		KEYWORD_VARCHAR2,
		KEYWORD_BULK,
		KEYWORD_ALTER,
		KEYWORD_FIELD,
		KEYWORD_REPLACE,
		KEYWORD_ORACLE_DATE,
		KEYWORD_NOPARALLEL,
		KEYWORD_STRING,
		KEYWORD_PROMPT,
		KEYWORD_VARIABLE,
		KEYWORD_KEEP,
		KEYWORD_SHARED_POOL,
		KEYWORD_TO,
		KEYWORD_COL,
		KEYWORD_BOTH,
		KEYWORD_SYNONYM,
		KEYWORD_INNER,
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
		KEYWORD_ENABLED,
		KEYWORD_INDEX,
		KEYWORD_BITMAP,
		KEYWORD_STA,
		KEYWORD_TIMEZONE_MINUTE,
		KEYWORD_SELECT,
		KEYWORD_MAXVALUE,
		KEYWORD_INDEXES,
		KEYWORD_COST,
		KEYWORD_CAST,
		KEYWORD_THAN,
		KEYWORD_EXEC,
		KEYWORD_CASE,
		KEYWORD_FOREIGN,
		KEYWORD_NATURAL,
		KEYWORD_FREELIST,
		KEYWORD_TIMEZONE_ABBR,
		KEYWORD_COMPATIBLE,
		KEYWORD_DATE_FORMAT,
		KEYWORD_NOVISIBLE,
		KEYWORD_BULK_EXCEPTIONS,
		KEYWORD_LOGFILE,
		KEYWORD_LOBFILE,
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
		KEYWORD_AGGREGATE,
		KEYWORD_CURSOR,
		KEYWORD_WRAPPED,
		KEYWORD_NUMERIC,
		KEYWORD_FOR,
		KEYWORD_DISTINCT,
		KEYWORD_OPEN,
		KEYWORD_ARE,
		KEYWORD_INITIAL,
		KEYWORD_MAXTRANS,
		KEYWORD_NOAUDIT,
		KEYWORD_ANY_CS,
		KEYWORD_FALSE,
		KEYWORD_COMPRESSION,
		KEYWORD_GROUPS,
		KEYWORD_TABLE,
		KEYWORD_LIKE,
		KEYWORD_CREATE,
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
		KEYWORD_LINK,
		KEYWORD_GOTO,
		KEYWORD_NOCHECK,
		KEYWORD_ESCAPE,
		KEYWORD_VERSION,
		KEYWORD_CHARACTER,
		KEYWORD_MODE,
		KEYWORD_BADFILE,
		KEYWORD_EXCEPTIONS,
		KEYWORD_DEF,
		KEYWORD_UNION,
		KEYWORD_DELETE,
		KEYWORD_BULK_ROWCOUNT,
		KEYWORD_DETERMINISTIC,
		KEYWORD_END,
		KEYWORD_TRIGGER,
		KEYWORD_ISOPEN,
		KEYWORD_RELY,
		KEYWORD_CACHE,
		KEYWORD_RETURN,
		KEYWORD_UNSIGNED,
		KEYWORD_ACCESS,
		KEYWORD_TRANSFORMS,
		KEYWORD_CURRENT_TIMESTAMP,
		KEYWORD_DEGREE,
		KEYWORD_DIRECTORY,
		KEYWORD_TERMINATED,
		KEYWORD_MINEXTENTS,
		KEYWORD_OLD,
		KEYWORD_GRANT,
		KEYWORD_NVARCHAR2,
		KEYWORD_TRANSACTION,
		KEYWORD_RENAME,
		KEYWORD_ENABLE,
		KEYWORD_MASK,
		KEYWORD_LITTLE,
		KEYWORD_SHOW,
		KEYWORD_SKIP,
		KEYWORD_PRESERVE,
		KEYWORD_FUNCTION,
		KEYWORD_HEAP,
		KEYWORD_TABLESPACE,
		KEYWORD_ENCRYPTION,
		KEYWORD_POSITIVE,
		KEYWORD_WORK,
		KEYWORD_HASH,
		KEYWORD_VARCHARC,
		KEYWORD_GLOBAL,
		KEYWORD_MARK,
		KEYWORD_LOGGING,
		KEYWORD_FORALL,
		KEYWORD_RESTRICT,
		KEYWORD_DEFAULT,
		KEYWORD_DENSE_RANK,
		KEYWORD_CHARACTERS,
		KEYWORD_REJECT,
		KEYWORD_TEMPORARY,
		KEYWORD_SERVERERROR,
		KEYWORD_OBJECT,
		KEYWORD_MINUTE,
		KEYWORD_SHARE,
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
		KEYWORD_GROUP,
		KEYWORD_USER,
		KEYWORD_ROWID,
		KEYWORD_FIRST,
		KEYWORD_STORAGE,
		new PlSqlTokenType(9999, "$$$END_OF_ARRAY_$$$")
		);
}
