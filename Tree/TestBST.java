import org.junit.Test;
import static org.junit.Assert.*;

public class TestBST {

    public void addTestValue2Bst(BST bst) {
        bst.put("zhao", 1);
        bst.put("yang", 2);
        bst.put("kai", 3);
    }

    @Test
    public void testGetAndPut() {
        //BST<String, Integer> bst = new BST<String, Integer>();
        RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
        bst.put("zhao", 1);
        assertEquals(1, bst.size());
        assertEquals(new Integer(1), bst.get("zhao"));

        bst.put("yang", 2);
        assertEquals(2, bst.size());
        bst.get("yang");
        assertEquals(new Integer(2), bst.get("yang"));

        bst.put("kai", 3);
        assertEquals(3, bst.size());
        assertEquals(new Integer(3), bst.get("kai"));

        bst.put("kai", 4);
        assertEquals(3, bst.size());
        assertEquals(new Integer(4), bst.get("kai"));

        bst.put("liu", 5);
        assertEquals(4, bst.size());

        String[] keyList = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
        int[] seq = StdRandom.permutation(13);

        RedBlackBST<String, Integer> randBst = new RedBlackBST<String, Integer>();
        for(int i = 0; i < 13; i++) {
            randBst.put(keyList[seq[i]], i);
            assertEquals(i + 1, randBst.size());
            assertEquals(new Integer(i), randBst.get(keyList[seq[i]]));
            // test balance after put
            assertTrue(randBst.testBalance());
        }

    }

    @Test
    public void testMinAndMax() {
        BST<String, Integer> bst = new BST<String, Integer>();
        assertNull(bst.min());
        assertNull(bst.max());
        addTestValue2Bst(bst);
        assertEquals("kai", bst.min());
        assertEquals("zhao", bst.max());

    }

    @Test
    public void testFloorAndCeil() {
        BST<String, Integer> bst = new BST<String, Integer>();
        assertNull(bst.floor("abc"));
        assertNull(bst.ceil("zzz"));
        addTestValue2Bst(bst);

        assertEquals("zhao", bst.floor("zhaz"));
        assertEquals("kai", bst.floor("kai"));
        assertNull(bst.floor("abc"));
        assertEquals("yang", bst.floor("zao"));

        assertEquals("yang", bst.ceil("yang"));
        assertEquals("kai", bst.ceil("abc"));
        assertEquals("zhao", bst.ceil("yanq"));
        assertNull(bst.ceil("zhzz"));
    }

    @Test
    public void testSelectAndRank() {
        BST<String, Integer> bst = new BST<String, Integer>();
        assertNull(bst.select(0));
        assertEquals(0, bst.rank("zhao"));

        addTestValue2Bst(bst);
        assertEquals("kai", bst.select(0));
        assertEquals("yang", bst.select(1));
        assertEquals("zhao", bst.select(2));
        assertNull(bst.select(5));

        assertEquals(0, bst.rank("kai"));
        assertEquals(1, bst.rank("yang"));
        assertEquals(2, bst.rank("zhao"));
        assertEquals(0, bst.rank("abc"));
    }

    @Test
    public void testDelMinAndDelMax() {
        BST<String, Integer> bst = new BST<String, Integer>();
        addTestValue2Bst(bst);
        bst.deleteMin();
        assertEquals("yang", bst.min());
        assertEquals(2, bst.size());
        bst.put("kai", 1);
        bst.put("liu", 6);
        bst.deleteMin();
        assertEquals(3, bst.size());
        assertEquals("liu", bst.min());

        bst.put("zao", 7);
        bst.deleteMax();
        assertEquals("zao", bst.max());
        assertEquals(3, bst.size());

        bst.deleteMax();
        assertEquals("yang", bst.max());
        assertEquals(2, bst.size());
    }

    @Test
    public void testDelete() {
        BST<String, Integer> bst = new BST<String, Integer>();
        addTestValue2Bst(bst);

        bst.delete("zhao");
        assertEquals(2, bst.size());
        assertNull(bst.get("zhao"));

        bst.delete("abc");
        assertEquals(2, bst.size());

        bst.put("zhao", 3);
        bst.put("liu", 4);
        bst.delete("kai");
        assertEquals(3, bst.size());
        assertNull(bst.get("kai"));
        bst.delete("yang");
        assertEquals(2, bst.size());
        assertNull(bst.get("yang"));
    }

    @Test
    public void testRedBlackDelMinAndMax() {
        String[] keyList = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
        int[] seq = StdRandom.permutation(13);

        RedBlackBST<String, Integer> randBst = new RedBlackBST<String, Integer>();
        for(int i = 0; i < 13; i++) {
            randBst.put(keyList[seq[i]], i);
        }

        for(int i = 0; i < 13; i++) {
            randBst.deleteMin();
            // test balance after deleteMin
            assertTrue(randBst.testBalance());
            assertEquals(13 - i - 1, randBst.size());
            assertNull(randBst.get(keyList[i]));
        }

        RedBlackBST<String, Integer> randBstMax = new RedBlackBST<String, Integer>();
        for(int i = 0; i < 13; i++) {
            randBstMax.put(keyList[seq[i]], i);
        }

        for(int i = 0; i < 13; i++) {
            randBstMax.deleteMax();
            assertTrue(randBst.testBalance());
            assertEquals(13 - i - 1, randBstMax.size());
            assertNull(randBstMax.get(keyList[13 - i - 1]));
        }
    }

    @Test
    public void testRedBlackDel() {
        String[] keyList = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"};
        int[] seq = StdRandom.permutation(13);
        // int[] seq = new int[] {3, 5, 1, 2, 7, 6, 11, 8, 12, 4, 0, 10, 9};

        RedBlackBST<String, Integer> randBst = new RedBlackBST<String, Integer>();
        for(int i = 0; i < 13; i++) {
            randBst.put(keyList[seq[i]], i);
        }

        // delete element not in keyList
        randBst.delete("o");
        assertTrue(randBst.testBalance());
        assertEquals(13, randBst.size());

        for(int i = 0; i< 13; i++) {
            System.out.print(seq[i]);
            System.out.print(" ");
        }

        int[] delSeq = StdRandom.permutation(13);
        for(int i = 0; i< 13; i++) {
            System.out.print(delSeq[i]);
            System.out.print(" ");
        }
        // int[] delSeq = new int[] {8, 11, 1, 5, 12, 6, 2, 7, 4, 3 ,9, 10, 0};


        for(int i = 0; i < 13; i++) {
            randBst.delete(keyList[delSeq[i]]);
            // test balance after deleteMin
            assertTrue(randBst.testBalance());
            assertEquals(13 - i - 1, randBst.size());
            assertNull(randBst.get(keyList[delSeq[i]]));
        }
    }

}
