import util.NestedListTestCase;

import java.util.*;

public class PermutationsII extends NestedListTestCase {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);
        List<Integer> src = new ArrayList<>(nums.length);
        for (int num : nums) {
            src.add(num);
        }
        permuteBack(result, new LinkedList<Integer>(), src);

        return result;
    }

    private void permuteBack(List<List<Integer>> result, LinkedList<Integer> current, List<Integer> src) {
        int len = src.size();
        if (len == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        int i = 0;
        while (i < len) {
            int item = src.get(i);
            current.add(item);
            src.remove(i);

            permuteBack(result, current, src);

            src.add(i, item);
            current.removeLast();
            i++;

            while (i < len && Objects.equals(src.get(i), src.get(i - 1))) {
                i++;
            }
        }
    }

    public void testTrivial() {
        testCase(new int[0], new int[0][0]);
        testCase(new int[] {1}, new int[][] {{1}});
        testCase(new int[] {Integer.MAX_VALUE}, new int[][] {{Integer.MAX_VALUE}});
        testCase(new int[] {Integer.MIN_VALUE}, new int[][] {{Integer.MIN_VALUE}});
    }

    public void testTwoElements() {
        testCase(new int[] {1, 2}, new int[][] {
            {1, 2}, {2, 1}
        });
        testCase(new int[] {2, 1}, new int[][] {
            {1, 2}, {2, 1}
        });
        testCase(new int[] {1, 1}, new int[][] {
            {1, 1}
        });
    }

    public void testThreeElements() {
        testCase(new int[] {1, 2, 3}, new int[][] {
            {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}
        });
        testCase(new int[] {2, 1, 1}, new int[][] {
            {1, 1, 2}, {1, 2, 1}, {2, 1, 1}
        });
        testCase(new int[] {1, 1, 1}, new int[][] {
            {1, 1, 1}
        });
    }

    public void testFourElements() {
        testCase(new int[] {1, 2, 3, 4}, new int[][] {
            {1, 2, 3, 4}, {1, 2, 4, 3}, {1, 3, 2, 4}, {1, 3, 4, 2}, {1, 4, 2, 3}, {1, 4, 3, 2},
            {2, 1, 3, 4}, {2, 1, 4, 3}, {2, 3, 1, 4}, {2, 3, 4, 1}, {2, 4, 1, 3}, {2, 4, 3, 1},
            {3, 1, 2, 4}, {3, 1, 4, 2}, {3, 2, 1, 4}, {3, 2, 4, 1}, {3, 4, 1, 2}, {3, 4, 2, 1},
            {4, 1, 2, 3}, {4, 1, 3, 2}, {4, 2, 1, 3}, {4, 2, 3, 1}, {4, 3, 1, 2}, {4, 3, 2, 1}
        });
        testCase(new int[] {1, 2, 1, 3}, new int[][] {
            {1, 1, 2, 3}, {1, 1, 3, 2}, {1, 2, 1, 3}, {1, 2, 3, 1}, {1, 3, 1, 2}, {1, 3, 2, 1},
            {2, 1, 1, 3}, {2, 1, 3, 1}, {2, 3, 1, 1},
            {3, 1, 1, 2}, {3, 1, 2, 1}, {3, 2, 1, 1}
        });
    }

    private void testCase(int[] nums, int[][] expected) {
        List<List<Integer>> result = permuteUnique(nums);
        System.out.println(result);
        assertEqualsOrdered(result, expected);
    }
}
