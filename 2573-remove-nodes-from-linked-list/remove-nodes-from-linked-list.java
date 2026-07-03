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
    public ListNode removeNodes(ListNode head) {
        head = reverse(head);

        int maxSoFar = head.val;
        ListNode curr = head;
        while (curr.next != null) {
            if (curr.next.val < maxSoFar) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
                maxSoFar = curr.val;
            }
        }

        return reverse(head);
    }

    private ListNode reverse(ListNode h) {
        ListNode prev = null;
        while (h != null) {
            ListNode next = h.next;
            h.next = prev;
            prev = h;
            h = next;
        }
        return prev;
    }
}