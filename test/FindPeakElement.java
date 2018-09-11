import util.TestCaseOneOf;

public class FindPeakElement extends TestCaseOneOf {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        } else if (len == 2) {
            return nums[0] > nums[1] ? 0 : 1;
        }


        return findPeakInRange(nums, 0, nums.length - 1);
    }

    private int findPeakInRange(int[] nums, int from, int to) {
        if (to < from) {
            return -1;
        }

        int mid = (from + to) / 2;

        if ((mid == 0 || nums[mid] > nums[mid - 1]) &&
            (mid == nums.length - 1 || nums[mid] > nums[mid + 1])) {
            return mid;
        } else {
            int left = findPeakInRange(nums, from, mid - 1);
            if (left >= 0) {
                return left;
            } else {
                return findPeakInRange(nums, mid + 1, to);
            }
        }
    }

    public void test1() {
        testCase(new int[] {1}, 0);
    }

    public void test2() {
        testCase(new int[] {1, 2}, 1);
        testCase(new int[] {3, 2}, 0);
    }

    public void test3() {
        testCase(new int[] {1, 2, 3}, 2);
        testCase(new int[] {1, 4, 3}, 1);
        testCase(new int[] {5, 4, 7}, 0, 2);
    }

    public void test4() {
        testCase(new int[] {1, 2, 3, 4}, 3);
        testCase(new int[] {1, 6, 3, 4}, 1, 3);
        testCase(new int[] {9, 6, 3, 4}, 0, 3);
        testCase(new int[] {9, 6, 8, 4}, 0, 2);
    }

    public void test5() {
        testCase(new int[] {1, 2, 3, 4, 5}, 4);
        testCase(new int[] {1, 2, 7, 4, 5}, 2, 4);
        testCase(new int[] {9, 2, 7, 4, 5}, 0, 2, 4);
        testCase(new int[] {1, 9, 7, 4, 5}, 1, 4);
        testCase(new int[] {-1, 9, 7, 4, 5}, 1, 4);
    }

    public void test6() {
        testCase(new int[] {1, 2, 3, 4, 5, 6}, 5);
        testCase(new int[] {9, 2, 3, 4, 5, 6}, 0, 5);
        testCase(new int[] {9, 2, 13, 4, 5, 6}, 0, 2, 5);
        testCase(new int[] {1, 0, 1, 0, -1, 6}, 0, 2, 5);
        testCase(new int[] {1, 2, 1, 2, 4, 3}, 1, 4);
    }

    private void testCase(int[] nums, int... expected) {
        assertOneOf(findPeakElement(nums), expected);
    }
}
