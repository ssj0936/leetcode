/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var findMedianSortedArrays = function (nums1, nums2) {
    
    //recording the last number in num2, and the second last number in num1
    var num1 = null,
        num2 = null;
    
    //if length is odd, need to return average of 2 number
    var isOdd = ((nums1.length + nums2.length) % 2 == 0),
        length = nums1.length + nums2.length;

    let count = 0;

    //recording smallest number of 2 arr and remove it, and then do the same process of biggest number
    while (count < length) {

        let isThisTurnChooseSmallest = (count % 2 == 0);
        // select a smlleset number and index move backward
        if (isThisTurnChooseSmallest) {
            let targetArr;
            if (nums1.length == 0) {
                targetArr = nums2;
            } else if (nums2.length == 0) {
                targetArr = nums1;
            } else {
                targetArr = (nums1[0] < nums2[0]) ? nums1 : nums2;
            }
            num1 = num2;
            num2 = targetArr[0];
            targetArr.splice(0, 1);
        } 
        // select a biggest number and index move foreward
        else {
            let targetArr;
            if (nums1.length == 0) {
                targetArr = nums2;
            } else if (nums2.length == 0) {
                targetArr = nums1;
            } else {
                targetArr = (nums1[nums1.length-1] > nums2[nums2.length-1]) ? nums1 : nums2;
            }
            num1 = num2;
            num2 = targetArr[targetArr.length-1];
            targetArr.splice(targetArr.length-1, 1);
        }
        
        ++count;
    }

    if(isOdd){
        return (num1+num2)/2;
    }else{
        return num2;
    }
};

var nums1 = [];
var nums2 = [];
var result = findMedianSortedArrays(nums1,nums2);
console.log(result);