import java.io.IOException;

public class UniqueWords {

    private BookReader book;

    public UniqueWords() throws IOException {
        book = new BookReader("WarAndPeace.txt");
        addUniqueWordsToHashTable();

//        addUniqueWordsToAVL();
//        addUniqueWordsToBST();
//        addUniqueWordsToArrayList();
//        addUniqueWordsToLinkedList();
//        addUniqueWordsToOrderedList();
//        addUniqueWordsToTrie();
    }

    public void addUniqueWordsToHashTable() {
        System.out.print("Adding unique words to a Hash Table...");
        var Unique = new MyHashTable<String, String>(32768);
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {
            if (Unique.get(book.words.current()) == null) {
                Unique.put(book.words.current(), book.words.current());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();;
        System.out.println("in "+(endTime-startTime)/1000000 + " milliseconds.");
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");
        System.out.println(Unique.maxProbe+" max probes");
    }

    /*public void addUniqueWordsToAVL(){
        System.out.print("Adding unique words to an AVL tree...");
        var Unique = new MyBinarySearchTree<String>(true);
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {
            if (Unique.find(book.words.current()) == null) {
                Unique.add(book.words.current());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();;
        System.out.println("in "+(endTime-startTime)/1000000 + " milliseconds.");
        System.out.println("The AVL Tree has a height of "+Unique.height());
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");
        System.out.println(Unique.rotations+" rotations");
    }

    public void addUniqueWordsToBST(){
        System.out.print("Adding unique words to a BST...");
        var Unique = new MyBinarySearchTree<String>();
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {
            if (Unique.find(book.words.current()) == null) {
                Unique.add(book.words.current());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();;
        System.out.println("in "+(endTime-startTime)/1000000 + " milliseconds.");
        System.out.println("The Binary Search Tree has a height of "+Unique.height());
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");
    }
    public void addUniqueWordsToTrie() {
        System.out.print("Adding unique words to a trie...");
        var Unique = new MyTrie();
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {
            if (!Unique.find(book.words.current())) {
                Unique.insert(book.words.current());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000 + " milliseconds.");
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");
    }

    public void addUniqueWordsToLinkedList(){
        System.out.print("Adding unique words to a linked list...");
        var Unique = new MyLinkedList<String>();
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {
            if(!Unique.contains(book.words.current())){
                Unique.addBefore(book.words.current());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000000+" seconds.");
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");

        System.out.print("Bubble sorting linked list...");
        startTime = System.nanoTime();
        Unique.sort();
        endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000000+" seconds. \n");
    }

    public void addUniqueWordsToArrayList(){
        System.out.print("Adding unique words to an array list...");
        var Unique = new MyArrayList<String>();
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {

            if(!Unique.contains(book.words.current())){
                Unique.insert(book.words.current(), Unique.size());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000000+" seconds.");
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");

        System.out.print("Bubble sorting array list...");
        startTime = System.nanoTime();
        Unique.sort();
        endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000000+" seconds. \n");
    }

    public void addUniqueWordsToOrderedList(){
        System.out.print("Adding unique words to an ordered list...");
        var Unique = new MyOrderedList<String>();
        book.words.first();
        long startTime = System.nanoTime();
        for (int i = 0; i < book.words.size(); i++) {
            if(!Unique.binarySearch(book.words.current())){
                Unique.add(book.words.current());
            }
            book.words.next();
        }
        long endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000000+" seconds.");
        System.out.println(Unique.size()+" unique words");
        System.out.println(Unique.comparisons+" comparisons");

        System.out.print("Bubble sorting array list...");
        startTime = System.nanoTime();
        endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000000+" seconds. \n");
    }*/
        public static void main (String[]args) throws IOException {
            UniqueWords test = new UniqueWords();
    }
}