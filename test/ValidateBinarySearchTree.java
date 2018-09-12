import junit.framework.TestCase;
import util.TreeNode;

public class ValidateBinarySearchTree extends TestCase {
    public boolean isValidBST(TreeNode root) {
        return
            root == null ||
            root.left == null && root.right == null ||
            isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, Integer min, Integer max) {
        return
            (max == null || root.val < max) &&
            (min == null || root.val > min) &&
            (root.left == null || isValidBST(root.left, min, root.val)) &&
            (root.right == null || isValidBST(root.right, root.val, max));

    }

    public void testNull() {
        assertTrue(isValidBST(null));
    }

    public void testTrivial() {
        assertTrue(isValidBST(new TreeNode(1)));
        assertTrue(isValidBST(new TreeNode(10)));
        assertTrue(isValidBST(new TreeNode(-10)));
        assertTrue(isValidBST(new TreeNode(Integer.MIN_VALUE)));
        assertTrue(isValidBST(new TreeNode(Integer.MAX_VALUE)));
    }

    public void testMinMaxOk() {
        TreeNode node = new TreeNode(Integer.MIN_VALUE);
        node.right = new TreeNode(Integer.MAX_VALUE);
        assertTrue(isValidBST(node));

        node = new TreeNode(Integer.MAX_VALUE);
        node.left = new TreeNode(Integer.MIN_VALUE);
        assertTrue(isValidBST(node));
    }

    public void testMinMaxFail() {
        TreeNode node = new TreeNode(Integer.MIN_VALUE);
        node.left = new TreeNode(Integer.MAX_VALUE);
        assertFalse(isValidBST(node));

        node = new TreeNode(Integer.MAX_VALUE);
        node.right = new TreeNode(Integer.MIN_VALUE);
        assertFalse(isValidBST(node));
    }

    public void testOnlyLeftOk() {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(1);

        assertTrue(isValidBST(node));
    }

    public void testOnlyLeftFail() {
        TreeNode node = new TreeNode(-1);
        node.left = new TreeNode(1);

        assertFalse(isValidBST(node));
    }

    public void testOnlyRightOk() {
        TreeNode node = new TreeNode(4);
        node.right = new TreeNode(5);

        assertTrue(isValidBST(node));
    }

    public void testOnlyRightFail() {
        TreeNode node = new TreeNode(10);
        node.right = new TreeNode(4);

        assertFalse(isValidBST(node));
    }

    public void testOneLevelOk() {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(3);
        node.right = new TreeNode(14);

        assertTrue(isValidBST(node));
    }

    public void testOneLevelFail() {
        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(3);
        node.right = new TreeNode(1);

        assertFalse(isValidBST(node));

        node.left.val = 1;
        assertFalse(isValidBST(node));

        node.left.val = 11;
        node.right.val = 14;
        assertFalse(isValidBST(node));
    }

    public void testThreeLevelsOk() {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(2);

        node.right = new TreeNode(14);
        node.right.left = new TreeNode(7);
        node.right.left.left = new TreeNode(4);
        node.right.left.right = new TreeNode(9);
        node.right.right = new TreeNode(20);
        node.right.right.left = new TreeNode(16);
        node.right.right.right = new TreeNode(25);

        assertTrue(isValidBST(node));
    }

    public void testTwoLevelsFail() {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(2);

        node.right = new TreeNode(14);
        node.right.left = new TreeNode(7);
        node.right.right = new TreeNode(4);

        assertFalse(isValidBST(node));
    }
}
