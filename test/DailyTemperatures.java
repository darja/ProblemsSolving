import util.ArrayTestCase;

import java.util.Stack;

public class DailyTemperatures extends ArrayTestCase {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;
    }

    public int[] dailyTemperatures1(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (temperatures[i] < temperatures[j]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    public void test() {
        testSingle(new int[] {73, 74, 75, 71, 69, 72, 76, 73}, new int[] {1, 1, 4, 2, 1, 1, 0, 0});
        testSingle(new int[] {73, 74, 75}, new int[] {1, 1, 0});
        testSingle(new int[] {75, 74, 75}, new int[] {0, 1, 0});
        testSingle(new int[] {75, 74, 73}, new int[] {0, 0, 0});
        testSingle(new int[] {75}, new int[] {0});
    }

    private void testSingle(int[] input, int[] expected) {
        assertArraysEqual(expected, dailyTemperatures(input));
    }
}
