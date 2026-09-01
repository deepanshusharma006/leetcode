import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        List<int[]> litters = new ArrayList<>();

        // Find starting position and litter positions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litters.add(new int[]{i, j});
                }
            }
        }

        int k = litters.size();

        // No litter
        if (k == 0) {
            return 0;
        }

        // Give every litter an index for bitmask
        int[][] litterIndex = new int[m][n];

        for (int[] row : litterIndex) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < k; i++) {
            int r = litters.get(i)[0];
            int c = litters.get(i)[1];

            litterIndex[r][c] = i;
        }

        int targetMask = (1 << k) - 1;

        // visited[row][col][remainingEnergy][mask]
        boolean[][][][] visited =
                new boolean[m][n][energy + 1][1 << k];

        Queue<int[]> queue = new LinkedList<>();

        // {row, col, currentEnergy, collectedMask, moves}
        queue.offer(new int[]{
                startR,
                startC,
                energy,
                0,
                0
        });

        visited[startR][startC][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];
            int currEnergy = curr[2];
            int mask = curr[3];
            int moves = curr[4];

            // All litter collected
            if (mask == targetMask) {
                return moves;
            }

            // No energy left, cannot move further
            if (currEnergy == 0) {
                continue;
            }

            // Explore 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Boundary check
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                char cell = classroom[nr].charAt(nc);

                // Cannot cross obstacle
                if (cell == 'X') {
                    continue;
                }

                int nextEnergy = currEnergy - 1;
                int nextMask = mask;

                // Collect litter
                if (litterIndex[nr][nc] != -1) {

                    int index = litterIndex[nr][nc];

                    nextMask = mask | (1 << index);
                }

                // Reset energy
                if (cell == 'R') {
                    nextEnergy = energy;
                }

                // New state
                if (!visited[nr][nc][nextEnergy][nextMask]) {

                    visited[nr][nc][nextEnergy][nextMask] = true;

                    queue.offer(new int[]{
                            nr,
                            nc,
                            nextEnergy,
                            nextMask,
                            moves + 1
                    });
                }
            }
        }

        return -1;
    }
}