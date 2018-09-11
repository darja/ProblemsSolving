import junit.framework.TestCase;
import util.ListNode;

public class MergeTwoLists extends TestCase {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public void test() {
        testSingle(new int[] {1, 2, 3}, new int[] {1, 2, 3}, new int[] {1, 1, 2, 2, 3, 3});
        testSingle(new int[] {1, 2, 3}, new int[] {1, 1, 2}, new int[] {1, 1, 1, 2, 2, 3});
        testSingle(new int[] {1, 2, 3}, new int[] {1, 1, 1}, new int[] {1, 1, 1, 1, 2, 3});
        testSingle(new int[] {1}, new int[] {1, 2, 3}, new int[] {1, 1, 2, 3});
    }

    private void testSingle(int[] arr1, int[] arr2, int[] expected) {
        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);

        ListNode result = mergeTwoLists(list1, list2);

        for (int i = 0; i < expected.length; ++i) {
            assertEquals("Digit " + i, expected[i], result.val);
            result = result.next;
        }
        assertNull(result);
    }

    @SuppressWarnings("Duplicates")
    private ListNode createList(int[] items) {
        if (items == null || items.length == 0) {
            return null;
        }

        ListNode head = new ListNode(items[0]);
        ListNode current = head;

        for (int i = 1; i < items.length; ++i) {
            ListNode next = new ListNode(items[i]);
            current.next = next;
            current = next;
        }

        return head;
    }

}
