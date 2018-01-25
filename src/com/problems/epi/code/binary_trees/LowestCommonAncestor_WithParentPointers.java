package com.problems.epi.code.binary_trees;

import com.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Key Insight: The lowest common ancestor is a first ancestor node that is common to both nodeA and nodeB.
 * Since parent pointers are available, there are two possible solutions.
 */
public class LowestCommonAncestor_WithParentPointers {
    /**
     * Brute-Force: Iterate over any one of the nodes and store the nodes in its search path in a hash table.
     * Then iterate over the second node and return the first parent node that appears in the hash table.
     */
    public static TreeNode<Integer> lowestCommonAncestor_BruteForce(TreeNode<Integer> n1, TreeNode<Integer> n2) {
        if (n1 == null || n2 == null) return null;
        List<TreeNode<Integer>> parents = new ArrayList<>();
        while (n1.parent != null) {
            n1 = n1.parent;
            parents.add(n1);
        }
        while (n2.parent != null) {
            n2 = n2.parent;
            if (parents.contains(n2)) return n2;
        }
        return null;
    }

    /**
     * Efficient solution: Find the depth of each node.
     * Iterate over the deeper node until it gets to the depth of the second node, then return the parent of any  * of the nodes.
     */
    public static TreeNode<Integer> lowestCommonAncestor_Efficient(TreeNode<Integer> n1, TreeNode<Integer> n2) {
        if (n1 == null || n2 == null) return null;
        int depth1 = getDepth(n1);
        int depth2 = getDepth(n2);
        int diff = Math.abs(depth1 - depth2);
        if (depth1 > depth2) {
            while (diff-- > 0) n1 = n1.parent;
        } else if (depth2 > depth1) while (diff-- > 0) n2 = n2.parent;
        while (n1 != n2) {
            n1 = n1.parent;
            n2 = n2.parent;
        }
        return n1;
    }

    private static int getDepth(TreeNode<Integer> node) {
        int depth = 0;
        while (node.parent != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }
}
