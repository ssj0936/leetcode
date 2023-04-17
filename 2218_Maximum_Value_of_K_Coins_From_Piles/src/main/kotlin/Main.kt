fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int
}

class Solution:Sol {
    var max = 0
    override fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        val sum = Array<MutableList<Int>>(piles.size){ mutableListOf()}
        for(i in piles.indices){
            var acc = 0
            sum[i].add(acc)
            for(j in piles[i].indices){
                acc+=piles[i][j]
                sum[i].add(acc)
            }
        }

        foo(sum, 0,k,0)
        return max
    }

    private fun foo(dp:Array<MutableList<Int>>, pileIndex:Int, k: Int, currSum:Int){
//        if(k<0)
//            return
        if(pileIndex > dp.lastIndex)
            return

        for(i in dp[pileIndex].indices){
            if(k-i == 0) {
                max = Math.max(currSum + dp[pileIndex][i], max)
                break
            }else if(k-i < 0){
                break
            }else
                foo(dp, pileIndex+1, k-i, currSum+dp[pileIndex][i])
        }
    }
}

class SolutionBetter:Sol{
    override fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        //dp[i][k]代表，從第0到第i個piles中取k個coin的最大值
        //其中針對每個pile i可以選擇要從裡面取 或是不從裡面取
        //不從裡面取的話 就是維持原樣dp[i-1][k]
        //要從裡面取的話 可能會取1個 那就是pile[0] + dp[i-1][k-1]，可能取兩個那就是(pile[0]+pile[1])+ dp[i-1][k-2]
        val dp = Array(piles.size){IntArray(k+1)}

        for(localK in 0 .. k){
            for(i in 0 .. piles.lastIndex){
                if(localK==0)
                    dp[i][localK] = 0
                else{
                    val valueNotTakeFromThisPile = if(i==0) 0 else dp[i-1][localK]
                    val valueTakeFromThisPile = kotlin.run {
                        var acc = 0
                        var max = 0
                        val availableCount = Math.min(localK, piles[i].size)
                        for(index in 0 until availableCount){
                            acc += piles[i][index]
                            val value = acc + (if(i==0) 0 else dp[i-1][localK-(index+1)])
                            max = Math.max(max, value)
                        }
                        max
                    }
                    dp[i][localK] = Math.max(valueNotTakeFromThisPile, valueTakeFromThisPile)
                }
            }
        }

        return dp[piles.lastIndex][k]
    }

}

class SolutionBest:Sol{
    override fun maxValueOfCoins(piles: List<List<Int>>, k: Int): Int {
        //dp[i][k]代表，從第0到第i個piles中取k個coin的最大值
        //其中針對每個pile i可以選擇要從裡面取 或是不從裡面取
        //不從裡面取的話 就是維持原樣dp[i-1][k]
        //要從裡面取的話 可能會取1個 那就是pile[0] + dp[i-1][k-1]，可能取兩個那就是(pile[0]+pile[1])+ dp[i-1][k-2]
        val dp = Array(piles.size+1){IntArray(k+1)}

        for(i in 0 .. piles.lastIndex){
            for(localK in 0 .. k){
                val valueNotTakeFromThisPile = dp[i][localK]
                var acc = 0
                var max = 0
                val availableCount = Math.min(localK, piles[i].size)
                for(index in 1 ..  availableCount){
//                    if(index>0)
                        acc += piles[i][index-1]
                    max = Math.max(max, acc +dp[i][localK-index])
//                    dp[i+1][localK] = Math.max(dp[i+1][localK], acc +dp[i][localK-index])
                }
                dp[i+1][localK] = Math.max(valueNotTakeFromThisPile, max)
            }
        }

        return dp[piles.size][k]
    }

}