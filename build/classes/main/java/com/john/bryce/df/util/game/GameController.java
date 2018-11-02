package com.john.bryce.df.util.game;

import java.util.List;

import com.john.bryce.df.data.DbHandler;
import com.john.bryce.df.data.entity.Question;
import com.john.bryce.df.data.entity.User;
import com.john.bryce.df.util.fcm.FcmSender;
import com.john.bryce.df.util.fcm.MessageType;
import com.john.bryce.df.util.fcm.message.FcmMessage;
import com.john.bryce.df.util.fcm.message.FcmQuestionMessage;
import com.john.bryce.df.util.game.callback.GameTimerCallbacks;
import com.john.bryce.df.util.game.timer.GamePlannedQuestionTimer;
import com.john.bryce.df.util.game.timer.GamePlannedStartTimer;
import com.john.bryce.df.util.game.timer.GameTimer;
import com.john.bryce.df.util.game.timer.GameUnplannedStartTimer;

public final class GameController implements GameTimerCallbacks {

	// Game settings
	private static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 10;

	// Timing constants
	private static final int GAME_START_DELAY = 300_000; // 5 Minutes
	public static final int INTERVAL_QUESTIONS = 30_000; // 30 Seconds
	public static final int SHORT_DELAY = 11_000; // 11 Seconds

	private boolean usersAllowed = true;
	private boolean timePassed = false;
	private String userDrawerId;
	private String userAnswerId;
	private int usersCount;
	private List<Question> questions;
	private int questionIndex;
	private GameTimer gameTimer;
	private long timeForGameToStart;
	
	private int pointsToDrawer = 0;
	private int pointsToAnswer = 0;

	public GameController() {
		this.questionIndex = 0;
		this.usersCount = 1;
		startPlannedTimer();
	}

	private void startPlannedTimer() {
		stopTimer();
		this.gameTimer = new GamePlannedStartTimer(this);
		this.timeForGameToStart = System.currentTimeMillis() + GAME_START_DELAY;
		this.gameTimer.setWait(this.timeForGameToStart);
		this.gameTimer.start();
	}

	public void setUserDrawerId(String userDrawerId) {
		this.userDrawerId = userDrawerId;
	}

	public void setUserAnswerId(String userAnswerId) {
		this.userAnswerId = userAnswerId;
		stopTimer();
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public boolean incrementUsersCount() {
		if (this.usersAllowed && this.usersCount < MAX_PLAYERS) {
			this.usersCount++;
			if (this.timePassed && this.usersCount >= MIN_PLAYERS) {
				startGameUnplanned();
			}
			return true;

		}
		return false;
	}

	public long getTimeForGameToStart() {
		if (this.timePassed) {
			return -1;
		}
		return this.timeForGameToStart;
	}

	@Override
	public void onTenSecondsLeft() {
		if (this.usersCount >= MIN_PLAYERS) {
			prepareGameAssets();
		} else {
			stopTimer();
			this.timePassed = true;
		}
	}

	private void prepareGameAssets() {
		this.usersAllowed = false;
		this.questions = DbHandler.getInstance().getQuestionDao().getRandom(this.usersCount);
		FcmSender.sendToAll(new FcmMessage(MessageType.GAME_READY));
	}

	private void stopTimer() {
		if (this.gameTimer != null) {
			this.gameTimer.interrupt();
			this.gameTimer = null;
		}
	}

	private void startGameUnplanned() {
		prepareGameAssets();
		startUnplannedTimer();
	}

	private void startUnplannedTimer() {
		stopTimer();
		this.gameTimer = new GameUnplannedStartTimer(this);
		this.gameTimer.setWait(System.currentTimeMillis() + SHORT_DELAY);
		this.gameTimer.start();
	}

	@Override
	public void onGameStarted() {
		startPlannedQuestionTimer();
	}

	private void startPlannedQuestionTimer() {
		stopTimer();
		this.gameTimer = new GamePlannedQuestionTimer(this);
		this.gameTimer.setWait(System.currentTimeMillis() + INTERVAL_QUESTIONS);
		this.gameTimer.start();
	}

	@Override
	public void onQuestionTimeout() {
		final FcmQuestionMessage fcmQuestionMessage = new FcmQuestionMessage();
		fcmQuestionMessage.setPointsToDrawer(pointsToDrawer);
		fcmQuestionMessage.setPointsToAnswer(pointsToAnswer);
		FcmSender.sendToAll(fcmQuestionMessage);
		this.pointsToDrawer = 0;
		this.pointsToAnswer = 0;
		startUnplannedQuestionTimer();
	}

	private void startUnplannedQuestionTimer() {
		stopTimer();
		this.gameTimer = new GameUnplannedStartTimer(this);
		this.gameTimer.setWait(System.currentTimeMillis() + SHORT_DELAY);
		this.gameTimer.start();
	}

	@Override
	public void onQuestionAnswered(int secondsTakenToAnswer) {
		if (userDrawerId != null) {
			this.pointsToDrawer = secondsTakenToAnswer;
			updateUserScore(userDrawerId, secondsTakenToAnswer);
		}
		this.pointsToAnswer = secondsTakenToAnswer;
		updateUserScore(userAnswerId, secondsTakenToAnswer);
		this.userDrawerId = null;
		this.userAnswerId = null;
		onQuestionTimeout();
	}

	private void updateUserScore(String id, int pointsToAdd) {
		final User user = DbHandler.getInstance().getUserDao().getById(id);
		user.setScore(user.getScore() + pointsToAdd);
		DbHandler.getInstance().getUserDao().update(user);
	}

	@Override
	public void onNextQuestion() {
		if (this.questionIndex < this.questions.size() - 1) {
			this.questionIndex++;
			onGameStarted();
		} else {
			DbHandler.getInstance().clear();
		}
	}

	@Override
	public String toString() {
		return "GameController [usersAllowed=" + usersAllowed + ", timePassed=" + timePassed + ", userDrawerId="
				+ userDrawerId + ", userAnswerId=" + userAnswerId + ", usersCount=" + usersCount + ", questions="
				+ questions + ", questionIndex=" + questionIndex + ", gameTimer=" + gameTimer + "]";
	}

}
