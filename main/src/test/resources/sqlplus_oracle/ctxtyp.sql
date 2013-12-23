Rem
Rem $Header: ctxtyp.sql 09-jul-2002.00:20:46 ehuang Exp $
Rem
Rem ctxtyp.sql
Rem
Rem Copyright (c) 2002, Oracle Corporation.  All rights reserved.  
Rem
Rem    NAME
Rem      ctxtyp.sql
Rem
Rem    DESCRIPTION
Rem      create type specifications
Rem
Rem    NOTES
Rem      <other useful comments, qualifications, etc.>
Rem
Rem    MODIFIED   (MM/DD/YY)
Rem    ehuang      07/09/02 - 
Rem    ehuang      06/17/02 - ehuang_component_upgrade
Rem    ehuang      06/11/02 - Created
Rem

PROMPT ... creating CONTEXT interface
@@dr0type.pkh

PROMPT ... creating CTXCAT interface
@@dr0typec.pkh

PROMPT ... creating CTXRULE interface
@@dr0typer.pkh

PROMPT ... creating CTXXPATH interface
@@dr0typex.pkh
