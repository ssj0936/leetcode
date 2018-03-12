/**
 * @param {string} s
 * @return {number}
 */
var lengthOfLongestSubstring = function (s) {
    var sArray = Array.from(s);
    // console.log(sArray);

    var start = 0,
        end = sArray.length;
    var LSS = "";
    for (var i = start; i < end; ++i) {
        let set = new Set(),
            substringIndex = i,
            substring = '';
        while (!set.has(sArray[substringIndex]) && substringIndex < sArray.length) {
            set.add(sArray[substringIndex]);
            substring += sArray[substringIndex];
            ++substringIndex;
        }
        // console.log(`${sArray[i]}:${substring}`);
        if (substring.length > LSS.length) {
            LSS = substring;
            end = (sArray.length) - LSS.length;
        }
    }
    return LSS.length;
};

var lengthOfLongestSubstring2 = function (s) {
    var sArray = Array.from(s);
    // console.log(sArray);

    var start = 0,
        end = s.length;
    var LSS = "",
        latestRepeatChar = '';
    for (var i = start; i < end; ++i) {
        let set = new Set(),
            substringIndex = i,
            substring = '';
        while (!set.has(s[substringIndex]) && substringIndex < s.length) {
            set.add(s[substringIndex]);
            substring += s[substringIndex];
            ++substringIndex;
        }

        // console.log(`${sArray[i]}:${substring}`);
        if (substring.length > LSS.length) {
            LSS = substring;
            end = (s.length) - LSS.length;
        }

        if (substringIndex != s.length) {
            latestRepeatChar = s[substringIndex];
            while (s[i] != latestRepeatChar) {
                ++i;
            }
            // --i;
            // console.log(`--------latestRepeatChar:${latestRepeatChar}`);
            // console.log(`--------move i to:sArray[${i}]:${sArray[i]}`);
        }
    }
    return LSS.length;
};

var lengthOfLongestSubstring3 = function (s) {
    var LSS = "",
        maxLength = 0;
    for (var i = 0; i < s.length; ++i) {
        let char = s[i];
        // console.log(char);
        //means first appearence in LSS
        if (LSS.indexOf(char) == -1) {
            LSS += char;
            if (LSS.length > maxLength) {
                // console.log(LSS);
                maxLength =  LSS.length;
            }
        } else {
            let indexOfFirstRepeatCharInLSS = LSS.indexOf(char);
            LSS = LSS.substr(indexOfFirstRepeatCharInLSS + 1);
            // console.log("===:"+LSS);
            LSS += char;
        }
    }
    return maxLength;
};

var s = "pwuwkew";
var result = lengthOfLongestSubstring2(s);

var div = document.getElementById('app');
var sDiv = document.createElement('div');
sDiv.innerText = s;

var resultDiv = document.createElement('div');
resultDiv.innerText = result;

div.appendChild(sDiv);
div.appendChild(resultDiv);