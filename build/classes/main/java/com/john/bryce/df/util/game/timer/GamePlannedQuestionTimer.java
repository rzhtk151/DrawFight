package com.john.bryce.df.util.game.timer;

import com.john.bryce.df.util.game.GameController;
import com.john.bryce.df.util.game.callback.GameTimerCallbacks;

public final class GamePlannedQuestionTimer extends GameTimer {

	public GamePlannedQuestionTimer(GameTimerCallbacks callbacks) {
		super(callbacks);
	}

	@Override
	public void run() {
		super.run();
		try {
			sleep(this.wait);
			this.callbacks.onQuestionTimeout();
		} catch (InterruptedException e) {
			final int secondsTakenToAnswer = (int) ((GameController.INTERVAL_QUESTIONS - (this.wait - System.currentTimeMillis())) / 1000);
			this.callbacks.onQuestionAnswered(secondsTakenToAnswer);
		}
	}

}
