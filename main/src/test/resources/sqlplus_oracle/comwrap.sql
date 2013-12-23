Rem  Copyright (c) 1998 by Oracle Corporation 
Rem    NAME
Rem     comwrap.sql - Wrap a layer of PL/SQL packaage over the external procedure callouts
Rem    DESCRIPTION
REM		 Expose a set of PL/SQL APIs through ORDCOM package to access the external procedure calls
REM		 in the COM cartridge.
REM    MODIFIED   (MM/DD/YY)
REM     kfleong	   10/11/97 -  Creation
REM
REM	

drop library utils_lib;
/* change this accordingly to the right version of dll */
create library utils_lib as '$ORACLE_HOME\bin\orawpcom10.dll';
/
drop package ORDCOM;
drop TYPE OAArgTable;
create TYPE OAArgTable AS TABLE OF VARCHAR2(32767);
/
drop TYPE OAArgTypeTable;
create TYPE OAArgTypeTable AS TABLE OF VARCHAR2(32);
/
drop function OAgetNumber;
create FUNCTION OAgetNumber( 
	x in binary_integer,
	a in varchar2,
	plist in OAArgTable,
	ptype in OAArgTypeTable,
	d out double precision,
	count in binary_integer) 
	return binary_integer as external
	library utils_lib
	name "OAgetNumber"
	language C;
/
drop function OAgetStr;
create FUNCTION OAgetStr(
	x in binary_integer,
	a in varchar2,
	plist in OAArgTable,
	ptype in OAArgTypeTable,
	b out varchar2,
	count in binary_integer) 
	return binary_integer as external
	library utils_lib
	name "OAgetStr"
	language C
	parameters (x, a, plist, ptype, b, b MAXLEN, count, RETURN);
/
drop function OAgetBool;
create FUNCTION OAgetbool(
	x in binary_integer,
	a in varchar2,
	plist in OAArgTable,
	ptype in OAArgTypeTable,
	b out boolean,
	count in binary_integer) 
	return binary_integer as external
	library utils_lib
	name "OAgetBool"
	language C;
/
drop function OAsetNumber;
create FUNCTION OAsetNumber(
	x in binary_integer,
	a in varchar2,
	b in double precision,
	d in varchar2)
    return binary_integer as external 
	library utils_lib
	name "OAsetNumber"
	language C;
/
drop function OAsetString;
create FUNCTION OAsetString(
	x in binary_integer,
	a in varchar2,
	b in varchar2,
	d in varchar2)
    return binary_integer as external 
	library utils_lib
	name "OAsetString"
	language C;
/
drop function OAsetBoolean;
create FUNCTION OAsetBoolean(
	x in binary_integer,
	a in varchar2,
	b in boolean,
	d in varchar2)
    return binary_integer as external 
	library utils_lib
	name "OAsetBoolean"
	language C;
/
drop function OAInvokeDouble;
create FUNCTION OAInvokeDouble(
	x in binary_integer,
	a in varchar2,
	b in OAArgTable,
	c in OAArgTypeTable,
	d in binary_integer,
	e out double precision,
	f in out OAArgTable)
    return binary_integer as external 
	library utils_lib
	name "OAInvokeDouble"
	language C
	parameters (x, a, b, c, d, e, f, RETURN);
/
drop function OAInvokeBoolean;
create FUNCTION OAInvokeBoolean(
	x in binary_integer,
	a in varchar2,
	b in OAArgTable,
	c in OAArgTypeTable,
	d in binary_integer,
	e out boolean,
	f in out OAArgTable)
    return binary_integer as external 
	library utils_lib
	name "OAInvokeBoolean"
	language C
	parameters (x, a, b, c, d, e, f, RETURN);
/
drop function OAInvokeString;
create FUNCTION OAInvokeString(
	x in binary_integer,
	a in varchar2,
	b in OAArgTable,
	c in OAArgTypeTable,
	d in binary_integer,
	e out varchar2,
	f in out OAArgTable)
    return binary_integer as external 
	library utils_lib
	name "OAInvokeString"
	language C
	parameters (x, a, b, c, d, e, e MAXLEN, f, RETURN);
