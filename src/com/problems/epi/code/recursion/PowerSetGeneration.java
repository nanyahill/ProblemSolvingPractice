package com.problems.epi.code.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerSetGeneration {

    /*********************************** Integers *********************************************************/

    /**
     * Clean solution and prints in lexicographic order
     */
    public static List<List<Integer>> generatePowerSet(List<Integer> set) {
        List<List<Integer>> res = new ArrayList<>();
        if (set == null || set.size() == 0) return res;
        generatePowerSet(new ArrayList<>(), res, 0, set.size(), set);
        return res;
    }
    private static void generatePowerSet(List<Integer> tmp, List<List<Integer>> res, int idx, int n, List<Integer> set) {
        res.add(new ArrayList<>(tmp));
        for (int i = idx; i < n; i++) {
            tmp.add(set.get(i));
            generatePowerSet(tmp, res, i + 1, n, set);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * Another way to implement without for loop
     * Uses the idea of making two choices (yes/no) for each element in the set.
     */
    public static List<List<Integer>> generatePowerSet_Integers(List<Integer> set) {
        List<List<Integer>> res = new ArrayList<>();
        if (set == null || set.size() == 0) return res;
        generatePowerSet_Integers(new ArrayList<>(), res, 0, set);
        return res;
    }

    private static void generatePowerSet_Integers(List<Integer> tmp, List<List<Integer>> res, int idx, List<Integer> set) {
        if(idx == set.size()) {
            res.add(new ArrayList<>(tmp));
        }
        else {
            tmp.add(set.get(idx));
            generatePowerSet_Integers(tmp, res, idx + 1, set); // set[i] is included
            tmp.remove(tmp.size() - 1);
            generatePowerSet_Integers(tmp, res, idx + 1, set); // set[i] is not included
        }
    }

    private static List<List<Integer>>  generatePowerSet_Iterative(List<Integer> set, List<List<Integer>> res) {
        List<List<Integer>> result = new ArrayList<>();
        if(set == null || set.size() == 0) return result;
        for(int i = 0; i < (1 << set.size()); i++) {
            List<Integer> tmp = new ArrayList<>();
            for(int j = 0; j < set.size(); j++) {
                // to print in squashed order: in which any subset involving aj can be listed only after all the subsets involving a1, . . . , aj−1
                // use index n - j - 1 instead of j
                if((i & (1 << j)) > 0) tmp.add(set.get(j));
            }

            res.add(tmp);
        }
        return result;
    }

    /*********************************** Strings *********************************************************/

    /**
     * This was from a Standford CS106b class lecture notes
     * Follows the definition of how to generate subsets
     * All subset can be grouped into two- those that contain it and those that don't
     * Ideal for Strings. Very clean
     */
    private static void generatePowerSet_String(String prefix, String input) {
        if(input.isEmpty()) System.out.println(prefix);
        else {
            generatePowerSet_String(prefix + input.charAt(0), input.substring(1));
            generatePowerSet_String(prefix, input.substring(1));
        }
    }

    public static void main(String[] args) {
       List<List<Integer>> result = generatePowerSet_Integers(Arrays.asList(new Integer[] {1,2,3,2}));
       //List<List<Integer>> result = subsets(new int[] {1,2,3});
        for (List<Integer> set : result) {
            System.out.print(Arrays.toString(set.toArray()));
            System.out.println();
        }
        System.out.print("a".substring(1));
    }
}
