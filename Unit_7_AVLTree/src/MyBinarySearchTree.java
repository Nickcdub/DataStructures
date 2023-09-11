public class MyBinarySearchTree<T extends Comparable<T>> {

    private class Node {
        T item;
        Node right, left;
        int height;
        int balanceFactor;

        public Node(T item) {
            this.item = item;
            height = -1;
            balanceFactor = 0;
        }

        public String toString() {
            return this.item + ":H" + height+"B:"+balanceFactor;
        }
    }

    boolean balancing;
    Node root;
    int size;
    long comparisons;
    long rotations;

    public MyBinarySearchTree(boolean balancing) {
        root = new Node(null);
        size = 0;
        comparisons = 0;
        this.balancing = balancing;
        rotations = 0;
    }

    public void add(T item) {
        if (size == 0) {
            root.item = item;
            root.height++;
        } else {
            add(item, root);
        }
        size++;
    }

    private Node add(T item, Node subtree) {
        //If subtree is null, we add item to the end
        if (subtree == null) {
            subtree = new Node(item);
            subtree.height = 0;
            return subtree;
        }
        //We have not found our item yet, check if our item is bigger than the subtree item, if yes, go right
        if (item.compareTo(subtree.item)>0){

            //As we recurse through the tree, we reattach everything at the ends
            subtree.right = add(item, subtree.right);
            updateHeight(subtree);
            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
                subtree = rebalance(subtree);
            }
            return subtree;
        }
        //Check if item is smaller than subtree item, if yes, go left
        else {
            //As we recurse through the tree, reattach everything at the ends
            subtree.left = add(item, subtree.left);
            updateHeight(subtree);
            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
                subtree = rebalance(subtree);
            }
            return subtree;
        }
    }


    public void remove(T item) {
        if (size == 0) return;
        else if (root != null && root.item == item && size == 1){
            root = null;
            size--;
        }
        else {
            remove(item, root);
        }
    }

    private Node remove(T item, Node subtree) {


        //If subtree is null, we return
        if (subtree == null)
            return subtree;

        //We have not found our item yet, check if our item is bigger than the subtree item, if yes, go right
        if (item.compareTo(subtree.item)>0){
            //As we recurse through the tree, we reattach everything at the ends
            subtree.right = remove(item, subtree.right);
            updateHeight(subtree);
            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
                updateHeight(root);
                subtree = rebalance(subtree);
            }

        }
        //Check if item is smaller than subtree item, if yes, go left
        else if (item.compareTo(subtree.item)<0) {
            //As we recurse through the tree, reattach everything at the ends
            subtree.left = remove(item, subtree.left);
            updateHeight(subtree);
            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
                //updateHeight(root);
                subtree = rebalance(subtree);
            }
        }

        //This occurs when the current item is the item we are looking for!
        else {
            // If we have one child, reattach the parent to that child, if we have no children, do the same, the left child will be null anyway
            if (subtree.left == null) {
                size--;
                //This is how we connect the parent to the child around our item
                return subtree.right;
            }
            else if (subtree.right == null) {
                size--;
                return subtree.left;
            }

            //The node has two children, we use our helper getmin method to find the minimum value on our larger than child
            //We want the minimum because we know that the minimum of the larger child will be less than the larger child and still more than the parent node

            subtree.item = getMin(subtree.right);
            //Clean up our mess and reattach the right subtree to the new parent node
            subtree.right = remove(subtree.item, subtree.right);
            updateHeight(subtree);
            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
                //updateHeight(root);
                subtree = rebalance(subtree);
            }
        }
        return subtree;
    }

    T getMin(Node subtree)
    {
        T min = subtree.item;
        while (subtree.left != null)
        {
            min = subtree.left.item;
            subtree = subtree.left;
        }
        return min;
    }

    T getMax(Node subtree)
    {
        T max = subtree.item;
        while (subtree.right != null)
        {
            max = subtree.right.item;
            subtree = subtree.right;
        }
        return max;
    }

    private Node rotateRight(Node node){
        if(node == null){
            return null;
        }

        Node nodeLeft = node.left; // B
        Node nodeRight = nodeLeft.right; // F

        //Rotation

        nodeLeft.right = node;//A.right = B
        node.left = nodeRight; //B.left = F

        //Update Height
        updateHeight(node);
        updateHeight(nodeLeft);

        rotations++;

        //retun new subtree
        return nodeLeft;
    }

    private Node rotateLeft(Node node){
        if(node == null){
            return null;
        }

        //node = A
        Node nodeRight = node.right; // G
        Node nodeLeft = nodeRight.left; //

        //Rotation
        nodeRight.left = node;//A.right = B
        node.right = nodeLeft; //B.left = D

        //Update Height
        updateHeight(node);
        updateHeight(nodeRight);

        rotations++;

        //retun new subtree
        return nodeRight;
    }

    public T find(T item) {

        comparisons++;
        if (size == 0) {
            return null;
        }
        return find(item, root);
    }

    private T find(T item, Node subtree) {

        if (item.compareTo(subtree.item) == 0) {
            return subtree.item;
        } else {
            comparisons++;
            if (item.compareTo(subtree.item) < 0) {
                if (subtree.left != null) return find(item, subtree.left);
                else return null;
            } else {
                if (subtree.right != null) return find(item, subtree.right);
                else return null;
            }
        }
    }

    public int height() {
        return root.height;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private void updateHeight(Node node) {
        if (node.right != null && node.left != null) {
            node.balanceFactor = node.left.height - node.right.height;
            if (node.right.height > node.left.height) node.height = node.right.height + 1;
            else node.height = node.left.height + 1;
        } else if (node.right != null) {
            node.height = node.right.height + 1;
            node.balanceFactor = -1 -node.right.height;
        }
        else if (node.left != null) {
            node.height = node.left.height + 1;
            node.balanceFactor = node.left.height +1;
        }
        else {
            node.height = node.balanceFactor = 0;
        }

    }

    private Node rebalance(Node node){
        //Rebalance will be called when the node balance is more than 1 or less than -1

        boolean isRoot = (root == node);
        //If node is right heavy, it is positive

        if(node.balanceFactor <-1){
            if(node.right.balanceFactor >0){
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }else{
            if(node.left.balanceFactor <0){
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        if(isRoot) root = node;
        return node;
    }

    public String toString() {
        if(isEmpty()) return "[]";
        StringBuilder str = new StringBuilder();
        str.append("[");
        stringHelper(root, str);
        str.replace(str.length() - 2, str.length(), "");
        str.append("]");
        return String.valueOf(str);
    }

    private void stringHelper(Node root, StringBuilder str) {

        if(root == null) return;
        stringHelper(root.left,str);
        str.append(root.item+":H"+root.height+":B"+root.balanceFactor+", ");
        stringHelper(root.right,str);
    }
}

class MyBSTtest{
    public static void main(String[] args) {
        MyBinarySearchTree<Integer> test = new MyBinarySearchTree<>(true);
        test.add(8);
        test.add(5);
        test.add(7);
        test.add(2);
        test.add(3);
        test.add(1);
        test.add(9);
        test.add(6);
        test.add(4);
        System.out.println(test);
        System.out.println(test.size);
        //[1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0] size:9
        test.remove(5);
        System.out.println(test);
    }
}


// public class MyBinarySearchTree<T extends Comparable<T>> {
//
//    private class Node {
//        T item;
//        Node right, left;
//        int height;
//        int balanceFactor;
//
//        public Node(T item) {
//            this.item = item;
//            height = -1;
//            balanceFactor = 0;
//        }
//
//        public String toString() {
//            return this.item + ":H" + height+"B:"+balanceFactor;
//        }
//    }
//
//    boolean balancing;
//    Node root;
//    int size;
//    long comparisons;
//    long rotations;
//
//    public MyBinarySearchTree(boolean balancing) {
//        root = new Node(null);
//        size = 0;
//        comparisons = 0;
//        this.balancing = balancing;
//        rotations = 0;
//    }
//
//    public void add(T item) {
//        if (size == 0) {
//            root.item = item;
//            root.height++;
//        } else {
//            add(item, root);
//        }
//        size++;
//    }
//
//    private Node add(T item, Node subtree) {
//        //If subtree is null, we add item to the end
//        if (subtree == null) {
//            subtree = new Node(item);
//            subtree.height = 0;
//            return subtree;
//        }
//        //We have not found our item yet, check if our item is bigger than the subtree item, if yes, go right
//        if (item.compareTo(subtree.item)>0){
//
//            //As we recurse through the tree, we reattach everything at the ends
//            subtree.right = add(item, subtree.right);
//            updateHeight(subtree);
//            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
//                subtree = rebalance(subtree);
//            }
//            return subtree;
//        }
//        //Check if item is smaller than subtree item, if yes, go left
//        else {
//            //As we recurse through the tree, reattach everything at the ends
//            subtree.left = add(item, subtree.left);
//            updateHeight(subtree);
//            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
//                subtree = rebalance(subtree);
//            }
//            return subtree;
//        }
//    }
//
//
//    public void remove(T item) {
//        if (size == 0) return;
//        else if (root != null && root.item == item && size == 1){
//            root = null;
//            size--;
//        }
//        else {
//            remove(item, root);
//        }
//    }
//
//    private Node remove(T item, Node subtree) {
//
//
//        //If subtree is null, we return
//        if (subtree == null)
//            return subtree;
//
//        //We have not found our item yet, check if our item is bigger than the subtree item, if yes, go right
//        if (item.compareTo(subtree.item)>0){
//            //As we recurse through the tree, we reattach everything at the ends
//            subtree.right = remove(item, subtree.right);
//            updateHeight(subtree);
//            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
//                updateHeight(root);
//                subtree = rebalance(subtree);
//            }
//
//        }
//        //Check if item is smaller than subtree item, if yes, go left
//        else if (item.compareTo(subtree.item)<0) {
//            //As we recurse through the tree, reattach everything at the ends
//            subtree.left = remove(item, subtree.left);
//            updateHeight(subtree);
//            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
//                //updateHeight(root);
//                subtree = rebalance(subtree);
//            }
//        }
//
//        //This occurs when the current item is the item we are looking for!
//        else {
//            // If we have one child, reattach the parent to that child, if we have no children, do the same, the left child will be null anyway
//            if (subtree.left == null) {
//                size--;
//                //This is how we connect the parent to the child around our item
//                return subtree.right;
//            }
//            else if (subtree.right == null) {
//                size--;
//                return subtree.left;
//            }
//
//            //The node has two children, we use our helper getmin method to find the minimum value on our larger than child
//            //We want the minimum because we know that the minimum of the larger child will be less than the larger child and still more than the parent node
//
//            subtree.item = getMin(subtree.right);
//            //Clean up our mess and reattach the right subtree to the new parent node
//            subtree.right = remove(subtree.item, subtree.right);
//            updateHeight(subtree);
//            if(balancing == true && (subtree.balanceFactor<-1 || subtree.balanceFactor>1)){
//                //updateHeight(root);
//                subtree = rebalance(subtree);
//            }
//        }
//        return subtree;
//    }
//
//    T getMin(Node subtree)
//    {
//        T min = subtree.item;
//        while (subtree.left != null)
//        {
//            min = subtree.left.item;
//            subtree = subtree.left;
//        }
//        return min;
//    }
//
//    T getMax(Node subtree)
//    {
//        T max = subtree.item;
//        while (subtree.right != null)
//        {
//            max = subtree.right.item;
//            subtree = subtree.right;
//        }
//        return max;
//    }
//
//    private Node rotateRight(Node node){
//        if(node == null){
//            return null;
//        }
//
//        Node nodeLeft = node.left; // B
//        Node nodeRight = nodeLeft.right; // F
//
//        //Rotation
//
//        nodeLeft.right = node;//A.right = B
//        node.left = nodeRight; //B.left = F
//
//        //Update Height
//        updateHeight(node);
//        updateHeight(nodeLeft);
//
//        rotations++;
//
//        //retun new subtree
//        return nodeLeft;
//    }
//
//    private Node rotateLeft(Node node){
//        if(node == null){
//            return null;
//        }
//
//        //node = A
//        Node nodeRight = node.right; // G
//        Node nodeLeft = nodeRight.left; //
//
//        //Rotation
//        nodeRight.left = node;//A.right = B
//        node.right = nodeLeft; //B.left = D
//
//        //Update Height
//        updateHeight(node);
//        updateHeight(nodeRight);
//
//        rotations++;
//
//        //retun new subtree
//        return nodeRight;
//    }
//
//    public T find(T item) {
//
//        comparisons++;
//        if (size == 0) {
//            return null;
//        }
//        return find(item, root);
//    }
//
//    private T find(T item, Node subtree) {
//
//        if (item.compareTo(subtree.item) == 0) {
//            return subtree.item;
//        } else {
//            comparisons++;
//            if (item.compareTo(subtree.item) < 0) {
//                if (subtree.left != null) return find(item, subtree.left);
//                else return null;
//            } else {
//                if (subtree.right != null) return find(item, subtree.right);
//                else return null;
//            }
//        }
//    }
//
//    public int height() {
//        return root.height;
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public boolean isEmpty() {
//        return (size == 0);
//    }
//
//    private void updateHeight(Node node) {
//        if (node.right != null && node.left != null) {
//            node.balanceFactor = node.left.height - node.right.height;
//            if (node.right.height > node.left.height) node.height = node.right.height + 1;
//            else node.height = node.left.height + 1;
//        } else if (node.right != null) {
//            node.height = node.right.height + 1;
//            node.balanceFactor = -1 -node.right.height;
//        }
//        else if (node.left != null) {
//            node.height = node.left.height + 1;
//            node.balanceFactor = node.left.height +1;
//        }
//        else {
//            node.height = node.balanceFactor = 0;
//        }
//
//    }
//
//    private Node rebalance(Node node){
//        //Rebalance will be called when the node balance is more than 1 or less than -1
//
//        boolean isRoot = (root == node);
//        //If node is right heavy, it is positive
//
//        if(node.balanceFactor <-1){
//            if(node.right.balanceFactor >0){
//                node.right = rotateRight(node.right);
//            }
//            node = rotateLeft(node);
//        }else{
//            if(node.left.balanceFactor <0){
//                node.left = rotateLeft(node.left);
//            }
//            node = rotateRight(node);
//        }
//        if(isRoot) root = node;
//        return node;
//    }
//
//    public String toString() {
//        if(isEmpty()) return "[]";
//        StringBuilder str = new StringBuilder();
//        str.append("[");
//        stringHelper(root, str);
//        str.replace(str.length() - 2, str.length(), "");
//        str.append("]");
//        return String.valueOf(str);
//    }
//
//    private void stringHelper(Node root, StringBuilder str) {
//
//        if(root == null) return;
//        stringHelper(root.left,str);
//        str.append(root.item+":H"+root.height+":B"+root.balanceFactor+", ");
//        stringHelper(root.right,str);
//    }
//}
//
//class MyBSTtest{
//    public static void main(String[] args) {
//        MyBinarySearchTree<Integer> test = new MyBinarySearchTree<>(true);
//        test.add(8);
//        test.add(5);
//        test.add(7);
//        test.add(2);
//        test.add(3);
//        test.add(1);
//        test.add(9);
//        test.add(6);
//        test.add(4);
//        System.out.println(test);
//        System.out.println(test.size);
//        //[1:H1, 2:H0, 3:H2, 4:H0, 5:H3, 6:H0, 7:H2, 8:H1, 9:H0] size:9
//        test.remove(5);
//        System.out.println(test);
//    }
//}
//
