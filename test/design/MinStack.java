package design;

import java.util.NoSuchElementException;

public class MinStack {
    private static class Node {
        final int value;
        private final int minValue;
        private final Node prev;

        private Node(int value, Node prev) {
            this.value = value;
            this.prev = prev;

            if (prev != null) {
                minValue = Math.min(value, prev.minValue);
            } else {
                minValue = value;
            }
        }
    }

    private Node root;

    /** initialize your data structure here. */
    public MinStack() {
        root = null;
    }

    public void push(int x) {
        root = new Node(x, root);
    }

    public void pop() {
        root = root.prev;
    }

    public int top() {
        if (root != null) {
            return root.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int getMin() {
        if (root != null) {
            return root.minValue;
        } else {
            throw new NoSuchElementException();
        }
    }
}
