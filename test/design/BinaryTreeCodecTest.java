package design;

import util.TreeNode;
import util.TreeTestCase;

public class BinaryTreeCodecTest extends TreeTestCase {
    BinaryTreeCodec codec = new BinaryTreeCodec();

    public void testNull() {
        String serialized = codec.serialize(null);
        assertNull(codec.deserialize(serialized));
    }

    private void testTree(TreeNode tree) {
        String serialized = codec.serialize(tree);
        System.out.printf("[%s]\n", serialized);
        TreeNode deserialized = codec.deserialize(serialized);
        assertTreeEquals(tree, deserialized);
    }

    public void testTrivial() {
        TreeNode tree = new TreeNode(1);
        testTree(tree);
    }

    public void testOnlyRight() {
        TreeNode tree = new TreeNode(1);
        tree.right = new TreeNode(2);
        testTree(tree);
    }

    public void testOnlyLeft() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        testTree(tree);
    }

    public void testOneLevel() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        testTree(tree);
    }

    public void testTwoLevels() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(3);
        tree.left.right = new TreeNode(4);
        tree.right = new TreeNode(5);
        tree.right.right = new TreeNode(6);
        testTree(tree);
    }

    public void testMoreLevels() {
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.left.right.left = new TreeNode(8);
        tree.left.right.right = new TreeNode(9);
        tree.right = new TreeNode(3);
        tree.right.left = new TreeNode(6);
        tree.right.left.left = new TreeNode(10);
        tree.right.right = new TreeNode(7);
        testTree(tree);
        // 1, 2, 3, 4, 5, 6, 7, , , 8, 9, 10, , ,
    }
}
