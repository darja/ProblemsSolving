import util.TreeNode;
import util.TreeTestCase;

public class AddTreeRow extends TreeTestCase {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }

        addRowIter(root, v, d, 2);

        return root;
    }

    public void addRowIter(TreeNode root, int v, int d, int level) {
        if (level == d) {
            TreeNode right = new TreeNode(v);
            right.right = root.right;
            root.right = right;

            TreeNode left = new TreeNode(v);
            left.left = root.left;
            root.left = left;
        } else {
            if (root.left != null) {
                addRowIter(root.left, v, d, level + 1);
            }

            if (root.right != null) {
                addRowIter(root.right, v, d, level + 1);
            }
        }
    }

    public void testAddRoot() {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(2);
        t.right = new TreeNode(2);

        TreeNode result = addOneRow(t, 0, 1);

        TreeNode e = new TreeNode(0);
        e.left = new TreeNode(1);
        e.left.left = new TreeNode(2);
        e.left.right = new TreeNode(2);

        assertTreeEquals(e, result);
    }

    public void testMiddle() {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(12);
        t.right = new TreeNode(12);

        t.left.left = new TreeNode(111);
        t.left.right = new TreeNode(112);
        t.right.left = new TreeNode(121);
        t.right.right = new TreeNode(122);

        t.right.right.left = new TreeNode(1221);

        TreeNode result = addOneRow(t, 100, 2);

        TreeNode e = new TreeNode(1);
        e.left = new TreeNode(100);
        e.right = new TreeNode(100);

        e.left.left = new TreeNode(12);
        e.right.right = new TreeNode(12);

        e.left.left.left = new TreeNode(111);
        e.left.left.right = new TreeNode(112);
        e.right.right.left = new TreeNode(121);
        e.right.right.right = new TreeNode(122);

        e.right.right.right.left = new TreeNode(1221);

        assertTreeEquals(e, result);
    }

    public void testEnd() {
        TreeNode t = new TreeNode(1);
        t.left = new TreeNode(12);
        t.right = new TreeNode(12);

        t.left.left = new TreeNode(111);
        t.left.right = new TreeNode(112);
        t.right.left = new TreeNode(121);
        t.right.right = new TreeNode(122);

        t.right.right.left = new TreeNode(1221);

        TreeNode result = addOneRow(t, 200, 5);

        TreeNode e = new TreeNode(1);
        e.left = new TreeNode(12);
        e.right = new TreeNode(12);

        e.left.left = new TreeNode(111);
        e.left.right = new TreeNode(112);
        e.right.left = new TreeNode(121);
        e.right.right = new TreeNode(122);

        e.right.right.left = new TreeNode(1221);
        e.right.right.left.left = new TreeNode(200);
        e.right.right.left.right = new TreeNode(200);

        assertTreeEquals(e, result);
    }
}
