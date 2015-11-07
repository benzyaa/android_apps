package com.moedojo.colorclick.timebasedtask;

import java.util.Calendar;
import java.util.Date;

import com.moedojo.colorclick.BaseActivity;
import com.moedojo.colorclick.apptimer.AppCountDownTimer;
import com.moedojo.colorclick.util.DateUtil;

import android.util.Log;
import android.widget.TextSwitcher;

public class TimeBasedTaskExecuter {
	private long timeLength;
	private TimeBasedTask timeBasedTask;
	private TextSwitcher timeCounterTextSwitcher;
	private AppCountDownTimer appCountdownTimer;
	
	public TimeBasedTaskExecuter(long timeLength, TimeBasedTask timeBasedTask) {
		this.timeLength = timeLength;
		this.timeBasedTask = timeBasedTask;
		this.timeBasedTask.setTimeBasedTaskExecuter(this);
		this.timeCounterTextSwitcher = this.timeBasedTask.getTimeDisplayTextSwitcher();
		this.initialCountdownTimer(this.timeLength);
	}
	
	private void initialCountdownTimer(long timeLength){
		this.appCountdownTimer = new AppCountDownTimer(timeLength,1,this);
	}
	
	public void restart(){
		this.initialCountdownTimer(timeLength);
		this.start();
	}
	
	public void start(){
		Log.d("moedojo_colorclick", "Time Start");
		this.appCountdownTimer.start();
		this.timeBasedTask.onStart();
	}
	
	public void tick(long millisUntilFinished){
		this.updateTimeCounterTextSwitcher(millisUntilFinished);
	}
	
	public void updateTimeCounterTextSwitcher(final long timeMills){
		if(this.timeCounterTextSwitcher == null) return;
		final TextSwitcher currentTimeCounterTextSwitcher = this.timeCounterTextSwitcher;
		final BaseActivity baseActivity = this.timeBasedTask.getBaseActivity();
		Runnable timerTextRunnable = new Runnable() {
			public void run() {
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(timeMills);
				Date currentCountDownDate = calendar.getTime();
				String dateStr = DateUtil.convertDateToString(currentCountDownDate, DateUtil.DEFAULT_QUIZ_DISPLAY_DATE_FORMAT);
				currentTimeCounterTextSwitcher.setText(dateStr);
			}
		};
		baseActivity.runOnUiThread(timerTextRunnable);
	}
	
	public void stop(){
		this.timeBasedTask.onStop();
	}
	
	public void doPause(){
		this.appCountdownTimer.pause();
	}
	
	public void doResume(){
		this.appCountdownTimer.start();
	}
	
	public void doStop(){
		this.stop();
	}
}
