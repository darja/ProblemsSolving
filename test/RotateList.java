import util.ListNode;
import util.ListNodeTestCase;

public class RotateList extends ListNodeTestCase {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode tail = head;

        int count = 1;
        while (tail.next != null) {
            tail = tail.next;
            count++;
        }

        if (k % count == 0) {
            return head;
        }

        int offset = count - k % count - 1;

        ListNode pivot = head;
        for (int i = 0; i < offset; ++i) {
            pivot = pivot.next;
        }
        ListNode newHead = pivot.next;
        pivot.next = null;
        tail.next = head;

        return newHead;
    }

    public void testTrivial() {
        testCase(null, 0, new int[0]);
        testCase(null, 1, new int[0]);
        testCase(new ListNode(4), 0, new int[] {4});
        testCase(new ListNode(4), 1, new int[] {4});
        testCase(new ListNode(4), 143, new int[] {4});
    }

    public void testRotation() {
        testCase(createList(), 1, new int[] {5, 1, 2, 3, 4});
        testCase(createList(), 2, new int[] {4, 5, 1, 2, 3});
        testCase(createList(), 3, new int[] {3, 4, 5, 1, 2});
        testCase(createList(), 5, new int[] {1, 2, 3, 4, 5});
        testCase(createList(), 15, new int[] {1, 2, 3, 4, 5});
        testCase(createList(), 8, new int[] {3, 4, 5, 1, 2});
    }

    private ListNode createList() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);

        return list;
    }

    private void testCase(ListNode input, int k, int[] expected) {
        assertListContent(rotateRight(input, k), expected);
    }
}
