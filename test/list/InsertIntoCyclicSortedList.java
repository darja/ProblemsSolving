package list;

import junit.framework.TestCase;

public class InsertIntoCyclicSortedList extends TestCase {
    public Node insert(Node head, int insertVal) {
        Node node = new Node();
        node.val = insertVal;

        if (head == null) {
            node.next = node;
            return node;
        }

        Node current = head;
        do {
            int curr = current.val;
            int next = current.next.val;

            if (curr <= insertVal && next >= insertVal ||
                curr > next && (curr <= insertVal || next >= insertVal)) {
                break;
            }

            current = current.next;
        } while (head != current);

        node.next = current.next;
        current.next = node;

        return head;
    }

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }

        @Override
        public String toString() {
            return String.format("Node [%s]", val);
        }
    }

    private void testOne(Node head, int insertedValue, int[] expectedSortedArray) {
        Node resultHead = insert(head, insertedValue);
        Node maxNode = resultHead;

        Node curr = resultHead.next;
        while (curr != resultHead) {
            if (curr.val >= maxNode.val) {
                maxNode = curr;
            } else break;
            curr = curr.next;
        }

        curr = maxNode.next;
        for (int item : expectedSortedArray) {
            assertEquals(item, curr.val);
            curr = curr.next;
        }

        if (head != null) {
            assertEquals(head, resultHead);
        } else {
            assertEquals(resultHead, resultHead.next);
        }
    }

    private Node listFromArray(int... array) {
        Node head = new Node();
        head.val = array[0];

        Node curr = head;
        for (int i = 1; i < array.length; ++i) {
            Node newNode = new Node();
            newNode.val = array[i];
            curr.next = newNode;
            curr = newNode;
        }
        curr.next = head;

        return head;
    }

    public void testNull() {
        testOne(null, 1, new int[] {1});
        testOne(null, 10, new int[] {10});
    }

    public void testOne() {
        testOne(listFromArray(1), 1, new int[] {1, 1});
        testOne(listFromArray(1), 2, new int[] {1, 2});
        testOne(listFromArray(1), 0, new int[] {0, 1});
    }

    public void testUniform() {
        testOne(listFromArray(1, 1, 1), 1, new int[] {1, 1, 1, 1});
        testOne(listFromArray(1, 1, 1), 2, new int[] {1, 1, 1, 2});
        testOne(listFromArray(1, 1, 1), 0, new int[] {0, 1, 1, 1});
    }

    public void testOther() {
        testOne(listFromArray(3, 5, 1), 2, new int[] {1, 2, 3, 5});
        testOne(listFromArray(5, 1, 3), 0, new int[] {0, 1, 3, 5});
        testOne(listFromArray(3, 5, 10, 1, 2), 15, new int[] {1, 2, 3, 5, 10, 15});

        testOne(listFromArray(1, 2, 3), 10, new int[] {1, 2, 3, 10});
        testOne(listFromArray(2, 3, 1), 10, new int[] {1, 2, 3, 10});
        testOne(listFromArray(3, 1, 2), 10, new int[] {1, 2, 3, 10});
    }
}
