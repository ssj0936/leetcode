import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class Test {
    lateinit var solution:Sol

    @BeforeTest
    fun setup(){
        solution = Solution()
    }

    @Test
    fun test01(){
        val words  = arrayOf("acca","bbbb","caca")
        val target = "aba"
        val expectation = 6
        assertEquals(expectation, solution.numWays(words, target))
    }

    @Test
    fun test02(){
        val words  = arrayOf("abba","baab")
        val target = "bab"
        val expectation = 4
        assertEquals(expectation, solution.numWays(words, target))
    }

    @Test
    fun test03(){
        val words  = arrayOf(
            "cbabddddbc", "addbaacbbd", "cccbacdccd", "cdcaccacac", "dddbacabbd",
            "bdbdadbccb", "ddadbacddd", "bbccdddadd", "dcabaccbbd", "ddddcddadc",
            "bdcaaaabdd", "adacdcdcdd", "cbaaadbdbb", "bccbabcbab", "accbdccadd",
            "dcccaaddbc", "cccccacabd", "acacdbcbbc", "dbbdbaccca", "bdbddbddda",
            "daabadbacb", "baccdbaada", "ccbabaabcb", "dcaabccbbb", "bcadddaacc",
            "acddbbdccb", "adbddbadab", "dbbcdcbcdd", "ddbabbadbb", "bccbcbbbab",
            "dabbbdbbcb", "dacdabadbb", "addcbbabab", "bcbbccadda", "abbcacadac",
            "ccdadcaada", "bcacdbccdb"
        )
        val target = "bcbbcccc"
        val expectation = 677452090
        assertEquals(expectation, solution.numWays(words, target))
    }

    @Test
    fun test04(){
        val words  = arrayOf(
            "cabbaacaaaccaabbbbaccacbabbbcb","bbcabcbcccbcacbbbaacacaaabbbac","cbabcaacbcaaabbcbaabaababbacbc","aacabbbcaaccaabbaccacabccaacca","bbabbaabcaabccbbabccaaccbabcab","bcaccbbaaccaabcbabbacaccbbcbbb","cbbcbcaaaacacabbbabacbaabbabaa","cbbbbbbcccbabbacacacacccbbccca","bcbccbccacccacaababcbcbbacbbbc","ccacaabaaabbbacacbacbaaacbcaca","bacaaaabaabccbcbbaacacccabbbcb","bcbcbcabbccabacbcbcaccacbcaaab","babbbcccbbbbbaabbbacbbaabaabcc","baaaacaaacbbaacccababbaacccbcb","babbaaabaaccaabacbbbacbcbababa","cbacacbacaaacbaaaabacbbccccaca","bcbcaccaabacaacaaaccaabbcacaaa","cccbabccaabbcbccbbabaaacbacaaa","bbbcabacbbcabcbcaaccbcacacccca","ccccbbaababacbabcaacabaccbabaa","caaabccbcaaccabbcbcaacccbcacba","cccbcaacbabaacbaaabbbbcbbbbcbb","cababbcacbabcbaababcbcabbaabba","aaaacacaaccbacacbbbbccaabcccca","cbcaaaaabacbacaccbcbcbccaabaac","bcbbccbabaccabcccacbbaacbbcbba","cccbabbbcbbabccbbabbbbcaaccaab","acccacccaabbcaccbcaaccbababacc","bcacabaacccbbcbbacabbbbbcaaaab","cacccaacbcbccbabccabbcbabbcacc","aacabbabcaacbaaacaabcabcaccaab","cccacabacbabccbccaaaaabbcacbcc","cabaacacacaaabaacaabababccbaaa","caabaccaacccbaabcacbcbbabccabc","bcbbccbbaaacbaacbccbcbababcacb","bbabbcabcbbcababbbbccabaaccbca","cacbbbccabaaaaccacbcbabaabbcba","ccbcacbabababbbcbcabbcccaccbca","acccabcacbcbbcbccaccaacbabcaab","ccacaabcbbaabaaccbabcbacaaabaa","cbabbbbcabbbbcbccabaabccaccaca","acbbbbbccabacabcbbabcaacbbaacc","baaababbcabcacbbcbabacbcbaaabc","cabbcabcbbacaaaaacbcbbcacaccac"
        )
        val target = "acbaccacbbaaabbbabac"
        val expectation = 555996654
        assertEquals(expectation, solution.numWays(words, target))
    }
}
