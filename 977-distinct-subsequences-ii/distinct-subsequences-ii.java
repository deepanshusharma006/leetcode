class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1000000007;

        long[] dp = new long[s.length() + 1];
        long[] last = new long[26];

        dp[0] = 1; // empty subsequence

        for (int i = 1; i <= s.length(); i++) {
            int ch = s.charAt(i - 1) - 'a';

            // Double the subsequences:
            // one copy without current char
            // one copy with current char
            dp[i] = (2 * dp[i - 1]) % MOD;

            // Remove duplicates caused by previous same character
            dp[i] = (dp[i] - last[ch] + MOD) % MOD;

            // Store dp value BEFORE current character
            last[ch] = dp[i - 1];
        }

        // Remove empty subsequence
        return (int) ((dp[s.length()] - 1 + MOD) % MOD);
    }
}