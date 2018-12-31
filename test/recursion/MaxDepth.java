package recursion;

import junit.framework.TestCase;
import util.TreeNode;

/**
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">Problem Description</a>
 */
public class MaxDepth extends TestCase {
    public void test0() {
        testSingle(0, null);
    }

    public void test1() {
        TreeNode node = new TreeNode(1);
        testSingle(1, node);
    }

    public void test2() {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.right = new TreeNode(3);

        testSingle(3, node);
    }

    public void test3() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.right = new TreeNode(3);

        testSingle(3, node);
    }

    public void test4() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(21);
        node.left.left = new TreeNode(211);
        node.left.right = new TreeNode(212);
        node.left.right.right = new TreeNode(2122);
        node.right = new TreeNode(22);
        node.right.right = new TreeNode(222);

        testSingle(4, node);
    }

    private void testSingle(int expected, TreeNode tree) {
        assertEquals(expected, maxDepth(tree));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.right), maxDepth(root.left));
    }
}
