import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
public class Day2{
	public static void main(String args[]){
		int[] lower = new int[0];
		int[] higher = new int[0];
		char[] character = new char[0];
		String[] passwords = new String[0];
		try{
			FileInputStream fis = new FileInputStream("Passwords.txt");
			Scanner sc = new Scanner(fis);
			while (sc.hasNextLine()){
				String line = sc.nextLine();
				String[] password = line.split(":");
				passwords = Arrays.copyOf(passwords,passwords.length+1);
				passwords[passwords.length-1] = password[1];
				String[] lowers = password[0].split("-");
				lower = Arrays.copyOf(lower,lower.length+1);
				lower[lower.length-1] = Integer.parseInt(lowers[0]);
				String[] highchar = lowers[1].split(" ");
				higher = Arrays.copyOf(higher,higher.length+1);
				higher[higher.length-1] = Integer.parseInt(highchar[0]);
				character = Arrays.copyOf(character,character.length+1);
				character[character.length-1] = highchar[1].charAt(0);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		int total = 0;
		int i;
		for(i=0;i<higher.length;i++){
			if(checkValid2(lower[i],higher[i],character[i],passwords[i])) total++;
		}
		System.out.println(total);
	}
	private static Boolean checkValid2(int first, int second, char character, String password){
		if ((password.charAt(first)==character && password.charAt(second)!=character)|| (password.charAt(second)==character && password.charAt(first)!=character)) return true;
		else return false;
	}




	private static Boolean checkValid(int lower, int higher, char character, String password){
		int total=0;
		int i;
		for(i=0;i<password.length();i++){
			if(password.charAt(i) == character) total++;
		}
		if (total >= lower && total <= higher) return true;
		else return false;
	}
}