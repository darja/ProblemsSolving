import junit.framework.TestCase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TwoSum extends TestCase {
    private int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numsSet = new HashMap<>();
        Set<Integer> duplicatedNums = new HashSet<>();

        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];

            if (numsSet.containsKey(num)) {
                duplicatedNums.add(num);
            }

            numsSet.put(num, i);
        }

        for (int i = 0; i < nums.length; ++i) {
            int num1 = nums[i];
            int num2 = target - num1;

            if (numsSet.containsKey(num2) && (num1 != num2 || duplicatedNums.contains(num1))) {
                return new int[] { i, numsSet.get(num2) };
            }
        }
        return new int[0];
    }

    public void test() {
        testCase(new int[] {2, 7, 11, 15}, 9, new int[] {0, 1});
        testCase(new int[] {1, 2, 7, 11, 15}, 9, new int[] {1, 2});
        testCase(new int[] {2, 7, 11, 15}, 17, new int[] {0, 3});
        testCase(new int[] {2, 7}, 9, new int[] {0, 1});
        testCase(new int[] {2, 2}, 4, new int[] {0, 1});
    }

    private void testCase(int[] nums, int target, int[] expected) {
        int[] actual = twoSum(nums, target);
        assertEquals("Index 0", actual[0], expected[0]);
        assertEquals("Index 1", actual[1], expected[1]);
    }
}
