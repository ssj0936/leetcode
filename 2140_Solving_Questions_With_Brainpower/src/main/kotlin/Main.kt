fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun mostPoints(questions: Array<IntArray>): Long
}

//要照順序
class SolutionDP:Sol {
    override fun mostPoints(questions: Array<IntArray>): Long {
        val dp = LongArray(questions.size).apply {
            this[0] = questions[0][0].toLong()
        }

        for(i in 1 .. questions.lastIndex){
            val point = questions[i][0].toLong()
            val notPick = dp[i-1]
            val pick = run{
                var max = point
                for(j in i-1 downTo 0){
                    if(j + questions[j][1] < i && dp[j]+point>max)
                        max = dp[j]+point
                }
                max
            }
            dp[i] = maxOf(pick, notPick)
        }
        return dp[questions.lastIndex]
    }
}

class Solution{
    fun mostPoints(questions: Array<IntArray>): Long {
        val dp = LongArray(questions.size).apply {
            this[lastIndex] = questions[lastIndex][0].toLong()
        }

        for(i in questions.lastIndex-1 downTo 0){
            val point = questions[i][0].toLong()
            val shouldSkip =questions[i][1]
            val notPick = dp[i+1]
            val pick = run{
                val startIndex = i + shouldSkip +1
                point + if(startIndex > questions.lastIndex)0 else dp[startIndex]
            }

            dp[i] = maxOf(pick, notPick)
        }

        return dp[0]
    }
}