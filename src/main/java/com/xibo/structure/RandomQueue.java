package com.xibo.structure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * 随机队列
 * @author xihao.pan
 */
public class RandomQueue<T> implements Iterable<T> {

	private T[] a = (T[]) new Object[2];
	private int N;

	public void enqueue(T data) {
		a[N++] = data;
		if(N == a.length) {
			resize(a.length << 1);
		}
	}

	public T dequeue() {
		if(N == 0) {
			throw new RuntimeException("Size : 0");
		}
		
		Random rand = new Random();
		int n = rand.nextInt(N);
		T temp = a[n];
		a[n] = a[N - 1];
		a[N - 1] = temp;
		T data = a[--N];
		a[N] = null;

		return data;
	}

	public T sample() {
		if(N == 0) {
			throw new RuntimeException("Size : 0");
		}
		
		Random rand = new Random();
		return a[rand.nextInt(N)];
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
