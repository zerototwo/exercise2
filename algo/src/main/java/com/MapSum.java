package com;

import java.util.*;

class MapSum {
    class TrieNode{
        boolean isWord;
        int num;
        Map<Character,TrieNode> trieNodeMap = new HashMap<>();
    }
    private TrieNode root;
    /** Initialize your data structure here. */
    public MapSum() {
        this.root = new TrieNode();
    }
    
    public void insert(String key, int val) {
        TrieNode cur = this.root;
        for(int i=0;i<key.length();i++){
            char ch = key.charAt(i);
            if(cur.trieNodeMap.get(ch)==null){
                cur.trieNodeMap.put(ch,new TrieNode());
            }
            if(i<key.length()-1){
                cur.trieNodeMap.get(ch).num = cur.trieNodeMap.get(ch).num+val;

            }else{
                cur.trieNodeMap.get(ch).num = val;
            }
            cur = cur.trieNodeMap.get(ch);
        }
     
        
    }
    
    public int sum(String prefix) {
        TrieNode cur = this.root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(cur.trieNodeMap.get(ch)==null){
                return 0;
            }
            cur = cur.trieNodeMap.get(ch);
        }
        return cur.num;
    }

    public static void main(String[] args) {

        List<Integer> list2 = new LinkedList<>();
        list2.add(0,1);
        list2.add(0,2);
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        StringTokenizer tokens = new StringTokenizer(sentence," ");
        dictionary.sort((o1,o2)->o1.length()-o2.length());
        StringJoiner joiner = new StringJoiner(" ");
        while(tokens.hasMoreTokens()){
            String word = tokens.nextToken();
            boolean isReplace = false;
            for(String root:dictionary){
                if(word.startsWith(root)){
                    joiner.add(root);
                    isReplace = true;
                    break;
                }
            }
            if(!isReplace) joiner.add(word);
        }
        return joiner.toString();
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */