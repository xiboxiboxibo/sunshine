package com.xibo.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 题目：有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 输入: "()"
 * 输出: true
 *
 * 输入: "()[]{}"
 * 输出: true
 *
 * 输入: "(]"
 * 输出: false
 *
 * 输入: "([)]"
 * 输出: false
 *
 * 输入: "{[]}"
 * 输出: true
 */
public class No_20 {

    private static Map<Character, Character> map = new HashMap() {
        {
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }
    };

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            Character val = map.get(c);
            if (val != null) {
                if (stack.isEmpty() || !val.equals(stack.pop())) {
                    return false;
                }
            }
            else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

}
