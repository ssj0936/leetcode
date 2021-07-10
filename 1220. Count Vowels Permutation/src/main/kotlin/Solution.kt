class Solution {
    fun countVowelPermutation(n: Int): Int {
        if (n == 1) return 5

        val mod = 1000000007

        val m: MutableMap<String, Long> = mutableMapOf(
            Pair("a",1L),
            Pair("e",1L),
            Pair("i",1L),
            Pair("o",1L),
            Pair("u",1L)
        )

        for(i in 2 .. n){
            val tmp = longArrayOf(m["a"]!!,m["e"]!!,m["i"]!!,m["o"]!!,m["u"]!!)
            m["a"] = (tmp[1]+tmp[2]+tmp[4]) % mod
            m["e"] = (tmp[0]+tmp[2]) % mod
            m["i"] = (tmp[1]+tmp[3]) % mod
            m["o"] = (tmp[2]) % mod
            m["u"] = (tmp[2]+tmp[3]) % mod
        }

        var res:Long = 0
        for (i in m.keys) {
            res += m[i]!!
            res %= mod
        }

        //wrong
        //need to module it every time you plus
        //return (m["a"]!!+m["e"]!!+m["i"]!!+m["o"]!!+m["u"]!!).toInt()%mod
        return res.toInt()
    }
}

fun main(args: Array<String>) {
    val s = Solution().countVowelPermutation(158)
    println(s)
}