import java.util.*;
public class Robot{
	private int positionX;
	private int positionY;
	private String facing;
	private String state;
	private List<Runnable> orders = new ArrayList<Runnable>();
	
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
	public void execute(){
		orders.forEach((order)->order.run());
	}
	
	public void printState(){
		Runnable printState = () -> {
			System.out.println(this.state);
		};
		printState.run();
	}
	
	public void printPosition(){
		Runnable printPosition = () -> {
			System.out.println("positioned at ("+positionX+","+positionY+")");
		};
		orders.add(printPosition);
	}
	
	public void turnLeft(){
		Runnable turnLeft = () -> {
			String currentFacing = this.facing;
			
			if(currentFacing.equals("North"))this.facing = "West";
			else if(currentFacing.equals("West"))this.facing = "South";
			else if(currentFacing.equals("South"))this.facing = "East";
			else if(currentFacing.equals("East"))this.facing = "North";
			this.state = "Now facing \""+this.facing+"\"";
		};
		orders.add(turnLeft);
	}
	
	public void turnRight(){
		Runnable turnRight = () -> {
			String currentFacing = this.facing;
			
			if(currentFacing.equals("North"))this.facing = "East";
			else if(currentFacing.equals("West"))this.facing = "North";
			else if(currentFacing.equals("South"))this.facing = "West";
			else if(currentFacing.equals("East"))this.facing = "South";
			this.state = "Now facing \""+this.facing+"\"";
		};
		orders.add(turnRight);
	}
	
	public void forward(){
		int speed = 1;
		forward(speed);
	}	
	
	public void backward(){
		Runnable backward = () -> {
			String currentFacing = this.facing;
			int speed = 1;
			if(currentFacing.equals("North"))this.positionY-=speed;
			else if(currentFacing.equals("West"))this.positionX+=speed;
			else if(currentFacing.equals("South"))this.positionY+=speed;
			else if(currentFacing.equals("East"))this.positionX-=speed;
			this.state = "Now facing \""+this.facing+"\" at ("+this.positionX+","+positionY+")";
		};
		orders.add(backward);
	}
	
	
	public void forward(int speed){
		Runnable forward = () -> {
			if(speed<1||speed>3)System.out.println("Incorrect speed, speed should be between 1 and 3.");
			else{
				String currentFacing = this.facing;
				
				if(currentFacing.equals("North"))this.positionY+=speed;
				else if(currentFacing.equals("West"))this.positionX-=speed;
				else if(currentFacing.equals("South"))this.positionY-=speed;
				else if(currentFacing.equals("East"))this.positionX+=speed;
				this.state = "Now facing \""+this.facing+"\" at ("+this.positionX+","+positionY+")";
			}
		};
		orders.add(forward);
	}
	
	
	public static void main(String []args){
		Robot myFirstRobot = new Robot();
		
		myFirstRobot.turnLeft();
		myFirstRobot.printState();
		myFirstRobot.forward();
		myFirstRobot.backward();
		myFirstRobot.execute();		
	}
}