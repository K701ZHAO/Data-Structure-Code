import org.junit.Test;
import static org.junit.Assert.*;

public class TestList {
    @Test
    public void testSize() {
        int size = 10;
        // test IntList size
        IntList temp = null;
        for (int i = 0; i < 10; i++) {
            temp = new IntList(i, temp);
        }
        assertEquals(size, temp.size());
        assertEquals(size, temp.iterativeSize());

        // test SLList size
        SLList sl = new SLList(0);
        for (int i = 1; i < 10; i++) {
            sl.addFirst(i);
        }
        assertEquals(size, sl.size());
    }

    @Test
    public void testAddAndGetFirst() {
        SLList sl = new SLList(0);
        assertEquals(0, sl.getFirst());

        sl.addFirst(1);
        assertEquals(1, sl.getFirst());
    }

    @Test
    public void testAddLast() {
        SLList sl = new SLList(0);
        sl.addLast(1);
        // test size after addLast
        assertEquals(2, sl.size());

        // test first elem after addLast
        assertEquals(0, sl.getFirst());
    }

    @Test
    public void testIntListAddFirst() {
        IntList il = new IntList(0, null);
        for (int i = 1; i < 10; i++) {
            il.addFirst(i);
        }
        for (int i = 10; i > 0; i--) {
            int ithElem = il.get(10 - i);
            assertEquals(ithElem, i - 1);
        }
    }
}
