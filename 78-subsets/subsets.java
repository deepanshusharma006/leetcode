class Solution {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        backtrack(nums, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(int[] nums, int index,
                           List<Integer> current,
                           List<List<Integer>> ans) {

        // current subset ko answer me add karo
        ans.add(new ArrayList<>(current));

        // har possible next element try karo
        for (int i = index; i < nums.length; i++) {

            // element choose karo
            current.add(nums[i]);

            // next elements ke liye recursion
            backtrack(nums, i + 1, current, ans);

            // element remove karo
            current.remove(current.size() - 1);
        }
    }
}