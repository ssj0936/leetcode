/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var longestSubstring = function (s, k) {
    return recursive(s, k);

    function recursive(s, k) {
        if (s.length == 0) return 0;
        if (s.length < k) return 0;
        //check if s is appropriate
        // if (isAppropriateSubstr(s, k)) return s.length;

        //先抽出s中不合格的元素
        //數量小於K的元素
        var sArray = Array.from(s);
        var sArraySorted = sArray.slice().sort();
        var inappropriateElement = [];

        var currentWord = null,
            currentCount = 0;

        for (var [i, v] of sArraySorted.entries()) {
            // console.log(`${i} | ${v}`);

            //結算
            if (currentWord != v) {
                if (currentWord != null) {
                    if (currentCount < k) inappropriateElement.push(currentWord);
                }
                currentCount = 0;
                currentWord = v;
            }
            ++currentCount;
            if (i == s.length - 1) {
                if (currentWord != null) {
                    if (currentCount < k) inappropriateElement.push(currentWord);
                }
                currentCount = 0;
                currentWord = v;
            }
        }

        if(inappropriateElement.length == 0) return s.length;

        // console.log(sArray);
        var arrOfIndex = [],index;
        for(var [i,v] of sArray.entries()){
            if(inappropriateElement.indexOf(v)!=-1){
                arrOfIndex.push(i);
            }
        }

        index = arrOfIndex[parseInt(arrOfIndex.length / 2)];
        
        // console.log(inappropriateElement);
        // console.log(index);
        // console.log(`${s.slice(0, index)} | ${s.slice(index + 1, s.length)}`);
        return Math.max(recursive(s.slice(0, index), k), recursive(s.slice(index + 1, s.length), k));   
        
    }
};

var s = "bbaaacbd",
    k = 3;
var result = longestSubstring(s, k);
console.log(result);