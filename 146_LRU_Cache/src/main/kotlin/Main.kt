/*
146. LRU Cache
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]
[null,null,null,1,null,2,null,1,3,4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4

Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.

 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class LRUCache(private val capacity: Int) {
    var hashmap:HashMap<Int, Node?> = hashMapOf()
    var head:Node? = null

    private fun removeNode(node: Node){
        if(node == head)
            head = null
        else{
            node.prev?.next = node.next
            node.next?.prev = node.prev
        }
    }

    private fun putNodeToHead(node:Node, isNewNode:Boolean){
        if(head == node)
            return

        //先拔掉
        if(!isNewNode && head!=null){
            removeNode(node)
        }

        //再接上
        if(head!=null) {
            //tail reconnect
            val tail = head?.prev
            tail?.next = node
            head?.prev = node

            node.next = head
            node.prev = tail
        }else{
            node.next = node
            node.prev = node
        }
        head = node
    }

    fun get(key: Int): Int {
        if(hashmap.get(key)!=null){
            val node = hashmap.get(key)!!
            putNodeToHead(node, false)
            return node.value
        }else
            return -1
    }

    fun put(key: Int, value: Int) {
        if(hashmap.get(key)!=null){
            val newOne:Node = hashmap.get(key)!!
            newOne.value = value
            putNodeToHead(newOne, false)
        }else{
            val newOne = Node(key, value)
            hashmap.put(key, newOne)

            //滿了 先排出
            if(hashmap.size == capacity) {
                val tail = head!!.prev!!
                removeNode(tail)
                hashmap.put(tail.key, null)
            }

            putNodeToHead(newOne, true)
        }
    }

    data class Node(
        val key: Int,
        var value:Int,
        var prev:Node? = null,
        var next:Node? = null
    )
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */