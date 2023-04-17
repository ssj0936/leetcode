fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    println('a'.toInt())
    println('z'.toInt())
}

interface Sol {
    fun numWays(words: Array<String>, target: String): Int
}

class Solution:Sol {

    override fun numWays(words: Array<String>, target: String): Int {
        val lengthEachWord = words[0].length
        //某一個位置 出現某一個字母 的字串個數
        val freq = Array(lengthEachWord){IntArray(26){0} }

        for(word in words){
            for(i in word.indices){
                val char = word[i]
                freq[i][char.toIndex()] +=1
            }
        }

        println(freq.contentDeepToString())

        val dp = Array(target.length){DoubleArray(lengthEachWord)}

        for(i in target.indices){
            val char = target[i]
            for(j in i until lengthEachWord){

//                if(i ==0 || j == 0)
//                    dp[i][j] = 0
//                else{
                    val valueWithoutThis:Double = if(j == 0) 0.0 else dp[i][j-1]
                    val valueWithThis:Double = (if(j ==0) 1.0 else if(i==0) 1.0 else dp[i-1][j-1]) * freq[j][char.toIndex()]
                    dp[i][j] = (valueWithoutThis + valueWithThis)% 1000000007
//                }
            }
        }
        println(dp.contentDeepToString())

        return (dp[target.lastIndex][lengthEachWord-1] % 1000000007).toInt()
    }
    private fun Char.toIndex():Int = this-'a'
}