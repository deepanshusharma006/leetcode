class Solution {
    public int missingInteger(int[] nums) {

        int prefixSum = nums[0];

        // Find sequential prefix sum
        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }

        // Store all elements
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Find smallest missing integer >= prefixSum
        while (set.contains(prefixSum)) {
            prefixSum++;
        }

        return prefixSum;
    }
}