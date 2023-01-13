fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun reverseList(head:ListNode?):ListNode?
}

class ListNode(var `val`:Int){
    var next:ListNode? = null
}

class Solution():Sol{
    override fun reverseList(head:ListNode?):ListNode?{
        if(head?.next == null) return head

        var p1:ListNode? = null
        var p2:ListNode? = head
//        var p3:ListNode? = head.next

        while (p2!=null){
            val tmp:ListNode? = p2.next
            p2.next = p1
            p1 = p2
            p2 = tmp
//            p3 = p3?.next
        }

        return p1
    }
}

class SolutionRecursive():Sol{
    override fun reverseList(head:ListNode?):ListNode?{
        if(head?.next == null) return head

        return foo(null,head)
    }

    private fun foo(p1:ListNode?, p2:ListNode?):ListNode?{
        return if(p2 == null)
            p1
        else {
            val tmp = p2.next
            p2.next = p1
            foo(p2, tmp)
        }
    }
}