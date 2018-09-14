import util.NestedListTestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FourSum extends NestedListTestCase {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new LinkedList<>();

        if (nums == null || nums.length < 4) {
            return result;
        }

        LinkedList<Integer> init = new LinkedList<>();

        Arrays.sort(nums);
        fourSumIter(result, init, nums, 0, target);

        return result;
    }

    private void fourSumIter(List<List<Integer>> result, LinkedList<Integer> current, int[] nums, int index, long target) {
        if (current.size() == 4) {
            if (target == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }

        int i = index;
        while (i < nums.length) {
            current.add(nums[i]);
            fourSumIter(result, current, nums, i + 1, target - nums[i]);
            current.removeLast();
            i++;

            while (i < nums.length && nums[i] == nums[i-1]) {
                ++i;
            }
        }
    }

    public void testFewElements() {
        testCase(new int[0], 0, new int[0][0]);
        testCase(new int[0], 19, new int[0][0]);
        testCase(new int[0], -1, new int[0][0]);
        testCase(new int[] { 1 }, 0, new int[0][0]);
        testCase(new int[] { 1, 2 }, 3, new int[0][0]);
        testCase(new int[] { 1, 2, 3 }, 3, new int[0][0]);
    }

    public void testNoVariants() {
        testCase(new int[] { 1, -1, 2, 3 }, 0, new int[0][0]);
        testCase(new int[] { 1, -1, 2, 3, 10 }, 300, new int[0][0]);
    }

    public void testOk() {
        testCase(new int[] { 1, -1, 2, 3, 1 }, 5, new int[][] {
            {1, -1, 2, 3}
        } );

        testCase(new int[] { 1, -1, 2, 3, 1, 5, -2 }, 4, new int[][] {
            {-1, 1, 1, 3},
            {-1, 2, -2, 5},
            {-2, 2, 3, 1}
        } );

        testCase(new int[] {-3,-2,-1,0,0,1,2,3}, 0, new int[][] {
            {-3,-2,2,3},
            {-3,-1,1,3},
            {-3,0,0,3},
            {-3,0,1,2},
            {-2,-1,0,3},
            {-2,-1,1,2},
            {-2,0,0,2},
            {-1,0,0,1}
        });

    }

    private void testCase(int[] nums, int target, int[][] expected) {
        List<List<Integer>> actual = fourSum(nums, target);
        assertEquals(actual, expected);
    }
}
