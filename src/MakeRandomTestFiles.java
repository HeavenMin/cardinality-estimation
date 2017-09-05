// MakeRandomTestFiles.java
// Example test file generator for distinct elements counter
// awirth for COMP90056
// Sep 2017

public class MakeRandomTestFiles{
	public static void main(String args[]){
		if(args.length != 1){
			System.err.println("Usage: MakeRandomTestFiles <number>");
			System.exit(1);
		}
		long N = Long.parseLong(args[0]);
		for(long i=0;i<N;i++){
			double d = StdRandom.uniform();
			d*=N;
			long x = Math.round(d);
			System.out.println(Long.toString(x));
		}
	}
}
