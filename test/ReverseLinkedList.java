import util.ListNode;
import util.ListNodeTestCase;

public class ReverseLinkedList extends ListNodeTestCase {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;

            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public void test() {
        testSingle(null, null);
        testSingle(new int[] {1}, new int[] {1});
        testSingle(new int[] {1, 2, 3}, new int[] {3, 2, 1});
        testSingle(new int[] {1, 2, 3, 4}, new int[] {4, 3, 2, 1});
        testSingle(new int[] {1, 2, 2, 4}, new int[] {4, 2, 2, 1});
    }

    private void testSingle(int[] input, int[] expectedOutput) {
        ListNode inputList = createListNode(input);
        ListNode output = reverseList(inputList);
        assertListContent(output, expectedOutput);
    }
}
