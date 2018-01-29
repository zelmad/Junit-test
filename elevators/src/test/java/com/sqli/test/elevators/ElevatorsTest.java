package com.sqli.test.elevators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * This test is about a building having several elevators with different states.
 * For example, each elevator can be at a different level, and can be going up,
 * or down.
 * </p>
 * 
 * The objective of this exercise is to write a program that will identify which
 * elevator will be served once a request for an elevator is registered.
 * </p>
 * 
 * Please note these rules : </br>
 * - An elevator is requested by default from floor <b>zero</b>. </br>
 * - An elevator can be resting at a given floor, going up or going down. </br>
 * - An elevator switch automatically his direction when reaching the edges of
 * the building (means the first and top floors). </br>
 * - An elevator can not switch his direction in the middle of a building.
 * Example: in a building of 10 floors, an elevator at the 3rd floor and
 * ascending can never go down before reaching the 10th floor. And vice
 * versa.</br>
 * - An elevator is never in between floors.
 * 
 * @author SQLi
 */
public class ElevatorsTest {

    @Test
    public void the_closest_elevator_to_floor_zero_should_arrive_first() {
        int numberOfFloors = 10;
        Building building = new Building(numberOfFloors, "id1:1", "id2:6");
        // "[elevator_id]:[elevator_current_floor]"

        String idOfFirstAvailableElevator = building.requestElevator(); 
        // a request is by default from floor 0.

        assertEquals("id1", idOfFirstAvailableElevator);
    }

    @Test
    public void elevators_going_up_arrive_last_to_floor_zero() {
        Building building = new Building(10, "id1:1", "id2:6");
        building.move("id1", "UP"); 
        // tell the elevator "id1" to move "UP".

        String idOfFirstAvailableElevator = building.requestElevator();

        assertEquals("id2", idOfFirstAvailableElevator);
    }

    @Test
    public void elevators_going_down_should_arrive_to_floor_zero_before_those_going_up() {
        Building building = new Building(10, "id1:1", "id2:6");
        building.move("id1", "UP");
        building.move("id2", "DOWN");

        String idOfFirstAvailableElevator = building.requestElevator();

        assertEquals("id2", idOfFirstAvailableElevator);
    }

    @Test
    public void elevators_going_down_should_be_compared_to_those_resting() {
        Building building = new Building(10, "id1:1", "id2:6", "id3:5");
        building.move("id1", "UP");
        building.move("id2", "DOWN");

        String idOfFirstAvailableElevator = building.requestElevator();
        assertEquals("id3", idOfFirstAvailableElevator);
    }

    @Test
    public void elevators_going_up_switch_direction_after_reaching_top_floor() {
        Building building = new Building(10, "id1:1", "id2:6");
        building.move("id1", "UP");
        building.move("id2", "UP");

        String idOfFirstAvailableElevator = building.requestElevator();

        assertEquals("id2", idOfFirstAvailableElevator); 
        // after both elevators reach top floor 
                                                         
        // "id2" should arrive first to floor 0
    }

    @Test
    public void can_request_elevator_in_middle_of_building() {
        Building building = new Building(10, "id1:1", "id2:6");

        String idOfFirstAvailableElevator = building.requestElevator(5); 
        // the request is made at the 5th floor

        assertEquals("id2", idOfFirstAvailableElevator); 
        // "id2" is the closest elevator to 5th floor
    }

}
