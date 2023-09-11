public class MyTrie {

    //Node that has fields linked to identity through, character held, terminal (is it the end of the word) and children (potential future chain paths)
    private static class Node implements Comparable<Node> {
        public boolean terminal;
        public char character;
        public MyOrderedList<Node> children;

        public Node(char character) {
            children = new MyOrderedList<>();
            terminal = false;
            this.character = character;

        }

        @Override
        //We should compare nodes by their character (kind of poetic)
        public int compareTo(Node o) {
            return (this.character - o.character);
        }

        //Helps us add children only when necessary, leafs shouldn't have children
        public void addChildren() {
            String chars = "0123456789'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            for (int i = 0; i < chars.length(); i++) {
                children.add(new Node(chars.charAt(i)));
            }
        }
    }

    private Node root;
    private int size;
    public long comparisons;

    //Start at root, keep track of comparisons and size
    public MyTrie() {
        size = 0;
        comparisons = 0;
        root = new Node(' ');
        root.addChildren();
    }

    //Binary searches each children to insert string character by character
    public void insert(String item) {
        Node current = root;
        for (int i = 0; i < item.length(); i++) {
            comparisons++;
            current = current.children.binarySearch(new Node(item.charAt(i)));
            if (i == item.length() - 1 && !current.terminal) {
                current.terminal = true;
                size++;
            } else if (current.children.isEmpty()) {
                current.addChildren();
            }
        }
    }

    //Similar to insert, remove terminal boolean and subtract size
    public void remove(String item) {
        Node current = root;
        for (int i = 0; i < item.length(); i++) {
            current = current.children.binarySearch(new Node(item.charAt(i)));
            if (i == item.length() - 1 && current.terminal) {
                current.terminal = false;
                size--;
            }
            if (current.children.isEmpty()) break;
        }
    }

    //Similar to insert and remove, just return whether terminal is on at the end of the word.
    public boolean find(String item) {

        Node current = root;
        for (int i = 0; i < item.length(); i++) {
            comparisons++;
            current = current.children.binarySearch(new Node(item.charAt(i)));
            if (i == item.length() - 1) {
                return current.terminal;
            }
            if (current.children.isEmpty()) break;
        }
        comparisons++;
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    //Use addwords to collect data for stringbuilder
    public String toString() {
        Node node = root;
        String str = "";
        StringBuilder output = new StringBuilder();
        output.append("[");
        addWords(node, str, output);
        output.setLength(output.length()-2);
        output.append("]");
        return output.toString();
    }

    //iterate through children and recurse through the node, when a terminal is found to be true, add the str to output builder
    private void addWords(Node node, String str, StringBuilder output) {
        for (int i = 0; i < node.children.size(); i++) {

            if (node.children.get(i).terminal) {
                output.append(str+node.children.get(i).character+", ");
            }
            if (!node.children.get(i).children.isEmpty()) {
                addWords(node.children.get(i), str + node.children.get(i).character, output);
            }

        }
    }

}
   /* class MyTrieTest {
        public static void main(String[] args) {
            MyTrie trie = new MyTrie();
            trie.insert("hello");
            trie.insert("a");
            trie.insert("aardvark");
            System.out.println(trie);//true

        }
    }*/
