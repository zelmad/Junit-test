package com.sqli.test.elevators;

public class Elevetor {
	
	//class elevator variables.
    
	private String Elevetor_Id;
	private int elevator_current_floor;
	private boolean moveUp;
	private boolean moveDown;
    
	//class constructor  
	public Elevetor(String Elevetor_Id, int elevator_current_floor){
		this.Elevetor_Id = Elevetor_Id;
		this.elevator_current_floor = elevator_current_floor;
	}
    // setters and getters.
	
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}
    
    public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}
    
	public String getElevetor_Id() {
		return Elevetor_Id;
	}

	public void setElevetor_Id(String elevetor_Id) {
		Elevetor_Id = elevetor_Id;
	}

	public int getElevator_current_floor() {
		return elevator_current_floor;
	}

	public void setElevator_current_floor(int elevator_current_floor) {
		this.elevator_current_floor = elevator_current_floor;
	}
    
    public boolean isMoveUp() {
		return moveUp;
	}
    
    public boolean isMoveDown() {
		return moveDown;
	}
    
	public void stopMove(){
		this.moveDown = false;
		this.moveUp = false;
	}
}

