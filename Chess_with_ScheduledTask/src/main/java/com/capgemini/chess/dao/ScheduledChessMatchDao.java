package com.capgemini.chess.dao;

import java.util.List;

import com.capgemini.chess.service.to.PlayerTO;

/**
 * Interface ScheduledChessMatchDao
 * method - checkChallenge
 */
public interface ScheduledChessMatchDao {

	/**
	 * @param acceptedChallenges - list of all send challenges
	 * @return acceptedChallenges - list of all current challenges
	 */
	List<PlayerTO> checkChallenge(List<PlayerTO> acceptedChallenges);
}
