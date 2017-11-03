package edu.colostate.ember.nlp.structure;

import edu.stanford.nlp.trees.TypedDependency;

import java.util.ArrayList;
import java.util.Collection;

public class DependencyTreeFactory {

    public static void constructDependencyTree(ArrayList<DependencyTreeEdge> edges) {
        for (DependencyTreeEdge edge : edges) {

        }


    }

    public static void constructDependencyTree(Collection<TypedDependency> typedDependencies) {
        ArrayList<DependencyTreeEdge> edges = new ArrayList<>();
        for (TypedDependency dependency : typedDependencies) {
            edges.add(new DependencyTreeEdge(dependency));
        }
        constructDependencyTree(edges);
    }

}
