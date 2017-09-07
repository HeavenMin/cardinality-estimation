import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.xml.stream.events.StartDocument;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

//TestDistinct.java
//Example "main class" for distinct elements counter
//awirth for COMP90056
//Sep 2017

//AUTHOR : MIN
//PURPOSE : "main class" for Cardinality estimation 
//VERSION : 1.0
//DATE : 9.2017

public class TestDistinct{
	
	public static void main(String args[]){
		
		String fileName = args[0];
		Scanner scanner;
//		double totalTime = 0.0;
//		Runtime runtime = Runtime.getRuntime();
		
//		Distinct ams = new AMS(0x0fffffff,10);
//		Distinct bjkst1 = new BJKST1(0x0fffffff,1);
//		Distinct bjkst3 = new BJKST3(0x0fffffff,1);
		
		// my advanced hyperLogLog algorithm
		Distinct minHyperLogLog = new MinHyperloglog(10);
		
		if(args.length == 0){
			System.err.println("No fileName argument.");
			System.exit(1);
		}

		try{
			File f = new File(fileName);
			scanner = new Scanner(f);
			String s;
			while(scanner.hasNextLine()){
				s = scanner.nextLine();
				
				// using to do the time testing
//				long start = System.currentTimeMillis(); 
				minHyperLogLog.add(s);
//				long now = System.currentTimeMillis();
//				double time = (now - start) /1000.0;
//				totalTime += time;
				
			}
			scanner.close();
//			System.out.format("%12f%n",ams.distinct());
			
			// using to do the time testing
//			long start = System.currentTimeMillis(); 
			
			System.out.format("%12f%n",minHyperLogLog.distinct());
//			System.out.println(((MinHyperloglog) minHyperLogLog).getMemoryOccpuy_new());
//			System.out.println(((MinHyperloglog) minHyperLogLog).getMemoryOccpuy_after());
			
//			long now = System.currentTimeMillis();
//			double time = (now - start) /1000.0;
//			totalTime += time;
//			System.out.format("%12f%n",totalTime);
			
		} catch (FileNotFoundException ex) {
			System.err.println("No file: "+fileName);
		}

	}
}
