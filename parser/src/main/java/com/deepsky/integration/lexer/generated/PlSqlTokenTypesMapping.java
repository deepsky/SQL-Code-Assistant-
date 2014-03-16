package com.deepsky.integration.lexer.generated;

import com.intellij.psi.tree.IElementType;

public class PlSqlTokenTypesMapping {
	public final static IElementType[] table;

	static {
		table = new IElementType[941];

		table[43] = PlSqlBaseTokenTypes.DOLLAR;
		table[10] = PlSqlBaseTokenTypes.LT;
		table[24] = PlSqlBaseTokenTypes.QUOTED_STR;
		table[9] = PlSqlBaseTokenTypes.STORAGE_SIZE;
		table[40] = PlSqlBaseTokenTypes.DOUBLEDOT;
		table[22] = PlSqlBaseTokenTypes.ANY_CHARACTER;
		table[6] = PlSqlBaseTokenTypes.BAD_CHARACTER;
		table[31] = PlSqlBaseTokenTypes.ASTERISK;
		table[54] = PlSqlBaseTokenTypes.ML_COMMENT;
		table[56] = PlSqlBaseTokenTypes.THEN_COND_CMPL;
		table[30] = PlSqlBaseTokenTypes.COMMA;
		table[23] = PlSqlBaseTokenTypes.IDENTIFIER;
		table[20] = PlSqlBaseTokenTypes.BAD_QUOTED_STRING;
		table[57] = PlSqlBaseTokenTypes.ELSE_COND_CMPL;
		table[14] = PlSqlBaseTokenTypes.CUSTOM_TOKEN;
		table[26] = PlSqlBaseTokenTypes.AT_PREFIXED;
		table[34] = PlSqlBaseTokenTypes.PLUS;
		table[17] = PlSqlBaseTokenTypes.TABLE_NAME_SPEC;
		table[33] = PlSqlBaseTokenTypes.CLOSE_PAREN;
		table[58] = PlSqlBaseTokenTypes.END_COND_CMPL;
		table[46] = PlSqlBaseTokenTypes.PASS_BY_NAME;
		table[5] = PlSqlBaseTokenTypes.BAD_ML_COMMENT;
		table[38] = PlSqlBaseTokenTypes.EQ;
		table[29] = PlSqlBaseTokenTypes.DOT;
		table[45] = PlSqlBaseTokenTypes.ASSIGNMENT_EQ;
		table[59] = PlSqlBaseTokenTypes.ERROR_COND_CMPL;
		table[36] = PlSqlBaseTokenTypes.DIVIDE;
		table[25] = PlSqlBaseTokenTypes.DOUBLE_QUOTED_STRING;
		table[18] = PlSqlBaseTokenTypes.QUOTED_STR_START;
		table[42] = PlSqlBaseTokenTypes.START_LABEL;
		table[12] = PlSqlBaseTokenTypes.GE;
		table[41] = PlSqlBaseTokenTypes.CONCAT;
		table[8] = PlSqlBaseTokenTypes.BAD_NUMBER_LITERAL;
		table[50] = PlSqlBaseTokenTypes.N;
		table[4] = PlSqlBaseTokenTypes.C_MARKER;
		table[7] = PlSqlBaseTokenTypes.BAD_CHAR_LITERAL;
		table[49] = PlSqlBaseTokenTypes.NUMBER;
		table[16] = PlSqlBaseTokenTypes.COLON_OLD;
		table[32] = PlSqlBaseTokenTypes.OPEN_PAREN;
		table[35] = PlSqlBaseTokenTypes.MINUS;
		table[27] = PlSqlBaseTokenTypes.SEMI;
		table[39] = PlSqlBaseTokenTypes.PERCENTAGE;
		table[13] = PlSqlBaseTokenTypes.NOT_EQ;
		table[47] = PlSqlBaseTokenTypes.VERTBAR;
		table[21] = PlSqlBaseTokenTypes.PLSQL_ENV_VAR;
		table[28] = PlSqlBaseTokenTypes.COLON;
		table[51] = PlSqlBaseTokenTypes.WS;
		table[44] = PlSqlBaseTokenTypes.END_LABEL;
		table[53] = PlSqlBaseTokenTypes.SL_COMMENT;
		table[19] = PlSqlBaseTokenTypes.QUOTED_STR_END;
		table[48] = PlSqlBaseTokenTypes.GT;
		table[55] = PlSqlBaseTokenTypes.IF_COND_CMPL;
		table[15] = PlSqlBaseTokenTypes.COLON_NEW;
		table[11] = PlSqlBaseTokenTypes.LE;
		table[52] = PlSqlBaseTokenTypes.LF;
		table[37] = PlSqlBaseTokenTypes.BACKSLASH;
		table[453] = PlSqlBaseTokenTypes.KEYWORD_USING;
		table[723] = PlSqlBaseTokenTypes.KEYWORD_ERROR_INDEX;
		table[602] = PlSqlBaseTokenTypes.KEYWORD_REFERENCING;
		table[611] = PlSqlBaseTokenTypes.KEYWORD_NESTED;
		table[612] = PlSqlBaseTokenTypes.KEYWORD_STORE;
		table[710] = PlSqlBaseTokenTypes.KEYWORD_FIPSFLAG;
		table[654] = PlSqlBaseTokenTypes.KEYWORD_EXTERNAL;
		table[826] = PlSqlBaseTokenTypes.KEYWORD_PRIVILEGES;
		table[861] = PlSqlBaseTokenTypes.KEYWORD_WAIT;
		table[633] = PlSqlBaseTokenTypes.KEYWORD_PCTFREE;
		table[754] = PlSqlBaseTokenTypes.KEYWORD_FLOAT;
		table[921] = PlSqlBaseTokenTypes.KEYWORD_LRTRIM;
		table[911] = PlSqlBaseTokenTypes.KEYWORD_MISSING;
		table[811] = PlSqlBaseTokenTypes.KEYWORD_OVER;
		table[467] = PlSqlBaseTokenTypes.KEYWORD_REFERENCES;
		table[741] = PlSqlBaseTokenTypes.KEYWORD_TIME;
		table[890] = PlSqlBaseTokenTypes.KEYWORD_CHARACTERSET;
		table[463] = PlSqlBaseTokenTypes.KEYWORD_MOVEMENT;
		table[432] = PlSqlBaseTokenTypes.KEYWORD_ROLE;
		table[586] = PlSqlBaseTokenTypes.KEYWORD_LOGON;
		table[847] = PlSqlBaseTokenTypes.KEYWORD_RIGHT;
		table[728] = PlSqlBaseTokenTypes.KEYWORD_ELSE;
		table[493] = PlSqlBaseTokenTypes.KEYWORD_HOST;
		table[640] = PlSqlBaseTokenTypes.KEYWORD_MONITORING;
		table[872] = PlSqlBaseTokenTypes.KEYWORD_SAVEPOINT;
		table[732] = PlSqlBaseTokenTypes.KEYWORD_NUMBER;
		table[805] = PlSqlBaseTokenTypes.KEYWORD_EXTRACT;
		table[632] = PlSqlBaseTokenTypes.KEYWORD_NOCOMPRESS;
		table[592] = PlSqlBaseTokenTypes.KEYWORD_DIASSOCIATE;
		table[471] = PlSqlBaseTokenTypes.KEYWORD_SYSDATE;
		table[651] = PlSqlBaseTokenTypes.KEYWORD_NOVALIDATE;
		table[713] = PlSqlBaseTokenTypes.KEYWORD_SUBTYPE;
		table[601] = PlSqlBaseTokenTypes.KEYWORD_EACH;
		table[418] = PlSqlBaseTokenTypes.KEYWORD_VIEW;
		table[892] = PlSqlBaseTokenTypes.KEYWORD_BIG;
		table[770] = PlSqlBaseTokenTypes.KEYWORD_SERIALLY_REUSABLE;
		table[809] = PlSqlBaseTokenTypes.KEYWORD_NEXTVAL;
		table[470] = PlSqlBaseTokenTypes.KEYWORD_UNIQUE;
		table[630] = PlSqlBaseTokenTypes.KEYWORD_DIRECT_LOAD;
		table[779] = PlSqlBaseTokenTypes.KEYWORD_RAISE;
		table[870] = PlSqlBaseTokenTypes.KEYWORD_EXCLUSIVE;
		table[582] = PlSqlBaseTokenTypes.KEYWORD_BEFORE;
		table[796] = PlSqlBaseTokenTypes.KEYWORD_SQLERRM;
		table[546] = PlSqlBaseTokenTypes.KEYWORD_NOGUARANTEE;
		table[828] = PlSqlBaseTokenTypes.KEYWORD_DEBUG;
		table[520] = PlSqlBaseTokenTypes.KEYWORD_GLOBALLY;
		table[656] = PlSqlBaseTokenTypes.KEYWORD_INSTANCES;
		table[860] = PlSqlBaseTokenTypes.KEYWORD_NOWAIT;
		table[909] = PlSqlBaseTokenTypes.KEYWORD_PREPROCESSOR;
		table[619] = PlSqlBaseTokenTypes.KEYWORD_PCTVERSION;
		table[558] = PlSqlBaseTokenTypes.KEYWORD_WRITE;
		table[714] = PlSqlBaseTokenTypes.KEYWORD_LOOP;
		table[788] = PlSqlBaseTokenTypes.KEYWORD_CURRENT;
		table[846] = PlSqlBaseTokenTypes.KEYWORD_LEFT;
		table[584] = PlSqlBaseTokenTypes.KEYWORD_SHUTDOWN;
		table[794] = PlSqlBaseTokenTypes.KEYWORD_MEMBER;
		table[843] = PlSqlBaseTokenTypes.KEYWORD_SID;
		table[490] = PlSqlBaseTokenTypes.KEYWORD_DEFINE;
		table[707] = PlSqlBaseTokenTypes.KEYWORD_RESTRICT_REFERENCES;
		table[650] = PlSqlBaseTokenTypes.KEYWORD_PARTITIONS;
		table[751] = PlSqlBaseTokenTypes.KEYWORD_INTEGER;
		table[855] = PlSqlBaseTokenTypes.KEYWORD_JOIN;
		table[807] = PlSqlBaseTokenTypes.KEYWORD_HOUR;
		table[430] = PlSqlBaseTokenTypes.KEYWORD_OPERATOR;
		table[588] = PlSqlBaseTokenTypes.KEYWORD_ANALYZE;
		table[683] = PlSqlBaseTokenTypes.KEYWORD_REF;
		table[604] = PlSqlBaseTokenTypes.KEYWORD_NEW;
		table[560] = PlSqlBaseTokenTypes.KEYWORD_INCLUDING;
		table[425] = PlSqlBaseTokenTypes.KEYWORD_SEQUENCE;
		table[434] = PlSqlBaseTokenTypes.KEYWORD_LIBRARY;
		table[492] = PlSqlBaseTokenTypes.KEYWORD_REM;
		table[789] = PlSqlBaseTokenTypes.KEYWORD_EXISTS;
		table[856] = PlSqlBaseTokenTypes.KEYWORD_HAVING;
		table[428] = PlSqlBaseTokenTypes.KEYWORD_PUBLIC;
		table[742] = PlSqlBaseTokenTypes.KEYWORD_ZONE;
		table[898] = PlSqlBaseTokenTypes.KEYWORD_SIZES;
		table[413] = PlSqlBaseTokenTypes.KEYWORD_BODY;
		table[680] = PlSqlBaseTokenTypes.KEYWORD_VISIBLE;
		table[415] = PlSqlBaseTokenTypes.KEYWORD_DROP;
		table[554] = PlSqlBaseTokenTypes.KEYWORD_NORMAL;
		table[769] = PlSqlBaseTokenTypes.KEYWORD_EXCEPTION;
		table[802] = PlSqlBaseTokenTypes.KEYWORD_LEAD;
		table[518] = PlSqlBaseTokenTypes.KEYWORD_BY;
		table[474] = PlSqlBaseTokenTypes.KEYWORD_LONG;
		table[866] = PlSqlBaseTokenTypes.KEYWORD_CLOSE;
		table[808] = PlSqlBaseTokenTypes.KEYWORD_ANY;
		table[902] = PlSqlBaseTokenTypes.KEYWORD_NOBADFILE;
		table[466] = PlSqlBaseTokenTypes.KEYWORD_KEY;
		table[480] = PlSqlBaseTokenTypes.KEYWORD_EXECUTE;
		table[443] = PlSqlBaseTokenTypes.KEYWORD_PACKAGES;
		table[483] = PlSqlBaseTokenTypes.KEYWORD_OSERROR;
		table[753] = PlSqlBaseTokenTypes.KEYWORD_DOUBLE;
		table[500] = PlSqlBaseTokenTypes.KEYWORD_REPHEADER;
		table[562] = PlSqlBaseTokenTypes.KEYWORD_AND;
		table[628] = PlSqlBaseTokenTypes.KEYWORD_COMPRESS;
		table[888] = PlSqlBaseTokenTypes.KEYWORD_DELIMITED;
		table[642] = PlSqlBaseTokenTypes.KEYWORD_OVERFLOW;
		table[729] = PlSqlBaseTokenTypes.KEYWORD_AUTONOMOUS_TRANSACTION;
		table[441] = PlSqlBaseTokenTypes.KEYWORD_COLUMN;
		table[745] = PlSqlBaseTokenTypes.KEYWORD_DAY;
		table[699] = PlSqlBaseTokenTypes.KEYWORD_FAST;
		table[838] = PlSqlBaseTokenTypes.KEYWORD_COLLECT;
		table[600] = PlSqlBaseTokenTypes.KEYWORD_UPDATE;
		table[738] = PlSqlBaseTokenTypes.KEYWORD_RAW;
		table[580] = PlSqlBaseTokenTypes.KEYWORD_CONNECT;
		table[816] = PlSqlBaseTokenTypes.KEYWORD_TIMEZONE_HOUR;
		table[550] = PlSqlBaseTokenTypes.KEYWORD_NOLOGGING;
		table[473] = PlSqlBaseTokenTypes.KEYWORD_SET;
		table[476] = PlSqlBaseTokenTypes.KEYWORD_VAR;
		table[907] = PlSqlBaseTokenTypes.KEYWORD_DATA_CACHE;
		table[591] = PlSqlBaseTokenTypes.KEYWORD_DDL;
		table[439] = PlSqlBaseTokenTypes.KEYWORD_STATISTICS;
		table[652] = PlSqlBaseTokenTypes.KEYWORD_ORGANIZATION;
		table[801] = PlSqlBaseTokenTypes.KEYWORD_LAG;
		table[446] = PlSqlBaseTokenTypes.KEYWORD_INDEXTYPES;
		table[778] = PlSqlBaseTokenTypes.KEYWORD_NAME;
		table[460] = PlSqlBaseTokenTypes.KEYWORD_DISABLE;
		table[799] = PlSqlBaseTokenTypes.KEYWORD_TRIM;
		table[444] = PlSqlBaseTokenTypes.KEYWORD_TYPES;
		table[774] = PlSqlBaseTokenTypes.KEYWORD_RESULT_CACHE;
		table[629] = PlSqlBaseTokenTypes.KEYWORD_ALL;
		table[638] = PlSqlBaseTokenTypes.KEYWORD_PARALLEL;
		table[904] = PlSqlBaseTokenTypes.KEYWORD_NODISCARDFILE;
		table[691] = PlSqlBaseTokenTypes.KEYWORD_PRECISION;
		table[712] = PlSqlBaseTokenTypes.KEYWORD_CONSTANT;
		table[873] = PlSqlBaseTokenTypes.KEYWORD_ORACLE_LOADER;
		table[542] = PlSqlBaseTokenTypes.KEYWORD_UNIFORM;
		table[787] = PlSqlBaseTokenTypes.KEYWORD_AT;
		table[589] = PlSqlBaseTokenTypes.KEYWORD_AUDIT;
		table[515] = PlSqlBaseTokenTypes.KEYWORD_AS;
		table[671] = PlSqlBaseTokenTypes.KEYWORD_CELL_FLASH_CACHE;
		table[696] = PlSqlBaseTokenTypes.KEYWORD_NEVER;
		table[840] = PlSqlBaseTokenTypes.KEYWORD_FLUSH;
		table[419] = PlSqlBaseTokenTypes.KEYWORD_CASCADE;
		table[499] = PlSqlBaseTokenTypes.KEYWORD_OFF;
		table[777] = PlSqlBaseTokenTypes.KEYWORD_JAVA;
		table[884] = PlSqlBaseTokenTypes.KEYWORD_DISABLED;
		table[800] = PlSqlBaseTokenTypes.KEYWORD_MULTISET;
		table[917] = PlSqlBaseTokenTypes.KEYWORD_ENCLOSED;
		table[551] = PlSqlBaseTokenTypes.KEYWORD_NO;
		table[575] = PlSqlBaseTokenTypes.KEYWORD_NOCACHE;
		table[412] = PlSqlBaseTokenTypes.KEYWORD_PACKAGE;
		table[887] = PlSqlBaseTokenTypes.KEYWORD_FIXED;
		table[597] = PlSqlBaseTokenTypes.KEYWORD_OF;
		table[695] = PlSqlBaseTokenTypes.KEYWORD_REWRITE;
		table[803] = PlSqlBaseTokenTypes.KEYWORD_RANK;
		table[737] = PlSqlBaseTokenTypes.KEYWORD_BYTE;
		table[842] = PlSqlBaseTokenTypes.KEYWORD_RESET;
		table[557] = PlSqlBaseTokenTypes.KEYWORD_ONLY;
		table[457] = PlSqlBaseTokenTypes.KEYWORD_ON;
		table[697] = PlSqlBaseTokenTypes.KEYWORD_REFRESH;
		table[417] = PlSqlBaseTokenTypes.KEYWORD_PURGE;
		table[786] = PlSqlBaseTokenTypes.KEYWORD_786; // KEYWORD_**
		table[658] = PlSqlBaseTokenTypes.KEYWORD_LIMIT;
		table[867] = PlSqlBaseTokenTypes.KEYWORD_FETCH;
		table[576] = PlSqlBaseTokenTypes.KEYWORD_INCREMENT;
		table[568] = PlSqlBaseTokenTypes.KEYWORD_COALESCE;
		table[514] = PlSqlBaseTokenTypes.KEYWORD_FINAL;
		table[507] = PlSqlBaseTokenTypes.KEYWORD_OR;
		table[934] = PlSqlBaseTokenTypes.KEYWORD_VARRAWC;
		table[583] = PlSqlBaseTokenTypes.KEYWORD_STARTUP;
		table[462] = PlSqlBaseTokenTypes.KEYWORD_ROW;
		table[692] = PlSqlBaseTokenTypes.KEYWORD_EXCLUDING;
		table[448] = PlSqlBaseTokenTypes.KEYWORD_MANAGED;
		table[889] = PlSqlBaseTokenTypes.KEYWORD_NEWLINE;
		table[578] = PlSqlBaseTokenTypes.KEYWORD_NOORDER;
		table[894] = PlSqlBaseTokenTypes.KEYWORD_ENDIAN;
		table[832] = PlSqlBaseTokenTypes.KEYWORD_SESSION;
		table[727] = PlSqlBaseTokenTypes.KEYWORD_THEN;
		table[744] = PlSqlBaseTokenTypes.KEYWORD_MONTH;
		table[886] = PlSqlBaseTokenTypes.KEYWORD_RECORDS;
		table[587] = PlSqlBaseTokenTypes.KEYWORD_LOGOFF;
		table[456] = PlSqlBaseTokenTypes.KEYWORD_COMMENT;
		table[623] = PlSqlBaseTokenTypes.KEYWORD_CREATION;
		table[645] = PlSqlBaseTokenTypes.KEYWORD_INTERVAL;
		table[795] = PlSqlBaseTokenTypes.KEYWORD_SQLCODE;
		table[862] = PlSqlBaseTokenTypes.KEYWORD_MERGE;
		table[519] = PlSqlBaseTokenTypes.KEYWORD_EXTERNALLY;
		table[464] = PlSqlBaseTokenTypes.KEYWORD_CONSTRAINT;
		table[626] = PlSqlBaseTokenTypes.KEYWORD_PCTTHRESHOLD;
		table[698] = PlSqlBaseTokenTypes.KEYWORD_BUILD;
		table[494] = PlSqlBaseTokenTypes.KEYWORD_QUIT;
		table[852] = PlSqlBaseTokenTypes.KEYWORD_ROWNUM;
		table[547] = PlSqlBaseTokenTypes.KEYWORD_AUTOEXTEND;
		table[928] = PlSqlBaseTokenTypes.KEYWORD_ZONED;
		table[827] = PlSqlBaseTokenTypes.KEYWORD_HIERARCHY;
		table[454] = PlSqlBaseTokenTypes.KEYWORD_NULL;
		table[567] = PlSqlBaseTokenTypes.KEYWORD_BACKUP;
		table[719] = PlSqlBaseTokenTypes.KEYWORD_ROWCOUNT;
		table[784] = PlSqlBaseTokenTypes.KEYWORD_TRUE;
		table[940] = PlSqlBaseTokenTypes.KEYWORD_LDTRIM;
		table[716] = PlSqlBaseTokenTypes.KEYWORD_SQL;
		table[905] = PlSqlBaseTokenTypes.KEYWORD_DISCARDFILE;
		table[527] = PlSqlBaseTokenTypes.KEYWORD_ACCOUNT;
		table[424] = PlSqlBaseTokenTypes.KEYWORD_FORCE;
		table[599] = PlSqlBaseTokenTypes.KEYWORD_INSERT;
		table[818] = PlSqlBaseTokenTypes.KEYWORD_TIMEZONE_REGION;
		table[859] = PlSqlBaseTokenTypes.KEYWORD_LAST;
		table[725] = PlSqlBaseTokenTypes.KEYWORD_COUNT;
		table[746] = PlSqlBaseTokenTypes.KEYWORD_SECOND;
		table[780] = PlSqlBaseTokenTypes.KEYWORD_SAVE;
		table[938] = PlSqlBaseTokenTypes.KEYWORD_LOCATION;
		table[736] = PlSqlBaseTokenTypes.KEYWORD_CHAR;
		table[622] = PlSqlBaseTokenTypes.KEYWORD_SEGMENT;
		table[850] = PlSqlBaseTokenTypes.KEYWORD_WHERE;
		table[426] = PlSqlBaseTokenTypes.KEYWORD_TYPE;
		table[704] = PlSqlBaseTokenTypes.KEYWORD_AUTHID;
		table[730] = PlSqlBaseTokenTypes.KEYWORD_PRIOR;
		table[594] = PlSqlBaseTokenTypes.KEYWORD_REVOKE;
		table[661] = PlSqlBaseTokenTypes.KEYWORD_MAXEXTENTS;
		table[643] = PlSqlBaseTokenTypes.KEYWORD_PARTITION;
		table[495] = PlSqlBaseTokenTypes.KEYWORD_SPOOL;
		table[465] = PlSqlBaseTokenTypes.KEYWORD_PRIMARY;
		table[605] = PlSqlBaseTokenTypes.KEYWORD_WHEN;
		table[615] = PlSqlBaseTokenTypes.KEYWORD_VALUE;
		table[675] = PlSqlBaseTokenTypes.KEYWORD_ACTION;
		table[488] = PlSqlBaseTokenTypes.KEYWORD_NONE;
		table[620] = PlSqlBaseTokenTypes.KEYWORD_FREEPOOLS;
		table[864] = PlSqlBaseTokenTypes.KEYWORD_RETURNING;
		table[572] = PlSqlBaseTokenTypes.KEYWORD_CYCLE;
		table[571] = PlSqlBaseTokenTypes.KEYWORD_MINVALUE;
		table[634] = PlSqlBaseTokenTypes.KEYWORD_PCTUSED;
		table[836] = PlSqlBaseTokenTypes.KEYWORD_MINUS;
		table[821] = PlSqlBaseTokenTypes.KEYWORD_TRAILING;
		table[750] = PlSqlBaseTokenTypes.KEYWORD_INT;
		table[534] = PlSqlBaseTokenTypes.KEYWORD_DATAFILE;
		table[724] = PlSqlBaseTokenTypes.KEYWORD_ERROR_CODE;
		table[610] = PlSqlBaseTokenTypes.KEYWORD_ROWS;
		table[559] = PlSqlBaseTokenTypes.KEYWORD_PERMANENT;
		table[835] = PlSqlBaseTokenTypes.KEYWORD_INTERSECT;
		table[563] = PlSqlBaseTokenTypes.KEYWORD_DATAFILES;
		table[641] = PlSqlBaseTokenTypes.KEYWORD_NOMONITORING;
		table[501] = PlSqlBaseTokenTypes.KEYWORD_SERVEROUTPUT;
		table[906] = PlSqlBaseTokenTypes.KEYWORD_READSIZE;
		table[678] = PlSqlBaseTokenTypes.KEYWORD_NOSORT;
		table[487] = PlSqlBaseTokenTypes.KEYWORD_ROLLBACK;
		table[815] = PlSqlBaseTokenTypes.KEYWORD_FROM;
		table[564] = PlSqlBaseTokenTypes.KEYWORD_ADD;
		table[552] = PlSqlBaseTokenTypes.KEYWORD_ONLINE;
		table[715] = PlSqlBaseTokenTypes.KEYWORD_WHILE;
		table[748] = PlSqlBaseTokenTypes.KEYWORD_REAL;
		table[509] = PlSqlBaseTokenTypes.KEYWORD_MATERIALIZED;
		table[539] = PlSqlBaseTokenTypes.KEYWORD_EXTENT;
		table[824] = PlSqlBaseTokenTypes.KEYWORD_IF;
		table[544] = PlSqlBaseTokenTypes.KEYWORD_RETENTION;
		table[556] = PlSqlBaseTokenTypes.KEYWORD_READ;
		table[637] = PlSqlBaseTokenTypes.KEYWORD_COMPUTE;
		table[647] = PlSqlBaseTokenTypes.KEYWORD_LESS;
		table[793] = PlSqlBaseTokenTypes.KEYWORD_BETWEEN;
		table[458] = PlSqlBaseTokenTypes.KEYWORD_IS;
		table[538] = PlSqlBaseTokenTypes.KEYWORD_REUSE;
		table[924] = PlSqlBaseTokenTypes.KEYWORD_RTRIM;
		table[685] = PlSqlBaseTokenTypes.KEYWORD_ROWTYPE;
		table[839] = PlSqlBaseTokenTypes.KEYWORD_INTO;
		table[830] = PlSqlBaseTokenTypes.KEYWORD_RESOURCE;
		table[676] = PlSqlBaseTokenTypes.KEYWORD_MODIFY;
		table[708] = PlSqlBaseTokenTypes.KEYWORD_INTERFACE;
		table[915] = PlSqlBaseTokenTypes.KEYWORD_CONCAT;
		table[621] = PlSqlBaseTokenTypes.KEYWORD_IN;
		table[435] = PlSqlBaseTokenTypes.KEYWORD_DATABASE;
		table[831] = PlSqlBaseTokenTypes.KEYWORD_INDEXTYPE;
		table[472] = PlSqlBaseTokenTypes.KEYWORD_SYSTIMESTAMP;
		table[541] = PlSqlBaseTokenTypes.KEYWORD_LOCAL;
		table[717] = PlSqlBaseTokenTypes.KEYWORD_FOUND;
		table[932] = PlSqlBaseTokenTypes.KEYWORD_VARRAW;
		table[863] = PlSqlBaseTokenTypes.KEYWORD_MATCHED;
		table[686] = PlSqlBaseTokenTypes.KEYWORD_VARRAY;
		table[857] = PlSqlBaseTokenTypes.KEYWORD_NULLS;
		table[666] = PlSqlBaseTokenTypes.KEYWORD_OPTIMAL;
		table[427] = PlSqlBaseTokenTypes.KEYWORD_VALIDATE;
		table[438] = PlSqlBaseTokenTypes.KEYWORD_ASSOCIATE;
		table[595] = PlSqlBaseTokenTypes.KEYWORD_SCHEMA;
		table[667] = PlSqlBaseTokenTypes.KEYWORD_BUFFER_POOL;
		table[663] = PlSqlBaseTokenTypes.KEYWORD_FREELISTS;
		table[690] = PlSqlBaseTokenTypes.KEYWORD_REDUCED;
		table[739] = PlSqlBaseTokenTypes.KEYWORD_BOOLEAN;
		table[743] = PlSqlBaseTokenTypes.KEYWORD_YEAR;
		table[771] = PlSqlBaseTokenTypes.KEYWORD_PIPELINED;
		table[687] = PlSqlBaseTokenTypes.KEYWORD_OPTION;
		table[758] = PlSqlBaseTokenTypes.KEYWORD_NVARCHAR;
		table[561] = PlSqlBaseTokenTypes.KEYWORD_CONTENTS;
		table[919] = PlSqlBaseTokenTypes.KEYWORD_WHITESPACE;
		table[513] = PlSqlBaseTokenTypes.KEYWORD_UNDER;
		table[420] = PlSqlBaseTokenTypes.KEYWORD_CONSTRAINTS;
		table[768] = PlSqlBaseTokenTypes.KEYWORD_CHARSET;
		table[510] = PlSqlBaseTokenTypes.KEYWORD_LOG;
		table[920] = PlSqlBaseTokenTypes.KEYWORD_OPTIONALLY;
		table[503] = PlSqlBaseTokenTypes.KEYWORD_DECLARE;
		table[706] = PlSqlBaseTokenTypes.KEYWORD_PRAGMA;
		table[618] = PlSqlBaseTokenTypes.KEYWORD_READS;
		table[813] = PlSqlBaseTokenTypes.KEYWORD_DBTIMEZONE;
		table[447] = PlSqlBaseTokenTypes.KEYWORD_SYSTEM;
		table[798] = PlSqlBaseTokenTypes.KEYWORD_DECODE;
		table[820] = PlSqlBaseTokenTypes.KEYWORD_LEADING;
		table[702] = PlSqlBaseTokenTypes.KEYWORD_702; // KEYWORD_VIEW_COLUMN_DEF_$INTERNAL$
		table[752] = PlSqlBaseTokenTypes.KEYWORD_PLS_INTEGER;
		table[901] = PlSqlBaseTokenTypes.KEYWORD_LOAD;
		table[662] = PlSqlBaseTokenTypes.KEYWORD_PCTINCREASE;
		table[711] = PlSqlBaseTokenTypes.KEYWORD_EXCEPTION_INIT;
		table[608] = PlSqlBaseTokenTypes.KEYWORD_DESC;
		table[677] = PlSqlBaseTokenTypes.KEYWORD_SORT;
		table[569] = PlSqlBaseTokenTypes.KEYWORD_MINIMUM;
		table[874] = PlSqlBaseTokenTypes.KEYWORD_ORACLE_DATAPUMP;
		table[535] = PlSqlBaseTokenTypes.KEYWORD_NEXT;
		table[762] = PlSqlBaseTokenTypes.KEYWORD_CLOB;
		table[891] = PlSqlBaseTokenTypes.KEYWORD_DATA;
		table[498] = PlSqlBaseTokenTypes.KEYWORD_REPFOOTER;
		table[523] = PlSqlBaseTokenTypes.KEYWORD_UNLIMITED;
		table[624] = PlSqlBaseTokenTypes.KEYWORD_DEFERRED;
		table[930] = PlSqlBaseTokenTypes.KEYWORD_ORACLE_NUMBER;
		table[740] = PlSqlBaseTokenTypes.KEYWORD_DATE;
		table[764] = PlSqlBaseTokenTypes.KEYWORD_BFILE;
		table[703] = PlSqlBaseTokenTypes.KEYWORD_TIMESTAMP;
		table[616] = PlSqlBaseTokenTypes.KEYWORD_LOB;
		table[825] = PlSqlBaseTokenTypes.KEYWORD_ELSIF;
		table[709] = PlSqlBaseTokenTypes.KEYWORD_BUILTIN;
		table[481] = PlSqlBaseTokenTypes.KEYWORD_WHENEVER;
		table[772] = PlSqlBaseTokenTypes.KEYWORD_PARALLEL_ENABLE;
		table[422] = PlSqlBaseTokenTypes.KEYWORD_PROCEDURE;
		table[756] = PlSqlBaseTokenTypes.KEYWORD_VARCHAR;
		table[679] = PlSqlBaseTokenTypes.KEYWORD_REVERSE;
		table[733] = PlSqlBaseTokenTypes.KEYWORD_BINARY_INTEGER;
		table[670] = PlSqlBaseTokenTypes.KEYWORD_FLASH_CACHE;
		table[810] = PlSqlBaseTokenTypes.KEYWORD_CURRVAL;
		table[757] = PlSqlBaseTokenTypes.KEYWORD_VARCHAR2;
		table[837] = PlSqlBaseTokenTypes.KEYWORD_BULK;
		table[414] = PlSqlBaseTokenTypes.KEYWORD_ALTER;
		table[912] = PlSqlBaseTokenTypes.KEYWORD_FIELD;
		table[508] = PlSqlBaseTokenTypes.KEYWORD_REPLACE;
		table[929] = PlSqlBaseTokenTypes.KEYWORD_ORACLE_DATE;
		table[639] = PlSqlBaseTokenTypes.KEYWORD_NOPARALLEL;
		table[829] = PlSqlBaseTokenTypes.KEYWORD_ADMIN;
		table[897] = PlSqlBaseTokenTypes.KEYWORD_STRING;
		table[845] = PlSqlBaseTokenTypes.KEYWORD_UNUSABLE;
		table[491] = PlSqlBaseTokenTypes.KEYWORD_PROMPT;
		table[477] = PlSqlBaseTokenTypes.KEYWORD_VARIABLE;
		table[841] = PlSqlBaseTokenTypes.KEYWORD_SHARED_POOL;
		table[668] = PlSqlBaseTokenTypes.KEYWORD_KEEP;
		table[565] = PlSqlBaseTokenTypes.KEYWORD_TO;
		table[478] = PlSqlBaseTokenTypes.KEYWORD_COL;
		table[822] = PlSqlBaseTokenTypes.KEYWORD_BOTH;
		table[848] = PlSqlBaseTokenTypes.KEYWORD_INNER;
		table[429] = PlSqlBaseTokenTypes.KEYWORD_SYNONYM;
		table[833] = PlSqlBaseTokenTypes.KEYWORD_BECOME;
		table[877] = PlSqlBaseTokenTypes.KEYWORD_NOLOGFILE;
		table[517] = PlSqlBaseTokenTypes.KEYWORD_IDENTIFIED;
		table[581] = PlSqlBaseTokenTypes.KEYWORD_AFTER;
		table[936] = PlSqlBaseTokenTypes.KEYWORD_TIMEZONE;
		table[646] = PlSqlBaseTokenTypes.KEYWORD_VALUES;
		table[763] = PlSqlBaseTokenTypes.KEYWORD_NCLOB;
		table[486] = PlSqlBaseTokenTypes.KEYWORD_COMMIT;
		table[672] = PlSqlBaseTokenTypes.KEYWORD_ENCRYPT;
		table[812] = PlSqlBaseTokenTypes.KEYWORD_SESSIONTIMEZONE;
		table[925] = PlSqlBaseTokenTypes.KEYWORD_LDRTRIM;
		table[876] = PlSqlBaseTokenTypes.KEYWORD_PARAMETERS;
		table[910] = PlSqlBaseTokenTypes.KEYWORD_FIELDS;
		table[701] = PlSqlBaseTokenTypes.KEYWORD_DEMAND;
		table[883] = PlSqlBaseTokenTypes.KEYWORD_ENABLED;
		table[423] = PlSqlBaseTokenTypes.KEYWORD_INDEX;
		table[496] = PlSqlBaseTokenTypes.KEYWORD_STA;
		table[606] = PlSqlBaseTokenTypes.KEYWORD_BITMAP;
		table[817] = PlSqlBaseTokenTypes.KEYWORD_TIMEZONE_MINUTE;
		table[790] = PlSqlBaseTokenTypes.KEYWORD_SELECT;
		table[570] = PlSqlBaseTokenTypes.KEYWORD_MAXVALUE;
		table[445] = PlSqlBaseTokenTypes.KEYWORD_INDEXES;
		table[451] = PlSqlBaseTokenTypes.KEYWORD_COST;
		table[537] = PlSqlBaseTokenTypes.KEYWORD_SIZE;
		table[797] = PlSqlBaseTokenTypes.KEYWORD_CAST;
		table[648] = PlSqlBaseTokenTypes.KEYWORD_THAN;
		table[479] = PlSqlBaseTokenTypes.KEYWORD_EXEC;
		table[726] = PlSqlBaseTokenTypes.KEYWORD_CASE;
		table[673] = PlSqlBaseTokenTypes.KEYWORD_FOREIGN;
		table[734] = PlSqlBaseTokenTypes.KEYWORD_NATURAL;
		table[664] = PlSqlBaseTokenTypes.KEYWORD_FREELIST;
		table[819] = PlSqlBaseTokenTypes.KEYWORD_TIMEZONE_ABBR;
		table[536] = PlSqlBaseTokenTypes.KEYWORD_MAXSIZE;
		table[532] = PlSqlBaseTokenTypes.KEYWORD_TEMPFILE;
		table[700] = PlSqlBaseTokenTypes.KEYWORD_COMPLETE;
		table[880] = PlSqlBaseTokenTypes.KEYWORD_COMPATIBLE;
		table[935] = PlSqlBaseTokenTypes.KEYWORD_DATE_FORMAT;
		table[844] = PlSqlBaseTokenTypes.KEYWORD_REBUILD;
		table[681] = PlSqlBaseTokenTypes.KEYWORD_NOVISIBLE;
		table[722] = PlSqlBaseTokenTypes.KEYWORD_BULK_EXCEPTIONS;
		table[878] = PlSqlBaseTokenTypes.KEYWORD_LOGFILE;
		table[916] = PlSqlBaseTokenTypes.KEYWORD_LOBFILE;
		table[524] = PlSqlBaseTokenTypes.KEYWORD_PROFILE;
		table[442] = PlSqlBaseTokenTypes.KEYWORD_FUNCTIONS;
		table[627] = PlSqlBaseTokenTypes.KEYWORD_FILESYSTEM_LIKE_LOGGING;
		table[766] = PlSqlBaseTokenTypes.KEYWORD_NOCOPY;
		table[555] = PlSqlBaseTokenTypes.KEYWORD_IMMEDIATE;
		table[931] = PlSqlBaseTokenTypes.KEYWORD_COUNTED;
		table[631] = PlSqlBaseTokenTypes.KEYWORD_OPERATIONS;
		table[881] = PlSqlBaseTokenTypes.KEYWORD_LATEST;
		table[747] = PlSqlBaseTokenTypes.KEYWORD_SMALLINT;
		table[452] = PlSqlBaseTokenTypes.KEYWORD_SELECTIVITY;
		table[765] = PlSqlBaseTokenTypes.KEYWORD_OUT;
		table[531] = PlSqlBaseTokenTypes.KEYWORD_SMALLFILE;
		table[939] = PlSqlBaseTokenTypes.KEYWORD_AGGREGATE;
		table[617] = PlSqlBaseTokenTypes.KEYWORD_CHUNK;
		table[684] = PlSqlBaseTokenTypes.KEYWORD_CURSOR;
		table[705] = PlSqlBaseTokenTypes.KEYWORD_WRAPPED;
		table[749] = PlSqlBaseTokenTypes.KEYWORD_NUMERIC;
		table[579] = PlSqlBaseTokenTypes.KEYWORD_FOR;
		table[814] = PlSqlBaseTokenTypes.KEYWORD_DISTINCT;
		table[871] = PlSqlBaseTokenTypes.KEYWORD_OPEN;
		table[913] = PlSqlBaseTokenTypes.KEYWORD_ARE;
		table[659] = PlSqlBaseTokenTypes.KEYWORD_INITIAL;
		table[590] = PlSqlBaseTokenTypes.KEYWORD_NOAUDIT;
		table[636] = PlSqlBaseTokenTypes.KEYWORD_MAXTRANS;
		table[767] = PlSqlBaseTokenTypes.KEYWORD_ANY_CS;
		table[785] = PlSqlBaseTokenTypes.KEYWORD_FALSE;
		table[882] = PlSqlBaseTokenTypes.KEYWORD_COMPRESSION;
		table[665] = PlSqlBaseTokenTypes.KEYWORD_GROUPS;
		table[529] = PlSqlBaseTokenTypes.KEYWORD_UNLOCK;
		table[416] = PlSqlBaseTokenTypes.KEYWORD_TABLE;
		table[791] = PlSqlBaseTokenTypes.KEYWORD_LIKE;
		table[506] = PlSqlBaseTokenTypes.KEYWORD_CREATE;
		table[689] = PlSqlBaseTokenTypes.KEYWORD_WITHOUT;
		table[484] = PlSqlBaseTokenTypes.KEYWORD_EXIT;
		table[459] = PlSqlBaseTokenTypes.KEYWORD_NOT;
		table[682] = PlSqlBaseTokenTypes.KEYWORD_RECORD;
		table[669] = PlSqlBaseTokenTypes.KEYWORD_RECYCLE;
		table[607] = PlSqlBaseTokenTypes.KEYWORD_ASC;
		table[497] = PlSqlBaseTokenTypes.KEYWORD_START;
		table[455] = PlSqlBaseTokenTypes.KEYWORD_TRUNCATE;
		table[783] = PlSqlBaseTokenTypes.KEYWORD_INDICES;
		table[776] = PlSqlBaseTokenTypes.KEYWORD_LANGUAGE;
		table[926] = PlSqlBaseTokenTypes.KEYWORD_POSITION;
		table[922] = PlSqlBaseTokenTypes.KEYWORD_NOTRIM;
		table[899] = PlSqlBaseTokenTypes.KEYWORD_BYTES;
		table[644] = PlSqlBaseTokenTypes.KEYWORD_RANGE;
		table[635] = PlSqlBaseTokenTypes.KEYWORD_INITRANS;
		table[694] = PlSqlBaseTokenTypes.KEYWORD_QUERY;
		table[436] = PlSqlBaseTokenTypes.KEYWORD_LINK;
		table[731] = PlSqlBaseTokenTypes.KEYWORD_GOTO;
		table[896] = PlSqlBaseTokenTypes.KEYWORD_NOCHECK;
		table[553] = PlSqlBaseTokenTypes.KEYWORD_OFFLINE;
		table[792] = PlSqlBaseTokenTypes.KEYWORD_ESCAPE;
		table[879] = PlSqlBaseTokenTypes.KEYWORD_VERSION;
		table[868] = PlSqlBaseTokenTypes.KEYWORD_MODE;
		table[760] = PlSqlBaseTokenTypes.KEYWORD_CHARACTER;
		table[903] = PlSqlBaseTokenTypes.KEYWORD_BADFILE;
		table[781] = PlSqlBaseTokenTypes.KEYWORD_EXCEPTIONS;
		table[489] = PlSqlBaseTokenTypes.KEYWORD_DEF;
		table[834] = PlSqlBaseTokenTypes.KEYWORD_UNION;
		table[598] = PlSqlBaseTokenTypes.KEYWORD_DELETE;
		table[721] = PlSqlBaseTokenTypes.KEYWORD_BULK_ROWCOUNT;
		table[773] = PlSqlBaseTokenTypes.KEYWORD_DETERMINISTIC;
		table[566] = PlSqlBaseTokenTypes.KEYWORD_END;
		table[526] = PlSqlBaseTokenTypes.KEYWORD_EXPIRE;
		table[437] = PlSqlBaseTokenTypes.KEYWORD_TRIGGER;
		table[720] = PlSqlBaseTokenTypes.KEYWORD_ISOPEN;
		table[468] = PlSqlBaseTokenTypes.KEYWORD_RELY;
		table[574] = PlSqlBaseTokenTypes.KEYWORD_CACHE;
		table[613] = PlSqlBaseTokenTypes.KEYWORD_RETURN;
		table[543] = PlSqlBaseTokenTypes.KEYWORD_DICTIONARY;
		table[530] = PlSqlBaseTokenTypes.KEYWORD_BIGFILE;
		table[927] = PlSqlBaseTokenTypes.KEYWORD_UNSIGNED;
		table[875] = PlSqlBaseTokenTypes.KEYWORD_ACCESS;
		table[914] = PlSqlBaseTokenTypes.KEYWORD_TRANSFORMS;
		table[851] = PlSqlBaseTokenTypes.KEYWORD_CURRENT_TIMESTAMP;
		table[655] = PlSqlBaseTokenTypes.KEYWORD_DEGREE;
		table[433] = PlSqlBaseTokenTypes.KEYWORD_DIRECTORY;
		table[918] = PlSqlBaseTokenTypes.KEYWORD_TERMINATED;
		table[775] = PlSqlBaseTokenTypes.KEYWORD_RELIES_ON;
		table[603] = PlSqlBaseTokenTypes.KEYWORD_OLD;
		table[660] = PlSqlBaseTokenTypes.KEYWORD_MINEXTENTS;
		table[593] = PlSqlBaseTokenTypes.KEYWORD_GRANT;
		table[865] = PlSqlBaseTokenTypes.KEYWORD_TRANSACTION;
		table[533] = PlSqlBaseTokenTypes.KEYWORD_UNDO;
		table[759] = PlSqlBaseTokenTypes.KEYWORD_NVARCHAR2;
		table[505] = PlSqlBaseTokenTypes.KEYWORD_RENAME;
		table[461] = PlSqlBaseTokenTypes.KEYWORD_ENABLE;
		table[937] = PlSqlBaseTokenTypes.KEYWORD_MASK;
		table[893] = PlSqlBaseTokenTypes.KEYWORD_LITTLE;
		table[475] = PlSqlBaseTokenTypes.KEYWORD_SHOW;
		table[908] = PlSqlBaseTokenTypes.KEYWORD_SKIP;
		table[525] = PlSqlBaseTokenTypes.KEYWORD_PASSWORD;
		table[609] = PlSqlBaseTokenTypes.KEYWORD_PRESERVE;
		table[421] = PlSqlBaseTokenTypes.KEYWORD_FUNCTION;
		table[653] = PlSqlBaseTokenTypes.KEYWORD_HEAP;
		table[521] = PlSqlBaseTokenTypes.KEYWORD_TABLESPACE;
		table[885] = PlSqlBaseTokenTypes.KEYWORD_ENCRYPTION;
		table[735] = PlSqlBaseTokenTypes.KEYWORD_POSITIVE;
		table[823] = PlSqlBaseTokenTypes.KEYWORD_WORK;
		table[614] = PlSqlBaseTokenTypes.KEYWORD_LOCATOR;
		table[649] = PlSqlBaseTokenTypes.KEYWORD_HASH;
		table[933] = PlSqlBaseTokenTypes.KEYWORD_VARCHARC;
		table[511] = PlSqlBaseTokenTypes.KEYWORD_GLOBAL;
		table[895] = PlSqlBaseTokenTypes.KEYWORD_MARK;
		table[549] = PlSqlBaseTokenTypes.KEYWORD_LOGGING;
		table[782] = PlSqlBaseTokenTypes.KEYWORD_FORALL;
		table[674] = PlSqlBaseTokenTypes.KEYWORD_RESTRICT;
		table[450] = PlSqlBaseTokenTypes.KEYWORD_DEFAULT;
		table[804] = PlSqlBaseTokenTypes.KEYWORD_DENSE_RANK;
		table[540] = PlSqlBaseTokenTypes.KEYWORD_MANAGEMENT;
		table[900] = PlSqlBaseTokenTypes.KEYWORD_CHARACTERS;
		table[657] = PlSqlBaseTokenTypes.KEYWORD_REJECT;
		table[512] = PlSqlBaseTokenTypes.KEYWORD_TEMPORARY;
		table[585] = PlSqlBaseTokenTypes.KEYWORD_SERVERERROR;
		table[516] = PlSqlBaseTokenTypes.KEYWORD_OBJECT;
		table[806] = PlSqlBaseTokenTypes.KEYWORD_MINUTE;
		table[869] = PlSqlBaseTokenTypes.KEYWORD_SHARE;
		table[522] = PlSqlBaseTokenTypes.KEYWORD_QUOTA;
		table[577] = PlSqlBaseTokenTypes.KEYWORD_ORDER;
		table[849] = PlSqlBaseTokenTypes.KEYWORD_FULL;
		table[923] = PlSqlBaseTokenTypes.KEYWORD_LTRIM;
		table[718] = PlSqlBaseTokenTypes.KEYWORD_NOTFOUND;
		table[440] = PlSqlBaseTokenTypes.KEYWORD_WITH;
		table[469] = PlSqlBaseTokenTypes.KEYWORD_CHECK;
		table[528] = PlSqlBaseTokenTypes.KEYWORD_LOCK;
		table[853] = PlSqlBaseTokenTypes.KEYWORD_THE;
		table[482] = PlSqlBaseTokenTypes.KEYWORD_SQLERROR;
		table[755] = PlSqlBaseTokenTypes.KEYWORD_DECIMAL;
		table[502] = PlSqlBaseTokenTypes.KEYWORD_BEGIN;
		table[761] = PlSqlBaseTokenTypes.KEYWORD_BLOB;
		table[573] = PlSqlBaseTokenTypes.KEYWORD_NOCYCLE;
		table[854] = PlSqlBaseTokenTypes.KEYWORD_OUTER;
		table[485] = PlSqlBaseTokenTypes.KEYWORD_CONTINUE;
		table[596] = PlSqlBaseTokenTypes.KEYWORD_INSTEAD;
		table[545] = PlSqlBaseTokenTypes.KEYWORD_GUARANTEE;
		table[625] = PlSqlBaseTokenTypes.KEYWORD_CLUSTER;
		table[548] = PlSqlBaseTokenTypes.KEYWORD_GROUP;
		table[688] = PlSqlBaseTokenTypes.KEYWORD_PREBUILT;
		table[858] = PlSqlBaseTokenTypes.KEYWORD_FIRST;
		table[431] = PlSqlBaseTokenTypes.KEYWORD_USER;
		table[693] = PlSqlBaseTokenTypes.KEYWORD_ROWID;
		table[449] = PlSqlBaseTokenTypes.KEYWORD_STORAGE;
	}
}
