Rem
Rem $Header: ctxtyb.sql 09-jul-2002.00:20:53 ehuang Exp $
Rem
Rem ctxtyb.sql
Rem
Rem Copyright (c) 2002, Oracle Corporation.  All rights reserved.  
Rem
Rem    NAME
Rem      ctxtyb.sql
Rem
Rem    DESCRIPTION
Rem      create or replace public and private type Bodies
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    ehuang      07/09/02 - 
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem

PROMPT ... creating CONTEXT interface body
@@dr0type.plb

PROMPT ... creating CTXCAT interface body
@@dr0typec.plb

PROMPT ... creating CTXRULE interface body
@@dr0typer.plb

PROMPT ... creating CTXXPATH interface body
@@dr0typex.plb
