package util;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return "ListNode[" + val + "]";
    }

    public static int getLength(ListNode list) {
        ListNode curr = list;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        return len;
    }
}
