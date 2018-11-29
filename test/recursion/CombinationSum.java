package recursion;

import util.MatrixTestCase;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/combination-sum/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
// todo may try again
public class CombinationSum extends MatrixTestCase {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, candidates, target, new LinkedList<Integer>(), 0, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] candidates, int target,
                           LinkedList<Integer> comb, int sum, int pos) {
        if (sum == target) {
            result.add(new ArrayList<>(comb));
            return;
        }

        if (sum > target) {
            return;
        }

        for (int i = pos; i < candidates.length; ++i) {
            int item = candidates[i];
            comb.push(item);
            backtrack(result, candidates, target, comb, sum + item, i);
            comb.pop();
        }
    }

    private void testSingle(int[][] expected, int[] candidates, int target) {
        assertMatricesEquals(expected, combinationSum(candidates, target));
    }

    public void test1() {
        int[][] expected = new int[][]{
            {7},
            {2, 2, 3}
        };
        testSingle(expected, new int[] {2, 3, 6, 7}, 7);
    }

    public void test2() {
        int[][] expected = new int[][]{
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 2},
            {1, 1, 1, 2, 2},
            {1, 2, 2, 2},
            {1, 1, 5},
            {2, 5}
        };
        testSingle(expected, new int[] {1, 2, 5}, 7);
    }

    public void test3() {
        int[][] expected = new int[0][0];
        testSingle(expected, new int[] {10}, 7);
    }
}
