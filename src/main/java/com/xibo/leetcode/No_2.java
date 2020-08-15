package com.xibo.leetcode;

/**
 * 题目：两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class No_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        int digit = 0;
        ListNode curr = res;
        while (l1 != null || l2 != null) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += digit;
            digit = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }
        if (digit > 0) {
            curr.next = new ListNode(digit);
        }
        return res.next;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
  }

}
