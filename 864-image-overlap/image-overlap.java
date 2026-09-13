class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {

        int n = img1.length;
        int maxOverlap = 0;

        // img1 ko har possible row shift ke liye move karo
        for (int rowShift = -n + 1; rowShift <= n - 1; rowShift++) {

            // img1 ko har possible column shift ke liye move karo
            for (int colShift = -n + 1; colShift <= n - 1; colShift++) {

                int overlap = 0;

                // img1 ke har cell ko check karo
                for (int i = 0; i < n; i++) {

                    for (int j = 0; j < n; j++) {

                        // img1 ka shifted position
                        int x = i + rowShift;
                        int y = j + colShift;

                        // shifted position matrix ke andar honi chahiye
                        if (x >= 0 && x < n && y >= 0 && y < n) {

                            // dono images me 1 hai
                            if (img1[i][j] == 1 && img2[x][y] == 1) {
                                overlap++;
                            }
                        }
                    }
                }

                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}