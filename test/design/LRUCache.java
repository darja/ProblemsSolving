package design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;

    private Map<Integer, Node> content = new HashMap<>();
    private Node latest;
    private Node first;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = content.get(key);

        if (node == null) {
            return -1;
        }

        makeNodeLatest(node);
//        printHistory(String.format("get %s", key));

        return node.value;
    }

    public void put(int key, int value) {
        Node node = content.get(key);
        if (node == null) {
            if (content.size() == capacity) {
                content.remove(first.key);
                Node tmp = first.next;
                first.next = null;
                first = tmp;

                if (first != null) {
                    first.prev = null;
                }
            }

            node = new Node(key, value);
            content.put(key, node);
        } else {
            node.value = value;
        }
        makeNodeLatest(node);
//        printHistory(String.format("put %s -> %s", key, value));
    }

    private void makeNodeLatest(Node node) {
        if (node == latest) {
            return;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (node.prev == null && node.next != null) {
            first = node.next;
            first.prev = null;
        }

        node.prev = latest;
        node.next = null;

        if (latest != null) {
            latest.next = node;
        }

        latest = node;
        if (latest.prev == null) {
            first = latest;
        }
    }

    private void printHistory(String annotation) {
        Node head = first;

        if (head == null) {
            System.out.print("<EMPTY>");
            return;
        }

        if (head.prev != null) {
            System.err.println("First has prev");
        }

        System.out.println(annotation);
        while (head != null) {
            System.out.print(head);
            if (head == latest) {
                System.out.print(" <- latest");
            }

            if (head == first) {
                System.out.print(" <- first");
            }
            System.out.println();

            head = head.next;
        }
        System.out.println();
    }

    private class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("[%s] -> [%s]", key, value);
        }
    }
}
