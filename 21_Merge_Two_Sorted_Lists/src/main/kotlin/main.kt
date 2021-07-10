/*
21. Merge Two Sorted Lists(Easy)
Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.

Example 1:
Input: l1 = [1,2,4], l2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: l1 = [], l2 = []
Output: []

Example 3:
Input: l1 = [], l2 = [0]
Output: [0]

Constraints:
The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both l1 and l2 are sorted in non-decreasing order.
 */

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */



class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 == null) return l2
        if(l2 == null) return l1

        if(l1.`val`>l2.`val`){
            l2.next = mergeTwoLists(l1,l2.next)
            return l2
        }else{
            l1.next = mergeTwoLists(l1.next,l2)
            return l1
        }
    }

    fun mergeTwoListsIterative(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 == null) return l2
        if(l2 == null) return l1

        var pointerL1 = l1
        var pointerL2 = l2
        var head:ListNode? = null
        var tail:ListNode? = head

        while( !(pointerL1== null && pointerL2==null) ){
            if(pointerL1==null){
                tail?.next = pointerL2
                break
            }

            if(pointerL2==null){
                tail?.next = pointerL1
                break
            }

            val smallOne =
                if(pointerL1.`val`<pointerL2.`val`){
                    val tmp = pointerL1
                    pointerL1 = pointerL1.next
                    tmp
                }else {
                    val tmp = pointerL2
                    pointerL2 = pointerL2.next
                    tmp
                }

            if(head == null) {
                head = smallOne
                tail = smallOne
            }else {
                tail?.next = smallOne
                tail = smallOne
            }
        }
        return head

    }

    //比較聰明的方法是 head塞一個最小數ListNode(-1) return回傳head.next就好了
    //可以省去很多初始化的意外
    fun mergeTwoListsIterativeV2(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 == null) return l2
        if(l2 == null) return l1

        var pointerL1 = l1
        var pointerL2 = l2
        var head = ListNode(-1)
        var tail:ListNode? = head

        while( !(pointerL1== null && pointerL2==null) ){
            if(pointerL1==null){
                tail?.next = pointerL2
                break
            }

            if(pointerL2==null){
                tail?.next = pointerL1
                break
            }

            if(pointerL1.`val`<pointerL2.`val`){
                tail?.next = pointerL1
                pointerL1 = pointerL1.next
            }else {
                tail?.next = pointerL2
                pointerL2 = pointerL2.next
            }

            tail = tail?.next!!

        }
        return head.next

    }
}
fun main(args: Array<String>) {
    val l1 = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(4)
        }
    }
    val l2 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(4)
        }
    }
    val result = Solution().mergeTwoListsIterativeV2(l1,l2)
    var tmp = result
    while (tmp!=null){
        println(tmp.`val`)
        tmp = tmp.next
    }
}