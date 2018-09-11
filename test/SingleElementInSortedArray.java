import junit.framework.TestCase;

public class SingleElementInSortedArray extends TestCase {
    public int singleNonDuplicate(int[] nums) {
        return findElement(nums, 0, nums.length - 1);
    }

    private Integer findElement(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mi = (start + end) / 2;
        int middleElement = nums[mi];

        if ((mi == 0 || middleElement != nums[mi - 1]) && (mi == nums.length - 1 || middleElement != nums[mi + 1])) {
            return middleElement;
        }

        Integer foundInLeft = findElement(nums, start, mi - 1);
        if (foundInLeft != null) {
            return foundInLeft;
        }
        return findElement(nums, mi + 1, end);
    }

    public void test() {
        testCase(new int[] {1, 1, 2}, 2);
        testCase(new int[] {0, 0, 2}, 2);
        testCase(new int[] {-1, -1, 2}, 2);
        testCase(new int[] {1, 2, 2}, 1);
        testCase(new int[] {1, 2, 2, 3, 3}, 1);
        testCase(new int[] {1, 1, 2, 3, 3}, 2);
        testCase(new int[] {1, 1, 2, 2, 3}, 3);
        testCase(new int[] {1, 2, 2, 3, 3, 4, 4}, 1);
        testCase(new int[] {1, 1, 2, 3, 3, 4, 4}, 2);
        testCase(new int[] {1, 1, 2, 2, 3, 4, 4}, 3);
        testCase(new int[] {1, 1, 2, 2, 3, 3, 4}, 4);
    }

    private void testCase(int[] nums, int result) {
        assertEquals(result, singleNonDuplicate(nums));
    }
}
