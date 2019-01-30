

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
    private boolean dayTickEnabled;
    private boolean hourEnabled;

    private static final String AD_HOC = "1";
    private static final String PASS = "2";
    private static final String RESS = "3";

    //selfmade for amounts in parking garage
    private ArrayList<Car> paidCars;
    private ArrayList<Car> subscribedCars;
    private ArrayList<Car> reservedCars;
    private ArrayList<Car> reservedCarSpots;
    //selfmade lists for cars that think the entrancequeue is too long and go away
    private ArrayList<Car> paidIgnoringCars;
    private ArrayList<Car> passIgnoringCars;

    private int previousTotalIgnoring;//the total cars that drove away because the queue's were too long of the previous day

    private CarQueue entranceCarQueue;
    private int queueLimit;
    private CarQueue entrancePassQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private Thread ticks;

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private String dagen[]={"maandag","dinsdag","woensdag","donderdag","vrijdag","zaterdag","zondag"};



    private int weekDayArrivals= 100; // average number of arriving cars per hour
    private int weekendArrivals = 200; // average number of arriving cars per hour
    private  int weekDayPassArrivals= 50; // average number of arriving cars per hour
    private int weekendPassArrivals = 100; // average number of arriving cars per hour
    private int weekDayRessArrivals = 20; //MOET VERANDERD WORDEN!
    private int weekendRessArrivals = 30; //MOET VERANDERD WORDEN!

    private int enterSpeed = 6; // number of cars that can enter per minute
    private int paymentSpeed = 2; // number of cars that can pay per minute
    private int exitSpeed = 3; // number of cars that can leave per minute



    private double pricePerHour = 2.00; // price per minute per car
    private double reservationPrice=10.00;
    private double previousDailyRevenue = 0; // revenue earned per day
    private double actualDailyRevenue=0;
    private double expectedRevenue=0;

    public Model(int numberOfFloors, int numberOfRows, int numberOfPlaces) {
        ticks=new Thread(new TickThread(this));
        //self added for amount in parking garage
        paidCars=new ArrayList<>();
        subscribedCars=new ArrayList<>();
        reservedCars=new ArrayList<>();
        paidIgnoringCars=new ArrayList<>();
        passIgnoringCars=new ArrayList<>();
        reservedCarSpots=new ArrayList<>();
        queueLimit=10;

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

    public int getNumberOfFloors() { return numberOfFloors; }

    public int getNumberOfRows() { return numberOfRows; }

    public int getNumberOfPlaces() { return numberOfPlaces; }

    public int getNumberOfOpenSpots(){ return numberOfOpenSpots; }

    public void createNewTicksThread(){ ticks=new Thread(new TickThread(this)); }

    public boolean isHundredEnabled(){ return hundredEnabled; }

    public void setSteps(){ steps=getMinutes() + (getHours() * 60) + (getDays() * 1440); }

    public CarQueue getEntrancePassQueue(){ return entrancePassQueue; }

    public CarQueue getEntranceCarQueue(){ return entranceCarQueue; }

    public void resetDay( ){ day -= 7; }

    public void resetMinute(){ minute-= 60; }

    public void resetHour(){ hour -= 24; }

    public void incrementHour(){ hour++; }

    public void incrementMinute(){ minute++; }

    public void incrementDay(){ day++; }

    public boolean isDayTickEnabledEnabled(){return dayTickEnabled;}

    public boolean isHourEnabled(){return hourEnabled;}

    public int getTotalSpots(){ return numberOfFloors*numberOfPlaces*numberOfRows; }

    public void resetIgnoreQueue(){
        paidIgnoringCars.clear();
        passIgnoringCars.clear();
    }
    public int getPreviousTotalIgnoring(){
        return previousTotalIgnoring;
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
       else if(car instanceof AdHocCar){
            paidCars.remove(car);
        } else if(car instanceof RessCarLocation) {

        } else{
            reservedCars.remove(car);
        }

        numberOfOpenSpots++;

        return car;
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
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor <2; floor++) {
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
        for (int floor = 2; floor <3; floor++) {
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

    //self made one step (is one minute)
    public void oneStep(){
        if(!ticks.isAlive()) {
            dayTickEnabled=false;
            hundredEnabled=false;
            hourEnabled=false;
            ticks.start();
        }
    }
    //selfmade tick hundred steps
    public void hundredSteps(){
        if(!ticks.isAlive()) {
            dayTickEnabled=false;
            hundredEnabled=true;
            hourEnabled=false;
            ticks.start();
        }
    }
    //selfmade- tick one day
    public void oneDay(){
        if(!ticks.isAlive()) {
            hundredEnabled=false;
            dayTickEnabled=true;
            hourEnabled=false;
            ticks.start();

        }
    }
    //selfmade- tick one hour
    public void oneHour(){
        if(!ticks.isAlive()) {
            hundredEnabled=false;
            dayTickEnabled=false;
            hourEnabled=true;
            ticks.start();

        }
    }
    public void carsArriving(){
        int numberOfCars=getNumberOfCars(weekDayArrivals, weekendArrivals);
        addArrivingCars(numberOfCars, AD_HOC);
        numberOfCars=getNumberOfCars(weekDayPassArrivals, weekendPassArrivals);
        addArrivingCars(numberOfCars, PASS);
        numberOfCars=getNumberOfCars(weekDayRessArrivals, weekendRessArrivals);
        addArrivingCars(numberOfCars, RESS);
    }
    public void carsEntering(CarQueue queue){
        int i=0;
        // Remove car from the front of the queue and assign to a parking space.
        //let the reservation cars change first

        while (queue.carsInQueue()>0 &&
                getNumberOfOpenSpots()>0 &&
                i<enterSpeed ){

            Car car = queue.removeCar();

            Location freeLocation;


            if((getAmountReservedSpots()+getAmountReservedCars()+getAmountPaidCars()
                  )<(
                    getNumberOfPlaces()*8)&&(car instanceof AdHocCar || car instanceof RessCarLocation)){

                freeLocation=getFirstFreeLocation();
                if(car instanceof AdHocCar){
                    paidCars.add(car);


                }
                if(car instanceof RessCarLocation){

                    reservedCarSpots.add(car);

                }
                setCarAt(freeLocation, car);

            }
            if(getAmountSubscribedCars()<(getNumberOfPlaces()*4)&&car instanceof ParkingPassCar) {

                freeLocation = getFirstFreePassLocation();
                subscribedCars.add(car);
                setCarAt(freeLocation, car);

            }
            i++;

        }
    }
    public void carsReadyToLeave(){
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

    public void carsPaying(){
        // Let cars pay.
        int i=0;
        while (paymentCarQueue.carsInQueue()>0 && i < paymentSpeed){
            Car car = paymentCarQueue.removeCar();
            calculatePrice(car);
            carLeavesSpot(car);
            i++;
        }
    }

    public void carsLeaving(){
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
        int averageNumberOfCarsPerHour;
        //weekday
        if(day<4){
            //thursday is bargainnight(koopavond), in groningen from 18.00 to 21.00 selfmade
            if(day==3) {
                if(hour==18 || hour==19 || hour==20 || hour==21){
                    averageNumberOfCarsPerHour =weekDay*2;
                } else {
                    averageNumberOfCarsPerHour = weekDay;
                }

            } else {
                averageNumberOfCarsPerHour = weekDay;
            }
            //weekend
        } else {
            //more becauseoftheater shows-other theaters(oosterpoort=example) have 2 shows per day
            // Whole theater can hold 1000. Theter is fully booked according to case
            //most of the time around 8 (oosterpoort)

            if(hour==20){
                averageNumberOfCarsPerHour=weekend+(1000-
                        (weekendArrivals+weekendPassArrivals+weekendRessArrivals))/3;
                //(1000-total per hour in weekend()/)3

            } else {
                averageNumberOfCarsPerHour = weekend;
            }
        }

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
                    if(getPayingArrivingCars()<=queueLimit){
                        entranceCarQueue.addCar(new AdHocCar());
                    } else {

                        paidIgnoringCars.add(new AdHocCar());
                    }
                }
                break;
            case PASS:
                for (int i = 0; i < numberOfCars; i++) {
                    if(getSubscribtionArrivingCars()<=queueLimit){
                        entrancePassQueue.addCar(new ParkingPassCar());
                    } else {

                        passIgnoringCars.add(new ParkingPassCar());
                    }
                }
                break;
            case RESS:
                for (int i = 0; i < numberOfCars; i++) {
                    if(getSubscribtionArrivingCars()<=queueLimit){
                        entrancePassQueue.addCar(new RessCarLocation());
                    } else {

                        passIgnoringCars.add(new ParkingRessCar());
                    }
                }
                break;
        }
    }
    private void carLeavesSpot(Car car){
        Location location=car.getLocation();
        removeCarAt(location);
        if(car instanceof RessCarLocation){
            reservedCarSpots.remove(car);
           Car parkingRessCar=new ParkingRessCar();
            setCarAt(location,parkingRessCar);
            reservedCars.add(parkingRessCar);
        } else {
            exitCarQueue.addCar(car);
        }
    }
    //selfmade-get the number of cars that are in the arrivingqueue and don't have a subscription
    public int getPayingArrivingCars(){
        return entranceCarQueue.carsInQueue();
    }
    //selfmade-get the number of cars that are  in the arrivingqueue  have a subscription
    public int getSubscribtionArrivingCars(){
        System.out.println(entrancePassQueue.carsInQueue());
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
    //selfmade-get the amount of cars that had reserved in the garage
    public int getAmountReservedCars(){
        return reservedCars.size();
    }
    //selfmade-get the amount of reserved spots in the garage
    public int getAmountReservedSpots(){


            return reservedCarSpots.size();


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
        if(car instanceof ParkingRessCar){
            actualDailyRevenue+=(((double)car.getStayMinutes()/(double)60)*pricePerHour)+reservationPrice;
        } else {
            actualDailyRevenue+=((double)car.getStayMinutes()/(double)60)*pricePerHour;
        }

    }
    //selfmade-Calculates the excpected price per customer that is in the garage and has not paid yet
    public void calculateExpectedRevenue(Car car){
        if(car instanceof ParkingRessCar){
            expectedRevenue += (((double) car.getStayMinutes() / (double) 60) * pricePerHour)+reservationPrice;
        } else {
            expectedRevenue += ((double) car.getStayMinutes() / (double) 60) * pricePerHour;
        }
    }
    //selfmade
    public int getPaidQueueIgnorers(){
        return paidIgnoringCars.size();
    }
    
    public int getPassQueueIgnorers(){
        return passIgnoringCars.size();
    }
    //selfmade- return the revenue that is expected to be earned, from the customers that are still parked
    public double getExpectedRevenue(){
        expectedRevenue=0;
        for(Car car: paidCars){
            calculateExpectedRevenue(car);
        }
        for(Car car: reservedCars){
            calculateExpectedRevenue(car);
        }
        return expectedRevenue;
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
    public void updateRevenuesAndResetIgnoreQueue(){
        if(getHours()==0 && getMinutes()==0) {
            previousDailyRevenue = actualDailyRevenue;
            actualDailyRevenue = 0;
            previousTotalIgnoring=paidIgnoringCars.size()+passIgnoringCars.size();
            resetIgnoreQueue();
        }
    }
    //selfmade-Quits the simulation B.S.
    public void quit()
    {
        System.exit(0);
    }
    public String revenueMessage()
    {
        if(actualDailyRevenue >= 1000 && actualDailyRevenue < 2000)
        {
            return "€1.000 bereikt";
        }
        else if(actualDailyRevenue >= 2000 && actualDailyRevenue < 3000)
        {
            return "€2.000 bereikt";
        }
        else if(actualDailyRevenue >= 3000 && actualDailyRevenue < 4000)
        {
            return "€3.000 bereikt";
        }
        else if(actualDailyRevenue >= 4000 && actualDailyRevenue < 5000)
        {
            return "€4.000 bereikt";
        }
        else if(actualDailyRevenue >= 5000 && actualDailyRevenue < 6000)
        {
            return "€5.000 bereikt";
        }
        else if(actualDailyRevenue >= 6000 && actualDailyRevenue < 7000)
        {
            return "€6.000 bereikt";
        }
        else if(actualDailyRevenue >= 7000 && actualDailyRevenue < 8000)
        {
            return "€7.000 bereikt";
        }
        else if(actualDailyRevenue >= 8000 && actualDailyRevenue < 9000)
        {
            return "€8.000 bereikt";
        }
        else if(actualDailyRevenue >= 9000 && actualDailyRevenue < 10000)
        {
            return "€9.000 bereikt";
        }  else if(actualDailyRevenue >= 10000 && actualDailyRevenue < 11000)
        {
            return "€10.000 bereikt";
        }
        return "";
    }

    public String endDayMessage()
    {
        if (hour == 0 && minute == 0 && getSteps() != 0) {
            return "Dag voorbij";
        }
        return "";
    }


}
