import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BookReader {

    //Book will hold our txt file and put it as a string, words will hold all the words
    public String book;
    public MyLinkedList<String> words;

    public BookReader(String filename) throws IOException {
        words = new MyLinkedList<>();
        book ="";

        readBook(filename);
        parseWords();


    }
    public void readBook(String filename) throws IOException{
        int charCount = 0;
        StringBuilder str = new StringBuilder();
        System.out.print("Reading input file "+filename+"... ");
        long startTime = System.nanoTime();
        File file = new File(filename);     //Creation of File Descriptor for input file
        FileReader fileReader =new FileReader(file);   //Creation of File Reader object
        BufferedReader bufferedReader =new BufferedReader(fileReader);  //Creation of BufferedReader object
        int character = 0;

        while((character = bufferedReader.read()) != -1) {
            str.append((char) character);
            charCount++;
        }
        book = String.valueOf(str);
        long endTime = System.nanoTime();
        System.out.println(charCount+" characters in "+(endTime-startTime)/1000000+" milliseconds. \n");
    }
    public void parseWords() {
        System.out.print("Finding words and adding them to a linked list... ");
        long startTime = System.nanoTime();

        boolean isWord = false;
        StringBuilder wordHolder = new StringBuilder();
        for (int i = 0; i < book.length(); i++) {
            int ascii = book.charAt(i);
            if(ascii<300) {
                if ((65 <= ascii && ascii <= 90) || (97 <= ascii && ascii <= 122) || (ascii == 39) || (48 <= ascii && ascii <= 57)) {
                    wordHolder.append((char) ascii);
                    isWord=true;
                } else if(String.valueOf(wordHolder)!="" || isWord){
                    words.addBefore(String.valueOf(wordHolder));
                    wordHolder.setLength(0);
                    isWord=false;
                }
            }

        }
        long endTime = System.nanoTime();
        System.out.println("in " + (endTime - startTime) / 1000000 + " milliseconds.");
        System.out.println("The book has " + book.length() + " characters and " + words.size() + " words. \n");
    }

    public static void main(String[] args) throws IOException {
        BookReader bookReader = new BookReader("test.txt");
    }

}