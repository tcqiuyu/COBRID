package edu.colostate.ember.nlp.structure;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.trees.TypedDependency;

import java.util.ArrayList;

public class DependencyTreeFactory {


    public static DependencyTreeNode constructDependencyTree(ArrayList<TypedDependency> typedDependencies) {
        ArrayList<DependencyTreeNode> nodes = new ArrayList<>();
        DependencyTreeNode[] node = new DependencyTreeNode[typedDependencies.size() + 1];
        for (TypedDependency dependency : typedDependencies) {
            int idx = dependency.dep().get(CoreAnnotations.IndexAnnotation.class);
            boolean start = true;
            DependencyTreeNode lastGov = null;

            while (node[idx] == null) {
                DependencyTreeNode dep = null, gov = null;

                if (start) {
                    dep = new DependencyTreeNode(dependency.dep());
                } else {
                    dep = lastGov;
                }
                gov = new DependencyTreeNode(dependency.gov());

                node[idx] = dep;
                idx = gov.getWordIndex();


                if (node[idx] != null) {// If the next node has been traversed
                    new DependencyTreeEdge(node[idx], dep, dependency.reln());
                } else if (idx == 0) {// If the next node is ROOT
                    node[idx] = gov;
                    new DependencyTreeEdge(gov, dep, dependency.reln());
                } else {// If there are more to traverse
                    start = false;
                    new DependencyTreeEdge(gov, dep, dependency.reln());
                    lastGov = gov;
                    dependency = typedDependencies.get(idx - 1);
                }

            }
        }

        return node[0];
    }


    public static void main(String[] args) {
        DependencyTreeNode[] node = new DependencyTreeNode[13 + 1];

    }
}
