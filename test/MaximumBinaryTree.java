import util.TreeNode;
import util.TreeTestCase;

public class MaximumBinaryTree extends TreeTestCase {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTreeFromRange(nums, 0, nums.length);
    }

    private TreeNode constructMaximumBinaryTreeFromRange(int[] nums, int from, int to) {
        if (from >= to) {
            return null;
        }

        int max = nums[from];
        int maxIndex = from;
        for (int i = from + 1; i < to; ++i) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTreeFromRange(nums, from, maxIndex);
        root.right = constructMaximumBinaryTreeFromRange(nums, maxIndex + 1, to);
        return root;
    }

    public void test1() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {3, 2, 1, 6, 0, 5});

        TreeNode expected = new TreeNode(6);
        expected.left = new TreeNode(3);
        expected.left.right = new TreeNode(2);
        expected.left.right.right = new TreeNode(1);
        expected.right = new TreeNode(5);
        expected.right.left = new TreeNode(0);

        assertTreeEquals(expected, tree);
    }

    public void testNull() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {});
        assertNull(tree);
    }

    public void testOneValue() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {1});
        TreeNode expected = new TreeNode(1);

        assertTreeEquals(expected, tree);
    }

    public void testTwoValues1() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {1, 2});

        TreeNode expected = new TreeNode(2);
        expected.left = new TreeNode(1);

        assertTreeEquals(expected, tree);
    }

    public void testTwoValues2() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {2, 1});

        TreeNode expected = new TreeNode(2);
        expected.right = new TreeNode(1);

        assertTreeEquals(expected, tree);
    }

    public void testThreeValues1() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {1, 2, 3});

        TreeNode expected = new TreeNode(3);
        expected.left = new TreeNode(2);
        expected.left.left = new TreeNode(1);

        assertTreeEquals(expected, tree);
    }

    public void testThreeValues2() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {1, 3, 2});

        TreeNode expected = new TreeNode(3);
        expected.left = new TreeNode(1);
        expected.right = new TreeNode(2);

        assertTreeEquals(expected, tree);
    }

    public void testThreeValues3() {
        TreeNode tree = constructMaximumBinaryTree(new int[] {3, 1, 2});

        TreeNode expected = new TreeNode(3);
        expected.right = new TreeNode(2);
        expected.right.left = new TreeNode(1);

        assertTreeEquals(expected, tree);
    }
}
