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
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return ;
        }
        //find mid node
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // reverse the second half
        ListNode secondHalf = reverse(slow.next);
        slow.next = null;

        ListNode firstHalf = head;

        //step3 merge both halves

        while(secondHalf != null){
            ListNode temp1 = firstHalf.next;
            ListNode temp2 = secondHalf.next;

            firstHalf.next = secondHalf; //firsthalf ki ek value k bd second half ki value aygi ek
            secondHalf.next = temp1;//oppsite hoga isme

            firstHalf = temp1;//ye loop ka kaam krii hai agli digit pr move hogi i++ wala kaam krege
            secondHalf = temp2;
        }
    }
     private ListNode reverse(ListNode head){
            ListNode prev = null;
            ListNode curr = head;
            while(curr != null){
                ListNode nextNode = curr.next;
               curr.next = prev;
               prev = curr;
               curr = nextNode;

            }
        
        return prev;
    }
}