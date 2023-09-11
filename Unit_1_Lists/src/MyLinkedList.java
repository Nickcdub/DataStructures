public class MyLinkedList<T>{

    //Use Node Class To Hold Values
    private class Node {
         //item Will Hold The Items
        T item;
        //Before And After Will Be Used To Point To Other Nodes And Keep The List Linked
        Node after, before;

        public Node( Node b, T it, Node n){
            item = it;
            after = n;
            before = b;

        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    //Create Constructors Under The Node Class.
    //First Will Be The First Element In The Last
    //Current Will Be The Selected Element In The List
    //Previous Will Be Current.Before and Help Prevent Null Pointer Exceptions When Current Adds Onto The End Of The List
    private Node first, current, previous;
    private int size;

    public MyLinkedList(){
        first = current = null;
        size = 0;
    }

    //Allows The LinkedList To Be Represented As A String Of Elements When Printed
    public String toString() {
        //If the list is empty, return empty list
        if(first == null) return "[]";
        //if not empty, construct string.
        StringBuilder result = new StringBuilder("["+first.item.toString());
        for(Node i = first.after; i != null; i = i.after){
            result.append(", ").append(i);
        }
        return result+"]";
    }

    //Iterates Through The Linked List To Find A Match For The Specified Item
    public boolean contains(T item){
        for(Node i = first; i != null; i = i.after){
            if (i.item == item) return true;
        }
        return false;
    }

    //Add Before The Currently Selected Node
    public void addBefore(T item) {
        //Current Does Not Equal Null, It Is Inside The List
        if (current != null && previous != null) {
            //Create a Node At Current, Use Before And After to Automatically Link The Node With Previous Node And Current
            current.before.after = new Node(previous, item, current);
            //Change Previous To The New Node
            previous = current.before.after;
            //Attach Previous To Current
            current.before = previous;
        } else if ( previous == null) {
            //Current Is In First Position, First Becomes A New Node, Attach Current After First
            first = new Node(null, item, current);
            //First Is Now Previous To Current
            previous = first;
            // Empty list: one node is first and last
        } else {
            //Current Is Off The Edge Of The List
            //Use Previous To Avoid A Null Pointer Exception
            previous.after = new Node(previous, item, current);
            //Previous Advances
            previous = previous.after;
        }
        //Increase Size
        size++;
    }
    //Move Current To First Position, Previous Is Not Null
    public T first(){
        current = first;
        previous = null;
        return  current.item;
    }

    public void addAfter(T item) {
        //If Current Equals Null, Do Nothing, If Not, Add It After Current, This Operation Does Not Affect Previous
        if ( current != null) {
            current.after = new Node(current, item, current.after);
            //Increase Size
            size++;
        }
    }

    //Allow Anyone To Obtain The Size Of The LinkedList
    public int size() {
        return size;
        }

    //Remove The Current Item From The List And Return The Item Removed
    public T remove(){
        //Save The Removed Item

         if(current != null){
             Node temp = current;
             if(current == first){
                 temp = first;
                 current = current.after;
                 first = current;
             }else {
                 //Attach Current.Before To Current.After
                 previous.after = current.after;
                 //Attach Current To Previous
                 current = current.after;

                 if (current != null) {
                     current.before = previous;
                     // previous.after = current;
                 }
             }
            //Decrease Size
            size --;
             return temp.item;
        }
        //If Current Is Null, Temp's Item Will Be Null
        return null;
    }

    //Allow Anyone To Access The Item In The Current Position
    public T current(){
        //current can not point to an item if it is null
        if (current!=null)return current.item;
        return null;
    }

    //Progresses The Current Position And Returns The New Item.
    public T next(){
        //If Current Is Already Null, Do Nothing
        if(current != null) {
            //Progress Current And Previous, Then Reattach Them To Each Other
            previous = current;
            current = previous.after;
            //current can not point to previous if it is null
            if(current!=null) current.before = previous;
            return current.item;
        }
        return null;
    }
    //Check If The LinkedList Is Empty Using Size
    public boolean isEmpty(){
        return size == 0;
    }

    public void moveToFront(){
        if(first != current) {
            first = new Node(null, current.item, first);
            remove();
            //Account for the size reduction in remove by adding it back
            size++;
        }
    }

    public void swapWithPrevious(){
        if(first!=current) {
            T temp = current.item;
            current.item = previous.item;
            previous.item = temp;
        }
    }
}

class LinkedListTest{
    public static void main(String[] args) {
        var test = new MyLinkedList<Integer>();
        var empty = new MyLinkedList<Integer>();
       // Linked List Contents []
        test.addBefore(1);
       // Linked List Contents [1]
        System.out.println(test.next());
        System.out.println(test);
        test.addBefore(2);
       // Linked List Contents [1, 2]
        System.out.println(test);
        test.addBefore(3);
       // Linked List Contents [1, 2, 3]
        System.out.println(test);
        System.out.println(test.first());//1
        test.addAfter(4);
       // Linked List Contents [1, 4, 2, 3]
        System.out.println(test);
        System.out.println(test.current());//1
        //Linked List Contents [1, 4, 2, 3]
        System.out.println(test);
        System.out.println(test.next());//4
        //Linked List Contents [1, 4, 2, 3]
        System.out.println(test);
        System.out.println(test.current());//4
        //Linked List Contents [1, 4, 2, 3]
        System.out.println(test);
        System.out.println(test.next());//2
       // Linked List Contents [1, 4, 2, 3]
        System.out.println(test);
        System.out.println(test.current());//2
        test.moveToFront();
        //Linked List Contents [2, 1, 4, 3]
        System.out.println(test);
       test.addBefore(5);
       // Linked List Contents [2, 1, 4, 5, 3]
        System.out.println(test);
        test.addBefore(6);
        //Linked List Contents [2, 1, 4, 5, 6, 3]
        System.out.println(test);
        test.addAfter(4);
        test.first();
        int hi = test.remove();
        //Linked List Contents [1, 4, 5, 6, 3, 4]
        System.out.println(test);
        System.out.println(test.size());//6
        System.out.println(test.contains(4));//true
        System.out.println(empty.contains(2));//false
        System.out.println(empty.isEmpty());//true
        System.out.println(test.contains(2));//true
        test.next();
        test.swapWithPrevious();
        //Linked List Contents [1, 4, 5, 6, 3, 4]
        System.out.println(test);
    }
}