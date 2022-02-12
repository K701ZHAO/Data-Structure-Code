import java.util.EmptyStackException;

public class SLList {

    public static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel = new IntNode(0, null);
    private int size;

    public SLList() {
        size = 0;
    }

    public SLList(int x) {
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size++;
    }

    public int getFirst() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return sentinel.next.item;
    }

    public void addLast(int x) {
        IntNode last = new IntNode(x, null);
        //  get original last node
        IntNode temp = sentinel;
        while(temp.next != null) {
            temp = temp.next;
        }
        temp.next = last;
        // add size
        size++;
    }

    public int size() {
        return size;
    }
}
