package com.xibo.structure;

import java.util.Iterator;

/**
 * 队列
 * @author xihao.pan
 */
public class Queue<T> implements Iterable<T> {

	public Queue() {}
	
	public Queue(Queue<T> q) {
		for(T item : q) {
			enqueue(item);
			size++;
		}
	}
	
	private class Node {
		
		T data;
		Node next;
		
		Node (T data) {
			this.data = data;
		}
	}
	
	private int size;
	private Node first;
	private Node last;

	public void enqueue(T data) {
		Node node = new Node(data);
		if(size++ == 0) {
			first = node;
			last = node;
		}
		else {
			last.next = node;
			last = node;
		}
	}

	public T dequeue() {
		T data = first.data;
		first = first.next;
		size--;
		return data;
	}

	public void catenation(Queue<T> queue) {
		if(first == null) {
			first = queue.first;
			last = queue.last;
			size = queue.size;
			return;
		}

		last.next = queue.first;
		last = queue.last;
		size += queue.size;
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

		Node next = first;
		
		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public T next() {
			
			T data = next.data;
			next = next.next;
			
			return data;
		}
		
	}
	
}
