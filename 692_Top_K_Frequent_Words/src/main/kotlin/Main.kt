import java.util.*

class Solution {
    fun topKFrequent(words: Array<String>, k: Int): List<String> {
        val hashMap = HashMap<String, Int>()

        val comparator = Comparator<String> { o1, o2 ->
            if(hashMap[o1] == hashMap[o2]){
                o2.compareTo(o1)
            }else
                hashMap[o1]!! - hashMap[o2]!!
        }
        val minHeap = PriorityQueue(comparator)

        words.forEach {
            hashMap.put(it, hashMap.getOrDefault(it,0)+1)
        }

        for(entry in hashMap){
            if(minHeap.size<k)
                minHeap.add(entry.key)
            else{
                val word = entry.key
                val peek = minHeap.peek()
                comparator.compare(word, peek)
                if(comparator.compare(word, peek)>0){
//                if(entry.value > hashMap.get(peek)!! || (entry.value == hashMap.get(peek)!! && peek.compareTo(word) > 0)){
                    minHeap.remove()
                    minHeap.add(word)
                }
            }
        }

        return mutableListOf<String>().apply {
            repeat(k){
                this.add(0, minHeap.remove())
            }
        }
    }
}