public class MyOrderedList<T extends Comparable<T>> {

    private MyArrayList<T> list;
    public long comparisons;

    public MyOrderedList() {
        list = new MyArrayList<>();
        comparisons = 0;
    }

    public void add(T item) {
        list.insert(item, list.size());
        for (int i = list.size() - 1; i > 0; i--) {
            comparisons++;
            if (list.get(i).compareTo(list.get(i - 1)) <= 0) {
                T temp = list.get(i);
                list.set(i,list.get(i-1));
                list.set(i-1,temp);
            } else break;
        }
    }

    public T remove(T item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(item) == 0) {
                return list.remove(i);
            }
        }
        return null;
    }

    public T binarySearch(T item) {
        if(list.isEmpty()) return null;
        return binarySearch(item, 0, list.size() - 1);
    }

    private T binarySearch(T item, int start, int finish) {
        //If start is more than finish, the item was not found.
        //comparisons++;
        if(start<=finish){
            int mid = start+(finish-start)/2;
            if(list.get(mid).compareTo(item) == 0){
                return list.get(mid);
            }else if(list.get(mid).compareTo(item)<0) {
                return binarySearch(item, mid + 1, finish);
            }else {
                return  binarySearch(item, start, mid-1);
            }
        }
        return null;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String toString() {
        return list.toString();
    }

    public T get(int index){
        return list.get(index);
    }

    public void set(int index, T item){
        list.set(index,item);
    }
}
class orderedListTest{
    public static void main(String[] args) {
        var test = new MyOrderedList<String>();
        test.add("beta");
        test.add("Alpha");
        test.add("gamma");
        test.add("android");
        System.out.println(test);
        System.out.println(test.binarySearch("beta"));//true
        System.out.println(test.binarySearch("Alpha"));//true
        System.out.println(test.binarySearch("gamma"));//true
        System.out.println(test.binarySearch("android"));//true
        System.out.println(test.binarySearch("epsilon"));//false
        System.out.println(test.remove("gamma"));//gamma
        System.out.println(test.remove("Alpha"));//Alpha
        System.out.println(test.remove("beta"));//beta
        System.out.println(test.remove("android"));//android
        System.out.println(test.remove("beta"));//null
        System.out.println(test.isEmpty());//true
        System.out.println(test.size());//0
    }
}

