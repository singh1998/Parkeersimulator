

package parkeersimulator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Model extends AbstractModel {

    private int numberOfFloors;
    private int numberOfRows;
    private int numberOfPlaces;
    private int numberOfOpenSpots;
    private int steps;
    private Car[][][] cars;
    //selfmade check if hundred steps button is pressed
    private boolean hundredEnabled;

    private static final String AD_HOC = "1";
    private static final String PASS = "2";

    //selfmade for amounts in parking garage
    private ArrayList<Car> paidCars;
    private ArrayList<Car> subscribedCars;

    private CarQueue entranceCarQueue;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;


    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    String dagen[]={"maandag","dinsdag","woensdag","donderdag","vrijdag","zaterdag","zondag"};

    private int tickPause = 100;

    int weekDayArrivals= 100; // average number of arriving cars per hour
    int weekendArrivals = 200; // average number of arriving cars per hour
    int weekDayPassArrivals= 50; // average number of arriving cars per hour
    int weekendPassArrivals = 5; // average number of arriving cars per hour

    int enterSpeed = 3; // number of cars that can enter per minute
    int paymentSpeed = 7; // number of cars that can pay per minute
    int exitSpeed = 5; // number of cars that can leave per minute



    double pricePerHour = 2.00; // price per minute per car
    double previousDailyRevenue = 0; // revenue earned per day
    double actualDailyRevenue=0;
    double ExpectedRevenue=0;

    public Model(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        //self added for amount in parking garage
        paidCars=new ArrayList<>();
        subscribedCars=new ArrayList<>();

        this.numberOfFloors = numberOfFloors;
        this.numberOfRows = numberOfRows;
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfOpenSpots =numberOfFloors*numberOfRows*numberOfPlaces;
        cars = new Car[numberOfFloors][numberOfRows][numberOfPlaces];
        entranceCarQueue = new CarQueue();
        entrancePassQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

    }
    //
    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public int getNumberOfOpenSpots(){
        return numberOfOpenSpots;
    }

    public Car getCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        return cars[location.getFloor()][location.getRow()][location.getPlace()];
    }

    public boolean setCarAt(Location location, Car car) {
        if (!locationIsValid(location)) {
            return false;
        }
        Car oldCar = getCarAt(location);
        if (oldCar == null) {
            cars[location.getFloor()][location.getRow()][location.getPlace()] = car;
            car.setLocation(location);
            numberOfOpenSpots--;
            return true;
        }
        return false;
    }

    public Car removeCarAt(Location location) {
        if (!locationIsValid(location)) {
            return null;
        }
        Car car = getCarAt(location);
        if (car == null) {
            return null;
        }
        cars[location.getFloor()][location.getRow()][location.getPlace()] = null;
        car.setLocation(null);


        //selfplaced - remove a car from the the countinglists that count the amount of cars in the parking garage
        if(car instanceof ParkingPassCar ){
            subscribedCars.remove(car);
        }
        if(car instanceof AdHocCar){
            paidCars.remove(car);
        }
        numberOfOpenSpots++;

        return car;
    }
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Location getFirstFreePassLocation() {
        for (int floor = getNumberOfFloors() - 1; floor < getNumberOfFloors() && (floor == 0 || floor > 0); floor--) {
            for (int row = getNumberOfRows() - 1; row < getNumberOfRows() && (row == 0 || row > 0); row--) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getCarAt(location) == null) {
                        return location;
                    }
                }
            }
        }
        return null;
    }
