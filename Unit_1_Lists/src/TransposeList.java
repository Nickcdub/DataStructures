public class TransposeList<T extends Comparable<T>> {

    private MyLinkedList list;

    public TransposeList() {
        list = new MyLinkedList<>();
    }

    //Keep Current At The End Of The List, Add Before Will Add On To The End!
    public void add(T item) {
        list.addBefore(item);
    }

    public T remove(T item) {

        //Reset Current
        list.first();
        T temp = null;
        //Iterate through list
        for (; list.current() != null; list.next()) {
            //Even if something is found, keep iterating so that current ends at the end of the list
            if (temp == null && ((T) list.current()).compareTo(item) == 0) {
                temp = item;
                list.remove();
            }
        }

        return temp;
    }

    public T find(T item){

        list.first();
        T temp = null;
        for (; list.current() != null; list.next()) {
            //Similar setup as remove, use swapWithPrevious() method to apply find function
            if (temp == null && ((T) list.current()).compareTo(item) == 0) {
                temp = item;
                list.swapWithPrevious();
            }
        }
        return temp;
    }

    //return size method
    public int size(){
        return  list.size();
    }

    //return isEmpty method
    public boolean isEmpty(){
        return list.isEmpty();
    }

    //return toString method
    public String toString(){
        return list.toString();
    }

}

class TransposeListTest{
    public static void main(String[] args) {

        var test = new TransposeList<Integer>();
        var empty = new TransposeList<Integer>();

        test.add(1);
        test.add(3);
        test.add(2);
        test.find(2);
        //[1, 2, 3]
        System.out.println(test);

        System.out.println(test.isEmpty());//false
        System.out.println(empty.isEmpty());//true

        test.add(6);
        test.add(4);
        test.add(5);
        System.out.println(test.remove(6));//6
        test.add(6);
        //[1, 2, 3, 4, 5, 5]
        System.out.println(test);
        System.out.println(test.find(6));//6
        System.out.println(test.size());//6
        System.out.println(empty.size());//0
        System.out.println(empty.find(4));//null
        System.out.println(empty.remove(4));//null
        //[]
        System.out.println(empty);

    }
}