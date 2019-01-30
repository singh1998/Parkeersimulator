/**
 * This class represents the thread that can be run, to run a simulation of a parking garage
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.model;

/**
 * Constructor for objects of class TickThread
 */
public class TickThread implements Runnable{
    private Model model;
    private int tickPause = 100;
    private boolean pause=true;

    /**
     * Constructor for objects of class TickThread
     */
    public TickThread(Model model){
        this.model=model;
    }

    /**
     * This method runs the thread when this calss is put in a thread object
     * and the start method from this thread method is called
     */
    @Override
    public void run() {

        if(model.isHundredEnabled()){
          tick_100();
        }
        else if(model.isDayTickEnabledEnabled()) {
            tickDay();
        } else if(model.isHourEnabled()) {
            tickHour();
        } else {
            tick_1();
        }

        model.createNewTicksThread();
    }

    /**
     *This method  decides how many simulation steps must be run
     *
     * @param limit The amount of simulation steps
     * @param pause The delay in milliseconds between steps
     */
    private void tickSoMany(int limit,boolean pause){
        this.pause=pause;
        for (int i = 0; i < limit; i++) {
            tick_1();
        }
    }
    /**
     * This method runs the simulation with 100 steps
     */
    private void tick_100(){

        tickSoMany(100,true);
    }
    /**
     * This method runs the simulation with 1440 steps, which simulates a day in the simulation
     */
    private void tickDay(){
        tickSoMany(1440,false);
    }
    /**
     * This method runs the simulation with oneStep
     */
    private void tick_1() {
        if(!pause){
            tickPause=0;
        }
        advanceTime();
        handleExit();

        //Pause.
        //
        try {
            Thread.sleep(tickPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //

        handleEntrance();
        model.updateRevenuesAndResetIgnoreQueue();
        updateViews();

    }
    /**
     * This method runs the simulation with 60 steps, which simulates a hour in the simulation
     */
    private void tickHour(){
        tickSoMany(60,false);
    }
    private void advanceTime(){
        // Advance the time by one minute.
        model.carCheckTime();
        model.incrementMinute();
        model.setSteps();
        while (model.getMinutes() > 59) {
            model.resetMinute();
            model.incrementHour();

        }
        while (model.getHours() > 23) {
            model.resetHour();

            model.incrementDay();

        }
        while (model.getDays() > 6) {

            model.resetDay();
        }
    }

    /**
     * This method handles the entrance of our parking garage simulation
     */
    private void handleEntrance(){
        model.carsArriving();
        model.carsEntering(model.getEntrancePassQueue());
        model.carsEntering(model.getEntranceCarQueue());
    }
    /**
     * This method handles the exit of our parking garage simulation
     */
    private void handleExit(){
        model.carsReadyToLeave();
        model.carsPaying();
        model.carsLeaving();
    }

    /**
     * This method updates the views that the simulation model holds
     */
    private void updateViews(){
        model.changeLocation();
        // Update the views.
        model.notifyViews();
    }

}