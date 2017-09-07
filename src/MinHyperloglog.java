// AUTHOR : MIN
// PURPOSE : advanced HyperLogLog algorithm
// VERSION : 2.0
// DATE : 9.2017

// Advanced Hyperloglog algorithm
public class MinHyperloglog implements Distinct {
	private final int DOMAIN = 32; //32bits hash funtion
	private double alpha;	//value of alpha depends on p
	private int p; //M = 2^p
	private int M;	// size of the register, M = 2^p
	private long registers[];	//same like the bucket
	Runtime runtime = Runtime.getRuntime();	//for the space testing
	private long memoryOccupy_new;	
	private long memoryOccupy_after;
	
	public MinHyperloglog(int p) {
		assert(p >= 4);	// p in the range [4..16], too small p will influence the accuracy
		this.p = p;
		this.M = 1 << p;	// M = 2^p for int
		
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
		this.registers = new long[M];	//init M registers
		runtime.gc();
		this.memoryOccupy_new = runtime.totalMemory() - runtime.freeMemory();
		this.memoryOccupy_after = 0;
		
	}

	public void add(Object object) {
		int x = MurmurHash2.hash(object);	//get murmurhash2 hash value
		int idx = x >>> (DOMAIN - p);	//get first p bits of x, and it's a 32bits hash funciton
		//number of the leading zeros in the binary representation of x plus 1
		//plue 1 is used to convenient calculation empty registers
		int w = leadingZeros((1 << (p - 1)) + 1 | (x << p)) + 1;
		//store the maximum number of leading zeros plus one for substream with index idx
		registers[idx] = Math.max(registers[idx], w);

	}
	
	//indicator used to calculate the normalized version of the harmonic mean
	private double harmonicMean(long[] registers) {
		runtime.gc();
		memoryOccupy_after = runtime.totalMemory() - runtime.freeMemory();
		double sum = 0.0;	
		for (int i = 0; i < M; i++) {
			sum += Math.pow(2, -registers[i]);
		}
		return M / sum;
	}

	// output the distinct number
	public double distinct() {
		double e = alpha * M * harmonicMean(registers);
		
		// to correct small cardinalities
		if (e < 2.5 * M) {
			int nbOf0 = numRegistersEqZero();
			if (nbOf0 != 0) {
				// linear counting when cardinality is very small
				e = M * Math.log((double) M / (double) nbOf0);
			}
		// to correct large cardinalities
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
	
	private int leadingZeros(int v) {
		return Integer.numberOfLeadingZeros(v);
	}
	
	public long getMemoryOccpuy_new() {
		return memoryOccupy_new;
	}
	
	public long getMemoryOccpuy_after() {
		return memoryOccupy_after;
	}

}
