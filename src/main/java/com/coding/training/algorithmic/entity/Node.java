package com.coding.training.algorithmic.entity;

public class Node {
    public int value;
    public Node next;
    public Node prev;
    public Node wild;

    public Node() {
        this(0, null, null, null);
    }

    public Node(int value) {
        this(value, null, null, null);
    }

    public Node(int value, Node next) {
        this(value, next, null, null);
    }

    public Node(int value, Node next, Node prev) {
        this(value, next, prev, null);
    }

    public Node(int value, Node next, Node prev, Node wild) {
        this.value = value;
        this.next = next;
        this.prev = prev;
        this.wild = wild;
    }
}
