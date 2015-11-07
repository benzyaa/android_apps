package com.moedojo.colorclick.apptimer;

import android.os.CountDownTimer;

/**
 * This class uses the native CountDownTimer to 
 * create a timer which could be paused and then
 * started again from the previous point. You can 
 * provide implementation for onTick() and onFinish()
 * then use it in your projects.
 */
public abstract class AppCountDownTimerPausable extends AppTimer{
   
    CountDownTimer countDownTimer = null;
    public AppCountDownTimerPausable(long millisInFuture, long countDownInterval) {
        super();
        this.millisInFuture = millisInFuture;
        this.countInterval = countDownInterval;
        this.millisRemaining = this.millisInFuture;
    }
    private void createCountDownTimer(){
        countDownTimer = new CountDownTimer(millisRemaining,countInterval) {

            @Override
            public void onTick(long millisUntilFinished) {
                millisRemaining = millisUntilFinished;
                AppCountDownTimerPausable.this.onTick(millisUntilFinished);

            }

            @Override
            public void onFinish() {
                AppCountDownTimerPausable.this.onFinish();

            }
        };
    }
    /**
     * Callback fired on regular interval.
     * 
     * @param millisUntilFinished The amount of time until finished. 
     */
    public abstract void onTick(long millisUntilFinished);
    /**
     * Callback fired when the time is up. 
     */
    public abstract void onFinish();
    /**
     * Cancel the countdown.
     */
    public final void cancel(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        this.millisRemaining = 0;
    }
    /**
     * Start or Resume the countdown. 
     * @return CountDownTimerPausable current instance
     */
    public synchronized final AppCountDownTimerPausable start(){
        if(isPaused){
            createCountDownTimer();
            countDownTimer.start();
            isPaused = false;
        }
        return this;
    }
    /**
     * Pauses the CountDownTimerPausable, so it could be resumed(start)
     * later from the same point where it was paused.
     */
    public void pause()throws IllegalStateException{
        if(isPaused==false){
            countDownTimer.cancel();
        } else{
            throw new IllegalStateException("CountDownTimerPausable is already in pause state, start counter before pausing it.");
        }
        isPaused = true;
    }
    public boolean isPaused() {
        return isPaused;
    }
}