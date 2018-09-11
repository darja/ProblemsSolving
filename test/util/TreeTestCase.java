package util;

import junit.framework.TestCase;

public class TreeTestCase extends TestCase {
    protected void assertTreeEquals(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null) {
            assertNull(tree2);
            return;
        }

        assertNotNull(tree2);

        assertEquals(tree1.val, tree2.val);
        assertTreeEquals(tree1.left, tree2.left);
        assertTreeEquals(tree1.right, tree2.right);
    }
}
