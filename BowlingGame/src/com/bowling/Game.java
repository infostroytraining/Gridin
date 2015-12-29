package com.bowling;

public class Game {

    private static final int STRIKE_POINTS = 10;
    private static final int COUNT_FRAMES = 10;
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
	rolls[currentRoll++] = pins;
    }

    public int score() {
	int score = 0;
	int frameIndex = 0;
	for (int frame = 0; frame < COUNT_FRAMES; ++frame) {
	    if (isStrike(frameIndex)) {
		score += STRIKE_POINTS + strikeBonus(frameIndex);
		++frameIndex;
	    } else if (isSpare(frameIndex)) {
		score += STRIKE_POINTS + spareBonus(frameIndex);
		frameIndex += 2;
	    } else {
		score += sumOfBallsInFrame(frameIndex);
		frameIndex += 2;
	    }
	}
	return score;
    }

    private boolean isStrike(int frameIndex) {
	return rolls[frameIndex] == STRIKE_POINTS;
    }

    private int sumOfBallsInFrame(int frameIndex) {
	return rolls[frameIndex] + rolls[frameIndex + 1];
    }

    private int strikeBonus(int frameIndex) {
	return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }

    private int spareBonus(int frameIndex) {
	return rolls[frameIndex + 2];
    }

    private boolean isSpare(int frameIndex) {
	return sumOfBallsInFrame(frameIndex) == STRIKE_POINTS;
    }
}
