// Input: [1,8,6,2,5,4,8,3,7]
// Output: 49


let input = [1,8,6,2,5,4,8,3,7];
/**
 * @param {number[]} height
 * @return {number}
 */

//naive
var maxArea = function(height) {
  let len = height.length;
  let max = 0;
  for(let i=0; i<len; ++i){
    for(let j=0; j<len; ++j){
      if(i == j) continue;
      let width = (i-j)<0 ? -1*(i-j) : (i-j);
      if(Math.min(height[i],height[j])*width > max){
        max = Math.min(height[i],height[j])*width;
      }
    }
  }
  return max
};

//簡單的說 就是頭尾的牆壁一直往內縮
//水的面積 = min(頭,尾) * (尾-頭)
//所以一個loop持續去往頭尾範圍之內找 比目前頭尾短邊更長的邊(c邊)
//找到後計算面積是否比上一回合大
//是->然後把c邊取代短邊
//否->繼續
//直到loop through整個array
var maxArea = function(height) {
  let containerHead = 0,
      containerTail = height.length-1,
      minHeight = Math.min(height[containerHead],height[containerTail]),
      max = minHeight * (containerTail-containerHead);
    
  while(containerHead < containerTail){
    //move small one pivot
    (height[containerHead] < height[containerTail]) ? containerHead++ : containerTail--;
    
    if(minHeight < Math.min(height[containerHead],height[containerTail]) &&
        max < (Math.min(height[containerHead],height[containerTail])*(containerTail-containerHead))){
      minHeight = Math.min(height[containerHead],height[containerTail]);
      max = (Math.min(height[containerHead],height[containerTail])*(containerTail-containerHead));
    }
  }
  return max
}

//更簡潔的版本
var maxArea = function(height) { 
    
    let max = 0; 
    let i = 0; 
    let j = height.length - 1; 
    
    while(i < j){ 
      let iHeight = height[i];
      let jHeight = height[j]
        max = Math.max(Math.min(iHeight, jHeight) * (j - i), max);
        
        iHeight < jHeight ? i++ : j--;
    }
    
    return max;
};


let result = maxArea(input);
console.log(result);