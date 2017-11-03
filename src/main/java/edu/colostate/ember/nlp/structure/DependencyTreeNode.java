package edu.colostate.ember.nlp.structure;

import edu.stanford.nlp.ling.IndexedWord;

import java.util.Collection;

public class DependencyTreeNode {

    private DependencyTreeNode parent;
    private DependencyTreeEdge parentEdge;
    private Collection<DependencyTreeNode> children;
    private Collection<DependencyTreeEdge> edges;
    private IndexedWord word;
    private int level;

    public DependencyTreeNode(IndexedWord word) {
        this.word = word;
    }

    public DependencyTreeEdge getParentEdge() {
        return parentEdge;
    }

    public void setParentEdge(DependencyTreeEdge parentEdge) {
        this.parentEdge = parentEdge;
    }

    public DependencyTreeNode getParent() {
        return parent;
    }

    public void setParent(DependencyTreeNode parent) {
        this.parent = parent;
    }

    public void addChild(DependencyTreeNode child) {
        children.add(child);
        edges.add(child.getParentEdge());
    }

    public void addChildrenEdge(DependencyTreeEdge edge) {
        this.edges.add(edge);
    }

    public Collection<DependencyTreeNode> getChildren() {
        return children;
    }

    public Collection<DependencyTreeEdge> getEdges() {
        return edges;
    }


}
