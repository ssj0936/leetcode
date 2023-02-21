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
    var count = 0
    var hashmap:HashMap<Int, Node> = hashMapOf()
    var head:Node? = null

    fun get(key: Int): Int {
        if(hashmap.containsKey(key)){
            val node = hashmap.get(key)!!
            node.next = head!!.next
            node.prev = head!!.prev
            head = node
            return node.value
        }else
            return -1
    }

    fun put(key: Int, value: Int) {
        if(hashmap.containsKey(key)){
            val newOne:Node = hashmap.get(key)!!
            newOne.value = value
            newOne.next = head!!.next
            newOne.prev = head!!.prev
            head = newOne
        }else{
            val newOne = Node(key, value)
            hashmap.put(key, newOne)

            if(count==0){
                head = newOne
                newOne.next = newOne
                newOne.prev = newOne
            }
            else{
                newOne.next = head!!.next
                newOne.prev = head!!.prev
                head = newOne
            }

            if(count == capacity) {
                val tail = head!!.prev
                tail!!.prev!!.next = tail.next
                tail!!.next!!.prev = tail.prev
            }
            else
                ++count
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