package com.timothy.wordladder;

import java.util.*;

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {

    // System.out.println(beginWord);
    // System.out.println(endWord);
    // System.out.println(wordList.toString());

    Set<String> hashset = new HashSet<String>(wordList);
    if(!hashset.contains(endWord)) return 0;

    Queue<String> queue = new LinkedList<String>();
    queue.offer(beginWord);
    int height = 0;
    while(!queue.isEmpty()){
      ++height;
      int HowManyNode = queue.size();
      for(int time = 0; time <HowManyNode ; ++time){
        String word = queue.poll();
        char[] wordChar = word.toCharArray();
        for(int i=0 ; i<wordChar.length ; ++i){
          char originalChar = wordChar[i];
          for(char alphabet = 'a';alphabet<='z';++alphabet){
            // System.out.println(alphabet);
            if(wordChar[i] == alphabet) continue;

            // String tmpStr = word;
            // char[] wordCharTmp = tmpStr.toCharArray();
            wordChar[i] = alphabet;
            String tmpStr = String.valueOf(wordChar);
            // System.out.println(tmpStr);
            

            if(tmpStr.equals(endWord)) {
              // System.out.println(tmpStr);
              // System.out.println("return");
              return height+1;
            }
            if(hashset.contains(tmpStr)){
              // System.out.println(tmpStr);
              // System.out.println("-----------get-----------");
              queue.offer(tmpStr);
              hashset.remove(tmpStr);
            }
          }
          wordChar[i] = originalChar;
        }
      }
    }
    return 0;
  }

  public int ladderLength_2(String beginWord, String endWord, List<String> wordList) {
    if(!wordList.contains(endWord)) return 0;

    Set<String> wordListSet = new HashSet<>(wordList);
    Set<String> startSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();

    int height = 0;
    startSet.add(beginWord);
    endSet.add(endWord);
    wordListSet.remove(beginWord);
    wordListSet.remove(endWord);

    while(!startSet.isEmpty() && !endSet.isEmpty()){
      height++;
      Set<String> nextRound = new HashSet<>();
      for(String word: startSet){
        char[] wordToChar = word.toCharArray();
        for(int charIndex = 0 ; charIndex<wordToChar.length ;++charIndex){
          char originalChar = wordToChar[charIndex];
          for(char c='a';c<='z';c++){
            if(c == originalChar) continue;

            wordToChar[charIndex] = c;
            String tmpWord = String.valueOf(wordToChar);
            if(endSet.contains(tmpWord)){
              return ++height;
            }else if(wordListSet.contains(tmpWord)){
              nextRound.add(tmpWord);
              wordListSet.remove(tmpWord);
            }
          }
          wordToChar[charIndex] = originalChar;
        }
      }
      if(nextRound.size()<endSet.size()){
        startSet = nextRound;
        endSet = endSet;
      }else{
        startSet = endSet;
        endSet = nextRound;
      }
    }
    return 0;
  }

  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    String[] words = {"hot","dot","dog","lot","log","cog"};
    List<String> wordList = Arrays.asList(words);

    Solution s = new Solution();
    int result = s.ladderLength_2(beginWord,endWord,wordList);
    System.out.println(result);
  }
}