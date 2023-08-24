import java.util.*

const val ONE = 0
const val TWO = 1
class Solution{
    fun maxProfit(prices: IntArray): Int {
//        if(prices.size<=1) return 0
//        if(prices.size == 2) return maxOf(0, prices[1]-prices[0])

        val dp = Array(prices.size+1){IntArray(2)}

        var min = Int.MAX_VALUE
        var oneMax = 0
        var closetValley = 1
        for(i in prices.indices){
            if(prices[i]<min){
                min = prices[i]
                closetValley = i
            }
            oneMax = maxOf(oneMax, prices[i] - min)
            val dpI = i+1
            val notTake = dp[dpI-1][TWO]
            val take = run{
                var max = 0
                if(closetValley!=1)
                    for(j in i downTo closetValley){
                        if(prices[i] - prices[j]>=0){
                            max = maxOf(max, (prices[i] - prices[j]) + dp[j+1][ONE])
                        }
                    }
                max
            }

            dp[dpI][ONE] = oneMax
            dp[dpI][TWO] = maxOf(take, notTake)
        }

        return maxOf(dp[prices.size][ONE], dp[prices.size][TWO])
    }
}

class SolutionGreedyWrong {
    fun maxProfit(prices: IntArray): Int {
        val maxHeap = PriorityQueue<Int>(compareBy { -it }).apply {add(0)}
        val mPrices = prices.toMutableList().apply { add(0) }
        var minI = 0

//        var subProfit =
        for(i in 1 .. mPrices.lastIndex){
            if(mPrices[i]<mPrices[i-1]){
                if(mPrices[i-1] - mPrices[minI] > 0)
                    maxHeap.add(mPrices[i-1] - mPrices[minI])
                minI = i
            }
        }

        val profit1 = maxHeap.remove()
        val profit2 = if(maxHeap.size>0) maxHeap.remove() else 0
        return profit1 + profit2
    }
}