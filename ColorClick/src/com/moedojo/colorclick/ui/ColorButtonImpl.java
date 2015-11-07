package com.moedojo.colorclick.ui;

import com.moedojo.colorclick.BaseActivity;
import com.moedojo.colorclick.displayTask.GameDisplayTask;
import com.moedojo.colorclick.model.ColorM;
import com.moedojo.colorclick.util.ScreenUtil;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorButtonImpl extends Button implements ColorButton{
	private static final int WIDTH_IN_PERCENT = 22;
	private static final int HEIGHT_IN_PERCENT = 16;
	private ColorM colorM;
	private GameDisplayTask gameDisplayTask;
	private BaseActivity baseActivity;
	
	public ColorButtonImpl(ColorM colorM,GameDisplayTask gameDisplayTask,BaseActivity activity){
		super(activity.getApplicationContext());
		this.colorM = colorM;
		this.gameDisplayTask = gameDisplayTask;
		this.baseActivity = activity;
		this.setStyle();
		this.setListener();
		
	}
	
	@Override
	public void setStyle(){
		int colorInt = Color.parseColor(this.colorM.getColorRGBHex());
		Drawable backgroundDrawable = this.getDrawableBackground(colorInt);
		this.setBackground(backgroundDrawable);
		DisplayMetrics screenDisplayMetrics = ScreenUtil.getDisplayWindowMetric(this.baseActivity);
		int actualHeight = (HEIGHT_IN_PERCENT * screenDisplayMetrics.heightPixels)/100;
		int actualWidth = (WIDTH_IN_PERCENT * screenDisplayMetrics.widthPixels)/100;
		this.setWidth(actualWidth);
		this.setHeight(actualHeight);
		Log.d("moedojo - colorClick", "actual button width : "+actualWidth);
		Log.d("moedojo - colorClick", "actual button height : "+actualHeight);
		
		LinearLayout.LayoutParams colorButtonLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		colorButtonLayoutParams.topMargin=5;
		colorButtonLayoutParams.bottomMargin = 5;
		this.setLayoutParams(colorButtonLayoutParams);
		this.setShadowLayer(1.5f, -1, 1, Color.LTGRAY);
	}
	
	private GradientDrawable getDrawableBackground(int colorInt){
		GradientDrawable gd = new GradientDrawable();
        gd.setColor(colorInt); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(3, 0xFFFFFF);
        return gd;
	}
	

	@Override
	public void setListener(){
		final ColorButtonImpl currentColorButtonImpl = this;
		this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("moedojo - colorClick", "Color Name"+currentColorButtonImpl.colorM.getColorName());
				currentColorButtonImpl.gameDisplayTask.generateQuestion(colorM);
			}
		});
	}

	public ColorM getColorM() {
		return colorM;
	}
}