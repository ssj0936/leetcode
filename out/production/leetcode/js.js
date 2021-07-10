/**
 * 用兩個array 一個紀錄該點以左的累積 一個紀錄該點以右的累積
 * 最後result則為兩array對應點的相乘
 **/
var productExceptSelf = function (nums) {
  let left = [];
  left[0] = 1;
  let p = 1;
  for (let i = 1; i < nums.length; ++i) {
    p *= nums[i - 1];
    left[i] = p;
  }

  let right = [];
  right[nums.length - 1] = 1;
  p = 1;
  for (let i = nums.length - 2; i >= 0; --i) {
    p *= nums[i + 1];
    right[i] = p;
  }

  let result = [];
  for (let i = 0; i < nums.length; ++i) {
    result[i] = left[i] * right[i];
  }
  return result
};

/**
 * follow-up:
 * 用constance space-complexity
 * 所以就是用result紀錄該點以左的累積
 * 然後在處理right時，直接用一個變數紀錄累積
 * 直接算在result上
 **/

var productExceptSelf = function (nums) {
  let result = [1];

  for (let i = 1; i < nums.length; ++i) {
    result[i] = result[i - 1] * nums[i - 1];
  }
  let p = 1;
  for (let i = nums.length - 1; i >= 0; --i) {
    result[i] = result[i] * p;
    p = p * nums[i]
  }
  return result
};

/**
 * 速度最快的解 構想蠻單純的
 * 
 * 如果nums中有兩個以上的0，代表output一定是0的array
 * 如果nums中只有一個0，代表output中 原本0的位置就是nums中除了0以外的所有乘積
 * 如果nums中沒有0 每個位置的值就是所有乘積/該值
 * 阿不是說不能用divide嗎 喜咧考
 */

var productExceptSelf = function (nums) {
  let zeroCount = 0;
  let totalProduct = nums.reduce((acc, elm) => {
    if (elm === 0) {
      zeroCount++
      return acc * 1
    } else {
      return acc * elm
    }
  })

  if (zeroCount > 1) {
    return new Array(nums.length).fill(0)
  } else if (zeroCount === 1) {
    return nums.map((e) => {
      if (e !== 0) {
        return 0
      } else {
        return totalProduct
      }
    })
  } else {
    return nums.map(e => totalProduct / e)
  }
}