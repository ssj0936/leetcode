fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun maxVowels(s: String, k: Int): Int {
        var cnt=0
        var max=0
        for(tailIndex in s.indices){
            if(s[tailIndex].isVowel()) ++cnt

            if(tailIndex >= k && s[tailIndex-k].isVowel()){
                --cnt
            }

            max = maxOf(max, cnt)

            if(max==k)
                break
        }

        return max
    }

    private fun Char.isVowel():Boolean = this=='a'||this=='e'||this=='i'||this=='o'||this=='u'
}