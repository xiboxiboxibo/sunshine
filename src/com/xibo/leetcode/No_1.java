package com.xibo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目：两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class No_1 {

    private Map<Integer, Integer> indexes = new HashMap();

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            Integer index = indexes.get(dif);
            if (index != null) {
                return new int[]{index, i};
            }
            indexes.put(nums[i], i);
        }
        return null;
    }

}
