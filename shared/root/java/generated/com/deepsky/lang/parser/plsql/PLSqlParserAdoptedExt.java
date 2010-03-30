package com.deepsky.lang.parser.plsql;

import com.intellij.lang.PsiBuilder;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.common.PlSqlTokenTypes;

import antlr.TokenStream;
import java.util.Map;
import java.util.HashMap;
import com.intellij.psi.tree.IElementType;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;

public abstract class PLSqlParserAdoptedExt extends com.deepsky.generated.plsql.adopted.PLSqlParserAdopted {
	PsiBuilder builder;
	abstract public int getPredicting();

	public PLSqlParserAdoptedExt(TokenStream t, PsiBuilder b){
		super(t);
		builder = b;
	}

	public void transform() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.transform();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.transform();
		}
		returnType = -1;
	}

	public void location() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.location();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.location();
		}
		returnType = -1;
	}

	public void alias() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alias();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alias();
		}
		returnType = -1;
	}

	public void handle_ws(int arg0){
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.handle_ws(arg0);
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.handle_ws(arg0);
		}
		returnType = -1;
	}

	public void package_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_name();
		}
		returnType = -1;
	}

	public void no_one_should_call_me() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.no_one_should_call_me();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.no_one_should_call_me();
		}
		returnType = -1;
	}

	public void identifier() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.identifier();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.identifier();
		}
		returnType = -1;
	}

	public void start_rule() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.start_rule();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.start_rule();
		}
		returnType = -1;
	}

	public void start_rule_inner() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.start_rule_inner();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.start_rule_inner();
		}
		returnType = -1;
	}

	public void create_or_replace() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_or_replace();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_or_replace();
		}
		returnType = -1;
	}

	public void package_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_spec();
		}
		returnType = -1;
	}

	public void function_body() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.function_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.function_body();
		}
		returnType = -1;
	}

	public void procedure_body() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.procedure_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.procedure_body();
		}
		returnType = -1;
	}

	public void sql_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sql_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sql_statement();
		}
		returnType = -1;
	}

	public void alter_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alter_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alter_command();
		}
		returnType = -1;
	}

	public void comment() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.comment();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.comment();
		}
		returnType = -1;
	}

	public void type_definition() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.type_definition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.type_definition();
		}
		returnType = -1;
	}

	public void drop_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.drop_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.drop_command();
		}
		returnType = -1;
	}

	public void truncate_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.truncate_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.truncate_command();
		}
		returnType = -1;
	}

	public void sqlplus_command_internal() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sqlplus_command_internal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sqlplus_command_internal();
		}
		returnType = -1;
	}

	public void table_name_ddl() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_name_ddl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_name_ddl();
		}
		returnType = -1;
	}

	public void callable_name_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.callable_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.callable_name_ref();
		}
		returnType = -1;
	}

	public void schema_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.schema_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.schema_name();
		}
		returnType = -1;
	}

	public void index_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.index_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.index_name();
		}
		returnType = -1;
	}

	public void object_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.object_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.object_name();
		}
		returnType = -1;
	}

	public void comment_string() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.comment_string();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.comment_string();
		}
		returnType = -1;
	}

	public void column_name_ddl() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_name_ddl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_name_ddl();
		}
		returnType = -1;
	}

	public void string_literal() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.string_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.string_literal();
		}
		returnType = -1;
	}

	public void column_def() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_def();
		}
		returnType = -1;
	}

	public void type_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.type_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.type_spec();
		}
		returnType = -1;
	}

	public void numeric_literal() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.numeric_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.numeric_literal();
		}
		returnType = -1;
	}

	public void not_null() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.not_null();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.not_null();
		}
		returnType = -1;
	}

	public void pk_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.pk_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.pk_spec();
		}
		returnType = -1;
	}

	public void fk_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.fk_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.fk_spec();
		}
		returnType = -1;
	}

	public void column_constraint() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_constraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_constraint();
		}
		returnType = -1;
	}

	public void row_movement_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.row_movement_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.row_movement_clause();
		}
		returnType = -1;
	}

	public void identifier2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.identifier2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.identifier2();
		}
		returnType = -1;
	}

	public void sqlplus_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sqlplus_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sqlplus_command();
		}
		returnType = -1;
	}

	public void base_expression() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.base_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.base_expression();
		}
		returnType = -1;
	}

	public void datatype() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.datatype();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.datatype();
		}
		returnType = -1;
	}

	public void sqlplus_exec_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sqlplus_exec_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sqlplus_exec_statement();
		}
		returnType = -1;
	}

	public void plsql_expression() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_expression();
		}
		returnType = -1;
	}

	public void identifier4() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.identifier4();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.identifier4();
		}
		returnType = -1;
	}

	public void sqlplus_path() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sqlplus_path();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sqlplus_path();
		}
		returnType = -1;
	}

	public void begin_block() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.begin_block();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.begin_block();
		}
		returnType = -1;
	}

	public void plsql_lvalue() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_lvalue();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_lvalue();
		}
		returnType = -1;
	}

	public void assignment_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.assignment_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.assignment_statement();
		}
		returnType = -1;
	}

	public void procedure_call() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.procedure_call();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.procedure_call();
		}
		returnType = -1;
	}

	public void identifier3() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.identifier3();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.identifier3();
		}
		returnType = -1;
	}

	public void package_body() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_body();
		}
		returnType = -1;
	}

	public void create_view() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_view();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_view();
		}
		returnType = -1;
	}

	public void create_table2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_table2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_table2();
		}
		returnType = -1;
	}

	public void create_temp_table2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_temp_table2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_temp_table2();
		}
		returnType = -1;
	}

	public void create_index2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_index2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_index2();
		}
		returnType = -1;
	}

	public void create_trigger() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_trigger();
		}
		returnType = -1;
	}

	public void create_directory() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_directory();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_directory();
		}
		returnType = -1;
	}

	public void create_db_link() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_db_link();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_db_link();
		}
		returnType = -1;
	}

	public void create_sequence() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.create_sequence();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.create_sequence();
		}
		returnType = -1;
	}

	public void sequence_opt() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sequence_opt();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sequence_opt();
		}
		returnType = -1;
	}

	public void trigger_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.trigger_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.trigger_name();
		}
		returnType = -1;
	}

	public void dml_trigger() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.dml_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.dml_trigger();
		}
		returnType = -1;
	}

	public void ddl_trigger() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.ddl_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.ddl_trigger();
		}
		returnType = -1;
	}

	public void db_event_trigger() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.db_event_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.db_event_trigger();
		}
		returnType = -1;
	}

	public void instead_of_trigger() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.instead_of_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.instead_of_trigger();
		}
		returnType = -1;
	}

	public void for_each() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.for_each();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.for_each();
		}
		returnType = -1;
	}

	public void referencing_old_new() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.referencing_old_new();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.referencing_old_new();
		}
		returnType = -1;
	}

	public void trigger_when() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.trigger_when();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.trigger_when();
		}
		returnType = -1;
	}

	public void trigger_target() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.trigger_target();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.trigger_target();
		}
		returnType = -1;
	}

	public void insert_update_delete() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.insert_update_delete();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.insert_update_delete();
		}
		returnType = -1;
	}

	public void view_name_ddl() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.view_name_ddl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.view_name_ddl();
		}
		returnType = -1;
	}

	public void condition() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.condition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.condition();
		}
		returnType = -1;
	}

	public void alter_trigger() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alter_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alter_trigger();
		}
		returnType = -1;
	}

	public void enable_disable_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.enable_disable_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.enable_disable_clause();
		}
		returnType = -1;
	}

	public void index_column_spec_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.index_column_spec_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.index_column_spec_list();
		}
		returnType = -1;
	}

	public void physical_properties() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.physical_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.physical_properties();
		}
		returnType = -1;
	}

	public void table_properties() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_properties();
		}
		returnType = -1;
	}

	public void constaraint() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.constaraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.constaraint();
		}
		returnType = -1;
	}

	public void organization_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.organization_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.organization_spec();
		}
		returnType = -1;
	}

	public void select_expression() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.select_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.select_expression();
		}
		returnType = -1;
	}

	public void cache_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cache_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cache_clause();
		}
		returnType = -1;
	}

	public void segment_attributes_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.segment_attributes_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.segment_attributes_clause();
		}
		returnType = -1;
	}

	public void physical_attributes_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.physical_attributes_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.physical_attributes_clause();
		}
		returnType = -1;
	}

	public void logging_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.logging_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.logging_clause();
		}
		returnType = -1;
	}

	public void table_compression() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_compression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_compression();
		}
		returnType = -1;
	}

	public void storage_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.storage_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.storage_spec();
		}
		returnType = -1;
	}

	public void table_partitioning_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_partitioning_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_partitioning_clause();
		}
		returnType = -1;
	}

	public void parallel_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.parallel_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.parallel_clause();
		}
		returnType = -1;
	}

	public void alter_table_options() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alter_table_options();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alter_table_options();
		}
		returnType = -1;
	}

	public void monitoring_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.monitoring_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.monitoring_clause();
		}
		returnType = -1;
	}

	public void range_partitions() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.range_partitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.range_partitions();
		}
		returnType = -1;
	}

	public void hash_partitions() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.hash_partitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.hash_partitions();
		}
		returnType = -1;
	}

	public void local_partitioned_index() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.local_partitioned_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.local_partitioned_index();
		}
		returnType = -1;
	}

	public void partition_item() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.partition_item();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.partition_item();
		}
		returnType = -1;
	}

	public void range_values_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.range_values_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.range_values_clause();
		}
		returnType = -1;
	}

	public void table_partition_description() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_partition_description();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_partition_description();
		}
		returnType = -1;
	}

	public void individual_hash_partitions() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.individual_hash_partitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.individual_hash_partitions();
		}
		returnType = -1;
	}

	public void hash_partitions_by_quantity() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.hash_partitions_by_quantity();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.hash_partitions_by_quantity();
		}
		returnType = -1;
	}

	public void partition_storage_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.partition_storage_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.partition_storage_clause();
		}
		returnType = -1;
	}

	public void external_table_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.external_table_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.external_table_spec();
		}
		returnType = -1;
	}

	public void reject_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.reject_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.reject_spec();
		}
		returnType = -1;
	}

	public void storage_params() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.storage_params();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.storage_params();
		}
		returnType = -1;
	}

	public void constraint_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.constraint_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.constraint_name();
		}
		returnType = -1;
	}

	public void pk_spec_constr() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.pk_spec_constr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.pk_spec_constr();
		}
		returnType = -1;
	}

	public void fk_spec_constr() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.fk_spec_constr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.fk_spec_constr();
		}
		returnType = -1;
	}

	public void unique_contsr() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.unique_contsr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.unique_contsr();
		}
		returnType = -1;
	}

	public void column_name_ddl_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_name_ddl_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_name_ddl_list();
		}
		returnType = -1;
	}

	public void referential_actions() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.referential_actions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.referential_actions();
		}
		returnType = -1;
	}

	public void alter_table() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alter_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alter_table();
		}
		returnType = -1;
	}

	public void constraint_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.constraint_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.constraint_clause();
		}
		returnType = -1;
	}

	public void add_syntax_1() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.add_syntax_1();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.add_syntax_1();
		}
		returnType = -1;
	}

	public void add_syntax_2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.add_syntax_2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.add_syntax_2();
		}
		returnType = -1;
	}

	public void modify_constraint_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.modify_constraint_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.modify_constraint_clause();
		}
		returnType = -1;
	}

	public void modify_syntax_2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.modify_syntax_2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.modify_syntax_2();
		}
		returnType = -1;
	}

	public void modify_syntax_1() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.modify_syntax_1();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.modify_syntax_1();
		}
		returnType = -1;
	}

	public void drop_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.drop_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.drop_clause();
		}
		returnType = -1;
	}

	public void column_def_alter() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_def_alter();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_def_alter();
		}
		returnType = -1;
	}

	public void inline_out_of_line_constraint() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.inline_out_of_line_constraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.inline_out_of_line_constraint();
		}
		returnType = -1;
	}

	public void constraint() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.constraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.constraint();
		}
		returnType = -1;
	}

	public void using_index_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.using_index_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.using_index_clause();
		}
		returnType = -1;
	}

	public void enable_disable_clause2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.enable_disable_clause2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.enable_disable_clause2();
		}
		returnType = -1;
	}

	public void index_properties() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.index_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.index_properties();
		}
		returnType = -1;
	}

	public void index_attributes() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.index_attributes();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.index_attributes();
		}
		returnType = -1;
	}

	public void global_partitioned_index() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.global_partitioned_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.global_partitioned_index();
		}
		returnType = -1;
	}

	public void index_partitioning_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.index_partitioning_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.index_partitioning_clause();
		}
		returnType = -1;
	}

	public void on_range_partitioned_table() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.on_range_partitioned_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.on_range_partitioned_table();
		}
		returnType = -1;
	}

	public void local_partition_item() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.local_partition_item();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.local_partition_item();
		}
		returnType = -1;
	}

	public void type_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.type_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.type_name();
		}
		returnType = -1;
	}

	public void record_item() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.record_item();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.record_item();
		}
		returnType = -1;
	}

	public void record_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.record_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.record_name();
		}
		returnType = -1;
	}

	public void v_column_def() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.v_column_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.v_column_def();
		}
		returnType = -1;
	}

	public void name_fragment() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.name_fragment();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.name_fragment();
		}
		returnType = -1;
	}

	public void serially_reusable_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.serially_reusable_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.serially_reusable_pragma();
		}
		returnType = -1;
	}

	public void package_obj_spec_ex() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_obj_spec_ex();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_obj_spec_ex();
		}
		returnType = -1;
	}

	public void package_obj_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_obj_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_obj_spec();
		}
		returnType = -1;
	}

	public void cond_comp_seq() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cond_comp_seq();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cond_comp_seq();
		}
		returnType = -1;
	}

	public void error_cond_compliation() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.error_cond_compliation();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.error_cond_compliation();
		}
		returnType = -1;
	}

	public void package_obj_body() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_obj_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_obj_body();
		}
		returnType = -1;
	}

	public void package_init_section() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.package_init_section();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.package_init_section();
		}
		returnType = -1;
	}

	public void seq_of_statements() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.seq_of_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.seq_of_statements();
		}
		returnType = -1;
	}

	public void variable_declaration() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.variable_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.variable_declaration();
		}
		returnType = -1;
	}

	public void subtype_declaration() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.subtype_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.subtype_declaration();
		}
		returnType = -1;
	}

	public void cursor_declaration() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cursor_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cursor_declaration();
		}
		returnType = -1;
	}

	public void cursor_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cursor_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cursor_spec();
		}
		returnType = -1;
	}

	public void procedure_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.procedure_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.procedure_spec();
		}
		returnType = -1;
	}

	public void function_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.function_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.function_spec();
		}
		returnType = -1;
	}

	public void exception_declaration() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exception_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exception_declaration();
		}
		returnType = -1;
	}

	public void exception_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exception_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exception_pragma();
		}
		returnType = -1;
	}

	public void restrict_ref_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.restrict_ref_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.restrict_ref_pragma();
		}
		returnType = -1;
	}

	public void interface_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.interface_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.interface_pragma();
		}
		returnType = -1;
	}

	public void builtin_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.builtin_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.builtin_pragma();
		}
		returnType = -1;
	}

	public void fipsflag_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.fipsflag_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.fipsflag_pragma();
		}
		returnType = -1;
	}

	public void timestamp_pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.timestamp_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.timestamp_pragma();
		}
		returnType = -1;
	}

	public void condition_compilation() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.condition_compilation();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.condition_compilation();
		}
		returnType = -1;
	}

	public void cond_comp_seq2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cond_comp_seq2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cond_comp_seq2();
		}
		returnType = -1;
	}

	public void function_declaration() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.function_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.function_declaration();
		}
		returnType = -1;
	}

	public void procedure_declaration() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.procedure_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.procedure_declaration();
		}
		returnType = -1;
	}

	public void variable_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.variable_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.variable_name();
		}
		returnType = -1;
	}

	public void default1() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.default1();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.default1();
		}
		returnType = -1;
	}

	public void table_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_name();
		}
		returnType = -1;
	}

	public void cursor_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cursor_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cursor_name();
		}
		returnType = -1;
	}

	public void argument_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.argument_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.argument_list();
		}
		returnType = -1;
	}

	public void select_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.select_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.select_command();
		}
		returnType = -1;
	}

	public void statement_tmpl() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.statement_tmpl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.statement_tmpl();
		}
		returnType = -1;
	}

	public void statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.statement();
		}
		returnType = -1;
	}

	public void label_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.label_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.label_name();
		}
		returnType = -1;
	}

	public void loop_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.loop_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.loop_statement();
		}
		returnType = -1;
	}

	public void forall_loop() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.forall_loop();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.forall_loop();
		}
		returnType = -1;
	}

	public void if_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.if_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.if_statement();
		}
		returnType = -1;
	}

	public void goto_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.goto_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.goto_statement();
		}
		returnType = -1;
	}

	public void raise_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.raise_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.raise_statement();
		}
		returnType = -1;
	}

	public void exit_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exit_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exit_statement();
		}
		returnType = -1;
	}

	public void null_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.null_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.null_statement();
		}
		returnType = -1;
	}

	public void return_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.return_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.return_statement();
		}
		returnType = -1;
	}

	public void case_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.case_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.case_statement();
		}
		returnType = -1;
	}

	public void immediate_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.immediate_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.immediate_command();
		}
		returnType = -1;
	}

	public void lock_table_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.lock_table_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.lock_table_statement();
		}
		returnType = -1;
	}

	public void open_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.open_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.open_statement();
		}
		returnType = -1;
	}

	public void close_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.close_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.close_statement();
		}
		returnType = -1;
	}

	public void fetch_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.fetch_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.fetch_statement();
		}
		returnType = -1;
	}

	public void set_transaction_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.set_transaction_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.set_transaction_command();
		}
		returnType = -1;
	}

	public void savepoint_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.savepoint_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.savepoint_statement();
		}
		returnType = -1;
	}

	public void sql_percentage() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sql_percentage();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sql_percentage();
		}
		returnType = -1;
	}

	public void declare_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.declare_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.declare_spec();
		}
		returnType = -1;
	}

	public void pragma() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.pragma();
		}
		returnType = -1;
	}

	public void rvalue() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.rvalue();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.rvalue();
		}
		returnType = -1;
	}

	public void function_call() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.function_call();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.function_call();
		}
		returnType = -1;
	}

	public void c_record_item_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.c_record_item_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.c_record_item_ref();
		}
		returnType = -1;
	}

	public void host_variable() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.host_variable();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.host_variable();
		}
		returnType = -1;
	}

	public void collection_method() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.collection_method();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.collection_method();
		}
		returnType = -1;
	}

	public void field_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.field_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.field_name();
		}
		returnType = -1;
	}

	public void percentage_type() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.percentage_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.percentage_type();
		}
		returnType = -1;
	}

	public void type_name_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.type_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.type_name_ref();
		}
		returnType = -1;
	}

	public void column_name_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_name_ref();
		}
		returnType = -1;
	}

	public void name_fragment_ex() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.name_fragment_ex();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.name_fragment_ex();
		}
		returnType = -1;
	}

	public void parameter_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.parameter_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.parameter_spec();
		}
		returnType = -1;
	}

	public void parameter_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.parameter_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.parameter_name();
		}
		returnType = -1;
	}

	public void character_set() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.character_set();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.character_set();
		}
		returnType = -1;
	}

	public void return_type() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.return_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.return_type();
		}
		returnType = -1;
	}

	public void exception_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exception_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exception_name();
		}
		returnType = -1;
	}

	public void exception_package_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exception_package_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exception_package_name();
		}
		returnType = -1;
	}

	public void complex_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.complex_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.complex_name();
		}
		returnType = -1;
	}

	public void oracle_err_number() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.oracle_err_number();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.oracle_err_number();
		}
		returnType = -1;
	}

	public void record_item_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.record_item_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.record_item_name();
		}
		returnType = -1;
	}

	public void func_proc_statements() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.func_proc_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.func_proc_statements();
		}
		returnType = -1;
	}

	public void declare_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.declare_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.declare_list();
		}
		returnType = -1;
	}

	public void plsql_block() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_block();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_block();
		}
		returnType = -1;
	}

	public void exception_section() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exception_section();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exception_section();
		}
		returnType = -1;
	}

	public void exception_handler() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exception_handler();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exception_handler();
		}
		returnType = -1;
	}

	public void call_argument_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.call_argument_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.call_argument_list();
		}
		returnType = -1;
	}

	public void forall_header() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.forall_header();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.forall_header();
		}
		returnType = -1;
	}

	public void loop_index() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.loop_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.loop_index();
		}
		returnType = -1;
	}

	public void forall_boundary() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.forall_boundary();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.forall_boundary();
		}
		returnType = -1;
	}

	public void numeric_loop_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.numeric_loop_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.numeric_loop_spec();
		}
		returnType = -1;
	}

	public void cursor_loop_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cursor_loop_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cursor_loop_spec();
		}
		returnType = -1;
	}

	public void boolean_literal() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.boolean_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.boolean_literal();
		}
		returnType = -1;
	}

	public void integer_expr() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.integer_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.integer_expr();
		}
		returnType = -1;
	}

	public void num_expression() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.num_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.num_expression();
		}
		returnType = -1;
	}

	public void num_term() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.num_term();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.num_term();
		}
		returnType = -1;
	}

	public void num_factor() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.num_factor();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.num_factor();
		}
		returnType = -1;
	}

	public void may_be_negate_base_expr() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.may_be_negate_base_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.may_be_negate_base_expr();
		}
		returnType = -1;
	}

	public void sign() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sign();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sign();
		}
		returnType = -1;
	}

	public void may_be_at_time_zone() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.may_be_at_time_zone();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.may_be_at_time_zone();
		}
		returnType = -1;
	}

	public void timezone_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.timezone_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.timezone_spec();
		}
		returnType = -1;
	}

	public void plsql_expr_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_expr_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_expr_list();
		}
		returnType = -1;
	}

	public void parentesized_plsql_exp_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.parentesized_plsql_exp_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.parentesized_plsql_exp_list();
		}
		returnType = -1;
	}

	public void logical_term() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.logical_term();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.logical_term();
		}
		returnType = -1;
	}

	public void maybe_neg_factor() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.maybe_neg_factor();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.maybe_neg_factor();
		}
		returnType = -1;
	}

	public void plsql_expression33() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_expression33();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_expression33();
		}
		returnType = -1;
	}

	public void subquery() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.subquery();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.subquery();
		}
		returnType = -1;
	}

	public void relational_op() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.relational_op();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.relational_op();
		}
		returnType = -1;
	}

	public void exp_set() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.exp_set();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.exp_set();
		}
		returnType = -1;
	}

	public void cast_proc() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.cast_proc();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.cast_proc();
		}
		returnType = -1;
	}

	public void trim_function() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.trim_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.trim_function();
		}
		returnType = -1;
	}

	public void count_function() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.count_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.count_function();
		}
		returnType = -1;
	}

	public void case_expression() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.case_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.case_expression();
		}
		returnType = -1;
	}

	public void dence_rank_analytics_func() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.dence_rank_analytics_func();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.dence_rank_analytics_func();
		}
		returnType = -1;
	}

	public void extract_date_function() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.extract_date_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.extract_date_function();
		}
		returnType = -1;
	}

	public void ident_percentage() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.ident_percentage();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.ident_percentage();
		}
		returnType = -1;
	}

	public void pseudo_column() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.pseudo_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.pseudo_column();
		}
		returnType = -1;
	}

	public void column_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_spec();
		}
		returnType = -1;
	}

	public void sequence_expr() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sequence_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sequence_expr();
		}
		returnType = -1;
	}

	public void order_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.order_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.order_clause();
		}
		returnType = -1;
	}

	public void asterisk_column() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.asterisk_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.asterisk_column();
		}
		returnType = -1;
	}

	public void ident_asterisk_column() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.ident_asterisk_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.ident_asterisk_column();
		}
		returnType = -1;
	}

	public void extract_consts() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.extract_consts();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.extract_consts();
		}
		returnType = -1;
	}

	public void date_literal() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.date_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.date_literal();
		}
		returnType = -1;
	}

	public void commit_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.commit_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.commit_statement();
		}
		returnType = -1;
	}

	public void elsif_statements() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.elsif_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.elsif_statements();
		}
		returnType = -1;
	}

	public void else_statements() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.else_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.else_statements();
		}
		returnType = -1;
	}

	public void insert_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.insert_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.insert_command();
		}
		returnType = -1;
	}

	public void update_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.update_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.update_command();
		}
		returnType = -1;
	}

	public void delete_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.delete_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.delete_command();
		}
		returnType = -1;
	}

	public void merge_command() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.merge_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.merge_command();
		}
		returnType = -1;
	}

	public void rollback_statement() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.rollback_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.rollback_statement();
		}
		returnType = -1;
	}

	public void select_first() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.select_first();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.select_first();
		}
		returnType = -1;
	}

	public void select_up_to_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.select_up_to_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.select_up_to_list();
		}
		returnType = -1;
	}

	public void into_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.into_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.into_clause();
		}
		returnType = -1;
	}

	public void table_reference_list_from() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_reference_list_from();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_reference_list_from();
		}
		returnType = -1;
	}

	public void where_condition() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.where_condition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.where_condition();
		}
		returnType = -1;
	}

	public void connect_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.connect_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.connect_clause();
		}
		returnType = -1;
	}

	public void group_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.group_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.group_clause();
		}
		returnType = -1;
	}

	public void update_clause() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.update_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.update_clause();
		}
		returnType = -1;
	}

	public void plsql_lvalue_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_lvalue_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_lvalue_list();
		}
		returnType = -1;
	}

	public void select_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.select_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.select_list();
		}
		returnType = -1;
	}

	public void displayed_column() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.displayed_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.displayed_column();
		}
		returnType = -1;
	}

	public void expr_column() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.expr_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.expr_column();
		}
		returnType = -1;
	}

	public void plsql_exp_list_using() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.plsql_exp_list_using();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.plsql_exp_list_using();
		}
		returnType = -1;
	}

	public void alter_system_session() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alter_system_session();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alter_system_session();
		}
		returnType = -1;
	}

	public void selected_table() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.selected_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.selected_table();
		}
		returnType = -1;
	}

	public void ansi_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.ansi_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.ansi_spec();
		}
		returnType = -1;
	}

	public void table_reference_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_reference_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_reference_list();
		}
		returnType = -1;
	}

	public void call_argument() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.call_argument();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.call_argument();
		}
		returnType = -1;
	}

	public void parameter_name_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.parameter_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.parameter_name_ref();
		}
		returnType = -1;
	}

	public void table_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_ref();
		}
		returnType = -1;
	}

	public void alias_ident() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.alias_ident();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.alias_ident();
		}
		returnType = -1;
	}

	public void row_proc() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.row_proc();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.row_proc();
		}
		returnType = -1;
	}

	public void the_proc() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.the_proc();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.the_proc();
		}
		returnType = -1;
	}

	public void from_subquery() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.from_subquery();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.from_subquery();
		}
		returnType = -1;
	}

	public void table_alias() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_alias();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_alias();
		}
		returnType = -1;
	}

	public void from_plain_table() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.from_plain_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.from_plain_table();
		}
		returnType = -1;
	}

	public void table_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_spec();
		}
		returnType = -1;
	}

	public void table_name2() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.table_name2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.table_name2();
		}
		returnType = -1;
	}

	public void link_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.link_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.link_name();
		}
		returnType = -1;
	}

	public void connect_clause_internal() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.connect_clause_internal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.connect_clause_internal();
		}
		returnType = -1;
	}

	public void sorted_def() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.sorted_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.sorted_def();
		}
		returnType = -1;
	}

	public void column_spec_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_spec_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_spec_list();
		}
		returnType = -1;
	}

	public void variable_ref() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.variable_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.variable_ref();
		}
		returnType = -1;
	}

	public void returning() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.returning();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.returning();
		}
		returnType = -1;
	}

	public void subquery_update() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.subquery_update();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.subquery_update();
		}
		returnType = -1;
	}

	public void simple_update() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.simple_update();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.simple_update();
		}
		returnType = -1;
	}

	public void when_action() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.when_action();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.when_action();
		}
		returnType = -1;
	}

	public void insert_columns() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.insert_columns();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.insert_columns();
		}
		returnType = -1;
	}

	public void lock_mode() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.lock_mode();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.lock_mode();
		}
		returnType = -1;
	}

	public void savepoint_name() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.savepoint_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.savepoint_name();
		}
		returnType = -1;
	}

	public void oracle_loader_params() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.oracle_loader_params();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.oracle_loader_params();
		}
		returnType = -1;
	}

	public void oracle_datapump_params() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.oracle_datapump_params();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.oracle_datapump_params();
		}
		returnType = -1;
	}

	public void directory_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.directory_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.directory_spec();
		}
		returnType = -1;
	}

	public void access_parameters() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.access_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.access_parameters();
		}
		returnType = -1;
	}

	public void write_access_parameters() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.write_access_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.write_access_parameters();
		}
		returnType = -1;
	}

	public void file_location_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.file_location_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.file_location_spec();
		}
		returnType = -1;
	}

	public void record_format_info() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.record_format_info();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.record_format_info();
		}
		returnType = -1;
	}

	public void field_definitions() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.field_definitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.field_definitions();
		}
		returnType = -1;
	}

	public void column_transforms() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.column_transforms();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.column_transforms();
		}
		returnType = -1;
	}

	public void rec_format() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.rec_format();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.rec_format();
		}
		returnType = -1;
	}

	public void rec_format_tail() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.rec_format_tail();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.rec_format_tail();
		}
		returnType = -1;
	}

	public void delim_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.delim_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.delim_spec();
		}
		returnType = -1;
	}

	public void trim_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.trim_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.trim_spec();
		}
		returnType = -1;
	}

	public void field_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.field_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.field_list();
		}
		returnType = -1;
	}

	public void const_str() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.const_str();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.const_str();
		}
		returnType = -1;
	}

	public void lobfile_attr_list() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.lobfile_attr_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.lobfile_attr_list();
		}
		returnType = -1;
	}

	public void field_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.field_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.field_spec();
		}
		returnType = -1;
	}

	public void pos_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.pos_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.pos_spec();
		}
		returnType = -1;
	}

	public void datatype_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.datatype_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.datatype_spec();
		}
		returnType = -1;
	}

	public void date_format_spec() throws antlr.RecognitionException,antlr.TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			super.date_format_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
		} else {
			super.date_format_spec();
		}
		returnType = -1;
	}

}
