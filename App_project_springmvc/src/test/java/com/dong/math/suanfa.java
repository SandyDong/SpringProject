package com.dong.math;

public class suanfa {

    public int[] twoSum(int[] nums, int target) {
        int[] valueIndex = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    valueIndex[0] = i;
                    valueIndex[1] = j;
                }
            }
        }
        return valueIndex;
    }
}
