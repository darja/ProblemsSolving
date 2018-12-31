package array;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/maximum-subarray/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
// todo should be improved
public class MaximumSubarray extends TestCase {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int maxNonPositive = Integer.MIN_VALUE;
        while (i < nums.length && nums[i] <= 0) {
            maxNonPositive = Math.max(maxNonPositive, nums[i]);
            ++i;
        }

        if (i == nums.length) {
            return maxNonPositive;
        }


        int maxSum = nums[i];
        int sum = maxSum;

        ++i;

        while (i < nums.length) {
            sum += nums[i];
            if (maxSum < sum) {
                maxSum = sum;
            } else if (sum < 0) {
                sum = 0;
            }
            ++i;
        }

        return maxSum;
    }

    public void test() {
        testSingle(new int[] {-2,1,-3,4,-1,2,1,-5,4}, 6);
        testSingle(new int[] {-2,10,-3,4,-1,2,1,-5,4}, 13);
        testSingle(new int[] {4,-1,2,1}, 6);
        testSingle(new int[] {0,-1}, 0);
        testSingle(new int[] {-2,-1}, -1);
    }

    private void testSingle(int[] input, int expected) {
        assertEquals(expected, maxSubArray(input));
    }
}
