package com.john.bryce.df.util.game.timer;

import com.john.bryce.df.util.game.callback.GameTimerCallbacks;

public final class GameUnplannedStartTimer extends GameTimer {
	
	public GameUnplannedStartTimer(GameTimerCallbacks callbacks) {
		super(callbacks);
	}

	@Override
	public void run() {
		super.run();
		try {
			sleep(this.wait);
			this.callbacks.onGameStarted();
		} catch (InterruptedException e) {
		}
	}

}
