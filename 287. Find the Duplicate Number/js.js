/**
 * @param {number[]} nums
 * @return {number}
 */

 //brutal force
var findDuplicate = function(nums) {
    for(var [i,v] of nums.entries()){
        // console.log(v);
        // console.log(">>>>");
        for(var j = nums.length-1;j>i;--j){
            // console.log(nums[j])
            if(v == nums[j]) return v
        }
        // console.log('----------------------');
    }
    return null;
};

//link list解
//把array中的值 當作是linklist的next(的index)
//例如：[1,3,5,6,7,4,3,3]
//=>head 是nums[0]，其值為1，所以next是nums[1]
//接下來linklist第二位，其值為nums[1] = 3,所以next是nums[3]
//所以當loop linklist的途中，經過重複的元素兩次時會形成cycle
//cycle起點的index，則為陣列中重複的值
var findDuplicate2 = function(nums) {
    if(nums.length == 2) return 1;

    var head = 0;
    var fastPivot = nums[nums[head]];
    var slowPivot = nums[head];

    while(fastPivot != slowPivot){
        fastPivot = nums[nums[fastPivot]];
        slowPivot = nums[slowPivot];
    }

    slowPivot = head;
    while(fastPivot != slowPivot){
        fastPivot = nums[fastPivot];
        slowPivot = nums[slowPivot];
    }
    return fastPivot;
};


var nums=[1,7,3,4,3,2,3,3];
var result = findDuplicate(nums);
console.log(result);
var result2 = findDuplicate2(nums);
console.log(result2);