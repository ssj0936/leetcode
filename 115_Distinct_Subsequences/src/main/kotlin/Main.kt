class Solution {
    fun numDistinct(s: String, t: String): Int {
        val dp = Array(s.length+1){LongArray(t.length+1)}.apply {
            this[0][0] = 1
//            for(i in 0 .. s.lastIndex)
//                this[i+1][0] = 1
        }

        for(i in 0 .. s.lastIndex){
            val dpI = i+1
            for(j in 0 ..t.lastIndex){
                val dpJ = j+1
                if(i<j)
                    dp[dpI][dpJ] = 0
                else if(s[i]!=t[j])
                    dp[dpI][dpJ] = 0
                else
                    dp[dpI][dpJ] = run{
                        var acc:Long = 0
                        for(k in dpI-1 downTo 0){
                            acc+=dp[k][dpJ-1]
                        }
                        acc
                    }/*dp[dpI-1][dpJ]+1*/
            }
        }
//
//        dp.forEach {
//            println(it.contentToString())
//        }

        val ans:Long = run{
            var sum:Long=0
            dp.forEach { sum += it.last() }
            sum
        }

        return if(ans > Int.MAX_VALUE)
            -1
        else
            ans.toInt()
    }
}

class Solution {
    fun numDistinct(s: String, t: String): Int {
        val dp = Array(s.length+1){LongArray(t.length+1)}.apply {
            this[0][0] = 1
            for(i in 0 .. s.lastIndex)
                this[i+1][0] = 1
        }

        for(i in 0 .. s.lastIndex){
            val dpI = i+1
            for(j in 0 ..minOf(i, t.lastIndex)){
                val dpJ = j+1
                val notTake = dp[dpI-1][dpJ]
                val take = if(s[i]==t[j]) dp[dpI-1][dpJ-1] else 0
                dp[dpI][dpJ] = take + notTake
            }
        }
        return if(dp[s.length][t.length]>Int.MAX_VALUE) -1 else dp[s.length][t.length].toInt()
    }
}
