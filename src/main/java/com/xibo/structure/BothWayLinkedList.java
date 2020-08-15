package com.xibo.structure;

import java.util.Iterator;

/**
 * 双向链表
 * @author xihao.pan
 */
public class BothWayLinkedList<T> implements Iterable<T> {

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

	public void add(T data) {
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

	public void addFirst(T data) {
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

	public void addLast(T data) {
		add(data);
	}

	public void add(int index, T data) {
		if(index > size && index != 0 || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
		}

		if(index == 0) {
			addFirst(data);
		}
		else if (index == size) {
			add(data);
		}
		else {
			Node node = new Node(data);
			Node oldNode = first;
			for(int i = 0; i < index; i++) {
				oldNode = oldNode.next;
			}
			node.prev = oldNode.prev;
			node.next = oldNode;
			oldNode.prev.next = node;
			oldNode.prev = node;
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
		if(index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index: " + index + ",Size: " + size);
		}
		
		if(index == 0) {
			return removeFirst();
		}
		else if(index == size - 1) {
			return removeLast();
		}
		else {
			Node node = first;
			for(int i = 0; i < index; i++) {
				node = node.next;
			}
			node.prev.next = node.next;
			node.next.prev = node.prev;
			size--;
			return node.data;
		}
	}

	public void remove(T data) {
		for(Node node = first; node != null; node = node.next) {
			if(node.data == data) {
				if(node == first) {
					removeFirst();
				}
				else if(node == last) {
					removeLast();
				}
				else {
					node.prev.next = node.next;
					node.next.prev = node.prev;
					size--;
				}
			}
		}
	}

	public T removeFirst() {
		T data = first.data;
		if(size > 1) {
			first = first.next;
			first.prev = null;
			size--;
		}
		else {
			first = null;
			last = null;
			size--;
		}
		return data;
	}

	public T removeLast() {
		T data = last.data;
		if(size > 1) {
			last = last.prev;
			last.next = null;
			size--;
		}
		else {
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
