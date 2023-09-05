import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Hangman{
	public static void main(String []args){
		String filename = "Wordlist.txt";
		String word = getWord(filename);
		int wordLength = word.length();
		System.out.println(word);
		System.out.println(wordLength);
		
		
		
	}
	public static String getWord(String filename){
		ArrayList<String> words = new ArrayList<String>();
		try{
			File wordFile = new File(filename);
			Scanner reader = new Scanner(wordFile);
			while (reader.hasNextLine()){
				words.add(reader.nextLine());
			}
			reader.close();
			
		}
		catch(FileNotFoundException e){
			System.out.println("An error has occured");
			e.printStackTrace();
		}
		int wordCount = words.size();
		int wordIndex = (int)(wordCount*Math.random());
		String word = words.get(wordIndex);
		return word;
	}
}
		