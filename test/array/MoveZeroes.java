package array;

import util.ArrayTestCase;

/**
 * @see <a href="https://leetcode.com/problems/move-zeroes/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class MoveZeroes extends ArrayTestCase {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }

        int i = 0;
        while (i < n && nums[i] != 0) {
            ++i;
        }
        if (i == n) {
            return;
        }

        int firstZero = i;
        ++i;
        while (i < n) {
            if (nums[i] != 0) {
                nums[firstZero] = nums[i];
                nums[i] = 0;
                firstZero++;
            }

            ++i;
        }
    }

    public void test() {
        testCase(new int[0], new int[0]);
        testCase(new int[] {1}, new int[] {1});
        testCase(new int[] {1, 2, 3}, new int[] {1, 2, 3});
        testCase(new int[] {0, 1, 2, 3}, new int[] {1, 2, 3, 0});
        testCase(new int[] {1, 0, 2, 3}, new int[] {1, 2, 3, 0});
        testCase(new int[] {1, 2, 3, 0}, new int[] {1, 2, 3, 0});
        testCase(new int[] {0, 1, 0, 2, 3}, new int[] {1, 2, 3, 0, 0});
        testCase(new int[] {0, 1, 0, 2, 3, 0}, new int[] {1, 2, 3, 0, 0, 0});
    }

    private void testCase(int[] input, int[] expected) {
        moveZeroes(input);
        assertArraysEqual(expected, input);
    }
}
