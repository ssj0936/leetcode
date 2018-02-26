/**
 * @param {string} str
 * @return {number}
 */
var myAtoi = function(str) {
    //no need to parse empty string
    if (str.length == 0) return 0;

    const INT_MAX = 2147483647,
        INT_MIN = -2147483648,
        NUMBER = "0123456789";

    //remove empty part
    var index = 0;
    while (str[index] == ' ') {
        ++index;
    }
    str = str.slice(index, str.length);
    if (str.length == 0) return 0;

    //extract number part
    var sign = null,
        number = null;

    if (str[0] == "+" || str[0] == "-") {
        sign = str.slice(0, 1);
        str = str.slice(1, str.length);
        if (str.length == 0) return 0;
    }

    index = 0;
    while (NUMBER.indexOf(str[index]) != -1) ++index;
    number = str.slice(0, index);

    //convert to number
    number = arrToNumber(number);

    if (sign != null && sign == "-") {
        number = -1 * number;
    }

    if (number > INT_MAX) return INT_MAX;
    else if (number < INT_MIN) return INT_MIN;
    else return number;

    function arrToNumber(arr) {
        var length = arr.length,
            result = 0;

        for (let i = 0; i < length; ++i) {
            result += arr[i] * Math.pow(10, (length - i - 1));
        }
        return result;
    }
};

var string = "-1";
var result = myAtoi(string);
console.log(result);