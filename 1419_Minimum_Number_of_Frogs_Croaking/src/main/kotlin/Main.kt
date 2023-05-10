fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution {
    fun minNumberOfFrogs(croakOfFrogs: String): Int {
        if(croakOfFrogs.length%5 != 0)
            return -1

        var ptr = 0
        val record = Array(5){mutableListOf<Int>()}

        var max = 0
        for(i in croakOfFrogs.indices){
            record[croakOfFrogs[i].toIndex()].add(i)

            if(croakOfFrogs[i]=='c' && (record[croakOfFrogs[i].toIndex()].size-ptr) > max)
                max = (record[croakOfFrogs[i].toIndex()].size-ptr)
            else if(croakOfFrogs[i]=='k'){

                for(c in 0 until 5){
                    if(record[c].size <= ptr || c>0 && record[c-1][ptr] > record[c][ptr])
                        return -1
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

class Solution2 {
    fun minNumberOfFrogs(croakOfFrogs: String): Int {
        if(croakOfFrogs.length%5 != 0)
            return -1

        var ptr = 0
        val record = Array(5){mutableListOf<Int>()}

        var max = 0
        for(i in croakOfFrogs.indices){
            record[croakOfFrogs[i].toIndex()].add(i)

            if(croakOfFrogs[i]=='c'){
                if((record[croakOfFrogs[i].toIndex()].size-ptr) > max)
                    max = (record[croakOfFrogs[i].toIndex()].size-ptr)
            }else {
                if (record[croakOfFrogs[i].toIndex() - 1].lastIndex < record[croakOfFrogs[i].toIndex()].lastIndex
                    || record[croakOfFrogs[i].toIndex() - 1][record[croakOfFrogs[i].toIndex()].lastIndex] > record[croakOfFrogs[i].toIndex()].last())
                    return -1

                if (croakOfFrogs[i] == 'k') {
                    ++ptr
                }
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

class Solution3 {
    fun minNumberOfFrogs(croakOfFrogs: String): Int {
        if(croakOfFrogs.length%5 != 0)
            return -1

        //紀錄目前為止 結束在對應index字元的subsequence有幾個
        val record = IntArray(5)

        var max = 0
        var currentFrogCnt = 0
        for(i in croakOfFrogs.indices){
            val index = croakOfFrogs[i].toIndex()

            ++record[index]

            //這邊有點像sliding windows的概念 紀錄同時最多會有幾隻青蛙在場上 遇到C就多一隻 遇到K結束就少一支
            if(croakOfFrogs[i]=='c'){
                ++currentFrogCnt
                if(currentFrogCnt > max)
                    max = currentFrogCnt
            }else {
                //這邊可以用一個意象，想像成我往前找前一個字元，然後把它拿過來放到我這邊
                //所以前面那格的各數減少，而我這格會是存從開始到目前index的子subsequence 的 個數
                if (record[index - 1]==0)
                    return -1

                --record[index - 1]

                if (croakOfFrogs[i] == 'k') {
                    --record[index]
                    --currentFrogCnt
                }
            }
        }
        //最後檢查 是否有剩下的，因為題目說一定要剛好用完，有的話就return -1
        return if(record.any { it!=0}) -1 else max
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