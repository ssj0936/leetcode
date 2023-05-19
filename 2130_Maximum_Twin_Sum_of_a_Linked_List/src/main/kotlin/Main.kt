fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
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

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
class Solution {
    fun pairSum(head: ListNode?): Int {
        var ptr = head
        val list = mutableListOf<Int>()
        while (ptr!=null){
            list.add(ptr.`val`)
            ptr = ptr.next
        }

        val size = list.lastIndex
        var max = 0
        for(i in 0 .. (size/2)){
            max = maxOf(max, list[i]+list[size-i])
        }

        return max
    }
}

class SolutionSpace1 {
    fun pairSum(head: ListNode?): Int {
        var max = 0
        var fast:ListNode? = head
        var slow:ListNode? = head

        while (fast!=null){
            fast = fast.next!!.next
            slow = slow!!.next
        }

        var prev:ListNode? = null
        while (slow!=null){
            val tmp = slow.next
            slow.next = prev
            prev = slow
            slow = tmp
        }

        var ptr1 = head
        var ptr2 = prev
        while (ptr2!=null){
            max = maxOf(max, ptr1!!.`val` + ptr2!!.`val`)
            ptr1 = ptr1.next
            ptr2 = ptr2.next
        }
        return max
    }
}