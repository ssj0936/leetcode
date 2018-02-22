/**
 * @param {string} str
 * @return {number}
 */
var myAtoi = function (str) {
    const INT_MAX = 2147483647,
        INT_MIN = -2147483648,
        NUMBER = "0123456789";

    if(str.length == 0) return 0;

    var signDetected = false,
        hasPassingAnyChar = false,
        isFirstChar = false,

        isNumberThroughing = false,
        isPrevCharNumber = false,
        isPrevCharSign = false;

    var sign = 1;
    var number = [];
    var finalResult;
    for (var i = 0; i < str.length; ++i) {
        var char = str.charAt(i);
        if (char == ' ' && !hasPassingAnyChar) {
            continue;
        }

        hasPassingAnyChar = true;

        //first char
        if (hasPassingAnyChar && !isFirstChar) {
            if (!isNumber(char) && char != '+' && char != '-') {
                //no number
                return 0;
            } else if (isNumber(char)) {
                if(char == '0') continue;
                isNumberThroughing = true;
                sign = 1;
                number.push(char);
            } else if (char == '+') {
                sign = 1;
                isPrevCharSign = true;
            } else if (char == '-') {
                sign = -1;
                isPrevCharSign = true;
            }
            isFirstChar = true;
        }
        //not first char
        else {
            if(!isNumberThroughing && !isNumber(char)){
                finalResult = null;
                return 0;
            }else if(isNumberThroughing && !isNumber(char)){
                //收尾
                return finalProcess(number);
            }else if(!isNumberThroughing && isNumber(char)){
                //第一個數字
                if(isPrevCharSign && isNumber(char)){
                    if(char == '0') continue;
                    isNumberThroughing = true;
                    isPrevCharSign = false;
                    number.push(char);
                }else if(!isPrevCharSign && isNumber(char)){
                    number.push(char);
                }else if(isPrevCharSign && !isNumber(char)){
                    return 0;
                }
            }else if(isNumberThroughing && isNumber(char)){
                number.push(char);
            }
        }
    }
    return finalProcess(number);

    function isNumber(char){
        return NUMBER.indexOf(char)!=-1;
    }

    function arrToNumber(arr){
        var length = arr.length,
            result = 0;

        for(let i =0;i<length;++i){
            result += arr[i]*Math.pow(10,(length-i-1));
        }
        return result;
    }

    function finalProcess(arr){
        var finalResult = sign * arrToNumber(arr);
        if(finalResult>INT_MAX) return INT_MAX;
        else if(finalResult<INT_MIN) return INT_MIN;
        else return finalResult;
    }
};

var string = "-1";
var result = myAtoi(string);
console.log(result);