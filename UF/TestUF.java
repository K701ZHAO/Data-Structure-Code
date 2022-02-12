import org.junit.Test;
import static org.junit.Assert.*;
public class TestUF {

    @Test
    public void testCount() {
        // UF uf = new UF(10);
        // QuickUF uf = new QuickUF(10);
        WeightedQuickUF uf = new WeightedQuickUF(10);
        assertEquals(10, uf.count());
        uf.union(1, 0);
        uf.union(2, 0);
        assertEquals(8, uf.count());
        uf.union(1,2);
        assertEquals(8, uf.count());
    }

    @Test
    public void testConnected() {
        // UF uf = new UF(10);
        // QuickUF uf = new QuickUF(10);
        WeightedQuickUF uf = new WeightedQuickUF(10);
        for(int i = 0; i < 9; i++) {
            assertFalse(uf.connected(i, i + 1));
        }

        uf.union(0, 1);
        assertTrue(uf.connected(0, 1));
        uf.union(1, 2);
        assertTrue(uf.connected(0, 2));
    }
}
