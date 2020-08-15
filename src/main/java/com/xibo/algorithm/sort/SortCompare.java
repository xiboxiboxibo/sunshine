package com.xibo.algorithm.sort;

import java.util.Random;

/**
 * 排序对比
 * @author xihao.pan
 */
public class SortCompare {

	private SortCompare() {}

	public static double time(Arithmetic alg, Double[] a) {
		long begin = System.currentTimeMillis();
		alg.execute(a);
		return (double) (System.currentTimeMillis() - begin) / 1000;
	}

	public static double timeRandomInput(Arithmetic alg, int N, int T) {
		double total = 0.0;
		Double[] a = new Double[N];
		Random random = new Random();
		
		for (int t = 0; t < T; t++) {
			for (int i = 0; i < N; i++) {
				a[i] = random.nextDouble();
			}
			total += time(alg, a);
		}
		return total;
	}

	/**
	 * 排序算法枚举
	 */
	public enum Arithmetic {
		SELECTION {
			@Override
			void execute(Double[] a) {
				SelectionSort.sort(a);
			}
		},
		INSERTION {
			@Override
			void execute(Double[] a) {
				InsertionSort.sort(a);
			}
		},
		SHELL {
			@Override
			void execute(Double[] a) {
				ShellSort.sort(a);
			}
		};

		abstract void execute(Double[] a);
	}
	
}