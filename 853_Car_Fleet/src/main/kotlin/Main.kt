import java.util.LinkedList

class Solution {
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val index = position.indices.sortedByDescending { position[it] }

        val queue = LinkedList<Int>().apply {
            index.forEach { this.offer(it) }
        }

        var count = 0

        while (queue.size>1){
            var maxP = Int.MAX_VALUE
            var minS = -1
            val size = queue.size

            var isAllSameSpeed = true
            repeat(size){
                val popIndex = queue.poll()
                val nextPos = position[popIndex] + speed[popIndex]
                if(nextPos<maxP){
                    isAllSameSpeed = isAllSameSpeed && if(minS==-1) true else minS==speed[popIndex]

                    maxP = nextPos
                    minS = speed[popIndex]
                    position[popIndex] = maxP
                    speed[popIndex] = minS


                    if(nextPos<target){
                        queue.offer(popIndex)
                    }else{
                        ++count
                    }

                }else{

                }
            }

            if(isAllSameSpeed)
                return count + queue.size
        }

        if(queue.size>=1)
            return count+1
        else
            return count
    }
}

class Solution {
    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
        val index = position.indices.sortedByDescending { position[it] }
        val arrTime = DoubleArray(position.size).apply {
            index.forEachIndexed { index, i ->  this[index] = (target-position[i]).toDouble() / speed[i]}
        }

        println(arrTime.contentToString())

        var tmp = Double.MIN_VALUE
        var acc = 0
        for(time in arrTime){
            if(time>tmp) {
                tmp = time
                ++acc
            }
        }
        return acc

    }
}