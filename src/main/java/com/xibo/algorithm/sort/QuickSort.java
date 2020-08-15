package com.xibo.algorithm.sort;

/**
 * 快速排序
 * @author xihao.pan
 */
public class QuickSort extends SortExample {

	private QuickSort() {}
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		sort(a, 0, a.length - 1);
	}
	
	public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
		if(hi <= lo + 8) {
			InsertionSort.sort(a, lo, hi);
			return;
		}
		if (hi <= lo) return;
		
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	private static < T extends Comparable<T> > int partition(T[] a, int lo, int hi) {
		T v = a[lo];
		int i = lo;
		int j = hi + 1;
		
		while(true) {
			while(less(a[++i], v)) if(i == hi) break;
			while(less(v, a[--j]));
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		
		return j;
	}
	
}
