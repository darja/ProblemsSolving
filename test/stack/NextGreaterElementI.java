package stack;

import util.ArrayTestCase;

import java.util.HashMap;
import java.util.Stack;

/**
 * @see <a href="https://leetcode.com/problems/next-greater-element-i/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class NextGreaterElementI extends ArrayTestCase {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nextEl = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                nextEl.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            if (nextEl.containsKey(nums1[i])) {
                result[i] = nextEl.get(nums1[i]);
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public void test() {
        testSingle(new int[] {}, new int[] {}, new int[] {});
        testSingle(new int[] {5, 5, -1}, new int[] {1, 2, 5}, new int[] {4,3,2,1,5});
        testSingle(new int[] {-1, 3, -1}, new int[] {6, 2, 4}, new int[] {6, 2, 3, 4});
        testSingle(new int[] {-1}, new int[] {1}, new int[] {1});
        testSingle(new int[] {2}, new int[] {1}, new int[] {1, 2});
    }

    private void testSingle(int[] result, int[] nums1, int[] nums2) {
        assertArraysEqual(result, nextGreaterElement(nums1, nums2));
    }
}
