import java.awt.print.Printable;

import com.sun.media.jai.codecimpl.util.Range;

// AUTHOR : MIN
// PURPOSE : AMS algorithm
// VERSION : 1
// DATE : 8.2017

public class Hyperloglog implements Distinct {
	private double alpha;
	private int p;
	private int M;	// size of the sketch
	private long registers[];
	// private int domain;
	// private Hash hashList[];
	// private int range = 536870912-3;

	public Hyperloglog(int p) {
		// this.domain = domain;
		assert(p >= 4);
		this.p = p;
		this.M = 1 << p;	// m = 2^p

//		this.alpha = 0.77351;

		if(p == 4) {
			this.alpha = 0.673;
		} else if (p == 5) {
			this.alpha = 0.697;
		} else if (p == 6) {
			this.alpha = 0.709;
		} else {
			this.alpha = 0.7213 / (1 + 1.079 / M);
		}

		this.registers = new long[M];

	}

	public void add(Object object) {
		int x = MurmurHash.hash(object);
//		int x = object.hashCode();
		int idx = x >>> (Integer.SIZE - p);
		int w = Integer.numberOfLeadingZeros((x << p) | (1 << (p - 1)) + 1) + 1;
		registers[idx] = Math.max(registers[idx], w);

	}

	private double countZ(long[] registers) {
		double sum = 0.0;
		for (int i = 0; i < M; i++) {
			sum+= Math.pow(2, -registers[i]);
		}
		return 1 / sum;
	}


	public double distinct() {
		double e = alpha * M * M * countZ(registers);

		if (e < 2.5 * M) {
			int v = numRegistersEqZero();
			if (v != 0) {
				e = M * Math.log((double) M / (double)v);
			}
		} else if (e > 1 / 30 * Math.pow(2, 32)) {
			e = -Math.pow(2, 32) * Math.log(1 - e / Math.pow(2, 32));
		}
		return e;
	}

	private int numRegistersEqZero() {
		int num = 0;
		for (int i = 0; i < M; i++) {
			if (registers[i] == 0) {
				num++;
			}
		}
		return num;
	}



}
