class Solution {

    Boolean[][][] dp;
    int m, n;

    public boolean hasValidPath(char[][] grid) {

        m = grid.length;
        n = grid[0].length;

        // Valid parentheses string ki length even honi chahiye
        if ((m + n - 1) % 2 == 1) {
            return false;
        }

        dp = new Boolean[m][n][m + n];

        return solve(grid, 0, 0, 0);
    }

    private boolean solve(char[][] grid, int row, int col, int balance) {

        // Balance negative ho gaya -> invalid
        if (balance < 0) {
            return false;
        }

        // Current cell ka bracket process karo
        if (grid[row][col] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Current cell process karne ke baad balance negative
        if (balance < 0) {
            return false;
        }

        // Last cell
        if (row == m - 1 && col == n - 1) {
            return balance == 0;
        }

        // Already calculated
        if (dp[row][col][balance] != null) {
            return dp[row][col][balance];
        }

        boolean possible = false;

        // Down
        if (row + 1 < m) {
            possible = solve(grid, row + 1, col, balance);
        }

        // Right
        if (!possible && col + 1 < n) {
            possible = solve(grid, row, col + 1, balance);
        }

        return dp[row][col][balance] = possible;
    }
}