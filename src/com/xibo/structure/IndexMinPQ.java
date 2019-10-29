package com.xibo.structure;

import java.util.NoSuchElementException;

/**
 * 索引小顶堆
 * @author xihao.pan
 */
public class IndexMinPQ<T extends Comparable<T>> {

	private int maxN;
	private int N;
	private int pq[];
	private int qp[];
	private T[] keys;
	
	public IndexMinPQ(int maxN) {
		keys = (T[]) new Comparable[maxN + 1];
		pq = new int[maxN + 1];
		qp = new int[maxN + 1];
		this.maxN = maxN;
		for (int i = 0; i <= maxN; i++) {
			qp[i] = -1;
		}
	}
	
	private boolean less(int i, int j) {
		return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
	}
	
	private void exch(int i, int j) {
		int temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	private void swim(int k) {
		while (k > 1 && !less(k/2, k)) {
			exch(k/2, k);
			k /= 2;
		}
	}
	
	private void sink(int k) {
		while (2*k <= N) {
			int i = 2*k;
			if (i < N && !less(i, i+1)) i++;
			if (!less(i, k)) break;
			exch(i, k);
			k = i;
		}
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public boolean contains(int i) {
		return qp[i] != -1;
	}
	
	public void insert(int i, T key) {
        if (i < 0 || i > maxN) throw new IllegalArgumentException();
        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
		N++;
		keys[i] = key;
		qp[i] = N;
		pq[N] = i;
		swim(N);
	}
	
	public int delMin() {
		int min = pq[1];
		exch(1, N);
		sink(1);
		keys[pq[1]] = null;
		qp[min] = -1;
		pq[N--] = -1;
		return min;
	}
	
	public T min() {
		return keys[pq[1]];
	}
	
	public int minIndex() {
		return pq[1];
	}
	
	public void change(int i, T key) {
		if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
		keys[i] = key;
		swim(qp[i]);
		sink(qp[i]);
	}
	
	public void delete(int i) {
		if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
		int index = pq[i];
		exch(index, N--);
		swim(index);
		sink(index);
		qp[i] = -1;
		keys[i] = null;
	}
	
}