/
drop function OACreate;
create FUNCTION OACreate(
	x in varchar2,
	a in binary_integer,
	b in varchar2,
	c out binary_integer)
    return binary_integer as external 
	library utils_lib
	name "OACreate"
	language C
	with context;
/
drop function OADestroy;
create FUNCTION OADestroy(
	a binary_integer)
    return binary_integer as external 
	library utils_lib
	name "OADestroy"
	language C;
/
drop function OAGetLastError;
create FUNCTION OAGetLastError(
	a out VARCHAR2,
	b out VARCHAR2,
	c out VARCHAR2,
	d out binary_integer)
    return binary_integer as external 
	library utils_lib
	name "OAgetLastError"
	language C;
/
drop function OAQueryMethods;
create FUNCTION OAQueryMethods(
	a binary_integer,
	b out VARCHAR2)
    return binary_integer as external 
	library utils_lib
	name "OAQueryMethods"
	language C;
/
CREATE PACKAGE ORDCOM AS


   /* Declare externally callable subprograms. */
   
   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	argCount binary_integer,
	retVal out double precision) return binary_integer;	
   
   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	argCount binary_integer,
	retVal out VARCHAR2) return binary_integer;	
 
   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2,
	argCount binary_integer, 
	retVal out boolean) return binary_integer;	
   
   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2,
	argCount binary_integer, 
	retVal out DATE) return binary_integer;	
 
   FUNCTION SetProperty (
	token binary_integer,
	PropName VARCHAR2,
	inVal double precision,
	DataType VARCHAR2) return binary_integer;
   
   FUNCTION SetProperty (
	token binary_integer,
	PropName VARCHAR2,
	inVal VARCHAR2,
	DataType VARCHAR2) return binary_integer;
   

   FUNCTION SetProperty (
	token binary_integer,
	PropName VARCHAR2,
	inVal boolean,
	DataType VARCHAR2) return binary_integer;

   FUNCTION SetProperty (
	token binary_integer,
	PropName VARCHAR2,
	inVal DATE,
	DataType VARCHAR2) return binary_integer;

   PROCEDURE InitArg;

   PROCEDURE SetArg(
	data double precision,
	type VARCHAR2);
  
   PROCEDURE SetArg(
	data VARCHAR2,
	type VARCHAR2);

   PROCEDURE SetArg(
	data DATE,
	type VARCHAR2);

   PROCEDURE SetArg(
	data BOOLEAN,
	type VARCHAR2);

   PROCEDURE SetPtrArg(
	data out double precision,
	type VARCHAR2);

   PROCEDURE SetPtrArg(
	data out VARCHAR2,
	type VARCHAR2);

   PROCEDURE InitOutArg;

   PROCEDURE GetArg(
	data out double precision,
      type VARCHAR2);
  
   PROCEDURE GetArg(
	data out VARCHAR2,
	type VARCHAR2);

   PROCEDURE GetArg(
	data out DATE,
	type VARCHAR2);

   PROCEDURE GetArg(
	data out BOOLEAN,
	type VARCHAR2);

   PROCEDURE GetLastError(
	 err_src out VARCHAR2,
	 err_desc out VARCHAR2,
	 err_help out VARCHAR2,
	 err_helpID out binary_integer);

   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal  out double precision) return binary_integer;

   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal  out boolean) return binary_integer;
	 
   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal  out VARCHAR2) return binary_integer;

   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal  out DATE) return binary_integer;

   FUNCTION CreateObject(
	 ProgID VARCHAR2,
	 crtype binary_integer,
	 servername VARCHAR2,
	 token out binary_integer) return binary_integer;


   FUNCTION DestroyObject(
     token binary_integer) return binary_integer;

END ORDCOM;

