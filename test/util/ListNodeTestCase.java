package util;

import junit.framework.TestCase;

public class ListNodeTestCase extends TestCase {
    protected static ListNode createListNode(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }

        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        int index = 1;
        while (index < values.length) {
            current.next = new ListNode(values[index]);
            current = current.next;
            index++;
        }
        current.next = null;
        return head;
    }

    protected void assertListContent(ListNode head, int[] expected) {
        ListNode curr = head;
        if (curr == null) {
            System.out.println("LIST: null");
        } else {
            System.out.print("LIST: ");
            while (curr != null) {
                System.out.print(curr.val);
                System.out.print(" ");
                curr = curr.next;
            }
            System.out.println();
        }

        if (expected == null) {
            assertNull(head);
            return;
        }

        curr = head;
        for (int i = 0; i < expected.length; ++i) {
            assertNotNull("Null item " + i, curr);
            assertEquals("Invalid value for item " + i, expected[i], curr.val);
            curr = curr.next;
        }
    }
}
