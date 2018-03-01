/**
 * @param {character[][]} grid
 * @return {number}
 */
var numIslands = function(grid) {
    var foundCount = 0;
    for(var [x,subArr] of grid.entries()){
        for(var [y,value] of subArr.entries()){
            if(value != "0" && value != "c" ){
                recursiveFindLand(x,y);
                ++foundCount;
            }
        }
    }
    // console.log(grid);
    return foundCount;

    function recursiveFindLand(x,y,from){
        // console.log(`${x},${y}`);
        if(grid[x][y] == "0" || grid[x][y] == "c") return;

        grid[x][y] = "c";
        // console.log([x,y])
        if(grid[0].length > (y+1)){
            recursiveFindLand(x,y+1);
        }
        if(0 <= (y-1)){
            recursiveFindLand(x,y-1);
        }
        if(grid.length > (x+1)){
            recursiveFindLand(x+1,y);
        }
        if(0<=(x-1)){
            recursiveFindLand(x-1,y);
        }
    }
};

var grid = [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["1","1","0","1","1"]];
var result = numIslands(grid);
console.log(result);