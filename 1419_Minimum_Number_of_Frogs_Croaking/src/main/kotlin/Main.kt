fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun minNumberOfFrogs(croakOfFrogs: String): Int {
        if(croakOfFrogs.length%5 != 0)
            return -1

        val croak = charArrayOf('c','r','o','a','k')
        var ptr = 0
        val record = Array(croakOfFrogs.length){mutableListOf<Int>()}
//        val record = hashMapOf<Char, MutableList<Int>>(
//            'c' to mutableListOf<Int>(),
//            'r' to mutableListOf<Int>(),
//            'o' to mutableListOf<Int>(),
//            'a' to mutableListOf<Int>(),
//            'k' to mutableListOf<Int>()
//        )

        var max = 0
        for(i in croakOfFrogs.indices){
            record[croakOfFrogs[i].toIndex()]!!.add(i)

            if(croakOfFrogs[i]=='c' && (record[croakOfFrogs[i].toIndex()]!!.size-ptr) > max)
                max = (record[croakOfFrogs[i].toIndex()]!!.size-ptr)
            else if(croakOfFrogs[i]=='k'){
                var tmp = -1
                for(c in croak){
                    if(record[c.toIndex()].size <= ptr || tmp > record[c.toIndex()]!![ptr])
                        return -1
                    else
                        tmp = record[c.toIndex()]!![ptr]
                }
                ++ptr
            }
        }
        return if(record.any { it.size != croakOfFrogs.length/5}) -1 else max
    }

    private fun Char.toIndex() = when(this){
        'c' -> 0
        'r' -> 1
        'o' -> 2
        'a' -> 3
        'k' -> 4
        else -> -1
    }
}