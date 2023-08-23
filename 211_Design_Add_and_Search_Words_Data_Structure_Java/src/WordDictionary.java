class WordDictionary {
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        var pointer = root;
        for(int i=0;i<word.length();++i){
            int index = word.charAt(i) - 'a';
            if(pointer.dict[index]==null)
                pointer.dict[index] = new TrieNode();

            pointer = pointer.dict[index];
        }
        pointer.value = word;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int index, TrieNode pointer){
        if(pointer == null)
            return false;

        if(index == word.length()){
            return pointer.value!=null;
        }

        if(word.charAt(index)!='.'){
            return search(word, index+1, pointer.dict[word.charAt(index)-'a']);
        }else {
            for(TrieNode n:pointer.dict){
                if(n != null && search(word, index+1, n))
                    return true;
            }
            return false;
        }
    }
}

class TrieNode{
    String value = null;
    TrieNode[] dict = new TrieNode[26];
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */