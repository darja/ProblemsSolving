import util.ListNode;
import util.ListNodeTestCase;

public class RemoveLinkedListElements extends ListNodeTestCase {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode curr = prev.next;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
                curr = curr.next;
            } else {
                prev = prev.next;
                curr = prev.next;
            }
        }

        return head;
    }

    public void test() {
        testCase(new int[0], 1, new int[0]);
        testCase(new int[0], 1, new int[0]);
        testCase(new int[] {1}, 1, new int[0]);
        testCase(new int[] {1, 1}, 1, new int[0]);
        testCase(new int[] {1, 1, 2}, 1, new int[] {2});
        testCase(new int[] {2, 1, 1}, 1, new int[] {2});
        testCase(new int[] {2, 1, 2}, 1, new int[] {2, 2});
        testCase(new int[] {1, 2, 1}, 1, new int[] {2});
        testCase(new int[] {2, 1, 1, 2}, 1, new int[] {2, 2});
        testCase(new int[] {2, 1, 3, 1, 2}, 1, new int[] {2, 3, 2});
    }

    private void testCase(int[] input, int val, int[] expectedOutput) {
        assertListContent(removeElements(createListNode(input), val), expectedOutput);
    }
}
