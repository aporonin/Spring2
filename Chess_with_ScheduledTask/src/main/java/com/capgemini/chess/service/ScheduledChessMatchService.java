package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.PlayerTO;

/**
 * Interface ScheduledChessMatchService Method - checkChallenge
 */
public interface ScheduledChessMatchService {

	/**
	 * @param acceptedChallenges - list of all send challenges
	 * @return acceptedChallenges - list of all current challenges
	 */
	List<PlayerTO> checkChallenge(List<PlayerTO> acceptedChallenges);
}
