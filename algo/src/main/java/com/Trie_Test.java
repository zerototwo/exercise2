package com;

import java.util.HashMap;


public class Trie_Test {

    public static void main(String args[]){
        System.out.println(Long.MIN_VALUE);
        Trie_Tree trie = new Trie_Tree();
        trie.insert("I");
        trie.insert("Love");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("China");
        trie.insert("xiaoliang");
        trie.insert("xiaoliang");
        trie.insert("man");
        trie.insert("handsome");
        trie.insert("love");
        trie.insert("chinaha");
        trie.insert("her");
        trie.insert("know");

        HashMap<String, Integer> map = trie.getAllWords();

        for (String key : map.keySet()) {
            System.out.println(key + " 出现: " + map.get(key) + "次");
        }



        map = trie.getWordsForPrefix("chin");

        System.out.println("\n\n包含chin（包括本身）前缀的单词及出现次数：");
        for (String key : map.keySet()) {
            System.out.println(key + " 出现: " + map.get(key) + "次");
        }

        if (trie.isExist("xiaoming") == false) {
            System.out.println("\n\n字典树中不存在：xiaoming ");
        }


    }
}