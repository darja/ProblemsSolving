package list;

import junit.framework.TestCase;
import util.ListNode;

/**
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class FindCycles extends TestCase {
    public void test1() {
        ListNode first = new ListNode(3);

        ListNode node1 = new ListNode(1);
        first.next = node1;

        ListNode node2 = new ListNode(5);
        node1.next = node2;

        ListNode node3 = new ListNode(11);
        node2.next = node3;
        node3.next = node1;

        assertTrue(hasCycle(first));
    }

    public void test2() {
        ListNode first = new ListNode(3);

        ListNode node1 = new ListNode(1);
        first.next = node1;

        ListNode node2 = new ListNode(5);
        node1.next = node2;

        ListNode node3 = new ListNode(11);
        node2.next = node3;

        assertFalse(hasCycle(first));
    }

    public void test3() {
        ListNode node1 = new ListNode(1);

        ListNode node2 = new ListNode(5);
        node1.next = node2;
        node2.next = node1;

        assertTrue(hasCycle(node1));
    }

    public void test4() {
        ListNode node1 = new ListNode(1);
        node1.next = node1;

        assertTrue(hasCycle(node1));
    }

    public void test5() {
        ListNode node1 = new ListNode(1);

        assertFalse(hasCycle(node1));
    }

    public void test6() {
        ListNode node1 = new ListNode(1);

        ListNode node2 = new ListNode(2);
        node1.next = node2;

        assertFalse(hasCycle(node1));
    }

    public void test7() {
        ListNode node1 = new ListNode(2000);
        ListNode head = node1;
        for (int i = 0; i < 10000; ++i) {
            ListNode node2 = new ListNode(i);
            node1.next = node2;
            node1 = node2;
        }
        assertFalse(hasCycle(head));

        node1.next = head;

        assertTrue(hasCycle(head));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode curr = head;

        int n = 1;
        while (curr.next != null) {
            for (int i = 0; i < n; ++i) {
                curr.val = i;
                curr = curr.next;

                if (curr == null) {
                    return false;
                }
            }

            curr = head;
            for (int i = 0; i < n; ++i) {
                if (curr.val != i) {
                    return true;
                }
                curr = curr.next;
            }

            curr = head;
            n += 10;
        }
        return false;
    }
}
