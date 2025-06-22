// LeetCode 2138: Divide a String Into Groups of Size k
//
// Problem Explanation:
// Given a string s, an integer k (the group size), and a fill character,
// divide s into groups of size k. If the last group has less than k characters,
// pad it with the fill character until its length becomes k.
//
// Example:
// s = "abcdefghij", k = 3, fill = 'x'
// Output: ["abc", "def", "ghi", "jxx"]
//
// Approach:
// 1. Iterate through the string in steps of size k.
// 2. For each step, extract a substring of length k if possible.
// 3. If fewer than k characters remain, pad with the fill character.
// 4. Store each group in a list and finally convert it to a String array.

import java.util.ArrayList;
import java.util.List;

public class June22 {

    /**
     * Divides the input string s into groups of size k. If the last group
     * has fewer than k characters, it is padded with the fill character.
     *
     * @param s    The input string to divide.
     * @param k    The size of each group.
     * @param fill The character used to fill the last group if necessary.
     * @return     An array of strings representing the divided groups.
     */
    public String[] divideString(String s, int k, char fill) {
        List<String> result = new ArrayList<>();
        int n = s.length();

        // Process the string in steps of k
        for (int i = 0; i < n; i += k) {
            if (i + k <= n) {
                // If there are at least k characters left, take the substring directly
                result.add(s.substring(i, i + k));
            } else {
                // If fewer than k characters remain, pad with fill character
                StringBuilder sb = new StringBuilder(s.substring(i));
                while (sb.length() < k) {
                    sb.append(fill);
                }
                result.add(sb.toString());
            }
        }
        // Convert the list to an array and return
        return result.toArray(new String[0]);
    }

    // Main method for demonstration and testing
    public static void main(String[] args) {
        June22 sol = new June22();

        String[] res1 = sol.divideString("abcdefghi", 3, 'x');
        System.out.println(java.util.Arrays.toString(res1)); // Output: [abc, def, ghi]

        String[] res2 = sol.divideString("abcdefghij", 3, 'x');
        System.out.println(java.util.Arrays.toString(res2)); // Output: [abc, def, ghi, jxx]
    }
}