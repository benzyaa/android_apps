package com.moedojo.colorclick.util;

import com.moedojo.colorclick.BaseActivity;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScreenUtil {
	public static View inflateLayout(int resourceId,ViewGroup viewGroup,Context context){
		LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		return layoutInflater.inflate(resourceId, viewGroup);
	}
	
	public static int getPixelByDp(int dp,Context context){
		Resources resources = context.getResources();
		int px = (int) TypedValue.applyDimension(
		        TypedValue.COMPLEX_UNIT_DIP,
		        dp, 
		        resources.getDisplayMetrics()
		);
		return px;
	}
	
	public static DisplayMetrics getDisplayWindowMetric(BaseActivity activity){
		DisplayMetrics displaymetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		return displaymetrics;
	}
	
}