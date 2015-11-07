package com.moedojo.colorclick.displayTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
import com.moedojo.colorclick.BaseActivity;
import com.moedojo.colorclick.R;
import com.moedojo.colorclick.model.ColorM;
import com.moedojo.colorclick.timebasedtask.TimeBasedTask;
import com.moedojo.colorclick.timebasedtask.TimeBasedTaskExecuter;
import com.moedojo.colorclick.ui.ColorButtonImpl;
import com.moedojo.colorclick.util.ScreenUtil;

public class GameDisplayTaskImpl extends DisplayTaskAbstract implements GameDisplayTask,TimeBasedTask{

	public GameDisplayTaskImpl(BaseActivity baseActivity) {
		super(baseActivity);
	}
	public static final String adsUnitId = "";
	private List<ColorM> colorList;
	private TextSwitcher currentColorNameTextSwitcher;
	private TextSwitcher currentScoreTextSwitcher;
	private TextSwitcher timeCounterTextSwitcher;
	private ColorM currentPresentQuestionColorM;
	private TimeBasedTaskExecuter timeBasedTaskExecuter;
	private int currentScore;
	
	@Override 
	public void doInitialTask() {
		this.colorList = this.getActivity().getMainApplication().getColorList();
		this.getActivity().setContentView(R.layout.activity_main);
		this.doInitialAds();
		this.setTextSwitcher();
		this.currentScore = 0;
		this.displayUpdateScore();
	}
	
	public void setTextSwitcher(){
		final GameDisplayTaskImpl currentGameDisplayTaskImpl = this; 
		final int textColor = Color.parseColor("#32CD32");
		this.currentColorNameTextSwitcher = (TextSwitcher) this.getActivity().findViewById(R.id.color_name_text_switcher);
		this.currentColorNameTextSwitcher.setFactory(new ViewFactory() {
			public View makeView() {
				TextView textView = new TextView(currentGameDisplayTaskImpl.getActivity().getApplicationContext());
				textView.setGravity(Gravity.CENTER);
				textView.setTextColor(textColor);
				textView.setTextSize(19);
				return textView;
			}
		});
		this.currentScoreTextSwitcher = (TextSwitcher) this.getActivity().findViewById(R.id.update_score_text_switcher);
		this.currentScoreTextSwitcher.setFactory(new ViewFactory() {
			public View makeView() {
				TextView textView = new TextView(currentGameDisplayTaskImpl.getActivity().getApplicationContext());
				textView.setGravity(Gravity.LEFT);
				textView.setTextColor(textColor);
				textView.setTextSize(15);
				return textView;
			}
		});
		this.timeCounterTextSwitcher = (TextSwitcher) this.getActivity().findViewById(R.id.time_counter_text_switcher);
		this.timeCounterTextSwitcher.setFactory(new ViewFactory() {
			public View makeView() {
				TextView textView = new TextView(currentGameDisplayTaskImpl.getActivity().getApplicationContext());
				textView.setGravity(Gravity.CENTER);
				textView.setTextColor(textColor);
				textView.setTextSize(19);
				return textView;
			}
		});
	}

	@Override
	public void generateQuestion(ColorM selectedColorM) {
		LinearLayout colorButtonLayout = (LinearLayout)this.getActivity().findViewById(R.id.color_button_layout);
		colorButtonLayout.removeAllViews();
		Log.d("moedojo - colorClick", "generateQuestion()");
		List<ColorM> colorListForShuffle = this.getShuffleColorList();
		
		LinearLayout innerColorButtonLinearLayout = (LinearLayout) ScreenUtil.inflateLayout(R.layout.color_item_layout, null, this.getActivity().getApplicationContext());
		for(int i=0;i<colorListForShuffle.size();i++){
			if(i%4 == 0){
				innerColorButtonLinearLayout = new LinearLayout(this.getActivity().getApplicationContext());
				colorButtonLayout.addView(innerColorButtonLinearLayout);
			}
			ColorM colorM = colorListForShuffle.get(i);
			ColorButtonImpl colorButton = new ColorButtonImpl(colorM, this, this.getActivity());
			innerColorButtonLinearLayout.addView(colorButton);
		}
		if(selectedColorM != null){
			this.checkForUpdateScore(selectedColorM);
		}
		this.displayPresentQuestion();
	}
	
	private List<ColorM> getShuffleColorList(){
		List<ColorM> colorListForShuffle = new ArrayList<ColorM>(this.colorList);
		Collections.shuffle(colorListForShuffle);
		return colorListForShuffle;
	}
	
	public void displayPresentQuestion(){
		List<ColorM> colorListForShuffle = this.getShuffleColorList();
		ColorM presentQuestionColorM = colorListForShuffle.get(0);
		String colorName = presentQuestionColorM.getColorName();
		this.currentColorNameTextSwitcher.setText(colorName);
		this.setCurrentPresentQuestionColorM(presentQuestionColorM);
	}
	
	public void checkForUpdateScore(ColorM selectedColorM){
		int plusValue = this.checkCorrectColor(selectedColorM, this.getCurrentPresentQuestionColorM()) ? 1 : 0;
		this.currentScore += plusValue;
		this.displayUpdateScore();
	}
	
	public void displayUpdateScore(){
		String pointText = this.getActivity().getApplicationContext().getResources().getString(R.string.points_text);
		pointText = String.format(pointText, String.valueOf(currentScore));
		this.currentScoreTextSwitcher.setText(pointText);
	}
	
	public boolean checkCorrectColor(ColorM selectedColorM,ColorM rightColorM){
		Log.d("moedojo - colorClick", "generateQuestion()");
		 return rightColorM.getColorName().equals(selectedColorM.getColorName());
	}
	

	protected ColorM getCurrentPresentQuestionColorM() {
		return currentPresentQuestionColorM;
	}

	protected void setCurrentPresentQuestionColorM(
			ColorM currentPresentQuestionColorM) {
		this.currentPresentQuestionColorM = currentPresentQuestionColorM;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		this.currentScore = 0;
		this.displayUpdateScore();
		this.generateQuestion(null);
	}

	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		this.onFinish();
	}
	
	@Override
	public void onFinish() {
		SummaryDialogDisplayTask summaryDialogDisplayTask = new SummaryDialogDisplayTaskImpl(this.getActivity(), this.timeBasedTaskExecuter, this.currentScore);
		summaryDialogDisplayTask.showDialog();
		
	}

	@Override
	public TextSwitcher getTimeDisplayTextSwitcher() {
		// TODO Auto-generated method stub
		return this.timeCounterTextSwitcher;
	}

	@Override
	public void setTimeBasedTaskExecuter(
			TimeBasedTaskExecuter timeBasedTaskExecuter) {
		this.timeBasedTaskExecuter = timeBasedTaskExecuter;
	}

	@Override
	public BaseActivity getBaseActivity() {
		
		return this.getActivity();
	}
	

	private void doInitialAds(){
		/*AdView adView = new AdView(this.getActivity().getApplicationContext());
		adView.setAdUnitId(adsUnitId);
		adView.setAdSize(AdSize.SMART_BANNER);
		AdRequest adRequest = new AdRequest.Builder().build();
		LinearLayout linearLayout = (LinearLayout)this.getActivity().findViewById(R.id.ad_layout);
		linearLayout.addView(adView);
		adView.loadAd(adRequest);*/
	}
}