var h = [0,1,0,2,1,0,1,3,2,1,2,1];
var trap = function(height){
  console.log(height);
  //if length<3 means no pits existing
  if(height.length < 3){
    console.log("height.length < 3, return 0")
    return 0;
  }

  //get head and tail element
  var temp = height.slice(0);
  var head = temp.splice(0,1);
  var tail = temp.splice(temp.length-1,1);
  var isHeadAndTailAreLargest = true;
  for(let el of temp){
    if(!(el <= head && el<=tail)){
      isHeadAndTailAreLargest = false;
      break;
    }
  }

  //if head and tail are the largest of the rest
  //then loop through every element of it 
  //waterlevel of unit = Math.min(head,tail) - unit height
  if(isHeadAndTailAreLargest){
    var waterHeight = Math.min(head,tail);
    var sum = 0;
    for(let el of temp){
      let diff = waterHeight - el;
      sum = (diff < 0) ? (sum + 0) : (sum + diff)
    }
    console.log("pit -> return:"+sum);
    return sum
  }
  //find max and split from it
  else{
    var max = Math.max(...height);
    var maxIndex = height.indexOf(max);
    //if max value is located on arr[0] or arr[arr.length-1]
    //set max to second large value
    if(height[0]==max || height[height.length-1]==max){
      var temp;
      if(height[0]==max){
        temp = height.slice(1);
        max = Math.max(...temp);
        maxIndex = temp.indexOf(max) + 1;
      }else if(height[height.length-1]==max){
        temp = height.slice(0,-1);
        max = Math.max(...temp);
        maxIndex = temp.indexOf(max);
      }
    }

    //split from it and recursive culculate sum of those
    var left = height.slice(0,maxIndex+1);
    var right = height.slice(maxIndex);
    console.log("L:"+left)
    console.log("R:"+right)
    var sum = trap(left)+trap(right);
    
    return sum;
  }
}

var result = trap(h);