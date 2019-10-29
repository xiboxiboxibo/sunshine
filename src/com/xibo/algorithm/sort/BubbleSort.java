package com.xibo.algorithm.sort;

import java.util.Comparator;

/**
 * 冒泡排序
 * @author xihao.pan
 */
public class BubbleSort extends SortExample{
	
	private BubbleSort() {}
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		boolean swappable = true;
		for (int length = a.length; length > 0 && swappable; length--) {
			swappable = false;
			for (int i = 1; i < length; i++) {
				if (less(a[i], a[i-1])) {
					T temp = a[i];
					a[i] = a[i-1];
					a[i-1] = temp;
					swappable = true;
				}
			}
		}
	}
	
	public static <T> void sort(T[] a, Comparator<T> comp) {
		boolean swappable = true;
		for(int length = a.length - 1; length > -1 && swappable; length--) {
			swappable = false;
			for(int i = 0; i < length; i++) {
				if(comp.compare(a[i], a[i + 1]) > 0) {
					T temp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = temp;
					swappable = true;
				}
			}
		}
	}
	
}
