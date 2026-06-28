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
    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevleft = dummy;

        // prevleft ko left se ek node pehle le jao
        for (int i = 1; i < left; i++) {
            prevleft = prevleft.next;
        }

        ListNode curr = prevleft.next;
        ListNode prev = null;

        // Reverse only required part
        for (int i = 0; i <= right - left; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode leftNode = prevleft.next;

        prevleft.next = prev;
        leftNode.next = curr;

        return dummy.next;
    }
}