import java.util.*;
import java.util.regex.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Main{
	public static HashMap<String,Item> itemMap = new HashMap<String,Item>();
	public static int changeCounter = 0;
	public static String mode;
	
	public static void main(String []args){
		UserInput();
		
		//System.out.println("\n"+changeCounter+"\n");

		//printAllCheapestPaths();
		//System.out.println("\n"+changeCounter+"\n");
		//System.out.println(itemMap.get("our ancestors").getCheapestPath());
		//printPricesOfPath(itemMap.get("our ancestors").getCheapestPath());
		
		
		//String output = itemMap.get("at dusk").getCheapestPath();
		//System.out.println(output);
	}
	
	public static void UserInput(){
		System.out.println("Which item do you want?: ");
		Scanner input = new Scanner(System.in);
		String item = input.nextLine().toLowerCase();
		
		System.out.println("type fast or cheap");
		mode = input.nextLine().toLowerCase();
		
		readFile();
		updateTillOptimal();
		if(mode.contains("f")){
			System.out.println("\n"+itemMap.get(item).getFastestPath());
			printPricesOfPath(itemMap.get(item).getFastestPath());
		}
		else{
			System.out.println("\n"+itemMap.get(item).getCheapestPath());
			printPricesOfPath(itemMap.get(item).getCheapestPath());
		}
		input.close();
	}
	
	
	
	public static void updateTillOptimal(){
		updateAllItems();
		while(changeCounter>0){
			System.out.println(changeCounter);
			updateAllItems();
		}
	}
	
	public static int storeNameToLevel(String store){
		switch (store) {
		  case "beginning":
			return 1;
		  case "second":
			return 2;
		  case "third":
			return 3;
		  case "4th":
			return 4;		
		  case "5th":
			return 5;
		}
		return 1;
	}
	
	public static void updateAllItems(){
		changeCounter = 0;
		itemMap.forEach((key, item) ->{
			item.updateAllFusions(mode);
			changeCounter+=item.changeCounter;
		});
	}	

	public static void printAllCheapestPaths(){
		itemMap.forEach((key, item) ->{
			System.out.println(item.getCheapestPath());
		});
	}
	
	public static void printPricesOfPath(String path){
		//String mydata = "some string with 'the data i want' inside";
		String[] s = path.split("\\)");
		String item;
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		int totalPrice = 0;
		for(String string:s){
			if(!string.equals("")){
				
				String ss[] = string.split("\\(");
				item = ss[ss.length-1].trim();
				if(items.putIfAbsent(item,1)!=null){
					items.put(item, items.putIfAbsent(item,1)+1);
				}
				
				totalPrice+=itemMap.get(item).price;
			}
		}
		
		items.forEach((key,count)->{
			int price = itemMap.get(key).price;
			String store = itemMap.get(key).store;
			System.out.print("\n"+store + " Store");
			System.out.printf("\n%dx %-20s %d x %4d = %-5d",count,key,count,price,price*count );	
		});
		System.out.println("\n----------------------------------------\n");
		System.out.println("Total                              "+totalPrice);
	}
	

	public static void readFile(){
		String file = "../../../../resources/advanced/item-fusion-data.txt";
		Item item1; Item item2; String item;
		String[] bothItems = new String[2]; String[] sSplit = new String[2];
		try{
			Scanner scanner = new Scanner(new File(file)); int storeLevel = 1;
			String s; boolean items=false; boolean fusions=false; String store="";
			while(scanner.hasNextLine()){
				s=scanner.nextLine().toLowerCase();
				if(s.contains("store item")){
					if(!items)items = true;
					if(s.contains("fusions")){
						if(!fusions){
							fusions=true; 
							items=false;
							storeLevel = 1;
						}
					}
					else{
						store=s.split(" ")[0].trim();
						storeLevel = storeNameToLevel(store);
					}
				}
				else{
					if(!(s.contains("****")||s.contains("name ~ cost")||s.isEmpty())){	
						if(items){
							if(s.contains("*")){
								s=s.replace("*","");
							}
							sSplit = s.split("~"); 
							item = sSplit[0].trim();
							
							itemMap.putIfAbsent(item,new Item(item,Integer.parseInt(sSplit[1].trim()),store,storeLevel));	
						}
						if(fusions){	
							sSplit = s.split("=");
							bothItems = sSplit[0].split("\\+");
							item1 = itemMap.get(bothItems[0].trim());
							
							item2 = itemMap.get(bothItems[1].trim());
							String result = sSplit[1].trim();
							if(itemMap.get(result)== null){
								itemMap.put(result,new Item(result));
							}
							itemMap.get(result).addFusion(new Fusion(
								item1, item2, itemMap.get(result), storeLevel),mode);
						}
					}
					else if(s.contains("****")) storeLevel++;
				}	
			}
			scanner.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
	}
}