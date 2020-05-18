package com.coding.training.algorithmic.history.linklist;


public class Node {
    private Integer value;
    private Node next;
    private Node wild;

    public Node(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    public Node getNext() {
        return this.next;
    }

    public void setNext(Node node) {
        this.next = node;
    }

    public Node getWild() {
        return wild;
    }

    public void setWild(Node wild) {
        this.wild = wild;
    }
}

