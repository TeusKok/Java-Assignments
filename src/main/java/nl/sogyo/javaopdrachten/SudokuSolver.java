import java.util.Scanner;
import java.util.ArrayList;
import java.time.ZonedDateTime;
public class SudokuSolver{
	public static int[][] board = new int[9][9];
	public static void main(String []args){
		String s = args[0];
		readSudoku(s);
		printBoard();
		if(checkRow(1,2))System.out.println("goed");
		if(checkRow(1,0))System.out.println("fout");
		if(checkColumn(1,2))System.out.println("goed");
		if(checkColumn(1,0))System.out.println("fout");
		if(checkBlock(6,3,2))System.out.println("goed");
		if(checkBlock(6,3,0))System.out.println("fout");
		long begin = ZonedDateTime.now().toInstant().toEpochMilli();
		if(solver()){
			printBoard();
			long end =ZonedDateTime.now().toInstant().toEpochMilli();

			System.out.println(end-begin);
		}
		else System.out.println("not succesfull");
	}
	
	public static void readSudoku(String s){
		//Scanner input = new Scanner(System.in);
		//int[] row = new int[9];
		//String s = input.nextLine();
		for(int i = 0; i<s.length();i++){
			board[(i)/9][i%9]=Character.getNumericValue(s.charAt(i));							
		}
		//input.close();
		
	}
	
	public static void printBoard(){
		for(int[] row:board){
			System.out.print("\n-------------------\n|");
			
			for (int number: row){
				System.out.print(number+"|");
			}
			
		}
	}
	
	public static boolean checkRow(int rowNumber,int number){
		int count=0;
		for (int n:board[rowNumber]){
			if(n==number)count++;
			if(count>1) return false;
		}
		return true;
	}
	
	public static boolean checkColumn(int columnNumber,int number){
		int count=0;
		for(int[] row: board){
			if(row[columnNumber]==number)count++;
			if(count>1) return false;
		}
		return true;
	}
	
	public static boolean checkBlock(int rowNumber,int columnNumber, int number){
		int count = 0;
		int rowMax =(((rowNumber)/3)+1)*3; 
		int colMax =(((columnNumber)/3)+1)*3; 
		for(int i = rowMax-3;i<rowMax ;i++){
			for(int j = colMax-3;j<colMax ;j++){
				if(board[i][j]==number)count++;
				if(count>1) return false;
			}
		}
		return true;
	}
	
	public static boolean isValid(int rowN,int colN, int num){
		if(checkRow(rowN,num)&&checkColumn(colN,num)&&checkBlock(rowN,colN,num)) {
			return true;
		}
		else return false;
	}
	
	public static boolean solver(){
		for(int i = 0;i<9 ;i++){
			for(int j = 0;j<9 ;j++){
				if(board[i][j]==0){
					for(int k = 1; k<=9; k++){
						board[i][j]=k;
						if(isValid(i,j,k)&&solver()){
							return true;
						}
						board[i][j] = 0;
					}
					return false;
					
				}
			}
			
		}
		return true;
		
	}

}