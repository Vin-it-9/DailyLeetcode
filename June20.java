// LeetCode 3443: Maximum Manhattan Distance After K Changes
// This solution calculates the maximum Manhattan distance achievable from the origin
// by performing moves as specified in the string s, with up to k changes allowed.
// At each step, the Manhattan distance is computed as |latitude| + |longitude|, then
// we calculate the maximum result considering that each change can adjust the Manhattan
// distance by 2 (by effectively flipping a move).
//
// The candidate distance at each step is the minimum between the number of moves taken so far (i + 1)
// and the current distance plus k * 2; the latter represents the maximum possible increase if we use all k changes.
//
// The class name is kept as 'Solution' to match the problem statement.
public class June20 {
    public int maxDistance(String s, int k) {
        int latitude = 0, longitude = 0, ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'N') {
                latitude++;
            } else if (c == 'S') {
                latitude--;
            } else if (c == 'E') {
                longitude++;
            } else if (c == 'W') {
                longitude--;
            }
            int currentDistance = Math.abs(latitude) + Math.abs(longitude);
            // With k changes, each change can contribute at most 2 to the Manhattan distance.
            int candidate = Math.min(i + 1, currentDistance + k * 2);
            ans = Math.max(ans, candidate);
        }
        return ans;
    }
}