
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyBinarySearchTreeAVLTest
{
    private MyBinarySearchTree<Integer> tree;

    @Before
    public final void setup()
    {
        tree = new MyBinarySearchTree<Integer>(true);
    }

    @Test
    public final void insert_e()
    {
        assertEquals("insert_e failed", "[]", tree.toString());
    }

    @Test
    public final void insert_0()
    {
        tree.add(0);
        assertEquals("insert_0 failed", "[0:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_1()
    {
        tree.add(0);
        tree.add(1);
        assertEquals("insert_1 failed", "[0:H1:B-1, 1:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_2()
    {
        tree.add(1);
        tree.add(0);
        assertEquals("insert_2 failed", "[0:H0:B0, 1:H1:B1]", tree.toString());
    }

    @Test
    public final void insert_3()
    {
        tree.add(0);
        tree.add(1);
        tree.add(2);
        assertEquals("insert_3 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_4()
    {
        tree.add(0);
        tree.add(2);
        tree.add(1);
        assertEquals("insert_4 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_5()
    {
        tree.add(1);
        tree.add(0);
        tree.add(2);
        assertEquals("insert_5 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_6()
    {
        tree.add(1);
        tree.add(2);
        tree.add(0);
        assertEquals("insert_6 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_7()
    {
        tree.add(2);
        tree.add(0);
        tree.add(1);
        assertEquals("insert_7 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_8()
    {
        tree.add(2);
        tree.add(1);
        tree.add(0);
        assertEquals("insert_8 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_9()
    {
        insert_ten_items_asc(tree);
        assertEquals("insert_9 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0, 3:H3:B-1, 4:H0:B0, 5:H1:B0, 6:H0:B0, 7:H2:B0, 8:H1:B-1, 9:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_10()
    {
        insert_ten_items_des(tree);
        assertEquals("insert_10 failed", "[0:H0:B0, 1:H1:B1, 2:H2:B0, 3:H0:B0, 4:H1:B0, 5:H0:B0, 6:H3:B1, 7:H0:B0, 8:H1:B0, 9:H0:B0]", tree.toString());
    }

    @Test
    public final void insert_11()
    {
        insert_ten_items_ran(tree);
        assertEquals("insert_11 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0, 3:H2:B1, 4:H0:B0, 5:H3:B0, 6:H0:B0, 7:H2:B-1, 8:H1:B-1, 9:H0:B0]", tree.toString());
    }

    @Test
    public final void height_0()
    {
        assertEquals("height_0 failed", -1, tree.height());
    }

    @Test
    public final void height_1()
    {
        tree.add(0);
        assertEquals("height_1 failed", 0, tree.height());
    }

    @Test
    public final void height_2()
    {
        tree.add(0);
        tree.add(1);
        assertEquals("height_2 failed", 1, tree.height());
    }

    @Test
    public final void height_3()
    {
        tree.add(0);
        tree.add(1);
        tree.add(2);
        assertEquals("height_3 failed", 1, tree.height());
    }

    @Test
    public final void height_4()
    {
        insert_ten_items_asc(tree);
        assertEquals("height_4 failed", 3, tree.height());
    }

    @Test
    public final void height_5()
    {
        insert_ten_items_des(tree);
        assertEquals("height_5 failed", 3, tree.height());
    }

    @Test
    public final void height_6()
    {
        insert_ten_items_ran(tree);
        assertEquals("height_6 failed", 3, tree.height());
    }

    @Test
    public final void remove_0()
    {
        tree.remove(0);
        assertEquals("remove_0 failed", "[]", tree.toString());
    }

    @Test
    public final void remove_1()
    {
        insert_ten_items_ran(tree);
        tree.remove(10);
        assertTest("remove_1 failed", tree.toString(),
                "[0:H0:B0, 1:H1:B0, 2:H0:B0, 3:H2:B1, 4:H0:B0, 5:H3:B0, 6:H0:B0, 7:H2:B-1, 8:H1:B-1, 9:H0:B0]",
                "_____");
        tree.remove(0);
        assertTest("remove_1 failed", tree.toString(),
                "[1:H1:B-1, 2:H0:B0, 3:H2:B1, 4:H0:B0, 5:H3:B0, 6:H0:B0, 7:H2:B-1, 8:H1:B-1, 9:H0:B0]",
                "_____");
        tree.remove(5);
        assertTest("remove_1 failed", tree.toString(),
                "[1:H0:B0, 2:H1:B0, 3:H0:B0, 4:H3:B-1, 6:H0:B0, 7:H2:B-1, 8:H1:B-1, 9:H0:B0]",
                "[1:H1:B-1, 2:H0:B0, 3:H2:B1, 4:H0:B0, 6:H3:B1, 7:H0:B0, 8:H1:B0, 9:H0:B0]");
        tree.remove(9);
        assertTest("remove_1 failed", tree.toString(),
                "[1:H0:B0, 2:H1:B0, 3:H0:B0, 4:H2:B0, 6:H0:B0, 7:H1:B0, 8:H0:B0]",
                "[1:H1:B-1, 2:H0:B0, 3:H2:B1, 4:H0:B0, 6:H3:B1, 7:H0:B0, 8:H1:B1]");
        tree.remove(3);
        assertTest("remove_1 failed", tree.toString(),
                "[1:H0:B0, 2:H1:B1, 4:H2:B0, 6:H0:B0, 7:H1:B0, 8:H0:B0]",
                "[1:H0:B0, 2:H1:B0, 4:H0:B0, 6:H2:B0, 7:H0:B0, 8:H1:B1]");
        tree.remove(7);
        assertTest("remove_1 failed", tree.toString(),
                "[1:H0:B0, 2:H1:B1, 4:H2:B0, 6:H1:B-1, 8:H0:B0]",
                "[1:H0:B0, 2:H1:B0, 4:H0:B0, 6:H2:B1, 8:H0:B0]");
        tree.remove(1);
        assertTest("remove_1 failed", tree.toString(),
                "[2:H0:B0, 4:H2:B-1, 6:H1:B-1, 8:H0:B0]",
                "[2:H1:B-1, 4:H0:B0, 6:H2:B1, 8:H0:B0]");
        tree.remove(8);
        assertTest("remove_1 failed", tree.toString(),
                "[2:H0:B0, 4:H1:B0, 6:H0:B0]",
                "_____");
        tree.remove(4);
        assertTest("remove_1 failed", tree.toString(),
                "[2:H1:B-1, 6:H0:B0]",
                "[2:H0:B0, 6:H1:B1]");
        tree.remove(1);
        assertTest("remove_1 failed", tree.toString(),
                "[2:H1:B-1, 6:H0:B0]",
                "[2:H0:B0, 6:H1:B1]");
        tree.remove(2);
        assertTest("remove_1 failed", tree.toString(),
                "[6:H0:B0]",
                "_____");
        tree.remove(6);
        assertEquals("remove_1 failed", "[]", tree.toString());
        tree.remove(0);
        assertEquals("remove_1 failed", "[]", tree.toString());
    }

    @Test
    public final void find_0()
    {
        assertEquals("find_0 failed", "[]", tree.toString());
        assertEquals("find_0 failed", null, tree.find(0));
        assertEquals("find_0 failed", 1, tree.comparisons);
    }

    @Test
    public final void find_1()
    {
        insert_ten_items_asc(tree);
        assertEquals("find_1 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0, 3:H3:B-1, 4:H0:B0, 5:H1:B0, 6:H0:B0, 7:H2:B0, 8:H1:B-1, 9:H0:B0]", tree.toString());
        assertEquals("find_1 failed", null, tree.find(10));
        assertEquals("find_1 failed", 5, tree.comparisons);
        assertEquals("find_1 failed", Integer.valueOf(0), tree.find(0));
        assertEquals("find_1 failed", 8, tree.comparisons);
        assertEquals("find_1 failed", Integer.valueOf(9), tree.find(9));
        assertEquals("find_1 failed", 12, tree.comparisons);
        assertEquals("find_1 failed", Integer.valueOf(5), tree.find(5));
        assertEquals("find_1 failed", 15, tree.comparisons);
    }

    @Test
    public final void find_2()
    {
        insert_ten_items_des(tree);
        assertEquals("find_2 failed", "[0:H0:B0, 1:H1:B1, 2:H2:B0, 3:H0:B0, 4:H1:B0, 5:H0:B0, 6:H3:B1, 7:H0:B0, 8:H1:B0, 9:H0:B0]", tree.toString());
        assertEquals("find_2 failed", null, tree.find(10));
        assertEquals("find_2 failed", 4, tree.comparisons);
        assertEquals("find_2 failed", Integer.valueOf(0), tree.find(0));
        assertEquals("find_2 failed", 8, tree.comparisons);
        assertEquals("find_2 failed", Integer.valueOf(9), tree.find(9));
        assertEquals("find_2 failed", 11, tree.comparisons);
        assertEquals("find_2 failed", Integer.valueOf(5), tree.find(5));
        assertEquals("find_2 failed", 15, tree.comparisons);
    }

    @Test
    public final void find_3()
    {
        insert_ten_items_ran(tree);
        assertEquals("find_3 failed", "[0:H0:B0, 1:H1:B0, 2:H0:B0, 3:H2:B1, 4:H0:B0, 5:H3:B0, 6:H0:B0, 7:H2:B-1, 8:H1:B-1, 9:H0:B0]", tree.toString());
        assertEquals("find_3 failed", null, tree.find(10));
        assertEquals("find_3 failed", 5, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(0), tree.find(0));
        assertEquals("find_3 failed", 9, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(1), tree.find(1));
        assertEquals("find_3 failed", 12, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(2), tree.find(2));
        assertEquals("find_3 failed", 16, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(3), tree.find(3));
        assertEquals("find_3 failed", 18, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(4), tree.find(4));
        assertEquals("find_3 failed", 21, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(5), tree.find(5));
        assertEquals("find_3 failed", 22, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(6), tree.find(6));
        assertEquals("find_3 failed", 25, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(7), tree.find(7));
        assertEquals("find_3 failed", 27, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(8), tree.find(8));
        assertEquals("find_3 failed", 30, tree.comparisons);
        assertEquals("find_3 failed", Integer.valueOf(9), tree.find(9));
        assertEquals("find_3 failed", 34, tree.comparisons);
    }

    @Test
    public final void empty_and_non_empty()
    {
        assertEquals("empty failed", true, tree.isEmpty());
        insert_ten_items_ran(tree);
        assertEquals("non_empty failed", false, tree.isEmpty());
    }

    @Test
    public final void unique_words() throws Exception
    {
		new UniqueWords();
//		new UniqueWords("./WarAndPeace.txt");
    }

    // ---------- helper methods ---------

    public final void insert_ten_items_asc(MyBinarySearchTree<Integer> tree)
    {
        for (int index = 0; index < 10; index++)
            tree.add(index);
    }

    public final void insert_ten_items_des(MyBinarySearchTree<Integer> tree)
    {
        for (int index = 9; index >= 0; index--)
            tree.add(index);
    }

    public final void insert_ten_items_ran(MyBinarySearchTree<Integer> tree)
    {
        int array[] = { 5, 3, 7, 1, 4, 6, 8, 2, 0, 9 };
        for (int index = 0; index < array.length; index++)
            tree.add(array[index]);
    }

    // helper function for old unit testing library
    public final void assertTest(String test_name, String actual_value, String ... expected_values)
    {
        for (String expected_value : expected_values)
            if (expected_value.equals(actual_value))
                return;

        assertEquals(test_name, String.format("%s", to_string(expected_values), actual_value), tree.toString());
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

