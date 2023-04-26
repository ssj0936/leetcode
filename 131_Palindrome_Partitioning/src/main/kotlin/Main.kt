fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

interface Sol{
    fun partition(s: String): List<List<String>>
}

class SolutionBest:Sol{
    private val result = mutableListOf<MutableList<String>>()
    override fun partition(s: String): List<List<String>>{
        helper(s, 0, s.lastIndex, mutableListOf())
        return result
    }

    fun helper(s: String, start: Int, end: Int, res:MutableList<String>){
        if(start>end)
            result.add(res)

//        for(i in start .. end){
            if(isPalindrome(s, start, end)) {
                helper(s, end, end, res.toMutableList().apply { add(s.substring(start..end)) })
            }
            helper(s, start, end, res)
//        }
    }

    fun isPalindrome(s:String, start: Int, end: Int):Boolean{
        var left = start
        var right = end
        while(left<right){
            if(s[left]!=s[right]){
                return false
            }
            left++
            right--
        }
        return true
    }

}

class SolutionBetter:Sol {
    override fun partition(s: String): List<List<String>> {
        val res = Array(s.length){mutableListOf<List<String>>()}
        val dp = Array(s.length){BooleanArray(s.length)}
        for(i in s.lastIndex downTo 0){
            for(j in i .. s.lastIndex){
                if(s[i]==s[j] && (j-i<=2 ||dp[i+1][j-1])) {
                    dp[i][j] = true

                    if(j==s.lastIndex)
                        res[i].add(mutableListOf(s.substring(i..j)))
                    else
                        for(combination in res[j+1]){
                            res[i].add(mutableListOf(s.substring(i..j)) + combination)
                        }
                }
            }
        }
//        println(res)

        return res[0]
    }
}


class Solution:Sol {
    override fun partition(s: String): List<List<String>> {
        //DP first to know i..j is palindrome or not
        val dp = Array(s.length){BooleanArray(s.length)}
        for(i in s.lastIndex downTo 0){
            for(j in i .. s.lastIndex){
                if(i == j) dp[i][j] = true
                else if(s[i]==s[j]){
                    dp[i][j] = if(j-i==1) true else dp[i+1][j-1]
                }
            }
        }

        return divideAndConquer(s, 0, s.lastIndex, dp)?: listOf()
    }

    private fun divideAndConquer(s:String, start:Int, end:Int, dp:Array<BooleanArray>):List<List<String>>?{
        if(start>end) return null

        val result = mutableListOf<List<String>>()
        for(i in start .. end){
            if(!dp[start][i]) continue

            val listOfStrings = divideAndConquer(s, i+1, end, dp)
            if(listOfStrings!=null){
                for(strings in listOfStrings){
                    result.add(mutableListOf(s.substring(start..i))+strings)
                }
            }else{
                result.add(mutableListOf(s.substring(start..i)))
            }
        }

        return if(result.isEmpty()) null else result
    }
}