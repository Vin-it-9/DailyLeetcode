// LeetCode 2294: Partition Array Such That Maximum Difference Is K
// Problem: Given an array nums and an integer k, partition nums into the minimum number of subsequences
// such that the difference between the maximum and minimum in each subsequence is at most k.
// Return the minimum number of subsequences needed.
//
// Approach:
// 1. Sort nums so that values close to each other are adjacent.
// 2. Greedily group elements: Start a new group whenever the difference between the current element and
//    the smallest element in the current group exceeds k.
// 3. Count how many groups are needed.

import java.util.*;

public class June19 {

    public static int partitionArray(int[] nums, int k) {
        Arrays.sort(nums); // Sort the array to group close values together
        int count = 1; // At least one group is needed
        int min = nums[0]; // Start of the current group

        for (int i = 1; i < nums.length; i++) {
            // If the difference exceeds k, start a new group
            if (nums[i] - min > k) {
                count++;
                min = nums[i]; // New group starts from this element
            }
        }

        return count;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] nums1 = {3, 6, 1, 2, 5};
        int k1 = 2;
        System.out.println("Output for nums1: " + partitionArray(nums1, k1)); // Expected: 2

        int[] nums2 = {1, 2, 3};
        int k2 = 1;
        System.out.println("Output for nums2: " + partitionArray(nums2, k2)); // Expected: 2

        int[] nums3 = {2, 2, 4, 5};
        int k3 = 0;
        System.out.println("Output for nums3: " + partitionArray(nums3, k3)); // Expected: 3
    }
}