package com.xibo.algorithm.sort;

import java.util.Arrays;

/**
 * 排序算法抽象父类
 * @author xihao.pan
 */
public abstract class SortExample {

	SortExample() {}

	static <T extends Comparable<T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	static <T extends Comparable<T>> void exch(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	static <T extends Comparable<T>> void show(T[] a) {
		System.out.println(Arrays.toString(a));
	}

	static <T extends Comparable<T>> boolean isSorted(T[] a) {
		for (int i = 1; i <a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
	
}
