/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var arr = [3, 2, 4],
    target = 6;

var twoSum = function (nums, target) {
    var map = new Map();
    nums.forEach((item, index) => {
        map.set(item, index);
    });

    for (var [index, value] of nums.entries()) {
        let diff = target - value;
        if (map.has(diff) && index != map.get(diff)) {
            return [index,map.get(diff)];
        }
    }
};

var result = twoSum(arr, target);
console.log(result);