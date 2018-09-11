import util.ListNode;
import util.ListNodeTestCase;

public class ReverseLinkedListII extends ListNodeTestCase {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }

        ListNode preReverse = null;

        if (m > 1) {
            preReverse = head;
            for (int i = 1; i < m - 1; ++i) {
                preReverse = preReverse.next;
            }
        }

        ListNode reversedHead = preReverse == null ? head : preReverse.next;
        ListNode prev = reversedHead;
        ListNode curr = prev.next;
        for (int i = 0; i < n - m; ++i) {
            ListNode tmp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = tmp;
            reversedHead.next = tmp;
        }

        if (preReverse == null) {
            return prev;
        } else {
            preReverse.next = prev;
            return head;
        }
    }

    public void test() {
        testCase(new int[] {1}, 1, 1, new int[] {1});
        testCase(new int[] {1, 2}, 1, 1, new int[] {1, 2});
        testCase(new int[] {1, 2, 3}, 1, 1, new int[] {1, 2, 3});
        testCase(new int[] {1, 2, 3, 4, 5}, 1, 1, new int[] {1, 2, 3, 4, 5});
        testCase(new int[] {1, 2, 3, 4, 5}, 1, 2, new int[] {2, 1, 3, 4, 5});
        testCase(new int[] {1, 2, 3, 4, 5}, 3, 5, new int[] {1, 2, 5, 4, 3});
        testCase(new int[] {1, 2, 3, 4, 5}, 2, 4, new int[] {1, 4, 3, 2, 5});
        testCase(new int[] {1, 2, 3, 4, 5}, 1, 5, new int[] {5, 4, 3, 2, 1});
    }

    private void testCase(int[] input, int m, int n, int[] expectedOutput) {
        assertListContent(reverseBetween(createListNode(input), m, n), expectedOutput);
    }
}
