package com.xibo.algorithm.sort;

/**
 * 插入排序
 * @author xihao.pan
 */
public class InsertionSort extends SortExample {

	private InsertionSort() {};
	
	public static <T extends Comparable<T>> void sort(T a[]) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}
	
	public static < T extends Comparable<T> > void sort(T a[], int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	public static <T extends Comparable<T>> Integer[] indirectSort(T[] a) {
		int N = a.length;
		Integer[] result = new Integer[N];
		for (int i = 0; i < N; i++) {
			result[i] = i;
		}
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0 && less(a[result[j]], a[result[j-1]]); j--) {
				exch(result, j, j-1);
			}
		}
		return result;
	}
	
}
