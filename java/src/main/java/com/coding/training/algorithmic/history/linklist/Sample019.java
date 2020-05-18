package com.coding.training.algorithmic.history.linklist;

/**
 * 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 *
 * 首先我们将第一个奇数节点（也就是头节点）的地址存起来，然后将所有奇数节点按照原有次序链接起来。
 * 其次把第一个偶数节点（第二个节点）的地址存起来，然后将所有的偶数节点按照原有次序链接起来。
 * 最后将偶数的第一个节点（此时应是被保存起来的偶数节点），链接在奇数的最后一个节点后面。然后返回被保存的头节点。
 */
public class Sample019 {

    public Node oddEvenList(Node head) {
        if (head == null) return head;
        Node odd = head, even = head.getNext(), evenhead = even;
        while (odd.getNext() != null && even.getNext() != null) {
            odd.setNext(odd.getNext().getNext());
            even.setNext(even.getNext().getNext());
            odd = odd.getNext();
            even = even.getNext();
        }
        odd.setNext(evenhead);
        return head;
    }

}
