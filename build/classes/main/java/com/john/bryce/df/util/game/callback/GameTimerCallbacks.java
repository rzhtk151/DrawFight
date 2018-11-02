package com.john.bryce.df.util.game.callback;

public interface GameTimerCallbacks {
	void onTenSecondsLeft();

	void onGameStarted();

	void onQuestionTimeout();

	void onNextQuestion();
	
	void onQuestionAnswered(int secondsTakenToAnswer);
}
