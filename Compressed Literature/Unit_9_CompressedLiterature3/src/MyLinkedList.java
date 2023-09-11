public class MyLinkedList<T extends Comparable<T>>{

    //Use Node Class To Hold Values
    private static class Node<T> {
        //item Will Hold The Items
        T item;
        //Before And After Will Be Used To Point To Other Nodes And Keep The List Linked
        Node<T> after, before;

        public Node( Node<T> b, T it, Node<T> n){
            item = it;
            after = n;
            before = b;
        }

        @Override
        public String toString() {
            return (String) item;
        }
    }

    //Create Constructors Under The Node Class.
    //First Will Be The First Element In The Last
    //Current Will Be The Selected Element In The List
    //Previous Will Be Current.Before and Help Prevent Null Pointer Exceptions When Current Adds Onto The End Of The List
    private Node<T> first, current, previous;
    private int size;
    public int comparisons;

    public MyLinkedList(){
        comparisons = 0;
        first = current = null;
        size = 0;
    }

    //Allows The LinkedList To Be Represented As A String Of Elements When Printed
    public String toString() {
        //If the list is empty, return empty list
        if(first.item == null) return "[]";
        //if not empty, construct string.
        StringBuilder result = new StringBuilder("["+first.item.toString());
        for(Node<T> i = first.after; i != null; i = i.after){
            result.append(", ").append(i);
        }
        return result+"]";
    }

    //Iterates Through The Linked List To Find A Match For The Specified Item
    public boolean contains(T item){
        comparisons++;
        for(Node<T> i = first; i != null; i = i.after){
            comparisons++;
            if (i.item.compareTo(item) == 0) return true;
        }
        return false;
    }

    //Add Before The Currently Selected Node
    public void addBefore(T item) {
        //Current Does Not Equal Null, It Is Inside The List
        if (current != null && previous != null) {
            //Create a Node At Current, Use Before And After to Automatically Link The Node With Previous Node And Current
            current.before.after = new Node<>(previous, item, current);
            //Change Previous To The New Node
            previous = current.before.after;
            //Attach Previous To Current
            current.before = previous;
        } else if ( previous == null) {
            //Current Is In First Position, First Becomes A New Node, Attach Current After First
            first = new Node<>(null, item, current);
            //First Is Now Previous To Current
            previous = first;
            // Empty list: one node is first and last
        } else {
            //Current Is Off The Edge Of The List
            //Use Previous To Avoid A Null Pointer Exception
            previous.after = new Node<>(previous, item, current);
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
        return current.item;
    }

    public void addAfter(T item) {
        //If Current Equals Null, Do Nothing, If Not, Add It After Current, This Operation Does Not Affect Previous
        if ( current != null) {
            current.after = new Node<>(current, item, current.after);
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
        Node<T> temp = current;

        if(current != null){
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
        }
        //If Current Is Null, Temp's Item Will Be Null
        return  temp.item;
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
            if(current!=null) {
                current.before = previous;
                return current.item;
            }
        }
        return null;
    }
    //Check If The LinkedList Is Empty Using Size
    public boolean isEmpty(){
        return size == 0;
    }

    public void moveToFront(){
        if(first != current) {
            first = new Node<>(null, current.item, first);
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
    //try a different type of sort
    public void sort(){
        for (int i = 0; i <size-1; i++) {
            boolean inversion = false;
            first();
            for (int j = 0; j < size-i-1; j++) {
                if((current.item).compareTo((current.after.item))>0){
                    inversion = true;
                    T temp = current.item;
                    current.item = current.after.item;
                    current.after.item = temp;
                }//end if
                next();
            }// end for j
            if(!inversion) break;
        }// end for i
    }// end method
}
