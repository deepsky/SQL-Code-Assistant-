package com.deepsky.lang.tnsnames.psi;

import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AddressImpl extends ASTWrapperPsiElement implements Address {

    public AddressImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public AddressElement[] getElements() {
        List<AddressElement> out = new ArrayList<AddressElement>();
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(TNSTypesAdopted.ADDRESS_ELEMENT));
        for(ASTNode node: nodes){
            out.add(new AddressElementImpl(node));
        }
        return out.toArray(new AddressElement[out.size()]);
    }

    public AddressElement findElementByName(@NotNull String name) {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(TNSTypesAdopted.ADDRESS_ELEMENT));
        for(ASTNode node: nodes){
            if(name.equalsIgnoreCase(node.getFirstChildNode().getText())){
                return (AddressElement) node.getPsi();
            }
        }

        return null;
    }
}
