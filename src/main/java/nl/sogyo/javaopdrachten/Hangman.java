import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hangman{
	public static void main(String []args){
		String filename = "words.txt";
		String word = getWord(filename);
		String[] wordArray = word.split("");
		int wordLength = word.length();
		String missedLetters = "";
		int guessesLeft = 10;
		String[] foundLetters = new String[wordLength];
		for(int i = 0; i<wordLength; i++) foundLetters[i]="_";
		boolean running = true;
		printWord(foundLetters);
		boolean correctGuess =false;
		String guess = "";
		Scanner input = new Scanner(System.in);
		
		while(running){
			guess = input.nextLine().toLowerCase();
			for(int i = 0; i<wordLength; i++){
				if(wordArray[i].equals(guess)){
					foundLetters[i]=guess;
					correctGuess=true;
				}
			}
			printWord(foundLetters);
			if(!correctGuess){
				guessesLeft=guessesLeft-1;
				missedLetters = missedLetters+" "+guess;
				System.out.println(guessesLeft+" guesses left. Missed letters:"+missedLetters);
			}
			correctGuess=false;
			if(guessesLeft<=0){
				System.out.println("You ran out of guesses, the word was: "+word+"\ngame over.");
				running = false;
			}
			if(checkVictory(foundLetters,wordArray)){
				System.out.println("you have won!");
				running = false;
			}			
		}
		input.close();
		
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
		return word.toLowerCase();
	}
	
	public static void printWord(String[] foundLetters){
		for(String i : foundLetters){
			System.out.print(i.toUpperCase()+" ");
		}
		System.out.print("\n");
	}
	
	public static boolean checkVictory(String[] foundLetters, String[] wordArray){
		boolean victory = true;
		for(int i = 0; i<wordArray.length; i++){
			if(!wordArray[i].equals(foundLetters[i])){
				victory = false;
				break;
			}
		}
		return victory;
	}
}
		