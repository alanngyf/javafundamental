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
        System.out.println(MinimumSizeSubarrayPrefixSum.minimumSize(arr, sum));
        System.out.println(MinimumSizeSubarrayPrefixSumBinarySearch.minimumSize(arr, sum));

        int[] arr2 = new int[] {1, 2, 3, 4, 5};
        int sum2 = 100;
        System.out.println(MinimumSizeSubarray.minimumSize(arr2, sum2));
        System.out.println(MinimumSizeSubarrayPrefixSum.minimumSize(arr2, sum2));
        System.out.println(MinimumSizeSubarrayPrefixSumBinarySearch.minimumSize(arr2, sum2));
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

class MinimumSizeSubarrayPrefixSum {
    public static int minimumSize(int[] nums, int s) {
        if (nums == null) return -1;

        int len = nums.length;
        int[] prefixSum = getPrefixSum(nums);
        int minSize = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (prefixSum[j + 1] - prefixSum[i] >= s) {
                    minSize = Math.min(j + 1 - i, minSize);
                }
            }
        }
        return (minSize == Integer.MAX_VALUE)? -1: minSize;
    }

    public static int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }
}

class MinimumSizeSubarrayPrefixSumBinarySearch {
    public static int minimumSize(int[] nums, int s) {
        if (nums == null) return -1;

        int len = nums.length;
        int[] prefixSum = getPrefixSum(nums);
        int minSize = Integer.MAX_VALUE;

        for (int start = 0; start < len; start++) {
            int end = getEndOfSubArray(prefixSum, start, s);
            if (prefixSum[end + 1] - prefixSum[start] >= s) {
                minSize = Math.min(minSize, end - start + 1);
            }
        }
        return (minSize == Integer.MAX_VALUE)? -1: minSize;
    }

    public static int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }

    public static int getEndOfSubArray(int[] prefixSum, int start, int s) {
        int left = start, right = prefixSum.length - 2;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid + 1] - prefixSum[start] >= s) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (prefixSum[left + 1] - prefixSum[start] >= s) {
            return left;
        }

        return right;
    }
}
