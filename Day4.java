import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4{
	public static void main(String args[]){
		String[] passports = new String[0];
		String temp = "";
		String line = "";
		
		try{
			FileInputStream fis = new FileInputStream("Passports.txt");
			Scanner sc = new Scanner(fis);
			while(sc.hasNextLine()){
				line = sc.nextLine();
				if(line == ""){
					passports = Arrays.copyOf(passports,passports.length+1);
					passports[passports.length-1] = temp;
					temp = "";
				}else{
					temp = temp + " " + line;
				}

			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		passports = Arrays.copyOf(passports,passports.length+1);
		passports[passports.length-1] = temp;
		System.out.println(checkValid2(passports));

	}
	public static int checkValid(String[] passports){
		int i;
		int total = 0;
		for(i=0;i<passports.length;i++){
			int count = passports[i].length() - passports[i].replace(" ","").length();
			if(count == 8){
				total++;
			}else if((count == 7) && (passports[i].contains("cid")==false)){
				total++;
			}
		}
		return total;
	}
	
	public static int checkValid2(String[] passports){
		int i;
		int total = 0;
		for(i=0;i<passports.length;i++){
			String[] fields = passports[i].split(" ");
			Arrays.sort(fields);
			int j;
			String[] temp = new String[0];
			for(j=0;j<fields.length;j++){
				if(fields[j].contains("cid")==false){
					temp = Arrays.copyOf(temp,temp.length+1);
					temp[temp.length-1] = fields[j];
				}
			}
			fields = temp;
			passports[i] = String.join(" ",fields);
			Pattern p = Pattern.compile("byr:((19[2-9][0-9])|(2000)|(2001)|(2002)) ecl:(amb|blu|brn|gry|grn|hzl|oth) eyr:((202[0-9])|2030) hcl:#([a-f0-9]{6}) hgt:(((1[5-8][0-9]|19[0-3])cm)|(((59)|(6[0-9])|7[0-6])in)) iyr:((201[0-9])|2020) pid:[0-9]{9}$",Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(passports[i]);
			if(m.find()) {
				System.out.println(passports[i]);
			total++;
			}
		}
		return total;
	}

	
}