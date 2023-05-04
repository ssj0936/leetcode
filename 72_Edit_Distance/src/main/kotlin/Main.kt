fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun minDistance(word1: String, word2: String): Int
}

class Solution2:Sol {
    override fun minDistance(word1: String, word2: String): Int {
        //如果其中一個是空字串，distance就是另一個字串的length
        if(word1.isEmpty() || word2.isEmpty())
            return maxOf(word1.length, word2.length)

        //dp[i][j]代表：word1.subString(i..last) 和 word2.subString(j..last)之間的 distance

        val dp = Array(word1.length+1){IntArray(word2.length+1)}.apply {
//            this[word1.lastIndex][word2.lastIndex] = if(word1.last() == word2.last()) 1 else 0
            //建立初始條件：
            //如果某方字串為空，那distance就是對方字串長度
            for(i in 0 ..word1.length){
                //相當於是word1的子字串，與word2最後面切出來的空字串做distance，所以長度就是word1子字串的長度
                this[i][word2.length] = word1.lastIndex -i +1
            }

            for(j in 0 ..word2.length){
                this[word1.length][j] = word2.lastIndex - j +1
            }
        }

        //分三種情況：
        //word1和word2要replace，那就是兩個字串扣除該字元後的子問題 並distance+1，如果該字元一樣，甚至不用replace直接退一格
        //word1要delete，word1退一格，word2維持原地不動，distance+1
        //word1要insert，insert也是insert word2的開頭，不然幹嘛insert，所以word1原地不動，word2對到了所以往後一格，distance+1
        //最後取最小紀錄
        for(i in word1.lastIndex downTo 0){
            for(j in word2.lastIndex downTo 0){
                val replace = dp[i+1][j+1] + if(word1[i]==word2[j]) 0 else 1
                val delete = dp[i+1][j] +1
                val insert = dp[i][j+1] +1
                dp[i][j] = minOf(replace, delete, insert)
            }
        }
        return dp[0][0]
    }
}

class Solution:Sol {
    var minStep = Int.MAX_VALUE
    override fun minDistance(word1: String, word2: String): Int {
        val dp = Array(word1.length+1){IntArray(word2.length+1)}

        val lcs = StringBuffer()
        for(i in 1 ..word1.length){
            val index1 = i-1
            for(j in 1 .. word2.length){
                val index2 = j-1
                if(word1[index1]==word2[index2])
                    dp[i][j] = dp[i-1][j-1] + 1
                else
                    dp[i][j] = maxOf(dp[i-1][j], dp[i][j-1])

                if(i == word1.length && dp[i][j]>dp[i][j-1]){
                    lcs.append(word2[index2])
                }
            }
        }

        if(lcs.isNotEmpty())
            brutalForce(lcs.toString(), word1, word2, 0,0,0,0)
        else
            minStep = maxOf(word1.length, word2.length)

        println(lcs)
        return minStep
    }

    private fun brutalForce(lcs:String, word1: String, word2: String, lcsPtr:Int, word1Ptr:Int, word2Ptr:Int, processCnt:Int){
        if(lcsPtr>=lcs.length){
            val step1 = if(word1Ptr>word1.lastIndex) 0 else (word1Ptr - word1.lastIndex+1)
            val step2 = if(word2Ptr>word2.lastIndex) 0 else (word2Ptr - word2.lastIndex+1)

            val finalCnt = processCnt + maxOf(step1 ,step2)
            minStep = minOf(finalCnt, minStep)
            return
        }
        val lcsTarget = lcs[lcsPtr]
        for(i in word1Ptr..word1.lastIndex){
            for(j in word2Ptr .. word2.lastIndex){
                if(word1[i] == lcsTarget && word2[j] == lcsTarget) {
                    val step1 = i - word1Ptr
                    val step2 = j - word2Ptr
                    val subProcess = maxOf(step1 ,step2)
                    brutalForce(lcs, word1, word2, lcsPtr + 1, i + 1, j + 1, processCnt + subProcess)
                }
            }
        }
    }
}