/
CREATE PACKAGE BODY ORDCOM AS
   dblVal double precision;
   inArgTable OAArgTable := OAArgTable();
   inArgTypeTable OAArgTypeTable := OAArgTypeTable();
   outArgTable OAArgTable := OAArgTable();
   outArgTableIdx binary_integer := 1;
   i binary_integer;
   j binary_integer;
   str VARCHAR2(32767);
   strl binary_integer;
   temp VARCHAR2(32767);

   PROCEDURE NextOutArg(arg out VARCHAR2) IS
   BEGIN
      arg := NULL;
      IF (outArgTableIdx >= outArgTable.FIRST) AND (outArgTableIdx <= outArgTable.LAST) THEN 
         arg := outArgTable(outArgTableIdx);
         outArgTableIdx := outArgTableIdx + 1;
      END IF;
   END NextOutArg;

   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	argCount binary_integer,
	retVal out double precision) RETURN binary_integer IS   
   BEGIN
   	i:=OAgetNumber(token, PropName, inArgTable, inArgTypeTable, dblVal, argCount);     			
	retval:=dblVal;
	RETURN i;
   END GetProperty;

   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	argCount binary_integer,
	retVal out VARCHAR2) RETURN binary_integer IS   
   BEGIN
   	i:=OAgetStr(token, PropName, inArgTable, inArgTypeTable, temp, argCount);     			
      retVal := temp;
	RETURN i;
   END GetProperty;


   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	argCount binary_integer,
	retVal out DATE) RETURN binary_integer IS  
	tempstr varchar2(20); 
   BEGIN
   	i:=OAgetStr(token, PropName, inArgTable, inArgTypeTable, tempstr, argCount);
	retVal:=TO_DATE(tempstr, 'DD MM YYYY HH24 MI SS');
	RETURN i;
   END GetProperty;

   FUNCTION GetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	argCount binary_integer,
	retVal out boolean) RETURN binary_integer IS   
   BEGIN
   	i:=OAgetBool(token, PropName, inArgTable, inArgTypeTable, retVal, argCount); 
	RETURN i;
   END GetProperty;


   FUNCTION SetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	inVal double precision,
	Datatype VARCHAR2) RETURN binary_integer IS   
   BEGIN
   	i:=OAsetNumber(token, PropName, inVal, Datatype);     			
	RETURN i;
   END SetProperty;

   FUNCTION SetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	inVal VARCHAR2,
	Datatype VARCHAR2) RETURN binary_integer IS   
   BEGIN
   	i:=OAsetString(token, PropName, inVal, Datatype);     			
	RETURN i;
   END SetProperty;	

   FUNCTION SetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	inVal boolean,
	Datatype VARCHAR2) RETURN binary_integer IS   
   BEGIN
   	i:=OAsetBoolean(token, PropName, inVal, Datatype);     			
	RETURN i;
   END SetProperty;	

   FUNCTION SetProperty (
	token binary_integer, 
	PropName VARCHAR2, 
	inVal DATE,
	Datatype VARCHAR2) RETURN binary_integer IS   
	OLEDate VARCHAR2(50);
   BEGIN
    OLEDate:=TO_CHAR(inVal,'DD MM YYYY HH24 MI SS');
   	i:=OAsetString(token, PropName, OLEDate, Datatype);     			
	RETURN i;
   END SetProperty;	

   PROCEDURE InitArg IS
   BEGIN
      inArgTable := OAArgTable();
      inArgTypeTable := OAArgTypeTable();
      outArgTable := OAArgTable();
      outArgTableIdx := 1;
   END InitArg;

   PROCEDURE SetArg(
	data double precision,
	type VARCHAR2) IS
   BEGIN
      inArgTable.EXTEND;
      inArgTypeTable.EXTEND;
      inArgTable(inArgTable.LAST) := TO_CHAR(data);
      inArgTypeTable(inArgTypeTable.LAST) := type;
   END SetArg;

   PROCEDURE SetArg(
	data boolean,
	type VARCHAR2) IS
   BEGIN
      inArgTable.EXTEND;
      inArgTypeTable.EXTEND;
      IF data THEN
         inArgTable(inArgTable.LAST) := '-1';
      ELSE
         inArgTable(inArgTable.LAST) := '0';
      END IF;
      inArgTypeTable(inArgTypeTable.LAST) := type;
   END SetArg;

   PROCEDURE SetPtrArg(
	data out double precision,
	type VARCHAR2) IS
   BEGIN
      data := 0;
   END SetPtrArg;

   PROCEDURE SetPtrArg(
	data out VARCHAR2,
	type VARCHAR2) IS
   BEGIN
      data := '';
   END SetPtrArg;

   PROCEDURE SetArg(
	data VARCHAR2,
	type VARCHAR2) IS
   BEGIN
      inArgTable.EXTEND;
      inArgTypeTable.EXTEND;
      inArgTable(inArgTable.LAST) := data;
      inArgTypeTable(inArgTypeTable.LAST) := type;
   END SetArg;

   PROCEDURE SetArg(
	data DATE,
	type VARCHAR2) IS
   BEGIN
      inArgTable.EXTEND;
      inArgTypeTable.EXTEND;
      inArgTable(inArgTable.LAST) := TO_CHAR(data,'DD MM YYYY HH24 MI SS');
      inArgTypeTable(inArgTypeTable.LAST) := type;
   END SetArg;

   PROCEDURE InitOutArg IS
   BEGIN
      outArgTableIdx := 1;
   END InitOutArg;

   PROCEDURE GetArg(
	data out double precision,
      type VARCHAR2) IS
   BEGIN
      data := 0;
      NextOutArg(str);
      IF NOT (str IS NULL) THEN
         data := TO_NUMBER(str);
      END IF;
   END GetArg;

   PROCEDURE GetArg(
	data out boolean,
	type VARCHAR2) IS
   BEGIN
      data := FALSE;
      NextOutArg(str);
      IF NOT (str IS NULL) THEN
         IF TO_NUMBER(str) != 0 THEN
            data := TRUE;
         END IF;
      END IF;
   END GetArg;

   PROCEDURE GetArg(
	data out VARCHAR2,
	type VARCHAR2) IS
   BEGIN
      NextOutArg(data);
   END GetArg;

   PROCEDURE GetArg(
	data out DATE,
	type VARCHAR2) IS
   BEGIN
      data := TO_DATE('01 01 0001 00 00 00', 'DD MM YYYY HH24 MI SS');
      NextOutArg(str);
      IF NOT (str IS NULL) THEN
         data := TO_DATE('01 01 0001 00 00 00', 'DD MM YYYY HH24 MI SS');
      END IF;
   END GetArg;

   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal out double precision) RETURN binary_integer IS
    BEGIN
       i:=OAInvokeDouble(token, MethodName, inArgTable, inArgTypeTable, argCount, retVal, outArgTable);
       RETURN i;
   END Invoke;

   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal out boolean) RETURN binary_integer IS
    BEGIN
       i:=OAInvokeBoolean(token, MethodName, inArgTable, inArgTypeTable, argCount, retVal, outArgTable);	 
       RETURN i;
   END Invoke;

   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal out VARCHAR2) RETURN binary_integer IS
    BEGIN
       i:=OAInvokeString(token, MethodName, inArgTable, inArgTypeTable, argCount, temp, outArgTable);
       retVal := temp;
       RETURN i;
   END Invoke;
   
   FUNCTION Invoke (
     token binary_integer,
	 MethodName VARCHAR2,
	 argCount binary_integer, 
	 retVal out DATE) RETURN binary_integer IS
       tempstr varchar2(20); 
    BEGIN
       i:=OAInvokeString(token, MethodName, inArgTable, inArgTypeTable, argCount, tempstr, outArgTable);
       IF i = 0 THEN
         retVal:=TO_DATE(tempstr, 'DD MM YYYY HH24 MI SS');
       END IF;
       RETURN i;
   END Invoke;
   	    
   FUNCTION CreateObject(
	 ProgID VARCHAR2,
	 crtype binary_integer,
	 servername VARCHAR2,
	 token out binary_integer) RETURN binary_integer IS
   BEGIN
	  i:=OACreate(ProgID, crtype, servername, token);
	  RETURN i;
   END CreateObject;
             
  PROCEDURE GetLastError(
	 err_src out VARCHAR2,
	 err_desc out VARCHAR2,
	 err_help out VARCHAR2,
	 err_helpID out binary_integer) IS
       terr_src VARCHAR(1025);
       terr_desc VARCHAR(1025);
       terr_help VARCHAR(1025);
  BEGIN
	i:=OAGetLastError(terr_src, terr_desc, terr_help, err_helpID);
      err_src := terr_src;
      err_desc := terr_desc;
      err_help := terr_help;
  END GetLastError;
 
   FUNCTION DestroyObject(
     token binary_integer) RETURN binary_integer IS
   BEGIN
	  i:=OADestroy(token);
	  RETURN i;
   END DestroyObject;

END ORDCOM;
/
