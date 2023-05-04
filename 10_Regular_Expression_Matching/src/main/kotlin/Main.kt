fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun isMatch(s: String, p: String): Boolean {
        data class Pattern(
            val char:Char,
            val star:Boolean = false
        ){
            fun isStar():Boolean = star
            fun isDot():Boolean = char == '.'
        }

        val patterns = mutableListOf<Pattern>()
        var ptrPattern = 0
        while (ptrPattern < p.length){
            val char = p[ptrPattern]
            val isStar = ptrPattern<p.lastIndex && p[ptrPattern+1]=='*'
            patterns.add(Pattern(char, isStar))

            ptrPattern += if(isStar) 2 else 1
        }

        val dp = Array(patterns.size+1){BooleanArray(s.length+1)}.apply {
            //各個長度patter對空字串的結果
            var stillStar = true
            for(i in patterns.size-1 downTo 0){
                if(stillStar && !patterns[i].isStar())
                    stillStar = false
                this[i][s.length] = stillStar
            }

            //空pattern對各個長度字串的結果
            for(j in 0 .. s.length){
                this[patterns.size][j] = false
            }

            this[patterns.size][s.length] = true
        }

        for(i in patterns.lastIndex downTo 0){
            for(j in s.lastIndex downTo 0){
                val isStar = patterns[i].isStar()
                val isDot = patterns[i].isDot()
                val char = patterns[i].char
                if(!isStar){
                    dp[i][j] = dp[i+1][j+1] && if(isDot) true else char==s[j]
                }else{
                    if(!isDot)
                        dp[i][j] = if(char==s[j]) dp[i+1][j+1] || dp[i][j+1] || dp[i+1][j] else dp[i+1][j]
                    else
                        dp[i][j] = dp[i+1][j+1] || dp[i+1][j] || dp[i][j+1]
                }
            }
        }

        return dp[0][0]
    }
}