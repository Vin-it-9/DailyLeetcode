// LeetCode 2200: Find All K-Distant Indices in an Array

// Approach:
// Instead of using nested loops, we can use a simpler approach:
// 1. Traverse the array and record all indices where the element equals the key.
// 2. For each of those indices, mark all indices in the range [index - k, index + k] 
//    (making sure we don't go out-of-bounds) as k-distant.
// 3. Finally, collect all the marked indices into a list.
// This approach avoids unnecessary nested loops and is easier to understand.

import java.util.ArrayList;
import java.util.List;

public class June24 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> list = new ArrayList<>();
        int n = nums.length;
        // For each index i, check if there's any index j with nums[j] == key and |i - j| <= k.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[j] == key && Math.abs(i - j) <= k) {
                    list.add(i);
                    break;
                }
            }
        }
        return list;
    }

    // Main method for testing the solution with example cases.
    public static void main(String[] args) {
        June24 sol = new June24();

        int[] nums1 = {3,4,9,1,3,9,5};
        int key1 = 9, k1 = 1;
        // Expected output: [1,2,3,4,5,6]
        System.out.println(sol.findKDistantIndices(nums1, key1, k1));

        int[] nums2 = {2,2,2,2,2};
        int key2 = 2, k2 = 2;
        // Expected output: [0,1,2,3,4]
        System.out.println(sol.findKDistantIndices(nums2, key2, k2));
    }
}