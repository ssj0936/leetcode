import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val n = nums1.size
        //n*k [0] = sum value of nums1, [1] = min number of nums2
        val dp = Array(n+1){Array(k+1){LongArray(2)} }

        for(j in 1 .. k){
            for(i in 1 .. n){
                if(i<j) {
//                    dp[i][j] = longArrayOf(0, Long.MAX_VALUE)
                    continue
                }

                val indexI = i-1
                if(j==1){
                    dp[i][j] = longArrayOf((nums1[indexI] * nums2[indexI]).toLong(), nums2[indexI].toLong())
                }
                if(j==i) {
//                    dp[i][j] = longArrayOf((nums1[indexI] * nums2[indexI]).toLong(), nums2[indexI].toLong())
                    val value = nums1[indexI] + dp[i - 1][j - 1][0]
                    val min = if(i==1) nums2[indexI].toLong() else minOf(dp[i - 1][j - 1][1], nums2[indexI].toLong())
                    dp[i][j] = longArrayOf(value, min)
                    println("i,j = $i, value = $value, min = $min")
                }else{
                    val notTake = dp[i-1][j]
                    val take = dp[i-1][j-1]
                    val minOfTake = if(j==1) nums2[indexI].toLong() else minOf(dp[i-1][j-1][1], nums2[indexI].toLong())

                    if(notTake[0]*notTake[1] > (take[0]+nums1[indexI])*minOfTake){
                        dp[i][j] = notTake
                        println("dp[$i][$j] =, value = ${dp[i][j][0]}, min = ${dp[i][j][1]} (notTake)")

                    }else{
                        dp[i][j] = longArrayOf(take[0]+nums1[indexI], minOfTake)
                        println("dp[$i][$j] =, value = ${dp[i][j][0]}, min = ${dp[i][j][1]} (take)")
                    }
                }
            }
        }

        return dp[n][k][0] * dp[n][k][1]
    }
}

class Solution2 {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val indexes = IntArray(nums1.size){it}.sortedBy {-nums2[it]}
//        println(indexes)

        var sum = 0L
        val minHeap = PriorityQueue<Int>()
        var score = 0L
        for(i in indexes.indices){
            val index = indexes[i]
            if(i<k){
                sum += nums1[index]
                minHeap.offer(nums1[index])
                score = sum * nums2[index]
            }else if(i>=k && nums1[index]>minHeap.peek()){
                sum += nums1[index] - minHeap.poll()
                minHeap.offer(nums1[index])
                score = maxOf(score, sum * nums2[index])
            }
        }
        return score
    }
}