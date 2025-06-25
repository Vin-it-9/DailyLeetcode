// LeetCode 2040: Kth Smallest Product of Two Sorted Arrays

// Approach:
// We use binary search to find the kth smallest product between two sorted arrays.
// 1. Sort both arrays (if not already sorted).
// 2. Define the search range for the product: [-10^10, 10^10].
// 3. Use binary search to find the smallest product that satisfies having at least k pairs 
//    where the product is <= mid.
// 4. Count pairs using efficient traversal of the sorted arrays.

import java.util.*;

public class June25 {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // Step 1: Define search boundaries
        long lo = -10000000000L, hi = 10000000000L;

        // Step 2: Binary search to find the kth smallest product
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            long count = countNoGreater(nums1, nums2, mid); // Count pairs with product <= mid
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    // Helper function to count pairs (a, b) such that a * b <= x
    private long countNoGreater(int[] A, int[] B, long x) {
        long count = 0;
        int n = B.length;
        for (int a : A) {
            if (a > 0) {
                // For positive a, we need b <= x / a
                long bound = x / a;
                count += upperBound(B, bound); // Count of b's in B <= bound
            } else if (a == 0) {
                // For a == 0, product is 0. If x >= 0, all b's in B work
                if (x >= 0) {
                    count += n;
                }
            } else { // a < 0
                // For negative a, inequality a * b <= x becomes b >= ceil(x / a)
                long bound = ceilDiv(x, a);
                count += (n - lowerBound(B, bound)); // Count of b's >= bound
            }
        }
        return count;
    }

    // Returns the first index in sorted array B such that B[index] > target
    private int upperBound(int[] B, long target) {
        int low = 0, high = B.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (B[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Returns the first index in sorted array B such that B[index] >= target
    private int lowerBound(int[] B, long target) {
        int low = 0, high = B.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (B[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // Helper function for ceiling division
    private long ceilDiv(long a, long b) {
        // When b < 0, division in Java truncates toward 0; using Math.ceil on double ensures correct behavior
        return (long) Math.ceil((double) a / b);
    }

    // Main method for testing the solution with example cases
    public static void main(String[] args) {

        June25 sol = new June25();

        // Example 1
        int[] nums1_1 = {2, 5};
        int[] nums2_1 = {3, 4};
        long k1 = 2;
        // Expected output: 8
        System.out.println(sol.kthSmallestProduct(nums1_1, nums2_1, k1)); // Output: 8

        // Example 2
        int[] nums1_2 = {-4, -2, 0, 3};
        int[] nums2_2 = {2, 4};
        long k2 = 6;
        // Expected output: 0
        System.out.println(sol.kthSmallestProduct(nums1_2, nums2_2, k2)); // Output: 0

        // Example 3
        int[] nums1_3 = {-2, -1, 0, 1, 2};
        int[] nums2_3 = {-3, -1, 2, 4, 5};
        long k3 = 3;
        // Expected output: -6
        System.out.println(sol.kthSmallestProduct(nums1_3, nums2_3, k3)); // Output: -6
    }
}