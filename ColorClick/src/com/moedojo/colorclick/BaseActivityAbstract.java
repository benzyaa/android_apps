package com.moedojo.colorclick;


import com.moedojo.colorclick.application.ApplicationInterface;
import com.moedojo.colorclick.displayTask.OnBackPressDialogDisplayTask;
import com.moedojo.colorclick.displayTask.OnBackPressDialogDisplayTaskImpl;
import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public abstract class BaseActivityAbstract extends Activity implements BaseActivity{
	
	protected TimeBasedTaskExecuter timeBasedTaskExecuter = null;
	private boolean isAnyDialogIsShow;
	private OnBackPressDialogDisplayTask onBackPressDialogDisplayTask;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.isAnyDialogIsShow = true;
		this.doInitialTask();
		this.onBackPressDialogDisplayTask = new OnBackPressDialogDisplayTaskImpl(this);
	}
	
	public ApplicationInterface getMainApplication(){
		return (ApplicationInterface)this.getApplication();
	}
	
	protected void terminateActivity(){
		this.finish();
	}

	public boolean isAnyDialogIsShow() {
		return isAnyDialogIsShow;
	}

	public void setAnyDialogIsShow(boolean isAnyDialogIsShow) {
		this.isAnyDialogIsShow = isAnyDialogIsShow;
	}

	@Override
	public void onBackPressed() {
		this.onBackPressDialogDisplayTask.showDialog();
		// TODO Auto-generated method stub
		//super.onBackPressed();
	/*	Log.d("moedojo_colorclick", "Back button is pressed - this.isAnyDialogIsShow. : "+this.isAnyDialogIsShow);
		if(!this.isAnyDialogIsShow){
			super.onBackPressed();
		}*/
		
		Log.d("moedojo_colorclick", "Back button is pressed.");
		
	}

	@Override
	public void displayQuitAppAlertDialog() {
		final BaseActivityAbstract currentActivity = this;
		new AlertDialog.Builder(this)
        .setMessage("Are you sure you want to exit?")
        .setCancelable(false)
        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	currentActivity.finish();
            	if(currentActivity.timeBasedTaskExecuter !=null){
            		currentActivity.timeBasedTaskExecuter.doStop();
            	}
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            	if(currentActivity.timeBasedTaskExecuter !=null){
            		currentActivity.timeBasedTaskExecuter.doResume();
            	}
            }
        })
        .show();
	}

	public TimeBasedTaskExecuter getTimeBasedTaskExecuter() {
		return timeBasedTaskExecuter;
	}
	
}