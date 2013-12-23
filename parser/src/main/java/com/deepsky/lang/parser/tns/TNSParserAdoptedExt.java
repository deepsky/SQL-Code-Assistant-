package com.deepsky.lang.parser.tns;

import com.intellij.lang.PsiBuilder;
import antlr.*;

public abstract class TNSParserAdoptedExt extends com.deepsky.generated.tns.adopted.TNSParserAdopted {
	PsiBuilder builder;
	abstract public int getPredicting();

	public TNSParserAdoptedExt(TokenStream t, PsiBuilder b){
		super(t);
		builder = b;
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

	public void net_service_desc() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.net_service_desc();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.net_service_desc();
		}
		returnType = -1;
	}

	public void network_alias() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.network_alias();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.network_alias();
		}
		returnType = -1;
	}

	public void description_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.description_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.description_list();
		}
		returnType = -1;
	}

	public void description() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.description();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.description();
		}
		returnType = -1;
	}

	public void description_params() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.description_params();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.description_params();
		}
		returnType = -1;
	}

	public void multi_address_parameters() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.multi_address_parameters();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.multi_address_parameters();
		}
		returnType = -1;
	}

	public void address_info() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.address_info();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.address_info();
		}
		returnType = -1;
	}

	public void connect_data() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.connect_data();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.connect_data();
		}
		returnType = -1;
	}

	public void security() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.security();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.security();
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

	public void address_list() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.address_list();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.address_list();
		}
		returnType = -1;
	}

	public void address() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.address();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.address();
		}
		returnType = -1;
	}

	public void protocol_address_information() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.protocol_address_information();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.protocol_address_information();
		}
		returnType = -1;
	}

	public void address_element() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.address_element();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.address_element();
		}
		returnType = -1;
	}

	public void host_address() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.host_address();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.host_address();
		}
		returnType = -1;
	}

	public void service_name1() throws RecognitionException,TokenStreamException{
		if (getPredicting() == 0) {
			PsiBuilder.Marker m = builder.mark();
			try {
			super.service_name1();
			if(returnType > 0 ){
				m.done( ANTLRType2AdoptedType.type2etype[returnType] );
			} else {
				m.drop();
			}
			} catch(antlr.RecognitionException ex){
				m.drop();
				throw ex;
			}
		} else {
			super.service_name1();
		}
		returnType = -1;
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

}
