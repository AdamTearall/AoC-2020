import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day11{
	public static void main(String args[]){
		long start = System.currentTimeMillis();
		String text = "";
		try{
			text = new String(Files.readAllBytes(Paths.get("Day11.txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] lines = text.split("\\n");
		int x = lines.length;
		int y = lines[0].length();
		char[][] seats = new char[x][y];
		int i;
		int j;
		for(i=0;i<lines.length;i++){
			for(j=0;j<lines[i].length();j++){
				seats[i][j] = lines[i].charAt(j);
			}
		}
		int answera = partA(seats,x,y);
		for(i=0;i<lines.length;i++){
			for(j=0;j<lines[i].length();j++){
				seats[i][j] = lines[i].charAt(j);
			}
		}
		int answerb = partB(seats,x,y);
		long end = System.currentTimeMillis();
		System.out.println("Part A: " + answera);
		System.out.println("Part B: " + answerb);
		System.out.println("Time: " + (end-start) + "ms");
	}
	public static int partA(char[][] seats,int x, int y){
		Boolean swap = false;
		do{
			swap = false;
			//prevents the changes affecting changes in the same pass
			char[][] copy = new char[x][y];
			int i;
			int j;
			for(i=0;i<x;i++){
				for(j=0;j<y;j++){
					copy[i][j] = seats[i][j];
				}
			}
			for(i=0;i<x;i++){
				for(j=0;j<y;j++){
					int temp = checkAround(seats,x,y,i,j);
					if(seats[i][j] == 'L' && temp == 0){
						copy[i][j] = '#';
						swap = true;
					}
					if(seats[i][j] == '#' && temp > 3){ 
						copy[i][j] = 'L';
						swap = true;
					}
				}
			}
			for(i=0;i<x;i++){
				for(j=0;j<y;j++){
					seats[i][j] = copy[i][j];
				}
			}
		}while(swap == true);
		int i;
		int j;
		int total = 0;
		for(i=0;i<x;i++){
			for(j=0;j<y;j++){
				if (seats[i][j] == '#'){
					total++;
					
				}
				
			}
			
		}
		return total;
	}
	public static int checkAround(char[][] seats, int x, int y, int i, int j){
		int total = 0;
		if((j>0) && (i>0) && (seats[i-1][j-1] == '#')) total++;
		if((i>0) && (seats[i-1][j] == '#')) total++;
		if((j+1<y) && (i>0) && (seats[i-1][j+1] == '#')) total++;
		if((j>0) && (seats[i][j-1] == '#')) total++;
		if((j+1<y) && (seats[i][j+1] == '#')) total++;
		if((i+1<x) && (j>0) && (seats[i+1][j-1] == '#')) total++;
		if((i+1<x) && (seats[i+1][j] == '#')) total++;
		if((i+1<x) && (j+1<y) && (seats[i+1][j+1] == '#')) total++;
		return total;
	}
	public static int partB(char[][]seats, int x, int y){
		Boolean swap = false;
		do{
			swap = false;
			char[][] copy = new char[x][y];
			int i;
			int j;
			for(i=0;i<x;i++){
				for(j=0;j<y;j++){
					copy[i][j] = seats[i][j];
				}
			}
			for(i=0;i<x;i++){
				for(j=0;j<y;j++){
					int temp = checkEnhanced(seats,x,y,i,j);
					if(seats[i][j] == 'L' && temp == 0){
						copy[i][j] = '#';
						swap = true;
					}
					if(seats[i][j] == '#' && temp > 4){ 
						copy[i][j] = 'L';
						swap = true;
					}
				}
			}
			for(i=0;i<x;i++){
				for(j=0;j<y;j++){
					seats[i][j] = copy[i][j];
				}
			}
		}while(swap==true);
		int i;
		int j;
		int total = 0;
		for(i=0;i<x;i++){
			for(j=0;j<y;j++){
				if (seats[i][j] == '#'){
					total++;
					
				}
				
			}
			
		}
		return total;
		
	}
	public static int checkEnhanced(char[][] seats, int x, int y, int i, int j){
		int total = 0;
		if (doCheck(seats,x,y,i,j,-1,-1)==true) total++;
		if (doCheck(seats,x,y,i,j,-1,0)==true) total++;
		if (doCheck(seats,x,y,i,j,-1,1)==true) total++;
		if (doCheck(seats,x,y,i,j,0,-1)==true) total++;
		if (doCheck(seats,x,y,i,j,0,1)==true) total++;
		if (doCheck(seats,x,y,i,j,1,-1)==true) total++;
		if (doCheck(seats,x,y,i,j,1,0)==true) total++;
		if (doCheck(seats,x,y,i,j,1,1)==true) total++;
		//System.out.println(total);
		return total;
	} 
	public static Boolean doCheck(char[][] seats, int x, int y, int i, int j, int ichange, int jchange){
		int tempi = ichange;
		int tempj = jchange;
		Boolean found = false;
		Boolean occupied = false;
		while(found==false){
			//we have reached the edge
			if((i+tempi==-1)|| (i+tempi==x) || (j+tempj==-1) || (j+tempj==y)){
				found=true;
				occupied=false;
			}else if(seats[i+tempi][j+tempj] == '.'){
				//System.out.println("dot");
				tempi+=ichange;
				tempj+=jchange;
			}else if(seats[i+tempi][j+tempj] == 'L'){
				//System.out.println("L");
				found=true;
				occupied=false;
			}else if(seats[i+tempi][j+tempj] == '#'){
				//System.out.println("Hashtag");
				found=true;
				occupied=true;
			}
		}
		//System.out.println(occupied);
		return occupied;
	}

}