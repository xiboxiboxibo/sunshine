package com.xibo.structure;

import java.util.Iterator;

/**
 * 栈 数组实现
 * @author xihao.pan
 */
public class ArrayStack<T> implements Iterable<T> {
	
	private T[] a = (T[]) new Object[2];
	private int N = 0;

	public void push(T item) {
		if(N == a.length) {
			resize(N << 1);
		}
		a[N++] = item;
	}

	public T pop() {
		T item = a[--N];
		a[N] = null;
		if(N > 0 && N == a.length >> 2) {
			resize(a.length >> 1);
		}
		return item;
	}

	private void resize(int max) {
		T[] temp = (T[]) new Object[max];
		for(int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator<T> {

		private int next = N;
		
		@Override
		public boolean hasNext() {
			return next > 0;
		}

		@Override
		public T next() {
			return a[--next];
		}
		
	}
	
}
