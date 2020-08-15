package com.xibo.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 题目：删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 */
public class No_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> nodes = new LinkedList<>();
        for (ListNode curr = head; curr != null; curr = curr.next) {
            nodes.add(curr);
        }
        if (nodes.size() == 1) {
            head = null;
        } else if (nodes.size() == n) {
            head = head.next;
        } else {
            ListNode prev = nodes.get(nodes.size() - n - 1);
            prev.next = prev.next.next;
        }
        return head;
    }

    private class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
          val = x;
        }
    }

}
