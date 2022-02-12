public class RedBlackBST <Key extends Comparable<Key>, Value>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private int N;
        private boolean color;
        private Node left, right;

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    private Node max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x;
        return max(x.right);
    }

    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        return min(x.left);
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
         h.color = !h.color;
         h.left.color = !h.left.color;
         h.right.color = !h.right.color;
    }

    private Node balance(Node h) {
        h.N = size(h.left) + size(h.right) + 1;
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        return h;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1, RED);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(x.right, key, val);
        else if (cmp < 0) x.left = put(x.left, key, val);
        else x.val = val;

        return balance(x);
    }

    public Value get(Key key) {
        Node x = get(root, key);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    private Node lendRedFromRight(Node h) {
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            flipColors(h);
            h = rotateLeft(h);
        }
        flipColors(h);
        return h;
    }

    private Node lendRedFromLeft(Node h) {
        if (isRed(h.left.left)) {
            flipColors(h);
            h = rotateRight(h);
        }
        flipColors(h);
        return h;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deleteMin() {
        if (isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h == null || h.left == null) return null;
        if (!isRed(h.left)) h = lendRedFromRight(h);
        h.left = deleteMin(h.left);

        return balance(h);
    }

    public void deleteMax() {
        if (isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    public Node deleteMax(Node h) {
        if (h == null) return null;
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) h = lendRedFromLeft(h); // lendRedFromLeft h.right.right becomes red
        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(Key key) {
        if(isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (h == null) return null;
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            if (h.left == null) return h;
            if (!isRed(h.left)) h = lendRedFromRight(h);
            h.left = delete(h.left, key);
        } else if (cmp > 0) {
            if (h.right == null) return h;
            if (isRed(h.left)) h = rotateRight(h);
            if (!isRed(h.right) && !isRed(h.right.left)) h = lendRedFromLeft(h);
            h.right = delete(h.right, key);
        } else {
            if (h.right == null) {
                if (h.left != null) h.left.color = h.color;
                return h.left;
            };
            Node replace;
            if (!isRed(h.left) && isRed(h.right.left)) {
                replace = min(h.right);
                h.right = deleteMin(h.right);
            } else {
                if (!isRed(h.left)) flipColors(h);
                replace = max(h.left);
                h.left = deleteMax(h.left);
            }
            replace.color = h.color;
            replace.left = h.left;
            replace.right = h.right;
            h = replace;
        }
        return balance(h);
    }

    // test balance
    private int leftHeight(Node h) {
        if (h == null) return 0;
        if (!isRed(h.left)) return 1 + leftHeight(h.left);
        return leftHeight(h.left);
    }

    private int rightHeight(Node h) {
        if (h == null) return 0;
        return 1 + rightHeight(h.right);
    }

    // compare left Height & right height
    public boolean isSameHeight(Node left, Node right) {
        return leftHeight(left) == leftHeight(right) && rightHeight(left) == rightHeight(right);
    }

    public boolean testBalance() {
        return testBalance(root);
    }

    private boolean testBalance(Node h) {
        if (h == null) return true;
        if (isRed(h.left) && !isSameHeight(h, h.left)) return false;
        if (!isRed(h.left) && !isSameHeight(h.left, h.right)) return false;

        return testBalance(h.left) && testBalance(h.right);
    }
}
