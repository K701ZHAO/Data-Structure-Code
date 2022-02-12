public class QuickUF {
    private int[] id;
    private int count;

    public QuickUF(int N) {
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
        }
        count = N;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId != qId) {
            id[p] = qId;
            count--;
        }
    }

    private int find(int p) {
        if (p >= id.length) {
            throw new IndexOutOfBoundsException("p is greater than length!");
        }
        int pId = id[p];
        if (pId == p) return p;
        return find(pId);
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
