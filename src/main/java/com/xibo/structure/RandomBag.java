package com.xibo.structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * 随机包
 * @author xihao.pan
 */
public class RandomBag<T> implements Iterable<T> {

	private T[] a = (T[]) new Object[2];
	private int N;
	
	public void add(T data) {
		a[N++] = data;
		if(N == a.length) {
			resize(a.length << 1);
		}
	}
	
	private void resize(int length) {
		T[] temp = (T[]) new Object[length];
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
	
	/**
	 * 迭代器
	 */
	private class MyIterator implements Iterator<T> {

		int next;
		T[] t;
		
		MyIterator() {
			t = Arrays.copyOf(a , a.length);
			Random rand = new Random();
			for(int i = 0; i < N; i++) {
				int n = rand.nextInt(N);
				T temp = t[i];
				t[i] = t[n];
				t[n] = temp;
			}
		}
		
		@Override
		public boolean hasNext() {
			return next < N;
		}

		@Override
		public T next() {
			return t[next++];
		}
		
	}
}
