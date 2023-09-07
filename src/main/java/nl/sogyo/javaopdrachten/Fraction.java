public class Fraction{
	
	private final int numerator;
	private final int denominator;
	
	public Fraction(int num, int denom){
		this.numerator = num;
		this.denominator = denom;
	}
	
	public String toString(){
		String s = ""; int num = this.numerator; int denom = this.denominator;
		if(num*denom<0)s+="-";
		s+=Math.abs(num)+"/"+Math.abs(denom);
		if(num%(double)denom==0)s=""+(int)(num/(double)denom);
		if(num==0)s="0";
		if(denom==0)s="Division by 0 is sadly not possible in this reality";
		
		return s;	
	}
	
	public double toDecimalNotation(){
		double frac = this.numerator/(double)this.denominator;
		int n = 5;
		double factor =(double)Math.pow(10,n);
		double roundedFrac = Math.round(frac*factor)/factor;
		return roundedFrac;
	}
	
	public int GCD(int a, int b){	//GreatestCommonDenominator
		if (b==0)return a;
		return GCD(b,a%b);
	}
	
	public Fraction add(int number){
		int newNum = this.numerator+(number*this.denominator);
		return simpleFraction(newNum,this.denominator);
	}
	
	public Fraction add(Fraction fraction){
		int num =this.numerator;
		int denom = this.denominator;
		int num2 = fraction.numerator;
		int denom2 = fraction.denominator;
		int newNum = num*denom2+num2*denom;
		int newDenom = denom*denom2;
		
		return simpleFraction(newNum,newDenom);
	}
	
	public Fraction subtract(int number){
		int newNum = this.numerator-(number*this.denominator);
		return simpleFraction(newNum,this.denominator);
		
	}
	
	public Fraction simpleFraction(int num, int denom){
		int gcd = GCD(num,denom);
		
		return new Fraction(num/gcd,denom/gcd);
	}
	
	public Fraction subtract(Fraction fraction){
		int num =this.numerator;
		int denom = this.denominator;
		int num2 = fraction.numerator;
		int denom2 = fraction.denominator;
		int newNum = num*denom2-num2*denom;
		int newDenom = denom*denom2;
		
		return simpleFraction(newNum,newDenom);
	}
	
	public Fraction multiply(int number){
		int newNom=this.numerator*number;
		
		return simpleFraction(newNom,this.denominator);
	}
	
	public Fraction multiply(Fraction fraction){
		int newNum = this.numerator*fraction.numerator;
		int newDenom = this.denominator*fraction.denominator;
		
		return simpleFraction(newNum,newDenom);
	}
	
	public Fraction divide(int number){
		int newDenom = this.denominator*2;
		
		return simpleFraction(this.numerator,newDenom);
	}
	
	public Fraction divide(Fraction fraction){
		int newNum = this.numerator*fraction.denominator;
		int newDenom = this.denominator*fraction.numerator;
		
		return simpleFraction(newNum,newDenom);
	}
	
	public static void main(String []args){
		int num = Integer.parseInt(args[0]);
		int denom = Integer.parseInt(args[1]);
		Fraction frac = new Fraction(num,denom);
		
		int num2 = Integer.parseInt(args[2]);
		int denom2 = Integer.parseInt(args[3]);
		Fraction frac2 = new Fraction(num2,denom2);
		
		System.out.println(frac.toDecimalNotation());
		System.out.println(frac.toString());
		
		System.out.println(frac.add(1).toString());
		System.out.println(frac.add(frac2).toString());
	
		System.out.println(frac.subtract(1).toString());
		System.out.println(frac.subtract(frac2).toString());
		
		System.out.println(frac.multiply(2).toString());
		System.out.println(frac.multiply(frac2).toString());
		
		System.out.println(frac.divide(2).toString());
		System.out.println(frac.divide(frac2).toString());
		
		
	}
}