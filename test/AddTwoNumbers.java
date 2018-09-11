import junit.framework.TestCase;
import util.ListNode;

public class AddTwoNumbers extends TestCase{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        int val = value(l1) + value(l2);
        ListNode result = new ListNode(val % 10);
        ListNode current = result;

        ListNode digit1 = l1.next;
        ListNode digit2 = l2.next;

        while (digit1 != null || digit2 != null) {
            val = value(digit1) + value(digit2) + val / 10;
            ListNode next = new ListNode(val % 10);
            current.next = next;
            current = next;

            digit1 = next(digit1);
            digit2 = next(digit2);
        }

        if (val >= 10) {
            current.next = new ListNode(1);
        }

        return result;
    }

    private int value(ListNode src) {
        return src == null ? 0 : src.val;
    }

    private ListNode next(ListNode src) {
        return src == null ? null : src.next;
    }

    public void test() {
//        testSingle(new int[]{2, 4, 3}, new int[]{5, 6, 4}, new int[]{7, 0, 8});
//        testSingle(new int[]{2, 4, 5}, new int[]{5, 6, 5}, new int[]{7, 0, 1, 1});
//        testSingle(new int[]{2, 1}, new int[]{5, 6, 5}, new int[]{7, 7, 5});
//        testSingle(new int[]{2}, new int[]{5, 6, 5}, new int[]{7, 6, 5});
//        testSingle(new int[]{8}, new int[]{5, 6, 5}, new int[]{3, 7, 5});
//        testSingle(new int[]{8}, new int[]{5}, new int[]{3, 1});
        testSingle(new int[]{5}, new int[]{5}, new int[]{0, 1});
        testSingle(new int[]{2}, new int[]{2}, new int[]{4});
        testSingle(new int[]{0}, new int[]{0}, new int[]{0});
        testSingle(new int[]{1}, new int[0], new int[]{1});
        testSingle(new int[0], new int[0], new int[0]);
    }

    private void testSingle(int[] digits1, int[] digits2, int[] expected) {
        ListNode l1 = createList(digits1);
        ListNode l2 = createList(digits2);

        ListNode result = addTwoNumbers(l1, l2);

        for (int i = 0; i < expected.length; ++i) {
            assertEquals("Digit " + i, result.val, expected[i]);
            result = result.next;
        }
        assertNull(result);
    }

    @SuppressWarnings("Duplicates")
    private ListNode createList(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }

        ListNode root = new ListNode(digits[0]);
        ListNode current = root;

        for (int i = 1; i < digits.length; ++i) {
            ListNode next = new ListNode(digits[i]);
            current.next = next;
            current = next;
        }

        return root;
    }

}
