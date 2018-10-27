import junit.framework.TestCase;

public class FindKthLargest extends TestCase {
    public void test() {
        Quicksort qs = new Quicksort();

//        int[] arr = new int[] {3,2};
//        int[] arr = new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
//        qs.sort(arr);
//        for (int i = 0; i < arr.length; ++i) {
//            System.out.print(String.format("%s:\t%s\n", arr.length - i, arr[i]));
//        }
//        assertTrue(validate(arr, 1, 2));
//
//        arr = new int[] {2, 1};
//        qs.sort(arr);
//        assertTrue(validate(arr, 1, 2));

        assertEquals(12, findKthLargest(new int[] {8,9,4,12,3,2,1}, 1));
        assertEquals(9, findKthLargest(new int[] {8,9,4,12,3,2,1}, 2));
        assertEquals(3, findKthLargest(new int[] {8,9,4,12,3,2,1}, 5));
        assertEquals(9, findKthLargest(new int[] {8,9,4,12,3,2}, 2));
        assertEquals(1, findKthLargest(new int[] {1}, 1));
        assertEquals(1, findKthLargest(new int[] {2,1}, 2));
        assertEquals(1, findKthLargest(new int[] {1,2}, 2));
        assertEquals(2, findKthLargest(new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6}, 20));
        assertEquals(2, findKthLargest(new int[] {3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,10,11,5,6,2,4,7,8,5,6}, 20));
    }

    private boolean validate(int[] actual, int... expected) {
        for (int i = 0; i < actual.length - 1; i++) {
            if (actual[i] != expected[i]) {
                return false;
            }
        }
        return true;
    }

    private int findKthLargest(int[] nums, int k) {
        return findKthLargestInRange(nums, 0, nums.length-1, k-1);
    }

    private int findKthLargestInRange(int[] nums, int left, int right, int k) {
        if (right == left) {
            return nums[right];
        } else if (right - left == 1) {
            if (nums[left] < nums[right]) {
                swap(nums, left, right);
            }
            return nums[k];
        }

        int i = left;
        int j = right;

        int pivotIndex = (left + right) / 2;
        int pivot = nums[pivotIndex];

        while (i < j) {
            while (i <= right && nums[i] > pivot) ++i;
            while (j >= left && nums[j] < pivot) --j;

            if (i < j) {
                swap(nums, i, j);
                ++i;
                --j;
            }
        }

        if (i != j) {
            if (nums[i] == pivot && nums[j] == pivot) {
                if (j == k) {
                    i = k;
                }
            } else if (nums[i] < nums[j]) {
                i = j;
            }
        }

        if (i == k) {
            return findMin(nums, left, i);
        } else if (i < k) {
            return findKthLargestInRange(nums, i, right, k);
        } else {
            return findKthLargestInRange(nums, left, i, k);
        }
    }

    private int findMin(int[] nums, int left, int right) {
        int min = Integer.MAX_VALUE;

        for (int i = left; i <= right; ++i) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
