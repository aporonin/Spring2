package com.capgemini.chess.service.to;

import java.util.Calendar;

/**
 * Description of player
 */
public class PlayerTO {

	private String name;
	private int level;
	private int points;
	private int games;
	private int wons;
	private boolean stateChallenge;
	private Calendar dateChallenge;

	/**
	 * @param name
	 * @param level
	 * @param points
	 * @param games
	 * @param wons
	 */
	public PlayerTO(String name, int level, int points, int games, int wons) {
		this.name = name;
		this.level = level;
		this.points = points;
		this.games = games;
		this.wons = wons;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return player's points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return player's game
	 */
	public int getGames() {
		return games;
	}

	/**
	 * @param games
	 */
	public void setGames(int games) {
		this.games = games;
	}

	/**
	 * @return player's win game
	 */
	public int getWons() {
		return wons;
	}

	/**
	 * @param wons
	 */
	public void setWons(int wons) {
		this.wons = wons;
	}

	/**
	 * @return player's state
	 */
	public boolean isStateChallenge() {
		return stateChallenge;
	}

	/**
	 * @param stateChallenge
	 */
	public void setStateChallenge(boolean stateChallenge) {
		this.stateChallenge = stateChallenge;
	}

	/**
	 * @return date of send challenge
	 */
	public Calendar getDateChallenge() {
		return dateChallenge;
	}

	/**
	 * @param calendar
	 */
	public void setDateChallenge(Calendar calendar) {
		this.dateChallenge = calendar;
	}
}
