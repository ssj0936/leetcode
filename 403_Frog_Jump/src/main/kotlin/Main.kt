class Solution {
    fun canCross(stones: IntArray): Boolean {
        val start = stones[0]
        val end = stones[stones.lastIndex]
        val stonePos = HashSet<Int>().apply { stones.forEach { this.add(it)}}
        //(startStone, step) -> get To final or not
        val cache = hashSetOf<Pair<Int,Int>>()

        fun helper(stoneFrom:Int, lastK:Int):Boolean{
            if(stoneFrom == end) return true

            for(i in 1 downTo -1){
                val k = (lastK+i)
                if(k == 0) continue

                val nextStop = stoneFrom + k

                if(cache.contains(Pair(stoneFrom, k)))
                    continue

                if(!stonePos.contains(nextStop) || nextStop>end){
                    cache.add(Pair(stoneFrom, k))
                    continue
                }

                if(helper(nextStop, k)) return true
            }

            return false
        }

        return if(stones.size==2)
            stones[1]-stones[0]==1
        else
            helper(stones[1], 1)
    }
}

class Solution {
    fun canCross(stones: IntArray): Boolean {
        val start = stones[0]
        val end = stones[stones.lastIndex]
        val cache = Array(stones.size){ hashSetOf<Int>()}.apply {
            this[0].add(0)
        }


        for(i in 1 .. stones.lastIndex){
            for(j in i-1 downTo 0){
                val dist = stones[i] - stones[j]
                if(cache[j].contains(dist-1) || cache[j].contains(dist) || cache[j].contains(dist+1))
                    cache[i].add(dist)
            }
        }

        return cache[stones.size-1].size>0
    }
}