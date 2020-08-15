package com.xibo.structure;

/**
 * 大顶堆
 * @author xihao.pan
 */
public class MaxPQ<T extends Comparable<T>> {

	private T[] pq;
	private int N = 0;
	
	public MaxPQ() {
		this(1);
	}
	
	public MaxPQ(int maxN) {
		pq = (T[]) new Comparable[maxN + 1];
	}
	
	public MaxPQ(T[] a) {
		N = a.length;
		pq = (T[]) new Comparable[a.length + 1];
		for (int i = 0; i < N; i++) {
			pq[i+1] = a[i];
		}
		for (int i = N >> 1; i > 0; i--) {
			sink(i);
		}
	}
	
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}
	
	private void exch(int i, int j) {
		T temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}
	
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N) {
			int i = 2*k;
			if (i < N && less(i, i+1)) i++;
			if (less(i, k)) break;
			exch(k,i);
			k = i;
		}
	}
	
	private void resize(int max) {
		
		T[] temp = (T[]) new Comparable[max];
		
		for (int i = 1; i <= N; i++) {
			temp[i] = pq[i];
		}
		
		pq = temp;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public void insert(T v) {
		
		if (N == pq.length - 1) {
			resize(pq.length << 1);
		}
		
		pq[++N] = v;
		swim(N);
	}
	
	public T delMax() {
		T max = pq[1];
		exch(1, N--);
		pq[N+1] = null;
		sink(1);
		if (N > 0 && N == pq.length >> 2) {
			resize(pq.length >> 1);
		}
		return max;
	}
	
	public T max() {
		return pq[1];
	}
	
}
