public class MyHashTable <K extends Comparable<K>, V>{

    private int capacity;
    private K[] keyBuckets;
    private V[] valueBuckets;
    private Integer size;
    public MyArrayList<K> keys = null;
    public Integer comparisons;
    public Integer maxProbe;

    public MyHashTable(int capacity){
        keys = new MyArrayList<>();
        keyBuckets = (K[]) new Comparable[capacity];
        valueBuckets = (V[]) new Object[capacity];
        comparisons=size=maxProbe=0;
        this.capacity = capacity;

    }

    private int hash(K key){
        //Return the distance between the absolute value of the hashcode and the closest lower multiple
       return Math.abs(key.hashCode())%capacity;
    }

    public V get(K key){
        int hash = hash(key);

        int count = 0;

        for (int i = hash; i < capacity; i = (i + 1) % capacity) {
            count++;
            if(keyBuckets[i]!=null && count!=capacity){
                if (keyBuckets[i].equals(key)){
                    return valueBuckets[i];
                }
            }else {
                return null;
            }
        }

        return null;
    }
    public void put(K key, V value){
        int hash = hash(key); //This hash value for our key will act as our initial index
        //probe will count the amount of probes that occur during the hash function
        int probe = 0;
        //Once we reach capacity, our i will reset itself at 0, we will end the loop within the for statement
            for (int i = hash; i < capacity; i = (i + 1) % capacity) {
                probe++;
                comparisons++;
                //check if there is a value in keyBuckets[i]
                if (keyBuckets[i] != null) {
                    //Check if the value in this space is the same as the key, if so, overwrite the value here
                    if (keyBuckets[i].equals(key)) {
                        valueBuckets[i] = value;
                        //If this is the first iteration, subtract the initial probe addition.
                        maxProbe = Math.max(maxProbe, probe);
                        comparisons--;
                        //comparisons += probe;
                        return;
                        //if probe = capacity, we've circled back around and it's time to stop looking
                    } else if (probe == capacity) return;

                    //if the buckets are null, we have a space to put our key-value pair
                } else if (keyBuckets[i] == null) {
                    keyBuckets[i] = key;
                    valueBuckets[i] = value;
                    keys.insert(key, size);
                    size++;
                    //If we found this on the first try (index hash), subtract the initial probe counter
                    //check which is greater and keep it
                    maxProbe = Math.max(maxProbe, probe);
                    //add number of probes to comparisons
                    //comparisons += probe;
                    return;
                }
            }
    }

    public Integer size(){
        return size;
    }

    public String toString(){
        StringBuilder stb = new StringBuilder();
        stb.append("[");
        for (int i = 0; i < capacity; i++) {
            if(keyBuckets[i] != null){
                stb.append(keyBuckets[i]+":"+valueBuckets[i]+", ");
            }
        }
        stb.replace(stb.length() - 2, stb.length(), "");
        stb.append("]");
        return String.valueOf(stb);
    }


}
class TableTest{
    public static void main(String[] args) {
       var test = new MyHashTable<Integer,Integer>(23);
      test.put(148,1);
      test.put(8,2);
      test.put(80,3);
      test.put(100,4);
      test.put(100,4);
        System.out.println(test.maxProbe);
        System.out.println(test.comparisons);
        test.get(100);
        System.out.println(test.comparisons);
        System.out.println();
    }
}
