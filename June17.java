// Java solution for LeetCode Problem 3405:
// "Count the Number of Arrays with K Matching Adjacent Elements"
// Problem Overview:
// You are given three integers n, m, and k, and you need to count how many arrays of size n,
// with elements in the range [1, m], have exactly k adjacent pairs with matching values.

// Key Idea:
// - The first element can be chosen in m ways.
// - For each subsequent element, there are two cases:
//   1. It matches the previous element (only 1 way). This needs to happen exactly k times.
//   2. It is different (m-1 ways).
// - Out of (n-1) adjacent pairs, choose k positions for matching pairs using C(n-1, k).
// - Multiply everything together modulo 10^9 + 7.

public class June17 {

    // Modulo constant for large number arithmetic
    static final int MOD = 1000000007;

    // Function to compute the number of good arrays based on input parameters.
    public static int countGoodArrays(int n, int m, int k) {
        // Compute combination C(n-1, k)
        long comb = nCk(n - 1, k);

        // Compute the number of ways to fill non-matching adjacent pairs: (m-1)^(n-1-k)
        long diffWays = modExp(m - 1, n - 1 - k, MOD);

        // Multiply by the number of ways to choose the first element (m choices)
        long ans = (m * ((comb % MOD) * (diffWays % MOD) % MOD)) % MOD;
        return (int) ans;
    }

    // Helper method for fast modular exponentiation.
    // It computes (base^exp) % mod efficiently.
    private static long modExp(long base, int exp, int mod) {
        long result = 1;
        base %= mod;
        while(exp > 0) {
            // If the exponent is odd, multiply the result with the base.
            if((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            // Square the base and halve the exponent.
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // Helper method to compute binomial coefficient nCk modulo mod.
    // This uses precomputed factorials and modular inverses based on Fermat's little theorem.
    private static long nCk(int n, int k) {
        if(k < 0 || k > n) {
            return 0;
        }
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        fact[0] = 1;
        // Compute all factorials % MOD
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }
        // Compute the modular inverse of n! using modular exponentiation.
        invFact[n] = modExp(fact[n], MOD - 2, MOD);
        // Backward compute the modular inverses for all factorials
        for (int i = n - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;
        }
        // Compute nCk = fact[n] / (fact[k] * fact[n-k]) modulo MOD.
        return (fact[n] * ((invFact[k] * invFact[n - k]) % MOD)) % MOD;
    }

    // Main method provided at the end.
    // This method tests the countGoodArrays function with several sample test cases:
    // Example 1: n = 3, m = 2, k = 1 -> Expected output: 4
    // Example 2: n = 4, m = 2, k = 2 -> Expected output: 6
    // Example 3: n = 5, m = 2, k = 0 -> Expected output: 2
    public static void main(String[] args) {
        int n1 = 3, m1 = 2, k1 = 1;
        System.out.println("Output for n = " + n1 + ", m = " + m1 + ", k = " + k1 + " is: " + countGoodArrays(n1, m1, k1));

        int n2 = 4, m2 = 2, k2 = 2;
        System.out.println("Output for n = " + n2 + ", m = " + m2 + ", k = " + k2 + " is: " + countGoodArrays(n2, m2, k2));

        int n3 = 5, m3 = 2, k3 = 0;
        System.out.println("Output for n = " + n3 + ", m = " + m3 + ", k = " + k3 + " is: " + countGoodArrays(n3, m3, k3));
    }
}