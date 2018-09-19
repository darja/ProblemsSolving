import util.NestedListTestCase;

import java.util.*;

public class Permutations extends NestedListTestCase {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums.length == 0) {
            return result;
        }

        List<Integer> source = new ArrayList<>();
        for (int i : nums) {
            source.add(i);
        }

        permuteBack(result, new LinkedList<Integer>(), source);

        return result;
    }

    private void permuteBack(List<List<Integer>> result, LinkedList<Integer> current, List<Integer> source) {
        if (source.isEmpty()) {
            result.add(new ArrayList<>(current));
            return;
        }

        Integer item;

        int len = source.size();
        for (int i = 0; i < len; ++i) {
            item = source.get(i);

            current.add(item);

            source.remove(i);
            permuteBack(result, current, source);
            source.add(i, item);
            current.removeLast();
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
        testCase(new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE}, new int[][] {
            {Integer.MAX_VALUE, Integer.MIN_VALUE},
            {Integer.MIN_VALUE, Integer.MAX_VALUE}
        });
    }

    public void testThreeElements() {
        testCase(new int[] {1, 2, 3}, new int[][] {
            {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}
        });
        testCase(new int[] {2, 1, 3}, new int[][] {
            {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}
        });
        testCase(new int[] {3, 2, 1}, new int[][] {
            {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}
        });
    }

    public void testFourElements() {
        testCase(new int[] {1, 2, 3, 4}, new int[][] {
            {1, 2, 3, 4}, {1, 2, 4, 3}, {1, 3, 2, 4}, {1, 3, 4, 2}, {1, 4, 2, 3}, {1, 4, 3, 2},
            {2, 1, 3, 4}, {2, 1, 4, 3}, {2, 3, 1, 4}, {2, 3, 4, 1}, {2, 4, 1, 3}, {2, 4, 3, 1},
            {3, 1, 2, 4}, {3, 1, 4, 2}, {3, 2, 1, 4}, {3, 2, 4, 1}, {3, 4, 1, 2}, {3, 4, 2, 1},
            {4, 1, 2, 3}, {4, 1, 3, 2}, {4, 2, 1, 3}, {4, 2, 3, 1}, {4, 3, 1, 2}, {4, 3, 2, 1}
        });
        testCase(new int[] {4, 2, 1, 3}, new int[][] {
            {1, 2, 3, 4}, {1, 2, 4, 3}, {1, 3, 2, 4}, {1, 3, 4, 2}, {1, 4, 2, 3}, {1, 4, 3, 2},
            {2, 1, 3, 4}, {2, 1, 4, 3}, {2, 3, 1, 4}, {2, 3, 4, 1}, {2, 4, 1, 3}, {2, 4, 3, 1},
            {3, 1, 2, 4}, {3, 1, 4, 2}, {3, 2, 1, 4}, {3, 2, 4, 1}, {3, 4, 1, 2}, {3, 4, 2, 1},
            {4, 1, 2, 3}, {4, 1, 3, 2}, {4, 2, 1, 3}, {4, 2, 3, 1}, {4, 3, 1, 2}, {4, 3, 2, 1}
        });
    }

    private void testCase(int[] nums, int[][] expected) {
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
        assertEqualsOrdered(result, expected);
    }
}
