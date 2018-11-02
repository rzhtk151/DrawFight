package com.john.bryce.df.util.game.timer;

import com.john.bryce.df.util.game.GameController;
import com.john.bryce.df.util.game.callback.GameTimerCallbacks;

public final class GamePlannedStartTimer extends GameTimer {

	public GamePlannedStartTimer(GameTimerCallbacks callbacks) {
		super(callbacks);
	}

	@Override
	public void run() {
		super.run();
		try {
			sleep(wait - GameController.SHORT_DELAY);
			final long curr1 = System.currentTimeMillis();
			this.callbacks.onTenSecondsLeft();
			final long curr2 = System.currentTimeMillis();
			sleep(GameController.SHORT_DELAY - (curr2 - curr1));
			this.callbacks.onGameStarted();
		} catch (InterruptedException e) {
		}
	}

}
