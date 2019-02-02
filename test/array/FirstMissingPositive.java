package array;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/first-missing-positive">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class FirstMissingPositive extends TestCase {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 1;

        for (int i = 0; i < n; ++i) {
            int item = nums[i];
            if (item > 0 && item <= n && i != item - 1 && nums[item - 1] != item) {
                nums[i] = nums[item - 1];
                nums[item - 1] = item;
                i--;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }

    private void testOne(int[] nums, int expected) {
        assertEquals(expected, firstMissingPositive(nums));
    }

    public void test() {
        testOne(new int[]{1, 1}, 2);
        testOne(new int[]{0, 0}, 1);
        testOne(new int[]{3, 3}, 1);
        testOne(new int[]{2, 2, 2, 2}, 1);

        testOne(new int[0], 1);
        testOne(new int[]{1}, 2);
        testOne(new int[]{3}, 1);
        testOne(new int[]{0}, 1);
        testOne(new int[]{-3}, 1);

        testOne(new int[]{3, 2, 1}, 4);
        testOne(new int[]{1, 2, 3}, 4);

        testOne(new int[]{5, 2, 3}, 1);
        testOne(new int[]{5, -1, 3}, 1);
        testOne(new int[]{6, 7, 8}, 1);

        testOne(new int[]{1, 2, 0}, 3);
        testOne(new int[]{3, 4, -1, 1}, 2);
        testOne(new int[]{7, 8, 9, 11, 12}, 1);
    }
}
