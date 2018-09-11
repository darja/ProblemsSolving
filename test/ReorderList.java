import util.ListNode;
import util.ListNodeTestCase;

public class ReorderList extends ListNodeTestCase {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode last = head.next;
        ListNode beforeLast = head;
        while (last.next != null) {
            beforeLast = last;
            last = last.next;
        }
        ListNode next = head.next;
        head.next = last;
        last.next = next;
        beforeLast.next = null;
        reorderList(next);
    }

    public void test() {
        testCase(null, null);
        testCase(new int[] {1}, new int[] {1});
        testCase(new int[] {1, 2}, new int[] {1, 2});
        testCase(new int[] {1, 2, 3, 4}, new int[] {1, 4, 2, 3});
        testCase(new int[] {1, 2, 3, 4, 5}, new int[] {1, 5, 2, 4, 3});
        testCase(new int[] {1, 2, 3, 4, 5, 6}, new int[] {1, 6, 2, 5, 3, 4});
    }

    private void testCase(int[] input, int[] expected) {
        ListNode list = createListNode(input);
        reorderList(list);
        assertListContent(list, expected);
    }
}
