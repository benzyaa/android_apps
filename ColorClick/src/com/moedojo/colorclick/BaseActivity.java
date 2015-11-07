package com.moedojo.colorclick;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.moedojo.colorclick.application.ApplicationInterface;
import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;

public interface BaseActivity {
	public void doInitialTask();
	public ApplicationInterface getMainApplication();
	public View findViewById(int id);
	public Context getApplicationContext();
	public void setContentView(View view);
	public void setContentView(int layoutResID);
	public void runOnUiThread(Runnable action);
	public WindowManager getWindowManager();
	public void setAnyDialogIsShow(boolean isAnyDialogIsShow);
	public void displayQuitAppAlertDialog();
	public TimeBasedTaskExecuter getTimeBasedTaskExecuter();
	public Application getApplication();
}
