class Solution {
    val result = arrayListOf<List<Int>>()
    fun subsetsWithDup(nums: IntArray): List<List<Int>> {
        nums.sort()
        val stack = arrayListOf<Int>()
        foo(nums, 0, stack)
        return result
    }

    private fun foo(nums: IntArray, index:Int, stack:ArrayList<Int>){
        result.add(ArrayList(stack))

        for(i in index .. nums.lastIndex){
            if(i!=index && nums[i]==nums[i-1]) continue

            stack.add(nums[i])
            foo(nums, i+1, stack)
            stack.removeAt(stack.lastIndex)
        }
    }
}