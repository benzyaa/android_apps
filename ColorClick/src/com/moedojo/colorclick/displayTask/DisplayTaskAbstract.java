package com.moedojo.colorclick.displayTask;

import com.moedojo.colorclick.BaseActivity;


public abstract class DisplayTaskAbstract implements DisplayTask {
	private BaseActivity activity;
	
	public DisplayTaskAbstract(BaseActivity baseActivity){
		this.activity = baseActivity;
		this.doInitialTask();
	}
	
	public BaseActivity getActivity() {
		return activity;
	}
}