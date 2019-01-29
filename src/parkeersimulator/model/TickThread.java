package parkeersimulator.model;


public class TickThread implements Runnable{
    private Model model;
    private int tickPause = 100;
    private boolean pause=true;

    public TickThread(Model model){
        this.model=model;
    }


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
    private void tickSoMany(int limit,boolean pause){
        this.pause=pause;
        for (int i = 0; i < limit; i++) {
            tick_1();
        }
    }
    private void tick_100(){

        tickSoMany(100,true);
    }
    private void tickDay(){
        tickSoMany(1440,false);
    }
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
    private void tickHour(){
        tickSoMany(60,false);
    }
    private void advanceTime(){
        // Advance the time by one minute.

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
    private void handleEntrance(){
        model.carsArriving();
        model.carsEntering(model.getEntrancePassQueue());
        model.carsEntering(model.getEntranceCarQueue());
    }
    private void handleExit(){
        model.carsReadyToLeave();
        model.carsPaying();
        model.carsLeaving();
    }
    private void updateViews(){
        model.changeLocation();
        // Update the views.
        model.notifyViews();
    }

}