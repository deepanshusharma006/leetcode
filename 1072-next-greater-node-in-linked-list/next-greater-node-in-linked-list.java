class Solution {
    public int[] nextLargerNodes(ListNode head) {
        // First pass: count nodes and copy values into an array
        List<Integer> values = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            values.add(curr.val);
            curr = curr.next;
        }

        int n = values.size();
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            int val = values.get(i);
            // Pop indices whose value is smaller than current val
            while (!stack.isEmpty() && values.get(stack.peek()) < val) {
                result[stack.pop()] = val;
            }
            stack.push(i);
        }

        // Any indices left in the stack have no next greater element; result stays 0
        return result;
    }
}