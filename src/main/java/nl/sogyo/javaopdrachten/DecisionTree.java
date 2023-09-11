import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTree{
	static ArrayList<Edge> edgeList = new ArrayList<Edge>();
	static HashMap<String,Node> nodeMap = new HashMap<String,Node>();
	static ArrayList<String> nodeNameList = new ArrayList<String>();
	static Node root;
	
	public static void main(String []args){
		String file = "../../../../resources/intermediate/decision-tree-data.txt";
		readFromFileToNodesAndEdges(file);
		if(nodeMap.size()==0){
			System.out.println("No Nodes were created, perhaps the checked file is empty");
			System.out.print(" or the syntax of the file is different than expected");
		}
		else{
			connectNodesUsingEdges();
			
			edgeList.clear();
			nodeMap.clear();
			nodeNameList.clear();
			
			executeTree();
		}		
	}
	
	public static void readFromFileToNodesAndEdges(String file){
		try{
			String[] strArr;
			Scanner scanner = new Scanner(new File(file));
				while(scanner.hasNextLine()){
					strArr=scanner.nextLine().split(",");
					if(strArr.length==2){
						nodeMap.put(strArr[0],new Node(strArr[0].trim(),strArr[1].trim()));
						nodeNameList.add(strArr[0].trim());
					}
					if(strArr.length==3){
						edgeList.add(new Edge(strArr[0].trim(),strArr[1].trim(),strArr[2].trim()));
					}				
				}
				scanner.close();
		}
		catch(FileNotFoundException e){
			System.out.println("No file was found");
		}
	}
	
	public static void connectNodesUsingEdges(){
		edgeList.forEach((edge) ->{
			if(edge.answer.equals("Ja")){
				nodeMap.get(edge.origin).setYesNode(nodeMap.get(edge.destination));
				nodeNameList.remove(edge.destination);
			}
			if(edge.answer.equals("Nee")){
				nodeMap.get(edge.origin).setNoNode(nodeMap.get(edge.destination));
				nodeNameList.remove(edge.destination);
			}
			
		});
		root = nodeMap.get(nodeNameList.get(0));
	}
	public static void executeTree(){
		Node currentNode = root;
		Scanner input = new Scanner(System.in);
		String userAnswer;
		
		System.out.println("Answer all questions with Yes or No, anyting other than Yes will be treated as No.");
		while(!currentNode.isAnswer){
			System.out.println(currentNode.question);
			userAnswer = input.nextLine().toLowerCase().trim();
			
			if(userAnswer.equals("yes")) currentNode = currentNode.getYesNode();
			else currentNode = currentNode.getNoNode();	
		}
		input.close();
		System.out.println("The answer is: "+currentNode.question);		
	}	
}