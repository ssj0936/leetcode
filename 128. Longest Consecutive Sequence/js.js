/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.
 * 
 * @param {number[]} nums
 * @return {number}
 */
var test = [100, 4, 200, 1, 3, 2];
var test = [0,-1];


/*
原本的構想 是把array做成set，然後最大最小值找出來，從最小loop到最大，去set之中找有沒下一位數字
然後取出max length
*/
var longestConsecutive = function(nums) {

    let min = Math.min(...nums);
    let max = Math.max(...nums);
    let numSet = new Set(nums);

    let index = 0;
    let indexStart,indexEnd,maxLength = 0;
    for(let index = min; index <= max; ++index){
      //stage1
      if(!numSet.has(index-1) && !numSet.has(index)){
      }
      //stage2
      else if(!numSet.has(index-1) && numSet.has(index)){
        indexStart = index-1;
        indexEnd = index-1;
      }else if(numSet.has(index-1) && numSet.has(index)){
        indexEnd++;
      }else if(numSet.has(index-1) && !numSet.has(index)){
        maxLength = Math.max(maxLength,(indexEnd-indexStart+1))
      }

      if(index == max){
        maxLength = Math.max(maxLength,(indexEnd-indexStart+1))
      }
    }
    return maxLength;
};

/*
把nums做成set，把nums中每個值loop一遍，若nums[i-1]存在於set中，表示該值不是sequence的起點，skip
找到sequence的起點後，從set之中，向後確認sequence有多長並記錄
*/
var longestConsecutive = function(nums) {
  let numSet = new Set(nums);

  let max = 0;
  for(let num of nums){
    
    //find sequence head
    if(numSet.has(num-1)){
      continue;
    }else{
      let length = 0;
      while(numSet.has(num + length)){
        length++;
      }
      max = Math.max(max,length);
    }
  }
  return max;
}

let result = longestConsecutive(test);
console.log(result)