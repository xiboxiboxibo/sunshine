package com.xibo.structure;

import java.util.Iterator;

/**
 * 单向队列 数组实现
 * @author xihao.pan
 */
public class ArrayQueue<T> implements Iterable<T> {

	private T[] a = (T[]) new Object[2];
	private int size = 0;
	private int first = 0;
	private int last = 0;

	public void enqueue(T data) {
		if(last == a.length) {
			resize(a.length << 1);
		}
		a[last++] = data;
		size++;
	}

	public T dequeue() {
		T data = a[first];
		a[first++] = null;
		if(--size > 0 && size == a.length >> 2) {
			resize(a.length >> 1);
		}
		return data;
	}

	private void resize(int length) {
		T[] temp = (T[]) new Object[length];
		for(int i = 0; i < size;) {
			temp[i++] = a[first++];
		}
		last = size;
		first = 0;
		a = temp;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
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
		
		@Override
		public boolean hasNext() {
			return next < a.length && a[next] != null;
		}

		@Override
		public T next() {
			return a[next++];
		}
		
	}
}
