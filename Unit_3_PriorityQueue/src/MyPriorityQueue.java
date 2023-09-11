
public class MyPriorityQueue<T extends Comparable<T>> {

    private MyArrayList<T> heap;

    public MyPriorityQueue() {
        heap = new MyArrayList<>();
    }

    public void insert(T item){
        heap.insert(item,heap.size());
        //call bubble up

        bubbleUp();
    }

    public T removeMin(){
        T temp = heap.get(0);
        heap.set(0,heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        //call sink down.
        sinkDown();

        return temp;
    }

    //return the root of the tree
    public T min(){
        return heap.get(0);
    }

    //return the array list heap size
    public int size(){
        return heap.size();
    }

    //check if empty
    public boolean isEmpty(){
        return heap.isEmpty();
    }

    //print heap
    public String toString(){
        return heap.toString();
    }

    private void bubbleUp(){

        //initialize value to end of list
        int value = heap.size()-1;
        //While parent >= min... this will take O(lg n) time
        while (parent(value) >= 0) {
            //value is larger than parent, uphold invariant
            if (heap.get(parent(value)).compareTo(heap.get(value)) > 0) {
                //Swap parent and value
                T temp = heap.get(value);
                heap.set(value, heap.get(parent(value)));
                heap.set(parent(value), temp);

                value = parent(value);
            } else break;//satisfies invariant, break
        }
    }

    private void sinkDown() {

        int k = 0;

        T left = heap.get(left(k));
        T right = heap.get(right(k));

        while( (heap.get(right(k))!=null && right.compareTo(heap.get(k))<0) || (heap.get(left(k))!=null  && left.compareTo(heap.get(k))<0)){


            //This is the case if both != null
            if(right!=null){
                if (left.compareTo(right)>0) {
                    T temp = heap.get(right(k));
                    heap.set(right(k), heap.get(k));
                    heap.set(k, temp);
                    k = right(k);
                } else if(left.compareTo(heap.get(k))<0){
                    T temp = heap.get(left(k));
                    heap.set(left(k), heap.get(k));
                    heap.set(k, temp);
                    k=left(k);
                }
            } else if(left.compareTo(heap.get(k))<0){
                T temp = heap.get(left(k));
                heap.set(left(k), heap.get(k));
                heap.set(k, temp);
                k=left(k);
            }
            left = heap.get(left(k));
            right = heap.get(right(k));
        }

    }

    //Return parent, right, and left using these algorithms to find locations on an index.
    private int parent(int index){
        return (index-1) / 2;
    }

    private int right(int index){
        return 2*index+2;
    }

    private int left(int index){
        return 2*index+1;
        //based on pre-order traversal
        // (int) Math.pow(2,bitCount(heap.size())+1)/2
    }

}

class MyPriorityTest{
    public static void main(String[] args) {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>();
        /* [4, 5, 6, 8, 9, 7]
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        queue.insert(8);
        queue.insert(9);
        queue.insert(7);
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);

        queue.insert(4);
        System.out.println(queue);
        queue.insert(7);
        System.out.println(queue);
        queue.insert(5);
        System.out.println(queue);
        queue.insert(2);
        System.out.println(queue);
        queue.insert(3);
        System.out.println(queue);
        queue.insert(6);
        System.out.println(queue);
        queue.insert(8);
        System.out.println(queue);
        queue.insert(9);
        System.out.println(queue);
        queue.insert(1);
        System.out.println(queue);
        queue.insert(0);


        System.out.println(queue);

        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
        System.out.println(queue.removeMin());
        System.out.println(queue);
*/
    }
}
