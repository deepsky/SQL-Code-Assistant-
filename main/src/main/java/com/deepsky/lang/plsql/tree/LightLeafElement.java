/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.plsql.tree;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.text.CharArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LightLeafElement extends LightweightElement {

    private final CharSequence myText;

    protected LightLeafElement(IElementType type, CharSequence text) {
        super(type);
        myText = text;
    }

    public int getTextLength() {
        return myText.length();
    }

    public CharSequence getChars() {
        return myText;
    }

    public String getText() {
        return myText.toString();
    }

    public char charAt(int position) {
        return myText.charAt(position);
    }

    public int copyTo(char[] buffer, int start) {
        if (buffer != null) {
            CharArrayUtil.getChars(myText, buffer, start);
        }
        return start + myText.length();
    }

    @NotNull
    public char[] textToCharArray() {
        final char[] buffer = new char[myText.length()];
        CharArrayUtil.getChars(myText, buffer, 0);
        return buffer;
    }

    public boolean textContains(char c) {
        CharSequence text = myText;
        for (int i = 0; i < text.length(); i++) {
            if (c == text.charAt(i)) return true;
        }

        return false;
    }

    protected int textMatches(CharSequence buffer, int start) {
        final CharSequence text = myText;
        return leafTextMatches(text, buffer, start);
    }

    public static int leafTextMatches(CharSequence text, CharSequence buffer, int start) {
        final int length = text.length();
        if (buffer.length() - start < length) return -1;
        for (int i = 0; i < length; i++) {
            if (text.charAt(i) != buffer.charAt(i + start)) return -1;
        }
        return start + length;
    }

//    public LeafElement rawReplaceWithText(String newText) {
//      LeafElement newLeaf = ASTFactory.leaf(getElementType(), newText);
//      copyUserDataTo(newLeaf);
//      rawReplaceWithList(newLeaf);
//      newLeaf.clearCaches();
//      return newLeaf;
//    }
//
//    public LeafElement replaceWithText(String newText) {
//      LeafElement newLeaf = ChangeUtil.copyLeafWithText(this, newText);
//      getTreeParent().replaceChild(this, newLeaf);
//      return newLeaf;
//    }
//
//    public LeafElement findLeafElementAt(int offset) {
//      return this;
//    }

/*
    @SuppressWarnings({"MethodOverloadsMethodOfSuperclass"})
    public boolean textMatches(final CharSequence buf, int start, int end) {
      final CharSequence text = getChars();
      final int len = text.length();

      if (end - start != len) return false;
      if (buf == text) return true;

      if (len > TEXT_MATCHES_THRESHOLD && text instanceof String && buf instanceof String) {
        return ((String)text).regionMatches(0,(String)buf,start,len);
      }

      for (int i = 0; i < len; i++) {
        if (text.charAt(i) != buf.charAt(start + i)) return false;
      }

      return true;
    }

    public void acceptTree(TreeElementVisitor visitor) {
      visitor.visitLeaf(this);
    }
*/

    public ASTNode findChildByType(IElementType type) {
        return null;
    }

    @Nullable
    public ASTNode findChildByType(@NotNull TokenSet typesSet) {
        return null;
    }

    @Nullable
    public ASTNode findChildByType(@NotNull TokenSet typesSet, @Nullable ASTNode anchor) {
        return null;
    }

    public int hc() {
        return leafHC(getChars());
    }

    public static int leafHC(CharSequence text) {
        final int len = text.length();
        int hc = 0;

        for (int i = 0; i < len; i++) {
            hc += text.charAt(i);
        }

        return hc;
    }

    public LightweightElement getFirstChildNode() {
        return null;
    }

    public LightweightElement getLastChildNode() {
        return null;
    }

    @Override
    public int getNotCachedLength() {
        return myText.length();
    }

    public int getCachedLength() {
        return getNotCachedLength();
    }

    public ASTNode[] getChildren(TokenSet filter) {
        return EMPTY_ARRAY;
    }

    public void addChild(@NotNull ASTNode child, ASTNode anchorBefore) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void addLeaf(@NotNull final IElementType leafType, final CharSequence leafText, final ASTNode anchorBefore) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void addChild(@NotNull ASTNode child) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void removeChild(@NotNull ASTNode child) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void replaceChild(@NotNull ASTNode oldChild, @NotNull ASTNode newChild) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void replaceAllChildrenToChildrenOf(ASTNode anotherParent) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void removeRange(@NotNull ASTNode first, ASTNode firstWhichStayInTree) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public void addChildren(ASTNode firstChild, ASTNode lastChild, ASTNode anchorBefore) {
        throw new RuntimeException(new IncorrectOperationException("Leaf elements cannot have children."));
    }

    public PsiElement getPsi() {
        return null;
    }

}
