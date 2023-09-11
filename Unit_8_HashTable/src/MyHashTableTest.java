
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MyHashTableTest
{
    private MyHashTable<Integer, Integer> hashtable;

    @Before
    public final void setup()
    {
        hashtable = new MyHashTable<Integer, Integer>(32768);
    }

    @Test
    public final void put_0()
    {
        hashtable.put(0, 10);
        assertEquals("put_0 failed", "[0:10]", hashtable.toString());
        assertEquals("put_0 failed", Integer.valueOf(1), hashtable.size());
        assertEquals("put_0 failed", Integer.valueOf(1),hashtable.comparisons);
        assertEquals("put_0 failed", Integer.valueOf(1),hashtable.maxProbe);
    }

    @Test
    public final void put_1()
    {
        hashtable.put(0, 10);
        hashtable.put(1, 11);
        assertEquals("put_1 failed", "[0:10, 1:11]", hashtable.toString());
        assertEquals("put_1 failed", Integer.valueOf(2), hashtable.size());
        assertEquals("put_1 failed", Integer.valueOf(2), hashtable.comparisons);
        assertEquals("put_1 failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_2()
    {
        hashtable.put(1, 11);
        hashtable.put(0, 10);
        assertEquals("put_2 failed", "[0:10, 1:11]", hashtable.toString());
        assertEquals("put_2 failed", Integer.valueOf(2), hashtable.size());
        assertEquals("put_2 failed", Integer.valueOf(2), hashtable.comparisons);
        assertEquals("put_2 failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_3()
    {
        hashtable.put(0, 10);
        hashtable.put(32768, 10);
        assertEquals("put_3 failed", "[0:10, 32768:10]", hashtable.toString());
        assertEquals("put_3 failed", Integer.valueOf(2), hashtable.size());
        assertEquals("put_3 failed", Integer.valueOf(3), hashtable.comparisons);
        assertEquals("put_3 failed", Integer.valueOf(2), hashtable.maxProbe);
    }

    @Test
    public final void put_4()
    {
        hashtable.put(32768, 10);
        hashtable.put(0, 10);
        assertEquals("put_4 failed", "[32768:10, 0:10]", hashtable.toString());
        assertEquals("put_4 failed", Integer.valueOf(2), hashtable.size());
        assertEquals("put_4 failed", Integer.valueOf(3), hashtable.comparisons);
        assertEquals("put_4 failed", Integer.valueOf(2), hashtable.maxProbe);
    }

    @Test
    public final void put_5()
    {
        hashtable.put(0, 10);
        hashtable.put(32768, 10);
        hashtable.put(1, 11);
        assertEquals("put_5 failed", "[0:10, 32768:10, 1:11]", hashtable.toString());
        assertEquals("put_5 failed", Integer.valueOf(3), hashtable.size());
        assertEquals("put_5 failed", Integer.valueOf(5), hashtable.comparisons);
        assertEquals("put_5 failed", Integer.valueOf(2), hashtable.maxProbe);
    }

    @Test
    public final void put_6()
    {
        hashtable.put(0, 10);
        hashtable.put(1, 11);
        hashtable.put(32768, 10);
        assertEquals("put_6 failed", "[0:10, 1:11, 32768:10]", hashtable.toString());
        assertEquals("put_6 failed", Integer.valueOf(3), hashtable.size());
        assertEquals("put_6 failed", Integer.valueOf(5), hashtable.comparisons);
        assertEquals("put_6 failed", Integer.valueOf(3), hashtable.maxProbe);
    }

    @Test
    public final void put_7()
    {
        hashtable.put(0, 10);
        hashtable.put(1, 11);
        hashtable.put(2, 12);
        hashtable.put(32768, 10);
        assertEquals("put_7 failed", "[0:10, 1:11, 2:12, 32768:10]", hashtable.toString());
        assertEquals("put_7 failed", Integer.valueOf(4), hashtable.size());
        assertEquals("put_7 failed", Integer.valueOf(7), hashtable.comparisons);
        assertEquals("put_7 failed", Integer.valueOf(4), hashtable.maxProbe);
    }

    @Test
    public final void put_8()
    {
        hashtable.put(0, 10);
        hashtable.put(1, 11);
        hashtable.put(32768, 10);
        hashtable.put(2, 12);
        assertEquals("put_8 failed", "[0:10, 1:11, 32768:10, 2:12]", hashtable.toString());
        assertEquals("put_8 failed", Integer.valueOf(4), hashtable.size());
        assertEquals("put_8 failed", Integer.valueOf(7), hashtable.comparisons);
        assertEquals("put_8 failed", Integer.valueOf(3), hashtable.maxProbe);
    }

    @Test
    public final void put_9()
    {
        hashtable.put(0, 10);
        hashtable.put(2, 12);
        hashtable.put(32769, 11);
        assertEquals("put_9 failed", "[0:10, 32769:11, 2:12]", hashtable.toString());
        assertEquals("put_9 failed", Integer.valueOf(3), hashtable.size());
        assertEquals("put_9 failed", Integer.valueOf(3), hashtable.comparisons);
        assertEquals("put_9 failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_10()
    {
        hashtable.put(0, 10);
        hashtable.put(32769, 11);
        hashtable.put(2, 12);
        assertEquals("put_10 failed", "[0:10, 32769:11, 2:12]", hashtable.toString());
        assertEquals("put_10 failed", Integer.valueOf(3), hashtable.size());
        assertEquals("put_10 failed", Integer.valueOf(3), hashtable.comparisons);
        assertEquals("put_10 failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_a()
    {
        insert_ten_items_asc(hashtable);
        assertEquals("put_a failed", "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString());
        assertEquals("put_a failed", Integer.valueOf(10), hashtable.size());
        assertEquals("put_a failed", Integer.valueOf(10), hashtable.comparisons);
        assertEquals("put_a failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_d()
    {
        insert_ten_items_des(hashtable);
        assertEquals("put_d failed", "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString());
        assertEquals("put_d failed", Integer.valueOf(10), hashtable.size());
        assertEquals("put_d failed", Integer.valueOf(10), hashtable.comparisons);
        assertEquals("put_d failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_r()
    {
        insert_ten_items_ran(hashtable);
        assertEquals("put_r failed", "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString());
        assertEquals("put_r failed", Integer.valueOf(10), hashtable.size());
        assertEquals("put_r failed", Integer.valueOf(10), hashtable.comparisons);
        assertEquals("put_r failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void put_u()
    {
        insert_ten_items_ran(hashtable);
        assertEquals("put_u failed", "[0:0, 1:1, 2:2, 3:3, 4:4, 5:5, 6:6, 7:7, 8:8, 9:9]", hashtable.toString());
        assertEquals("put_u failed", Integer.valueOf(10), hashtable.size());
        assertEquals("put_u failed", Integer.valueOf(10), hashtable.comparisons);
        assertEquals("put_u failed", Integer.valueOf(1), hashtable.maxProbe);
        insert_ten_items_ran_diff_val(hashtable);
        assertEquals("put_u failed", "[0:0, 1:10, 2:20, 3:30, 4:40, 5:50, 6:60, 7:70, 8:80, 9:90]", hashtable.toString());
        assertEquals("put_u failed", Integer.valueOf(10), hashtable.size());
        assertEquals("put_u failed", Integer.valueOf(10), hashtable.comparisons);
        assertEquals("put_u failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
        public final void get_0()
//    public final void put_0()
    {
        assertEquals("get_0 failed", null, hashtable.get(0));
        assertEquals("get_0 failed", Integer.valueOf(0), hashtable.size());
        assertEquals("get_0 failed", Integer.valueOf(0), hashtable.comparisons);
        assertEquals("get_0 failed", Integer.valueOf(0), hashtable.maxProbe);
    }

    @Test
    public final void get_1()
    {
        hashtable.put(0, 10);
        assertEquals("get_1 failed", Integer.valueOf(10), hashtable.get(0));
        assertEquals("get_1 failed", Integer.valueOf(1), hashtable.size());
        assertEquals("get_1 failed", Integer.valueOf(1), hashtable.comparisons);
        assertEquals("get_1 failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void get_2()
    {
        hashtable.put(0, 10);
        hashtable.put(1, 11);
        assertEquals("get_2 failed", null, hashtable.get(2));
        assertEquals("get_2 failed", Integer.valueOf(10), hashtable.get(0));
        assertEquals("get_2 failed", Integer.valueOf(11), hashtable.get(1));
        assertEquals("get_2 failed", null, hashtable.get(2));
        assertEquals("get_2 failed", Integer.valueOf(2), hashtable.size());
        assertEquals("get_2 failed", Integer.valueOf(2), hashtable.comparisons);
        assertEquals("get_2 failed", Integer.valueOf(1), hashtable.maxProbe);
    }

    @Test
    public final void get_3()
    {
        hashtable.put(0, 10);
        hashtable.put(1, 11);
        hashtable.put(32768, 10);
        assertEquals("get_3 failed", Integer.valueOf(10), hashtable.get(0));
        assertEquals("get_3 failed", Integer.valueOf(11), hashtable.get(1));
        assertEquals("get_3 failed", Integer.valueOf(10), hashtable.get(32768));
        assertEquals("get_3 failed", Integer.valueOf(3), hashtable.size());
        assertEquals("get_3 failed", Integer.valueOf(5), hashtable.comparisons);
        assertEquals("get_3 failed", Integer.valueOf(3), hashtable.maxProbe);
    }

    @Test
    public final void unique_words() throws IOException {
        new UniqueWords();
//		new UniqueWords("WarAndPiece.txt");

//		570240 words
//		20228 unique words
//		40430 comparisons
//		191 max probe
    }

    // ---------- helper methods ---------

    public final void insert_ten_items_asc(MyHashTable<Integer, Integer> hashtable)
    {
        for (int index = 0; index < 10; index++)
            hashtable.put(index, index % 32768);
    }

    public final void insert_ten_items_des(MyHashTable<Integer, Integer> hashtable)
    {
        for (int index = 9; index >= 0; index--)
            hashtable.put(index, index % 32768);
    }

    public final void insert_ten_items_ran(MyHashTable<Integer, Integer> hashtable)
    {
        int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
        for (int index = 0; index < array.length; index++)
            hashtable.put(index, index % 32768);
    }

    public final void insert_ten_items_ran_diff_val(MyHashTable<Integer, Integer> hashtable)
    {
        int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
        for (int index = 0; index < array.length; index++)
            hashtable.put(index, index * 10);
    }

    // helper function for old unit testing library
    public final void assertTest(String test_name, String actual_value, String ... expected_values)
    {
        for (String expected_value : expected_values)
            if (expected_value.equals(actual_value))
                return;

        assertEquals(test_name, String.format("%s", to_string(expected_values), actual_value), hashtable.toString());
    }

    public final String to_string(String ... values)
    {
        int length = values.length;
        if (length == 0)
            return "";

        if (length == 1)
            return values[0];

        StringBuilder builder = new StringBuilder();
        builder.append(values[0]);
        for (int index = 1; index < length; index++)
            builder.append(" OR ").append(values[index]);
        return builder.toString();
    }
}