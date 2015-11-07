package com.moedojo.colorclick.displayTask;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.moedojo.colorclick.BaseActivity;
import com.moedojo.colorclick.BaseActivityAbstract;
import com.moedojo.colorclick.R;
import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;

public class WelcomeDialogDisplayTaskImpl extends DisplayTaskAbstract implements WelcomeDialogDisplayTask{

	private Dialog dialog;
	private Button dialogButton;
	private TimeBasedTaskExecuter timebasedTaskExecuter;
	public WelcomeDialogDisplayTaskImpl(BaseActivity baseActivity, TimeBasedTaskExecuter timebasedTaskExecuter) {
		super(baseActivity);
		this.timebasedTaskExecuter = timebasedTaskExecuter;
	}
	
	private void generateDialogContent(){
		this.dialog = new Dialog((BaseActivityAbstract)this.getActivity());
		this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.dialog.setContentView(R.layout.welcome_dialog_layout);
		this.dialog.setCancelable(false);
		this.dialogButton = (Button) dialog.findViewById(R.id.start_button); 
	}
	
	private void setListener(){
		final WelcomeDialogDisplayTaskImpl currentWelcomeDialogDisplayTaskImpl = this;
		this.dialogButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentWelcomeDialogDisplayTaskImpl.timebasedTaskExecuter.start();
				currentWelcomeDialogDisplayTaskImpl.dialog.dismiss();
				currentWelcomeDialogDisplayTaskImpl.getActivity().setAnyDialogIsShow(false);
				
			}
		});
		this.dialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				currentWelcomeDialogDisplayTaskImpl.getActivity().setAnyDialogIsShow(true);
				
			}
		});
	}

	@Override
	public void doInitialTask() {
		this.generateDialogContent();
		this.setListener();
	}

	@Override
	public void showDialog() {
		this.dialog.show();
	}
}
