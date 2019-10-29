package com.xibo.algorithm.sort;

/**
 * 快速排序 优化实现
 * @author xihao.pan
 */
public class QuickSortOfJava extends SortExample {
	
	private static final int INSERTION_SORT_CUTOFF = 8; // 少于此数使用插入排序
	private static final int MEDIAN_OF_3_CUTOFF = 40; // 少于此数取中间数
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		sort(a, 0, a.length - 1);
	}
	
	public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
		int N = hi - lo + 1;
		if (N <= INSERTION_SORT_CUTOFF) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		else if (N <= MEDIAN_OF_3_CUTOFF) {
			int m = median3(a, lo, lo + N/2, hi);
			exch(a, lo, m);
		} 
		else {
			int eps = N/8;
			int mid = lo + N/2;
			int m1 = median3(a, lo, lo + eps, lo + eps + eps);
			int m2 = median3(a, mid - eps, mid, mid + eps);
			int m3 = median3(a, hi - eps - eps, hi - eps, hi);
			int ninther = median3(a, m1, m2, m3);
			exch(a, lo, ninther);
		}
		
		T v = a[lo];
        int i = lo, j = hi + 1;
        int p = lo, q = hi + 1;
		
		while (true) {
			while (less(v, a[++i])) if (i >= hi) break;
			while (less(a[--j], v)) if (j <= lo) break;
            if (i == j && eq(a[i], v)) {
            	exch(a, ++p, i);
            }
			if (i >= j) break;
            exch(a, i, j);
            if (eq(a[i], v)) exch(a, ++p, i);
            if (eq(a[j], v)) exch(a, --q, j);
		}
		
		i = j + 1;
		for(int k = lo; k <= p; k++) {
			exch(a, k, j--);
		}
		for(int k = hi; k >= q; k--) {
			exch(a, k, i++);
		}
		
		sort(a, lo, j);
		sort(a, i, hi);
	}

	
	private static int median3(Comparable[] a, int i, int j, int k) {
		return (less(a[i], a[j]) ? 
			   (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
			   (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
	}
	
    private static boolean eq(Comparable v, Comparable w) {
        if (v == w) return true;
        return v.compareTo(w) == 0;
    }
	
}
