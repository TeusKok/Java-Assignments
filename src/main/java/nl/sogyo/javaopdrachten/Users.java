import java.util.Scanner;
public class Users{
	
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a Username");
		String username = input.next();
		System.out.println("Enter a Password");
		String password = input.next();
		while(!validatePassword(password)){
			System.out.println("This is not a valid password. Please enter a stronger password.");
			password = input.next();
		}
		System.out.println("Registered user "+username);
		
		
	}
	
	public static boolean validatePassword(String password){
		boolean hasUpperCase = false;
		boolean hasLowerCase = false;
		boolean hasNumber = false;
		
		char c;
		
		for(int i =0;i<password.length();i++){
			c = password.charAt(i);
			if(Character.isDigit(c))hasNumber = true;
			else if(Character.isUpperCase(c))hasUpperCase = true;
			else if(Character.isLowerCase(c))hasLowerCase = true;
			if(hasNumber&&hasLowerCase&&hasUpperCase)return true;
		}
		return false;
	}
	
	public static void registerUser(String username,String password)throws Exception{
		if(validatePassword(password))System.out.println("Registered user "+username);
		else {
			throw new Exception("Something has gone wrong");
		}
	}
}
	