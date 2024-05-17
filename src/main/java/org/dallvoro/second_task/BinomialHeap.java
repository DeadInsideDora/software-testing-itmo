package org.dallvoro.second_task;

import java.util.NoSuchElementException;

public class BinomialHeap {
    private Node head;

    private static class Node {
        int key;
        int order;
        Node child;
        Node sibling;

        public Node(int key) {
            this.key = key;
            order = 0;
            child = null;
            sibling = null;
        }
    }

    public BinomialHeap() {
        head = null;
    }

    public BinomialHeap(int[] a) {
        this();
        for (int key : a) {
            insert(key);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(int key) {
        BinomialHeap heap = new BinomialHeap();
        heap.head = new Node(key);
        mergeHeap(heap);
    }

    public int first() {
        emptyCheck();
        Node minimum = head;
        Node current = head;
        while (current.sibling != null) {
            if (current.sibling.key < minimum.key) {
                minimum = current.sibling;
            }
            current = current.sibling;
        }
        return minimum.key;
    }

    public int pop() {
        emptyCheck();
        Node minimum = erase();
        if (minimum.child == null) {
            return minimum.key;
        }
        Node current = minimum.child;
        minimum.child = null;
        Node previous = null;
        Node next = current.sibling;
        while (next != null) {
            current.sibling = previous;
            previous = current;
            current = next;
            next = next.sibling;
        }
        current.sibling = previous;
        BinomialHeap heap = new BinomialHeap();
        heap.head = current;
        mergeHeap(heap);
        return minimum.key;
    }

    private void mergeHeap(BinomialHeap other) {
        head = merge(head, other.head).sibling;
        Node current = this.head;
        Node previous = null;
        Node next = current.sibling;
        for (; next != null; next = current.sibling) {
            if (current.order < next.order ||
                    next.sibling != null && next.sibling.order == current.order) {
                previous = current;
                current = next;
                continue;
            }
            if (next.key > current.key) {
                current.sibling = next.sibling;
                link(next, current);
                continue;
            }
            if (previous == null) {
                head = next;
            } else {
                previous.sibling = next;
            }
            link(current, next);
            current = next;
        }
    }

    private void link(Node left, Node right) {
        left.sibling = right.child;
        right.child = left;
        right.order++;
    }

    private Node erase() {
        Node minimum = head;
        Node previous = null;
        Node current = head;
        while (current.sibling != null) {
            if (minimum.key > current.sibling.key) {
                previous = current;
                minimum = current.sibling;
            }
            current = current.sibling;
        }
        if (previous != null) {
            previous.sibling = minimum.sibling;
        }
        if (minimum == head) {
            head = minimum.sibling;
        }
        return minimum;
    }

    private Node merge(Node left, Node right) {
        return merge(new Node(0), left, right);
    }

    private Node merge(Node root, Node left, Node right) {
        if (left == null) {
            if (right == null) {
                return root;
            }
            root.sibling = merge(right, null, right.sibling);
            return root;
        }
        if (right == null) {
            root.sibling = merge(left, left.sibling, null);
            return root;
        }
        if (left.order < right.order) {
            root.sibling = merge(left, left.sibling, right);
            return root;
        }
        root.sibling = merge(right, left, right.sibling);
        return root;
    }

    private void emptyCheck() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
    }
}
