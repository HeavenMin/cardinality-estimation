
// AUTHOR : MIN
// PURPOSE : AMS algorithm
// VERSION : 2.0
// DATE : 9.2017

// Hyperloglog++ algorithm
public class Hyperloglog implements Distinct {
	private double alpha;	//value of alpha depends on M
	private int p; 
	private int M;	// size of the sketch, M = 2^p
	private long registers[];	//same like the bucket
	
	public Hyperloglog(int p) {
		assert(p >= 4);	// p in the range [4..16]
		this.p = p;
		this.M = 1 << p;	// M = 2^p

//		this.alpha = 0.77351;	//the normal hyperloglog will use this value

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
		int x = MurmurHash.hash(object);	//get murmurhash2 hash value
		int idx = x >>> (Integer.SIZE - p);	//get first p bits of x
		//number of the leading zeros in the binary representation of x plus 1
		int w = Integer.numberOfLeadingZeros((x << p) | (1 << (p - 1)) + 1) + 1;
		//store the maximum number of leading zeros plus one for substream with
		//index idx
		registers[idx] = Math.max(registers[idx], w);

	}
	
	//Harmonic mean
	private double harmonicMean(long[] registers) {
		double sum = 0.0;
		for (int i = 0; i < M; i++) {
			sum += Math.pow(2, -registers[i]);
		}
		return M / sum;
	}

	// output the distinct number
	public double distinct() {
		double e = alpha * M * harmonicMean(registers);
		
		// to amend small cardinalities
		if (e < 2.5 * M) {
			int v = numRegistersEqZero();
			if (v != 0) {
				e = M * Math.log((double) M / (double)v);
			}
		// to amend large cardinalities
		} else if (e > 1 / 30 * Math.pow(2, 32)) {
			e = -Math.pow(2, 32) * Math.log(1 - e / Math.pow(2, 32));
		}
		return e;
	}
	
	//count the number of registers equal to 0
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
