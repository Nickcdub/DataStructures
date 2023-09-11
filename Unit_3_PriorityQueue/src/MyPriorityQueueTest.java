
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyPriorityQueueTest
{
    private MyPriorityQueue<Integer> queue;

    @Before
    public final void setup()
    {
        queue = new MyPriorityQueue<Integer>();
    }

    @Test
    public final void insert_0()
    {
        insert_ten_items_asc(queue);
        assertEquals("insert_0 failed", "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", queue.toString());
    }

    @Test
    public final void insert_1()
    {
        insert_ten_items_des(queue);
        assertEquals("insert_1 failed", "[0, 1, 4, 3, 2, 8, 5, 9, 6, 7]", queue.toString());
    }

    @Test
    public final void insert_2()
    {
        insert_ten_items_ran(queue);
        assertEquals("insert_2 failed", "[0, 1, 6, 2, 4, 7, 8, 5, 3, 9]", queue.toString());
    }

    @Test
    public final void insert_3()
    {
        insert_ten_items_ran(queue);
        insert_ten_items_ran(queue);
        assertEquals("insert_3 failed", "[0, 0, 1, 1, 4, 6, 3, 5, 2, 9, 5, 7, 7, 8, 4, 6, 8, 3, 2, 9]", queue.toString());
    }

    @Test
    public final void size_0()
    {
        assertEquals("size_0 failed", 0, queue.size());
    }

    @Test
    public final void size_1()
    {
        queue.insert(0);
        assertEquals("size_1 failed", 1, queue.size());
    }

    @Test
    public final void size_2()
    {
        insert_ten_items_ran(queue);
        assertEquals("size_2 failed", 10, queue.size());
    }

    @Test
    public final void size_3()
    {
        insert_ten_items_ran(queue);
        insert_ten_items_ran(queue);
        assertEquals("size_3 failed", 20, queue.size());
    }

    @Test
    public final void empty_and_non_empty()
    {
        assertEquals("empty failed", true, queue.isEmpty());
        insert_ten_items_ran(queue);
        insert_ten_items_ran(queue);
        assertEquals("non_empty failed", false, queue.isEmpty());
    }

    @Test
    public final void min_0()
    {
        assertEquals("min_0 failed", null, queue.min());
        queue.insert(5);
        assertEquals("min_0 failed", Integer.valueOf(5), queue.min());
        queue.insert(2);
        assertEquals("min_0 failed", Integer.valueOf(2), queue.min());
        queue.insert(1);
        assertEquals("min_0 failed", Integer.valueOf(1), queue.min());
        queue.insert(3);
        assertEquals("min_0 failed", Integer.valueOf(1), queue.min());
    }

    @Test
    public final void remove_0()
    {
        assertEquals("remove_0 failed", null, queue.removeMin());
        assertEquals("remove_0 failed", "[]", queue.toString());
        insert_ten_items_ran(queue);
        assertEquals("remove_0 failed", Integer.valueOf(0), queue.removeMin());
        assertEquals("remove_0 failed", "[1, 2, 6, 3, 4, 7, 8, 5, 9]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(1), queue.removeMin());
        assertEquals("remove_0 failed", "[2, 3, 6, 5, 4, 7, 8, 9]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(2), queue.removeMin());
        assertEquals("remove_0 failed", "[3, 4, 6, 5, 9, 7, 8]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(3), queue.removeMin());
        assertEquals("remove_0 failed", "[4, 5, 6, 8, 9, 7]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(4), queue.removeMin());
        assertEquals("remove_0 failed", "[5, 7, 6, 8, 9]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(5), queue.removeMin());
        assertEquals("remove_0 failed", "[6, 7, 9, 8]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(6), queue.removeMin());
        assertEquals("remove_0 failed", "[7, 8, 9]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(7), queue.removeMin());
        assertEquals("remove_0 failed", "[8, 9]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(8), queue.removeMin());
        assertEquals("remove_0 failed", "[9]", queue.toString());
        assertEquals("remove_0 failed", Integer.valueOf(9), queue.removeMin());
        assertEquals("remove_0 failed", "[]", queue.toString());
        assertEquals("remove_0 failed", null, queue.removeMin());
        assertEquals("remove_0 failed", "[]", queue.toString());
    }

    @Test
    public final void example()
    {
        assertEquals("example failed", null, queue.min());
        assertEquals("example failed", "[]", queue.toString());
        queue.insert(4);
        assertEquals("example failed", Integer.valueOf(4), queue.min());
        assertEquals("example failed", "[4]", queue.toString());
        queue.insert(7);
        assertEquals("example failed", Integer.valueOf(4), queue.min());
        assertEquals("example failed", "[4, 7]", queue.toString());
        queue.insert(5);
        assertEquals("example failed", Integer.valueOf(4), queue.min());
        assertEquals("example failed", "[4, 7, 5]", queue.toString());
        queue.insert(2);
        assertEquals("example failed", Integer.valueOf(2), queue.min());
        assertEquals("example failed", "[2, 4, 5, 7]", queue.toString());
        queue.insert(3);
        assertEquals("example failed", Integer.valueOf(2), queue.min());
        assertEquals("example failed", "[2, 3, 5, 7, 4]", queue.toString());
        queue.insert(6);
        assertEquals("example failed", Integer.valueOf(2), queue.min());
        assertEquals("example failed", "[2, 3, 5, 7, 4, 6]", queue.toString());
        queue.insert(8);
        assertEquals("example failed", Integer.valueOf(2), queue.min());
        assertEquals("example failed", "[2, 3, 5, 7, 4, 6, 8]", queue.toString());
        queue.insert(9);
        assertEquals("example failed", Integer.valueOf(2), queue.min());
        assertEquals("example failed", "[2, 3, 5, 7, 4, 6, 8, 9]", queue.toString());
        queue.insert(1);
        assertEquals("example failed", Integer.valueOf(1), queue.min());
        assertEquals("example failed", "[1, 2, 5, 3, 4, 6, 8, 9, 7]", queue.toString());
        queue.insert(0);
        assertEquals("example failed", Integer.valueOf(0), queue.min());
        assertEquals("example failed", "[0, 1, 5, 3, 2, 6, 8, 9, 7, 4]", queue.toString());
    }

    // ---------- helper methods ---------

    public final void insert_ten_items_asc(MyPriorityQueue<Integer> queue)
    {
        for (int index = 0; index < 10; index++)
            queue.insert(index);
    }

    public final void insert_ten_items_des(MyPriorityQueue<Integer> queue)
    {
        for (int index = 9; index >= 0; index--)
            queue.insert(index);
    }

    public final void insert_ten_items_ran(MyPriorityQueue<Integer> queue)
    {
        int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
        for (int index = 0; index < array.length; index++)
            queue.insert(array[index]);
    }
}

