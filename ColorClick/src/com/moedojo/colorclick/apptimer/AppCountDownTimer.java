package com.moedojo.colorclick.apptimer;


import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;


public class AppCountDownTimer extends AppCountDownTimerPausable {
	
	private TimeBasedTaskExecuter timeBasedTaskExecuter;
	
	public AppCountDownTimer(long millisInFuture, long countDownInterval,TimeBasedTaskExecuter timeBasedTaskExecuter) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		this.timeBasedTaskExecuter = timeBasedTaskExecuter;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		this.timeBasedTaskExecuter.tick(millisUntilFinished);
	}

	@Override
	public void onFinish() {
		this.timeBasedTaskExecuter.stop();
	}

	@Override
	public void pause() throws IllegalStateException {
		super.pause();
	}
}