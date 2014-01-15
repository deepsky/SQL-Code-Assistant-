package com.deepsky.lang.plsql.completion.legacy.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PatternElement {

    private String head;
    private List<InnerNode> nodes = new ArrayList<InnerNode>();

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String toString() {
        StringBuilder b = new StringBuilder(head);
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getPosition() != -1) {
                b.append(nodes.get(i).getPosition());
            }
            if (nodes.get(i).isASTNode()) {
                b.append("#");
            } else {
                if (nodes.get(i).getPosition() != -1) {
                    b.append("$");
                }
            }
            b.append(nodes.get(i).getText());

            if (i < nodes.size() - 1) {
                b.append(" ");
            }
        }
        return b.toString();
    }


    public Iterator<InnerNode> iterator() {
        return nodes.iterator();
    }

    public void addNode(STPPatternParser.InnerNodeImpl iNode) {
        nodes.add(iNode);
    }

    public interface InnerNode {
        String getText();

        boolean isASTNode();

        int getPosition();
    }
}
