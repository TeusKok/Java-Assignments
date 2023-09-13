import java.util.Scanner;
import java.util.ArrayList;
import java.time.ZonedDateTime;

/* Two sudoku strings:
800000000003600000070090200050007000000045700000100030001000068008500010090000400
000820090500000000308040007100000040006402503000090010093004000004035200000700900
*/
public class SudokuSolver{
	public static int[][] board = new int[9][9];
	public static int validCount =0;
	public static int invalidCount =0;
	
	public static void main(String []args){
		String s = args[0];
		readSudoku(s);
		printBoard();
		long begin = ZonedDateTime.now().toInstant().toEpochMilli();
		if(solver()){
			printBoard();
			long end =ZonedDateTime.now().toInstant().toEpochMilli();
			System.out.println("\nSolving the sudoku took "+(end-begin)+" milliseconds");
		}
		else System.out.println("not succesfull");
		System.out.println("Number of valid checks: "+validCount);
		System.out.println("Number of invalid checks: "+invalidCount);
	}
	
	public static void readSudoku(String s){
		for(int i = 0; i<s.length();i++){
			board[(i)/9][i%9]=Character.getNumericValue(s.charAt(i));							
		}	
	}
	
	public static void printBoard(){
		for(int[] row:board){
			System.out.print("\n-------------------\n|");
			for (int number: row){
				System.out.print(number+"|");
			}			
		}
		System.out.print("\n");
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
			validCount++;
			return true;
		}
		else {
			invalidCount++;
			return false;
		}
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