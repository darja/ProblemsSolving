package map;

import junit.framework.TestCase;

import java.util.HashMap;

/**
 * @see <a href="https://leetcode.com/problems/contains-duplicate-ii/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class ContainsDuplicate2 extends TestCase {

    public void test() {
        testSingle(true, new int[] {1,2,2,2,3,1}, 3);
        testSingle(false, new int[] {1,2,5,4,2,5,1,2,3,1}, 2);
        testSingle(false, new int[] {1}, 6);
        testSingle(false, new int[] {1}, 1);
        testSingle(true, new int[] {1,1}, 2);
        testSingle(true, new int[] {1,1}, 5);
        testSingle(false, new int[] {1,2,4,1}, 2);
        testSingle(true, new int[] {1,1,1}, 2);
    }

    private void testSingle(boolean expected, int[] nums, int k) {
        assertEquals(expected, containsNearbyDuplicate(nums, k));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> lastIndices = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; ++i) {
            Integer index = lastIndices.get(nums[i]);
            if (index == null) {
                lastIndices.put(nums[i], i);
            } else {
                if (i - index <= k) {
                    return true;
                } else {
                    lastIndices.put(nums[i], i);
                }
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            int from = Math.max(i - k, 0);
            int to = Math.min(i + k + 1, n);

            for (int j = from; j < to; ++j) {
                if (i != j && nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
