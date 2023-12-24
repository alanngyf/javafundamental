package com.gbacoder.algo.twopointers;

/**
 * @author alanulog
 * @create 2023-12-23
 *
 * Lintcode 406 · Minimum Size Subarray Sum
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return -1 instead.
 *
 * Example 1:
 * Input: [2,3,1,2,4,3], s = 7
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Example 2:
 * Input: [1, 2, 3, 4, 5], s = 100
 * Output: -1
 */
public class MinSubarraySumDemo {
    public static void main(String[] args) {
        int[] arr = new int[] {2,3,1,2,4,3};
        int sum = 7;
        System.out.println(MinimumSizeSubarray.minimumSize(arr, sum));

        int[] arr2 = new int[] {1, 2, 3, 4, 5};
        int sum2 = 100;
        System.out.println(MinimumSizeSubarray.minimumSize(arr2, sum2));
    }
}

class MinimumSizeSubarray {
    public static int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) return -1;

        int minSize = Integer.MAX_VALUE;
        int len = nums.length;
        int currSumOfSubArray = 0;
        int j = 0;

        for (int i = 0; i < len; i++) {
            while (j < len && currSumOfSubArray < s) {
                currSumOfSubArray += nums[j];
                j++;
            }

            if (currSumOfSubArray >= s) {
                minSize = Math.min(minSize, j - i);
            }
            currSumOfSubArray -= nums[i];
        }
        return (minSize == Integer.MAX_VALUE)? -1 : minSize;
    }
}
