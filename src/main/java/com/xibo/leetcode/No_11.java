package com.xibo.leetcode;

/**
 * 题目：盛最多水的容器
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 */
public class No_11 {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, max = 0, temp;
        while (left < right) {
            if (height[left] < height[right]) {
                temp = (right-left) * height[left];
                while (height[left++] > height[left]);
            }
            else {
                temp = (right-left) * height[right];
                while (height[right--] > height[right]);
            }
            if (temp > max) max = temp;
        }
        return max;
    }

}
