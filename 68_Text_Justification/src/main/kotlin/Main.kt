class Solution {
    val result = mutableListOf<String>()
    val BLANK = ' '
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        foo(words, maxWidth, 0)
//        result.forEach { println(it) }
        return result
    }

    fun foo(words: Array<String>, maxWidth: Int, i:Int){
        if(i>=words.size) return

        //phase 1, define valid range
        var endI = i
        var charCount = 0
        while (endI<words.size){
            charCount += words[endI].length
            val minExtraSpace = endI - i
            if(charCount + minExtraSpace > maxWidth){
                charCount -= words[endI].length
                break
            }
            else
                ++endI
        }
        --endI

        //phase 2, put extra space in it
        val extraSpaceCount = maxWidth - charCount
        val slotCount = endI-i
        val minSpacePerSlot = if(slotCount!=0) extraSpaceCount/slotCount else -1
        var extraSpaceCoda = if(slotCount!=0) (extraSpaceCount - minSpacePerSlot*slotCount) else -1

        //normal case, endI point in end of sequence
        var line = ""

        if(endI!=words.lastIndex){
            //only one word
            if(i == endI){
                line += words[endI]
                line += blankGen(extraSpaceCount)
            }
            //multiple words
            else{
                for(j in i .. endI){
                    line +=(words[j])
                    if(j != endI){
                        line += (blankGen(minSpacePerSlot))
                        if(extraSpaceCoda-- > 0){
                            line += (BLANK)
                        }
                    }
                }
            }
        }
        //last Line
        else{
            for(j in i .. endI){
                line +=(words[j])
                if(j != endI){
                    line +=(BLANK)
                }
            }
            line +=(blankGen(extraSpaceCount - slotCount))
        }

        result.add(line)

        foo(words,maxWidth, endI+1)
    }

    fun blankGen(i:Int) = String(CharArray(i){' '})
}