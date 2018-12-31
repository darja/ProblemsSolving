package recursion;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/majority-element/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class MajorityElement extends TestCase {
    public void test() {
        testMajorityElement(1, new int[] {1});
        testMajorityElement(1, new int[] {1, 1, 1});
        testMajorityElement(1, new int[] {1, 2, 1});
        testMajorityElement(1, new int[] {2, 1, 1});
        testMajorityElement(1, new int[] {1, 2, 1, 1});
        testMajorityElement(1, new int[] {2, 1, 1, 1});
        testMajorityElement(1, new int[] {1, 1, 2, 1});
        testMajorityElement(1, new int[] {2,2,3,3,1,1,1,2,1,1,1,1,1,1,1,2});
    }

    private void testMajorityElement(int expectedValue, int[] nums) {
        assertEquals(expectedValue, majorityElement(nums));
    }

    public int majorityElement(int[] nums) {
        return majorityFrom(nums, 0);
    }

    public int majorityFrom(int[] nums, int left) {
        if (nums.length - 1 - left <= 1) {
            return nums[left];
        }

        int pivot = nums[left];

        int i = left;
        int j = nums.length - 1;

        while (i < j) {
            while (i <= nums.length - 1 && nums[i] == pivot) ++i;
            while (j >= left && nums[j] != pivot) --j;

            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;

                ++i;
                --j;
            }
        }

        if (i > nums.length / 2) {
            return nums[left];
        } else {
            return majorityFrom(nums, i);
        }
    }
}
