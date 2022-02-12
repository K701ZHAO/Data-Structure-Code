public class IntList {
	private int first;
	private IntList rest;

	public IntList(int f, IntList r) {
		first = f;
		rest = r;
	}

	public void addFirst(int x) {
		IntList cpy = new IntList(first, rest);
		rest = cpy;
		first = x;
	}

	// 不考虑this为null的情况，应为此时intlist.size()就已经报错了。
	public int size() {
		if (rest == null) return 1;
		return 1 + rest.size();
	}

	public int iterativeSize() {
		IntList p = this;
		int totalSize = 0;
		while (p != null) {
			totalSize += 1;
			p = p.rest;
		}
		return totalSize;
	}

	public int get(int i) {
		if (i == 0) return first;

		if (rest == null) throw new NullPointerException();

		return rest.get(i - 1);
	}
}