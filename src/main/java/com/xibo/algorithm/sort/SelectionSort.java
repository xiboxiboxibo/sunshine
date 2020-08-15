package com.xibo.algorithm.sort;

/**
 * 选择排序
 * @author xihao.pan
 */
public class SelectionSort extends SortExample {

	private SelectionSort() {}
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		int N = a.length;
		for(int i = 0; i < N; i++) {
			int min = i;
			for(int j = i + 1; j < N; j++) {
				if(less(a[j], a[min])) {
					min = j;
				}
			}
			exch(a, i, min);
		}
	}

	public static <T extends Comparable<T>> void sortByRecursion(T[] a) {
		sort(a, 0, 0, 1);
	}

	public static <T extends Comparable<T>> void sort(T[] a, int i, int min, int cur) {
		if (i == a.length) {
			return;
		}
		if (cur == a.length) {
			exch(a, i, min);
			sort(a, i+1, i+1, i+2);
			return;
		}
		if (less(a[cur], a[min])) {
			min = cur;
		}
		sort(a, i, min, ++cur);
	}
	
}
