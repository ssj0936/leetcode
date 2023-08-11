class Solution {
    public int search(int[] nums, int target) {
        var head = 0;
        var tail = nums.length-1;
        while (head<=tail){
            var mid = head + (tail - head)/2;
            if(nums[mid]==target)
                return mid;

            if(nums[head]<=nums[tail]){
                if(nums[mid]<target)
                    head = mid+1;
                else
                    tail = mid-1;
            }else {
                if(nums[mid]>=nums[head]){
                    if(target>=nums[head] && target<nums[mid])
                        tail = mid -1;
                    else
                        head = mid +1;
                }else {
                    if(target>nums[mid] && target<=nums[tail])
                        head = mid+1;
                    else
                        tail = mid-1;
                }
            }
        }

        return -1;
    }
}