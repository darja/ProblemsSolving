package array;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @see <a href="https://leetcode.com/problems/degree-of-an-array/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class DegreeOfArray extends TestCase {
    class NumberStat {
        int first;
        int last;
        int count;

        NumberStat(int index) {
            count = 1;
            first = last = index;
        }

        void addEntry(int index) {
            last = index;
            count++;
        }

        int getIntervalLength() {
            return last - first + 1;
        }
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, NumberStat> stat = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            int item = nums[i];
            if (stat.containsKey(item)) {
                stat.get(item).addEntry(i);
            } else {
                stat.put(item, new NumberStat(i));
            }
        }

        int degree = 0;
        int minSubArraySize = stat.isEmpty() ? 0 : Integer.MAX_VALUE;

        for (int item : stat.keySet()) {
            NumberStat itemStat = stat.get(item);
            int subArraySize = itemStat.getIntervalLength();
            if (itemStat.count > degree) {
                degree = itemStat.count;
                minSubArraySize = subArraySize;

            } else if (itemStat.count == degree && subArraySize < minSubArraySize){
                minSubArraySize = subArraySize;

            }
        }

        return minSubArraySize;
    }

    public void test() {
        testSingle(new int[] {1, 2, 2, 1, 3}, 2);
        testSingle(new int[] {1, 2, 2, 1, 3}, 2);
        testSingle(new int[] {1}, 1);
        testSingle(new int[] {}, 0);
        testSingle(new int[] {1, 1, 1, 1, 1, 1}, 6);
        testSingle(new int[] {1, 1, 1, 2, 3, 2, 2}, 3);
        testSingle(new int[]{1, 2, 2, 3, 1, 4, 2}, 6);
    }

    private void testSingle(int[] input, int expected) {
        assertEquals(expected, findShortestSubArray(input));
    }
}
