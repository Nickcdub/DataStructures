public class MTFList<T extends Comparable<T>> {


    private MyLinkedList list;

    //Create the linkedlist to store values
    public MTFList() {
        list = new MyLinkedList<>();
    }

    //Current will always be at the first position, addBefore will be used to add values
    public void add (T item){
        list.addBefore(item);
        //After every operation that moves current, return to first position
        list.first();
    }

    public T remove (T item) {

        //Create temp to remove the item if it is found.
        T temp = null;

        //Iterate through list
            for (int i = 0; list.current() != null; list.next()) {
                //If match is found, temp updates to that value and the forloop breaks
                if (((T) list.current()).compareTo(item) == 0) {
                    temp = item;
                    list.remove();
                    break;
                }
            }
        //reset current and return temp
            list.first();
            return temp;

    }

    public T find (T item) {
        T temp = null;

        //Similar set upn as remove
        for (int i = 0; list.current() != null; list.next()) {
            if (((T) list.current()).compareTo(item) == 0) {
                temp = item;
                //Use moveToFront() to swap the values within the two nodes
                list.moveToFront();
                break;
            }
        }
        list.first();
        return temp;
    }

    //Return list size
    public int size(){
        return  list.size();
    }

    //Return isEmpty method
    public boolean isEmpty(){
        return list.isEmpty();
    }

    //Return toString method
    public String toString(){
        return list.toString();
    }
}

class MTFListTest{
    public static void main(String[] args) {

        var test = new MTFList<Integer>();
        var empty = new MTFList();

        test.add(1);
        test.add(4);
        test.add(5);
        test.add(3);
        test.add(2);

        //Should return [2, 3, 5, 4, 1]
        System.out.println(test);
        System.out.println(test.isEmpty());//false
        System.out.println(empty.isEmpty());//true

        test.remove(5);
        test.find(1);
        test.find(1);
        //Should return [1, 2, 3, 4]
        System.out.println(test);

        test.find(3);
        //Should return [3, 1, 2, 4]
        System.out.println(test);

        test.add(2);
        test.add(5);
        test.find(2);
        //Should return [2, 5, 3, 1, 2, 4]
        System.out.println(test);
        System.out.println(test.size());//6
        System.out.println(empty.size());//0

        test.add(1);
        System.out.println(test.remove(6));//null
        System.out.println(test.remove(2));//2
        System.out.println(test.find(0));//null
        //Should return [1, 5, 3, 1, 2, 4]
        System.out.println(test);


    }
}

