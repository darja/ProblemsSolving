package util;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public void print() {
        List<TreeNode> level = new LinkedList<>();
        level.add(this);
        boolean hasLevel = true;

        while (hasLevel) {
            hasLevel = false;
            List<TreeNode> nextLevel = new LinkedList<>();
            for (TreeNode t : level) {
                System.out.print(t != null ? t.val : "()");
                System.out.print("\t");

                nextLevel.add(t != null ? t.left : null);
                nextLevel.add(t != null ? t.right : null);

                if (t != null && (t.left != null || t.right != null)) {
                    hasLevel = true;
                }
            }
            System.out.println();
            level = nextLevel;

        }
    }
}
