package recursion;

import junit.framework.TestCase;

/**
 * @see <a href="https://leetcode.com/problems/number-of-islands/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class NumberOfIslands extends TestCase {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    count++;
                    unmarkIsland(grid, i, j);
                }
            }
        }
        return count;
    }

    private void unmarkIsland(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length ||
            j < 0 || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] == '1') {
            grid[i][j] = '0';

            unmarkIsland(grid, i - 1, j);
            unmarkIsland(grid, i + 1, j);
            unmarkIsland(grid, i, j - 1);
            unmarkIsland(grid, i, j + 1);
        }
    }

    public void test1() {
        testCase(new char[][] {
            {'0', '0', '0', '1'},
            {'0', '1', '0', '1'},
            {'0', '1', '1', '1'},
            {'0', '0', '1', '0'}
        }, 1);
    }

    public void test2() {
        testCase(new char[][] {
            {'0', '0', '0', '1'},
            {'0', '1', '0', '1'},
            {'0', '1', '1', '1'},
            {'0', '0', '1', '0'},
            {'1', '1', '0', '1'}
        }, 3);
    }

    public void test3() {
        testCase(new char[][] {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '1'},
            {'0', '0', '1'},
            {'1', '1', '0'},
            {'1', '1', '0'}
        }, 2);
    }

    public void test4() {
        testCase(new char[][] {{'1'}}, 1);
    }

    public void test5() {
        testCase(new char[][] {{'1', '1', '0'}}, 1);
    }

    public void test6() {
        testCase(new char[][] {
            {'0', '0', '0'},
            {'0', '0', '0'},
        }, 0);
    }

    private void testCase(char[][] grid, int expectedCount) {
        assertEquals(expectedCount, numIslands(grid));
    }
}
