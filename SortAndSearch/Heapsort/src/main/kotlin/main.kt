class HeapSort(){
    fun heapsort(nums:IntArray){
        //也就是界線
        var lastIndex:Int =nums.size-1

        while (lastIndex>0){
            swap(nums,0,lastIndex)
            --lastIndex
            shiftToBottom(nums,0,lastIndex)
//            println(nums.contentToString())
        }
    }

    fun heapfy(nums: IntArray, start:Int, end:Int){

        for(i in ((end-start+1)/2)-1 downTo start){
            shiftToBottom(nums,i,end)
        }
    }

    private fun shiftToBottom(nums: IntArray, root:Int, end:Int){
        var left = 2*root+1
        var right = 2*root+2
        var max = root

        if(left>end && right>end) return
//        println("${nums[left]},${nums[right]}")
        //合理範圍內 左子比root大
        if( left<=end && nums[max] < nums[left]){
            max = left
//            println(1111)
        }
        //合理範圍內 右子比root大
        if( right<=end && nums[max] < nums[right]){
            max = right
//            println(2222)
        }

        if(max == root) return
        else{
            swap(nums,max,root)
            shiftToBottom(nums,max,end)
        }
    }

    fun swap(nums: IntArray, indexA:Int, indexB:Int){
        val tmp = nums[indexA]
        nums[indexA] = nums[indexB]
        nums[indexB] = tmp
    }
}

fun main(args: Array<String>) {
    val input = intArrayOf(19, 1, 10, 14, 16, 4, 7, 9, 3, 2, 8, 5, 11)
    println(input.contentToString())
    val h = HeapSort()
    h.heapfy(input,0,input.size-1)
    println(input.contentToString())
    println("---------------")
    h.heapsort(input)
    println(input.contentToString())
}