package com.xibo.algorithm.sort;

/**
 * 归并排序
 * @author xihao.pan
 */
public class MergeSort extends SortExample {

	private MergeSort() {}
	
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sortByRecursion(a, aux, 0, a.length-1);
	}

	/**
	 * 递归实现
	 */
	private static void sortByRecursion(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(lo >= hi) return;

		int mid = lo +(hi - lo) / 2;

		sortByRecursion(a, aux, lo, mid);
		sortByRecursion(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	/**
	 * 循环实现
	 */
	public static void sortByCycle(Comparable[] a) {
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		for (int sz = 1; sz < N; sz <<= 1) {
			for (int lo = 0; lo < N - sz; lo += sz*2) {
				merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz*2 - 1 , N - 1));
			}
		}
	}
	
	private static void merge(Comparable a[], Comparable[] aux, int lo, int mid, int hi) {
		for(int k = lo; k <= mid; k++) {
			aux[k] = a[k];
		}
		for(int i = mid + 1, j = hi; j > mid;) {
			aux[i++] = a[j--];
		}
		int i = lo, j = hi;
		for(int k = lo; k <= hi; k++) {
			if(less(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j--];
		}
	}
	
}
