package tree;

import junit.framework.TestCase;
import util.TreeNode;

/**
 * @see <a href="https://leetcode.com/problems/longest-univalue-path/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class LongestUnivaluePath extends TestCase {
    private int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        maxPath = 0;
        maxPathFrom(root);
        return maxPath;
    }

    private int maxPathFrom(TreeNode root) {
        if (root == null) return 0;

        int leftMax = maxPathFrom(root.left);
        int rightMax = maxPathFrom(root.right);

        int leftPath = root.left != null && root.left.val == root.val ? leftMax + 1 : 0;
        int rightPath = root.right != null && root.right.val == root.val ? rightMax + 1 : 0;

        maxPath = Math.max(maxPath, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }

    private void testOne(TreeNode tree, int expected) {
        assertEquals(expected, longestUnivaluePath(tree));
    }

    public void testTrivial() {
        testOne(null, 0);
        testOne(new TreeNode(1), 0);
        testOne(new TreeNode(2), 0);
    }

    public void testOneLevelAllEqual() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(1);
        testOne(tree, 2);
    }

    public void testOneLevelAllDifferent() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(3);
        tree.right = new TreeNode(2);
        testOne(tree, 0);
    }

    public void testOneLevelNotAllEqual() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(1);
        testOne(tree, 1);

        tree = new TreeNode(1);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(2);
        testOne(tree, 1);

        tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        testOne(tree, 0);
    }

    public void testOnlyLeftEqual() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(1);
        testOne(tree, 1);
    }

    public void testOnlyRightEqual() {
        TreeNode tree = new TreeNode(1);
        tree.right = new TreeNode(1);
        testOne(tree, 1);
    }

    public void testOnlyLeftNotEqual() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        testOne(tree, 0);
    }

    public void testOnlyRightNotEqual() {
        TreeNode tree = new TreeNode(1);
        tree.right = new TreeNode(0);
        testOne(tree, 0);
    }

    public void testTwoLevels() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(3);
        tree.left.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        testOne(tree, 0);

        tree = new TreeNode(3);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(2);
        tree.right.left = new TreeNode(2);
        testOne(tree, 1);

        tree = new TreeNode(3);
        tree.left = new TreeNode(1);
        tree.left.right = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.right.left = new TreeNode(2);
        testOne(tree, 1);
    }

    public void testMoreLevels_1() {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(1);
        tree.left.left = new TreeNode(1);
        tree.left.left.left = new TreeNode(3);
        tree.left.right = new TreeNode(1);
        tree.left.right.right = new TreeNode(1);
        tree.right = new TreeNode(2);
        tree.right.right = new TreeNode(1);
        tree.right.right.left = new TreeNode(1);
        testOne(tree, 3);
    }

    public void testMoreLevels_2() {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(1);
        tree.left.left = new TreeNode(1);
        tree.left.left.left = new TreeNode(3);
        tree.left.right = new TreeNode(1);
        tree.left.right.right = new TreeNode(1);
        tree.right = new TreeNode(2);
        tree.right.right = new TreeNode(1);
        tree.right.right.left = new TreeNode(1);
        tree.right.right.left.left = new TreeNode(1);
        tree.right.right.left.right = new TreeNode(1);
        tree.right.right.left.right.left = new TreeNode(1);
        tree.right.right.left.right.right = new TreeNode(1);
        testOne(tree, 3);
    }

    public void testMoreLevels_3() {
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(1);
        tree.left.left = new TreeNode(1);
        tree.left.left.right = new TreeNode(8);
        tree.left.right = new TreeNode(1);
        tree.left.right.left = new TreeNode(1);
        tree.left.right.left.left = new TreeNode(1);
        tree.left.right.right = new TreeNode(1);
        tree.left.right.right.left = new TreeNode(3);
        tree.left.right.right.right = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.right.left = new TreeNode(2);
        tree.right.right = new TreeNode(2);
        tree.right.right.left = new TreeNode(2);
        tree.right.right.right = new TreeNode(3);
        testOne(tree, 4);
    }
}
