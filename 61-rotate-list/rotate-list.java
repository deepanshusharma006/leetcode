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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k==0){
            return head;
        }
        //step1. find the length

        int length = 1;
        ListNode temp = head;
        while(temp.next != null){
        temp = temp.next;
        length++;
        }

        //step 2. find k 

        k = k % length;
        if(k ==0){
            return head;
        }

        //step 3. make it circular

        temp.next = head;

        //step 4. find new tail

      int stepToNewTail = length-k;
       ListNode newTail = head;
      for(int i=1;i<stepToNewTail;i++){
       newTail = newTail.next;
      
      }
ListNode newHead = newTail.next;
newTail.next = null;
return newHead;

    }
}