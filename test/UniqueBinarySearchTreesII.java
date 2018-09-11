import util.TreeNode;
import util.TreeTestCase;

import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesII extends TreeTestCase {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> trees = new LinkedList<>();
        if (n <= 0) {
            return trees;
        }

        trees.add(new TreeNode(1));
        if (n == 1) {
            return trees;
        }

        for (int i = 2; i <= n; ++i) {
            List<TreeNode> newTrees = new LinkedList<>();

            for (TreeNode t : trees) {
                TreeNode newNode = new TreeNode(i);
                newNode.left = copyTree(t);
                newTrees.add(newNode);

                TreeNode right = t;
                while (right != null) {
                    TreeNode node = new TreeNode(i);
                    node.left = right.right;
                    right.right = node;

                    newTrees.add(copyTree(t));

                    right.right = node.left;
                    right = right.right;
                }
            }
            trees = newTrees;
        }

        return trees;
    }

    private TreeNode copyTree(TreeNode tree) {
        if (tree == null) {
            return null;
        }

        TreeNode copy = new TreeNode(tree.val);
        copy.left = copyTree(tree.left);
        copy.right = copyTree(tree.right);

        return copy;
    }

    public void test() {
//        testSingle(1, 1);
//        testSingle(2, 2);
        testSingle(5, 3);
        testSingle(14, 4);
        testSingle(42, 5);

    }

    private void testSingle(int count, int n) {
        System.out.printf("===== %s =====\n", n);

        List<TreeNode> trees = generateTrees(n);
        for (TreeNode tree: trees) {
            System.out.println("--- tree ---");
            tree.print();
            System.out.println();
        }

        assertEquals(count, trees.size());
    }
}
