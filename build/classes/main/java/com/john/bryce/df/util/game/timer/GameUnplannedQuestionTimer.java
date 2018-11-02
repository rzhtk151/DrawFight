package com.john.bryce.df.util.game.timer;

import com.john.bryce.df.util.game.callback.GameTimerCallbacks;

public class GameUnplannedQuestionTimer extends GameTimer {

	public GameUnplannedQuestionTimer(GameTimerCallbacks callbacks) {
		super(callbacks);
	}

	@Override
	public void run() {
		super.run();
		try {
			sleep(this.wait);
			this.callbacks.onNextQuestion();
		} catch (InterruptedException e) {
		}
	}

}
