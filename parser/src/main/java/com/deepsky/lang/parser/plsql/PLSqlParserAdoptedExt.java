package com.deepsky.lang.parser.plsql;

import com.intellij.lang.PsiBuilder;
import antlr.*;

public abstract class PLSqlParserAdoptedExt extends com.deepsky.generated.plsql.adopted.PLSqlParserAdopted {
	PsiBuilder builder;
	abstract public int getPredicting();

	public PLSqlParserAdoptedExt(TokenStream t, PsiBuilder b){
		super(t);
		builder = b;
	}

	public void no_one_should_call_me() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.no_one_should_call_me();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.no_one_should_call_me();
		}
		returnType = -1;
	}

	public void identifier() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.identifier();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.identifier();
		}
		returnType = -1;
	}

	public void start_rule() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.start_rule();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.start_rule();
		}
		returnType = -1;
	}

	public void start_rule_inner() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.start_rule_inner();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.start_rule_inner();
		}
		returnType = -1;
	}

	public void create_or_replace() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_or_replace();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_or_replace();
		}
		returnType = -1;
	}

	public void rename_object() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.rename_object();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.rename_object();
		}
		returnType = -1;
	}

	public void package_body() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_body();
		}
		returnType = -1;
	}

	public void package_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_spec();
		}
		returnType = -1;
	}

	public Integer function_body() throws RecognitionException,TokenStreamException{
		Integer ret;
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			ret =super.function_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
		ret = super.function_body();
		}
		returnType = -1;
		return ret;
	}

	public Integer procedure_body() throws RecognitionException,TokenStreamException{
		Integer ret;
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			ret =super.procedure_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
		ret = super.procedure_body();
		}
		returnType = -1;
		return ret;
	}

	public void create_trigger() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_trigger();
		}
		returnType = -1;
	}

	public void select_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.select_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.select_command();
		}
		returnType = -1;
	}

	public void insert_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.insert_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.insert_command();
		}
		returnType = -1;
	}

	public void update_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.update_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.update_command();
		}
		returnType = -1;
	}

	public void delete_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.delete_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.delete_command();
		}
		returnType = -1;
	}

	public void merge_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.merge_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.merge_command();
		}
		returnType = -1;
	}

	public void grant_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.grant_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.grant_command();
		}
		returnType = -1;
	}

	public void revoke_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.revoke_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.revoke_command();
		}
		returnType = -1;
	}

	public void rollback_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.rollback_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.rollback_statement();
		}
		returnType = -1;
	}

	public void commit_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.commit_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.commit_statement();
		}
		returnType = -1;
	}

	public void alter_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_command();
		}
		returnType = -1;
	}

	public void associate_statistics() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.associate_statistics();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.associate_statistics();
		}
		returnType = -1;
	}

	public void comment() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.comment();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.comment();
		}
		returnType = -1;
	}

	public void type_definition() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.type_definition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.type_definition();
		}
		returnType = -1;
	}

	public void drop_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.drop_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.drop_command();
		}
		returnType = -1;
	}

	public void truncate_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.truncate_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.truncate_command();
		}
		returnType = -1;
	}

	public void sqlplus_command_internal() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sqlplus_command_internal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sqlplus_command_internal();
		}
		returnType = -1;
	}

	public void schema_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.schema_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.schema_name();
		}
		returnType = -1;
	}

	public void table_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_ref();
		}
		returnType = -1;
	}

	public void callable_name_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.callable_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.callable_name_ref();
		}
		returnType = -1;
	}

	public void package_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_name();
		}
		returnType = -1;
	}

	public void index_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.index_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.index_name();
		}
		returnType = -1;
	}

	public void sequence_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sequence_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sequence_name();
		}
		returnType = -1;
	}

	public void object_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.object_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.object_name();
		}
		returnType = -1;
	}

	public void drop_tablespace() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.drop_tablespace();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.drop_tablespace();
		}
		returnType = -1;
	}

	public void column_association() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_association();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_association();
		}
		returnType = -1;
	}

	public void function_association() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.function_association();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.function_association();
		}
		returnType = -1;
	}

	public void storage_table_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.storage_table_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.storage_table_clause();
		}
		returnType = -1;
	}

	public void column_spec_ex() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_spec_ex();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_spec_ex();
		}
		returnType = -1;
	}

	public void using_statistics_type() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.using_statistics_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.using_statistics_type();
		}
		returnType = -1;
	}

	public void name_fragment2() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.name_fragment2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.name_fragment2();
		}
		returnType = -1;
	}

	public void column_name_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_name_ref();
		}
		returnType = -1;
	}

	public void default_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.default_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.default_clause();
		}
		returnType = -1;
	}

	public void numeric_literal() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.numeric_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.numeric_literal();
		}
		returnType = -1;
	}

	public void statistics_type() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.statistics_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.statistics_type();
		}
		returnType = -1;
	}

	public void comment_string() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.comment_string();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.comment_string();
		}
		returnType = -1;
	}

	public void string_literal() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.string_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.string_literal();
		}
		returnType = -1;
	}

	public void column_def() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_def();
		}
		returnType = -1;
	}

	public void column_name_ddl() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_name_ddl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_name_ddl();
		}
		returnType = -1;
	}

	public void type_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.type_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.type_spec();
		}
		returnType = -1;
	}

	public void column_constraint() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_constraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_constraint();
		}
		returnType = -1;
	}

	public void not_null() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.not_null();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.not_null();
		}
		returnType = -1;
	}

	public void row_movement_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.row_movement_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.row_movement_clause();
		}
		returnType = -1;
	}

	public void constraint_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.constraint_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.constraint_name();
		}
		returnType = -1;
	}

	public void condition() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.condition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.condition();
		}
		returnType = -1;
	}

	public void sqlplus_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sqlplus_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sqlplus_command();
		}
		returnType = -1;
	}

	public void identifier2() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.identifier2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.identifier2();
		}
		returnType = -1;
	}

	public void base_expression() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.base_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.base_expression();
		}
		returnType = -1;
	}

	public void datatype() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.datatype();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.datatype();
		}
		returnType = -1;
	}

	public void sqlplus_exec_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sqlplus_exec_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sqlplus_exec_statement();
		}
		returnType = -1;
	}

	public void plsql_expression() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_expression();
		}
		returnType = -1;
	}

	public void till_eol() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.till_eol();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.till_eol();
		}
		returnType = -1;
	}

	public void sqlplus_path() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sqlplus_path();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sqlplus_path();
		}
		returnType = -1;
	}

	public void begin_block() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.begin_block();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.begin_block();
		}
		returnType = -1;
	}

	public void plsql_lvalue() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_lvalue();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_lvalue();
		}
		returnType = -1;
	}

	public void assignment_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.assignment_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.assignment_statement();
		}
		returnType = -1;
	}

	public void procedure_call() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.procedure_call();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.procedure_call();
		}
		returnType = -1;
	}

	public void identifier3() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.identifier3();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.identifier3();
		}
		returnType = -1;
	}

	public void rename_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.rename_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.rename_table();
		}
		returnType = -1;
	}

	public void create_view() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_view();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_view();
		}
		returnType = -1;
	}

	public void create_materialized_view_log() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_materialized_view_log();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_materialized_view_log();
		}
		returnType = -1;
	}

	public void create_materialized_view() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_materialized_view();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_materialized_view();
		}
		returnType = -1;
	}

	public void create_view_column_def() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_view_column_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_view_column_def();
		}
		returnType = -1;
	}

	public void create_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_table();
		}
		returnType = -1;
	}

	public void create_temp_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_temp_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_temp_table();
		}
		returnType = -1;
	}

	public void create_index() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_index();
		}
		returnType = -1;
	}

	public void create_directory() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_directory();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_directory();
		}
		returnType = -1;
	}

	public void create_db_link() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_db_link();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_db_link();
		}
		returnType = -1;
	}

	public void create_sequence() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_sequence();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_sequence();
		}
		returnType = -1;
	}

	public void create_synonym() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_synonym();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_synonym();
		}
		returnType = -1;
	}

	public void create_tablespace() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_tablespace();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_tablespace();
		}
		returnType = -1;
	}

	public void create_user() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_user();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_user();
		}
		returnType = -1;
	}

	public void type_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.type_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.type_name();
		}
		returnType = -1;
	}

	public void type_name_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.type_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.type_name_ref();
		}
		returnType = -1;
	}

	public void record_item() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.record_item();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.record_item();
		}
		returnType = -1;
	}

	public void password() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.password();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.password();
		}
		returnType = -1;
	}

	public void create_user_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_user_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_user_spec();
		}
		returnType = -1;
	}

	public void tablespace_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.tablespace_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.tablespace_name();
		}
		returnType = -1;
	}

	public void file_specification() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.file_specification();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.file_specification();
		}
		returnType = -1;
	}

	public void tablespace_group_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.tablespace_group_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.tablespace_group_clause();
		}
		returnType = -1;
	}

	public void undo_tablespace_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.undo_tablespace_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.undo_tablespace_spec();
		}
		returnType = -1;
	}

	public void create_tablespace_rest() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_tablespace_rest();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_tablespace_rest();
		}
		returnType = -1;
	}

	public void extent_management_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.extent_management_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.extent_management_clause();
		}
		returnType = -1;
	}

	public void tablespace_retention_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.tablespace_retention_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.tablespace_retention_clause();
		}
		returnType = -1;
	}

	public void tablespace_logging_clauses() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.tablespace_logging_clauses();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.tablespace_logging_clauses();
		}
		returnType = -1;
	}

	public void tablespace_state_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.tablespace_state_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.tablespace_state_clause();
		}
		returnType = -1;
	}

	public void autoextend_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.autoextend_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.autoextend_clause();
		}
		returnType = -1;
	}

	public void datafile_tempfile_clauses() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.datafile_tempfile_clauses();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.datafile_tempfile_clauses();
		}
		returnType = -1;
	}

	public void alter_tablespace() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_tablespace();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_tablespace();
		}
		returnType = -1;
	}

	public void sequence_opt() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sequence_opt();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sequence_opt();
		}
		returnType = -1;
	}

	public void synonym_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.synonym_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.synonym_name();
		}
		returnType = -1;
	}

	public void synonym_obj() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.synonym_obj();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.synonym_obj();
		}
		returnType = -1;
	}

	public void trigger_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.trigger_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.trigger_name();
		}
		returnType = -1;
	}

	public void dml_trigger() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.dml_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.dml_trigger();
		}
		returnType = -1;
	}

	public void ddl_trigger() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.ddl_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.ddl_trigger();
		}
		returnType = -1;
	}

	public void db_event_trigger() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.db_event_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.db_event_trigger();
		}
		returnType = -1;
	}

	public void instead_of_trigger() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.instead_of_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.instead_of_trigger();
		}
		returnType = -1;
	}

	public void for_each() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.for_each();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.for_each();
		}
		returnType = -1;
	}

	public void referencing_old_new() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.referencing_old_new();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.referencing_old_new();
		}
		returnType = -1;
	}

	public void trigger_when() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.trigger_when();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.trigger_when();
		}
		returnType = -1;
	}

	public void trigger_target() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.trigger_target();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.trigger_target();
		}
		returnType = -1;
	}

	public void insert_update_delete() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.insert_update_delete();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.insert_update_delete();
		}
		returnType = -1;
	}

	public void alter_trigger() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_trigger();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_trigger();
		}
		returnType = -1;
	}

	public void enable_disable_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.enable_disable_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.enable_disable_clause();
		}
		returnType = -1;
	}

	public void index_column_spec_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.index_column_spec_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.index_column_spec_list();
		}
		returnType = -1;
	}

	public void physical_properties() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.physical_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.physical_properties();
		}
		returnType = -1;
	}

	public void table_pair() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_pair();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_pair();
		}
		returnType = -1;
	}

	public void table_name_ddl() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_name_ddl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_name_ddl();
		}
		returnType = -1;
	}

	public void table_level_constraint() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_level_constraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_level_constraint();
		}
		returnType = -1;
	}

	public void nested_tab_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.nested_tab_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.nested_tab_spec();
		}
		returnType = -1;
	}

	public void lob_storage_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lob_storage_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lob_storage_clause();
		}
		returnType = -1;
	}

	public void select_expression() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.select_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.select_expression();
		}
		returnType = -1;
	}

	public void cache_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cache_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cache_clause();
		}
		returnType = -1;
	}

	public void lob_parameters() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lob_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lob_parameters();
		}
		returnType = -1;
	}

	public void storage_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.storage_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.storage_spec();
		}
		returnType = -1;
	}

	public void logging_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.logging_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.logging_clause();
		}
		returnType = -1;
	}

	public void deferred_segment_creation() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.deferred_segment_creation();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.deferred_segment_creation();
		}
		returnType = -1;
	}

	public void segment_attributes_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.segment_attributes_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.segment_attributes_clause();
		}
		returnType = -1;
	}

	public void organization_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.organization_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.organization_spec();
		}
		returnType = -1;
	}

	public void cluster_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cluster_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cluster_clause();
		}
		returnType = -1;
	}

	public void table_properties() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_properties();
		}
		returnType = -1;
	}

	public void physical_attributes_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.physical_attributes_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.physical_attributes_clause();
		}
		returnType = -1;
	}

	public void table_compression() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_compression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_compression();
		}
		returnType = -1;
	}

	public void table_partitioning_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_partitioning_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_partitioning_clause();
		}
		returnType = -1;
	}

	public void parallel_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.parallel_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.parallel_clause();
		}
		returnType = -1;
	}

	public void alter_table_options() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_table_options();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_table_options();
		}
		returnType = -1;
	}

	public void monitoring_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.monitoring_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.monitoring_clause();
		}
		returnType = -1;
	}

	public void index_org_overflow_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.index_org_overflow_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.index_org_overflow_clause();
		}
		returnType = -1;
	}

	public void range_partitions() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.range_partitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.range_partitions();
		}
		returnType = -1;
	}

	public void hash_partitions() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.hash_partitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.hash_partitions();
		}
		returnType = -1;
	}

	public void local_partitioned_index() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.local_partitioned_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.local_partitioned_index();
		}
		returnType = -1;
	}

	public void partition_item() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.partition_item();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.partition_item();
		}
		returnType = -1;
	}

	public void partition_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.partition_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.partition_name();
		}
		returnType = -1;
	}

	public void range_values_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.range_values_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.range_values_clause();
		}
		returnType = -1;
	}

	public void table_partition_description() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_partition_description();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_partition_description();
		}
		returnType = -1;
	}

	public void individual_hash_partitions() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.individual_hash_partitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.individual_hash_partitions();
		}
		returnType = -1;
	}

	public void hash_partitions_by_quantity() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.hash_partitions_by_quantity();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.hash_partitions_by_quantity();
		}
		returnType = -1;
	}

	public void hash_partition_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.hash_partition_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.hash_partition_spec();
		}
		returnType = -1;
	}

	public void partition_storage_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.partition_storage_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.partition_storage_clause();
		}
		returnType = -1;
	}

	public void external_table_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.external_table_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.external_table_spec();
		}
		returnType = -1;
	}

	public void ext_table_properties() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.ext_table_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.ext_table_properties();
		}
		returnType = -1;
	}

	public void reject_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.reject_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.reject_spec();
		}
		returnType = -1;
	}

	public void storage_params() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.storage_params();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.storage_params();
		}
		returnType = -1;
	}

	public void pk_spec_constr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.pk_spec_constr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.pk_spec_constr();
		}
		returnType = -1;
	}

	public void fk_spec_constr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.fk_spec_constr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.fk_spec_constr();
		}
		returnType = -1;
	}

	public void check_condition() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.check_condition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.check_condition();
		}
		returnType = -1;
	}

	public void unique_constr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.unique_constr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.unique_constr();
		}
		returnType = -1;
	}

	public void owner_column_name_ref_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.owner_column_name_ref_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.owner_column_name_ref_list();
		}
		returnType = -1;
	}

	public void using_index_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.using_index_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.using_index_clause();
		}
		returnType = -1;
	}

	public void column_name_ref_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_name_ref_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_name_ref_list();
		}
		returnType = -1;
	}

	public void referential_actions() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.referential_actions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.referential_actions();
		}
		returnType = -1;
	}

	public void alter_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_table();
		}
		returnType = -1;
	}

	public void alter_table_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_table_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_table_spec();
		}
		returnType = -1;
	}

	public void add_syntax_1() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.add_syntax_1();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.add_syntax_1();
		}
		returnType = -1;
	}

	public void add_syntax_2() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.add_syntax_2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.add_syntax_2();
		}
		returnType = -1;
	}

	public void modify_constraint_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.modify_constraint_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.modify_constraint_clause();
		}
		returnType = -1;
	}

	public void alter_column_def() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_column_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_column_def();
		}
		returnType = -1;
	}

	public void drop_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.drop_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.drop_clause();
		}
		returnType = -1;
	}

	public void inline_out_of_line_constraint() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.inline_out_of_line_constraint();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.inline_out_of_line_constraint();
		}
		returnType = -1;
	}

	public void index_properties() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.index_properties();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.index_properties();
		}
		returnType = -1;
	}

	public void index_attributes() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.index_attributes();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.index_attributes();
		}
		returnType = -1;
	}

	public void global_partitioned_index() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.global_partitioned_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.global_partitioned_index();
		}
		returnType = -1;
	}

	public void index_partitioning_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.index_partitioning_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.index_partitioning_clause();
		}
		returnType = -1;
	}

	public void on_range_partitioned_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.on_range_partitioned_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.on_range_partitioned_table();
		}
		returnType = -1;
	}

	public void local_partition_item() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.local_partition_item();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.local_partition_item();
		}
		returnType = -1;
	}

	public void record_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.record_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.record_name();
		}
		returnType = -1;
	}

	public void view_name_ddl() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.view_name_ddl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.view_name_ddl();
		}
		returnType = -1;
	}

	public void v_column_def() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.v_column_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.v_column_def();
		}
		returnType = -1;
	}

	public void create_mv_attributes() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.create_mv_attributes();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.create_mv_attributes();
		}
		returnType = -1;
	}

	public void mv_log_physical_props() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.mv_log_physical_props();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.mv_log_physical_props();
		}
		returnType = -1;
	}

	public void mv_log_with_param() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.mv_log_with_param();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.mv_log_with_param();
		}
		returnType = -1;
	}

	public void column_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_spec();
		}
		returnType = -1;
	}

	public void refresh_attributes() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.refresh_attributes();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.refresh_attributes();
		}
		returnType = -1;
	}

	public void mv_build_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.mv_build_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.mv_build_clause();
		}
		returnType = -1;
	}

	public void mv_index_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.mv_index_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.mv_index_clause();
		}
		returnType = -1;
	}

	public void date_expr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.date_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.date_expr();
		}
		returnType = -1;
	}

	public void num_expression() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.num_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.num_expression();
		}
		returnType = -1;
	}

	public void serially_reusable_pragma() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.serially_reusable_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.serially_reusable_pragma();
		}
		returnType = -1;
	}

	public void package_obj_spec_ex() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_obj_spec_ex();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_obj_spec_ex();
		}
		returnType = -1;
	}

	public void package_obj_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_obj_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_obj_spec();
		}
		returnType = -1;
	}

	public void cond_comp_seq() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cond_comp_seq();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cond_comp_seq();
		}
		returnType = -1;
	}

	public void error_cond_compliation() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.error_cond_compliation();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.error_cond_compliation();
		}
		returnType = -1;
	}

	public void package_obj_body() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_obj_body();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_obj_body();
		}
		returnType = -1;
	}

	public void package_init_section() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.package_init_section();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.package_init_section();
		}
		returnType = -1;
	}

	public void seq_of_statements() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.seq_of_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.seq_of_statements();
		}
		returnType = -1;
	}

	public void subtype_declaration() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.subtype_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.subtype_declaration();
		}
		returnType = -1;
	}

	public void cursor_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cursor_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cursor_spec();
		}
		returnType = -1;
	}

	public void pragmas() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.pragmas();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.pragmas();
		}
		returnType = -1;
	}

	public void variable_declaration() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.variable_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.variable_declaration();
		}
		returnType = -1;
	}

	public void exception_declaration() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exception_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exception_declaration();
		}
		returnType = -1;
	}

	public void complex_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.complex_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.complex_name();
		}
		returnType = -1;
	}

	public void condition_compilation() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.condition_compilation();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.condition_compilation();
		}
		returnType = -1;
	}

	public void cond_comp_seq2() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cond_comp_seq2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cond_comp_seq2();
		}
		returnType = -1;
	}

	public void variable_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.variable_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.variable_name();
		}
		returnType = -1;
	}

	public void default1() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.default1();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.default1();
		}
		returnType = -1;
	}

	public void cursor_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cursor_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cursor_name();
		}
		returnType = -1;
	}

	public void argument_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.argument_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.argument_list();
		}
		returnType = -1;
	}

	public void return_type() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.return_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.return_type();
		}
		returnType = -1;
	}

	public void select_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.select_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.select_statement();
		}
		returnType = -1;
	}

	public void statement_tmpl() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.statement_tmpl();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.statement_tmpl();
		}
		returnType = -1;
	}

	public void statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.statement();
		}
		returnType = -1;
	}

	public void label_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.label_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.label_name();
		}
		returnType = -1;
	}

	public void loop_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.loop_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.loop_statement();
		}
		returnType = -1;
	}

	public void forall_loop() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.forall_loop();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.forall_loop();
		}
		returnType = -1;
	}

	public void if_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.if_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.if_statement();
		}
		returnType = -1;
	}

	public void goto_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.goto_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.goto_statement();
		}
		returnType = -1;
	}

	public void raise_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.raise_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.raise_statement();
		}
		returnType = -1;
	}

	public void exit_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exit_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exit_statement();
		}
		returnType = -1;
	}

	public void case_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.case_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.case_statement();
		}
		returnType = -1;
	}

	public void immediate_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.immediate_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.immediate_command();
		}
		returnType = -1;
	}

	public void lock_table_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lock_table_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lock_table_statement();
		}
		returnType = -1;
	}

	public void open_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.open_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.open_statement();
		}
		returnType = -1;
	}

	public void close_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.close_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.close_statement();
		}
		returnType = -1;
	}

	public void fetch_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.fetch_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.fetch_statement();
		}
		returnType = -1;
	}

	public void set_transaction_command() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.set_transaction_command();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.set_transaction_command();
		}
		returnType = -1;
	}

	public void savepoint_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.savepoint_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.savepoint_statement();
		}
		returnType = -1;
	}

	public void name_fragment_ex() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.name_fragment_ex();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.name_fragment_ex();
		}
		returnType = -1;
	}

	public void exec_name_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exec_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exec_name_ref();
		}
		returnType = -1;
	}

	public void sql_percentage() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sql_percentage();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sql_percentage();
		}
		returnType = -1;
	}

	public void pragma_autonomous_transaction() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.pragma_autonomous_transaction();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.pragma_autonomous_transaction();
		}
		returnType = -1;
	}

	public void rvalue() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.rvalue();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.rvalue();
		}
		returnType = -1;
	}

	public void name_fragment() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.name_fragment();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.name_fragment();
		}
		returnType = -1;
	}

	public void function_call() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.function_call();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.function_call();
		}
		returnType = -1;
	}

	public void c_record_item_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.c_record_item_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.c_record_item_ref();
		}
		returnType = -1;
	}

	public void bind_variable() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.bind_variable();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.bind_variable();
		}
		returnType = -1;
	}

	public void collection_method() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.collection_method();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.collection_method();
		}
		returnType = -1;
	}

	public void field_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.field_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.field_name();
		}
		returnType = -1;
	}

	public void datatype_param_info() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.datatype_param_info();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.datatype_param_info();
		}
		returnType = -1;
	}

	public void percentage_type() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.percentage_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.percentage_type();
		}
		returnType = -1;
	}

	public void dblink_percentage_type() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.dblink_percentage_type();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.dblink_percentage_type();
		}
		returnType = -1;
	}

	public void percentage_type_w_schema() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.percentage_type_w_schema();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.percentage_type_w_schema();
		}
		returnType = -1;
	}

	public void table_ref_db_link() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_ref_db_link();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_ref_db_link();
		}
		returnType = -1;
	}

	public void column_name_ref_db_link() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_name_ref_db_link();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_name_ref_db_link();
		}
		returnType = -1;
	}

	public void type_name_ref_single() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.type_name_ref_single();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.type_name_ref_single();
		}
		returnType = -1;
	}

	public void sequence_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sequence_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sequence_ref();
		}
		returnType = -1;
	}

	public void identifier4() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.identifier4();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.identifier4();
		}
		returnType = -1;
	}

	public void parameter_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.parameter_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.parameter_spec();
		}
		returnType = -1;
	}

	public void parameter_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.parameter_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.parameter_name();
		}
		returnType = -1;
	}

	public void character_set() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.character_set();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.character_set();
		}
		returnType = -1;
	}

	public void procedure_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.procedure_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.procedure_spec();
		}
		returnType = -1;
	}

	public void procedure_declaration() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.procedure_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.procedure_declaration();
		}
		returnType = -1;
	}

	public void function_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.function_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.function_spec();
		}
		returnType = -1;
	}

	public void function_declaration() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.function_declaration();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.function_declaration();
		}
		returnType = -1;
	}

	public void exception_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exception_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exception_name();
		}
		returnType = -1;
	}

	public void exception_package_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exception_package_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exception_package_name();
		}
		returnType = -1;
	}

	public void exception_pragma() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exception_pragma();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exception_pragma();
		}
		returnType = -1;
	}

	public void oracle_err_number() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.oracle_err_number();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.oracle_err_number();
		}
		returnType = -1;
	}

	public void record_item_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.record_item_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.record_item_name();
		}
		returnType = -1;
	}

	public void func_proc_statements() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.func_proc_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.func_proc_statements();
		}
		returnType = -1;
	}

	public void declare_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.declare_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.declare_list();
		}
		returnType = -1;
	}

	public void plsql_block() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_block();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_block();
		}
		returnType = -1;
	}

	public void exception_section() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exception_section();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exception_section();
		}
		returnType = -1;
	}

	public void plsql_block_end() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_block_end();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_block_end();
		}
		returnType = -1;
	}

	public void exception_handler() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exception_handler();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exception_handler();
		}
		returnType = -1;
	}

	public void declare_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.declare_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.declare_spec();
		}
		returnType = -1;
	}

	public void call_argument_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.call_argument_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.call_argument_list();
		}
		returnType = -1;
	}

	public void null_statement() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.null_statement();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.null_statement();
		}
		returnType = -1;
	}

	public void forall_header() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.forall_header();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.forall_header();
		}
		returnType = -1;
	}

	public void num_loop_index() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.num_loop_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.num_loop_index();
		}
		returnType = -1;
	}

	public void forall_boundary() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.forall_boundary();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.forall_boundary();
		}
		returnType = -1;
	}

	public void numeric_loop_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.numeric_loop_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.numeric_loop_spec();
		}
		returnType = -1;
	}

	public void cursor_loop_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cursor_loop_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cursor_loop_spec();
		}
		returnType = -1;
	}

	public void numeric_loop_spec2() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.numeric_loop_spec2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.numeric_loop_spec2();
		}
		returnType = -1;
	}

	public void cursor_loop_index() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cursor_loop_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cursor_loop_index();
		}
		returnType = -1;
	}

	public void cursor_loop_spec2() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cursor_loop_spec2();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cursor_loop_spec2();
		}
		returnType = -1;
	}

	public void cursor_name_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cursor_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cursor_name_ref();
		}
		returnType = -1;
	}

	public void boolean_literal() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.boolean_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.boolean_literal();
		}
		returnType = -1;
	}

	public void integer_expr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.integer_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.integer_expr();
		}
		returnType = -1;
	}

	public void num_term() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.num_term();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.num_term();
		}
		returnType = -1;
	}

	public void num_factor() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.num_factor();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.num_factor();
		}
		returnType = -1;
	}

	public void may_be_negate_base_expr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.may_be_negate_base_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.may_be_negate_base_expr();
		}
		returnType = -1;
	}

	public void sign() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sign();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sign();
		}
		returnType = -1;
	}

	public void may_be_at_time_zone() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.may_be_at_time_zone();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.may_be_at_time_zone();
		}
		returnType = -1;
	}

	public void timezone_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.timezone_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.timezone_spec();
		}
		returnType = -1;
	}

	public void expr_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.expr_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.expr_list();
		}
		returnType = -1;
	}

	public void parentesized_exp_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.parentesized_exp_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.parentesized_exp_list();
		}
		returnType = -1;
	}

	public void logical_term() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.logical_term();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.logical_term();
		}
		returnType = -1;
	}

	public void maybe_neg_factor() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.maybe_neg_factor();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.maybe_neg_factor();
		}
		returnType = -1;
	}

	public void plsql_expression33() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_expression33();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_expression33();
		}
		returnType = -1;
	}

	public void subquery() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.subquery();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.subquery();
		}
		returnType = -1;
	}

	public void relational_op() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.relational_op();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.relational_op();
		}
		returnType = -1;
	}

	public void exp_set() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.exp_set();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.exp_set();
		}
		returnType = -1;
	}

	public void cast_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cast_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cast_function();
		}
		returnType = -1;
	}

	public void decode_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.decode_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.decode_function();
		}
		returnType = -1;
	}

	public void trim_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.trim_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.trim_function();
		}
		returnType = -1;
	}

	public void count_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.count_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.count_function();
		}
		returnType = -1;
	}

	public void case_expression() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.case_expression();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.case_expression();
		}
		returnType = -1;
	}

	public void multiset_operator() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.multiset_operator();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.multiset_operator();
		}
		returnType = -1;
	}

	public void lag_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lag_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lag_function();
		}
		returnType = -1;
	}

	public void lead_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lead_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lead_function();
		}
		returnType = -1;
	}

	public void dence_rank_analytics_func() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.dence_rank_analytics_func();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.dence_rank_analytics_func();
		}
		returnType = -1;
	}

	public void extract_date_function() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.extract_date_function();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.extract_date_function();
		}
		returnType = -1;
	}

	public void ident_percentage() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.ident_percentage();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.ident_percentage();
		}
		returnType = -1;
	}

	public void pseudo_column() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.pseudo_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.pseudo_column();
		}
		returnType = -1;
	}

	public void sequence_expr() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sequence_expr();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sequence_expr();
		}
		returnType = -1;
	}

	public void builtin_callable_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.builtin_callable_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.builtin_callable_name();
		}
		returnType = -1;
	}

	public void dence_rank__arg_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.dence_rank__arg_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.dence_rank__arg_list();
		}
		returnType = -1;
	}

	public void query_partition_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.query_partition_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.query_partition_clause();
		}
		returnType = -1;
	}

	public void order_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.order_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.order_clause();
		}
		returnType = -1;
	}

	public void lag_lead_func_arg_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lag_lead_func_arg_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lag_lead_func_arg_list();
		}
		returnType = -1;
	}

	public void builtin_func_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.builtin_func_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.builtin_func_name();
		}
		returnType = -1;
	}

	public void call_argument() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.call_argument();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.call_argument();
		}
		returnType = -1;
	}

	public void asterisk_column() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.asterisk_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.asterisk_column();
		}
		returnType = -1;
	}

	public void ident_asterisk_column() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.ident_asterisk_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.ident_asterisk_column();
		}
		returnType = -1;
	}

	public void extract_date_func_arg_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.extract_date_func_arg_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.extract_date_func_arg_list();
		}
		returnType = -1;
	}

	public void extract_consts() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.extract_consts();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.extract_consts();
		}
		returnType = -1;
	}

	public void trim_func_arg_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.trim_func_arg_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.trim_func_arg_list();
		}
		returnType = -1;
	}

	public void decode_function_arg_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.decode_function_arg_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.decode_function_arg_list();
		}
		returnType = -1;
	}

	public void cast_func_arg_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.cast_func_arg_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.cast_func_arg_list();
		}
		returnType = -1;
	}

	public void date_literal() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.date_literal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.date_literal();
		}
		returnType = -1;
	}

	public void elsif_statements() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.elsif_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.elsif_statements();
		}
		returnType = -1;
	}

	public void else_statements() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.else_statements();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.else_statements();
		}
		returnType = -1;
	}

	public void revoke_system_privilege() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.revoke_system_privilege();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.revoke_system_privilege();
		}
		returnType = -1;
	}

	public void revoke_object_privilege() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.revoke_object_privilege();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.revoke_object_privilege();
		}
		returnType = -1;
	}

	public void system_privilege() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.system_privilege();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.system_privilege();
		}
		returnType = -1;
	}

	public void user_role() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.user_role();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.user_role();
		}
		returnType = -1;
	}

	public void grantee() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.grantee();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.grantee();
		}
		returnType = -1;
	}

	public void object_privilege() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.object_privilege();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.object_privilege();
		}
		returnType = -1;
	}

	public void grant_object_privilege() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.grant_object_privilege();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.grant_object_privilege();
		}
		returnType = -1;
	}

	public void grant_system_privilege() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.grant_system_privilege();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.grant_system_privilege();
		}
		returnType = -1;
	}

	public void select_first() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.select_first();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.select_first();
		}
		returnType = -1;
	}

	public void select_up_to_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.select_up_to_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.select_up_to_list();
		}
		returnType = -1;
	}

	public void into_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.into_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.into_clause();
		}
		returnType = -1;
	}

	public void table_reference_list_from() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_reference_list_from();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_reference_list_from();
		}
		returnType = -1;
	}

	public void where_condition() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.where_condition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.where_condition();
		}
		returnType = -1;
	}

	public void connect_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.connect_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.connect_clause();
		}
		returnType = -1;
	}

	public void group_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.group_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.group_clause();
		}
		returnType = -1;
	}

	public void update_clause() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.update_clause();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.update_clause();
		}
		returnType = -1;
	}

	public void plsql_lvalue_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_lvalue_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_lvalue_list();
		}
		returnType = -1;
	}

	public void displayed_column() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.displayed_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.displayed_column();
		}
		returnType = -1;
	}

	public void expr_column() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.expr_column();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.expr_column();
		}
		returnType = -1;
	}

	public void alias() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alias();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alias();
		}
		returnType = -1;
	}

	public void plsql_exp_list_using() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.plsql_exp_list_using();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.plsql_exp_list_using();
		}
		returnType = -1;
	}

	public void alter_system_session() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_system_session();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_system_session();
		}
		returnType = -1;
	}

	public void alter_index() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alter_index();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alter_index();
		}
		returnType = -1;
	}

	public void selected_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.selected_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.selected_table();
		}
		returnType = -1;
	}

	public void ansi_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.ansi_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.ansi_spec();
		}
		returnType = -1;
	}

	public void table_reference_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_reference_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_reference_list();
		}
		returnType = -1;
	}

	public void parameter_name_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.parameter_name_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.parameter_name_ref();
		}
		returnType = -1;
	}

	public void alias_ident() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.alias_ident();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.alias_ident();
		}
		returnType = -1;
	}

	public void correlation_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.correlation_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.correlation_name();
		}
		returnType = -1;
	}

	public void correlation_name_ident() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.correlation_name_ident();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.correlation_name_ident();
		}
		returnType = -1;
	}

	public void identifier_alias() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.identifier_alias();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.identifier_alias();
		}
		returnType = -1;
	}

	public void table_func() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_func();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_func();
		}
		returnType = -1;
	}

	public void the_proc() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.the_proc();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.the_proc();
		}
		returnType = -1;
	}

	public void from_subquery() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.from_subquery();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.from_subquery();
		}
		returnType = -1;
	}

	public void table_alias() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_alias();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_alias();
		}
		returnType = -1;
	}

	public void from_plain_table() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.from_plain_table();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.from_plain_table();
		}
		returnType = -1;
	}

	public void table_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_spec();
		}
		returnType = -1;
	}

	public void ansi_condition() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.ansi_condition();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.ansi_condition();
		}
		returnType = -1;
	}

	public void table_ref_ex() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.table_ref_ex();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.table_ref_ex();
		}
		returnType = -1;
	}

	public void link_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.link_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.link_name();
		}
		returnType = -1;
	}

	public void connect_clause_internal() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.connect_clause_internal();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.connect_clause_internal();
		}
		returnType = -1;
	}

	public void sorted_def() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.sorted_def();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.sorted_def();
		}
		returnType = -1;
	}

	public void column_spec_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_spec_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_spec_list();
		}
		returnType = -1;
	}

	public void variable_ref() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.variable_ref();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.variable_ref();
		}
		returnType = -1;
	}

	public void returning() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.returning();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.returning();
		}
		returnType = -1;
	}

	public void column_spec_list_wo_paren() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_spec_list_wo_paren();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_spec_list_wo_paren();
		}
		returnType = -1;
	}

	public void when_action() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.when_action();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.when_action();
		}
		returnType = -1;
	}

	public void insert_columns() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.insert_columns();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.insert_columns();
		}
		returnType = -1;
	}

	public void subquery_update() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.subquery_update();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.subquery_update();
		}
		returnType = -1;
	}

	public void simple_update() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.simple_update();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.simple_update();
		}
		returnType = -1;
	}

	public void lock_mode() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lock_mode();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lock_mode();
		}
		returnType = -1;
	}

	public void savepoint_name() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.savepoint_name();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.savepoint_name();
		}
		returnType = -1;
	}

	public void directory_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.directory_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.directory_spec();
		}
		returnType = -1;
	}

	public void access_parameters() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.access_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.access_parameters();
		}
		returnType = -1;
	}

	public void write_access_parameters() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.write_access_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.write_access_parameters();
		}
		returnType = -1;
	}

	public void location() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.location();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.location();
		}
		returnType = -1;
	}

	public void write_access_parameters_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.write_access_parameters_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.write_access_parameters_spec();
		}
		returnType = -1;
	}

	public void file_location_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.file_location_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.file_location_spec();
		}
		returnType = -1;
	}

	public void loader_access_parameters() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.loader_access_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.loader_access_parameters();
		}
		returnType = -1;
	}

	public void record_format_info() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.record_format_info();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.record_format_info();
		}
		returnType = -1;
	}

	public void field_definitions() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.field_definitions();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.field_definitions();
		}
		returnType = -1;
	}

	public void column_transforms() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.column_transforms();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.column_transforms();
		}
		returnType = -1;
	}

	public void rec_format() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.rec_format();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.rec_format();
		}
		returnType = -1;
	}

	public void rec_format_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.rec_format_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.rec_format_spec();
		}
		returnType = -1;
	}

	public void delim_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.delim_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.delim_spec();
		}
		returnType = -1;
	}

	public void trim_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.trim_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.trim_spec();
		}
		returnType = -1;
	}

	public void field_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.field_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.field_list();
		}
		returnType = -1;
	}

	public void transform() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.transform();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.transform();
		}
		returnType = -1;
	}

	public void const_str() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.const_str();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.const_str();
		}
		returnType = -1;
	}

	public void lobfile_attr_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.lobfile_attr_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.lobfile_attr_list();
		}
		returnType = -1;
	}

	public void field_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.field_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.field_spec();
		}
		returnType = -1;
	}

	public void pos_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.pos_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.pos_spec();
		}
		returnType = -1;
	}

	public void datatype_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.datatype_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.datatype_spec();
		}
		returnType = -1;
	}

	public void date_format_spec() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.date_format_spec();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.date_format_spec();
		}
		returnType = -1;
	}

}
