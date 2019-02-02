package list;

import util.ListNode;
import util.ListNodeTestCase;

/**
 * @see <a href="https://leetcode.com/problems/partition-list/">Problem Description</a>
 */
@SuppressWarnings("WeakerAccess")
public class PartitionList extends ListNodeTestCase {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rightHead = null;
        ListNode rightTail = null;
        ListNode leftHead = null;
        ListNode leftTail = null;
        ListNode current = head;
        ListNode tmp;

        while (current != null) {
            if (current.val < x) {
                if (leftTail != null) {
                    leftTail.next = current;
                    leftTail = current;
                } else {
                    if (leftHead == null) {
                        leftHead = current;
                    } else {
                        leftTail = current;
                        leftHead.next = leftTail;
                    }
                }
            } else {
                if (rightTail == null) {
                    if (rightHead == null) {
                        rightHead = current;
                    } else {
                        rightTail = current;
                        rightHead.next = rightTail;
                    }
                } else {
                    rightTail.next = current;
                    rightTail = current;
                }
            }

            tmp = current;
            current = current.next;
            tmp.next = null;
        }

        if (leftTail == null) {
            leftTail = leftHead;
        }

        if (leftTail != null) {
            leftTail.next = rightHead;
            return leftHead;
        } else {
            return rightHead;
        }
    }

    private void testCase(ListNode list, int x, int[] expected) {
        assertListContent(partition(list, x), expected);
    }

    public void testTrivial() {
        testCase(null, 1, new int[0]);
        testCase(new ListNode(1), 1, new int[] {1});
        testCase(new ListNode(1), 2, new int[] {1});
    }

    public void testAllLessThan() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-1);
        l.next.next.next.next = new ListNode(5);

        testCase(l, 20, new int[] {1, 8, 3, -1, 5});
    }

    public void testAllGreaterThan() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-1);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(3);

        testCase(l, -3, new int[] {1, 8, 3, -1, 5, 3});
    }

    public void testSingleElementOnLeft() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(1);

        testCase(l, 1, new int[] { -2, 1, 8, 3, 5, 1});
    }

    public void testTwoElementsOnLeft() {
        ListNode l = new ListNode(2);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(0);

        testCase(l, 1, new int[] { -2, 0, 2, 8, 3, 5});
    }

    public void testSingleElementOnRight() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(1);

        testCase(l, 7, new int[] { 1, 3, -2, 5, 1, 8});
    }

    public void testTwoElementsOnRight() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(1);

        testCase(l, 4, new int[] { 1, 3, -2, 1, 8, 5});
    }
}
