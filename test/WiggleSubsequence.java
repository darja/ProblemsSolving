import junit.framework.TestCase;

public class WiggleSubsequence extends TestCase {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int up = 1,
            down = 1,
            result = 1;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
                result = up;
            }

            if (nums[i] < nums[i - 1]) {
                down = up + 1;
                result = down;
            }
        }

        return result;
    }

    public void test() {
        testSingle(new int[0], 0);
        testSingle(new int[]{1}, 1);
        testSingle(new int[]{1, 5, 2, 6}, 4);
        testSingle(new int[]{1, 2, 3, 4, 5, 6, 7}, 2);
        testSingle(new int[]{1, 5, 5, 2, 6}, 4);
        testSingle(new int[]{1, 5, 5, 5, 5, 5, 2, 6}, 4);
        testSingle(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}, 7);
    }

    private void testSingle(int[] nums, int expectedResult) {
        assertEquals(expectedResult, wiggleMaxLength(nums));
    }
}
