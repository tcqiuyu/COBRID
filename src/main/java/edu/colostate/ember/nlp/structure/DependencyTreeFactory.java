package edu.colostate.ember.nlp.structure;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.trees.TypedDependency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class DependencyTreeFactory {


    public static DependencyTreeNode constructDependencyTree(ArrayList<TypedDependency> typedDependencies) {
        DependencyTreeNode[] node = new DependencyTreeNode[typedDependencies.size() + 1];
        for (TypedDependency dependency : typedDependencies) {
            int idx = dependency.dep().get(CoreAnnotations.IndexAnnotation.class);
            boolean start = true;
            DependencyTreeNode lastGov = null;

            while (node[idx] == null) {
                DependencyTreeNode dep = null, gov = null;

                if (start) {// If it is the start of a new path, then construct new node
                    dep = new DependencyTreeNode(dependency.dep());
                } else {// otherwise, use the one stored in previous loop
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
        bfsDependencyTree(node[0], (nod) -> System.out.println(nod.getWordIndex()));
        return node[0];
    }

    public static void bfsDependencyTree(DependencyTreeNode root, Consumer<? super DependencyTreeNode> func) {
        Queue<DependencyTreeNode> queue = new LinkedList<>();
        root.setLevel(0);
        queue.add(root);

        while (!queue.isEmpty()) {
            DependencyTreeNode tmpNode = queue.poll();
            func.accept(tmpNode);

            if (!tmpNode.getChildren().isEmpty()) {
                tmpNode.getChildren().forEach(node -> node.setLevel(tmpNode.getLevel() + 1));
                queue.addAll(tmpNode.getChildren());

            }
        }
    }

    public static void main(String[] args) {
        DependencyTreeNode[] node = new DependencyTreeNode[13 + 1];

    }
}
