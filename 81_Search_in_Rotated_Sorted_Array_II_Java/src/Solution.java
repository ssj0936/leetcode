class Solution {
    public boolean search(int[] nums, int target) {
        var head=0;
        var tail = nums.length-1;
        while (head<=tail){
            var mid = head + (tail - head)/2;
//            System.out.println("head:"+head+"/tail:"+tail+"/mid:"+mid);
            if(nums[mid] == target || nums[head] == target || nums[tail] == target)
                return true;

            if(/*mid!=tail && */nums[mid]==nums[head] && nums[mid]==nums[tail]){
                var isPrevAllSame = true;
                for(int i = head;i<=mid;++i){
                    if(nums[i]!=nums[head]) {
                        isPrevAllSame = false;
                        break;
                    }
                }
//                System.out.println("isPrevAllSame:"+isPrevAllSame);
                if(isPrevAllSame)
                    head = mid+1;
                else
                    tail = mid-1;

            }
            //normal case
            else if(nums[tail]>=nums[mid] && nums[mid]>=nums[head]){
                if(target>nums[mid])
                    head = mid+1;
                else
                    tail = mid -1;
            }else {
                if(nums[head]<=nums[mid]){
                    if (target >= nums[head] && target < nums[mid])
                        tail = mid - 1;
                    else
                        head = mid + 1;
                } else {
                    if (target > nums[mid] && target < nums[tail])
                        head = mid + 1;
                    else
                        tail = mid - 1;
                }
            }
        }
        return false;
    }
}