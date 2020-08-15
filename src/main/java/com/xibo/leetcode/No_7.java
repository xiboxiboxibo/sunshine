package com.xibo.leetcode;

/**
 * 题目：整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class No_7 {

    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            res = res * 10 + pop;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

}
