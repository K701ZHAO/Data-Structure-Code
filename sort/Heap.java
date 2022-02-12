public class Heap extends Example {
	public static void sort(Comparable[] a) {
		int N = a.length;
		for(int i = (N - 1) / 2; i >= 0; i--) {
			sink(a, i, N);
		}

		while(N >= 1) {
			exch(a, 0, N--);
			sink(a, 0, N);
		}
	}

	private static void sink(Comparable[] a, int k, int n) {
		if (left(k) > n - 1) return;
		int max = left(k);
		if (right(k) <= n - 1 && less(a[max], a[right(k)])) max = right(k);
		if (less(a[k], a[max])) exch(a, k, max);
		sink(a, max, n);

	}

	private static int left(int k) {
		return 2 * k + 1;
	}

	private static int right(int k) {
		return 2 * k + 2;
	}

	private static int parent(int k) {
		return (k - 1) / 2;
	}
}