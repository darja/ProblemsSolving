package list;

import util.ListNode;
import util.ListNodeTestCase;

/**
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class LinkedListCycle extends ListNodeTestCase {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            System.out.printf("Slow: %s, Fast: %s\n", slow.val, fast.val);
            if (slow == fast) {
                return true;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    public void testTrivial() {
        assertFalse(hasCycle(null));
        assertFalse(hasCycle(new ListNode(10)));
    }

    public void testCycle() {
        for (int i = 1; i < 10; ++i) {
            generateAndTestCycle(i);
        }
    }

    public void testNoCycle() {
        ListNode list = new ListNode(1);
        ListNode last = list;
        for (int i = 2; i <= 20; ++i) {
            assertFalse(hasCycle(list));
            last.next = new ListNode(i);
            last = last.next;
        }
    }

    private ListNode[] generateListNode(int count) {
        ListNode l = new ListNode(1);
        ListNode last = l;

        for (int i = 2; i <= count; ++i) {
            last.next = new ListNode(i);
            last = last.next;
        }

        return new ListNode[] {l, last};
    }

    private void generateAndTestCycle(int count) {
        ListNode[] l = generateListNode(count);

        ListNode first = l[0];
        ListNode last = l[1];
        ListNode cycled = first;

        for (int i = 0; i < count; ++i) {
            last.next = cycled;
            System.out.printf("\nCycle at %s\n", cycled.val);

            assertTrue(hasCycle(first));

            cycled = cycled.next;
        }
    }
}
