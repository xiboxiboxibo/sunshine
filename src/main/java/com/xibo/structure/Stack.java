package com.xibo.structure;

import java.util.Iterator;

/**
 * 栈
 * @author xihao.pan
 */
public class Stack<T> implements Iterable<T> {

	public Stack() {}
	
	public Stack(Stack<T> s) {
		first = s.first;
		size = s.size;
	}
	
	private class Node {
		
		T data;
		Node next;
		
		Node(T data) {
			this.data = data;
		}
		
	}
	
	int size;
	Node first;

	public void push(T data) {
		Node node = new Node(data);
		node.next = first;
		first = node;
		size++;
	}

	public T pop() {
		T data = first.data;
		first = first.next;
		size--;
		return data;
	}

	public T peek() {
		return first.data;
	}

	public void catenation(Stack<T> stack) {
		if (first == null) {
			first = stack.first;
			size = stack.size;
			return;
		}
		
		Node node = first;
		while (node.next != null) {
			node = node.next;
		}
		node.next = stack.first;
		size += stack.size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}

	public static <Item> Stack<Item> copy(Stack<Item> stack) {
		Stack<Item> tempStack = new Stack<>();
		Stack<Item> stackCopy = new Stack<>();
		for(Item item : stack) {
			tempStack.push(item);
		}
		for(Item item : tempStack) {
			stackCopy.push(item);
		}
		return stackCopy;
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
