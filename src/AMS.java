import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.StackConsumer;

// AUTHOR : MIN
// PURPOSE : AMS algorithm
// VERSION : 1
// DATE : 8.2017

public class AMS implements Distinct {
	private int zList[];
	private int n;	//domain and range
	private Hash hashList[];
	private int RANGE = 536870912-3;
	private int c = 18;	// c = 1 / epsilon^2
//	private int c = 1835;
	private int k;
	
	// del1 decide the success rate
	public AMS(int n, int del1) {
		k = (int) Math.ceil(c*Math.log(del1));
		this.n = n;
		
		zList = new int[k];
		hashList = new Hash[k];
		for (int i = 0; i < k; i++) {
			hashList[i] = new Hash();
		}
	}
	
	public void add(Object object) {
		for (int i = 0; i < k; i++) {
			int value = Hash.h_basic(object, n);
			value = hashList[i].h2u(value, RANGE);
			int bestZ = Distinct.zeros(value);
			if (bestZ > zList[i]) {
				zList[i] = bestZ;
			}
		}
	}
	
	private double getMean(int[] data) {
		double sum = 0;
		for (int i = 0; i < k; i++) {
			sum += data[i];
		}
		return sum / k;
	}
	
	public static double getMedian(int[] data) {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        return (copy.length % 2 != 0) ? copy[copy.length / 2] : (
        		copy[copy.length / 2] + copy[(copy.length / 2) - 1]) / 2;
    }
	
	public double distinct(){
		double z = getMedian(zList);
//		double z = getMean(zList);
		return Math.pow(2, z+0.5);
	}
	

}

