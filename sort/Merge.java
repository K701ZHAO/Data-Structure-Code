public class Merge {
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);

	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int high) {
		if (hi <= lo) return;
		int mid = lo + (high - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid, high);
		merge(a, aux, lo, mid, high);
	}

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int high) {
		int i = lo;
		int j = mid;
		for(int k = lo, k < high; k++) {
			aux[k] = a[k];
		}
		for(int k = lo, k < high; k++) {
			if (i > mid) a[k] = aux[j++];
			if (j > high) a[k] = aux[i++];
			else if (less(a[j], a[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
}
