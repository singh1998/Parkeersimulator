package parkeersimulator.model;


public class TickThread implements Runnable{
    private Model model;
    private int tickPause = 100;
    public TickThread(Model model){
        this.model=model;
    }


    @Override
    public void run() {

        if(model.isHundredEnabled()){
          tick_100();
        } else {
            tick_1();
        }
        model.createNewTicksThread();
    }
    private void tickSoMany(int limit){
        for (int i = 0; i < limit; i++) {
            tick_1();
        }
    }
    public void tick_100(){
        tickSoMany(100);
    }
    private void tick_1() {
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
        model.updateRevenues();
        updateViews();

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