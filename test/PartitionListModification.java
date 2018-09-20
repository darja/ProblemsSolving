import util.ListNode;
import util.ListNodeTestCase;

public class PartitionListModification extends ListNodeTestCase {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = head;
        ListNode pivot = head;

        if (left.val == x) {
            left = null;
        } else {
            while (right.val != x) {
                right = right.next;
            }
            pivot = right;

            ListNode prev = null;

            while (left.val != x) {
                if (left.val < x) {
                    prev = left;
                    left = left.next;
                } else {
                    ListNode tmp = left;

                    if (prev == null) {
                        head = head.next;
                    } else {
                        prev.next = left.next;
                    }
                    left = left.next;

                    tmp.next = right.next;
                    right.next = tmp;
                    right = right.next;
                }
            }
            left = prev;
        }

        ListNode prev = right;
        right = right.next;

        while (right != null) {
            if (right.val < x) {
                if (left == null) {
                    left = right;
                    right = right.next;
                    prev.next = right == null ? null : right.next;
                    left.next = pivot;
                    head = left;
                } else {
                    ListNode tmp = right;
                    prev.next = right.next;
                    right = right.next;

                    left.next = tmp;
                    tmp.next = pivot;
                    left = tmp;
                }
            } else {
                right = right.next;
                prev = prev.next;
            }
        }

        return head;
    }

    private void testCase(ListNode list, int x, int[] expected) {
        assertListContent(partition(list, x), expected);
    }

    public void testTrivial() {
        testCase(null, 1, new int[0]);
        testCase(new ListNode(1), 1, new int[] {1});
        testCase(new ListNode(1), 2, new int[] {1});
    }

    public void testNoDup() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-1);
        l.next.next.next.next = new ListNode(5);

        testCase(l, 3, new int[] {1, -1, 3, 8, 5});
    }

    public void testDupPivot() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-1);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(3);

        testCase(l, 3, new int[] {1, -1, 3, 8, 5, 3});
    }

    public void testDupOther() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-2);
        l.next.next.next.next = new ListNode(5);
        l.next.next.next.next.next = new ListNode(1);

        testCase(l, 3, new int[] {1, -2, 1, 3, 8, 5});
    }

    public void testPivotStartToMiddle() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(-2);

        testCase(l, 1, new int[] {-2, 1, 8, 3});
    }

    public void testPivotStartToStart() {
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(5);

        testCase(l, 1, new int[] {1, 8, 3, 5});
    }

    public void testPivotStartToEnd() {
        ListNode l = new ListNode(10);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(5);

        testCase(l, 10, new int[] {8, 3, 5, 10});
    }

    public void testPivotMiddleToStart() {
        ListNode l = new ListNode(10);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(5);

        testCase(l, 3, new int[] {3, 10, 8, 5});
    }

    public void testPivotMiddleToEnd() {
        ListNode l = new ListNode(2);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(5);

        testCase(l, 8, new int[] {2, 3, 5, 8});
    }

    public void testPivotEndToStart() {
        ListNode l = new ListNode(2);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(1);

        testCase(l, 1, new int[] {1, 2, 8, 3});
    }

    public void testPivotEndToMiddle() {
        ListNode l = new ListNode(2);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(5);

        testCase(l, 5, new int[] {2, 3, 5, 8});
    }

    public void testPivotEndToEnd() {
        ListNode l = new ListNode(2);
        l.next = new ListNode(8);
        l.next.next = new ListNode(3);
        l.next.next.next = new ListNode(10);

        testCase(l, 10, new int[] {2, 8, 3, 10});
    }
}
