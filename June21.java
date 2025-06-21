// LeetCode 3085: Minimum Deletions to Make String K-Special
// Given a string `word` and integer `k`, make it so that for any two characters i and j,
// |freq(i) - freq(j)| <= k, with minimum deletions.
//
// Approach:
// 1. Count frequency for each character in the word.
// 2. For each possible target frequency t (from 1 to max frequency), try to make all
//    character frequencies within [t, t + k] by deleting excess occurrences from characters
//    whose frequency > t + k, and deleting all occurrences from characters whose frequency < t.
// 3. The answer is the minimum number of deletions over all possible t.
//
// Time complexity: O(26 * max_freq) = O(max_freq), which is efficient for English lowercase letters.

public class June21 {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }
        // Filter zero frequencies and sort
        int maxFreq = 0;
        for (int f : freq)
            maxFreq = Math.max(maxFreq, f);

        int minDel = Integer.MAX_VALUE;
        // Try each possible target frequency t
        for (int t = 1; t <= maxFreq; t++) {
            int deletions = 0;
            for (int f : freq) {
                if (f == 0) continue;
                // If frequency < t, delete all occurrences
                if (f < t)
                    deletions += f;
                    // If frequency > t + k, delete the excess
                else if (f > t + k)
                    deletions += f - (t + k);
                // If t <= f <= t + k, do nothing
            }
            minDel = Math.min(minDel, deletions);
        }
        return minDel;
    }

    // Main method for demonstration
    public static void main(String[] args) {
        June21 sol = new June21();
        System.out.println(sol.minimumDeletions("aabcaba", 0)); // Output: 3
        System.out.println(sol.minimumDeletions("dabdcbdcdcd", 2)); // Output: 2
        System.out.println(sol.minimumDeletions("aaabaaa", 2)); // Output: 1
    }
}