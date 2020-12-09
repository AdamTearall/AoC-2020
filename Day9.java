import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Day9{
	public static void main(String args[]){
		String text = "";
		try{
			text = new String(Files.readAllBytes(Paths.get("Day9.txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] lines = text.split("\\n");
		long[] numbers = new long[lines.length];
		int i;
		for(i=0;i<lines.length;i++){
			numbers[i] = Long.parseLong(lines[i]);
		}
		long invalid = partA(numbers);
		partB(numbers,invalid);
	}

	public static long partA(long[] numbers){
		int i;
		for(i=25;i<numbers.length;i++){
			if(checkValid(numbers,i)==false){
				System.out.println(numbers[i]);
				return numbers[i];
			}
		}
		return 0;
	}

	public static int partB(long[] numbers, long sum){
		int i;
		for(i=0;i<numbers.length;i++){
			int temp = checkSum(numbers,i,sum);
			if(temp != -1){
				//temp is the highest index, i is the lowest index
				long[] correctnums = new long[temp-i];
				System.arraycopy(numbers,i,correctnums,0,temp-i);
				Arrays.sort(correctnums);
				System.out.println(correctnums[0]+correctnums[correctnums.length-1]);
				return 0;
			}
		}
		return 0;
	}

	public static int checkSum(long[] numbers, int index, long sum){
		long total = 0;
		int higher = index;
		while(total < sum){
			total+=numbers[higher];
			higher++;
		}
		if(total==sum) return higher-1;
		else return -1;
	}

	public static Boolean checkValid(long[] numbers,int index){
		int i;
		for(i=index-25;i<index;i++){
			int j;
			for(j=i;j<index;j++){
				if(((numbers[i]+numbers[j]) == numbers[index])&&(i!=j)){
					return true;
				}
			}
		}
		return false;
	}
}