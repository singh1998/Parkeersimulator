package parkeersimulator.model;


public class TickThread implements Runnable{
    private Model model;
    public TickThread(Model model){
        this.model=model;
    }



    @Override
    public void run() {
        if(model.isHundredEnabled()){
            for (int i = 0; i < 100; i++) {
                tick();
            }
        } else {
            tick();
        }
        model.createNewTicksThread();
    }
    private void tick() {
        advanceTime();
        handleExit();
        //Pause.
        //
        try {
            Thread.sleep(model.getTickPause());
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