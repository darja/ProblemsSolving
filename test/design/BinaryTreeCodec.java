package design;

import util.TreeNode;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BinaryTreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        List<TreeNode> rootLevel = new LinkedList<>();
        rootLevel.add(root);
        serializeFromLevel(sb, rootLevel);

        int n = sb.length();

        return sb.substring(0, n - 1);
    }

    private void serializeFromLevel(StringBuilder sb, List<TreeNode> levelNodes) {
        List<TreeNode> nextLevelNodes = new LinkedList<>();
        boolean isOver = true;
        for (TreeNode node : levelNodes) {
            if (node != null) {
                nextLevelNodes.add(node.left);
                nextLevelNodes.add(node.right);
                sb.append(node.val);
                if (node.left != null || node.right != null) {
                    isOver = false;
                }
            } else {
                nextLevelNodes.add(null);
                nextLevelNodes.add(null);
            }
            sb.append(',');
        }

        if (!isOver) {
            serializeFromLevel(sb, nextLevelNodes);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(",");

        if (parts[0].length() == 0) {
            return null;
        }

        int rootValue = Integer.parseInt(parts[0]);
        TreeNode root = new TreeNode(rootValue);

        if (parts.length > 1) {
            List<TreeNode> rootLevel = new LinkedList<>();
            rootLevel.add(root);
            deserializeFromLevel(parts, 1, rootLevel);
        }
        return root;
    }

    private void deserializeFromLevel(String[] parts, int fromIndex, List<TreeNode> levelNodes) {
        List<TreeNode> nextLevelNodes = new LinkedList<>();
        int i = fromIndex;
        for (TreeNode node : levelNodes) {
            TreeNode left = null;
            TreeNode right = null;
            if (node != null) {
                if (i >= parts.length) return;
                left = createNodeFromString(parts[i]);
                node.left = left;
                i++;

                if (i >= parts.length) return;
                right = createNodeFromString(parts[i]);
                node.right = right;
                i++;
            }
            nextLevelNodes.add(left);
            nextLevelNodes.add(right);
        }
        if (i < parts.length) {
            deserializeFromLevel(parts, i, nextLevelNodes);
        }
    }

    private TreeNode createNodeFromString(String val) {
        if (val.isEmpty()) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(val));
        }
    }
}
