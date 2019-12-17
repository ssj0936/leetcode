// Input:  [1,2,3,4]
// Output: [24,12,8,6]
package com.timothy.leetcode.productofarrayexceptself;

import java.util.Arrays;
class Solution {
  public int[] productExceptSelf(int[] nums) {
    int[] left = new int[nums.length];
    for(int i = 0;i<nums.length;++i){
      if(i == 0) left[i] = 1;
      else left[i] = left[i-1]*nums[i-1];
    }
    System.out.println(Arrays.toString(left));

    int[] right = new int[nums.length];
    for(int i = nums.length-1 ; i>=0;--i){
      if(i == nums.length-1) right[i] = 1;
      else right[i] = right[i+1]*nums[i+1];
    }

    System.out.println(Arrays.toString(right));

    int[] result = new int[nums.length];
    for(int i = 0;i<nums.length;++i){
      result[i] = left[i]*right[i];
    }
    return result;

  }
  public static void main(String[] args) {
    int[] nums = {1,2,3,4};
    Solution s = new Solution();
    System.out.println(Arrays.toString(s.productExceptSelf(nums)));
  }
}