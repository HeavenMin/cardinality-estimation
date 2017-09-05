import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// TestDistinct.java
// Example "main class" for distinct elements counter
// awirth for COMP90056
// Sep 2017

public class TestDistinct{
	
	public static void main(String args[]){
		int i;
		
		int d = 0;
		
		//Distinct a = new AMS(0x0fffffff,10);
		//Distinct b = new BJKST1(0x0001ffff,1);
//		Distinct c = new BJKST3(0x0fffffff,1);
		
		if(args.length == 0){
			System.err.println("No fileName argument.");
			System.exit(1);
		}
		String fileName = args[0];
		
		Scanner scanner;
		try{
			File f = new File(fileName);
			scanner = new Scanner(f);
			String s;
			while(scanner.hasNextLine()){
				s = scanner.nextLine();
//				c.add(s);
			}
			scanner.close();
//			System.out.format("%12f%n",c.distinct());
		} catch (FileNotFoundException ex) {
			System.err.println("No file: "+fileName);
		}

	}
}
