package com.john.bryce.df.util.game.timer;

import com.john.bryce.df.util.game.callback.GameTimerCallbacks;

public abstract class GameTimer extends Thread {
	
	protected long wait;
	protected final GameTimerCallbacks callbacks;
	
	public GameTimer(GameTimerCallbacks callbacks) {
		this.callbacks = callbacks;
	}
	
	public final void setWait(long wait) {
		this.wait = wait;
	}

	@Override
	public String toString() {
		return "GameTimer [wait=" + wait + "]";
	}
	
}
