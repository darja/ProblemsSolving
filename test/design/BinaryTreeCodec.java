package design;

import util.TreeNode;

import java.util.LinkedList;

@SuppressWarnings("WeakerAccess")
public class BinaryTreeCodec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            TreeNode current = nodes.removeFirst();
            if (current != null) {
                sb.append(current.val);
                sb.append(',');
                nodes.addLast(current.left);
                nodes.addLast(current.right);
            } else {
                sb.append(',');
            }

        }

        int n = sb.length();
        return sb.substring(0, n - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] parts = data.split(",");

        if (parts[0].length() == 0) {
            return null;
        }

        int rootValue = Integer.parseInt(parts[0]);
        TreeNode root = new TreeNode(rootValue);

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.addLast(root);

        for (int i = 1; i < parts.length; i += 2) {
            TreeNode current = nodes.removeFirst();
            if (!parts[i].isEmpty()) {
                TreeNode left = createNodeFromString(parts[i]);
                current.left = left;
                nodes.addLast(left);
            }

            if (i + 1 < parts.length && !parts[i + 1].isEmpty()) {
                TreeNode right = createNodeFromString(parts[i + 1]);
                current.right = right;
                nodes.addLast(right);
            }
        }

        return root;
    }

    private TreeNode createNodeFromString(String val) {
        if (val.isEmpty()) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(val));
        }
    }
}
