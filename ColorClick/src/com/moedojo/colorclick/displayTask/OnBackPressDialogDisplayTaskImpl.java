package com.moedojo.colorclick.displayTask;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.moedojo.colorclick.BaseActivity;
import com.moedojo.colorclick.BaseActivityAbstract;
import com.moedojo.colorclick.R;

public class OnBackPressDialogDisplayTaskImpl extends DisplayTaskAbstract implements OnBackPressDialogDisplayTask{

	private Dialog dialog;
	private Button stopButton;
	private Button quitButton;
	private Button returnButton;
	public OnBackPressDialogDisplayTaskImpl(BaseActivity baseActivity) {
		super(baseActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doInitialTask() {
		// TODO Auto-generated method stub
		this.generateDialogContent();
		this.setListener();
	}
	
	private void generateDialogContent(){
		this.dialog = new Dialog((BaseActivityAbstract)this.getActivity());
		this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.dialog.setContentView(R.layout.onbackpress_button_menu_dialog_layout);
		this.stopButton = (Button) this.dialog.findViewById(R.id.stop_button);
		this.quitButton = (Button) this.dialog.findViewById(R.id.quit_button);
		this.returnButton = (Button) this.dialog.findViewById(R.id.return_button);
	}
	
	private void setListener(){
		final OnBackPressDialogDisplayTaskImpl currentOnBackPressDialogDisplayTaskImpl = this;
		this.stopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentOnBackPressDialogDisplayTaskImpl.getActivity().getTimeBasedTaskExecuter().doStop();
				currentOnBackPressDialogDisplayTaskImpl.dialog.dismiss();
			}
		});
		this.quitButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					currentOnBackPressDialogDisplayTaskImpl.getActivity().displayQuitAppAlertDialog();
					currentOnBackPressDialogDisplayTaskImpl.dialog.dismiss();
				}
			});
		this.returnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentOnBackPressDialogDisplayTaskImpl.dialog.dismiss();
				currentOnBackPressDialogDisplayTaskImpl.getActivity().getTimeBasedTaskExecuter().doResume();
			}
		});
	}

	@Override
	public void showDialog() {
		this.dialog.show();
		this.getActivity().getTimeBasedTaskExecuter().doPause();
	}
}