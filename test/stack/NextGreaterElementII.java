package stack;

import util.ArrayTestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @see <a href="https://leetcode.com/problems/next-greater-element-ii/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class NextGreaterElementII extends ArrayTestCase {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> idMap = new HashMap<>();

        int len = nums.length;

        for (int i = 0; i < 2 * len - 1; ++i) {
            if (i > len && stack.isEmpty()) {
                break;
            }

            int j = i % len;
            while (!stack.isEmpty() && nums[j] > nums[stack.peek()]) {
                idMap.put(stack.peek(), j);
                stack.pop();
            }
            stack.push(j);
        }

        int[] result = new int[len];
        for (int i = 0; i < len; ++i) {
            if (idMap.containsKey(i)) {
                result[i] = nums[idMap.get(i)];
            } else {
                result[i] = -1;
            }
        }

        return result;
    }

    public void test() {
        testSingle(new int[] {1, 2, -1}, new int[] {2, -1, 1});
        testSingle(new int[] {1, 1}, new int[] {-1, -1});
        testSingle(new int[] {3, 2, 1}, new int[] {-1, 3, 3});
        testSingle(new int[0], new int[0]);
    }

    private void testSingle(int[] input, int[] expected) {
        assertArraysEqual(expected, nextGreaterElements(input));
    }
}
