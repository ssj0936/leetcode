/*Spiral Matrix
Solution
Given a matrix of m x n elements (m rows, n columns)
return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/
var data = [
  [1],
  [5],
  [2]
];
var data = [
  [1, 2, 3, 4]
];

var data = [
  []
];

var data = [
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
];

/**
 * @param {number[][]} matrix
 * @return {number[]}
 */
var spiralOrder = function(matrix) {
  if(matrix.length == 0) return matrix;
  
  const move = [
    [0,1],//right
    [1,0],//down
    [0,-1],//left
    [-1,0],//up
  ];
  let m = matrix.length, n = matrix[0].length;
  let matrixSize = m*n;

  //start at a fake point[0,-1] left of start point[0,0]
  let currentRow = 0,currentCol = -1;
  let currentMoveIndex = 0;
  let result = [];

  //repeat m*n time
  while(matrixSize>0){
    let tempRow,tempCol;
    tempRow = currentRow + move[currentMoveIndex][0],
    tempCol = currentCol + move[currentMoveIndex][1];

    while(tempRow<0 || tempCol<0 
    || tempRow> (m-1) || tempCol > (n-1) 
    || matrix[tempRow][tempCol] == null){
      currentMoveIndex = (currentMoveIndex+1)%4;
      tempRow = currentRow + move[currentMoveIndex][0],
      tempCol = currentCol + move[currentMoveIndex][1];
    }
    // console.log(`tempRow:${tempRow},tempCol:${tempCol}`);

    currentRow = tempRow;
    currentCol = tempCol;
    result.push(matrix[currentRow][currentCol]);
    //set passed point to null
    matrix[currentRow][currentCol] = null;
    matrixSize--;
  }
  return result;
};

let result = spiralOrder(data);
console.log(result);