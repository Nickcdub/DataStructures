import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanEncoder {

    private String inputFileName;
    private String outputFileName;
    private String codesFileName;
    private BookReader book;
    private MyOrderedList<FrequencyNode> frequencies;
    private MyOrderedList<CodeNode> codes;
    private MyHashTable<String, Integer> frequenciesHash;
    private MyHashTable<String, String> codesHash;
    private HuffmanNode huffmanTree;
    private byte[] encodedText;
    boolean wordCode;


    class FrequencyNode implements Comparable<FrequencyNode>{

        char character;
        Integer count;

        public FrequencyNode(char character){
            this.character = character;
            count = 0;
        }

        @Override
        public int compareTo(FrequencyNode o) {
            return this.character - o.character;
        }

        public String toString(){
            return (this.character+":"+this.count);
        }
    }

    class HuffmanNode implements Comparable<HuffmanNode>{

        char character;
        String word;
        Integer weight;
        HuffmanNode right;
        HuffmanNode left;

        public HuffmanNode(char character, Integer weight){
            this.character = character;
            this.weight = weight;
        }

        public HuffmanNode(String word, Integer weight){
            this.word = word;
            this.weight = weight;
        }

        public HuffmanNode(HuffmanNode right, HuffmanNode left){
            this.left = left;
            this.right = right;
            this.weight = left.weight + right.weight;
        }



        @Override
        public int compareTo(HuffmanNode o) {
            return this.weight - o.weight;
        }

        public String toString(){
            if (!wordCode) return (this.character+":"+this.weight);
            return (this.word+":"+this.weight);
        }
    }

    class CodeNode implements Comparable<CodeNode>{

        char character;
        String code;

        public CodeNode(char character){
            this.character = character;
            this.code = code;
        }

        @Override
        public int compareTo(CodeNode o) {
            return this.character - o.character;
        }

        public String toString(){
            return(this.character+":"+this.code);
        }
    }

    public HuffmanEncoder() throws IOException {
        this.inputFileName = "WarAndPeace.txt";
        this.outputFileName = "WarAndPeace-compressed.bin";
        this.codesFileName = "WarAndPeace-codes.txt";
        huffmanTree = null;

        book = new BookReader(inputFileName);
        frequencies = new MyOrderedList<>();
        codes = new MyOrderedList<>();
        frequenciesHash = new MyHashTable<>(32768);
        codesHash = new MyHashTable<>(32768);

        wordCode = true;
        countFrequency();
        buildTree();
        encode();
        writeFiles();

    }

    private void countFrequency(){
        FrequencyNode charCheck;
        Integer valueCheck;
        MyLinkedList<String> bookList = book.wordsAndSeparators;
        long startTime = System.nanoTime();
        if(!wordCode) {
            System.out.print("Counting frequencies of characters ...");
            for (int i = 0; i < book.book.length(); i++) {
                charCheck = frequencies.binarySearch(new FrequencyNode(book.book.charAt(i)));
                if (charCheck == null) frequencies.add(new FrequencyNode(book.book.charAt(i)));
                frequencies.binarySearch(new FrequencyNode(book.book.charAt(i))).count++;
            }
            long endTime = System.nanoTime();
            System.out.println(frequencies.size()+" unique characters found in "+(endTime-startTime)/1000000+" milliseconds. \n");
        }else{
            System.out.print("Counting frequencies of words and separators ...");
            bookList.first();
            while(bookList.current()!=null){
                valueCheck = frequenciesHash.get(bookList.current());
                if(valueCheck == null) frequenciesHash.put(bookList.current(),1);
                else frequenciesHash.put(bookList.current(), valueCheck+1);
                bookList.next();
            }
            long endTime = System.nanoTime();
            System.out.println(frequenciesHash.size()+" unique words and separators found in "+(endTime-startTime)/1000000+" milliseconds. \n");
        }

    }

    //This appears to work correctly
    private void buildTree(){
        System.out.print("Building a Huffman tree and reading codes... ");

        long startTime = System.nanoTime();
        //Create the priority queue that will weigh the tree
        MyPriorityQueue<HuffmanNode> tree = new MyPriorityQueue();
       if(wordCode){
           for (int i = 0; i < frequenciesHash.size(); i++) {
               String key = frequenciesHash.keys.get(i);
               tree.insert(new HuffmanNode(key, frequenciesHash.get(key)));
           }
       }else {
           for (int i = 0; i < frequencies.size(); i++) {
               tree.insert(new HuffmanNode(frequencies.get(i).character, frequencies.get(i).count));
           }
       }
        while(tree.size()>1){
            tree.insert(new HuffmanNode(tree.removeMin(), tree.removeMin()));
        }
        huffmanTree = tree.min();
        extractCodes(huffmanTree,"");
        long endTime = System.nanoTime();
       // System.out.println(tree.min().weight);
       // System.out.println(book.wordsAndSeparators.size() - book.words.size());
        System.out.println("in "+(endTime-startTime)/1000000+" milliseconds. \n");
//        System.out.println(book.wordsAndSeparators.size());
//        System.out.println(huffmanTree); //This returns the total weight of the tree (same as # of characters)
    }

    private void extractCodes(HuffmanNode root, String code){

        if (root.left != null) {
            extractCodes(root.left, code + "0");
        }
        if (root.right != null) {
            extractCodes(root.right, code + "1");
        }
        if(root.left==null && root.right==null) {
           if(wordCode){
               codesHash.put(root.word, code);
           }
           else{
                CodeNode temp = new CodeNode(root.character);
                temp.code = code;
                codes.add(temp);
            }
        }
    }

    private void encode() {
        System.out.print("Encoding message...");
        long startTime = System.nanoTime();

//        //Stringbuilder will build the string of bits
        StringBuilder str = new StringBuilder();
//        //This iterates through the book and adds each character's corresponding bit code to the stringbuilder
//

        if(wordCode){
            book.wordsAndSeparators.first();
            for (int i = 0; i < book.wordsAndSeparators.size(); i++) {
                str.append(codesHash.get(book.wordsAndSeparators.current()));
                book.wordsAndSeparators.next();
            }
        }else {
            for (int i = 0; i < book.book.length(); i++) {
                str.append(codes.binarySearch(new CodeNode(book.book.charAt(i))).code);
            }
        }

        //Bits carries the entire text in bits
        String bits = str.toString();
        System.out.print(bits.length()+" bits converted to ");
        //This splits the bits into groups of 8 and adds them to the bytes array
        String[] bytes = bits.split("(?<=\\G.{" + 8 + "})");
        System.out.println(bytes.length+" bytes");

        //This initializes encodedText with the amount of bytes needed to encode
        encodedText = new byte[bytes.length];
        //This iterates over bytes and adds each byte to encodedText
        for (int i = 0; i < bytes.length; i += 8) {
            byte b = (byte) Integer.parseInt(bytes[i], 2);
            encodedText[i] = b;
        }
        long endTime = System.nanoTime();
        System.out.println("in "+(endTime-startTime)/1000000+" milliseconds. \n");
    }


    private void writeFiles() throws IOException {
        FileWriter writer = new FileWriter(codesFileName);
        writer.write(codesHash.toString());
        writer.close();

        try (FileOutputStream outputStream = new FileOutputStream(outputFileName)) {
            outputStream.write(encodedText);
        }

    }
}

class Huffmantest{
    public static void main(String[] args) throws IOException {
        HuffmanEncoder test = new HuffmanEncoder();
    }
}
