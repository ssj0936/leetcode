/*
911. Online Election(Medium)
You are given two integer arrays persons and times. In an election, the ith vote was cast for persons[i] at time times[i].
For each query at a time t, find the person that was leading the election at time t. Votes cast at time t will count towards our query. In the case of a tie, the most recent vote (among tied candidates) wins.
Implement the TopVotedCandidate class:

TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned rules.

Example 1:
Input
["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
Output
[null, 0, 1, 1, 0, 0, 1]

Explanation
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
topVotedCandidate.q(15); // return 0
topVotedCandidate.q(24); // return 0
topVotedCandidate.q(8); // return 1

Constraints:

1 <= persons.length <= 5000
times.length == persons.length
0 <= persons[i] < persons.length
0 <= times[i] <= 10^9
times is sorted in a strictly increasing order.
times[0] <= t <= 109
At most 104 calls will be made to q.

 */

/*
依然是非常難以理解的題意，簡單的來說給你person 和 time兩個arr，在t[i]時間點有一個人會投給p[i]號候選人，q是一個query function告訴你在某個時間點，誰是暫時的贏家
一個小細節是：如果平手的話 就取上一次結果

目前的想法：
先算出time中每個時間點的贏家，然後在每次的query中，找出適合的時間點取值
因為time是已經排列好的，應該是可以用binary search找出適當的位子
 */
class TopVotedCandidate(persons: IntArray, val times: IntArray) {

    private val mapV2 = IntArray(persons.size){0}
    var currentWinner = -1
    //tmpWinnerReflectTime[i] 代表在time[i]時當下的贏家
    private val tmpWinnerReflectTime = IntArray(times.size){-1}
    init {
        for(i in persons.indices){
            ++mapV2[persons[i]]
            if((currentWinner != -1 && mapV2[persons[i]] >= mapV2[currentWinner])
                || currentWinner == -1){
                currentWinner = persons[i]
            }
            
            tmpWinnerReflectTime[i] = currentWinner
        }
//        println(times.binarySearch(25))
//
//        println(persons.contentToString())
//        println(times.contentToString())
//        println(tmpWinnerReflectTime.contentToString())
    }

    fun q(t: Int): Int {

        val searchResult = times.binarySearch(t).run {
            if(this<0) ((this+1)*-1) -1
            else this
        }
        return tmpWinnerReflectTime[searchResult]
    }

    //自己寫binary search試看看
    //結論：邊際條件需要多注意
    fun qV2(t: Int): Int {
        var left = 0
        var right = times.lastIndex
        while (left<=right){
            val mid = (left+right)/2
            if(times[mid] == t) {
                left = mid
                break
            }else if(times[mid] < t){
                left = mid+1
            }else{
                right = mid-1
            }
        }

        return if(times[left] == t) tmpWinnerReflectTime[left]
        else tmpWinnerReflectTime[left-1]
    }



}

fun main(args: Array<String>) {
    val person = intArrayOf(0, 1, 1, 0, 0, 1, 0)
    val time = intArrayOf(0, 5, 10, 15, 20, 25, 30)
    val queryList = listOf(3,12,25,15,24,8)

    val solutionClass = TopVotedCandidate(person,time)
    for(q in queryList){
        val result = solutionClass.qV2(q)
        println("t:$q, result:$result")
    }
}