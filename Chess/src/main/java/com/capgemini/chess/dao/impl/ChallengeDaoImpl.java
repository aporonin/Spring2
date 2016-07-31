package com.capgemini.chess.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Implementation of interface ChallengeDao
 */
@Repository
public class ChallengeDaoImpl implements ChallengeDao {

	private int currentLevel;

	@Override
	public final List<PlayerTO> createChallengeList(PlayerTO player, List<PlayerTO> players) {
		List<PlayerTO> opponents = new ArrayList<>();
		currentLevel = player.getLevel();
		for (PlayerTO p : players) {
			if (Math.abs(player.getLevel() - p.getLevel()) <= 2 && Math.abs(player.getGames() - p.getGames()) <= 10) {
				opponents.add(p);
				if (opponents.size() == 5) {
					break;
				}
			}
		}
		return opponents;
	}

	@Override
	public final PlayerTO selectChallenge(List<PlayerTO> opponents, int numberOpponent) {
		PlayerTO opponent = opponents.get(numberOpponent - 1);
		giveInfoPlayer(opponent);
		return opponent;
	}

	@Override
	public final String giveInfoPlayer(PlayerTO player) {
		String info = player.toString();
		return info;
	}

	@Override
	public final boolean updateChallenge(PlayerTO player) {
		boolean gameState;
		if (player.getLevel() == currentLevel) {
			gameState = true;
		} else {
			gameState = false;
		}
		return gameState;
	}

	@Override
	public final List<PlayerTO> sendChallenge(PlayerTO opponent) {
		List<PlayerTO> sendChallenge = new ArrayList<>();
		opponent.setDateChallenge(Calendar.getInstance());
		sendChallenge.add(opponent);
		return sendChallenge;
	}

	@Override
	public final List<PlayerTO> setAcceptedChallenge(PlayerTO opponent, List<PlayerTO> sendChallenge) {
		List<PlayerTO> acceptedChallenge = new ArrayList<>();
		if (opponent.isStateChallenge()) {
			acceptedChallenge.add(opponent);
			sendChallenge.remove(opponent);
		} else {
			sendChallenge.remove(opponent);
		}
		return acceptedChallenge;
	}

	@Override
	public final boolean startMatch(PlayerTO state) {
		boolean startMatch = false;
		if (state.isStateChallenge()) {
			startMatch = true;
		} else {
			startMatch = false;
		}
		return startMatch;
	}
}
