import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class MasterMind{
	static ArrayList<String> code = new ArrayList<String>();
	static ArrayList<String> codeCopy = new ArrayList<String>();
	static ArrayList<String> guess = new ArrayList<String>();
	static ArrayList<String> result = new ArrayList<String>();
	static HashMap<String,String> colorMap = new HashMap<String,String>();
	static boolean correct = false;
	static boolean running = true;
	static int turns = 0;
	
	
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
	
		public static final String ANSI_RESET = "\u001B[0m";
	public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String WHITE = "\033[0;37m";   // WHITE
	public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
	
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		generateCode(); fillColorMap();
		
		while(running&&turns<10){ 
			System.out.println("\ntype in 4 colors seperated by spaces,"
				+" choose from Red, Blue, Yellow, Cyan, Purple, and Green");
			System.out.println("You have "+(10-turns)+" guesses left");
			guess.clear();
			result.clear();
			codeCopy.clear();
			for(int i =0; i<4;i++){
				guess.add(input.next().toUpperCase());
			}
			code.forEach(color ->{codeCopy.add(color);});
			printPins();
			checkGuess();
			printResults();

			if(correct&&result.size()==4) running = false;
			turns++;
		}
		if(turns<10)System.out.println("You have won");
		else System.out.println("You have run out of guesses.\nYou lost.");
	
		input.close();
	}
	
	public static void generateCode(){
		String[] colorArray = {"RED","BLUE","YELLOW","CYAN","PURPLE","GREEN"};
		for(int i =0; i<4;i++){
			code.add(colorArray[((int)(Math.random()*(6)))]);
		}
	}
	
	public static void fillColorMap(){
		colorMap.put("RED",RED);
		colorMap.put("BLUE",BLUE);
		colorMap.put("YELLOW",YELLOW);
		colorMap.put("CYAN",CYAN);
		colorMap.put("PURPLE",PURPLE);
		colorMap.put("GREEN",GREEN);
	}
	
	public static void printPins(){
		System.out.print("\n"); 
		guess.forEach(color ->{
			String c = colorMap.get(color);
			System.out.printf("%sO ",c);
		});
		System.out.print("  ");
	}
	
	public static void printResults(){
		
		result.forEach(color ->{
			if(color.equals("BLACK")){
				System.out.printf("%s%sO%s ",BLACK,WHITE_BACKGROUND,ANSI_RESET);	
			}
			else System.out.printf("%sO ",WHITE);
		});
		System.out.print(ANSI_RESET+"\n");
	}
	
	public static void checkGuess(){
		int length = 4;
		int i =0;
		while(i<length){
			if(codeCopy.get(i).equals(guess.get(i))){
				result.add("BLACK");
				codeCopy.remove(i);
				guess.remove(i);
				length--;
				correct = true;
			}
			else i++;
		}
		i=0; int j=0;
		while(i<length){
			if(codeCopy.get(i).equals(guess.get(j))){
				result.add("WHITE");
				codeCopy.remove(i);
				guess.remove(j);
				j=0;
				length--;
				correct = false;
			}
			else j++;
			if(j>=length){
				j=0;
				i++;
			}
		}
		
	}
	
	
}