package com.xibo.helloworld;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Test implements Serializable {

	public void test() {
		System.err.println("1");
	}

	private void readObject(ObjectInputStream s) {
		throw new UnsupportedOperationException();
	}

}