import util.ListNode;
import util.ListNodeTestCase;

import java.util.ArrayList;
import java.util.List;

public class SplitLinkedListInParts extends ListNodeTestCase {
    public ListNode[] splitListToParts(ListNode root, int k) {
        int n = 0;
        ListNode curr = root;
        while (curr != null) {
            curr = curr.next;
            n++;
        }

        ListNode[] result = new ListNode[k];
        if (n <= k) {
            curr = root;
            for (int i = 0; i < n; ++i) {
                result[i] = curr;
                curr = curr.next;
                result[i].next = null;
            }
            return result;
        }

        int maxLenCount = n % k;
        int minLenCount = k - maxLenCount;
        int minLen = n / k;
        int maxLen = minLen + 1;

        curr = root;
        for (int i = 0; i < maxLenCount; ++i) {
            result[i] = curr;
            curr = cutListNode(curr, maxLen);
        }

        for (int i = 0; i < minLenCount; ++i) {
            result[maxLenCount + i] = curr;
            curr = cutListNode(curr, minLen);
        }

        return result;
    }

    private ListNode cutListNode(ListNode head, int len) {
        ListNode curr = head;
        int i = 1;
        while (i < len) {
            curr = curr.next;
            i++;
        }
        ListNode rest = curr.next;
        curr.next = null;
        return rest;
    }

    public void testSmall() {
        testCase(new int[] {1, 2, 3}, 3, new ArrayList<int[]>() {{
            add(new int[] {1});
            add(new int[] {2});
            add(new int[] {3});
        }});

        testCase(new int[] {1, 2, 3}, 4, new ArrayList<int[]>() {{
            add(new int[] {1});
            add(new int[] {2});
            add(new int[] {3});
            add(new int[0]);
        }});

        testCase(new int[] {1, 2, 3}, 5, new ArrayList<int[]>() {{
            add(new int[] {1});
            add(new int[] {2});
            add(new int[] {3});
            add(new int[0]);
            add(new int[0]);
        }});
    }

    public void testEmpty() {
        testCase(new int[0], 1, new ArrayList<int[]>() {{ add(new int[0]); }});
        testCase(new int[0], 3, new ArrayList<int[]>() {{
            add(new int[0]);
            add(new int[0]);
            add(new int[0]);
        }});
    }

    public void testNormal() {
        testCase(new int[] {1, 2, 3, 4, 5, 6, 7}, 3, new ArrayList<int[]>() {{
            add(new int[] {1, 2, 3});
            add(new int[] {4, 5});
            add(new int[] {6, 7});
        }});

        testCase(new int[] {1, 2, 3, 4, 5, 6}, 3, new ArrayList<int[]>() {{
            add(new int[] {1, 2});
            add(new int[] {3, 4});
            add(new int[] {5, 6});
        }});

        testCase(new int[] {1, 2, 3, 4, 5}, 3, new ArrayList<int[]>() {{
            add(new int[] {1, 2});
            add(new int[] {3, 4});
            add(new int[] {5});
        }});

        testCase(new int[] {1, 2, 3, 4}, 3, new ArrayList<int[]>() {{
            add(new int[] {1, 2});
            add(new int[] {3});
            add(new int[] {4});
        }});
    }

    private void testCase(int[] input, int k, List<int[]> expectedResult) {
        ListNode[] result = splitListToParts(createListNode(input), k);
        assertEquals(result.length, expectedResult.size());
        assertEquals(result.length, k);
        for (int i = 0; i < k; ++i) {
            assertListContent(result[i], expectedResult.get(i));
        }
    }
}
