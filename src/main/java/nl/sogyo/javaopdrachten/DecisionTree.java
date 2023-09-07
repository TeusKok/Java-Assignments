import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree{
	public static void main(String []args){
		String file = "/C:/Users/sogyo/Documents/java-opdrachten/src/main/resources/intermediate/decision-tree-data.txt";
		String[] strArr;
		//ArrayList<Node> nodeList = new ArrayList<Node>();
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		HashMap<String,Node> nodeMap = new HashMap<String,Node>();
		ArrayList<String> NodeNameList = new ArrayList<String>();
		//HashMap<String,Edge> edgeMap = new HashMap<String,Edge>();
		
		try{
			Scanner scanner = new Scanner(new File(file));
			while(scanner.hasNextLine()){
				strArr=scanner.nextLine().split(",");
				if(strArr.length==2){
					nodeMap.put(strArr[0],new Node(strArr[0].trim(),strArr[1].trim()));
					NodeNameList.add(strArr[0].trim());
				}
				if(strArr.length==3){
					edgeList.add(new Edge(strArr[0].trim(),strArr[1].trim(),strArr[2].trim()));
				}				
			}
			scanner.close();
			
			edgeList.forEach((edge) ->{
				if(edge.answer.equals("Ja")){
					nodeMap.get(edge.origin).setYesNode(nodeMap.get(edge.destination));
					NodeNameList.remove(edge.destination);
				}
				if(edge.answer.equals("Nee")){
					nodeMap.get(edge.origin).setNoNode(nodeMap.get(edge.destination));
					NodeNameList.remove(edge.destination);
				}
				
			});
			Node currentNode = nodeMap.get(NodeNameList.get(0));
			
			Scanner input = new Scanner(System.in);
			String answer;
			
			System.out.println("Answer all questions with Yes or No, anyting other than Yes will be treated as No.");
			while(!currentNode.isAnswer){
				System.out.println(currentNode.question);
				answer = input.nextLine();
				if(answer.equals("Yes")){
					currentNode = currentNode.getYesNode();
				}
				else{
					currentNode = currentNode.getNoNode();
				}
				
			}
			System.out.println("The answer is: "+currentNode.question);
			//System.out.println(edgeList.get(0).answer);
			//System.out.println(NodeNameList);
			input.close();
			
		}
		catch(FileNotFoundException e){
			System.out.println("No file was found");
		}
		
	}
}