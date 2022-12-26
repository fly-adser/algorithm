package SymbolTable.Trie;

public class client {
    public static void main(String[] args) {
        Trie root = new Trie();
        root.insert("leetcode");
        if (root.search("leetcode")) {
            System.out.print("there is a word named leetcode!");
        }
    }
}
