package com.moedojo.colorclick.application;

import java.util.ArrayList;
import java.util.List;

import com.moedojo.colorclick.R;
import com.moedojo.colorclick.model.ColorM;

import android.app.Application;
import android.content.res.TypedArray;

public class ApplicationImpl extends Application implements ApplicationInterface{

	private List<ColorM> colorList;
	
	@Override
	public void onCreate() {
		super.onCreate();
		this.initialColorList();
	}

	private void initialColorList(){
		this.colorList = new ArrayList<ColorM>();
		TypedArray colorTextArray = this.getResources().obtainTypedArray(R.array.color_text_arr);
		TypedArray colorRGBArray = this.getResources().obtainTypedArray(R.array.color_rgb_arr);
		int sizeForList = colorTextArray.length();
		for(int i=0;i<sizeForList;i++){
			String colorName = colorTextArray.getString(i);
			String colorRGBHex = colorRGBArray.getString(i);
			ColorM colorM = new ColorM();
			colorM.setColorName(colorName);
			colorM.setColorRGBHex(colorRGBHex);
			this.colorList.add(colorM);
		}
	}

	@Override
	public List<ColorM> getColorList() {
		return this.colorList;
	}
}