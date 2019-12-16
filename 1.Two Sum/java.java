import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


class Solution {
    public int[] twoSum(int[] nums, int target) {
      Map<Integer,Integer> m = new HashMap<Integer,Integer>();
      for(int i=0;i<nums.length;++i){
        if(m.containsKey(target - nums[i])){
          int[] result = new int[2];
          result[0] = m.get(target - nums[i]);
          result[1] = i;
          return result;
        }
        m.put(nums[i],i);
      }
      return null;
    }


  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    Solution s = new Solution();
    int[] result = s.twoSum(nums, target);
    System.out.println(Arrays.toString(result));
  }
}