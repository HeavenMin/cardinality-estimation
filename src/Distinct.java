// Distinct.java
// Interface for distinct elements counter
// awirth for COMP90056
// Aug 2017

public interface Distinct{
	public static int zeros(int v){
			return Integer.numberOfTrailingZeros(v);
	}
	
	void add(Object o);
	double distinct();
}