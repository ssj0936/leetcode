class Solution {
    fun partitionString(s: String): Int {
        val dp = IntArray(s.length+1).apply {
            this[1] = 1
        }

        for(i in 1 .. s.lastIndex){
            val dpIndex = i+1
            val take = run{
                val shown = hashSetOf<Char>()
                var min = Int.MAX_VALUE
                for (j in i downTo 0){
                    if(!shown.contains(s[j])){
                        shown.add(s[j])
                        min = minOf(min, dp[j-1+1])
                    }else{
                        break
                    }
                }
                min+1
            }

            dp[dpIndex] = take
        }
        return dp[s.length]
    }
}

class SolutionGreedy {
    var count = 0
    fun partitionString(s: String): Int {
        helper(s, 0)
        return count
    }

    private fun helper(s:String, i:Int){
        if(i>=s.length) return

        val visited = BooleanArray(26)
        var pointer = i
        while (pointer<s.length && !visited[s[pointer]-'a']){
            visited[s[pointer]-'a'] = true
            ++pointer
        }

        ++count
        helper(s, pointer)
    }
}