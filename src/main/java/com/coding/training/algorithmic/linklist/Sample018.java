package com.coding.training.algorithmic.linklist;

/**
 *
 * 对链表进行插入排序
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 */
public class Sample018 {

    public Node insertionSortList(Node head) {
        Node root = new Node(Integer.MIN_VALUE);
        while (head != null) {
            Node temp = root;
            while (temp.getNext() != null && head.getValue() >= temp.getNext().getValue())
                temp = temp.getNext();
            if(temp.getNext()==null){
                temp.setNext(head);
                head=head.getNext();
                temp.getNext().setNext(null);
            }
            else{
                Node temp2 = temp.getNext();
                temp.setNext(head);
                head=head.getNext();
                temp.getNext().setNext(temp2);
            }
        }
        return root.getNext();
    }
}
