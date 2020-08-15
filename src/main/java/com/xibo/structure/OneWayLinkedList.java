package com.xibo.structure;

import java.util.Iterator;

/**
 * 单向链表
 * @author xihao.pan
 */
public class OneWayLinkedList<T> implements Iterable<T> {

	private class Node {
		
		T data;
		Node next;
		
		Node(T obj) {
			this.data = obj;
		}
		
	}
	
	private Node first;
	private Node last;
	private int size;

	public void add(T data) {
		Node node = new Node(data);
		if(size++ == 0) {
			first = node;
		}
		else {
			last.next = node;
		}
		last = node;
	}

	public void addFirst(T data) {
		Node node = new Node(data);
		if(size++ == 0) {
			last = node;
		}
		else {
			node.next = first;
		}
		first = node;
	}

	public void addLast(T data) {
		add(data);
	}

	public void add(int index, T data) {
		if (index > size && index != 0 || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
		}

		if (index == 0) {
			addFirst(data);
		}
		else if (index == size) {
			add(data);
		}
		else {
			Node node = new Node(data);
			Node prevNode = first;
			for(int i = 1; i < index; i++) {
				prevNode = prevNode.next;
			}
			node.next = prevNode.next;
			prevNode.next = node;
			size++;
		}
	}

	public T get(int index) {
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
		}
		Node node = first;
		for(int i = 0; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	public T getFirst() {
		if(size == 0) {
			throw new IndexOutOfBoundsException("Size: " + size);
		}
		return first.data;
	}

	public T getLast() {
		if(size == 0) {
			throw new IndexOutOfBoundsException("Size: " + size);
		}
		return last.data;
	}

	public T remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
		}
		
		if (index == 0) {
			return removeFirst();
		}
		else if (index == size - 1) {
			return removeLast();
		}
		else {
			Node node = first;
			for(int i = 0; i < index - 1; i++) {
				node = node.next;
			}
			T data = node.next.data;
			node.next = node.next.next;
			size--;
			return data;
		}
	}

	public void remove(T data) {
		for(Node node = first; node != null; node = node.next) {
			while(node.next != null && node.next.data.equals(data)) {
				node.next = node.next.next;
				size--;
			}
		}
		
		if(first != null && first.data.equals(data)) {
			first = first.next;
			size--;
		}
	}

	public T removeFirst() {
		T data = first.data;
		first = first.next;
		last = size == 1 ? null : last;
		size--;
		return data;
	}

	public T removeLast() {
		T data = last.data;
		if (size > 1) {
			Node node = first;
			for(int i = 0; i < size - 2; i++) {
				node = node.next;
			}
			node.next = null;
			last = node;
			size--;
		} else {
			first = null;
			last = null;
			size--;
		}
		return data;
	}

	public boolean contains(T data) {
		for(Node node = first; node != null; node = node.next) {
			if(node.data.equals(data)) {
				return true;
			}
		}
		return false;
	}

	public void print() {
		for(T data : this) {
			System.out.print(data + " -> ");
		}
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
