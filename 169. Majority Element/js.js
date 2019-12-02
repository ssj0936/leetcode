/*
Given an array of size n, find the majority element. 
The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2

*/

/**
 * @param {number[]} nums
 * @return {number}
 */
var testData = [3, 2, 3];
var testData = [2, 2, 1, 1, 1, 2, 2];

//單純用一個map紀錄出現次數
var majorityElement = function (nums) {
    var count = new Map();
    var gap = Math.ceil(nums.length / 2);
    var majority = 0;
    for (let num of nums) {
        count.set(num, ((count.has(num) ? (count.get(num) + 1) : 1)))
        if (count.get(num) == gap) {
            majority = num;
            break;
        }
    }
    return majority;
};


//用兩兩抵銷的概念
var majorityElement = function (nums) {
    let count = 0;
    let m = 0;
    let candidate;
    for (let i = 0; i < nums.length; i++) {
        if (count === 0) {
            candidate = nums[i];
            count = 1;
        } else {
            if (nums[i] === candidate)
                count++;
            else
                count--;
        }
    }
    return candidate;

};

var result = majorityElement(testData);
console.log(result);