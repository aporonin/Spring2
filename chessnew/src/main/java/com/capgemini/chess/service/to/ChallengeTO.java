package com.capgemini.chess.service.to;

import java.util.Calendar;

public class ChallengeTO {

	private PlayerTO player;
	private PlayerTO opponent;
	private boolean challengeState;
	private Calendar dateChallenge;
	
	
	public ChallengeTO(PlayerTO player, PlayerTO opponent, boolean challengeState) {
		this.player = player;
		this.opponent=opponent;
		this.challengeState = challengeState;
	}
	
	public PlayerTO getPlayer() {
		return player;
	}
	public void setPlayer(PlayerTO player) {
		this.player = player;
	}
	public boolean isChallengeState() {
		return challengeState;
	}
	public void setChallengeState(boolean challengeState) {
		this.challengeState = challengeState;
	}

	public PlayerTO getOpponent() {
		return opponent;
	}

	public void setOpponent(PlayerTO opponent) {
		this.opponent = opponent;
	}

	public Calendar getDateChallenge() {
		return dateChallenge;
	}

	public void setDateChallenge(Calendar dateChallenge) {
		this.dateChallenge = dateChallenge;
	}
	
	
}
