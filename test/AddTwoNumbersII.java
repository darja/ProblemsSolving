import util.ListNode;
import util.ListNodeTestCase;

public class AddTwoNumbersII extends ListNodeTestCase {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        int len1 = getLength(l1);
        int len2 = getLength(l2);

        if (len1 < len2) {
            ListNode tmp = l1;
            l1 = l2;
            l2 = tmp;

            int len = len1;
            len1 = len2;
            len2 = len;
        }

        while (len1 > len2) {
            ListNode zero = new ListNode(0);
            zero.next = l2;
            l2 = zero;
            len2++;
        }

        int rem = add(l1, l2);
        if (rem > 0) {
            ListNode lead = new ListNode(rem);
            lead.next = l1;
            return lead;
        } else {
            return l1;
        }
    }

    private int add(ListNode l1, ListNode l2) {
        if (l1.next == null) {
            int sum = l1.val + l2.val;
            int rem = sum / 10;
            l1.val = sum % 10;
            return rem;
        } else {
            int prevRem = add(l1.next, l2.next);
            int sum = l1.val + l2.val + prevRem;
            int rem = sum / 10;
            l1.val = sum % 10;
            return rem;
        }
    }

    private int getLength(ListNode list) {
        ListNode curr = list;
        int len = 0;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        return len;
    }

    private void test(int[] arr1, int[] arr2, int[] expectedResult) {
        ListNode l1 = createListNode(arr1);
        ListNode l2 = createListNode(arr2);

        ListNode result = addTwoNumbers(l1, l2);
        assertListContent(result, expectedResult);
    }

    public void test() {
        test(new int[] {1}, new int[] {2}, new int[] {3});
        test(new int[] {1, 1}, new int[] {2}, new int[] {1, 3});
        test(new int[] {9, 9}, new int[] {2}, new int[] {1, 0, 1});
        test(new int[] {9}, new int[] {2}, new int[] {1, 1});
        test(new int[] {9}, new int[] {1}, new int[] {1, 0});

        test(new int[0], new int[] {2}, new int[] {2});
        test(new int[] {2}, new int[0], new int[] {2});
    }
}
