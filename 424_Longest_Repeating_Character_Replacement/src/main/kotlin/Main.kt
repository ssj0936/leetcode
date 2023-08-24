class Solution {

    var max = 0
    fun characterReplacement(s: String, k: Int): Int {
        val set = hashSetOf<Char>().apply {s.forEach { this.add(it) }}

        for(char in set){
            foo(s, k, char)
        }

        return max
    }

    fun foo(s: String, k: Int, target:Char){
        var head = 0
        var counter = k
        for(i in s.indices){
            if(s[i]!=target){
                --counter
                if(counter<0){
                    while (head<=i && s[head]==target)
                        ++head

                    ++head
                }
            }
            max = maxOf(max, i-head +1)
        }
    }
}

class Solution {
    var max = 0
    fun characterReplacement(s: String, k: Int): Int {
        val freq = IntArray(26)
        var head = 0
//        var maxFreq = freq.max()

        for(tail in s.indices){
            ++freq[s[tail]-'A']
//            maxFreq = maxOf(maxFreq, ++freq[s[tail]-'A'])

            while (((tail - head +1) - freq.max() )>k){
                --freq[s[head]-'A']
                ++head
            }
            max = maxOf(max, tail - head +1)
        }
        return max
    }
}

