package game;

public class Points {

	private int points;
	private int previousPoints;
	private int Totalpoints;
	
	public Points(int x){
		points = x;
		Totalpoints = x;
		
	}
	
	public Points(){
		points = 0;
	}
	
	public void addPoints(int x){
		previousPoints = points;
		points = x;
		Totalpoints += points;
	}
	
	public void undo(){ // You Can only call undo once
		Totalpoints -= points;
		points = previousPoints;
	}
	
	public int getPoints(){
		return Totalpoints;
		
	}
}
