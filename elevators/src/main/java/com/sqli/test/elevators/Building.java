package com.sqli.test.elevators;

public class Building {

    /**
     * @param numberOfFloors: the number of floors in the building
     * @param elevators: an array of descriptions of the elevators of the building. 
     *                   A description has the following format "[elevator_id]:[elevator_current_floor]".
     */
    //creat a tabl of class Elevetor.
    
	private int numberOfFloors;
	private Elevetor[] elevators;
	
    //constructor of class Building which recieves any Numbre of floors and multiple of Strings.
    public Building(int numberOfFloors, String... elevatorsString) {
        
    	this.elevators = new Elevetor[elevatorsString.length];
    	this.numberOfFloors = numberOfFloors;
    	for(int i=0; i<elevatorsString.length; i++){
            
            //listIdFloor tabl contain elevetor_id and elevator_current_floor.
    		String[] listIdFloor = elevatorsString[i].split(":");
    		elevators[i] = new Elevetor(listIdFloor[0], Integer.parseInt(listIdFloor[1])); 
    	}
    }
    /**
     * Request an elevator at floor zero.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator() {
        /*
        *ElevetorGoingUpId : id of the possible elevator going up to serve.
        *ElevetorGoingDownId : id of the possible elevator going down to serve.
        *ElevetorNotMovingId : id of the possible stable elevator to serve.
        */
    	String ElevetorGoingUpId ="";
    	String ElevetorGoingDownId ="";
    	String ElevetorNotMovingId ="";
        /*
        *closest_elevater_UP : floor of elevator with id == ElevetorGoingUpId.
        *closest_elevater_DOWN : floor of elevator with id == ElevetorGoingDownId;
        *closest_elevater_NotMoving : floor of elevator with id == ElevetorNotMovingId.
        */
    	int closest_elevater_UP = -1;
    	int closest_elevater_DOWN = numberOfFloors+1;
    	int closest_elevater_NotMoving = numberOfFloors+1;
        //test all Elevator elements.
    	for(int i=0; i<elevators.length; i++){
            /* if our elevator move up then take the elevator with max floor     
            *  between the elevators going up.
            */
    		if(elevators[i].isMoveUp()){
    			if(closest_elevater_UP < elevators[i].getElevator_current_floor()){
    				closest_elevater_UP = elevators[i].getElevator_current_floor();
    				ElevetorGoingUpId = elevators[i].getElevetor_Id();
    			}
    		}
            /*
            *  if our elevator move down then take the elevator with min floor     
            *  between the elevators going down.
            *  
            */
    		else  if(elevators[i].isMoveDown()){
    			if(closest_elevater_DOWN > elevators[i].getElevator_current_floor()){
    				closest_elevater_DOWN = elevators[i].getElevator_current_floor();
    				ElevetorGoingDownId = elevators[i].getElevetor_Id();
    			}
    		}
            /*
            *if our elevator is stabl then take the elevator with nearest floor     
            *  between the elevators stabl.
            *
            */
    		else{
    			if(closest_elevater_NotMoving > elevators[i].getElevator_current_floor()){
    				closest_elevater_NotMoving = elevators[i].getElevator_current_floor();
    				ElevetorNotMovingId = elevators[i].getElevetor_Id();
    			}
    		}
    	}
    	/*
        * if there is one closest elevator stable or moving down then take first the    
        * closest elevator if exist else stabl elevator's.
        */
    	if(closest_elevater_NotMoving !=numberOfFloors+1 || closest_elevater_DOWN != numberOfFloors+1 ){
    		if(closest_elevater_NotMoving > closest_elevater_DOWN ){
    			return ElevetorGoingDownId;
    		}
    		else {
    			return ElevetorNotMovingId;
    		}
    	}
        // else take the closest elevator id moving up.
    	else {
    		return ElevetorGoingUpId;
    	}
    	
    }

    /**
     * Request an elevator at floor indicate by the {@code floor} param.
     * @param floor : the floor where the request is made.
     * @return the id of the elevator that should serve the request.
     */
    public String requestElevator(int floor) {
    	String idOfFirstAvailableElevator =elevators[0].getElevetor_Id();
    	int highElevator = Math.abs(numberOfFloors - elevators[0].getElevator_current_floor());
    	//search the high floor elevator.
        for(int i=1; i<elevators.length; i++){
        	int high_first_elevator = Math.abs(numberOfFloors - elevators[i].getElevator_current_floor());
            //search the closest elevetor to the top floor.  
        	if(high_first_elevator < highElevator){
        		idOfFirstAvailableElevator = elevators[i].getElevetor_Id();
        		highElevator = high_first_elevator;
        	}
        }
        return idOfFirstAvailableElevator;
    }

    /**
     * Request the elevator with the id {@code elevatorId} to stop at the floor indicated by the {@code floor} param.
     * @param elevatorId : the id of the elevator to whom give the order.
     * @param floor : the floor at which the elevator should stop
     */
    public void stopAt(String elevatorId, int floor) {
    	for(int i=0; i<elevators.length; i++){
            //stop the elevator at the proposing floor.
        	if(elevators[i].getElevetor_Id().equals(elevatorId)){
        		elevators[i].setElevator_current_floor(floor);
        		return;
        	}
        }
    }

    /**
     * Move the elevator with the id {@code elevatorId} in the direction indicated by the {@code direction} param.
     * @param elevatorId : the id of the elevator to move.
     * @param direction : the direction to go. Can be "UP" or "DOWN".
     */
    public void move(String elevatorId, String direction) {
    	for(int i=0; i<elevators.length; i++){
        	if(elevators[i].getElevetor_Id().equals(elevatorId)){
                //testing if the request direction is DOWN their set the function MoveDown to true.
        		if(direction.equals("DOWN")){
        			elevators[i].setMoveDown(true);
        		}
                //testing if the request digestion is UP their set the function MoveUp to true.
        		else{
        			elevators[i].setMoveUp(true);
        		}
        		return;
        	}
        }
    }
    
	public void stopAllMoves(){
		for(int i=0; i<elevators.length; i++){
			elevators[i].stopMove();
		}
	}
}