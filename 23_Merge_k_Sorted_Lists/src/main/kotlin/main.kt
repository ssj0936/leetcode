import java.lang.StringBuilder

/*
23. Merge k Sorted Lists(Hard)
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
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
    /*
    解法一
    算暴力法吧，每次掃過一遍linkedList的root們把最小的取出來接在新的root上，並把該root往後退一位，直到array全空
    假設每個list個數為N，每一輪會比對k次，O(k*N*N)

     */

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        //空list
        if(lists.isEmpty()) return null

        //list裡面 至少有一個不是空list
        var smallestNode:ListNode? = null
        var smallestIndex:Int = -1

        for((index,node) in lists.withIndex()){
            if(node!=null){
                if(smallestNode==null || (smallestNode!=null && node.`val`<smallestNode.`val`)) {
                    smallestNode = node
                    smallestIndex = index
                }
            }
        }
        var root:ListNode? = smallestNode?.apply {
            lists[smallestIndex] = lists[smallestIndex]?.next
            this.next = mergeKLists(lists)
        }
        return root
    }

    /*
    divide and conquer

    把k個對半切分兩組，持續到兩組為0-1個linkedlist為止，再分別擊破做排序
    假設每個list個數為N，第一次做k/2次比較 對象是2N，剩下k/2組 並且每組長度增長為兩倍(4N)
    第二次會做k/4次比較 對象是4N
    第二次會做k/8次比較 對象是8N
    ...
    最後做k/2^logK次比較，對象是2^logK*N
    O(KN + KN + .... + KN) = O(logK*N)
     */
    fun mergeKListsDC(lists: Array<ListNode?>,start:Int = 0, end:Int = lists.lastIndex): ListNode? {
        if(lists.isEmpty()) return null
        if(end == start) return lists[start]
        else{
            val mid = (start + end)/2
            val left = mergeKListsDC(lists,start, mid)
            val right = mergeKListsDC(lists,mid+1, end)
            return merge2List(left, right)
        }
    }

    private fun merge2List(listA: ListNode?, listB: ListNode?):ListNode?{
        var rootA = listA
        var rootB = listB

        val dummy = ListNode(Int.MIN_VALUE)
        var pivot:ListNode = dummy

        while (rootA!=null && rootB!=null){
            var smallOne:ListNode?
            if(rootA.`val`<rootB.`val`){
                smallOne = rootA
                rootA = rootA.next
            }else{
                smallOne = rootB
                rootB = rootB.next
            }
            pivot.next = smallOne
            pivot = pivot.next!!
        }

        if(rootA!=null)
            pivot.next = rootA

        if(rootB!=null)
            pivot.next = rootB

        return dummy.next
    }
}

//Input: lists = [[1,4,5],[1,3,4],[2,6]]

fun main(args: Array<String>) {
    val linkedList01 = ListNode(1).apply {
        next = ListNode(4).apply {
            next = ListNode(5)
        }
    }

    val linkedList02 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(4)
        }
    }

    val linkedList03 = ListNode(2).apply {
        next = ListNode(6)
    }

    val input = arrayOf<ListNode?>(linkedList01,linkedList02,linkedList03)
    val result = Solution().mergeKListsDC(input)
    var pivot = result
    val stringBuilder = StringBuilder()
    while (pivot != null){
        stringBuilder.append(pivot.`val`).append(",")
        pivot = pivot.next
    }
    println(stringBuilder)
}