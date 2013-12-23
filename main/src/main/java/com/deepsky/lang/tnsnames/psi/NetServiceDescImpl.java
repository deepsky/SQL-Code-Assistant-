package com.deepsky.lang.tnsnames.psi;

import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NetServiceDescImpl extends ASTWrapperPsiElement implements NetServiceDesc {
    public NetServiceDescImpl(ASTNode astNode) {
        super(astNode);
    }


    @NotNull
    public String getNetworkAlias() {
        ASTNode node = getNode().findChildByType(TNSTypesAdopted.NETWORK_ALIAS);
        if (node == null) {
            throw new SyntaxTreeCorruptedException("Cannot parse NET_SERVICE_DESC");
        }

        return node.getText();
    }

    @NotNull
    public Address[] getAddressInfos() {
        List<Address> addresses = new ArrayList<Address>();
        ASTNode[] descriptions = getNode().getChildren(TokenSet.create(TNSTypesAdopted.DESCRIPTION));
        for (ASTNode node : descriptions) {
            ASTNode[] addressList = node.getChildren(TokenSet.create(TNSTypesAdopted.ADDRESS));
            for(ASTNode address: addressList){
                addresses.add((Address) address.getPsi());
            }
        }

        ASTNode[] description_lists = getNode().getChildren(TokenSet.create(TNSTypesAdopted.DESCRIPTION_LIST));
        for (ASTNode desc_list : description_lists) {
            ASTNode[] descList = desc_list.getChildren(TokenSet.create(TNSTypesAdopted.DESCRIPTION));
            for (ASTNode node : descList) {
                ASTNode[] addressList = node.getChildren(TokenSet.create(TNSTypesAdopted.ADDRESS));
                for(ASTNode address: addressList){
                    addresses.add((Address) address.getPsi());
                }
            }
        }

        return addresses.toArray(new Address[addresses.size()]);
    }

    @NotNull
    public String getBody() {
        ASTNode[] body = getNode().getChildren(TokenSet.create(
                TNSTypesAdopted.DESCRIPTION,
                TNSTypesAdopted.DESCRIPTION_LIST));

        if(body.length != 1){
            throw new SyntaxTreeCorruptedException();
        }
        return body[0].getText();
    }
}
