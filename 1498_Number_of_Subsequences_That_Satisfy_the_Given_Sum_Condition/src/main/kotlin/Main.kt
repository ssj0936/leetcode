fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun numSubseq(nums: IntArray, target: Int): Int
}

class Solution2Pointer:Sol{
    private val mod = 1000000007
    override fun numSubseq(nums: IntArray, target: Int): Int {
        nums.sort()
        var head = 0
        var tail = nums.lastIndex

        val powerTable = IntArray(nums.size).apply {
            this[0] = 1
            for(i in 1 .. nums.lastIndex){
                this[i] = this[i-1]*2 % mod
            }
        }
        var cnt = 0
        while (head<=tail){
            while (tail>head && nums[head]+nums[tail]>target)
                --tail

            if(nums[head]+nums[tail]<=target) {
                cnt = (cnt + powerTable[tail - head]) % mod
                ++head
            }
        }
        return cnt
    }
}

class Solution:Sol {
    override fun numSubseq(nums: IntArray, target: Int): Int {
        fun isSatisfy(min:Int, max:Int):Boolean = min+max<=target

        val dp = Array(nums.size){Array<Pair<Int, Pair<Int, Int>>?>(nums.size){null} }

        for(i in nums.lastIndex downTo 0){
            for(j in i .. nums.lastIndex){
                if(i == j){
                    dp[i][j] = Pair(if(isSatisfy(nums[i],nums[j])) 1 else 0, Pair(nums[i], nums[i]))
                }
                else{
                    val newRange = minOf( dp[i][j-1]!!.getMin(), nums[j]) to maxOf(dp[i][j-1]!!.getMax(), nums[j])
                    val selfIsQualified = isSatisfy(nums[j],nums[j])
                    val newRangeIsQualify = isSatisfy(minOf( dp[i][j-1]!!.getMin(), nums[j]) , maxOf(dp[i][j-1]!!.getMax(), nums[j]))

                    var tmpCnt = dp[i+1][j]!!.getSubsequenceCnt()
//                    for(k in i+1 .. j){
//                        tmpCnt += dp[k][j]!!.getSubsequenceCnt()
//                    }
                    val cntWithThis = if(j > i+1) (dp[i][j-1]!!.getSubsequenceCnt() - dp[i+1][j-1]!!.getSubsequenceCnt()) else 0

                    val newCnt = dp[i][j-1]!!.getSubsequenceCnt() + dp[i+1][j]!!.getSubsequenceCnt() - dp[i+1][j-1]!!.getSubsequenceCnt()
//                    val newCnt = /*dp[i][j-1]!!.getSubsequenceCnt()*/ + /*(if(selfIsQualified) 1 else 0) + *//*(if(newRangeIsQualify) dp[i][j-1]!!.getSubsequenceCnt() else 0)*/ tmpCnt + cntWithThis
                    dp[i][j] = Pair(
                        newCnt,
                        newRange
                    )
                }
            }
        }
        return dp[0][nums.lastIndex]!!.getSubsequenceCnt()
    }

    private fun Pair<Int, Pair<Int, Int>>.getMax() = this.second.second
    private fun Pair<Int, Pair<Int, Int>>.getMin() = this.second.first
    private fun Pair<Int, Pair<Int, Int>>.getSubsequenceCnt() = this.first
}

class SolutionDP:Sol {
    override fun numSubseq(nums: IntArray, target: Int): Int {
        fun isSatisfy(min:Int, max:Int):Boolean = min+max<=target

        //0 cnt, 1 min, 2 max
        val dp = Array(nums.size){IntArray(3)}
        var min = Int.MAX_VALUE
        var max = Int.MIN_VALUE
        for(i in 0 .. nums.lastIndex){
            min = minOf(min, nums[i])
            max = maxOf(max, nums[i])

            val notPick = dp[i-1][0]
            val pick = dp[i-1][0]*2 + if(minOf())
        }

    }

    private fun Pair<Int, Pair<Int, Int>>.getMax() = this.second.second
    private fun Pair<Int, Pair<Int, Int>>.getMin() = this.second.first
    private fun Pair<Int, Pair<Int, Int>>.getSubsequenceCnt() = this.first
}