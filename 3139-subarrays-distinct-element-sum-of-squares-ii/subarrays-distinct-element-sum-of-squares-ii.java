
import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        long[] sum = new long[4 * n];
        long[] sq = new long[4 * n];
        long[] lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = last.getOrDefault(nums[i], -1);

            update(1, 0, n - 1, prev + 1, i,
                   sum, sq, lazy);

            ans = (ans + sq[1]) % MOD;
            last.put(nums[i], i);
        }

        return (int) ans;
    }

    private void apply(int node, int l, int r, long delta,
                       long[] sum, long[] sq, long[] lazy) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sq[node] = (sq[node]
                + 2L * delta % MOD * oldSum % MOD
                + delta * delta % MOD * len % MOD) % MOD;

        sum[node] = (oldSum + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void pushDown(int node, int l, int r,
                          long[] sum, long[] sq, long[] lazy) {
        if (lazy[node] == 0 || l == r) return;

        int mid = l + (r - l) / 2;
        long delta = lazy[node];

        apply(node * 2, l, mid, delta, sum, sq, lazy);
        apply(node * 2 + 1, mid + 1, r, delta, sum, sq, lazy);

        lazy[node] = 0;
    }

    private void update(int node, int l, int r,
                        int ql, int qr,
                        long[] sum, long[] sq, long[] lazy) {
        if (ql > r || qr < l) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1, sum, sq, lazy);
            return;
        }

        pushDown(node, l, r, sum, sq, lazy);

        int mid = l + (r - l) / 2;

        update(node * 2, l, mid, ql, qr, sum, sq, lazy);
        update(node * 2 + 1, mid + 1, r, ql, qr, sum, sq, lazy);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
