import java.util.*;

class Solution {

    static class Node {
        int l, r, idx;
        long w;

        Node(int l, int r, long w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Node[] a = new Node[n];

        for (int i = 0; i < n; i++) {
            a[i] = new Node(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        // Sort by right endpoint
        Arrays.sort(a, (x, y) -> {
            if (x.r != y.r)
                return Integer.compare(x.r, y.r);
            return Integer.compare(x.idx, y.idx);
        });

        // prev[i] = last interval ending strictly before a[i].l
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = findPrevious(a, i, a[i].l);
        }

        /*
         * dp[k] = best answer using processed intervals
         *         and choosing at most k intervals.
         *
         * We only need previous row, so memory is O(4).
         */
        State[] dp = new State[5];

        for (int k = 0; k <= 4; k++) {
            dp[k] = new State(0, new int[0]);
        }

        /*
         * We need DP for previous compatible interval.
         * dpTable[i][k] stores best state among first i intervals.
         *
         * Each State contains max 4 indices only.
         */
        State[][] table = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            table[0][k] = new State(0, new int[0]);
        }

        for (int i = 1; i <= n; i++) {

            Node cur = a[i - 1];

            for (int k = 0; k <= 4; k++) {

                // Skip current interval
                State skip = table[i - 1][k];

                if (k == 0) {
                    table[i][k] = skip;
                    continue;
                }

                // Take current interval
                int p = prev[i - 1] + 1;

                State before = table[p][k - 1];

                int[] ids = new int[before.ids.length + 1];

                System.arraycopy(
                    before.ids,
                    0,
                    ids,
                    0,
                    before.ids.length
                );

                ids[ids.length - 1] = cur.idx;

                Arrays.sort(ids);

                State take = new State(
                    before.score + cur.w,
                    ids
                );

                table[i][k] = better(take, skip);
            }
        }

        return table[n][4].ids;
    }

    private int findPrevious(Node[] a, int pos, int left) {

        int lo = 0;
        int hi = pos - 1;
        int ans = -1;

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            // Strictly smaller because touching intervals overlap
            if (a[mid].r < left) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private State better(State x, State y) {

        if (x.score != y.score) {
            return x.score > y.score ? x : y;
        }

        // Same score -> lexicographically smaller
        return lexicographicallySmaller(x.ids, y.ids) ? x : y;
    }

    private boolean lexicographicallySmaller(int[] a, int[] b) {

        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}