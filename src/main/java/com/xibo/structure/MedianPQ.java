package com.xibo.structure;

/**
 * 中顶堆
 * @author xihao.pan
 */
public class MedianPQ {
	
	private MaxPQ<Double> maxPQ = new MaxPQ<Double>();
	private MinPQ<Double> minPQ = new MinPQ<Double>();
	
	public MedianPQ() {}
	
	public MedianPQ(Double[] a) {
		for (Double item: a) {
			insert(item);		
		}
	}
	
	public boolean isEmpty() {
		return maxPQ.isEmpty();
	}
	
	public int size() {
		return maxPQ.size() + minPQ.size();
	}
	
	public void insert(Double v) {
        if (maxPQ.isEmpty() || v.compareTo(this.maxPQ.max()) < 0) {
        	this.maxPQ.insert(v);
        }   
        else {
        	this.minPQ.insert(v);
        }
        updateMedian();
	}
	
	public Double delMedian() {
		Double v;
        if (maxPQ.size() > minPQ.size()) {
        	v = maxPQ.delMax();
        }
        else {
        	v = (maxPQ.delMax() + minPQ.delMin())/2;
        }
        updateMedian();
        return v;
	}
	
    private void updateMedian() {
        while (maxPQ.size() - minPQ.size() > 1) {
            minPQ.insert(maxPQ.delMax());
        }
        while (minPQ.size() > maxPQ.size()) {
            maxPQ.insert(minPQ.delMin());
        }
    }
	
}
