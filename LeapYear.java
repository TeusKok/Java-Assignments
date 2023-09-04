public class LeapYear{
	public static void main(String []args) {
	int year = Integer.parseInt(args[0]);
	boolean leap = true;
	if(year%4 != 0) leap = false;
	if((year%100 == 0)&& (year%400!=0)) leap = false;
	if(leap) System.out.printf("%d is a leap year",year);
	else System.out.printf("%d is not a leap year",year);
	
	
	
	}
}