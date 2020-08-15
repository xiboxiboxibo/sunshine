package com.xibo.structure;

import java.util.Iterator;

/**
 * 双向队列
 * @author xihao.pan
 */
public class Deque<T> implements Iterable<T> {

	private class Node {
		
		T data;
		Node next;
		Node prev;
		
		Node(T data) {
			this.data = data;
		}
		
	}
	
	private Node first;
	private Node last;
	private int size;

	public void pushLeft(T data) {
		Node node = new Node(data);
		if(size++ == 0) {
			last = node;
		}
		else {
			first.prev = node;
			node.next = first;
		}
		first = node;
	}

	public void pushRight(T data) {
		Node node = new Node(data);
		if(size++ == 0) {
			first = node;
		}
		else {
			last.next = node;
			node.prev = last;
		}
		last = node;
	}

	public T popLeft() {
		T data = first.data;
		if(size > 1) {
			first = first.next;
			first.prev = null;
			size--;
		}
		else if(size == 1) {
			first = null;
			last = null;
			size--;
		}
		return data;
	}

	public T popRight() {
		T data= last.data;
		if(size > 1) {
			last = last.prev;
			last.next = null;
			size--;
		}
		else if(size == 1) {
			first = null;
			last = null;
			size--;
		}
		return data;
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
