import util.NestedListTestCase;

import java.util.ArrayList;
import java.util.List;

public class Subsets extends NestedListTestCase {
    public List<List<Integer>> subsets(int[] nums) {
        int subsetsCount = 1;
        for (int num : nums) {
            subsetsCount *= 2;
        }

        List<List<Integer>> result = new ArrayList<>(subsetsCount);

        for (int i = 0; i < subsetsCount; ++i) {
            List<Integer> item = new ArrayList<>();

            int code = i;
            int j = 0;
            while (code > 0) {
                if (code % 2 == 1) {
                    item.add(nums[j]);
                }
                code /= 2;
                j++;
            }
            result.add(item);
        }

        return result;
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

    public void test3() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}
        };
        testCase(new int[] {1, 2, 3}, expected);
    }

    public void test4() {
        int[][] expected = new int[][] {
            {}, {1}, {2}, {3}, {4}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4},
            {1, 2, 3}, {1, 2, 4}, {1, 3, 4}, {2, 3, 4}, {1, 2, 3, 4}
        };
        testCase(new int[] {1, 2, 3, 4}, expected);
    }

    private void testCase(int[] nums, int[][] expected) {
        List<List<Integer>> result = subsets(nums);
        assertEquals(result, expected);
    }
}
