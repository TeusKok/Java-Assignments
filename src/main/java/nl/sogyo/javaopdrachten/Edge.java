public class Edge{
	public String origin;
	public String destination;
	public String answer;
	
	public Edge(String origin,String destination,String answer){
		this.origin = origin;
		this.destination = destination;
		this.answer = answer;
	}
	
	
	public static void main(String []args){
		Edge edge = new Edge("N4","N5","Yes");
		System.out.println(edge.origin);
		System.out.println(edge.destination);
		System.out.println(edge.answer);
	}
}