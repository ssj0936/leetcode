/**
 * @param {number[][]} matrix
 * @return {void} Do not return anything, modify matrix in-place instead.
 */
var setZeroes = function (matrix) {
    // n[i][j]
    //loop through first time 
    //set entire row and column of element which is null
    for (var i = 0; i < matrix.length; ++i) {
        for (var j = 0; j < matrix[i].length; ++j) {
            if (matrix[i][j] == 0) {
                // set entire row to null
                for (var pivot = 0; pivot < matrix[i].length; ++pivot){
                    if(matrix[i][pivot] == 0) continue;
                    matrix[i][pivot] = NaN;
                }
                
                // set entire column to null
                for(var pivot = 0; pivot<matrix.length;++pivot){
                    if(matrix[pivot][j]==0) continue;
                    matrix[pivot][j] = NaN;
                }
            }
        }
    }

    for (var i = 0; i < matrix.length; ++i) {
        for (var j = 0; j < matrix[i].length; ++j) {
            if(isNaN(matrix[i][j])) {
                matrix[i][j] = 0;
            }
                
        }
    }
};

var m = [
    [0, 1, 2, 0],
    [3, 4, 5, 2],
    [1, 3, 1, 5]
];

var printMatrix = function (matrix) {
    var table = document.createElement("table");
    table.setAttribute("border", 1);

    for (var line of matrix) {
        var tr = document.createElement("tr");
        table.appendChild(tr);
        for (var element of line) {
            var td = document.createElement("td");
            td.innerHTML = element;
            tr.appendChild(td);
        }
    }
    document.getElementById('app').appendChild(table);
}
printMatrix(m);
setZeroes(m);
printMatrix(m);
// console.log(m);