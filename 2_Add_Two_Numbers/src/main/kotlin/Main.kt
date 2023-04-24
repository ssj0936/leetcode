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
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

interface Sol{
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode?
}

class Solution:Sol {
    override fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var pointer1:ListNode? = l1
        var pointer2:ListNode? = l2
        val pointerResult = ListNode(-1)
        var pointer = pointerResult

        var carry = 0
        while (pointer1 != null || pointer2!=null || carry!=0){
            val subSum = (pointer1?.`val`?:0) + (pointer2?.`val`?:0) + carry
            carry = subSum/10
            val subResult = subSum%10

            val node = ListNode(subResult)
            pointer.next = node
            pointer = node

            pointer1 = pointer1?.next
            pointer2 = pointer2?.next
        }
        return pointerResult.next
    }
}