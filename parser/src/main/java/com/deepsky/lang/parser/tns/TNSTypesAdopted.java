package com.deepsky.lang.parser.tns;

import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.deepsky.generated.tns.TNSTokenTypes;
import com.deepsky.integration.tns.TNSElementType;

public interface TNSTypesAdopted {
	TNSElementType ERROR_TOKEN_A = new TNSElementType("ERROR_TOKEN_A", TNSTokenTypes.ERROR_TOKEN_A);
	TNSElementType NET_SERVICE_DESC = new TNSElementType("NET_SERVICE_DESC", TNSTokenTypes.NET_SERVICE_DESC);
	TNSElementType DESCRIPTION_LIST = new TNSElementType("DESCRIPTION_LIST", TNSTokenTypes.DESCRIPTION_LIST);
	TNSElementType DESCRIPTION = new TNSElementType("DESCRIPTION", TNSTokenTypes.DESCRIPTION);
	TNSElementType ADDRESS = new TNSElementType("ADDRESS", TNSTokenTypes.ADDRESS);
	TNSElementType NETWORK_ALIAS = new TNSElementType("NETWORK_ALIAS", TNSTokenTypes.NETWORK_ALIAS);
	TNSElementType HOST_ADDRESS = new TNSElementType("HOST_ADDRESS", TNSTokenTypes.HOST_ADDRESS);
	TNSElementType ADDRESS_ELEMENT = new TNSElementType("ADDRESS_ELEMENT", TNSTokenTypes.ADDRESS_ELEMENT);
}
