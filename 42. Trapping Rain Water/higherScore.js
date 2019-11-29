
var h = [0,1,0,2,1,0,1,3,2,1,2,1];
var h = [2,0,2];

/**
 * @param {number[]} height
 * @return {number}
 */


//core concept: pit is gnerate by two high wall and low ground in the middle
//so water level in specific unit is corresponding to 
//1.highest height of wall on left of it
//2.highest height of wall on right of it
//3.water level = min(leftHighestHeight, rightHighestHeight) - gorund height
var trap = function(height) {
    
    //its not possible to get a pit if arr length less than 3
    if(height.length < 3) return 0;
    
    //record left highest high for every position
    var maxLeft = [],maxRight = [];
    var m=height[0];
    maxLeft[0] = height[0];
    for(let i = 1;i<height.length;++i){
      m = Math.max(m,height[i]);
      maxLeft[i] = m;
    }

    //record right highest high for every position
    m=height[height.length-1];
    maxRight[height.length-1] = height[height.length-1];
    for(let i = height.length-1-1;i>=0;--i){
      m = Math.max(m,height[i]);
      maxRight[i] = m;
    }

    //loop through every element and calculate water lavel
    var water = 0;
    for(let i in height){
      let sum = Math.min(maxLeft[i],maxRight[i]) - height[i];
      water = (sum<0) ? water : water + sum;
    }
    return water;
};

var result = trap(h);
console.log(result)