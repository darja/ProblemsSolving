package design;

import util.TreeNode;

import java.util.LinkedList;

public class BSTIterator {
    private LinkedList<TreeNode> path = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        appendLeftBranch(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !path.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode last = path.pop();
        appendLeftBranch(last.right);
        return last.val;
    }

    private void appendLeftBranch(TreeNode node) {
        TreeNode nodeToAdd = node;
        while (nodeToAdd != null) {
            path.push(nodeToAdd);
            nodeToAdd = nodeToAdd.left;
        }
    }
}