//cannot refactor duplicate code because of missing return statement error, if you return null, there will be an error
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }

    public void changeLocation() {
        for (int floor = 0; floor < getNumberOfFloors(); floor++) {
            for (int row = 0; row < getNumberOfRows(); row++) {
                for (int place = 0; place < getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    private boolean locationIsValid(Location location) {
        int floor = location.getFloor();
        int row = location.getRow();
        int place = location.getPlace();
        if (floor < 0 || floor >= numberOfFloors || row < 0 || row > numberOfRows || place < 0 || place > numberOfPlaces) {
            return false;
        }
        return true;
    }

    public class TickThread implements Runnable{
        @Override
        public void run() {
            if(hundredEnabled){
                for (int i = 0; i < 100; i++) {
                    tick();
                }
            } else {
                tick();
            }
            ticks=new Thread(new TickThread());
        }
        private void tick() {

            advanceTime();
            handleExit();
            /* Pause.
            try {
                Thread.sleep(tickPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
            handleEntrance();
            updateRevenues();
            updateViews();

        }
        private void advanceTime(){
            // Advance the time by one minute.



            minute++;
            steps=getMinutes() + (getHours() * 60) + (getDays() * 1440);
            while (minute > 59) {
                minute -= 60;
                hour++;

            }
            while (hour > 23) {
                hour -= 24;

                day++;



            }
            while (day > 6) {

                day -= 7;
            }
        }
        private void handleEntrance(){
            carsArriving();
            carsEntering(entrancePassQueue);
            carsEntering(entranceCarQueue);
        }
        private void handleExit(){
            carsReadyToLeave();
            carsPaying();
            carsLeaving();
        }
        private void updateViews(){
            changeLocation();
            // Update the views.
            notifyViews();
        }

    }
    Thread ticks=new Thread(new TickThread());
    //self made one step
    public void oneStep(){
        if(!ticks.isAlive()) {
            hundredEnabled=false;
            ticks.start();
        }
    }
    //selfmade hundred steps
    public void hundredSteps(){
        if(!ticks.isAlive()) {
            hundredEnabled=true;
            ticks.start();
        }
    }
    private void carsArriving(){
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
    }
    private void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed) {
            Car car = queue.removeCar();
            Color color = car.getColor();
            //selfplaced-check if car is a ParkingPassCar or a AdHocCar and the add the car to the correct countinlist and control Passspaces
             Location freeLocation;
            if(car instanceof ParkingPassCar ){
                subscribedCars.add(car);
                freeLocation = getFirstFreePassLocation();

            } else {
                paidCars.add(car);
                freeLocation = getFirstFreeLocation();
            }
            setCarAt(freeLocation, car);
            i++;
        }
    }
    private void carsReadyToLeave(){
        // Add leaving cars to the payment queue.
        Car car = getFirstLeavingCar();
        while (car!=null) {
            if (car.getHasToPay()){
                car.setIsPaying(true);
                paymentCarQueue.addCar(car);
            }
            else {
                carLeavesSpot(car);
            }
            car = getFirstLeavingCar();
        }
    }

    private void carsPaying(){
        // Let cars pay.
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            calculatePrice(car);
            carLeavesSpot(car);
            i++;
        }
    }

    private void carsLeaving(){
        // Let cars leave.
        int i=0;
        while (exitCarQueue.carsInQueue()>0 && i < exitSpeed){
            exitCarQueue.removeCar();
            i++;
        }
    }

    private int getNumberOfCars(int weekDay, int weekend){
        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDay
                : weekend;

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.3;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        return (int)Math.round(numberOfCarsPerHour / 60);
    }
    private void addArrivingCars(int numberOfCars, String type){
        // Add the cars to the back of the queue.
        switch(type) {
            case AD_HOC:
                for (int i = 0; i < numberOfCars; i++) {
                    entranceCarQueue.addCar(new AdHocCar());
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    entrancePassQueue.addCar(new ParkingPassCar());
                }
                break;
        }
    }
    private void carLeavesSpot(Car car){
        removeCarAt(car.getLocation());
        exitCarQueue.addCar(car);
    }
    //selfmade-get the number of cars that are in the arrivingqueue and don't have a subscription
    public int getPayingArrivingCars(){
        return entranceCarQueue.carsInQueue();
    }
    //selfmade-get the number of cars that are  in the arrivingqueue  have a subscription
    public int getSubscribtionArrivingCars(){
        return  entrancePassQueue.carsInQueue();
    }


    //selfmade-get the number of cars that are in the exitqueue
    public int getLeavingCars(){
        return exitCarQueue.carsInQueue();
    }

    //selfmade-get the number of customers that are in the payingqueue
    public int getPayingCars(){
       return paymentCarQueue.carsInQueue();
    }
    //selfmade-get the amount of paid cars in the parking garage
    public int getAmountPaidCars(){
        return paidCars.size();
    }

    //selfmade-get the amount of subscribed cars in the parking garage
    public int getAmountSubscribedCars(){
        return subscribedCars.size();
    }
    //selfmade-get the amount of minutes that have been passed
    public int getMinutes(){
        return minute;
    }
    //selfmade-get the amount of hours that have been passed
    public int getHours(){
        return hour;
    }
    //selfmade-get the day of the week
    public String getDay(){
        return dagen[day];
    }
    //selfmade-get the amount of days that have been passed in a week
    public int getDays(){
        return day;
    }
    //selfmade get amount of steps in simulation
    public int getSteps(){
        return steps;
    }
    //selfmade-Calculates the price per customer that pays and ads it to the  actual revenue of the current day
    public void calculatePrice(Car car)
    {
       actualDailyRevenue+=((double)car.getStayMinutes()/(double)60)*pricePerHour;
    }
    //selfmade-Calculates the excpected price per customer that is in the garage and has not paid yet
    public void calculateExpectedRevenue(Car car){
        ExpectedRevenue+=((double)car.getStayMinutes()/(double)60)*pricePerHour;
    }

    //selfmade- return the revenue that is expected to be earned, from the customers that are still parked
    public double getExpectedRevenue(){
        ExpectedRevenue=0;
        for(Car car: paidCars){
            calculateExpectedRevenue(car);
        }
        return ExpectedRevenue;


    }

    //selfmade-Get the  daily revenue of the previous day
    public double getDailyRevenue()
    {
        return previousDailyRevenue;
    }
    public double getActualDailyRevenue(){
        return actualDailyRevenue;
    }
    //selfmade-assign actual daily revenue of previous day to previousDailyRevenu and reset actual daily revenue
    public void updateRevenues(){
        if(getHours()==0 && getMinutes()==0) {
            previousDailyRevenue = actualDailyRevenue;
            actualDailyRevenue = 0;
        }
    }

}
