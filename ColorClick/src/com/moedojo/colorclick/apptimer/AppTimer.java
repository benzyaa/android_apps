package com.moedojo.colorclick.apptimer;

public abstract class AppTimer {
 		long millisInFuture = 0;
 		long countInterval = 0;
 		long millisRemaining =  0;
 		boolean isPaused = true;
	  public abstract void onTick(long millisUntilFinished);
	  public abstract void onFinish();
	  public abstract void cancel();
	  public abstract AppTimer start();
	  public abstract void pause();
	  public boolean isPaused() {
		return isPaused;
	  }
}
