/*jshint esversion: 6 */

/**
 * @param {number[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */

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
};

var d = [
    [0, 1, 0],
    [0, 0, 1],
    [1, 1, 1],
    [0, 0, 0]
];
var gameOfLife = function (board) {

    const STATE_DEAD = 0,
        STATE_LIVE = 1,
        STATE_LIVE_TO_DEAD = 2,
        STATE_DEAD_TO_LIVE = 3;

    for (let row = 0; row < board.length; ++row) {
        for (let col = 0; col < board[row].length; ++col) {
            let elementArround = [
                (row - 1 < 0 || col - 1 < 0) ? 0 : board[row - 1][col - 1],
                (row - 1 < 0) ? 0 : board[row - 1][col],
                (row - 1 < 0 || col + 1 > board[row - 1].length - 1) ? 0 : board[row - 1][col + 1],
                (col - 1 < 0) ? 0 : board[row][col - 1],
                ((col + 1) > (board[row].length - 1)) ? 0 : board[row][col + 1],
                (row + 1 > (board.length - 1) || (col - 1) < 0) ? 0 : board[row + 1][col - 1],
                (row + 1 > board.length - 1) ? 0 : board[row + 1][col],
                (row + 1 > board.length - 1 || col + 1 > board[row + 1].length - 1) ? 0 : board[row + 1][col + 1]
            ];
            console.log(elementArround);
            for (var i in elementArround) {
                if (parseInt(elementArround[i] / 2) > 0)
                    elementArround[i] = (elementArround[i] + 1) % 2;
            }
            let sumArround = elementArround.reduce((a, e) => {
                return a + e;
            }, 0);

            //dead unit
            if (board[row][col] == 0)
                board[row][col] = (sumArround == 3) ? STATE_DEAD_TO_LIVE : STATE_DEAD;
            else
                board[row][col] = (sumArround >= 2 && sumArround <= 3) ? STATE_LIVE : STATE_LIVE_TO_DEAD;
            // printMatrix(board);
        }
    }
    // printMatrix(board);
    for (let row = 0; row < board.length; ++row) {
        for (let col = 0; col < board[row].length; ++col) {
            board[row][col] %= 2;
        }
    }
    // printMatrix(board);
};

var gameOfLife = function (board) {
    const STATE_DEAD = 0,
        STATE_LIVE = 1,
        STATE_LIVE_TO_DEAD = 2,
        STATE_DEAD_TO_LIVE = 3;
    const coorDiff = [
        [-1,-1],
        [-1,0],
        [-1,1],
        [0,-1],
        [0,1],
        [1,-1],
        [1,0],
        [1,1],
    ];

    board.forEach((row,i)=>{
        board[i].forEach((col,j)=>{
            var neighborAliveSum = 0;
            coorDiff.forEach((el,index)=>{
                let neighborRow = i+el[0],
                    neighborCol = j+el[1];
                if(neighborRow<0 || neighborCol<0 || neighborRow > board.length-1 || neighborCol > board[i].length-1) {}else{
                    if (board[neighborRow][neighborCol] == STATE_LIVE_TO_DEAD || board[neighborRow][neighborCol] == STATE_DEAD_TO_LIVE)
                        neighborAliveSum += (board[neighborRow][neighborCol] + 1) % 2;
                    else
                        neighborAliveSum += board[neighborRow][neighborCol];
                }
            });

            if (board[i][j] == 0)
                board[i][j] = (neighborAliveSum == 3) ? STATE_DEAD_TO_LIVE : STATE_DEAD;
            else
                board[i][j] = (neighborAliveSum >= 2 && neighborAliveSum <= 3) ? STATE_LIVE : STATE_LIVE_TO_DEAD;
        });
    });
    board.forEach((row,i)=>{
        board[i].forEach((col,j)=>{
            board[i][j] %= 2;
        });
    });
};

printMatrix(d);
gameOfLife(d);
printMatrix(d);