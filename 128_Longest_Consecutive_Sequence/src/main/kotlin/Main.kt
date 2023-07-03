class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        val set = HashSet<Int>()
        nums.forEach { set.add(it) }

        var maxLength = 0
        for(num in nums){
            if(!set.contains(num)) continue

            var leng = 1

            //backward
            var n = num-1
            while (set.contains(n)){
                ++leng
                set.remove(n)
                --n
            }

            var m = num+1
            while (set.contains(m)){
                ++leng
                set.remove(m)
                ++m
            }

            if(leng>maxLength)
                maxLength = leng
        }

        return maxLength
    }
}