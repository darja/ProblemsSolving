import junit.framework.TestCase;

public class FindMinimumInRotatedSortedArray extends TestCase {
    public int findMin(int[] nums) {
        return Math.min(findMinIter(nums, 0, nums.length - 1), nums[0]);
    }

    private int findMinIter(int[] nums, int from, int to) {
        if (to == from) {
            return nums[from];
        } else if (to - from == 1) {
            return Math.min(nums[from], nums[to]);
        }

        int mid = (from + to) / 2;
        if (nums[mid - 1] > nums[mid]) {
            return nums[mid];
        }

        if (nums[mid] > nums[0]) {
            return findMinIter(nums, mid, to);
        } else {
            return findMinIter(nums, from, mid);
        }
    }

    public void test1() {
        testOne(new int[]{1}, 1);
    }

    public void test2() {
        testOne(new int[]{1, 2}, 1);
        testOne(new int[]{2, 1}, 1);
    }

    public void test3() {
        testOne(new int[]{2, 3, 1}, 1);
        testOne(new int[]{1, 2, 3}, 1);
        testOne(new int[]{3, 1, 2}, 1);
    }

    public void test4() {
        testOne(new int[]{1, 2, 3, 4}, 1);
        testOne(new int[]{2, 3, 4, 1}, 1);
        testOne(new int[]{3, 4, 1, 2}, 1);
        testOne(new int[]{4, 1, 2, 3}, 1);
    }

    public void test5() {
        testOne(new int[] {1, 2, 3, 4, 5}, 1);
        testOne(new int[] {2, 3, 4, 5, 1}, 1);
        testOne(new int[] {3, 4, 5, 1, 2}, 1);
        testOne(new int[] {4, 5, 1, 2, 3}, 1);
        testOne(new int[] {5, 1, 2, 3, 4}, 1);
    }

    private void testOne(int[] nums, int expected) {
        assertEquals(expected, findMin(nums));
    }
}
