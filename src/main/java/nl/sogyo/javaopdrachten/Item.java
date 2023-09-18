import java.util.ArrayList;
public class Item{
	String store = "";
	int storeLevel = 6;
	String name;
	boolean craftable = false;
	boolean buyable = false;
	boolean checked = false;
	boolean fuseCheaper = false;
	//String cheapestString;
	//String coreItems;
	int changeCounter = 0;
	ArrayList<Fusion> fusionList = new ArrayList<>();
	Fusion cheapestFusion;
	Fusion fastestFusion;
	Item item1; Item item2;
	int price = 100000;
	int craftPrice = 100000;
	public static void main(String [] args){
		
		
	}
	public Item(String name, int price, String store, int storeLevel){
		this.name = name;
		this.price = price;
		this.store = store;
		this.storeLevel = storeLevel;
		this.buyable = true;
	}
	
	
	
	public void addFusion(Fusion fusion,String mode){
		this.fusionList.add(fusion);
		this.updateFusion(fusion,mode);
		
	}
	
	
	public void updateFusion(Fusion fusion, String mode){
		int tempCraftPrice = fusion.item1.getBestPrice()+fusion.item2.getBestPrice();
		if(mode.contains("f")){	
			if(fastestFusion==null)fastestFusion=fusion;
			if(fusion.storeLevel<=this.storeLevel){
				if(fusion.storeLevel<fastestFusion.storeLevel){
					fastestFusion=fusion;
					this.changeCounter ++;
					this.craftPrice = tempCraftPrice;
					if(this.craftPrice<this.price) this.fuseCheaper=true;					
				}
				else {
					if(fusion.storeLevel==fastestFusion.storeLevel
						&&tempCraftPrice<this.craftPrice){
					fastestFusion=fusion;
					this.changeCounter ++;
					this.craftPrice = tempCraftPrice;
					if(this.craftPrice<this.price) this.fuseCheaper=true;
					}
				}
				
			}
		}
		else{
			
			if(tempCraftPrice<this.craftPrice){
				this.changeCounter ++;
				this.craftPrice = tempCraftPrice;
				this.cheapestFusion = fusion;
				if(this.craftPrice<this.price) this.fuseCheaper=true;
			}
		}
	}
	
	public void updateAllFusions(String mode){
		this.changeCounter = 0;
		this.fusionList.forEach((fusion) -> {
			this.updateFusion(fusion,mode);
		});
	}
	
	public String getFastestPath(){
		String s="("+this.name;
		if(this.fastestFusion.storeLevel<this.storeLevel
			||this.fastestFusion.storeLevel==this.storeLevel&&this.fuseCheaper){
			
			s+= " = "+this.fastestFusion.item1.getFastestPath()+" + ";
			s+= this.fastestFusion.item2.getFastestPath();
		}
		return s+")" ;
	}
	
	public String getCheapestPath(){
		String s="("+this.name;
		if(this.fuseCheaper){
			
			s+= " = "+this.cheapestFusion.item1.getCheapestPath()+" + ";
			s+= this.cheapestFusion.item2.getCheapestPath();
		}
		return s+")" ;
	}
	
	public int getBestPrice(){
		return Math.min(this.craftPrice,this.price);
	}
	
	
	
	
	
	public Item(String name){
		this.name=name;
		
	}
}