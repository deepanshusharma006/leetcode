class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Reserved seats ko row-wise store karo
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Sirf seats 2 to 9 matter karti hain
            if (col >= 2 && col <= 9) {
                map.put(row, map.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Initially har empty row me 2 families aa sakti hain
        int ans = (n - map.size()) * 2;

        for (int mask : map.values()) {

            // Family 1: seats 2,3,4,5
            boolean left = (mask & (1 << 2)) == 0 &&
                           (mask & (1 << 3)) == 0 &&
                           (mask & (1 << 4)) == 0 &&
                           (mask & (1 << 5)) == 0;

            // Family 2: seats 6,7,8,9
            boolean right = (mask & (1 << 6)) == 0 &&
                            (mask & (1 << 7)) == 0 &&
                            (mask & (1 << 8)) == 0 &&
                            (mask & (1 << 9)) == 0;

            // Family can sit in middle: 4,5,6,7
            boolean middle = (mask & (1 << 4)) == 0 &&
                             (mask & (1 << 5)) == 0 &&
                             (mask & (1 << 6)) == 0 &&
                             (mask & (1 << 7)) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || right || middle) {
                ans += 1;
            }
        }

        return ans;
    }
}