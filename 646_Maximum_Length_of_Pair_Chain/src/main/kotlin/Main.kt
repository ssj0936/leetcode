class Solution {
    fun findLongestChain(pairs: Array<IntArray>): Int {
        pairs.sortWith { o1, o2 ->
            //終點相同 起點大的排後
            if (o1[1] == o2[1])
                o2[0] - o1[0]
            else // 終點大的排後
                o1[1] - o2[1]
        }

        val dp = IntArray(pairs.size+1)
        for(i in pairs.indices){
            val dpI = i+1
            val notTake = dp[dpI-1]
            val take = 1 + run{
                val target = pairs[i][0]-1
                var left = 0
                var right = i-1
                while (left<=right){
                    val mid = left + (right - left)/2
                    if(target >= pairs[mid][1]){
                        left = mid+1
                    }else
                        right = mid-1
                }
                dp[left-1+1]
            }

            dp[dpI] = maxOf(notTake, take)
        }


        return dp[pairs.size]
    }
}