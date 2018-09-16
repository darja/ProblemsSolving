import util.ArrayTestCase;

import java.util.Arrays;

public class RemoveElement extends ArrayTestCase {
    public int removeElement(int[] nums, int val) {
        int offset = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == val) {
                offset++;
            } else {
                nums[i - offset] = nums[i];
            }
        }

        return nums.length - offset;
    }

    public void test() {
        testCase(new int[] {1, 2, 3, 4, 1}, 1, new int[] {2, 3, 4});
        testCase(new int[] {1, 2, 3, 4, 1}, 2, new int[] {1, 3, 4, 1});
        testCase(new int[] {1, 2, 3, 4, 2, 1}, 2, new int[] {1, 3, 4, 1});
        testCase(new int[] {1, 2, 3, 4, 2, 1}, 0, new int[] {1, 2, 3, 4, 2, 1});
    }

    private void testCase(int[] nums, int val, int[] expected) {
        int newLen = removeElement(nums, val);
        assertEquals(newLen, expected.length);
        int[] changedArray = Arrays.copyOf(nums, newLen);
        assertArraysEqual(expected, changedArray);
    }
}
