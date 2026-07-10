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
    public ListNode partition(ListNode head, int x) {
      if(head == null || head.next == null){
        return head;
      }
      ListNode beforeDummy = new ListNode(0);
      ListNode afterDummy = new ListNode(0);
      ListNode before = beforeDummy;
      ListNode after = afterDummy;

      ListNode curr = head;

      while(curr != null){
        if(curr.val < x){
            before.next = curr;
            before = before.next;
        }
        else{
            after.next = curr;
            after = after.next;
        }
        curr = curr.next;
      }
      after.next = null;
      before.next = afterDummy.next;
        
      return beforeDummy.next;  
        
    }
}