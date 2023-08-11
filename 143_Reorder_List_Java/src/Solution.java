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

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public void reorderList(ListNode head) {
        if(head.next==null || head.next.next==null) return;

        var oneStep = head;
        var twoStep = head;

        while (twoStep!=null && twoStep.next!=null){
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }

        ListNode prev = null;
        var pointer1 = oneStep;
        var pointer2 = pointer1.next;
        while (pointer2!=null){
            pointer1.next = prev;
            prev = pointer1;
            pointer1 = pointer2;
            pointer2 = pointer2.next;
        }

        var dummy = new ListNode(-1);
        var pointer = dummy;
        var p1 = head;
        var p2 = pointer1;
        while (p1!=null && p2!=null){
            pointer.next = p1;
            pointer = pointer.next;

            pointer.next = p2;
            pointer = pointer.next;

            p1 = p1.next;
            p2 = p2.next;
        }

        while (p2!=null){
            pointer.next = p2;
            pointer = pointer.next;
            p2 = p2.next;
        }
    }
}