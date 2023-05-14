class Solution {
    fun maxUncrossedLines(nums1: IntArray, nums2: IntArray): Int {
        val dp = Array(nums1.size+1){IntArray(nums2.size+1)}
        for(i in nums1.indices){
            val iDpIndex = i+1
            for(j in nums2.indices){
                val jDpIndex = j+1

                dp[iDpIndex][jDpIndex] = if(nums1[i] == nums2[j])
                    dp[iDpIndex-1][jDpIndex-1]+1
                else
                    maxOf(dp[iDpIndex-1][jDpIndex], dp[iDpIndex][jDpIndex-1])
            }
        }

        return dp[nums1.size][nums2.size]
    }
}