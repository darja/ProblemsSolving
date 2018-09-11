import util.ListNode;
import util.TreeNode;
import util.TreeTestCase;

public class SortedListToBST extends TreeTestCase {
    public TreeNode sortedListToBST(ListNode head) {
        int length = ListNode.getLength(head);
        return listToTree(head, length);
    }

    public TreeNode listToTree(ListNode list, int length) {
        if (length == 1) return new TreeNode(list.val);
        if (length < 1) return null;

        ListNode prev = list;
        ListNode curr = list;
        for (int i = 0; i < length / 2; ++i) {
            if (curr != prev) {
                prev = prev.next;
            }
            curr = curr.next;
        }

        TreeNode root = new TreeNode(curr.val);
        ListNode left = list;
        ListNode right = curr.next;
        prev.next = null;

        root.left = listToTree(left, length / 2);
        root.right = listToTree(right, length - length / 2 - 1);

        return root;
    }

    public void testNoElements() {
        assertTreeEquals(null, null);
    }

    public void testOneElement() {
        assertTreeEquals(new TreeNode(1), sortedListToBST(new ListNode(1)));
    }

    public void test2Elements() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);

        TreeNode tree = new TreeNode(2);
        tree.left = new TreeNode(1);

        assertTreeEquals(tree, sortedListToBST(list));
    }

    public void test3Elements() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);

        TreeNode tree = new TreeNode(2);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(3);

        assertTreeEquals(tree, sortedListToBST(list));
    }

    public void test4Elements() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);

        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(1);
        tree.right = new TreeNode(4);

        assertTreeEquals(tree, sortedListToBST(list));
    }

    public void test5Elements() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);

        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(1);
        tree.right = new TreeNode(5);
        tree.right.left = new TreeNode(4);

        TreeNode result = sortedListToBST(list);

        assertTreeEquals(tree, result);
    }

}
