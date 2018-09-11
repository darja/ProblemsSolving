import util.MatrixTestCase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Combinations extends MatrixTestCase {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new LinkedList<>();
        Stack<Integer> comb = new Stack<>();
        backtrack(result, comb, 0, k, n);
        return result;
    }

    private void backtrack(List<List<Integer>> result, Stack<Integer> combination, int current, int k, int n) {
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = current + 1; i <= n; ++i) {
            combination.push(i);
            backtrack(result, combination, i, k, n);
            combination.pop();
        }
    }

    private void testSingle(int[][] expected, int n, int k) {
        List<List<Integer>> result = combine(n, k);
        assertMatricesEquals(expected, result);
    }

    public void test2out4() {
        int[][] expected = new int[][] {
            {1, 2},
            {1, 3},
            {1, 4},
            {2, 3},
            {2, 4},
            {3, 4},
        };
        testSingle(expected, 4, 2);
    }

    public void test1out2() {
        int[][] expected = new int[][] {
            {1},
            {2}
        };
        testSingle(expected, 2, 1);
    }

    public void test1out4() {
        int[][] expected = new int[][] {
            {1}, {2}, {3}, {4}
        };
        testSingle(expected, 4, 1);
    }

    public void test3out4() {
        int[][] expected = new int[][] {
            {1, 2, 3},
            {1, 2, 4},
            {1, 3, 4},
            {2, 3, 4}
        };
        testSingle(expected, 4, 3);
    }

    public void test3out2() {
        int[][] expected = new int[0][0];
        testSingle(expected, 2, 3);
    }


}
