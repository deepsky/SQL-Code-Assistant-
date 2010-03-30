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
		IElementType ANY_CHARACTER = new PlSqlTokenType(19, "ANY_CHARACTER");
		IElementType BAD_CHARACTER = new PlSqlTokenType(5, "BAD_CHARACTER");
		IElementType ASTERISK = new PlSqlTokenType(27, "ASTERISK");
		IElementType ML_COMMENT = new PlSqlTokenType(50, "ML_COMMENT");
		IElementType THEN_COND_CMPL = new PlSqlTokenType(52, "THEN_COND_CMPL");
		IElementType COMMA = new PlSqlTokenType(26, "COMMA");
		IElementType IDENTIFIER = new PlSqlTokenType(17, "IDENTIFIER");
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
		IElementType PLSQL_ENV_VAR = new PlSqlTokenType(18, "PLSQL_ENV_VAR");
		IElementType WS = new PlSqlTokenType(47, "WS");
		IElementType END_LABEL = new PlSqlTokenType(40, "END_LABEL");
		IElementType SL_COMMENT = new PlSqlTokenType(49, "SL_COMMENT");
		IElementType GT = new PlSqlTokenType(44, "GT");
		IElementType IF_COND_CMPL = new PlSqlTokenType(51, "IF_COND_CMPL");
		IElementType COLON_NEW = new PlSqlTokenType(14, "COLON_NEW");
		IElementType LE = new PlSqlTokenType(10, "LE");
		IElementType BACKSLASH = new PlSqlTokenType(33, "BACKSLASH");
		IElementType LF = new PlSqlTokenType(48, "LF");
		IElementType KEYWORD_USING = new PlSqlTokenType(420, "USING");
		IElementType KEYWORD_ERROR_INDEX = new PlSqlTokenType(564, "ERROR_INDEX");
		IElementType KEYWORD_STORE = new PlSqlTokenType(482, "STORE");
		IElementType KEYWORD_REFERENCING = new PlSqlTokenType(447, "REFERENCING");
		IElementType KEYWORD_FIPSFLAG = new PlSqlTokenType(616, "FIPSFLAG");
		IElementType KEYWORD_EXTERNAL = new PlSqlTokenType(495, "EXTERNAL");
		IElementType KEYWORD_WAIT = new PlSqlTokenType(691, "WAIT");
		IElementType KEYWORD_PCTFREE = new PlSqlTokenType(473, "PCTFREE");
		IElementType KEYWORD_FLOAT = new PlSqlTokenType(596, "FLOAT");
		IElementType KEYWORD_LRTRIM = new PlSqlTokenType(727, "LRTRIM");
		IElementType KEYWORD_MISSING = new PlSqlTokenType(725, "MISSING");
		IElementType KEYWORD_REFERENCES = new PlSqlTokenType(370, "REFERENCES");
		IElementType KEYWORD_TIME = new PlSqlTokenType(582, "TIME");
		IElementType KEYWORD_OVER = new PlSqlTokenType(649, "OVER");
		IElementType KEYWORD_CHARACTERSET = new PlSqlTokenType(705, "CHARACTERSET");
		IElementType KEYWORD_MOVEMENT = new PlSqlTokenType(367, "MOVEMENT");
		IElementType KEYWORD_ROLE = new PlSqlTokenType(350, "ROLE");
		IElementType KEYWORD_LOGON = new PlSqlTokenType(427, "LOGON");
		IElementType KEYWORD_RIGHT = new PlSqlTokenType(673, "RIGHT");
		IElementType KEYWORD_ELSE = new PlSqlTokenType(568, "ELSE");
		IElementType KEYWORD_MONITORING = new PlSqlTokenType(477, "MONITORING");
		IElementType KEYWORD_SAVEPOINT = new PlSqlTokenType(698, "SAVEPOINT");
		IElementType KEYWORD_NUMBER = new PlSqlTokenType(575, "NUMBER");
		IElementType KEYWORD_EXTRACT = new PlSqlTokenType(653, "EXTRACT");
		IElementType KEYWORD_NOCOMPRESS = new PlSqlTokenType(472, "NOCOMPRESS");
		IElementType KEYWORD_NOVALIDATE = new PlSqlTokenType(490, "NOVALIDATE");
		IElementType KEYWORD_DIASSOCIATE = new PlSqlTokenType(435, "DIASSOCIATE");
		IElementType KEYWORD_SYSDATE = new PlSqlTokenType(361, "SYSDATE");
		IElementType KEYWORD_SUBTYPE = new PlSqlTokenType(543, "SUBTYPE");
		IElementType KEYWORD_EACH = new PlSqlTokenType(446, "EACH");
		IElementType KEYWORD_VIEW = new PlSqlTokenType(334, "VIEW");
		IElementType KEYWORD_BIG = new PlSqlTokenType(706, "BIG");
		IElementType KEYWORD_SERIALLY_REUSABLE = new PlSqlTokenType(611, "SERIALLY_REUSABLE");
		IElementType KEYWORD_NEXTVAL = new PlSqlTokenType(647, "NEXTVAL");
		IElementType KEYWORD_UNIQUE = new PlSqlTokenType(452, "UNIQUE");
		IElementType KEYWORD_DIRECT_LOAD = new PlSqlTokenType(470, "DIRECT_LOAD");
		IElementType KEYWORD_RAISE = new PlSqlTokenType(549, "RAISE");
		IElementType KEYWORD_BEFORE = new PlSqlTokenType(423, "BEFORE");
		IElementType KEYWORD_EXCLUSIVE = new PlSqlTokenType(697, "EXCLUSIVE");
		IElementType KEYWORD_SQLERRM = new PlSqlTokenType(640, "SQLERRM");
		IElementType KEYWORD_INSTANCES = new PlSqlTokenType(497, "INSTANCES");
		IElementType KEYWORD_NOWAIT = new PlSqlTokenType(690, "NOWAIT");
		IElementType KEYWORD_PREPROCESSOR = new PlSqlTokenType(746, "PREPROCESSOR");
		IElementType KEYWORD_LOOP = new PlSqlTokenType(544, "LOOP");
		IElementType KEYWORD_CURRENT = new PlSqlTokenType(632, "CURRENT");
		IElementType KEYWORD_LEFT = new PlSqlTokenType(672, "LEFT");
		IElementType KEYWORD_SHUTDOWN = new PlSqlTokenType(425, "SHUTDOWN");
		IElementType KEYWORD_MEMBER = new PlSqlTokenType(637, "MEMBER");
		IElementType KEYWORD_DEFINE = new PlSqlTokenType(389, "DEFINE");
		IElementType KEYWORD_SID = new PlSqlTokenType(671, "SID");
		IElementType KEYWORD_RESTRICT_REFERENCES = new PlSqlTokenType(613, "RESTRICT_REFERENCES");
		IElementType KEYWORD_PARTITIONS = new PlSqlTokenType(489, "PARTITIONS");
		IElementType KEYWORD_INTEGER = new PlSqlTokenType(592, "INTEGER");
		IElementType KEYWORD_HOUR = new PlSqlTokenType(645, "HOUR");
		IElementType KEYWORD_JOIN = new PlSqlTokenType(676, "JOIN");
		IElementType KEYWORD_OPERATOR = new PlSqlTokenType(348, "OPERATOR");
		IElementType KEYWORD_ANALYZE = new PlSqlTokenType(429, "ANALYZE");
		IElementType KEYWORD_REF = new PlSqlTokenType(531, "REF");
		IElementType KEYWORD_NEW = new PlSqlTokenType(450, "NEW");
		IElementType KEYWORD_INCLUDING = new PlSqlTokenType(492, "INCLUDING");
		IElementType KEYWORD_SEQUENCE = new PlSqlTokenType(343, "SEQUENCE");
		IElementType KEYWORD_LIBRARY = new PlSqlTokenType(352, "LIBRARY");
		IElementType KEYWORD_REM = new PlSqlTokenType(391, "REM");
		IElementType KEYWORD_EXISTS = new PlSqlTokenType(633, "EXISTS");
		IElementType KEYWORD_HAVING = new PlSqlTokenType(686, "HAVING");
		IElementType KEYWORD_PUBLIC = new PlSqlTokenType(346, "PUBLIC");
		IElementType KEYWORD_ZONE = new PlSqlTokenType(583, "ZONE");
		IElementType KEYWORD_SIZES = new PlSqlTokenType(712, "SIZES");
		IElementType KEYWORD_BODY = new PlSqlTokenType(340, "BODY");
		IElementType KEYWORD_VISIBLE = new PlSqlTokenType(526, "VISIBLE");
		IElementType KEYWORD_DROP = new PlSqlTokenType(331, "DROP");
		IElementType KEYWORD_EXCEPTION = new PlSqlTokenType(610, "EXCEPTION");
		IElementType KEYWORD_BY = new PlSqlTokenType(413, "BY");
		IElementType KEYWORD_LONG = new PlSqlTokenType(373, "LONG");
		IElementType KEYWORD_CLOSE = new PlSqlTokenType(555, "CLOSE");
		IElementType KEYWORD_ANY = new PlSqlTokenType(646, "ANY");
		IElementType KEYWORD_KEY = new PlSqlTokenType(369, "KEY");
		IElementType KEYWORD_NOBADFILE = new PlSqlTokenType(715, "NOBADFILE");
		IElementType KEYWORD_EXECUTE = new PlSqlTokenType(379, "EXECUTE");
		IElementType KEYWORD_OSERROR = new PlSqlTokenType(382, "OSERROR");
		IElementType KEYWORD_DOUBLE = new PlSqlTokenType(594, "DOUBLE");
		IElementType KEYWORD_REPHEADER = new PlSqlTokenType(398, "REPHEADER");
		IElementType KEYWORD_AND = new PlSqlTokenType(631, "AND");
		IElementType KEYWORD_COMPRESS = new PlSqlTokenType(468, "COMPRESS");
		IElementType KEYWORD_DELIMITED = new PlSqlTokenType(703, "DELIMITED");
		IElementType KEYWORD_OVERFLOW = new PlSqlTokenType(487, "OVERFLOW");
		IElementType KEYWORD_AUTONOMOUS_TRANSACTION = new PlSqlTokenType(570, "AUTONOMOUS_TRANSACTION");
		IElementType KEYWORD_COLUMN = new PlSqlTokenType(359, "COLUMN");
		IElementType KEYWORD_DAY = new PlSqlTokenType(586, "DAY");
		IElementType KEYWORD_COLLECT = new PlSqlTokenType(663, "COLLECT");
		IElementType KEYWORD_UPDATE = new PlSqlTokenType(444, "UPDATE");
		IElementType KEYWORD_RAW = new PlSqlTokenType(578, "RAW");
		IElementType KEYWORD_CONNECT = new PlSqlTokenType(417, "CONNECT");
		IElementType KEYWORD_NOLOGGING = new PlSqlTokenType(466, "NOLOGGING");
		IElementType KEYWORD_TIMEZONE_HOUR = new PlSqlTokenType(655, "TIMEZONE_HOUR");
		IElementType KEYWORD_VAR = new PlSqlTokenType(375, "VAR");
		IElementType KEYWORD_SET = new PlSqlTokenType(372, "SET");
		IElementType KEYWORD_DATA_CACHE = new PlSqlTokenType(723, "DATA_CACHE");
		IElementType KEYWORD_DDL = new PlSqlTokenType(434, "DDL");
		IElementType KEYWORD_STATISTICS = new PlSqlTokenType(431, "STATISTICS");
		IElementType KEYWORD_ORGANIZATION = new PlSqlTokenType(491, "ORGANIZATION");
		IElementType KEYWORD_NAME = new PlSqlTokenType(619, "NAME");
		IElementType KEYWORD_DISABLE = new PlSqlTokenType(364, "DISABLE");
		IElementType KEYWORD_TRIM = new PlSqlTokenType(641, "TRIM");
		IElementType KEYWORD_ALL = new PlSqlTokenType(469, "ALL");
		IElementType KEYWORD_PARALLEL = new PlSqlTokenType(463, "PARALLEL");
		IElementType KEYWORD_NODISCARDFILE = new PlSqlTokenType(717, "NODISCARDFILE");
		IElementType KEYWORD_CONSTANT = new PlSqlTokenType(542, "CONSTANT");
		IElementType KEYWORD_PRECISION = new PlSqlTokenType(595, "PRECISION");
		IElementType KEYWORD_ORACLE_LOADER = new PlSqlTokenType(759, "ORACLE_LOADER");
		IElementType KEYWORD_AT = new PlSqlTokenType(630, "AT");
		IElementType KEYWORD_AS = new PlSqlTokenType(449, "AS");
		IElementType KEYWORD_AUDIT = new PlSqlTokenType(432, "AUDIT");
		IElementType KEYWORD_FLUSH = new PlSqlTokenType(668, "FLUSH");
		IElementType KEYWORD_CASCADE = new PlSqlTokenType(335, "CASCADE");
		IElementType KEYWORD_OFF = new PlSqlTokenType(394, "OFF");
		IElementType KEYWORD_JAVA = new PlSqlTokenType(618, "JAVA");
		IElementType KEYWORD_DISABLED = new PlSqlTokenType(749, "DISABLED");
		IElementType KEYWORD_ENCLOSED = new PlSqlTokenType(733, "ENCLOSED");
		IElementType KEYWORD_NO = new PlSqlTokenType(518, "NO");
		IElementType KEYWORD_PACKAGE = new PlSqlTokenType(339, "PACKAGE");
		IElementType KEYWORD_NOCACHE = new PlSqlTokenType(411, "NOCACHE");
		IElementType KEYWORD_FIXED = new PlSqlTokenType(704, "FIXED");
		IElementType KEYWORD_OF = new PlSqlTokenType(441, "OF");
		IElementType KEYWORD_RANK = new PlSqlTokenType(642, "RANK");
		IElementType KEYWORD_BYTE = new PlSqlTokenType(577, "BYTE");
		IElementType KEYWORD_ON = new PlSqlTokenType(357, "ON");
		IElementType KEYWORD_ONLY = new PlSqlTokenType(538, "ONLY");
		IElementType KEYWORD_RESET = new PlSqlTokenType(670, "RESET");
		IElementType KEYWORD_PURGE = new PlSqlTokenType(333, "PURGE");
		IElementType KEYWORD_629 = new PlSqlTokenType(629, "**"); // KEYWORD_**
		IElementType KEYWORD_LIMIT = new PlSqlTokenType(499, "LIMIT");
		IElementType KEYWORD_INCREMENT = new PlSqlTokenType(412, "INCREMENT");
		IElementType KEYWORD_FETCH = new PlSqlTokenType(556, "FETCH");
		IElementType KEYWORD_OR = new PlSqlTokenType(404, "OR");
		IElementType KEYWORD_VARRAWC = new PlSqlTokenType(737, "VARRAWC");
		IElementType KEYWORD_STARTUP = new PlSqlTokenType(424, "STARTUP");
		IElementType KEYWORD_ROW = new PlSqlTokenType(366, "ROW");
		IElementType KEYWORD_NOORDER = new PlSqlTokenType(416, "NOORDER");
		IElementType KEYWORD_NEWLINE = new PlSqlTokenType(702, "NEWLINE");
		IElementType KEYWORD_SESSION = new PlSqlTokenType(667, "SESSION");
		IElementType KEYWORD_ENDIAN = new PlSqlTokenType(708, "ENDIAN");
		IElementType KEYWORD_THEN = new PlSqlTokenType(567, "THEN");
		IElementType KEYWORD_MONTH = new PlSqlTokenType(585, "MONTH");
		IElementType KEYWORD_LOGOFF = new PlSqlTokenType(428, "LOGOFF");
		IElementType KEYWORD_COMMENT = new PlSqlTokenType(356, "COMMENT");
		IElementType KEYWORD_RECORDS = new PlSqlTokenType(699, "RECORDS");
		IElementType KEYWORD_INTERVAL = new PlSqlTokenType(481, "INTERVAL");
		IElementType KEYWORD_SQLCODE = new PlSqlTokenType(639, "SQLCODE");
		IElementType KEYWORD_MERGE = new PlSqlTokenType(552, "MERGE");
		IElementType KEYWORD_PCTTHRESHOLD = new PlSqlTokenType(493, "PCTTHRESHOLD");
		IElementType KEYWORD_CONSTRAINT = new PlSqlTokenType(371, "CONSTRAINT");
		IElementType KEYWORD_QUIT = new PlSqlTokenType(392, "QUIT");
		IElementType KEYWORD_ROWNUM = new PlSqlTokenType(683, "ROWNUM");
		IElementType KEYWORD_ZONED = new PlSqlTokenType(741, "ZONED");
		IElementType KEYWORD_NULL = new PlSqlTokenType(363, "NULL");
		IElementType KEYWORD_ROWCOUNT = new PlSqlTokenType(560, "ROWCOUNT");
		IElementType KEYWORD_TRUE = new PlSqlTokenType(627, "TRUE");
		IElementType KEYWORD_LDTRIM = new PlSqlTokenType(731, "LDTRIM");
		IElementType KEYWORD_SQL = new PlSqlTokenType(557, "SQL");
		IElementType KEYWORD_DISCARDFILE = new PlSqlTokenType(718, "DISCARDFILE");
		IElementType KEYWORD_FORCE = new PlSqlTokenType(342, "FORCE");
		IElementType KEYWORD_INSERT = new PlSqlTokenType(443, "INSERT");
		IElementType KEYWORD_COUNT = new PlSqlTokenType(566, "COUNT");
		IElementType KEYWORD_SECOND = new PlSqlTokenType(587, "SECOND");
		IElementType KEYWORD_SAVE = new PlSqlTokenType(624, "SAVE");
		IElementType KEYWORD_LAST = new PlSqlTokenType(689, "LAST");
		IElementType KEYWORD_LOCATION = new PlSqlTokenType(743, "LOCATION");
		IElementType KEYWORD_CHAR = new PlSqlTokenType(576, "CHAR");
		IElementType KEYWORD_TYPE = new PlSqlTokenType(344, "TYPE");
		IElementType KEYWORD_WHERE = new PlSqlTokenType(677, "WHERE");
		IElementType KEYWORD_AUTHID = new PlSqlTokenType(539, "AUTHID");
		IElementType KEYWORD_PRIOR = new PlSqlTokenType(571, "PRIOR");
		IElementType KEYWORD_MAXEXTENTS = new PlSqlTokenType(505, "MAXEXTENTS");
		IElementType KEYWORD_REVOKE = new PlSqlTokenType(438, "REVOKE");
		IElementType KEYWORD_PARTITION = new PlSqlTokenType(479, "PARTITION");
		IElementType KEYWORD_SPOOL = new PlSqlTokenType(393, "SPOOL");
		IElementType KEYWORD_WHEN = new PlSqlTokenType(451, "WHEN");
		IElementType KEYWORD_PRIMARY = new PlSqlTokenType(368, "PRIMARY");
		IElementType KEYWORD_ACTION = new PlSqlTokenType(519, "ACTION");
		IElementType KEYWORD_NONE = new PlSqlTokenType(387, "NONE");
		IElementType KEYWORD_MINVALUE = new PlSqlTokenType(407, "MINVALUE");
		IElementType KEYWORD_CYCLE = new PlSqlTokenType(408, "CYCLE");
		IElementType KEYWORD_RETURNING = new PlSqlTokenType(693, "RETURNING");
		IElementType KEYWORD_PCTUSED = new PlSqlTokenType(474, "PCTUSED");
		IElementType KEYWORD_MINUS = new PlSqlTokenType(661, "MINUS");
		IElementType KEYWORD_TRAILING = new PlSqlTokenType(679, "TRAILING");
		IElementType KEYWORD_INT = new PlSqlTokenType(591, "INT");
		IElementType KEYWORD_ERROR_CODE = new PlSqlTokenType(565, "ERROR_CODE");
		IElementType KEYWORD_ROWS = new PlSqlTokenType(459, "ROWS");
		IElementType KEYWORD_INTERSECT = new PlSqlTokenType(660, "INTERSECT");
		IElementType KEYWORD_NOMONITORING = new PlSqlTokenType(478, "NOMONITORING");
		IElementType KEYWORD_SERVEROUTPUT = new PlSqlTokenType(399, "SERVEROUTPUT");
		IElementType KEYWORD_READSIZE = new PlSqlTokenType(721, "READSIZE");
		IElementType KEYWORD_NOSORT = new PlSqlTokenType(524, "NOSORT");
		IElementType KEYWORD_ROLLBACK = new PlSqlTokenType(386, "ROLLBACK");
		IElementType KEYWORD_FROM = new PlSqlTokenType(654, "FROM");
		IElementType KEYWORD_ADD = new PlSqlTokenType(520, "ADD");
		IElementType KEYWORD_ONLINE = new PlSqlTokenType(461, "ONLINE");
		IElementType KEYWORD_WHILE = new PlSqlTokenType(545, "WHILE");
		IElementType KEYWORD_REAL = new PlSqlTokenType(589, "REAL");
		IElementType KEYWORD_IF = new PlSqlTokenType(547, "IF");
		IElementType KEYWORD_READ = new PlSqlTokenType(537, "READ");
		IElementType KEYWORD_COMPUTE = new PlSqlTokenType(462, "COMPUTE");
		IElementType KEYWORD_LESS = new PlSqlTokenType(485, "LESS");
		IElementType KEYWORD_BETWEEN = new PlSqlTokenType(636, "BETWEEN");
		IElementType KEYWORD_IS = new PlSqlTokenType(358, "IS");
		IElementType KEYWORD_RTRIM = new PlSqlTokenType(730, "RTRIM");
		IElementType KEYWORD_ROWTYPE = new PlSqlTokenType(534, "ROWTYPE");
		IElementType KEYWORD_INTO = new PlSqlTokenType(664, "INTO");
		IElementType KEYWORD_MODIFY = new PlSqlTokenType(521, "MODIFY");
		IElementType KEYWORD_MLSLABEL = new PlSqlTokenType(603, "MLSLABEL");
		IElementType KEYWORD_INTERFACE = new PlSqlTokenType(614, "INTERFACE");
		IElementType KEYWORD_CONCAT = new PlSqlTokenType(744, "CONCAT");
		IElementType KEYWORD_IN = new PlSqlTokenType(483, "IN");
		IElementType KEYWORD_DATABASE = new PlSqlTokenType(353, "DATABASE");
		IElementType KEYWORD_SYSTIMESTAMP = new PlSqlTokenType(681, "SYSTIMESTAMP");
		IElementType KEYWORD_LOCAL = new PlSqlTokenType(528, "LOCAL");
		IElementType KEYWORD_FOUND = new PlSqlTokenType(558, "FOUND");
		IElementType KEYWORD_VARRAW = new PlSqlTokenType(735, "VARRAW");
		IElementType KEYWORD_MATCHED = new PlSqlTokenType(692, "MATCHED");
		IElementType KEYWORD_VARRAY = new PlSqlTokenType(535, "VARRAY");
		IElementType KEYWORD_NULLS = new PlSqlTokenType(687, "NULLS");
		IElementType KEYWORD_OPTIMAL = new PlSqlTokenType(510, "OPTIMAL");
		IElementType KEYWORD_VALIDATE = new PlSqlTokenType(345, "VALIDATE");
		IElementType KEYWORD_ASSOCIATE = new PlSqlTokenType(430, "ASSOCIATE");
		IElementType KEYWORD_SCHEMA = new PlSqlTokenType(439, "SCHEMA");
		IElementType KEYWORD_BUFFER_POOL = new PlSqlTokenType(511, "BUFFER_POOL");
		IElementType KEYWORD_FREELISTS = new PlSqlTokenType(507, "FREELISTS");
		IElementType KEYWORD_BOOLEAN = new PlSqlTokenType(579, "BOOLEAN");
		IElementType KEYWORD_YEAR = new PlSqlTokenType(584, "YEAR");
		IElementType KEYWORD_PIPELINED = new PlSqlTokenType(620, "PIPELINED");
		IElementType KEYWORD_OPTION = new PlSqlTokenType(536, "OPTION");
		IElementType KEYWORD_NVARCHAR = new PlSqlTokenType(600, "NVARCHAR");
		IElementType KEYWORD_WHITESPACE = new PlSqlTokenType(756, "WHITESPACE");
		IElementType KEYWORD_CONSTRAINTS = new PlSqlTokenType(336, "CONSTRAINTS");
		IElementType KEYWORD_CHARSET = new PlSqlTokenType(609, "CHARSET");
		IElementType KEYWORD_OPTIONALLY = new PlSqlTokenType(757, "OPTIONALLY");
		IElementType KEYWORD_DECLARE = new PlSqlTokenType(401, "DECLARE");
		IElementType KEYWORD_PRAGMA = new PlSqlTokenType(569, "PRAGMA");
		IElementType KEYWORD_DBTIMEZONE = new PlSqlTokenType(651, "DBTIMEZONE");
		IElementType KEYWORD_SYSTEM = new PlSqlTokenType(666, "SYSTEM");
		IElementType KEYWORD_LEADING = new PlSqlTokenType(678, "LEADING");
		IElementType KEYWORD_PLS_INTEGER = new PlSqlTokenType(593, "PLS_INTEGER");
		IElementType KEYWORD_LOAD = new PlSqlTokenType(714, "LOAD");
		IElementType KEYWORD_PCTINCREASE = new PlSqlTokenType(506, "PCTINCREASE");
		IElementType KEYWORD_EXCEPTION_INIT = new PlSqlTokenType(612, "EXCEPTION_INIT");
		IElementType KEYWORD_SORT = new PlSqlTokenType(523, "SORT");
		IElementType KEYWORD_DESC = new PlSqlTokenType(455, "DESC");
		IElementType KEYWORD_ORACLE_DATAPUMP = new PlSqlTokenType(760, "ORACLE_DATAPUMP");
		IElementType KEYWORD_NEXT = new PlSqlTokenType(503, "NEXT");
		IElementType KEYWORD_CLOB = new PlSqlTokenType(605, "CLOB");
		IElementType KEYWORD_DATA = new PlSqlTokenType(753, "DATA");
		IElementType KEYWORD_REPFOOTER = new PlSqlTokenType(397, "REPFOOTER");
		IElementType KEYWORD_UNLIMITED = new PlSqlTokenType(500, "UNLIMITED");
		IElementType KEYWORD_ORACLE_NUMBER = new PlSqlTokenType(738, "ORACLE_NUMBER");
		IElementType KEYWORD_DATE = new PlSqlTokenType(580, "DATE");
		IElementType KEYWORD_TIMESTAMP = new PlSqlTokenType(581, "TIMESTAMP");
		IElementType KEYWORD_ELSIF = new PlSqlTokenType(658, "ELSIF");
		IElementType KEYWORD_BUILTIN = new PlSqlTokenType(615, "BUILTIN");
		IElementType KEYWORD_WHENEVER = new PlSqlTokenType(380, "WHENEVER");
		IElementType KEYWORD_PARALLEL_ENABLE = new PlSqlTokenType(621, "PARALLEL_ENABLE");
		IElementType KEYWORD_PROCEDURE = new PlSqlTokenType(338, "PROCEDURE");
		IElementType KEYWORD_VARCHAR = new PlSqlTokenType(598, "VARCHAR");
		IElementType KEYWORD_REVERSE = new PlSqlTokenType(525, "REVERSE");
		IElementType KEYWORD_BINARY_INTEGER = new PlSqlTokenType(572, "BINARY_INTEGER");
		IElementType KEYWORD_CURRVAL = new PlSqlTokenType(648, "CURRVAL");
		IElementType KEYWORD_VARCHAR2 = new PlSqlTokenType(599, "VARCHAR2");
		IElementType KEYWORD_BULK = new PlSqlTokenType(662, "BULK");
		IElementType KEYWORD_ALTER = new PlSqlTokenType(330, "ALTER");
		IElementType KEYWORD_FIELD = new PlSqlTokenType(726, "FIELD");
		IElementType KEYWORD_REPLACE = new PlSqlTokenType(405, "REPLACE");
		IElementType KEYWORD_ORACLE_DATE = new PlSqlTokenType(739, "ORACLE_DATE");
		IElementType KEYWORD_NOPARALLEL = new PlSqlTokenType(464, "NOPARALLEL");
		IElementType KEYWORD_STRING = new PlSqlTokenType(711, "STRING");
		IElementType KEYWORD_PROMPT = new PlSqlTokenType(390, "PROMPT");
		IElementType KEYWORD_VARIABLE = new PlSqlTokenType(376, "VARIABLE");
		IElementType KEYWORD_KEEP = new PlSqlTokenType(512, "KEEP");
		IElementType KEYWORD_SHARED_POOL = new PlSqlTokenType(669, "SHARED_POOL");
		IElementType KEYWORD_TO = new PlSqlTokenType(418, "TO");
		IElementType KEYWORD_COL = new PlSqlTokenType(377, "COL");
		IElementType KEYWORD_BOTH = new PlSqlTokenType(680, "BOTH");
		IElementType KEYWORD_SYNONYM = new PlSqlTokenType(347, "SYNONYM");
		IElementType KEYWORD_INNER = new PlSqlTokenType(674, "INNER");
		IElementType KEYWORD_NOLOGFILE = new PlSqlTokenType(719, "NOLOGFILE");
		IElementType KEYWORD_IDENTIFIED = new PlSqlTokenType(419, "IDENTIFIED");
		IElementType KEYWORD_AFTER = new PlSqlTokenType(422, "AFTER");
		IElementType KEYWORD_TIMEZONE = new PlSqlTokenType(758, "TIMEZONE");
		IElementType KEYWORD_VALUES = new PlSqlTokenType(484, "VALUES");
		IElementType KEYWORD_COMMIT = new PlSqlTokenType(385, "COMMIT");
		IElementType KEYWORD_ENCRYPT = new PlSqlTokenType(514, "ENCRYPT");
		IElementType KEYWORD_SESSIONTIMEZONE = new PlSqlTokenType(650, "SESSIONTIMEZONE");
		IElementType KEYWORD_LDRTRIM = new PlSqlTokenType(765, "LDRTRIM");
		IElementType KEYWORD_PARAMETERS = new PlSqlTokenType(700, "PARAMETERS");
		IElementType KEYWORD_FIELDS = new PlSqlTokenType(724, "FIELDS");
		IElementType KEYWORD_ENABLED = new PlSqlTokenType(748, "ENABLED");
		IElementType KEYWORD_INDEX = new PlSqlTokenType(341, "INDEX");
		IElementType KEYWORD_BITMAP = new PlSqlTokenType(453, "BITMAP");
		IElementType KEYWORD_STA = new PlSqlTokenType(395, "STA");
		IElementType KEYWORD_TIMEZONE_MINUTE = new PlSqlTokenType(656, "TIMEZONE_MINUTE");
		IElementType KEYWORD_SELECT = new PlSqlTokenType(551, "SELECT");
		IElementType KEYWORD_MAXVALUE = new PlSqlTokenType(406, "MAXVALUE");
		IElementType KEYWORD_CAST = new PlSqlTokenType(638, "CAST");
		IElementType KEYWORD_THAN = new PlSqlTokenType(486, "THAN");
		IElementType KEYWORD_EXEC = new PlSqlTokenType(378, "EXEC");
		IElementType KEYWORD_CASE = new PlSqlTokenType(550, "CASE");
		IElementType KEYWORD_FOREIGN = new PlSqlTokenType(516, "FOREIGN");
		IElementType KEYWORD_NATURAL = new PlSqlTokenType(573, "NATURAL");
		IElementType KEYWORD_FREELIST = new PlSqlTokenType(508, "FREELIST");
		IElementType KEYWORD_COMPATIBLE = new PlSqlTokenType(752, "COMPATIBLE");
		IElementType KEYWORD_DATE_FORMAT = new PlSqlTokenType(734, "DATE_FORMAT");
		IElementType KEYWORD_NOVISIBLE = new PlSqlTokenType(527, "NOVISIBLE");
		IElementType KEYWORD_BULK_EXCEPTIONS = new PlSqlTokenType(563, "BULK_EXCEPTIONS");
		IElementType KEYWORD_LOGFILE = new PlSqlTokenType(720, "LOGFILE");
		IElementType KEYWORD_LOBFILE = new PlSqlTokenType(745, "LOBFILE");
		IElementType KEYWORD_FILESYSTEM_LIKE_LOGGING = new PlSqlTokenType(467, "FILESYSTEM_LIKE_LOGGING");
		IElementType KEYWORD_NOCOPY = new PlSqlTokenType(607, "NOCOPY");
		IElementType KEYWORD_IMMEDIATE = new PlSqlTokenType(665, "IMMEDIATE");
		IElementType KEYWORD_COUNTED = new PlSqlTokenType(740, "COUNTED");
		IElementType KEYWORD_OPERATIONS = new PlSqlTokenType(471, "OPERATIONS");
		IElementType KEYWORD_LATEST = new PlSqlTokenType(761, "LATEST");
		IElementType KEYWORD_SMALLINT = new PlSqlTokenType(588, "SMALLINT");
		IElementType KEYWORD_OUT = new PlSqlTokenType(606, "OUT");
		IElementType KEYWORD_AGGREGATE = new PlSqlTokenType(623, "AGGREGATE");
		IElementType KEYWORD_CURSOR = new PlSqlTokenType(532, "CURSOR");
		IElementType KEYWORD_WRAPPED = new PlSqlTokenType(540, "WRAPPED");
		IElementType KEYWORD_NUMERIC = new PlSqlTokenType(590, "NUMERIC");
		IElementType KEYWORD_FOR = new PlSqlTokenType(445, "FOR");
		IElementType KEYWORD_DISTINCT = new PlSqlTokenType(652, "DISTINCT");
		IElementType KEYWORD_OPEN = new PlSqlTokenType(554, "OPEN");
		IElementType KEYWORD_ARE = new PlSqlTokenType(763, "ARE");
		IElementType KEYWORD_INITIAL = new PlSqlTokenType(502, "INITIAL");
		IElementType KEYWORD_MAXTRANS = new PlSqlTokenType(476, "MAXTRANS");
		IElementType KEYWORD_NOAUDIT = new PlSqlTokenType(433, "NOAUDIT");
		IElementType KEYWORD_ANY_CS = new PlSqlTokenType(608, "ANY_CS");
		IElementType KEYWORD_FALSE = new PlSqlTokenType(628, "FALSE");
		IElementType KEYWORD_COMPRESSION = new PlSqlTokenType(747, "COMPRESSION");
		IElementType KEYWORD_GROUPS = new PlSqlTokenType(509, "GROUPS");
		IElementType KEYWORD_TABLE = new PlSqlTokenType(332, "TABLE");
		IElementType KEYWORD_LIKE = new PlSqlTokenType(634, "LIKE");
		IElementType KEYWORD_CREATE = new PlSqlTokenType(403, "CREATE");
		IElementType KEYWORD_EXIT = new PlSqlTokenType(383, "EXIT");
		IElementType KEYWORD_NOT = new PlSqlTokenType(362, "NOT");
		IElementType KEYWORD_RECORD = new PlSqlTokenType(530, "RECORD");
		IElementType KEYWORD_RECYCLE = new PlSqlTokenType(513, "RECYCLE");
		IElementType KEYWORD_ASC = new PlSqlTokenType(454, "ASC");
		IElementType KEYWORD_START = new PlSqlTokenType(396, "START");
		IElementType KEYWORD_TRUNCATE = new PlSqlTokenType(355, "TRUNCATE");
		IElementType KEYWORD_INDICES = new PlSqlTokenType(626, "INDICES");
		IElementType KEYWORD_LANGUAGE = new PlSqlTokenType(617, "LANGUAGE");
		IElementType KEYWORD_POSITION = new PlSqlTokenType(732, "POSITION");
		IElementType KEYWORD_NOTRIM = new PlSqlTokenType(728, "NOTRIM");
		IElementType KEYWORD_BYTES = new PlSqlTokenType(713, "BYTES");
		IElementType KEYWORD_RANGE = new PlSqlTokenType(480, "RANGE");
		IElementType KEYWORD_INITRANS = new PlSqlTokenType(475, "INITRANS");
		IElementType KEYWORD_LINK = new PlSqlTokenType(354, "LINK");
		IElementType KEYWORD_GOTO = new PlSqlTokenType(548, "GOTO");
		IElementType KEYWORD_NOCHECK = new PlSqlTokenType(710, "NOCHECK");
		IElementType KEYWORD_ESCAPE = new PlSqlTokenType(635, "ESCAPE");
		IElementType KEYWORD_VERSION = new PlSqlTokenType(751, "VERSION");
		IElementType KEYWORD_CHARACTER = new PlSqlTokenType(602, "CHARACTER");
		IElementType KEYWORD_MODE = new PlSqlTokenType(695, "MODE");
		IElementType KEYWORD_BADFILE = new PlSqlTokenType(716, "BADFILE");
		IElementType KEYWORD_EXCEPTIONS = new PlSqlTokenType(625, "EXCEPTIONS");
		IElementType KEYWORD_DEF = new PlSqlTokenType(388, "DEF");
		IElementType KEYWORD_UNION = new PlSqlTokenType(659, "UNION");
		IElementType KEYWORD_DELETE = new PlSqlTokenType(442, "DELETE");
		IElementType KEYWORD_BULK_ROWCOUNT = new PlSqlTokenType(562, "BULK_ROWCOUNT");
		IElementType KEYWORD_DETERMINISTIC = new PlSqlTokenType(622, "DETERMINISTIC");
		IElementType KEYWORD_END = new PlSqlTokenType(541, "END");
		IElementType KEYWORD_TRIGGER = new PlSqlTokenType(421, "TRIGGER");
		IElementType KEYWORD_ISOPEN = new PlSqlTokenType(561, "ISOPEN");
		IElementType KEYWORD_RELY = new PlSqlTokenType(522, "RELY");
		IElementType KEYWORD_CACHE = new PlSqlTokenType(410, "CACHE");
		IElementType KEYWORD_RETURN = new PlSqlTokenType(533, "RETURN");
		IElementType KEYWORD_UNSIGNED = new PlSqlTokenType(742, "UNSIGNED");
		IElementType KEYWORD_ACCESS = new PlSqlTokenType(701, "ACCESS");
		IElementType KEYWORD_TRANSFORMS = new PlSqlTokenType(764, "TRANSFORMS");
		IElementType KEYWORD_CURRENT_TIMESTAMP = new PlSqlTokenType(682, "CURRENT_TIMESTAMP");
		IElementType KEYWORD_DEGREE = new PlSqlTokenType(496, "DEGREE");
		IElementType KEYWORD_DIRECTORY = new PlSqlTokenType(351, "DIRECTORY");
		IElementType KEYWORD_TERMINATED = new PlSqlTokenType(755, "TERMINATED");
		IElementType KEYWORD_MINEXTENTS = new PlSqlTokenType(504, "MINEXTENTS");
		IElementType KEYWORD_OLD = new PlSqlTokenType(448, "OLD");
		IElementType KEYWORD_GRANT = new PlSqlTokenType(436, "GRANT");
		IElementType KEYWORD_NVARCHAR2 = new PlSqlTokenType(601, "NVARCHAR2");
		IElementType KEYWORD_TRANSACTION = new PlSqlTokenType(694, "TRANSACTION");
		IElementType KEYWORD_RENAME = new PlSqlTokenType(437, "RENAME");
		IElementType KEYWORD_ENABLE = new PlSqlTokenType(365, "ENABLE");
		IElementType KEYWORD_MASK = new PlSqlTokenType(754, "MASK");
		IElementType KEYWORD_LITTLE = new PlSqlTokenType(707, "LITTLE");
		IElementType KEYWORD_SHOW = new PlSqlTokenType(374, "SHOW");
		IElementType KEYWORD_SKIP = new PlSqlTokenType(722, "SKIP");
		IElementType KEYWORD_PRESERVE = new PlSqlTokenType(458, "PRESERVE");
		IElementType KEYWORD_FUNCTION = new PlSqlTokenType(337, "FUNCTION");
		IElementType KEYWORD_HEAP = new PlSqlTokenType(494, "HEAP");
		IElementType KEYWORD_TABLESPACE = new PlSqlTokenType(460, "TABLESPACE");
		IElementType KEYWORD_ENCRYPTION = new PlSqlTokenType(750, "ENCRYPTION");
		IElementType KEYWORD_POSITIVE = new PlSqlTokenType(574, "POSITIVE");
		IElementType KEYWORD_WORK = new PlSqlTokenType(657, "WORK");
		IElementType KEYWORD_HASH = new PlSqlTokenType(488, "HASH");
		IElementType KEYWORD_VARCHARC = new PlSqlTokenType(736, "VARCHARC");
		IElementType KEYWORD_GLOBAL = new PlSqlTokenType(456, "GLOBAL");
		IElementType KEYWORD_LOGGING = new PlSqlTokenType(465, "LOGGING");
		IElementType KEYWORD_MARK = new PlSqlTokenType(709, "MARK");
		IElementType KEYWORD_FORALL = new PlSqlTokenType(546, "FORALL");
		IElementType KEYWORD_RESTRICT = new PlSqlTokenType(517, "RESTRICT");
		IElementType KEYWORD_DEFAULT = new PlSqlTokenType(360, "DEFAULT");
		IElementType KEYWORD_DENSE_RANK = new PlSqlTokenType(643, "DENSE_RANK");
		IElementType KEYWORD_CHARACTERS = new PlSqlTokenType(762, "CHARACTERS");
		IElementType KEYWORD_REJECT = new PlSqlTokenType(498, "REJECT");
		IElementType KEYWORD_TEMPORARY = new PlSqlTokenType(457, "TEMPORARY");
		IElementType KEYWORD_SERVERERROR = new PlSqlTokenType(426, "SERVERERROR");
		IElementType KEYWORD_OBJECT = new PlSqlTokenType(529, "OBJECT");
		IElementType KEYWORD_MINUTE = new PlSqlTokenType(644, "MINUTE");
		IElementType KEYWORD_SHARE = new PlSqlTokenType(696, "SHARE");
		IElementType KEYWORD_ORDER = new PlSqlTokenType(415, "ORDER");
		IElementType KEYWORD_LTRIM = new PlSqlTokenType(729, "LTRIM");
		IElementType KEYWORD_NOTFOUND = new PlSqlTokenType(559, "NOTFOUND");
		IElementType KEYWORD_WITH = new PlSqlTokenType(414, "WITH");
		IElementType KEYWORD_CHECK = new PlSqlTokenType(515, "CHECK");
		IElementType KEYWORD_LOCK = new PlSqlTokenType(553, "LOCK");
		IElementType KEYWORD_THE = new PlSqlTokenType(684, "THE");
		IElementType KEYWORD_SQLERROR = new PlSqlTokenType(381, "SQLERROR");
		IElementType KEYWORD_DECIMAL = new PlSqlTokenType(597, "DECIMAL");
		IElementType KEYWORD_BEGIN = new PlSqlTokenType(400, "BEGIN");
		IElementType KEYWORD_BLOB = new PlSqlTokenType(604, "BLOB");
		IElementType KEYWORD_NOCYCLE = new PlSqlTokenType(409, "NOCYCLE");
		IElementType KEYWORD_OUTER = new PlSqlTokenType(675, "OUTER");
		IElementType KEYWORD_CONTINUE = new PlSqlTokenType(384, "CONTINUE");
		IElementType KEYWORD_INSTEAD = new PlSqlTokenType(440, "INSTEAD");
		IElementType KEYWORD_GROUP = new PlSqlTokenType(685, "GROUP");
		IElementType KEYWORD_USER = new PlSqlTokenType(349, "USER");
		IElementType KEYWORD_FIRST = new PlSqlTokenType(688, "FIRST");
		IElementType KEYWORD_STORAGE = new PlSqlTokenType(501, "STORAGE");

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
		KEYWORD_BEFORE,
		KEYWORD_EXCLUSIVE,
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
		KEYWORD_BY,
		KEYWORD_LONG,
		KEYWORD_CLOSE,
		KEYWORD_ANY,
		KEYWORD_KEY,
		KEYWORD_NOBADFILE,
		KEYWORD_EXECUTE,
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
		KEYWORD_VAR,
		KEYWORD_SET,
		KEYWORD_DATA_CACHE,
		KEYWORD_DDL,
		KEYWORD_STATISTICS,
		KEYWORD_ORGANIZATION,
		KEYWORD_NAME,
		KEYWORD_DISABLE,
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
		KEYWORD_ENCLOSED,
		KEYWORD_NO,
		KEYWORD_PACKAGE,
		KEYWORD_NOCACHE,
		KEYWORD_FIXED,
		KEYWORD_OF,
		KEYWORD_RANK,
		KEYWORD_BYTE,
		KEYWORD_ON,
		KEYWORD_ONLY,
		KEYWORD_RESET,
		KEYWORD_PURGE,
		KEYWORD_629,
		KEYWORD_LIMIT,
		KEYWORD_INCREMENT,
		KEYWORD_FETCH,
		KEYWORD_OR,
		KEYWORD_VARRAWC,
		KEYWORD_STARTUP,
		KEYWORD_ROW,
		KEYWORD_NOORDER,
		KEYWORD_NEWLINE,
		KEYWORD_SESSION,
		KEYWORD_ENDIAN,
		KEYWORD_THEN,
		KEYWORD_MONTH,
		KEYWORD_LOGOFF,
		KEYWORD_COMMENT,
		KEYWORD_RECORDS,
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
		KEYWORD_MLSLABEL,
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
		KEYWORD_CHARSET,
		KEYWORD_OPTIONALLY,
		KEYWORD_DECLARE,
		KEYWORD_PRAGMA,
		KEYWORD_DBTIMEZONE,
		KEYWORD_SYSTEM,
		KEYWORD_LEADING,
		KEYWORD_PLS_INTEGER,
		KEYWORD_LOAD,
		KEYWORD_PCTINCREASE,
		KEYWORD_EXCEPTION_INIT,
		KEYWORD_SORT,
		KEYWORD_DESC,
		KEYWORD_ORACLE_DATAPUMP,
		KEYWORD_NEXT,
		KEYWORD_CLOB,
		KEYWORD_DATA,
		KEYWORD_REPFOOTER,
		KEYWORD_UNLIMITED,
		KEYWORD_ORACLE_NUMBER,
		KEYWORD_DATE,
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
		KEYWORD_CAST,
		KEYWORD_THAN,
		KEYWORD_EXEC,
		KEYWORD_CASE,
		KEYWORD_FOREIGN,
		KEYWORD_NATURAL,
		KEYWORD_FREELIST,
		KEYWORD_COMPATIBLE,
		KEYWORD_DATE_FORMAT,
		KEYWORD_NOVISIBLE,
		KEYWORD_BULK_EXCEPTIONS,
		KEYWORD_LOGFILE,
		KEYWORD_LOBFILE,
		KEYWORD_FILESYSTEM_LIKE_LOGGING,
		KEYWORD_NOCOPY,
		KEYWORD_IMMEDIATE,
		KEYWORD_COUNTED,
		KEYWORD_OPERATIONS,
		KEYWORD_LATEST,
		KEYWORD_SMALLINT,
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
		KEYWORD_LOGGING,
		KEYWORD_MARK,
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
		KEYWORD_FIRST,
		KEYWORD_STORAGE,
		new PlSqlTokenType(9999, "$$$END_OF_ARRAY_$$$")
		);
}
