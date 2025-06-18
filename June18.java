// LeetCode 2966: Divide Array Into Arrays With Max Difference
// Problem: Given an array nums of size n (multiple of 3) and integer k,
// divide nums into n/3 arrays of size 3 such that the difference between any two
// elements in each array is <= k. Return the arrays, or empty array if not possible.
//
// Approach:
// 1. Sort the array to make grouping easier.
// 2. For each consecutive group of 3, check if the difference between max and min is <= k.
// 3. If yes, add the group to the result, else return empty array.

import java.util.*;

public class June18 {

    public static int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums); // Sort to make consecutive grouping optimal

        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < n; i += 3) {
            // Get the min and max for the current group of 3
            int min = nums[i];
            int max = nums[i + 2];
            if (max - min > k) {
                // Not possible to group as required
                return new int[0][];
            }
            // Add the group to the result
            result.add(new int[] {nums[i], nums[i + 1], nums[i + 2]});
        }

        // Convert list of arrays to 2D array for the result
        return result.toArray(new int[result.size()][]);
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums1 = {1,3,4,8,7,9,3,5,1};
        int k1 = 2;
        int[][] res1 = divideArray(nums1, k1);
        System.out.println("Test 1 Output: " + Arrays.deepToString(res1));
        // Expected: [[1,1,3],[3,4,5],[7,8,9]]

        int[] nums2 = {2,4,2,2,5,2};
        int k2 = 2;
        int[][] res2 = divideArray(nums2, k2);
        System.out.println("Test 2 Output: " + Arrays.deepToString(res2));
        // Expected: []

        int[] nums3 = {4,2,9,8,2,12,7,12,10,5,8,5,5,7,9,2,5,11};
        int k3 = 14;
        int[][] res3 = divideArray(nums3, k3);
        System.out.println("Test 3 Output: " + Arrays.deepToString(res3));
        // Expected: Any valid grouping where max-min in each group <= 14
    }
}