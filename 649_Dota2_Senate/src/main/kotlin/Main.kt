fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments at Run/Debug configuration
    println("Program arguments: ${args.joinToString()}")
}

class Solution{
    val R = "Radiant"
    val D = "Dire"

    fun predictPartyVictory(senate: String): String {
        var killedR = 0
        var killedD = 0
        var cntR = 0
        var cntD = 0
        senate.forEach { if(it=='R') ++cntR else ++cntD}
        val killed = mutableSetOf<Int>()

        while (cntR!=0 && cntD!=0){
            for(s in senate.indices){
                if(killed.contains(s)) continue

                if(senate[s] == 'R'){
                    if(killedR>0) {
                        killed.add(s)
                        --killedR
                        --cntR
//                        if(cntR==0) return D
                    }
                    else ++killedD
                }else{
                    if(killedD>0){
                        killed.add(s)
                        --killedD
                        --cntD
//                        if(cntD==0) return R
                    }
                    else ++killedR
                }
            }
        }

        return if(cntD==0) R else D
    }
}