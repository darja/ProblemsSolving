package list;

import util.ListNode;
import util.ListNodeTestCase;

/**
 * @see <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/">Problem Description</a>
 */
@SuppressWarnings("ALL")
// todo can be improved
public class IntersectionOfTwoLinkedLists extends ListNodeTestCase {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        if (lenA > lenB) {
            while (lenA > lenB) {
                headA = headA.next;
                lenA--;
            }
        } else if (lenB > lenA) {
            while (lenB > lenA) {
                headB = headB.next;
                lenB--;
            }
        }

        while (lenA > 0 && headA != headB) {
            headA = headA.next;
            headB = headB.next;
            lenA--;
        }
        return headA;
    }

    private int getLength(ListNode list) {
        int l = 0;
        ListNode curr = list;
        while (curr != null) {
            curr = curr.next;
            l++;
        }
        return l;
    }

    public ListNode getIntersectionNodeSlow(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB;

        while (currA != null) {
            currB = headB;
            while (currB != null) {
                if (currA == currB) {
                    return currA;
                }
                currB = currB.next;
            }
            currA = currA.next;
        }
        return null;
    }

    public void testNoIntersection() {
        assertListContent(getIntersectionNode(createListNode(new int[] {1, 2, 3}), createListNode(new int[] {5, 6, 7})), null);
        assertListContent(getIntersectionNode(createListNode(new int[0]), createListNode(new int[0])), null);
        assertListContent(getIntersectionNode(createListNode(new int[0]), createListNode(new int[] {1})), null);
        assertListContent(getIntersectionNode(createListNode(new int[] {2}), createListNode(new int[0])), null);
    }

    public void testIntersectionSmall() {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);

        ListNode headB = new ListNode(4);
        headB.next = headA;

        assertListContent(getIntersectionNode(headA, headB), new int[] {1, 2});
    }

    public void testIntersection1() {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);

        ListNode headB = new ListNode(4);
        headB.next = new ListNode(5);
        headB.next.next = new ListNode(6);
        headB.next.next.next = headA;

        assertListContent(getIntersectionNode(headA, headB), new int[] {1, 2, 3});
    }

    public void testIntersection2() {
        ListNode headA = new ListNode(1);
        headA.next = new ListNode(2);
        headA.next.next = new ListNode(3);

        ListNode headB = new ListNode(4);
        headB.next = new ListNode(5);
        headB.next.next = new ListNode(6);
        headB.next.next.next = headA.next;

        assertListContent(getIntersectionNode(headA, headB), new int[] {2, 3});
    }
}
