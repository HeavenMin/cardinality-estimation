// Distinct.java
// Interface for distinct elements counter
// awirth for COMP90056
// Aug 2017

//AUTHOR : MIN
//PURPOSE : need to be implements
//VERSION : 1.0
//DATE : 8.2017

public interface Distinct{
	// this method returns numebr of trailing zeros
	public static int zeros(int v){
			return Integer.numberOfTrailingZeros(v);
	}
	
	void add(Object o);
	double distinct();
}