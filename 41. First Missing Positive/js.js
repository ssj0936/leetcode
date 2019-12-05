/**
 * @param {number[]} nums
 * @return {number}
 */
/*
Given an unsorted integer array, find the first missing positive integer.
For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

/**
 * 原本的構想：
 * 對nums push一個0
 * 先用swap的方式，把nums中負數挪到陣列左邊(或是從array中移除)(O(n))
 * 然後針對大於0的部分，用swap的方式，把0放到nums[0]，把1放到nums[1]...依此類推
 * 歸類完之後，在loop一遍，第一個index與其值不同的index，則為result
 */
var firstMissingPositive = function (nums) {
  if (nums.length == 0) return 1;
  nums.push(0);
  var head, tail, temp;
  indexHead = 0;
  indexTail = nums.length - 1;

  while (indexHead <= indexTail) {
    while (nums[indexHead] < 0 && indexHead < nums.length) {
      ++indexHead;
    }

    while (nums[indexTail] >= 0 && indexTail >= 0) {
      --indexTail;
    }

    if (indexHead > indexTail) {
      break;
    } else {
      //swap
      temp = nums[indexHead];
      nums[indexHead] = nums[indexTail];
      nums[indexTail] = temp;
    }
  }
  // console.log(nums);
  // console.log(indexHead);
  // console.log(indexTail);

  //indexHead is pointing the start of positive part(0 is excluded)
  var newHeadIndex = indexHead;
  while (indexHead < nums.length) {
    if ((nums[indexHead] != indexHead - newHeadIndex) && nums[indexHead] < nums.length - newHeadIndex) {
      let index1 = indexHead,
        index2 = nums[indexHead] + newHeadIndex;
      // console.log(`${index1}:${nums[index1]}|${index2}:${nums[index2]}`)
      if (nums[index1] == nums[index2]) {
        // console.log(nums);
        ++indexHead;
        continue;
      } else {
        let temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
        // console.log(nums);
      }
    } else {
      ++indexHead;
    }
  }

  indexHead = newHeadIndex;
  while (indexHead < nums.length) {
    // console.log(nums[indexHead]);
    // console.log(nums[indexHead]);
    if (nums[indexHead] != (indexHead - newHeadIndex))
      return indexHead - newHeadIndex;
    else ++indexHead;
  }

  function getShiftIndex(currentIndex, newHeadIndex) {
    return currentIndex - newHeadIndex;
  }
};

/**
 * 但後來發現，先把負數取出的步驟多此一舉
 * 
 */
var firstMissingPositive2 = function (nums) {
  if (nums.length == 0) return 1;
  nums.push(0);
  var indexHead = 0;
  while (indexHead < nums.length) {
    if ((nums[indexHead] != indexHead) && nums[indexHead] < nums.length) {
      let index1 = indexHead,
        index2 = nums[indexHead];
      // console.log(`${index1}:${nums[index1]}|${index2}:${nums[index2]}`)
      if (nums[index1] == nums[index2] || nums[indexHead] < 0) {
        // console.log(nums);
        ++indexHead;
        continue;
      } else {
        let temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
        // console.log(nums);
      }
    } else {
      ++indexHead;
    }
  }

  indexHead = 0;
  while (indexHead < nums.length) {
    // console.log(nums[indexHead]);
    // console.log(nums[indexHead]);
    if (nums[indexHead] != (indexHead))
      return indexHead;
    else ++indexHead;
  }
  return indexHead;
}
//20191204
var firstMissingPositive = function (nums) {
  if (nums.length == 0) return 1;

  nums.push(0);
  let index = 0;
  while (index < nums.length) {
    //negtine number, skip
    if (nums[index] < 0) {
      index++;
    } 
    //number in this index correctlly placed in spot, skip
    else if (nums[index] == index) {
      index++;
    } 
    //number that out of range of arr length, skip
    else if (nums[index] >= nums.length - 1) {
      index++;
    } 
    //swap this number to the slot whith same index
    //ex.put 2 to arr[2], put 10 to arr[10]
    else {

      //if 2 swap object are the same
      // then no need to swap and preventing infinity loop
      if (nums[nums[index]] == nums[index]) {
        index++;
        continue;
      }

      //swap
      let tmp = nums[nums[index]];
      nums[nums[index]] = nums[index];
      nums[index] = tmp;
    }
  }
  // console.log(nums)
  index = 0;
  while (nums[index] == index) {
    ++index;
  }
  // console.log(index)
  return index;
};

// var nums = [1];
var nums = [3, 4, -1, 1, -9, 5, 65, -8, 0, 54, 0, -4, 22];
// var nums = [2,5,74,1,5,9,4,1,556,8,4,33,64,15,7,5];
var result = firstMissingPositive2(nums);
console.log(result);