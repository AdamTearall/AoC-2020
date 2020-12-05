import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.math.*;

public class Day3{
	public static void main(String args[]){
		String[] lines =  new String[0];
		try{
			FileInputStream fis = new FileInputStream("Trees.txt");
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()){
				lines = Arrays.copyOf(lines,lines.length+1);
				lines[lines.length-1] = sc.nextLine();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		//biiiiiiiiiiig number
		long total = variableSlope(lines,3,1);
		total *= variableSlope(lines,1,1);
		total *= variableSlope(lines,7,1);
		total *= variableSlope(lines,5,1);
		total *= variableSlope(lines,1,2);
		System.out.println(total);
	}

	public static int variableSlope(String[] lines, int right, int down){
		int x = 0;
		int y = 0;
		int total = 0;
		char hashtag = new Character('#');
		while(x<lines.length){
			if(y>=lines[0].length()) y-=lines[0].length();
			if(lines[x].charAt(y) == hashtag) total++; 
			y+=right;
			x+=down;
		}
		return total;
	}
}