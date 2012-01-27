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

import com.deepsky.lang.plsql.resolver.ContextPathProvider;
import com.deepsky.lang.plsql.resolver.factory.ContextPathManager;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.DebugUtil;
import com.intellij.psi.impl.source.DummyHolderFactory;
import com.intellij.psi.impl.source.tree.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import com.intellij.util.text.CharArrayCharSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LightweightElement implements ASTNode, ContextPathProvider { //extends TreeElement {

    private final IElementType myType;

    LightweightElement firstChild;
    LightweightElement lastChild;
    LightweightElement parent;

    private LightweightElement myNextSibling = null;
    private LightweightElement myPrevSibling = null;

    private volatile int myStartOffsetInParent = -1;

    protected PsiElement myPsi;

    int myCachedLength  = NOT_CACHED;
    int myHC = -1;

    public LightweightElement(IElementType type) {
        this.myType = type;
    }


    public void setPsi(@NotNull PsiElement psi) {
      this.myPsi = psi;
    }

    public Object clone(){
        throw new MethodNotSupportedException("Not supported: clone");
    }

    public ASTNode findLeafElementAt(int offset) {
        LightweightElement element = this;
        startFind:
        while (true) {
            LightweightElement child = element.getFirstChildNode();
            while (child != null) {
                final int textLength = child.getTextLength();
                if (textLength > offset) {
                    if (child instanceof LightLeafElement) {
                        return child;
                    }
                    element = child;
                    continue startFind;
                }
                offset -= textLength;
                child = child.getTreeNext();
            }
            return null;
        }
    }

    public <T> T getCopyableUserData(Key<T> key) {
        throw new MethodNotSupportedException("Not supported: getCopyableUserData");
    }

    public <T> void putCopyableUserData(Key<T> key, T value) {
        throw new MethodNotSupportedException("Not supported: putCopyableUserData");
    }

    public ASTNode findChildByType(IElementType type) {
        for(ASTNode element = getFirstChildNode(); element != null; element = element.getTreeNext()){
          if (element.getElementType() == type) return element;
        }
        return null;
    }

    public ASTNode findChildByType(@NotNull TokenSet typesSet) {
        for(ASTNode element = getFirstChildNode(); element != null; element = element.getTreeNext()){
          if (typesSet.contains(element.getElementType())) return element;
        }
        return null;
    }

    public ASTNode findChildByType(@NotNull TokenSet typesSet, @Nullable ASTNode anchor) {
        ASTNode child = anchor;
        while (true) {
          if (child == null) return null;
          if (typesSet.contains(child.getElementType())) return child;
          child = child.getTreeNext();
        }
    }

    public PsiElement getPsi() {
        return myPsi;
    }

    public <T extends PsiElement> T getPsi(Class<T> clazz) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public char[] textToCharArray() {
        char[] buffer = new char[getTextLength()];
        AstBufferUtil.toBuffer(this, buffer, 0);
        return buffer;
    }


    public IElementType getElementType() {
        return myType;
    }

    public String getText() {
        char[] buffer = new char[getTextLength()];
        toBuffer(this, buffer, 0, null);
        return new String(buffer);
    }

    public static int toBuffer(ASTNode element, char[] buffer, int offset, TokenSet skipTypes) {
//      if (element instanceof ForeignLeafPsiElement || skipTypes != null && skipTypes.contains(element.getElementType())) return offset;
      if (element instanceof LightLeafElement) {
        return ((LightLeafElement)element).copyTo(buffer, offset);
      }

      if (element instanceof LightFileElement) {
        LightFileElement lpe = (LightFileElement)element;
        int lpeResult = lpe.copyTo(buffer, offset);
        if (lpeResult > 0) return lpeResult;
      }

      int curOffset = offset;
      for (LightweightElement child = (LightweightElement)element.getFirstChildNode(); child != null; child = child.getTreeNext()) {
        curOffset = toBuffer(child, buffer, curOffset, skipTypes);
      }
      return curOffset;
    }


    public CharSequence getChars() {
        char[] buffer = new char[getTextLength()];
        AstBufferUtil.toBuffer(this, buffer, 0);
        return new CharArrayCharSequence(buffer);
    }

    public boolean textContains(char c) {
        for (ASTNode child = getFirstChildNode(); child != null; child = child.getTreeNext()) {
          if (child.textContains(c)) return true;
        }
        return false;
    }


    public int getStartOffset() {
      if (parent == null) return 0;
      return parent.getStartOffset() + getStartOffsetInParent();
    }


    public final int getStartOffsetInParent() {
      if (parent == null) return -1;
      int offsetInParent = myStartOffsetInParent;
      if (offsetInParent != -1) return offsetInParent;

//      synchronized (START_OFFSET_LOCK) {
        LightweightElement cur = this;
        offsetInParent = myStartOffsetInParent;
        if (offsetInParent != -1) return offsetInParent;

        while (true) {
          LightweightElement prev = cur.getTreePrev();
          if (prev == null) break;
          cur = prev;
          offsetInParent = cur.myStartOffsetInParent;
          if (offsetInParent != -1) break;
        }

        if (offsetInParent == -1) {
          cur.myStartOffsetInParent = offsetInParent = 0;
        }

        while (cur != this) {
          LightweightElement next = cur.getTreeNext();
          offsetInParent += cur.getTextLength();
          next.myStartOffsetInParent = offsetInParent;
          cur = next;
        }
        return offsetInParent;
//      }
    }



    public int getTextLength() {
        int cachedLength = myCachedLength;
        if (cachedLength >= 0) return cachedLength;

//        synchronized (START_OFFSET_LOCK) {
          cachedLength = myCachedLength;
          if (cachedLength >= 0) return cachedLength;

          walkCachingLength();
          return myCachedLength;
//        }
    }

     private static final int NOT_CACHED = -239;

    private void walkCachingLength() {
      LightweightElement cur = this;

      while (cur != null) {
        cur = next(cur, cur.getCachedLength() == NOT_CACHED);
      }

//      LOG.assertTrue(myCachedLength >= 0, myCachedLength);
    }
    
    private LightweightElement next(LightweightElement cur, boolean down) {
      if (down) {
        LightweightElement composite = cur; // It's a composite or we won't be going down
        LightweightElement child = composite.firstChild;
        if (child != null) {
//          LOG.assertTrue(child.getTreeParent() == composite, cur);
          return child;
        }

        composite.myCachedLength = 0;
      }

      // up
      while (cur != this) {
        LightweightElement parent = cur.getTreeParent();
        int curLength = cur.getCachedLength();
//        LOG.assertTrue(curLength != NOT_CACHED, cur);
        parent.myCachedLength -= curLength;

        LightweightElement next = cur.getTreeNext();
        if (next != null) {
//          LOG.assertTrue(next.getTreePrev() == cur, cur);
          return next;
        }

//        LOG.assertTrue(parent.lastChild == cur, parent);
        parent.myCachedLength = -parent.myCachedLength + NOT_CACHED;

        cur = parent;
      }

      return null;
    }



    public TextRange getTextRange() {
        int start = getStartOffset();
        return new TextRange(start, start + getTextLength());
    }

    public LightweightElement getTreeParent() {
        return parent;
    }


    public LightweightElement getFirstChildNode() {
        return firstChild;
    }

    public LightweightElement getLastChildNode() {
        return lastChild;
    }

    public LightweightElement getTreeNext() {
        return myNextSibling;
    }

    public LightweightElement getTreePrev() {
        return myPrevSibling;
    }

    public ASTNode[] getChildren(@Nullable TokenSet filter) {
//        int count = countChildren(filter);
//        if (count == 0) {
//          return EMPTY_ARRAY;
//        }
//        final ASTNode[] result = new ASTNode[count];
        final List<ASTNode> out = new ArrayList<ASTNode>();
//        count = 0;
        for (ASTNode child = getFirstChildNode(); child != null; child = child.getTreeNext()) {
          if (filter == null || filter.contains(child.getElementType())) {
            //result[count++] = child;
              out.add(child);
          }
        }
        return out.toArray(new ASTNode[out.size()]);
    }

    public void addChild(@NotNull ASTNode child) {
        addChild(child, null);
    }

    public void addChild(@NotNull ASTNode child, final ASTNode anchorBefore) {
        throw new MethodNotSupportedException("Not supported: clone");
/*
//        LOG.assertTrue(anchorBefore == null || ((TreeElement)anchorBefore).getTreeParent() == this, "anchorBefore == null || anchorBefore.getTreeParent() == parent");
        TreeUtil.ensureParsed(getFirstChildNode());
        TreeUtil.ensureParsed(child);
        final TreeElement last = ((TreeElement)child).getTreeNext();
        final TreeElement first = (TreeElement)child;

        removeChildrenInner(first, last);

        ChangeUtil.prepareAndRunChangeAction(new ChangeUtil.ChangeAction(){
          public void makeChange(TreeChangeEvent destinationTreeChange) {
            if (anchorBefore != null) {
              insertBefore(destinationTreeChange, (TreeElement)anchorBefore, first);
            }
            else {
              add(destinationTreeChange, LightweightElement.this, first);
            }
          }
        }, this);
*/
    }

    public void addLeaf(@NotNull IElementType leafType, CharSequence leafText, ASTNode anchorBefore) {
        throw new MethodNotSupportedException("Not supported: clone");
/*
        FileElement holder = new DummyHolder(getManager(), null).getTreeElement();
        final LeafElement leaf = ASTFactory.leaf(leafType, holder.getCharTable().intern(leafText));
        CodeEditUtil.setNodeGenerated(leaf, true);
        holder.rawAddChildren(leaf);

        addChild(leaf, anchorBefore);
*/
    }

    public void removeChild(@NotNull ASTNode child) {
        removeChildInner((TreeElement)child);
    }


    private static void removeChildInner(final TreeElement child) {
      removeChildrenInner(child, child.getTreeNext());
    }

    private static void removeChildrenInner(final TreeElement first, final TreeElement last) {
        throw new MethodNotSupportedException("Not supported: removeChildrenInner");
//      final FileElement fileElement = TreeUtil.getFileElement(first);
//      if (fileElement != null) {
//        ChangeUtil.prepareAndRunChangeAction(new ChangeUtil.ChangeAction() {
//          public void makeChange(TreeChangeEvent destinationTreeChange) {
//            remove(destinationTreeChange, first, last);
//            repairRemovedElement(fileElement, first);
//          }
//        }, first.getTreeParent());
//      }
//      else {
//        first.rawRemoveUpTo(last);
//      }
    }

    public void removeRange(@NotNull ASTNode firstNodeToRemove, ASTNode firstNodeToKeep) {
        removeChildrenInner((TreeElement)firstNodeToRemove, (TreeElement)firstNodeToKeep);
    }

    public void replaceChild(@NotNull ASTNode oldChild, @NotNull ASTNode newChild) {
        throw new MethodNotSupportedException("Not supported: replaceChild");
//        LOG.assertTrue(((TreeElement)oldChild).getTreeParent() == this);
//        final TreeElement oldChild1 = (TreeElement)oldChild;
//        final TreeElement newChildNext = ((TreeElement)newChild).getTreeNext();
//        final TreeElement newChild1 = (TreeElement)newChild;
//
//        if(oldChild1 == newChild1) return;
//
//        removeChildrenInner(newChild1, newChildNext);
//
//        ChangeUtil.prepareAndRunChangeAction(new ChangeUtil.ChangeAction(){
//          public void makeChange(TreeChangeEvent destinationTreeChange) {
//            replace(destinationTreeChange, oldChild1, newChild1);
//            repairRemovedElement(LightweightElement.this, oldChild1);
//          }
//        }, this);
    }

    public void replaceAllChildrenToChildrenOf(final ASTNode anotherParent) {
        throw new MethodNotSupportedException("Not supported: replaceChild");
//        TreeUtil.ensureParsed(getFirstChildNode());
//        TreeUtil.ensureParsed(anotherParent.getFirstChildNode());
//        final ASTNode firstChild1 = anotherParent.getFirstChildNode();
//        ChangeUtil.prepareAndRunChangeAction(new ChangeUtil.ChangeAction(){
//          public void makeChange(TreeChangeEvent destinationTreeChange) {
//            destinationTreeChange.addElementaryChange(anotherParent, ChangeInfoImpl.create(ChangeInfo.CONTENTS_CHANGED, anotherParent));
//            ((LightweightElement)anotherParent).rawRemoveAllChildren();
//          }
//        }, (TreeElement)anotherParent);
//
//        if (firstChild1 != null) {
//          ChangeUtil.prepareAndRunChangeAction(new ChangeUtil.ChangeAction(){
//            public void makeChange(TreeChangeEvent destinationTreeChange) {
//              if(getTreeParent() != null){
//                final ChangeInfoImpl changeInfo = ChangeInfoImpl.create(ChangeInfo.CONTENTS_CHANGED, CompositeElement.this);
//                changeInfo.setOldLength(getTextLength());
//                destinationTreeChange.addElementaryChange(LightweightElement.this, changeInfo);
//                rawRemoveAllChildren();
//                rawAddChildren((TreeElement)firstChild1);
//              }
//              else{
//                final TreeElement first = getFirstChildNode();
//                remove(destinationTreeChange, first, null);
//                add(destinationTreeChange, LightweightElement.this, (TreeElement)firstChild1);
//                repairRemovedElement(LightweightElement.this, first);
//              }
//            }
//          }, this);
//        }
//        else {
//          removeAllChildren();
//        }
    }

//    private void rawRemoveAllChildren() {
//      LightweightElement first = getFirstChildNode();
//      if (first != null) {
//        first.rawRemoveUpToLast();
//      }
//    }

    public void setTreeParent(LightweightElement parent) {
        this.parent = parent;
    }

    public void setTreePrev(LightweightElement prev) {
        this.myPrevSibling = prev;
    }

    public void setTreeNext(LightweightElement next) {
        this.myNextSibling = next;
    }

    public void setFirstChildNode(LightweightElement firstChild) {
      this.firstChild = firstChild;
    }

    public void setLastChildNode(LightweightElement lastChild) {
      this.lastChild = lastChild;
    }


    public void rawAddChildren(@NotNull LightweightElement first) {
      final LightweightElement last = getLastChildNode();
      if (last == null){
        setFirstChildNode(first);
        first.setTreePrev(null);
        while(true){
          final LightweightElement treeNext = first.getTreeNext();
          first.setTreeParent(this);
          if(treeNext == null) break;
          first = treeNext;
        }
        setLastChildNode(first);
        first.setTreeParent(this);
      }
      else {
        last.rawInsertAfterMe(first);
      }

      if (DebugUtil.CHECK) DebugUtil.checkTreeStructure(this);
    }

    public void rawInsertAfterMe(@NotNull LightweightElement firstNew) {
      firstNew.rawRemoveUpToLast();
      final LightweightElement p = getTreeParent();
      final LightweightElement treeNext = getTreeNext();
      firstNew.setTreePrev(this);
      setTreeNext(firstNew);
      while(true){
        final LightweightElement n = firstNew.getTreeNext();
        assert n != this : "Attempt to create cycle";
        firstNew.setTreeParent(p);
        if(n == null) break;
        firstNew = n;
      }

      if(treeNext == null){
        if(p != null){
          firstNew.setTreeParent(p);
          p.setLastChildNode(firstNew);
        }
      }
      else{
        firstNew.setTreeNext(treeNext);
        treeNext.setTreePrev(firstNew);
      }
      if (DebugUtil.CHECK){
        DebugUtil.checkTreeStructure(this);
      }
    }


    public void rawRemoveUpToLast() {
      rawRemoveUpTo(null);
    }

    // remove nodes from this[including] to end[excluding] from the parent
    public void rawRemoveUpTo(LightweightElement end) {
      if(this == end) return;

      final LightweightElement parent = getTreeParent();
      final LightweightElement startPrev = getTreePrev();
      final LightweightElement endPrev = end != null ? end.getTreePrev() : null;

      assert end == null || end.getTreeParent() == parent : "Trying to remove non-child";

      if (parent != null){
        if (this == parent.getFirstChildNode()) {
          parent.setFirstChildNode(end);
        }
        if (end == null) {
          parent.setLastChildNode(startPrev);
        }
      }
      if (startPrev != null){
        startPrev.setTreeNext(end);
      }
      if (end != null){
        end.setTreePrev(startPrev);
      }

      setTreePrev(null);
      if (endPrev != null){
        endPrev.setTreeNext(null);
      }

      if (parent != null){
        for(LightweightElement element = this; element != null; element = element.getTreeNext()){
          element.setTreeParent(null);
          // todo -- element.onInvalidated();
        }
      }

      if (DebugUtil.CHECK){
        if (parent != null){
          DebugUtil.checkTreeStructure(parent);
        }
        DebugUtil.checkTreeStructure(this);
      }
    }


    private static void repairRemovedElement(final CompositeElement oldParent, final TreeElement oldChild) {
      if(oldChild == null) return;
      final FileElement treeElement = DummyHolderFactory.createHolder(oldParent.getManager(), null, false).getTreeElement();
      treeElement.rawAddChildren(oldChild);
    }


    public void addChildren(ASTNode firstChild, ASTNode lastChild, ASTNode anchorBefore) {
        while (firstChild != lastChild) {
          final ASTNode next1 = firstChild.getTreeNext();
          addChild(firstChild, anchorBefore);
          firstChild = next1;
        }
    }

    public ASTNode copyElement() {
        throw new MethodNotSupportedException("Not supported: copyElement");
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getNotCachedLength() {
        throw new MethodNotSupportedException("Not supported: getNotCachedLength");
//        int length = 0;
//        TreeElement child = getFirstChildNode();
//        while(child != null){
//          length += child.getNotCachedLength();
//          child = child.getTreeNext();
//        }
//        return length;
    }


    public int getCachedLength() {
        return myCachedLength;
    }

/*
    protected int textMatches(CharSequence buffer, int start) {
        int curOffset = start;
        for (TreeElement child = getFirstChildNode(); child != null; child = child.getTreeNext()) {
          curOffset = child.textMatches(buffer, curOffset);
          if (curOffset == -1) return -1;
        }
        return curOffset;
    }
*/

    public int hc() {
        int hc = myHC;
        if (hc == -1) {
          hc = 0;
          LightweightElement child = firstChild;
          while (child != null) {
            hc += child.hc();
            child = child.getTreeNext();
          }
          myHC = hc;
        }
        return hc;
    }


    public void acceptTree(TreeElementVisitor visitor) {
        throw new MethodNotSupportedException("Not supported: acceptTree");
//        visitor.visitComposite(this);
    }

    public <T> T getUserData(@NotNull Key<T> key) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public CtxPath getCtxPath1() {
        return ContextPathManager.getCtxPath(this);
    }
}
