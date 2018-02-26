/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var threeSum = function(nums) {
    if (nums.length < 3) return [];

    nums = numberSort(nums);
    // console.log(nums);

    var i0, i1, i2,len = nums.length;
    var results = [];
    var set = new Set();
    for (i0 = 0, i1 = i0 + 1, i2 = len - 1; i0 < len - 2; ++i0, i1 = i0 + 1, i2 = len - 1) {
        while (i1 < i2) {
            if (nums[i0] + nums[i1] + nums[i2] > 0) {
                --i2;
            } else if (nums[i0] + nums[i1] + nums[i2] < 0) {
                ++i1;
            } else {
                if(!set.has(`${nums[i0]}_${nums[i1]}_${nums[i2]}`)){
                    set.add(`${nums[i0]}_${nums[i1]}_${nums[i2]}`);
                    results.push([nums[i0] , nums[i1] , nums[i2]]);
                }

                --i2;
                ++i1;
            }
        }
    }

    return results;

    function numberSort(nums) {
        return nums.sort((a, b) => {
            return a - b;
        });
    }
};

var number = [-1,0,1,2,-1,-4,-1,-1,-1,-1];
var results = threeSum(number);
console.log(results);