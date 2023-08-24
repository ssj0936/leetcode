class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        znum = nums.count(0)

        if znum>1:
            return [0]*len(nums)
        else:
            result = [1]*len(nums)
            for i in range(1,len(nums)):
                result[i] = result[i-1] * nums[i-1]

            acc = nums[-1]
            for i in range(len(nums)-2, -1 , -1):
                result[i] *= acc
                acc *=nums[i]
            return result