import java.util.function.Predicate;



// AUTHOR : MIN
// PURPOSE : AMS algorithm
// VERSION : 1
// DATE : 9.2017

public class Hash {
	private int p = 1073741789;	//a big prime numebr
	private int a,b;
	
	public Hash() {
		//randomly get a and b
		a=StdRandom.uniform(p-1)+1;
		b=StdRandom.uniform(p+1);
	}
	
	public int h2u(int x,int range){
		long prod = (long)a*(long)x + (long)b;
		long y = prod % (long) p;
		int r = (int)y % range;
		return r;	//the remainder
	}
	
	public static int h_basic(Object key,int domain){
		// domain should be something like 0x0fffffff
        return (key.hashCode() & domain);
	}

}
