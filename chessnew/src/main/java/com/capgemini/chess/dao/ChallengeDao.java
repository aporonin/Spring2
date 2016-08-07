package com.capgemini.chess.dao;

import java.util.List;

import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Interface ChallengeDao
 */
public interface ChallengeDao {

	/**
	 * @param player - player which search opponents
	 * @param playerTo - list of opponents
	 * @return list of created challenges
	 */
	List<ChallengeTO> createChallengeList(PlayerTO player, List<PlayerTO> playerTo);

	/**
	 * @param player
	 * @return challenge
	 */
	ChallengeTO updateChallenge(PlayerTO player, ChallengeTO selectedChallenge);

	/**
	 * @param opponents - list of selected challenges
	 * @param opponent -  selected number of opponent
	 * @return selected challenge
	 */
	ChallengeTO selectChallenge(List<ChallengeTO> opponents, int opponent);

	/**
	 * @param player
	 * @return information about player
	 */
	String giveInfoPlayer(PlayerTO player);

	/**
	 * @param challenge -  selected challenge
	 * @return send challenge
	 */
	ChallengeTO sendChallenge(ChallengeTO challenge);

	/**
	 * @param challengesForSender - list of sender's challenges 
	 * @param challengesForReceiver - list of receiver's challenges
	 * @return list of accepted challenges
	 */
	List<ChallengeTO> setAcceptedChallenge(List<ChallengeTO> challengesForSender, List<ChallengeTO> challengesForReceiver);

	List<ChallengeTO> getOpponentsList(int userId);
}
