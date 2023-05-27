import java.math.RoundingMode
import java.text.DecimalFormat

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        if(k==0 || n-k >=maxPts) return 1.0

        //dp[i] = 走到<=i位置的機率
        val dp = DoubleArray(k+maxPts).apply {
            this[0] = 1.0
        }

        for(i in 1 .. dp.lastIndex){
            val lowerBound = minOf(i-1, k-1)
            if(i<=maxPts)
                dp[i] = dp[i-1] + dp[lowerBound]/maxPts
            else
                dp[i] = dp[i-1] + (dp[lowerBound]-dp[i-maxPts-1])/maxPts
        }
        return roundOffDecimal((dp[n]-dp[k-1])/(dp.last() - dp[k-1]))
    }

    private fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.#####")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}

class Solution2 {
    fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        if(n >= k + maxPts -1) return 1.0

        val dp = IntArray(k-1+maxPts +1)

        var sumUp = 0
        var sumAll = 0
        for(i in 1 .. dp.lastIndex){
            /*if(i<=maxPts){
                dp[i] = run{
                    var sum = 1
                    for(j in 1 .. maxPts){
                        if(i - j <0) break
                        sum+=dp[i-j]
                    }
                    sum
                }
            }else */if(i<k){
                dp[i] = run{
                    var sum = if(i<=maxPts) 1 else 0
                    for(j in 1 .. maxPts){
                        if(i - j <0) break
                        sum+=dp[i-j]
                    }
                    sum
                }
            }else{
                val start = i-k+1
                dp[i] = run{
                    println("i:$i")
                    var str = ""
                    var sum = if(i<=maxPts) 1 else 0
                    for(j in start .. maxPts){
                        if(i - j <0) break
                        str +="dp[${i-j}] + "
                        sum+=dp[i-j]
                    }
                    println(str)
                    sum
                }
            }

            if(i in k .. n)
                sumUp += dp[i]
            if(i >= k)
                sumAll += dp[i]
        }

        println("$sumUp / $sumAll")

        println(dp.contentToString())
        return 0.0
    }

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.#####")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }

}