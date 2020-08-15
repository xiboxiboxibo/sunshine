package com.xibo.structure;

import java.util.Iterator;

/**
 * 双向队列 数组实现
 * @author xihao.pan
 */
public class ArrayDeque<T> implements Iterable<T> {

	private T[] a = (T[]) new Object[2];
	private int first;
	private int last = 1;
	private int size;

	public void addFirst(T data) {
		a[ first = (first - 1) & (a.length - 1) ] = data;
		if(++size == a.length) {
			resize(a.length << 1);
		}
	}

	public void addLast(T data) {
		a[ last = (last + 1) & (a.length - 1) ] = data;
		if(++size == a.length) {
			resize(a.length << 1);
		}
	}

	public T popFirst() {
		if(size == 0) {
			throw new RuntimeException("Size : 0");
		}
		
		T data = a[first];
		a[first] = null;
		first = (first + 1) & (a.length - 1);
		
		if(--size == a.length >> 2) {
			resize(a.length >> 1);
		}

		return data;
	}

	public T popLast() {
		if(size == 0) {
			throw new RuntimeException("Size : 0");
		}
		
		T data = a[last];
		a[last] = null;
		last = (last - 1) & (a.length - 1);
		
		if(--size == a.length >> 2) {
			resize(a.length >> 1);
		}
		
		return data;
	}
	
	private void resize(int length) {
		T[] temp = (T[]) new Object[length];
		for(int i = 0; i < size; i++) {
			temp[i] = a[first];
			first = (first + 1) & (a.length - 1);
		}
		first = 0;
		last = size - 1;
		a = temp;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	/**
	 * 迭代器
	 */
	private class MyIterator implements Iterator<T> {

		int next = first;
		int N = size;
		
		@Override
		public boolean hasNext() {
			return N > 0;
		}

		@Override
		public T next() {
			T data = a[next];
			next = (next + 1) & (a.length - 1);
			N--;
			return data;
		}
		
	}
	
}
