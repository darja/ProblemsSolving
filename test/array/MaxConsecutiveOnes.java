package array;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/max-consecutive-ones/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class MaxConsecutiveOnes extends TestCase {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int max = 0;
        int current = 0;
        for (int num : nums) {
            if (num == 0) {
                max = Math.max(max, current);
                current = 0;
            } else {
                current++;
            }
        }
        return Math.max(max, current);
    }

    private void testOne(int[] nums, int expected) {
        assertEquals(expected, findMaxConsecutiveOnes(nums));
    }

    public void test() {
        testOne(new int[0], 0);
        testOne(new int[] {1}, 1);
        testOne(new int[] {0}, 0);
        testOne(new int[] {1, 1, 1}, 3);
        testOne(new int[] {1, 0, 1}, 1);
        testOne(new int[] {1, 1, 0, 1}, 2);
        testOne(new int[] {1, 0, 1, 1, 0, 1}, 2);
        testOne(new int[] {1, 0, 1, 1, 0, 1, 1, 1}, 3);
        testOne(new int[] {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1}, 4);
    }
}
