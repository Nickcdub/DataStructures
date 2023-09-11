public class MyArrayList<T extends Comparable<T>>{

    //List Is The Generic Array That Will Hold Our Elements (Starts with 16 spaces)
    //Capacity Will Keep Track Of Empty Space In list (Starts at 16)
    //Size Will Help Make Sure We Resize When Capacity Is Reached. (Starts at a size of 0)
    private T[] list ;
    private int capacity;
    private int size;
    public int comparisons;

    public MyArrayList() {
        comparisons = 0;
        capacity = 16;
        list = (T[]) new Comparable[capacity];
        size = 0;
    }

    //Inserts An Item At A Specified Index
    public void insert(T item, int index){

        //If list Is At Capacity, Double The Capacity
        if(size == capacity) reSize();

        //Shuffle Spaces After Index To The Right
        //Make Sure Index Is A Valid Number
        if (size - index >= 0 && index>=0){
            System.arraycopy(list, index, list, index + 1, size - index);
            //Insert The Item At Index And Increase Size
            list[index] = item;
            size++;
        }
    }

    //Removes The Item Found At A Specified Index
    public T remove(int index){
        //Temp Will Hold The Value At The Index If The Index Is Valid
        T temp;
        //Check If The Index Is Valid
        if(size>0 && index<=size-1){
            temp = list[index];
            //Search Through list At O(n) Speed, Shuffle All Values After Index To The Left.
            if (size - 1 - index >= 0) System.arraycopy(list, index + 1, list, index, size - 1 - index);
            //Remove The Left Over Value And Decrease Size
            list[size-1] = null;
            size--;
            return temp;
        }
        //If The Index Is Invalid, Return Null
        return null;
    }

    //Searches list To See If The Specified Item Matches Any Of The Values At Index i
    public boolean contains(T item){
        comparisons++;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if(item.compareTo(list[i]) == 0) return true;
        }
        //No Matches Were Found, Return False
        return false;
    }

    //Search list To Find Index That A Specified Item Is Located In
    public int indexOf(T item){
        for (int i = 0; i < size; i++) {
            if(item.compareTo(list[i]) == 0) return i;
        }
        //Item Not Found, Return -1
        return -1;
    }

    //Retrieves The Item At A Given Index As Long As The Index Is Valid, If Not, Returns Null
    public T get(int index){
        if(0<=index && index<=size-1) return list[index];
        return null;
    }

    //Sets The Item At A Given Index As Long As The Index Is Valid, If Not, Returns Nothing
    public void set(int index, T item){
        if(0<=index && index<=size-1) list[index] = item;
    }

    //Returns The Size Of The ArrayList
    public int size(){
        return size;
    }

    //Checks If The ArrayList Is Empty
    public boolean isEmpty(){
        return(size == 0);
    }

    //Allows The ArrayList To Be Represented As A String Of Elements When Printed
    public String toString(){
//If the list is empty, return empty list
        if(list[0] == null) return "[]";
        //if not empty, construct string.
        StringBuilder result = new StringBuilder(list[0].toString());
        for(int i = 1; i < size; i++){
            result.append(", ").append(list[i]);
        }
        return "["+result.toString() + "]";
    }

    //This Method Copies The Array Onto A Larger Array In Order To Make The Array Appear More Dynamic
    private void reSize(){
        T[] temp = (T[]) new Comparable[capacity*2];

        if (capacity >= 0) System.arraycopy(list, 0, temp, 0, capacity);
        list = temp;
        capacity = capacity*2;
    }

    public void sort(){
        for (int i = 0; i < size-1; i++) {
            boolean inversion = false;
            for (int j = 0; j < size-i-1; j++) {
                if(list[j].compareTo(list[j+1])>0){
                    inversion = true;
                    T temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                }//end if

            }// end for j
            if(!inversion) break;
        }// end for i
    }// end method

}

class ArrayListTest {
    public static void main(String[] args) {

        var arr = new MyArrayList<Integer>();
        var empty = new MyArrayList<Integer>();
      arr.insert(36,0);
      arr.insert(27,1);
        arr.insert(8,2);
      arr.insert(7,3);

        System.out.println(arr);
        arr.sort();
        System.out.println(arr);
    }
}