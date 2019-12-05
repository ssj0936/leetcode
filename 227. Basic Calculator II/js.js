/*Implement a basic calculator to evaluate a simple expression string.

The expression string contains only 
non-negative integers, +, -, *, / operators and empty spaces. 
The integer division should truncate toward zero.

Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/

var line = " 51- 62*2+3/ 5/44";
// var line = "14/3*2"
// var line = " 551+ 62*2+3/ 5/44"
/**
 * @param {string} s
 * @return {number}
 */
var calculate = function(line) {
    //remove space first;
    line = line.replace(/\s+/g,'');

    //get all numbers
    let reNumber = new RegExp(/\d+/g);
    let numbers = line.match(reNumber);
    for(let i in numbers){
      numbers[i] = parseInt(numbers[i])
    }
    if(numbers.length == 1) return numbers[0];

    //get all operators
    let reOp = new RegExp(/[\+|\-|\*|\/]/g);
    let Ops = line.match(reOp);

    //deal with * and / first
    //save result to second position and set first position to null
    for(let opIndex=0; opIndex<Ops.length;++opIndex){
      if(Ops[opIndex] === "*"){
        numbers[opIndex+1] = Math.floor(numbers[opIndex] * numbers[opIndex + 1]);
        Ops[opIndex] = null;
        numbers[opIndex] = null;
      }else if(Ops[opIndex] === "/"){
        numbers[opIndex + 1] = Math.trunc(numbers[opIndex] / numbers[opIndex + 1]);
        Ops[opIndex] = null
        numbers[opIndex] = null
      }
    }

    //remove those null
    numbers = numbers.filter((el)=>{
      return el != null;
    });

    Ops = Ops.filter((el)=>{
      return el != null;
    })

    //deal with the rest, + and -
    //save result to second position and set first position to null
    for(let opIndex=0; opIndex<Ops.length;++opIndex){
      // console.log(opIndex)
      if(Ops[opIndex] === "+"){
        numbers[opIndex+1] = numbers[opIndex] + numbers[opIndex + 1];
        Ops[opIndex] = null;
        numbers[opIndex] = null;
      }else if(Ops[opIndex] === "-"){
        numbers[opIndex + 1] = numbers[opIndex] - numbers[opIndex + 1];
        Ops[opIndex] = null
        numbers[opIndex] = null
      }
    }
    
    //last number is result
    return numbers[numbers.length-1];
};


//主要概念是先做乘除 最後再加減
//loop through每一個el
//是數字就concat起來
//是OP的話，參考前一次的OP做動作
//若是+ 則push進stack
//若是- 則加上負號push進stack
//若是* 則pop一位相乘後 push進stack
//若是/ 則pop一位相除後 push進stack
//最後將stack裡的東西全部相加為答案
var calculate = function(line) {
  line = line.replace(/\s+/g,'');

  let number = '',
    calStack = [],
    prevOps = null;

  for(let i in line){
    //is a number, the concat it to a number string
    if(!isNaN(line[i])){
      number = number+line[i];
    } 
    if(isNaN(line[i]) || i == line.length-1){
      if(prevOps == null){
        calStack.push(Number(number));
      }else if(prevOps == "+"){
        calStack.push(Number(number));
      }else if(prevOps == "-"){
        calStack.push(Number(-number));
      }else if(prevOps == "*"){
        calStack.push(calStack.pop() * Number(number))
      }else if(prevOps == "/"){
        calStack.push(Math.trunc(calStack.pop() / Number(number)))
      }
      prevOps = line[i];
      number = "";
    }
  }

  return calStack.reduce((el,a)=>{
    return el+a;
  });
}

var result = calculate(line);
console.log(result);