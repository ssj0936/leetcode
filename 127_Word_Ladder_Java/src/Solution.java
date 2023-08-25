import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class Solution {
    HashSet<String> visited = new HashSet<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        var queue = new LinkedList<String>();
        visited.add(beginWord);
        queue.offer(beginWord);

        var pathLen = 1;
        while (!queue.isEmpty()){
            var size = queue.size();
            for(int i=0;i<size;++i) {
                var pop = queue.poll();

                for (String nextWord : wordList) {
                    if (visited.contains(nextWord)) continue;
                    if (!isDistanceOne(pop, nextWord)) continue;

                    if(nextWord.equals(endWord))
                        return pathLen+1;

                    visited.add(nextWord);
                    queue.offer(nextWord);
                }
            }
            ++pathLen;
        }

        return 0;
    }

    private boolean isDistanceOne(String w1, String w2){
        var diffAcc = 0;
        for(int i=0;i<w1.length();++i){
            if(w1.charAt(i) != w2.charAt(i)) {
                ++diffAcc;
                if(diffAcc>1) return false;
            }
        }
        return true;
    }
}