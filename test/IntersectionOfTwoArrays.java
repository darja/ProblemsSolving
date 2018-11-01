import util.ArrayTestCase;

import java.util.HashSet;

public class IntersectionOfTwoArrays extends ArrayTestCase {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int item : nums1) {
            set1.add(item);
        }

        HashSet<Integer> intersect = new HashSet<>();
        for (int item : nums2) {
            if (set1.contains(item)) {
                intersect.add(item);
            }
        }

        int[] result = new int[intersect.size()];
        int i = 0;
        for (int item: intersect) {
            result[i] = item;
            i++;
        }
        return result;
    }

    private void testOne(int[] nums1, int[] nums2, int[] expected) {
        int[] result = intersection(nums1, nums2);
        assertArraysEqualUnordered(expected, result);
    }

    public void testEmpty() {
        testOne(new int[0], new int[0], new int[0]);
        testOne(new int[0], new int[] {1}, new int[0]);
        testOne(new int[] {2}, new int[0], new int[0]);
        testOne(new int[] {2, 3, 3}, new int[0], new int[0]);
        testOne(new int[0], new int[] {1, 2, 3}, new int[0]);
        testOne(new int[] {1}, new int[] {2}, new int[0]);
        testOne(new int[] {1, 3, 4}, new int[] {2, 33}, new int[0]);
    }

    public void testTrivial() {
        testOne(new int[] {1}, new int[] {1}, new int[] {1});
        testOne(new int[] {1}, new int[] {1, 1}, new int[] {1});
        testOne(new int[] {1, 1, 1}, new int[] {1}, new int[] {1});
    }

    public void testMore() {
        testOne(new int[] {2, 3, 1, 1, 1, 2, 2}, new int[] {1, 3}, new int[] {1, 3});
        testOne(new int[] {1, 2, 8, 3, 1}, new int[] {8, 1, 14}, new int[] {1, 8});
    }
}
