package com.xibo.leetcode;

import java.util.Arrays;

/**
 * 题目：最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class No_16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, minDif = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int left = i+1, right = nums.length-1; left < right;) {
                int sum = nums[i] + nums[left] + nums[right];
                int dif = sum - target;
                if (dif < 0) {
                    left++;
                    dif = -dif;
                    if (dif < minDif) {
                        minDif = dif;
                        res = sum;
                    }
                    continue;
                }
                if (dif > 0) {
                    right--;
                    if (dif < minDif) {
                        minDif = dif;
                        res = sum;
                    }
                    continue;
                }
                return sum;
            }
        }
        return res;
    }

}
