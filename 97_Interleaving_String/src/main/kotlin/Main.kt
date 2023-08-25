class Solution {
    var lenS1=0
    var lenS2=0
    var lenS3=0

    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        lenS1 = s1.length
        lenS2 = s2.length
        lenS3 = s3.length
        val cache = Array(lenS1+1){IntArray(lenS2+1){-1}}
        if((lenS1 + lenS2)!= lenS3)
            return false

        return foo(s1, s2, s3, 0,0,0,cache)
    }


    fun foo(s1: String, s2: String, s3: String, i1:Int, i2:Int, i3:Int, cache:Array<IntArray>):Boolean{
        if(i3==lenS3) {
            return true
        }

        if(cache[i1][i2] != -1)
            return cache[i1][i2]==1

        if(i1 <lenS1 && s1[i1] == s3[i3] && foo(s1, s2, s3, i1+1,i2,i3+1,cache)){
            cache[i1][i2] = 1
            return true
        }

        if(i2<lenS2 && s2[i2] == s3[i3] && foo(s1, s2, s3, i1, i2+1, i3+1,cache)) {
            cache[i1][i2] = 1
            return true
        }

            cache[i1][i2] = 0

        return false
    }
}

class Solution {
    fun isInterleave(s1: String, s2: String, s3: String): Boolean {
        if((s1.length + s2.length)!= s3.length) return false

        val dp = Array(s1.length+1){Array(s2.length+1){BooleanArray(2)} }.apply {
            for(i in s1.indices){
                if(s1[i] == s3[i])
                    this[i+1][0][0] = true
                else
                    break
            }

            for(i in s2.indices){
                if(s2[i]==s3[i])
                    this[0][i+1][1] = true
                else
                    break
            }

            this[0][0][0] = true
            this[0][0][1] = true
        }

        for(i in s1.indices){
            for(j in s2.indices){
                val dpI = i+1
                val dpJ = j+1

                if(s1[i] == s3[i+j+1])
                    dp[dpI][dpJ][0] = dp[dpI][dpJ][0] || dp[dpI-1][dpJ][0] || dp[dpI-1][dpJ][1]
                if(s2[j] == s3[i+j+1])
                    dp[dpI][dpJ][1] = dp[dpI][dpJ][1] || dp[dpI][dpJ-1][0] || dp[dpI][dpJ-1][1]
            }
        }

        return dp[s1.length][s2.length][0] || dp[s1.length][s2.length][1]

    }
}