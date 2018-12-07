package array;

import junit.framework.TestCase;

import java.util.TreeSet;

/**
 * @see <a href="https://leetcode.com/problems/k-empty-slots/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
// todo can be improved
public class KEmptySlots extends TestCase {
    public int kEmptySlots(int[] flowers, int k) {
        TreeSet<Integer> blooming = new TreeSet<>();
        for (int i = 0; i < flowers.length - k; ++i) {
            blooming.add(flowers[i]);

            Integer h = blooming.higher(flowers[i]);
            if (h != null && h - flowers[i] - 1 == k) return i + 1;

            Integer l = blooming.lower(flowers[i]);
            if (l != null && flowers[i] - l - 1 == k) return i + 1;
        }

        return -1;
    }

    private void testOne(int[] flowers, int k, int expected) {
        assertEquals(expected, kEmptySlots(flowers, k));
    }

    public void test() {
        testOne(new int[] {1, 3, 2}, 1, 2);
        testOne(new int[] {1, 2, 3}, 1, -1);
        testOne(new int[] {1, 2, 3, 5, 4}, 1, 4);
        testOne(new int[] {5, 4, 2, 1, 3}, 1, 3);
        testOne(new int[] {5, 4, 2, 1, 3}, 3, -1);
        testOne(new int[] {1, 4, 2, 5, 9, 3, 6, 8, 7}, 3, 5);
        testOne(new int[] {1, 5, 2, 4, 9, 3, 6, 8, 7}, 5, -1);
    }
}
