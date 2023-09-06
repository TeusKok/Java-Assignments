public class Robot{
	private int positionX;
	private int positionY;
	private String facing;
	private String state;
	public Robot(){
		positionX = 0;
		positionY = 0;
		facing = "North";
		state = "Still facing \""+facing+"\"";
	}
	public Robot(int positionX,int positionY,String facing){
		this.positionX = positionX;
		this.positionY = positionY;
		this.facing = facing;
		this.state = "Still facing \""+facing+"\"";
	}
	public void printState(){
		System.out.println(this.state);
	}
	public void printPosition(){
		System.out.println("positioned at ("+positionX+","+positionY+")");
	}
	public void turnLeft(){
		String currentFacing = this.facing;
		
		if(currentFacing.equals("North"))this.facing = "West";
		else if(currentFacing.equals("West"))this.facing = "South";
		else if(currentFacing.equals("South"))this.facing = "East";
		else if(currentFacing.equals("East"))this.facing = "North";
		this.state = "Now facing \""+this.facing+"\"";
	}
	
	public void turnRight(){
		String currentFacing = this.facing;
		
		if(currentFacing.equals("North"))this.facing = "East";
		else if(currentFacing.equals("West"))this.facing = "North";
		else if(currentFacing.equals("South"))this.facing = "West";
		else if(currentFacing.equals("East"))this.facing = "South";
		this.state = "Now facing \""+this.facing+"\"";
	}
	
	public void forward(){
		String currentFacing = this.facing;
		int speed = 1;
		if(currentFacing.equals("North"))this.positionY+=speed;
		else if(currentFacing.equals("West"))this.positionX-=speed;
		else if(currentFacing.equals("South"))this.positionY-=speed;
		else if(currentFacing.equals("East"))this.positionX+=speed;
		this.state = "Now facing \""+this.facing+"\" at ("+this.positionX+","+positionY+")";
	}
	
	
	public void backward(){
		String currentFacing = this.facing;
		int speed = 1;
		if(currentFacing.equals("North"))this.positionY-=speed;
		else if(currentFacing.equals("West"))this.positionX+=speed;
		else if(currentFacing.equals("South"))this.positionY+=speed;
		else if(currentFacing.equals("East"))this.positionX-=speed;
		this.state = "Now facing \""+this.facing+"\" at ("+this.positionX+","+positionY+")";
	}
	
	
	public void forward(int speed){
		if(speed<1||speed>3)System.out.println("Incorrect speed, speed should be between 1 and 3.");
		else{
			String currentFacing = this.facing;
			
			if(currentFacing.equals("North"))this.positionY+=speed;
			else if(currentFacing.equals("West"))this.positionX-=speed;
			else if(currentFacing.equals("South"))this.positionY-=speed;
			else if(currentFacing.equals("East"))this.positionX+=speed;
			this.state = "Now facing \""+this.facing+"\" at ("+this.positionX+","+positionY+")";
		}
	}
	
	
	public static void main(String []args){
		Robot rob = new Robot();
		rob.printState();
		rob.turnRight();
		rob.printState();
		rob.backward();
		rob.printState();
		rob.turnLeft();
		rob.printState();
		rob.forward();
		rob.printState();
		Robot robo = new Robot(1,2,"East");
		robo.printState();
		robo.printPosition();
	}
}