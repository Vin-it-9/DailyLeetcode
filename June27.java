import java.util.*;

public class June27 {
        // Helper: Check if t*k is a subsequence of s
        private boolean isRepeatedSubsequence(String s, String t, int k) {
            int m = t.length(), j = 0, cnt = 0;
            for (char c : s.toCharArray()) {
                if (c == t.charAt(j)) {
                    j++;
                    if (j == m) {
                        cnt++;
                        if (cnt == k) return true;
                        j = 0;
                    }
                }
            }
            return false;
        }

        public String longestSubsequenceRepeatedK(String s, int k) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) freq[c - 'a']++;

            // Only characters with freq >= k can be used in answer
            List<Character> cand = new ArrayList<>();
            for (int i = 25; i >= 0; i--) {
                if (freq[i] >= k) cand.add((char) ('a' + i));
            }

            String res = "";
            // BFS to generate all strings up to length s.length() / k
            Queue<String> q = new LinkedList<>();
            q.offer("");
            int maxLen = s.length() / k; // can't be longer than this

            while (!q.isEmpty()) {
                String cur = q.poll();
                for (char c : cand) {
                    String next = cur + c;
                    if (isRepeatedSubsequence(s, next, k)) {
                        if (next.length() > res.length() ||
                                (next.length() == res.length() && next.compareTo(res) > 0)) {
                            res = next;
                        }
                        if (next.length() < maxLen) {
                            q.offer(next);
                        }
                    }
                }
            }
            return res;
        }

        // Main method to test the solution
        public static void main(String[] args) {
            June27 sol = new June27();

            System.out.println(sol.longestSubsequenceRepeatedK("letsleetcode", 2)); // Output: "let"
            System.out.println(sol.longestSubsequenceRepeatedK("bb", 2)); // Output: "b"
            System.out.println(sol.longestSubsequenceRepeatedK("ab", 2)); // Output: ""
        }

}
