import java.time.*;

public class Quote {
    static String[][] quotes = {
        {"galileo", "eppur si muove"},
        {"archimedes", "eureka!"},
        {"erasmus", "in regione caecorum rex est luscus"},
        {"socrates", "I know nothing except the fact of my ignorance"},
        {"rené descartes", "cogito, ergo sum"},
        {"sir isaac newton", "if I have seen further it is by standing on the shoulders of giants"}
    };

    public static void main(String []args) {
        LocalDate today = LocalDate.now();

		int dayOfMonth = today.getDayOfMonth();
		int dayOfYear = today.getDayOfYear();
		int quoteIndex = (dayOfYear%quotes.length)-1;
		
		String dayOfWeek = capFirstLetter(today.getDayOfWeek().toString());
		String month = capFirstLetter(today.getMonth().toString());
		String suffix = genSuffix(dayOfMonth);
		
		System.out.printf("Quote for %s the %d%s of %s:\n",dayOfWeek,dayOfMonth,suffix,month);
		System.out.println(formatQuote(quotes[quoteIndex]));
    }
	
	public static String capFirstLetter(String word){
		String result  = word.substring(0,1).toUpperCase();
		result += word.substring(1,word.length()).toLowerCase();
		return result;
	}
	
	public static String formatQuote(String[] quote){
		String rawQuote = quote[1];
		String rawQuoter = quote[0];
		String finalQuote = "\"";
		if(!rawQuote.substring(rawQuote.length()-1).matches("\\p{Punct}"))rawQuote+=".";
		finalQuote+=capFirstLetter(rawQuote)+"\" -- "+capFirstLetter(rawQuoter);
		return finalQuote;
	}
	
	public static String genSuffix(int dayOfMonth){
		if(dayOfMonth==1||dayOfMonth==21||dayOfMonth==31)return"st";
		if(dayOfMonth==2||dayOfMonth==22)return"nd";
		return "th"; 
	}
	
}  
