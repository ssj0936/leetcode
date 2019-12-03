var A = [ 1, 2],
    B = [-2,-1],
    C = [-1, 2],
    D = [ 0, 2];

/**
 * @param {number[]} A
 * @param {number[]} B
 * @param {number[]} C
 * @param {number[]} D
 * @return {number}
 */

//naive solution
var fourSumCount = function(A, B, C, D) {
    var count = 0, length = A.length;
    for(let i =0;i<length;++i){
      for(let j =0;j<length;++j){
        for(let k =0;k<length;++k){
          for(let l =0;l<length;++l){
            if(A[i]+B[j]+C[k]+D[l] == 0) count++;
          }          
        }
      } 
    }
    return count;
};

//use 2 hashtable
//更快的方法：在產生HashCD時 就直接計算
var fourSumCount = function(A, B, C, D) {
    var count = 0;
    var length = A.length;
    
    var hashAB = new Map();
    for(let i =0;i<length;++i){
      for(let j =0;j<length;++j){
        let sum = A[i] + B[j];
        hashAB.set(sum, (hashAB.has(sum)) ? (hashAB.get(sum)+1):1)
      } 
    }
    // hashAB.forEach(function(value, key, map) {
    //   console.log(`Key: ${key}, Value: ${value}`);
    // });
    // console.log("--------------")

    var hashCD = new Map();
    for(let k =0;k<length;++k){
      for(let l =0;l<length;++l){
        let sum = C[k] + D[l];
        hashCD.set(sum, (hashCD.has(sum)) ? (hashCD.get(sum)+1) : 1)
      } 
    }
    // hashCD.forEach(function(value, key, map) {
    //   console.log(`Key: ${key}, Value: ${value}`);
    // });

    for(let[key, value] of hashAB.entries()){
      if(hashCD.has(-1*key)){
        count += (parseInt(value) * parseInt(hashCD.get(-1*key)));
      }
    }
    return count;
};

var result = fourSumCount(A,B,C,D);
console.log(result);