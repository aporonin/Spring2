package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.PlayerTO;

/**
 * Interface ChallangeService
 */
public interface ChallangeService {

	/**
	 * @param player - player which search opponents
	 * @param playerTo - list of opponents
	 * @return list of selected opponents
	 */
	List<PlayerTO> createChallengeList(PlayerTO player, List<PlayerTO> playerTo);
	
	/**
	 * @param player
	 * @return true if player's level is the same before match
	 */
	boolean updateChallenge(PlayerTO player);

	/**
	 * @param opponents - list of selected opponents
	 * @param opponent -  selected number of opponent
	 * @return selected opponent
	 */
	PlayerTO selectChallenge(List<PlayerTO> opponents, int opponent);
	
	/**
	 * @param player
	 * @return information about player
	 */
	String giveInfoPlayer(PlayerTO player);
	
	/**
	 * @param opponent -  selected opponent
	 * @return - list of send challenges
	 */
	List<PlayerTO> sendChallenge(PlayerTO opponent);
	
	/**
	 * @param opponent 
	 * @param sendChallenge - list of send challenges
	 * @return list of accepted challenges
	 */
	List<PlayerTO> setAcceptedChallenge(PlayerTO opponent, List<PlayerTO> sendChallenge);
	
	/**
	 * @param state - decision of opponent
	 * @return true if match is possible
	 */
	boolean startMatch(PlayerTO state);
}
