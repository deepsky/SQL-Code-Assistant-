Rem
Rem $Header: ctxityp.sql 09-jul-2002.00:20:17 ehuang Exp $
Rem
Rem ctxityp.sql
Rem
Rem Copyright (c) 2002, Oracle Corporation.  All rights reserved.  
Rem
Rem    NAME
Rem      ctxityp.sql
Rem
Rem    DESCRIPTION
Rem      create index types
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    ehuang      07/09/02 - 
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/12/02 - Created
Rem

PROMPT ... creating CONTEXT index type
@@dr0itype.sql

PROMPT ... creating CTXCAT index type
@@dr0itypc.sql

PROMPT ... creating CTXRULE index type
@@dr0itypr.sql

PROMPT ... creating CTXXPATH index type
@@dr0itypx.sql
