package com.moedojo.colorclick;

import com.google.analytics.tracking.android.EasyTracker;
import com.moedojo.colorclick.displayTask.GameDisplayTask;
import com.moedojo.colorclick.displayTask.GameDisplayTaskImpl;
import com.moedojo.colorclick.displayTask.WelcomeDialogDisplayTask;
import com.moedojo.colorclick.displayTask.WelcomeDialogDisplayTaskImpl;
import com.moedojo.colorclick.timebasedtask.TimeBasedTask;
import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;


public class MainActivity extends BaseActivityAbstract{
	private static final long TIME_INTERVAL = 3*60000l;
	private GameDisplayTask displayTask = null;
	private WelcomeDialogDisplayTask welcomeDialogDisplayTask = null;
	@Override
	public void doInitialTask() {
		this.displayTask = new GameDisplayTaskImpl(this);
		this.timeBasedTaskExecuter = new TimeBasedTaskExecuter(TIME_INTERVAL, (TimeBasedTask)this.displayTask);
		welcomeDialogDisplayTask = new WelcomeDialogDisplayTaskImpl(this,this.timeBasedTaskExecuter);
		welcomeDialogDisplayTask.showDialog();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		 EasyTracker.getInstance(this).activityStop(this);
	}
}