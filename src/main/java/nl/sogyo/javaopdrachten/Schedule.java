import java.util.HashMap;
public class Schedule{
	final String name;
	int duration; //minutes
	int maxDuration = 0; //minutes
	HashMap<String,Schedule> Schedules = new HashMap<String,Schedule>(); 
	
	public Schedule(String name, int duration){
		this.name = name;
		this.duration = duration;
	}
	
	public Schedule daySchedule(String name){
		Schedule day = new Schedule(name, 0);
		day.setMaxDuration(8); //8 hours per day
		return day;
	}
	
	public Schedule weekSchedule(String name){
		Schedule week = new Schedule(name, 0);
		week.setMaxDuration(40); //40 hours per week
		week.addSchedule(daySchedule("Monday"));
		week.addSchedule(daySchedule("Tuesday"));
		week.addSchedule(daySchedule("Wednesday"));
		week.addSchedule(daySchedule("Thursday"));
		week.addSchedule(daySchedule("Friday"));
		return week;
	}
	
	public void setMaxDuration (int maxDuration){ 
		this.maxDuration = maxDuration*60;
	}
	
	public void addSchedule(Schedule schedule){
		Schedules.put(schedule.name,schedule);
		this.duration+=schedule.duration;
		this.checkOvertime();
	}
	
	public void addSchedule(String name, int duration){
		Schedule newSchedule = new Schedule(name,duration);
		this.addSchedule(newSchedule);
	}
	
	public void checkOvertime(){
		if(this.duration>this.maxDuration && this.maxDuration>0){
			System.out.println("Because of the schedule you just added you are now into overtime ");
			System.out.printf("for %s. You are %d minutes into overtime.\n"
				,this.name,this.duration-this.maxDuration);
		}
	}
	
	public void addScheduleToDay(String day,Schedule schedule){
		this.Schedules.get(day).addSchedule(schedule);
	}
	
	public void addScheduleToDay(String day,String name, int duration){
		this.Schedules.get(day).addSchedule(name,duration);
	}
	
	public int getDurationFromDay(String day){
		return this.Schedules.get(day).duration;
	}
	
	public void printDuration(){
		System.out.println(this.name+" takes "+this.durationToString());
	}
	
	public void printDuration(String day){
		Schedule schedule = this.Schedules.get(day);
		System.out.println(schedule.name+" takes "+schedule.durationToString());
	}
	
	public void printSchedule(){
		this.Schedules.forEach((key, value) -> {
			System.out.printf("\n%s for %s",key,value.durationToString());
		});
		System.out.print("\n");
	}
	
	public void printSchedule(String day){
		this.Schedules.get(day).printSchedule();
	}
	
	public String durationToString(){
		int minutes = this.duration;
		int tempMinutes;
		String s="";
		if(minutes>60){
			tempMinutes = minutes;
			minutes = minutes%60;
			int hours = (tempMinutes-minutes)/60;
			return minutes+" minutes and "+hours+" hours";
		}
		return minutes+" minutes";		
	}
	
	public static void main(String []args){
		Schedule s = new Schedule("not so smart",0);
		Schedule week1 = s.weekSchedule("week1");
		System.out.println(week1.Schedules.get("Monday").maxDuration);
		Schedule yoga = new Schedule("Yoga",45);
		week1.addScheduleToDay("Monday",yoga);
		week1.printDuration();
		week1.printDuration("Monday");

		week1.printSchedule();
		week1.printSchedule("Monday");
		week1.addScheduleToDay("Monday","swimming",30);
		week1.printSchedule();
		week1.printSchedule("Monday");
		
		week1.addScheduleToDay("Monday","Nothing",500);

		week1.printDuration();
		week1.printDuration("Monday");
	}
} 