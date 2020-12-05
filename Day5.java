import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Day5{
	public static void main(String args[]){
		//gets the inputs and puts them in an array
		String inputs[] = new String[0];
		try{
			FileInputStream fis = new FileInputStream("Day5.txt");
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()){
				inputs = Arrays.copyOf(inputs, inputs.length+1);
				inputs[inputs.length-1] = sc.nextLine();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		partAB(inputs);
	}

	


	public static void partAB(String[] inputs){
		int[] ids = new int[0];
		for(String line : inputs){
			int i;
			int high=127;
			int low=0;
			//does the seat row
			//NEED 2.0 to force non-integer division
			for(i=0;i<7;i++){
				if(line.charAt(i)== 'F'){
					high = (int) Math.floor((high+low)/2.0);
				}else{
					low =  low + (int) Math.ceil((high-low)/2.0);
				}
			}
			int seat = low;
			high = 7;
			low = 0;
			//does the seat column
			for(i=7;i<10;i++){
				if(line.charAt(i)=='L'){
					high = (int) Math.floor((high+low)/2.0);
				}else{
					low =  low + (int) Math.ceil((high-low)/2.0);
				}
			}
			//instead saves the IDs to a list
			int id = (seat*8)+low;
			ids = Arrays.copyOf(ids,ids.length+1);
			ids[ids.length-1] = id;
		}
		//puts the ids in number order
		Arrays.sort(ids);
		int i;
		int myid=0;
		//as specified by the task, there is only one missing
		//this finds the adjacent pair of values that are two apart
		for(i=0;i<ids.length-1;i++){
			if(ids[i]==(ids[i+1]-2)){
				myid = ids[i]+1;
			}
		}
		System.out.println("Part 1: " + ids[ids.length-1]);
		System.out.println("Part 2: " + myid);
	}
}