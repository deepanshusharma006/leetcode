class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k == 0 && isSortable(nums, k)) {
                ans += k;
            }
        }

        return ans;
    }

    private boolean isSortable(int[] nums, int k) {
        int n = nums.length;
        int prevMax = -1;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;
            int descents = 0;
            int min = nums[start];
            int max = nums[start];

            for (int i = start; i < end; i++) {
                if (nums[i] > nums[i + 1]) {
                    descents++;
                }

                min = Math.min(min, nums[i + 1]);
                max = Math.max(max, nums[i + 1]);
            }

            if (descents > 1) {
                return false;
            }

            if (descents == 1 && nums[end] > nums[start]) {
                return false;
            }

            if (prevMax > min) {
                return false;
            }

            prevMax = max;
        }

        return true;
    }
}