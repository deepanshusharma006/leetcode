class Solution {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {

        dfs(root);

        return ans;
    }

    int[] dfs(TreeNode root) {

        // Base case
        if (root == null) {
            return new int[]{0, 0};
        }

        // Left subtree
        int[] left = dfs(root.left);

        // Right subtree
        int[] right = dfs(root.right);

        // Total sum of current subtree
        int sum = left[0] + right[0] + root.val;

        // Total nodes of current subtree
        int count = left[1] + right[1] + 1;

        // Average == current node?
        if (sum / count == root.val) {
            ans++;
        }

        // Return {sum, count}
        return new int[]{sum, count};
    }
}