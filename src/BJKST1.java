import java.util.PriorityQueue;

//AUTHOR : MIN
//PURPOSE : AMS algorithm
//VERSION : 1
//DATE : 8.2017

public class BJKST1 implements Distinct {
	
	private int t;
	private int dom;
	private int ran;
	private Hash h;
	private PriorityQueue<Integer> pq;
	
	public BJKST1(int n, int eps1) {
		this.dom = n;
		this.ran = 536870912-3;
		//this.ran = n*n*n; // ideally init with n*n*n
		h = new Hash();
		
		t = 96*eps1*eps1;	//96 is a magic number from BJKST paper
		pq = new PriorityQueue<Integer>(t);
	}
	
	public void add(Object object) {
		int value = Hash.h_basic(object, dom);
		value = h.h2u(value, ran);
		
		if (pq.size() < t) {
			pq.add(-value);
		} else {
			int m = pq.peek();
			if (value < -m && !pq.contains(-value)) {
				pq.poll();
				pq.add(-value);
			}
		}
	}
	public double distinct() {
		int m = pq.peek();
		return (double)t * (double)ran / (double)(-m);
	}
	
}