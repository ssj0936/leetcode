import java.util.PriorityQueue

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}
class Solution {
    fun longestObstacleCourseAtEachPosition(obstacles: IntArray): IntArray {

        val binaryList = mutableListOf<Int>()
        fun binarySearch(target:Int):Int{
            var head = 0
            var tail = binaryList.lastIndex
            while (head<=tail){
                val mid = (head + tail)/2
                if(binaryList[mid] == target) head = mid+1
                else if(target < binaryList[mid]) tail = mid-1
                else head = mid+1
            }

            return head
        }

        val result = IntArray(obstacles.size)

        obstacles.forEachIndexed { index, obstacle ->
            val insertPosition = binarySearch(obstacle)
            if(insertPosition == binaryList.size){
                binaryList.add(obstacle)
                result[index] = binaryList.size
            }else{
                binaryList[insertPosition] = obstacle
                result[index] = insertPosition+1
            }
        }
        return result
    }
}


class SolutionDP {
    fun longestObstacleCourseAtEachPosition(obstacles: IntArray): IntArray {
        val dp = IntArray(obstacles.size).apply { this[0]=1 }

        for(i in 1 .. obstacles.lastIndex){
            val value = obstacles[i]
            val maxPrev:Int = run {
                var maxIndex = -1
                var maxValue = -1
                for(j in 0 until i){
                    if(obstacles[j]<=value && dp[j]>maxValue){
                        maxIndex = j
                        maxValue = dp[j]
                    }
                }
                maxIndex
            }

            dp[i] = 1 + (if(maxPrev == -1) 0 else dp[maxPrev])
        }

        return dp
    }
}