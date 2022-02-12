
public class BST <Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N; // # nodes in subtrees rooted here
        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        Node x = min(root);
        if (x == null) return null;
        return x.key;
    }

    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        Node x = max(root);
        if (x == null) return null;
        return x.key;
    }

    private Node max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        Node tmp = floor(x.right, key);

        if (cmp < 0) return floor(x.left, key);
        else if (cmp > 0 && tmp != null) return tmp;
        else return x;
    }

    public Key ceil(Key key) {
        Node x = ceil(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        Node tmp = ceil(x.left, key);

        if (cmp > 0) return ceil(x.right, key);
        else if (cmp < 0 && tmp != null) return tmp;
        else return x;
    }

    public Key select(int k) {
        Node x = select(root, k);
        if (x == null) return null;
        return x.key;
    }

    private Node select(Node x, int k) {
        if (x == null) return null;
        int leftSize = size(x.left);

        if (leftSize == k) return x;
        else if (leftSize < k) return select(x.right, k - leftSize - 1);
        else return select(x.left, k);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return size(x.left) + 1 + rank(x.right, key);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x == null) return null;
        if (x.right == null) return x.left;

        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.left) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node tmp = min(x.right);
            x.right = deleteMin(x.right);
            tmp.left = x.left;
            tmp.right = x.right;
            x = tmp;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }
	
}