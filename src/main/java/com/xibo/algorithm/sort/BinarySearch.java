package com.xibo.algorithm.sort;

/**
 * 二分搜索
 * @author xihao.pan
 */
public class BinarySearch {
	
	private BinarySearch() {}
	
	// 循环
	public static int searchByCirculation(int key, int[] a) {
		int lo = 0;					
		int hi = a.length - 1;		
		
		while (hi >= lo) {
			int mid = lo + (hi - lo) / 2;
			if (key > a[mid]) {
				lo = mid + 1;
			} else if (key < a[mid]) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}
		return - 1;
	}
	
	// 递归
	public static int searchByRecursion(int key, int[] a) {
		return searchByRecursion(key, a, 0, a.length - 1);
	}
	
	// 递归
	public static int searchByRecursion(int key, int[] a, int lo, int hi) {
		if(lo > hi) return -1;

		int mid = (lo + hi) / 2;
		if (key > a[mid]) {
			return searchByRecursion(key, a, mid + 1, hi);
		}
		else if (key < a[mid]) {
			return searchByRecursion(key, a, lo, mid - 1);
		} else {
			return mid;
		}
	}
	
	// 少于的数量
	public static int countLess(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		int index = 0;
		boolean found = false;
		
		while (hi >= lo) {
			int middle = (hi + lo) / 2;
			if (key > a[middle]) {
				lo = middle + 1;
			} else if (key < a[middle]) {
				hi = middle - 1;
			} else {
				found = true;
				index = middle;
				break;
			}
		}
		
		if (!found) {
			return -1;
		}
		
		while (index != 0 && a[index - 1] == key) {
			index--;
		}
		
		return index;
	}
	
	// 相等的数量
	public static int countEqual(int key, int[] a) {
		int lo = 0;
		int hi = a.length - 1;
		int index = 0;
		int count = 0;
		boolean found = false;
		
		while (hi >= lo) {
			int middle = (hi + lo) / 2;
			if(key > a[middle]) {
				lo = middle + 1;
			} else if (key < a[middle]) {
				hi = middle - 1;
			} else {
				found = true;
				index = middle;
				count++;
				break;
			}
		}
		
		if (!found) {
			return -1;
		}
		
		int leftIndex = index;
		while (leftIndex != 0 && a[leftIndex - 1] == key) {
			leftIndex--;
			count++;
		}
		
		int rightIndex = index;
		while (rightIndex != a.length - 1 && a[rightIndex + 1] == key) {
			rightIndex++;
			count++;
		}
		return count;
	}
	
}
