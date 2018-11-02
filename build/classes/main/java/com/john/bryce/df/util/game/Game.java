package com.john.bryce.df.util.game;

import java.util.List;

import com.john.bryce.df.data.DbHandler;
import com.john.bryce.df.data.entity.Question;
import com.john.bryce.df.data.entity.User;
import com.john.bryce.df.util.game.timer.GamePlannedStartTimer;

public final class Game /*implements GamePlannedStartTimer.Callbacks*/ {

    private static final int GAME_START_DELAY = 300_000; // 5 Minutes;

    private boolean usersAllowed = true;
    private boolean gameStarted = false;
    private boolean timePassed = false;
    private User drawer;
    private int usersCount;
    private long gameStartTime;
    private long gameEndTime;
    private List<Question> questions;
    private int questionIndex = 0;
    private GamePlannedStartTimer gameTimer;

    public Game() {
        usersCount = 1;
        this.gameStartTime = System.currentTimeMillis() + GAME_START_DELAY;
        resetTimer(this.gameStartTime);
    }

    public User getDrawer() {
        return drawer;
    }

    public void setDrawer(User drawer) {
        this.drawer = drawer;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void incrementUsersCount() {
        if (this.usersAllowed && this.usersCount < 10) {
            this.usersCount++;
            if (this.timePassed && this.usersCount >= 2) {
                gameStartLate();
            }
        }
        if (this.usersCount == 10) {
            gameStartEarly();
        }
    }

    public long getGameStartTime() {
        return gameStartTime;
    }

    public long getGameEndTime() {
        return gameEndTime;
    }

    public boolean gameStarted() {
        return gameStarted;
    }

    //@Override
    public void onTenSecondsLeft() {
        if (this.usersCount >= 2) {
            this.usersAllowed = false;
            this.questions = DbHandler.getInstance().getQuestionDao().getRandom(this.usersCount);
        }
    }

    //@Override
    public void onGameStarted() {
        if (this.usersAllowed && this.usersCount >= 2) {
            gameStartLate();
        } else if (!this.usersAllowed) {
            this.gameStarted = true;
        }
        this.timePassed = true;
    }

    private void resetTimer(long time) {
        if (this.gameTimer != null) {
            this.gameTimer.interrupt();
        }
        //this.gameTimer = new GamePlannedStartTimer(this);
        this.gameTimer.setWait(time);
        this.gameTimer.start();
    }

    private void stopTimer() {
        if (this.gameTimer != null) {
            this.gameTimer.interrupt();
            this.gameTimer = null;
        }
    }

    private void gameStartEarly() {
        stopTimer();
        gameStartUnplanned();
    }

    private void gameStartLate() {
        gameStartUnplanned();
    }

    private void gameStartUnplanned() {
        this.usersAllowed = false;
        this.gameStarted = true;
        this.questions = DbHandler.getInstance().getQuestionDao().getRandom(this.usersCount);
    }

    @Override
    public String toString() {
        return "Game [drawer=" + drawer + ", usersCount=" + usersCount + ", gameStartTime=" + gameStartTime
                + ", gameEndTime=" + gameEndTime + ", questions=" + questions + "]";
    }
}
