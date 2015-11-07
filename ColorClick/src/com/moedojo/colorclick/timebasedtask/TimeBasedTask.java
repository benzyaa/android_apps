package com.moedojo.colorclick.timebasedtask;

import com.moedojo.colorclick.BaseActivity;

import android.widget.TextSwitcher;

public interface TimeBasedTask {
	public BaseActivity getBaseActivity();
	public void onStart();
	public void onTick();
	public void onStop();
	public void onFinish();
	public TextSwitcher getTimeDisplayTextSwitcher();
	public void setTimeBasedTaskExecuter(TimeBasedTaskExecuter timeBasedTaskExecuter);
}
