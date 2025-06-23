// LeetCode 2081: Sum of k-Mirror Numbers
//
// A k-mirror number is a positive integer that is palindrome in both base-10 
// and base-k (without leading zeros). This solution generates palindromic 
// numbers in base-10 in increasing order and checks whether their representation 
// in base-k is also a palindrome. We sum the n smallest such k-mirror numbers.
//
// Approach:
// 1. Generate palindromic numbers in base-10 by constructing them based on their length.
//    For even lengths, mirror the first half; for odd lengths, add a middle digit.
// 2. Use Long.toString(num, k) to obtain the base-k representation and test if it is a palindrome.
// 3. Continue generating palindromes until we have collected n valid k-mirror numbers.
// 4. Return the sum of these numbers.

import java.util.ArrayList;
import java.util.List;

public class June23 {

    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;
        int length = 1;
        // Continue until we find n k-mirror numbers.
        while (count < n) {
            List<Long> palindromes = generatePalindromes(length);
            // Process the generated palindromes in increasing order.
            for (long p : palindromes) {
                if (isKPalindrome(p, k)) {
                    sum += p;
                    count++;
                    if (count == n) {
                        break;
                    }
                }
            }
            length++;
        }
        return sum;
    }

    // Generates all base-10 palindromes with the given length.
    private List<Long> generatePalindromes(int length) {
        List<Long> res = new ArrayList<>();
        // For length 1, simply add digits 1 to 9.
        if (length == 1) {
            for (int d = 1; d <= 9; d++) {
                res.add((long)d);
            }
            return res;
        }

        // Determine the half length.
        int half = length / 2;
        // Calculate start and end for the first half
        long start = (long) Math.pow(10, half - 1);
        long end = (long) Math.pow(10, half) - 1;

        // Even length palindromes
        if (length % 2 == 0) {
            for (long i = start; i <= end; i++) {
                String left = Long.toString(i);
                String right = new StringBuilder(left).reverse().toString();
                long pal = Long.parseLong(left + right);
                res.add(pal);
            }
        } else {
            // Odd length palindromes: try every possible middle digit.
            for (long i = start; i <= end; i++) {
                String left = Long.toString(i);
                String rev = new StringBuilder(left).reverse().toString();
                for (int mid = 0; mid <= 9; mid++) {
                    long pal = Long.parseLong(left + mid + rev);
                    res.add(pal);
                }
            }
        }
        return res;
    }

    // Checks if the number's base-k representation is a palindrome.
    private boolean isKPalindrome(long num, int k) {
        String baseK = Long.toString(num, k);
        return isPalindrome(baseK);
    }

    // Helper method to check if a string is a palindrome.
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    // Main method for testing the solution with example cases.
    public static void main(String[] args) {
        June23 sol = new June23();
        // Example 1: k = 2, n = 5, expected output 25
        System.out.println(sol.kMirror(2, 5));  // Output: 25

        // Example 2: k = 3, n = 7, expected output 499
        System.out.println(sol.kMirror(3, 7));  // Output: 499

        // Example 3: k = 7, n = 17, expected output: 20379000 (as per sample)
        System.out.println(sol.kMirror(7, 17)); // Output: 20379000
    }
}