import java.util.*

/*
92. Reverse Linked List II(Medium)

Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5], left = 2, right = 4
Output: [1,4,3,2,5]

Example 2:
Input: head = [5], left = 1, right = 1
Output: [5]

Constraints:

The number of nodes in the list is n.
1 <= n <= 500
-500 <= Node.val <= 500
1 <= left <= right <= n

Follow up: Could you do it in one pass?

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
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        if(head == null) return head
        if(left == right) return head

        //start from 1
        var pointerIndex = 1
        var pointer = head

        //reverse from head
        if(left == 1){
            var newHead: ListNode? = null
            pointer = newHead
            var reversePointer = head
            val stack = Stack<ListNode>()
            repeat(right - left + 1) {
                stack.push(reversePointer)
                reversePointer = reversePointer?.next
            }

            while (stack.isNotEmpty()){
                if(newHead==null){
                    newHead = stack.pop()
                    pointer = newHead
                }else{
                    pointer?.next = stack.pop()
                    pointer = pointer?.next
                }
            }
            pointer?.next = reversePointer

            return newHead
        }else {
            //pointer 指向要轉前一個node
            repeat(left - pointerIndex -1) {
                pointer = pointer?.next
            }

            //另一個pointer指向reverse段
            var reversePointer = pointer?.next
            val stack = Stack<ListNode>()
            repeat(right - left + 1) {
                stack.push(reversePointer)
                reversePointer = reversePointer?.next
            }

            //邊push邊接起來
            while (stack.isNotEmpty()){
                pointer?.next = stack.pop()
                pointer = pointer?.next
            }
            pointer?.next = reversePointer
            return head
        }
    }

    //往前面插一個假的head 把解法一的兩個狀況收斂成一個
    fun reverseBetween2(head: ListNode?, left: Int, right: Int): ListNode? {
        if(head == null) return head
        if(left == right) return head

        var newHead: ListNode? = ListNode(999).apply { next = head }
        var pointer = newHead

        //pointer 指向要轉前一個node
        repeat(left-1) {
            pointer = pointer?.next
        }

        //另一個pointer指向reverse段
        var reversePointer = pointer?.next
        val stack = Stack<ListNode>()
        repeat(right - left + 1) {
            stack.push(reversePointer)
            reversePointer = reversePointer?.next
        }

        //邊push邊接起來
        while (stack.isNotEmpty()){
            pointer?.next = stack.pop()
            pointer = pointer?.next
        }
        pointer?.next = reversePointer
        return newHead?.next
    }

    //不使用stack的話，需要多幾個pointer來實作一次過的解
    fun reverseBetween3(head: ListNode?, left: Int, right: Int): ListNode? {
        if(head == null) return head
        if(left == right) return head

        var newHead: ListNode? = ListNode(999).apply { next = head }
        var breakLeftPointer = newHead

        //pointer 指向要轉前一個node
        repeat(left-1) {
            breakLeftPointer = breakLeftPointer?.next
        }

        //另一個pointer指向reverse段
        var reversePointer = breakLeftPointer?.next
        //reverse段的尾巴
        var tail = reversePointer
        //reverse斷後的第一點 需要記錄下來接回去
        var breakRightPointer:ListNode? = reversePointer?.next
        var tmpIndex = left
        while(++tmpIndex<=right) {
            val tmp = breakRightPointer?.next
            breakRightPointer?.next = reversePointer
            reversePointer = breakRightPointer
            breakRightPointer = tmp

//            println("reversePointer:${reversePointer?.`val`}, breakRightPointer:${breakRightPointer?.`val`}, tail:${tail?.`val`},")
        }
        breakLeftPointer?.next = reversePointer
        tail?.next = breakRightPointer
        return newHead?.next
    }

}
fun main(args: Array<String>) {
    val list = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
                next = ListNode(4).apply {
                    next = ListNode(5)
                }
            }
        }
    }
    var result = Solution().reverseBetween3(list,1,4)

    val sb = StringBuilder()
    while (result!=null){
        sb.append("${result.`val`},")
        result = result.next
    }
    println(sb)
}