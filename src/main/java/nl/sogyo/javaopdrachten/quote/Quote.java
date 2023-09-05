import java.util.*;
import java.time.*;

public class Quote {
    String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };

    public static void main(String []args) {
		
        LocalDate today = LocalDate.now();
		
		String dayOfWeek = unCaps(today.getDayOfWeek().toString());				
		
		int dayOfMonth = today.getDayOfMonth();
		int dayOfYear = today.getDayOfYear();
		int quoteIndex = dayOfYear%6;
		
		
		String month = unCaps(today.getMonth().toString());
		
		System.out.printf("Quote for %s the %dth of %s:\n",dayOfWeek,dayOfMonth,month);
		
    }
	
	public static String unCaps(String word){
		
		String result  = word.substring(1,word.length()).toLowerCase();
		result = word.substring(0,1)+result;
		return result;
	}
}  
