import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
public class Day6{
	public static void main(String args[]){
		String[] inputs = new String[0];
		int[] numbers = new int[0];
		String temp = "";
		String line = "";
		int number = 0;
		try{
			FileInputStream fis = new FileInputStream("Day6.txt");
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if(line==""){
					inputs = Arrays.copyOf(inputs,inputs.length+1);
					inputs[inputs.length-1]=temp;
					numbers = Arrays.copyOf(numbers,numbers.length+1);
					numbers[numbers.length-1] = number;
					temp = "";
					number = 0;
				}else{
					temp += line;
					number++;
				}
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		inputs = Arrays.copyOf(inputs,inputs.length+1);
		inputs[inputs.length-1]=temp;
		numbers = Arrays.copyOf(numbers,numbers.length+1);
		numbers[numbers.length-1] = number;
		partAB(inputs,numbers);
	}
	
	public static void partAB(String[] inputs,int[] numbers){
		int attotal = 0;
		int bttotal = 0;
		int j = 0;
		for(String line : inputs){
			int i;
			int atotal = 0;
			int btotal = 0;
			for(i=97;i<123;i++){
				int atemp = line.indexOf((char)i);
				int btemp = line.length() - line.replace(Character.toString((char)i),"").length();
				if (btemp == numbers[j]) btotal++;
				if(atemp != -1) atotal++;
			}
			attotal+=atotal;
			bttotal+=btotal;
			j++;
		}
		System.out.println("Part A: " + attotal);
		System.out.println("Part B: " + bttotal);
	}
}