package com.coding.training.algorithmic.history.linklist;

/**
 * 求两个已排序链表中相同的数据
 */
public class Sample012 {

    public static void main(String[] args) {
        Node head1 = createLinkList(new int[]{1, 3, 5, 7, 9, 10, 11, 12});
        Node head2 = createLinkList(new int[]{2, 4, 5, 8, 10, 12});

        duplicateData(head1, head2);
    }

    public static void duplicateData(Node head1, Node head2) {
        Node curr1 = head1;
        Node curr2 = head2;

        while (curr1 != null || curr2 != null) {
            if (curr1.getValue() < curr2.getValue()) {
                curr1 = curr1.getNext();
            } else if (curr1.getValue() > curr2.getValue()) {
                curr2 = curr2.getNext();
            } else {
                System.out.println(curr1.getValue() + " ");
                curr1 = curr1.getNext();
                curr2 = curr2.getNext();
            }
        }
    }

    public static Node createLinkList(int[] arr) {
        if (arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node curr = head;

        for (int i = 1; i < arr.length; i++) {
            curr.setNext(new Node(arr[i]));
            curr = curr.getNext();
        }

        return head;
    }
}
