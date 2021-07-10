import java.util.*
import kotlin.Comparator

data class KeySide(
    var x:Int,
    var height:Int,
    var isLeft:Boolean
):Comparable<KeySide>{
    override fun compareTo(other: KeySide): Int {
        return if(x!=other.x){
            x - other.x
        } else{
            ( if(isLeft) -height else height ) - (if(other.isLeft) -other.height else other.height)
        }
    }
}

class Solution{
    fun getSkyline(buildings: Array<IntArray>):List<List<Int>>{

        val keySide = mutableListOf<KeySide>()
        for(building in buildings){
            keySide.add(
                KeySide(
                    building[0],
                    building[2],
                    true)
            )

            keySide.add(
                KeySide(
                    building[1],
                    building[2],
                    false)
            )
        }
        //scan from left to right
        keySide.sort()

        //for current max height recording
        val heap = PriorityQueue(Comparator<Int> { o1, o2 -> (o2-o1) }).apply {
            offer(0)
        }
        val result = mutableListOf<List<Int>>()

        for(side in keySide){
            when(side.isLeft){
                //left, means it's building entering point
                true->{
                    if(side.height > heap.peek()){
                        result.add(listOf(side.x,side.height))
                    }
                    heap.offer(side.height)
                }

                //right, means it's building leaving point
                false->{
                    heap.remove(side.height)
                    if(side.height > heap.peek()){
                        result.add(listOf(side.x, heap.peek()))
                    }
                }
            }
        }

//        println(keySide)
//        println(result)
        return result
    }
}

fun main(args: Array<String>) {
//    val nums:Array<IntArray> = arrayOf(
//            intArrayOf(2,9,10),
//            intArrayOf(3,7,15),
//            intArrayOf(5,12,12),
//            intArrayOf(15,20,10),
//            intArrayOf(19,24,8)
//    )
//
    val nums:Array<IntArray> = arrayOf(
        intArrayOf(2,9,10),
        intArrayOf(9,12,15)

    )

//    [[2,9,10],[9,12,15]]
    val s = Solution().getSkyline(nums)
//    val result:List<List<Int>> = s.getSkyline(nums)
    println(s)
}