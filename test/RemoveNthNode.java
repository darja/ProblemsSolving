import junit.framework.TestCase;
import util.ListNode;

public class RemoveNthNode extends TestCase {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null && n == 1) {
            return null;
        }

        ListNode current = head;

        for (int i = 0; i < n; ++i) {
            current = current.next;
        }

        if (current == null) {
            return head.next;
        }

        ListNode beforeRemoved = head;

        while (current.next != null) {
            current = current.next;
            beforeRemoved = beforeRemoved.next;
        }

        beforeRemoved.next = beforeRemoved.next.next;

        return head;
    }

    public void test() {
        testSingle(new int[] {1, 2, 3, 4, 5}, 2, new int[] {1, 2, 3, 5});
        testSingle(new int[] {1, 2, 3, 4, 5}, 1, new int[] {1, 2, 3, 4});
        testSingle(new int[] {1, 2, 3, 4, 5}, 5, new int[] {2, 3, 4, 5});
        testSingle(new int[] {1, 2}, 1, new int[] {1});
        testSingle(new int[] {1, 2}, 2, new int[] {2});
        testSingle(new int[] {1, 2, 3}, 2, new int[] {1, 3});
        testSingle(new int[] {1}, 1, new int[] {});
    }

    private void testSingle(int[] items, int n, int[] expected) {
        ListNode list = createList(items);

        ListNode result = removeNthFromEnd(list, n);

        for (int i = 0; i < expected.length; ++i) {
            assertEquals("Digit " + i, expected[i], result.val);
            result = result.next;
        }
        assertNull(result);
    }

    @SuppressWarnings("Duplicates")
    private ListNode createList(int[] items) {
        if (items == null || items.length == 0) {
            return null;
        }

        ListNode head = new ListNode(items[0]);
        ListNode current = head;

        for (int i = 1; i < items.length; ++i) {
            ListNode next = new ListNode(items[i]);
            current.next = next;
            current = next;
        }

        return head;
    }
}
