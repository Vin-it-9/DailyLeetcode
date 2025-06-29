import java.util.Arrays;

public class June29 {

    public int numSubseq(int[] nums, int target) {
        int mod = 1_000_000_007;
        Arrays.sort(nums);
        int n = nums.length;
        int[] pow2 = new int[n+1];
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow2[i] = (pow2[i-1] * 2) % mod;
        }

        int left = 0, right = n - 1, ans = 0;
        while (left <= right) {
            if (nums[left] + nums[right] <= target) {
                ans = (ans + pow2[right-left]) % mod;
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

}
