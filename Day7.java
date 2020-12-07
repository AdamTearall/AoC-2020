import java.util.Scanner;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day7{
	static Boolean found = false;
	public static void main(String args[]){
		//create a dictionary of string:list of string
		Map<String,String[]> dict = new HashMap<String,String[]>();
		String text="";
		try{
			text = new String(Files.readAllBytes(Paths.get("Day7.txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
		//bag and its contents
		text = text.replaceAll("\\bbags\\b","bag");
		String[] lines = text.split("\n");	
		int i;
		for(i=0;i<lines.length;i++){
			//key/value pair for bag type and what it contains
			String[] line = lines[i].split(" bag contain");
			String key = line[0];
			String[] value = line[1].split(",");
			int j;
			for(j=0;j<value.length;j++){
				if (value[j].charAt(value[j].length()-1) == '.'){
					value[j] = value[j].substring(3,value[j].length()-5);
				}else{
					value[j] = value[j].substring(3,value[j].length()-4);
				}
			}
			dict.put(key,value);			
		}
		partA(dict);
		dict = new HashMap<String,String[]>();
		for(i=0;i<lines.length;i++){
			//key/value pair for bag type and what it contains
			String[] line = lines[i].split(" bag contain");
			String key = line[0];
			String[] value = line[1].split(",");
			int j;
			for(j=0;j<value.length;j++){
				if (value[j].charAt(value[j].length()-1) == '.'){
					value[j] = value[j].substring(1,value[j].length()-5);
				}else{
					value[j] = value[j].substring(1,value[j].length()-4);
				}
			}
			dict.put(key,value);			
		}
		partB(dict);
	}


	//you have as much idea as I do
	//this solution is absolutely disgusting
	//for this, I deserve death
	public static void partA(Map<String,String[]> dictionary){
		int total = 0;
		for(String key:dictionary.keySet()){
			reCursed(dictionary,key);
			if(found==true){
				total++;
				found = false;
			}
		}
		System.out.println(total);
	}
	
	public static void reCursed(Map<String,String[]> dict, String key){
		String[] values = dict.get(key);
		
		//base case
		if(values[0].contains("other")){

		}else{
			for(String value:values){
			if(found == true) break;
			if(value.contains("shiny gold")){
				found = true;
			}else{
				reCursed(dict,value);
				
			}
		}

		}
		
	}
	public static void partB(Map<String,String[]> dictionary){
		System.out.println(reCursed2(dictionary,"shiny gold"));
	}
	public static int reCursed2(Map<String,String[]> dict, String key){
		String[] values = dict.get(key);
		int total = 0;
		//base case
		if(values[0].contains("no oth")){
			return 0;
		}else{
			for(String value:values){
				total += ((((int) value.charAt(0))-48)*reCursed2(dict,value.substring(2,value.length())))+(((int)  value.charAt(0))-48) ;
			}
			return total;
		}
	}
}