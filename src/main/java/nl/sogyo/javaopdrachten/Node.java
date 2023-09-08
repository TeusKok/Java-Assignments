public class Node{
	public String name;
	public String question;
	public boolean isAnswer;
	public Node nextYes;
	public Node nextNo;
	
	public Node(String name,String question){
		this.name = name;
		this.question = question;
		this.isAnswer = true;
	}
	
	public void setYesNode(Node node){
		this.nextYes = node;
		this.isAnswer = false;
	}
	
	public void setNoNode(Node node){
		this.nextNo = node;
		this.isAnswer = false;
	}
	
	public Node getYesNode(){
		return this.nextYes;
	}

	public Node getNoNode(){
		return this.nextNo;
	}
	
	public static void main(String []args){
		Node node = new Node("N4","Is the edge of the leaf smooth?");
		System.out.println(node.name);
		System.out.println(node.question);
	}
}