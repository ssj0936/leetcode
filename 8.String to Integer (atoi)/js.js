/**
 * @param {string} str
 * @return {number}
 */
var myAtoi = function (str) {
    const INT_MAX = 2147483647,
        INT_MIN = -2147483648;

    var signDetected = false,
        isStringThroughing = false,
        isSignExist = false;
    for (let i = 0; i < str.length; ++i) {
        console.log(str[i]);

        var char = str[i];
        if(char == '' && !isStringThroughing)
            continue;
        
    }


};

var string = "    +51651adawdgg";
myAtoi(string);