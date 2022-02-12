public class QuickSort extends Example {

	public static void sort (Comparable[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi) return;
		int mid = partition(a, lo, hi);
		sort(a, lo, mid - 1);
		sort(a, mid + 1, hi);
	}

	public static Comparable select(Comparable[] a, int k) {
		StdRandom.shuffle(a);
		int lo = 0;
		int hi = a.length - 1;
		while(hi > lo) {
			int mid = partition(a, lo, hi);
			if (mid == k) return a[k];
			if (mid > k) hi = mid - 1;
			if (mid < k) lo = mid + 1;
		}
		return a[k];
	}

	private static void partition(Comparable[] a, int lo, int hi) {
		Comparable v = a[lo];
		int i = lo;
		int j = hi + 1;

		while(true) {
			while(less(a[++i], v)) if (i >= hi) break;
			while(less(v, a[--j])) if (j <= lo) break;
			if (i >= j) break;
			exch(a, i, j);
		}

		exch(a, lo, j);
		return j;
	}
}