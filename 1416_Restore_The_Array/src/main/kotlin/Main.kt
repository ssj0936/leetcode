fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val test = "050".toInt()
    println(test)
}

interface Sol{
    fun numberOfArrays(s: String, k: Int): Int
}

class Solution:Sol {
    private val modulo = 1000000000+7
    override fun numberOfArrays(s: String, k: Int): Int {
        val dp = Array(s.length){IntArray(s.length)}
        val kInString = run {
            var string= ""
            var newK = k
            while (newK!=0){
                string = (newK%10).toString() + string
                newK /= 10
            }
            string
        }.length

        for(i in s.lastIndex downTo 0 ){
            for(j in i .. s.lastIndex){
                if(isLeadingZero(s, i, j))
                    dp[i][j] = 0
                else{
                    val noSplit = if(isInRange(k,kInString, s, i, j)) 1 else 0
                    val split = run {
                        var accumulator = 0
                        for(pivot in i until j){
//                            accumulator += (dp[i][pivot] * dp[pivot+1][j])
                            if(!isInRange(k,kInString, s, i, pivot)) break
//                            if(s.substring(i..pivot).toInt() !in 1..k) break
                            accumulator += dp[pivot+1][j]
                        }
                        accumulator
                    }
//                    println("${s.substring(i..j)} dp[$i][$j] = noSplit:$noSplit, split:$split")
                    dp[i][j] = (noSplit + split) % modulo
                }
//                println("${s.substring(i..j)} dp[$i][$j] = ${dp[i][j]}")
            }
        }
        return dp[0][s.lastIndex]
    }

    private fun isLeadingZero(s: String, i:Int, j:Int):Boolean{
        return s[i]=='0' && i!=j
    }

//    private fun isInRange(k: String, s: String, i:Int, j:Int):Boolean{
//        if(j-i+1>9) return false
//        return s.substring(i..j).toInt() in 1..k
//    }

    private fun isInRange(k: Int, kStringSize: Int, s: String, i:Int, j:Int):Boolean{
        if(j-i+1 > kStringSize) return false
        return s.substring(i..j).toInt() in 1..k
    }
}

class SolutionBetter:Sol {
    private val modulo = 1000000000+7
    override fun numberOfArrays(s: String, k: Int): Int {
        val dp = IntArray(s.length)

        for(i in s.lastIndex downTo 0){
            if(isLeadingZero(s, i, s.lastIndex))
                dp[i] = 0
            else{
                val noSplit = if(isInRange(k, s, i, s.lastIndex)) 1 else 0
                val split = run {
                    var accumulator = 0
                    for(pivot in i until  s.lastIndex){
//                            accumulator += (dp[i][pivot] * dp[pivot+1][j])
                        if(!isInRange(k, s, i, pivot)) break
//                            if(s.substring(i..pivot).toInt() !in 1..k) break
                        accumulator = (accumulator+dp[pivot+1])%modulo
                    }
                    accumulator
                }
//                    println("${s.substring(i..j)} dp[$i][$j] = noSplit:$noSplit, split:$split")
                dp[i]= (noSplit + split) % modulo
            }
        }

        return dp[0]
    }

    private fun isLeadingZero(s: String, i:Int, j:Int):Boolean{
        return s[i]=='0' && i!=j
    }

//    private fun isInRange(k: String, s: String, i:Int, j:Int):Boolean{
//        if(j-i+1>9) return false
//        return s.substring(i..j).toInt() in 1..k
//    }

    private fun isInRange(k: Int, s: String, i:Int, j:Int):Boolean{
        if(j-i+1 > 9) return false
        return s.substring(i..j).toInt() in 1..k
    }
}