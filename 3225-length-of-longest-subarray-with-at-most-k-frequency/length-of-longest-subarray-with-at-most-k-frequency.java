class Solution {
    public int maxSubarrayLength(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            // frequency increase
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            // agar frequency k se zyada ho gayi
            while (map.get(nums[right]) > k) {

                map.put(nums[left], map.get(nums[left]) - 1);

                left++;
            }

            // current valid window ki length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}