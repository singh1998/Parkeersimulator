/**
 * This class represents a queue that can store cars
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.model;

import java.util.LinkedList;
import java.util.Queue;

public class CarQueue {

    private Queue<Car> queue = new LinkedList<>();

    /**
     * This method adds a car tho this queue
     * and returns true if the car has been added
     * @param car a Car Object
     * @return boolean type
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     * This method removes the first car from this queue
     * and gives this car
     * @return a Car object
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     * This method returns the size of this queue
     * @return an int with the size of this queue
     */
    public int carsInQueue(){
    	return queue.size();
    }
}
