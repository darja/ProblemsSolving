import util.NestedListTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetsII extends NestedListTestCase {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        subsetsBack(result, new LinkedList<Integer>(), nums, 0);
        return result;
    }

    private void subsetsBack(LinkedList<List<Integer>> result, LinkedList<Integer> current, int[] nums, int index) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; ++i) {
            current.add(nums[i]);

            if (!result.contains(current)) {
                subsetsBack(result, current, nums, i + 1);
            }
            current.removeLast();
        }
    }

    public void test1() {
        int[][] expected = new int[][] {
            {1}, {}
        };
        testCase(new int[] {1}, expected);
    }

    public void test2() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {1, 2}
        };
        testCase(new int[] {1, 2}, expected);
    }

    public void test2dup() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {1, 2}, {1, 2, 2}, {2, 2}
        };
        testCase(new int[] {1, 2, 2}, expected);
        testCase(new int[] {2, 1, 2}, expected);
    }

    public void test3() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}
        };
        testCase(new int[] {1, 2, 3}, expected);
    }

    public void test3dup() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3},
            {1, 1}, {1, 1, 2}, {1, 1, 2, 2}, {1, 1, 2, 2, 3},
            {1, 1, 2, 3}, {1, 1, 3}, {2, 2}, {2, 2, 3},
            {1, 2, 2, 3}, {1, 2, 2}
        };
        testCase(new int[] {1, 2, 3, 2, 1}, expected);
    }

    public void test4() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {3}, {4}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4},
            {1, 2, 3}, {1, 2, 4}, {1, 3, 4}, {2, 3, 4}, {1, 2, 3, 4}
        };
        testCase(new int[] {1, 2, 3, 4}, expected);
    }

    private void testCase(int[] nums, int[][] expected) {
        List<List<Integer>> result = subsetsWithDup(nums);
        assertEquals(result, expected);
    }
}
