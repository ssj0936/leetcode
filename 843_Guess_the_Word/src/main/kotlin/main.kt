/*
843. Guess the Word(Hard)

This is an interactive problem.
You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.
You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.
This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it will return -1 instead.
For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
Output: You guessed the secret word correctly.
Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.

Example 2:
Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
Output: You guessed the secret word correctly.

Constraints:

1 <= wordlist.length <= 100
wordlist[i].length == 6
wordlist[i] consist of lowercase English letters.
All the strings of wordlist are unique.
secret exists in wordlist.
numguesses == 10
 */
/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     fun guess(word: String): Int {}
 * }
 */

interface Master {
    fun guess(word: String): Int
}
class Solution {

    /*
    有點看答案才想到
    簡單的說就是隨機拿一個word去guess，如果return 6 那表示猜中
    如果不是 就把這個word和wordlist裡其他自去match，只留下match數和guess return數相同的word
    在繼續進行下一輪

    有個問題，本來都只固定取arr[0]，但某一個case裡面會剛好要11 turn才能猜中，改成arr[rand]才有辦法符合規定
     */
    fun findSecretWord(wordlist: Array<String>, master: Master) {
        var arr = wordlist.toList()
        var count = 0

        repeat(10){
            val target = arr[(arr.indices).random()]
            var guessResult = master.guess(target)
            if(guessResult == 6) return
            arr = arr.filter{it!=target && match(target,it)==guessResult}

            ++count
        }
    }

    fun match(word1:String, word2:String):Int{
        var count = 0
        for(i in word1.indices){
            if(word1[i] == word2[i]) ++count
        }
        return count
    }

    /*
    這是速度最快的答案，也沒有random的要素在，重點在於挑選guess的候選人，挑出一個最有潛力最有鑑別度的

    解題大意是：一樣進行10回合
    以一個2D array，每一次對於每個字串，其中的每一個位子(0-6)，出現的每一個字母，做計算
    選擇候選人時，loop過每一個字串，去剛剛的count表中，算出這個字串的分數，取出最高分的字串當作候選人

    之所以選一個分數最高的，也就是相對"大眾臉"的word，代表他有一次篩掉大部分word的潛力
     */
    fun findSecretWordV2(wordlist: Array<String>, master: Master) {
        var arr = wordlist.toList()
        var count = 0

        repeat(10){

            //scoring
            val scoreTable = Array(6){IntArray(26){0} }
            for(word in arr){
                for(i in 0 until 6){
                    ++scoreTable[i][word[i]-'a']
                }
            }

            //get candidate
            var max = 0
            var guessCandidate = arr[0]
            for(word in arr){
                var score = 0

                for(i in 0 until 6){
                    score += scoreTable[i][word[i]-'a']
                }

                if(score>max){
                    max = score
                    guessCandidate = word
                }
            }

            val guessResult = master.guess(guessCandidate)
            if(guessResult == 6) return
            else{
                arr = arr.filter { it!=guessCandidate && match(it,guessCandidate)==guessResult }
            }
        }
    }

}

fun main(args: Array<String>) {
    println("Hello World!")
}