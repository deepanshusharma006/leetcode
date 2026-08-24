class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Step 1: Prefix Sum
        for (int i = 1; i < n; i++) {
            stones[i] = stones[i] + stones[i - 1];
        }

        // Step 2: Start with total sum
        int dp = stones[n - 1];

        // Step 3: Calculate from right to left
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }

        return dp;
    }
}