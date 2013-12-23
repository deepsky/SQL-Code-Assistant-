package com.deepsky.lang.parser.tns;

import com.intellij.psi.tree.IElementType;
import com.deepsky.generated.tns.TNSTokenTypes;

public class ANTLRType2AdoptedType {
	static public IElementType[] type2etype = new IElementType[13];
	static {
		type2etype[TNSTokenTypes.ERROR_TOKEN_A] = TNSTypesAdopted.ERROR_TOKEN_A;
		type2etype[TNSTokenTypes.NET_SERVICE_DESC] = TNSTypesAdopted.NET_SERVICE_DESC;
		type2etype[TNSTokenTypes.DESCRIPTION_LIST] = TNSTypesAdopted.DESCRIPTION_LIST;
		type2etype[TNSTokenTypes.DESCRIPTION] = TNSTypesAdopted.DESCRIPTION;
		type2etype[TNSTokenTypes.ADDRESS] = TNSTypesAdopted.ADDRESS;
		type2etype[TNSTokenTypes.NETWORK_ALIAS] = TNSTypesAdopted.NETWORK_ALIAS;
		type2etype[TNSTokenTypes.HOST_ADDRESS] = TNSTypesAdopted.HOST_ADDRESS;
		type2etype[TNSTokenTypes.ADDRESS_ELEMENT] = TNSTypesAdopted.ADDRESS_ELEMENT;
	}
}
