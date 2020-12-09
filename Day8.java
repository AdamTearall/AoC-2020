import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day8{
	static int index = 0;
	static int acc = 0;
	static int[] beenbefore = new int[0];
	public static void main(String args[]){
		long start = System.currentTimeMillis();
		String text="";
		String[] operators = new String[0];
		int[] operands = new int[0];
		try{
			text = new String(Files.readAllBytes(Paths.get("Day8.txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] lines = text.split("\n");
		for(String line:lines){
			String[] splits = line.split(" ");
			operators = Arrays.copyOf(operators,operators.length+1);
			operators[operators.length-1] = splits[0];
			operands = Arrays.copyOf(operands,operands.length+1);
			operands[operands.length-1] = Integer.parseInt(splits[1]);
		}
		partA(operators,operands);
		System.out.println("Part A:" + acc);
		partB(operators,operands);
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end-start) + "ms");

	}

	public static void partA(String[] operators,int[] operands){
		acc=0;index=0; beenbefore = new int[0];
		Boolean found = false;
		while((found == false)){
			beenbefore = Arrays.copyOf(beenbefore,beenbefore.length+1);
			beenbefore[beenbefore.length-1] = index;
			nextInstruction(operators[index],operands[index]);
			for(int b:beenbefore){
				if(b==index){
					found=true;
				}
			}
		}

	}
	//we need to use arrays.copyOf so that the arrays do not stay changed permanently
	public static void partB(String[] operators, int[]operands){
		String[] original = Arrays.copyOf(operators,operators.length);
		String[] attempts = new String[0];
		int i;
		for(i=0;i<operators.length;i++){
			operators = Arrays.copyOf(original,original.length);
			if(operators[i].contains("nop")){
				attempts = operators;
				attempts[i] = "jmp";
				try{
					partA(attempts,operands);
				}catch(ArrayIndexOutOfBoundsException e){
					
						System.out.println("Part B: " + acc);
					
				}				
			}else if(operators[i].contains("jmp")){
				attempts = operators;
				attempts[i] = "nop";
				try{
					partA(attempts,operands);
				}catch(ArrayIndexOutOfBoundsException e){
					
						System.out.println("Part B: " + acc);
					
				}
				
			}
		}
		operators = Arrays.copyOf(original,original.length);
	}
	public static void nextInstruction(String operator, int operand){
		switch(operator){
			case "jmp": index += operand; break;
			case "acc": acc += operand; index++; break;
			case "nop": index++; break;
		}
	}


}