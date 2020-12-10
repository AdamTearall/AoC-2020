import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day10{
	public static void main(String args[]){
		long test = Long.parseLong(193434623158032);
		float tester = test/7;
		System.out.println(tester);
		long start = System.currentTimeMillis();
		String text = "";
		try{
			text = new String(Files.readAllBytes(Paths.get("Day10.txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] lines = text.split("\\n");
		int[] joltages = new int[lines.length+1];
		int i;
		for(i=0;i<lines.length;i++){
			joltages[i+1] = Integer.parseInt(lines[i]);
		}
		joltages[0]=0;
		int[] temp =  {1,4,5,6,7,10,11,12,15,16,19};
		Arrays.sort(joltages);

		System.out.println("Part A: " + partA(joltages));
		System.out.println("Part B: " + partB(joltages));
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "ms");
	}
	public static int partA(int[] joltages){		
		int onej = 0;
		int threej = 0;
		int i;
		for(i=0;i<joltages.length-1;i++){
			if(joltages[i+1]-joltages[i] == 1){
				onej++;
			}else if(joltages[i+1]-joltages[i] == 3){
				threej++;
			}
		}
		threej++;
		return (onej*threej);
	}
	public static long partB(int[] joltages){
		int counter = 0;
		int[] ranges = new int[0];
		int i;
		for(i=0;i<joltages.length-2;i++){
			counter++;
			if((joltages[i+1] == joltages[i]+3) && (counter != 0)){
				if(counter > 2){
					ranges = Arrays.copyOf(ranges,ranges.length+1);
					ranges[ranges.length-1] = counter;
				}
				counter = 0;
			}
		}
		
		ranges = Arrays.copyOf(ranges,ranges.length+1);
		ranges[ranges.length-1] = 3;
		
		long total = 1;
		for(int range:ranges){
			switch(range){
				case 3: total*=2;break;
				case 4: total*=4;break;
				case 5: total*=7;break;
			}
		}
		return total;
	}
}