public class WeightedQuickUF {
    private int[] id;
    private int[] size;
    private int count;

    public WeightedQuickUF(int N) {
        id = new int[N];
        size = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
        count = N;
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot != qRoot) {
            if (size[pRoot] > size[qRoot]) {
                id[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            } else {
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            count--;
        }

    }

    private int find(int p) {
        if (p >= id.length) {
            throw new IndexOutOfBoundsException("p is greater than length!");
        }
        int pId = id[p];
        if (pId == p) return p;
        int pRoot = find(pId);
        id[pId] = pRoot;
        return pRoot;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
