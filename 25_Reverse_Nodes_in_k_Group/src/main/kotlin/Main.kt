import java.util.*

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
class SolutionTimeK {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if(k==1) return head

        val stack = LinkedList<ListNode>()

        val dummy = ListNode(0)
        var processPointer = dummy

        var pointer = head
        var headPointer = pointer
        while (pointer!=null){
            stack.push(pointer)
            pointer = pointer.next
            if(stack.size == k){
                while (stack.isNotEmpty()){
                    processPointer.next = stack.pop()
                    processPointer = processPointer.next!!
                }

                headPointer = pointer
            }
        }

        processPointer.next = headPointer
        return dummy.next
    }
}

class Solution {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        if(k==1) return head

        val dummy = ListNode(0)
        var processPointer: ListNode? = dummy

        var pointer1: ListNode? = head
        var pointer2: ListNode? = null
        var pointer3: ListNode? = pointer1?.next
        var nail: ListNode? = head
        var count = 0

        while (pointer1!=null){
            pointer1.next = pointer2
            pointer2 = pointer1
            pointer1 = pointer3
            pointer3 = pointer3?.next
            ++count

            if(count == k){
                processPointer?.next = pointer2
                processPointer = nail

                pointer2 = null
                nail = pointer1
                count = 0
            }
        }

        if(count!=0){
            pointer3 = pointer2?.next
            while (pointer1!=nail){
                pointer2?.next = pointer1
                pointer1 = pointer2
                pointer2 = pointer3
                pointer3 = pointer3?.next
            }

            processPointer?.next = pointer1
        }


        return dummy.next
    }
}