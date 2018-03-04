/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 * 構想：
 * 找出字串中 數量小於K的字母
 * 並以這些字母切割字串
 * 
 * 對於每個子字串 在做相同的遞迴處理
 * 若該字串中不含有數量小於K的字母 則return這個字串的length
 */
var longestSubstring = function (s, k) {
    return recursive(s, k);

    function recursive(s, k) {
        if (s.length == 0) return 0;
        if (s.length < k) return 0;

        //先抽出s中不合格的元素
        //數量小於K的元素
        var temp = {};
        var sArray = Array.from(s);
        for (var i of sArray) {
            temp[i] = temp[i] || 0;
            ++temp[i];

        }
        var inappropriateElement = [];
        for (var i in temp) {
            if (temp[i] < k) inappropriateElement.push(i);
        }

        //check if s is appropriate
        if (inappropriateElement.length == 0) {
            return s.length;
        } else {
            // console.log(s);
            // console.log(inappropriateElement);
            
            var split = s.split(new RegExp(inappropriateElement.join('|')));
            var max = 0;
            for(var i of split){
                var len = recursive(i,k);
                max = (len>max)? len : max;
            }
            return max;
        }
    }
};

var s = "bbaaaccbd",
    k = 3;
var result = longestSubstring(s, k);
console.log(result);