/*
19. Remove Nth Node From End of List
Medium
Given the head of a linked list, remove the nth node from the end of the list and return its head.
https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz


Follow up: Could you do this in one pass?
 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

interface Sol{
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode?
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Solution:Sol {
    override fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var newhead = head
        if(newhead?.next==null) return null

        var pivotFirst:ListNode? = ListNode(Int.MAX_VALUE).apply {
            next = head
        }
        var pivotRemove:ListNode? = ListNode(Int.MAX_VALUE).apply {
            next = head
        }
        var pivotThird:ListNode? = ListNode(Int.MAX_VALUE).apply {
            next = pivotRemove
        }
        var delayForSecond = n-1
//        var delayForThird = n

        while (pivotFirst?.next != null){
            //pivotFirst
            pivotFirst = pivotFirst.next

            //pivotSecond
            if(delayForSecond==0) {
                pivotRemove = pivotRemove?.next
                pivotThird = pivotThird?.next
            }else
                delayForSecond-=1

//            //pivotThird
//            if(delayForSecond==0){
//                pivotThird = pivotThird?.next
//            }else{
//                delayForThird-=1
//            }
        }
        //remove
        pivotThird?.next = pivotRemove?.next
        if(head == pivotRemove)
            newhead = pivotRemove?.next

        return newhead
    }
}