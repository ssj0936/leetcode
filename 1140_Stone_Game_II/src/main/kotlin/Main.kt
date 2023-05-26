fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun stoneGameII(piles: IntArray): Int {
        val n = piles.size
        val dp = Array(n){IntArray(piles.size+1)}

        //sum of piles[i .. n-1]
        var sum = 0
        for(i in n-1 downTo 0){
            sum += piles[i]
            for(m in 1 .. n){
                if(i+(2*m)-1 >= n-1)
                    dp[i][m] = sum
                else{
                    var max = 0
                    for(s in 1 .. 2*m){
                        if(sum - dp[i+s][maxOf(s,m)] > max)
                            max = sum - dp[i+s][maxOf(s,m)]
                    }
                    dp[i][m] = max
                }
            }
        }

        return dp[0][1]
    }
}

class Solution2 {
    var max = 0
    fun stoneGameII(piles: IntArray): Int {
        dfs(piles, -1, 1, 0, true)
        return max
    }

//    private fun dfs(piles:IntArray, index:Int, m:Int, score:Int, isAlice:Boolean){
//        if(index == piles.lastIndex)
//            max = maxOf(max,score)
//
//        val count = 2*m
//        var subsum = 0
//        for(x in 1 .. count){
//            if(index+x > piles.lastIndex) break
//            subsum += piles[index+x]
//            dfs(piles, index+x, maxOf(m,x), if(isAlice) (score+subsum) else score, !isAlice)
//        }
//    }
//    private fun dfs(piles:IntArray, index:Int, m:Int, score:Int, isAlice:Boolean){
//        if(index == piles.lastIndex)
//            max = maxOf(max,score)
//
//        val count = 2*m
//        var subsum = 0
//        for(x in 1 .. count){
//            if(index+x > piles.lastIndex) break
//            subsum += piles[index+x]
//
//            val newM = maxOf(m,x)
//            val countBobBanTake = 2*newM
//            val maxIndexBobTake = minOf(piles.lastIndex, index+x+countBobBanTake)
//
//            val newNewM = maxOf(newM, countBobBanTake)
//            dfs(piles, maxIndexBobTake, newNewM, if(isAlice) (score+subsum) else score, isAlice)
//        }
//    }

    private fun dfs(piles:IntArray, index:Int, m:Int, score:Int, isAlice:Boolean){
        if(index == piles.lastIndex)
            max = maxOf(max,score)

        val count = 2*m
        var subsum = 0
        for(x in 1 .. count){
            if(index+x > piles.lastIndex) break
            subsum += piles[index+x]

            val newM = maxOf(m,x)
            val countBobBanTake = 2*newM
            val maxIndexBobTake = minOf(piles.lastIndex, index+x+countBobBanTake)

            val newNewM = maxOf(newM, countBobBanTake)
            dfs(piles, maxIndexBobTake, newNewM, if(isAlice) (score+subsum) else score, isAlice)
        }
    }
}