import junit.framework.TestCase;

public class TwoSumSorted extends TestCase {
    public int[] twoSum(int[] numbers, int target) {
        int lo = 0;
        int hi = numbers.length - 1;

        int sum;
        while (lo < hi) {
            sum = numbers[lo] + numbers[hi];
            if (sum == target) {
                return new int[] {lo + 1, hi + 1};
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[0];
    }

    public void test() {
        testCase(new int[] {2, 7, 11, 15}, 9, new int[] {1, 2});
        testCase(new int[] {1, 2, 7, 11, 15}, 16, new int[] {1, 5});
        testCase(new int[] {1, 2, 7, 11, 15}, 9, new int[] {2, 3});
        testCase(new int[] {2, 7, 11, 15}, 17, new int[] {1, 4});
        testCase(new int[] {2, 7}, 9, new int[] {1, 2});
        testCase(new int[] {2, 2}, 4, new int[] {1, 2});
    }

    private void testCase(int[] nums, int target, int[] expected) {
        int[] actual = twoSum(nums, target);
        assertEquals("Index 0", actual[0], expected[0]);
        assertEquals("Index 1", actual[1], expected[1]);
    }

}
