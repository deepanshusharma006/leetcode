/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int first = -1;
        int last = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr != null && curr.next != null) {

            ListNode next = curr.next;

            boolean isLocalMax =
                    curr.val > prev.val && curr.val > next.val;

            boolean isLocalMin =
                    curr.val < prev.val && curr.val < next.val;

            if (isLocalMax || isLocalMin) {

                if (first == -1) {
                    first = index;
                } else {
                    minDistance = Math.min(
                        minDistance,
                        index - last
                    );
                }

                last = index;
            }

            prev = curr;
            curr = curr.next;
            index++;
        }

        // Less than 2 critical points
        if (first == -1 || first == last) {
            return new int[]{-1, -1};
        }

        return new int[]{
            minDistance,
            last - first
        };
    }
}