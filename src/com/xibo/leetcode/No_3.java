package com.xibo.leetcode;

/**
 * 题目：无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class No_3 {

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if(length == 0 || length == 1)
            return length;
        char[] chs = s.toCharArray();
        int res = 0, begin = 0;
        for(int i = 0; i < length; i++) {
            for(int j = begin; j < i; j++) {
                if(chs[i] == chs[j]) {
                    res = Math.max(res, i-begin);
                    begin = j+1;
                    break;
                }
            }
        }
        return Math.max(res, length-begin);
    }

}
