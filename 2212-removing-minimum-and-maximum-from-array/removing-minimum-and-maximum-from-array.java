class Solution {
    public int minimumDeletions(int[] nums) {

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int n = nums.length;

        // Dono ko left se delete karo
        int left = Math.max(minIndex, maxIndex) + 1;

        // Dono ko right se delete karo
        int right = n - Math.min(minIndex, maxIndex);

        // Ek left se aur ek right se
        int both = Math.min(minIndex, maxIndex) + 1
                 + n - Math.max(minIndex, maxIndex);

        return Math.min(left, Math.min(right, both));
    }
}