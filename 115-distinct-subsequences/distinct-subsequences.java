class Solution {

    public int numDistinct(String s, String t) {

        int[][] dp = new int[s.length()][t.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return solve(s, t, s.length() - 1, t.length() - 1, dp);
    }

    public int solve(String s, String t, int i, int j, int[][] dp) {

        if (j < 0) {
            return 1;
        }

        if (i < 0) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s.charAt(i) == t.charAt(j)) {

            dp[i][j] = solve(s, t, i - 1, j - 1, dp)
                      + solve(s, t, i - 1, j, dp);

        } else {

            dp[i][j] = solve(s, t, i - 1, j, dp);
        }

        return dp[i][j];
    }
}