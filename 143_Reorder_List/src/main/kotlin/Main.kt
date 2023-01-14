fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class ListNode(var `val`:Int){
    var next: ListNode? = null
}

class Solution{
    fun reorderList(head:ListNode?):Unit{
        //get list count
        val listSize = run {
            var acc = 0
            var pointer = head
            while (pointer!=null){
                ++acc
                pointer = pointer.next
            }
            acc
        }

        //if list count == 0 or 1, no need to process
        if(listSize == 0 || listSize==1) return

        //
        var count:Int = if(listSize%2 == 0) listSize/2 else listSize/2 +1
        val anotherHeadPointer = run{
            var pointer = head
            var tmp:ListNode?
            while (count-- != 0) {
                tmp = pointer
                pointer = pointer!!.next

                if(count == 0)
                    tmp?.next = null
            }
            pointer
        }

        var newAnotherHeadPointer = reverseLinkList(anotherHeadPointer)

        var newHead = head
        while (newAnotherHeadPointer!=null){
            val tmpHead = newHead!!.next
            val tmpNewHead = newAnotherHeadPointer.next

            newHead.next = newAnotherHeadPointer
            newAnotherHeadPointer.next = tmpHead
            newHead = tmpHead
            newAnotherHeadPointer = tmpNewHead
        }
    }

    private fun reverseLinkList(head:ListNode?):ListNode?{
        if(head?.next == null)
            return head

        var pointer1:ListNode? = null
        var pointer2:ListNode? = head
        var pointer3:ListNode? = pointer2!!.next

        while(pointer2 != null){
            pointer2.next = pointer1
            pointer1 = pointer2
            pointer2 = pointer3
            pointer3 = pointer3?.next
        }

        return pointer1
    }
}