// LeetCode 2311: Longest Binary Subsequence Less Than or Equal to K

// Approach:
// 1. Start with the largest subsequence consisting of all zeros, as they contribute 0 to the binary number.
// 2. Add ones from the rightmost side of the binary string (least significant bit) to maximize the subsequence length.
// 3. Stop adding ones when the binary number exceeds k.
// 4. Return the length of the subsequence.

public class June26 {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int length = 0;
        long currentValue = 0; // To track the value of the binary subsequence
        long weight = 1; // Represents the position of the bit (1, 2, 4, 8, ...)

        // Iterate from rightmost (least significant bit) to leftmost (most significant bit)
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '0') {
                // Always add '0' to the subsequence
                length++;
            } else {
                // Add '1' if it doesn't exceed k
                if (currentValue + weight <= k) {
                    currentValue += weight;
                    length++;
                }
                // Update weight for the next bit
                weight *= 2;
                // Stop if weight exceeds k (no further ones can be added)
                if (weight > k) break;
            }
        }

        return length;
    }

    // Main method for testing the solution with example cases
    public static void main(String[] args) {

        June26 sol = new June26();
        // Example 1
        String s1 = "1001010";
        int k1 = 5;
        // Expected output: 5
        System.out.println(sol.longestSubsequence(s1, k1)); // Output: 5

        // Example 2
        String s2 = "00101001";
        int k2 = 1;
        // Expected output: 6
        System.out.println(sol.longestSubsequence(s2, k2)); // Output: 6
    }
}