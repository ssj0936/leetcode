class SolutionOri {
    fun isMatch(s: String, p: String): Boolean {
        if(s == p) return true
        val dp = hashMapOf<Pair<Int, Int>, Boolean>()
        return helper(s, p, 0, 0, dp)
    }

    private fun helper(s:String, p:String, ps:Int, pp:Int, dp:HashMap<Pair<Int, Int>, Boolean>):Boolean{
        dp.get(ps to pp)?.let {
            return it
        }

        var pointerS = ps
        var pointerP = pp

        while (pointerS<s.length && pointerP<p.length && (s[pointerS] == p[pointerP] || p[pointerP] == '?')){
            ++pointerS
            ++pointerP
        }
        var returnValue = false

        if(pointerS == s.length && pointerP == p.length) {//兩個都走完
            returnValue = true
        }else if(pointerP == p.length) {//pattern走完 string還有剩
            returnValue = false
        }else if(p[pointerP]=='*'){
            for (i in s.length downTo pointerS) {
                val result = helper(s, p, i, pointerP + 1, dp)
                dp.put(i to pointerP + 1, result)
                returnValue = result
                if (result) break
            }
        }
        return returnValue
    }
}

class SolutionDP{
    fun isMatch(s: String, p: String): Boolean {
        if(p.isNotEmpty() && p.all {it=='*'}) return true
        val dp = Array(s.length+1){BooleanArray(p.length+1)}.apply {
            this[0][0] = true

            for(i in p.indices){
                if(p[i]!='*') break
                else this[0][i+1] = true
            }
        }

        for(ps in 0 .. s.lastIndex){
            val dpPs = ps+1
            for(pp in 0 ..p.lastIndex){
                val dpPp = pp+1

                when(p[pp]){
                    '*'->{
                        dp[dpPs][dpPp] = dp[dpPs][dpPp-1] || dp[dpPs-1][dpPp]

//                        for (i in dpPs downTo 0) {
//                            if(dp[i][dpPp - 1]){
//                                dp[dpPs][dpPp] = dp[i][dpPp - 1]
//                                break
//                            }
//                        }
                    }
                    '?'->{
                        dp[dpPs][dpPp] = (dp[dpPs-1][dpPp-1])
                    }
                    else->{
                        dp[dpPs][dpPp] = (s[ps]==p[pp]) && (dp[dpPs-1][dpPp-1])
                    }
                }
            }
        }

        return dp[s.length][p.length]
    }
}

class Solution {
    fun isMatch(s: String, p: String): Boolean {
        val sLength = s.length
        val pLength = p.length
        var sChance = -1
        var pChance = -1
        var i = 0
        var j = 0

        while (i < sLength){
            if( j<pLength && (s[i] == p[j] || p[j]=='?')){
                ++i
                ++j
            }else if(j<pLength && p[j]=='*'){
                sChance = i
                pChance = j++
            }else if(sChance>=0){//兩這不同 p[j]不為?且不為*
                j = pChance + 1
                i = ++sChance
            }else
                return false
        }
        var isRemainAllStar = true
        for (index in j until pLength) {
            if (p[index] != '*') {
                isRemainAllStar = false
                break
            }
        }

        return isRemainAllStar
    }
}