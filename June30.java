// LeetCode 594: Longest Harmonious Subsequence

// Approach:
// 1. Count the frequency of each number in the array using a HashMap.
// 2. For each unique number, check if number + 1 exists in the map.
// 3. If it exists, calculate the sum of the frequencies of number and number + 1.
// 4. Track the maximum such sum, which is the answer.

import java.util.HashMap;
import java.util.Map;

public class June30 {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        // Count the frequency of each number
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxLen = 0;
        // For each number, check if num + 1 exists
        for (int key : freq.keySet()) {
            if (freq.containsKey(key + 1)) {
                maxLen = Math.max(maxLen, freq.get(key) + freq.get(key + 1));
            }
        }
        return maxLen;
    }

    // Main method for testing the solution with example cases.
    public static void main(String[] args) {

        June30 sol = new June30();

        int[] nums1 = {1,3,2,2,5,2,3,7};
        // Expected output: 5
        System.out.println(sol.findLHS(nums1));

        int[] nums2 = {1,2,3,4};
        // Expected output: 2
        System.out.println(sol.findLHS(nums2));

        int[] nums3 = {1,1,1,1};
        // Expected output: 0
        System.out.println(sol.findLHS(nums3));
    }
}