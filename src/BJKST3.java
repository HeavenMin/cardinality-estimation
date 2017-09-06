import java.util.HashSet;
import java.util.Set;

//AUTHOR : MIN
//PURPOSE : AMS algorithm
//VERSION : 1
//DATE : 8.2017

public class BJKST3 implements Distinct {
	private Hash h;
	private Hash g;
	private int z;
	private int thresh;
	private int dom;
	private int b=3;
	private int c=576; // magic number from the paper
	private int ran;
	private Set<Pair<Integer,Integer>> B;
	
	public BJKST3(int n, int eps1) {
		z = 0;
		B = new HashSet<Pair<Integer,Integer>>();
		dom = n;
		ran = (int) Math.ceil(b* Math.pow(Math.log(n) /
					Math.log(2)+1 * c * eps1 * eps1, 2));
		//System.out.format("ran %d%n", ran);
		h = new Hash();
		g = new Hash();
		thresh = 576 * eps1 * eps1; 
	}
	public void add(Object object) {
		int val = Hash.h_basic(object, dom);
		int gval = g.h2u(val, ran);
		int hval = h.h2u(val, dom);
		int vz = Distinct.zeros(hval);
	
		if (vz >= z) {
			Pair<Integer,Integer> p =
					new Pair<Integer,Integer>(gval,vz);
			B.add(p);
			while (B.size() >= thresh) {
				z++;
				Set<Pair<Integer,Integer>> l = new HashSet<Pair<Integer,Integer>>();
				for (Pair<Integer,Integer> q:B) {
					if (q.getRight() < z) {
						l.add(q);	
					}
				}
				B.removeAll(l);
			}
		}
		
	}
	public double distinct() {
		return B.size() * Math.pow(2, z);
	}
	
}