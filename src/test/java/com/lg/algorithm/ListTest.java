package com.lg.algorithm;

import com.lg.algorithm.model.ListNode;
import org.junit.Test;

/**
 * <p>
 * description:
 * </p>
 *
 * @author leiguang
 * @version 0.1.0
 * @date 2019-04-03 09:13
 */
public class ListTest {

    /**
     * 找出两个链表的交点
     * 算法描述：
     * 存在两个链表l1,l2，将两个链表同时遍历，如果某个链表遍历完成，则再去遍历另外一个链表，并对比对应位置上的元素是否相等，
     * 如果相等，则就是链表相交点
     */
    @Test
    public void getIntersectionNode(){

        //交点
        ListNode commonHead = new ListNode(0);
        addNode(2, commonHead);

        //链表1
        ListNode head1 = new ListNode(1);
        ListNode tail1 = addNode(2, head1);
        tail1.next = commonHead;
        print(head1, "head1------");

        //链表2
        ListNode head2 = new ListNode(2);
        ListNode tail2 = addNode(5, head2);
        tail2.next = commonHead;
        print(head2, "head2------");

        //计算相交节点
        ListNode l1 = head1, l2 = head2;
        while (l1 != l2){
            l1 = (l1 == null) ? head2 : l1.next;
            l2 = (l2 == null) ? head1 : l2.next;
        }
        System.out.println("重合的节点：" + l1.value);
    }


    /**
     * 链表反转
     *  算法分析：
     *  头插入法
     */
    @Test
    public void reverseList(){
        ListNode list = new ListNode(2);
        addNode(5, list);
        print(list, "reverse before: ");

        //反转后的链表头
        ListNode reverse = new ListNode(0);

        /*reverse.next = list;
        ListNode temp = list.next;
        list.next = null;
        while (temp != null) {
            ListNode next = reverse.next;
            ListNode listNext = temp.next;

            reverse.next = temp;
            temp.next = next;
            temp = listNext;
        }*/
        while (list != null) {
            ListNode next = list.next;
            list.next = reverse.next;
            reverse.next = list;
            list = next;
        }
        print(reverse.next, "reverse after: ");
    }

    /**
     * 从有序链表中删除重复节点
     * Given 1->1->2, return 1->2.
     * Given 1->1->2->3->3, return 1->2->3.
     *
     */
    @Test
    public void deleteDuplicates(){
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        print(head, "delete before: ");
        ListNode preValue = head;
        while (preValue != null && preValue.next != null) {
            if (preValue.value == preValue.next.value){
                preValue.next = preValue.next.next;
            }else
                preValue = preValue.next;
        }
        print(head, "delete after: ");
    }

    public ListNode addNode(int n, ListNode head){
        ListNode pre = head;
        for (int i = 1; i <= n; i++) {
            ListNode tail = new ListNode(i + n);
            pre.next = tail;
            pre = tail;
        }
        return pre;
    }

    public void print(ListNode head, String name){
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        ListNode node = head;
        while (node != null) {
            sb.append(node.value + ",");
            node = node.next;
        }
        System.out.println(sb.toString());
    }
}
