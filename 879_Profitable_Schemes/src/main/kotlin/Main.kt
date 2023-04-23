fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

interface Sol {
    fun profitableSchemes(n: Int, minProfit: Int, group: IntArray, profit: IntArray): Int
}

class Solution:Sol {
    private val modulo = 1000000007

    override fun profitableSchemes(n: Int, minProfit: Int, group: IntArray, profit: IntArray): Int {
        val dp = Array(n+1){Array(minProfit+1){ IntArray(group.size+1) } }
        dp[0][0][0] = 1

        for(g in 1 .. group.size){//第幾個工作
            val groupIndex = g-1
            for(i in 0 .. n){//全部有幾個人
                for(p in 0 .. minProfit){//最少能賺多少
                    dp[i][p][g] = (dp[i][p][g-1] + if(i >= group[groupIndex]) dp[i-group[groupIndex]][maxOf(0,p-profit[groupIndex])][g-1] else 0) % modulo
                }
            }
        }

        var result = 0
        for(i in 0 .. n){
            result = (result + dp[i][minProfit][group.size])%modulo
        }

        return result
    }
}

class SolutionBetterSpace:Sol {
    private val modulo = 1000000007

    override fun profitableSchemes(n: Int, minProfit: Int, group: IntArray, profit: IntArray): Int {
        val dp = Array(n+1){IntArray(minProfit+1)/*{ IntArray(group.size+1) }*/ }
        dp[0][0] = 1

        for(g in 1 .. group.size){//第幾個工作
            val groupIndex = g-1
            for(i in n downTo  0){//全部有幾個人
                for(p in minProfit downTo  0){//最少能賺多少
                    dp[i][p] = (dp[i][p] + if(i >= group[groupIndex]) dp[i-group[groupIndex]][maxOf(0,p-profit[groupIndex])] else 0) % modulo
                }
            }
        }

        var result = 0
        for(i in 0 .. n){
            result = (result + dp[i][minProfit])%modulo
        }

        return result
    }
}