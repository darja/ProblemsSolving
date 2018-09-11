import junit.framework.TestCase;
import util.ListNode;

public class SwapPairs extends TestCase {
    public void testEmpty() {
        ListNode n = new ListNode(1);
        assertEquals(n, swapPairs(n));
    }

    public void test() {
        testSingle(new int[] {1,2,3,4}, new int[] {2,1,4,3});
        testSingle(new int[] {1,2,3}, new int[] {2,1,3});
        testSingle(new int[] {1,2,3,4,5,6,7,8}, new int[] {2,1,4,3,6,5,8,7});
        testSingle(new int[] {1,2}, new int[] {2,1});
    }

    private void testSingle(int[] input, int[] output) {
        ListNode root = new ListNode(input[0]);
        ListNode n1 = root;
        for (int i = 1; i < input.length; ++i) {
            ListNode n2 = new ListNode(input[i]);
            n1.next = n2;
            n1 = n2;
        }

        ListNode result = swapPairs(root);

        for (int i = 0; i < output.length; ++i) {
            assertEquals(output[i], result.val);
            result = result.next;
        }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode head0 = new ListNode(0);
        head0.next = head;

        ListNode ptr = head0;
        while (ptr != null && ptr.next != null) {
            ptr = swapNearestPair(ptr);
        }
        return head0.next;
    }

    public ListNode swapNearestPair(ListNode n) {
        ListNode n1 = n.next;
        if (n1.next == null) {
            return null;
        }
        ListNode n2 = n1.next;

        n.next = n2;
        n1.next = n2.next;
        n2.next = n1;

        return n1;
    }
}
