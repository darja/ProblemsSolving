import util.NestedListTestCase;

import java.util.*;

public class ThreeSum extends NestedListTestCase {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        int i = 0;
        while (i < nums.length) {
            checkThirdElement(result, nums, i);
            i++;
            while (i < nums.length && nums[i - 1] == nums[i]) {
                i++;
            }
        }

        return result;
    }

    private void checkThirdElement(List<List<Integer>> result, int[] nums, int index) {
        int third = nums[index];
        int lo = index + 1;
        int hi = nums.length - 1;


        boolean moveLo;
        boolean moveHi;
        while (lo < hi) {
            moveLo = false;
            moveHi = false;

            int sum = nums[lo] + nums[hi] + third;
            if (sum == 0) {
                List<Integer> item = new ArrayList<>(3);
                item.add(nums[lo]);
                item.add(nums[hi]);
                item.add(third);
                result.add(item);
                moveLo = true;
                moveHi = true;
            } else if (sum > 0) {
                moveHi = true;
            } else {
                moveLo = true;
            }

            if (moveLo) {
                lo++;
                while (lo < nums.length && nums[lo] == nums[lo - 1]) {
                    lo++;
                }
            }

            if (moveHi) {
                hi--;
                while (hi > index && nums[hi] == nums[hi + 1]) {
                    hi--;
                }
            }
        }
    }

    public void testTrivial() {
        testCase(new int[0], new int[0][0]);
        testCase(new int[] {0}, new int[0][0]);
        testCase(new int[] {1, -1}, new int[0][0]);
    }

    public void testNoResult() {
        testCase(new int[] {-1, -1 -1}, new int[0][0]);
        testCase(new int[] {-1, 101 -1, 20}, new int[0][0]);
        testCase(new int[] {-1, -1 -1, -1, -1}, new int[0][0]);
    }

    public void testResult() {
        testCase(new int[] {1, 0, -1}, new int[][] {
            {-1, 1, 0}
        });
        testCase(new int[] {-2, 0, 0, 2, 2}, new int[][] {
            {-2, 2, 0}
        });
        testCase(new int[] {-1, 0, 1, 2, -1, -4}, new int[][] {
            {-1, 0, 1},
            {-1, -1, 2}
        });
        testCase(new int[] {-1, 0, 1, -2, 0, -1, 2, 0}, new int[][] {
            {-1, 0, 1},
            {-1, -1, 2},
            {-2, 0, 2},
            {0, 0, 0}
        });
    }

    public void testCase(int[] nums, int[][] expected) {
        assertEquals(threeSum(nums), expected);
    }
}
