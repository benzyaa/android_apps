package com.moedojo.colorclick.displayTask;

import android.app.Dialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.moedojo.colorclick.BaseActivity;
import com.moedojo.colorclick.BaseActivityAbstract;
import com.moedojo.colorclick.R;
import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;

public class SummaryDialogDisplayTaskImpl extends DisplayTaskAbstract implements SummaryDialogDisplayTask {

	private Dialog dialog;
	private Button tryAgainDialogButton;
	private Button quitDialogButton;
	private TimeBasedTaskExecuter timeBasedTaskExecuter;
	private TextSwitcher earnedPointTextSwitcher;
	public SummaryDialogDisplayTaskImpl(BaseActivity baseActivity,TimeBasedTaskExecuter timeBasedTaskExecuter,int earnedPoints) {
		super(baseActivity);
		this.timeBasedTaskExecuter = timeBasedTaskExecuter;
		this.setEarnedPointText(earnedPoints);
	}

	@Override
	public void doInitialTask() {
		this.generateDialogContent();
		this.setListener();
	}
	
	public void showDialog(){
		this.dialog.show();
	}
	
	private void generateDialogContent(){
		this.dialog = new Dialog((BaseActivityAbstract)this.getActivity());
		this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.dialog.setContentView(R.layout.summary_result_dialog_layout);
		this.dialog.setCancelable(false);
		this.tryAgainDialogButton = (Button)dialog.findViewById(R.id.summary_dialog_retry_button);
		this.quitDialogButton = (Button)dialog.findViewById(R.id.summary_dialog_quit_button);
		
		final SummaryDialogDisplayTaskImpl currentSummaryDialogDisplayTaskImpl = this;
		this.earnedPointTextSwitcher = (TextSwitcher) this.dialog.findViewById(R.id.summary_point_textswitcher);
		final int textColorInt = Color.parseColor("#41A62A");
		this.earnedPointTextSwitcher.setFactory(new ViewFactory() {
			public View makeView() {
				TextView textView = new TextView(currentSummaryDialogDisplayTaskImpl.getActivity().getApplicationContext());
				textView.setGravity(Gravity.LEFT);
				textView.setTextColor(textColorInt);
				return textView;
			}
		});
		
	}
	
	private void setEarnedPointText(int earnedPoints){
		String pointText = this.getActivity().getApplicationContext().getResources().getString(R.string.earned_points_text);
		pointText = String.format(pointText, String.valueOf(earnedPoints));
		this.earnedPointTextSwitcher.setText(pointText);
	}
	
	private void setListener(){
		final SummaryDialogDisplayTaskImpl currentSummaryDialogDisplayTaskImpl = this;
		this.tryAgainDialogButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentSummaryDialogDisplayTaskImpl.timeBasedTaskExecuter.restart();
				currentSummaryDialogDisplayTaskImpl.dialog.dismiss();
			}
		});
		this.quitDialogButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				currentSummaryDialogDisplayTaskImpl.getActivity().displayQuitAppAlertDialog();
			}
		});
	}
}