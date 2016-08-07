package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

public interface ListChallengeService {

	/**
	 * @param player
	 * @param opponent
	 * @return list of selected challenges
	 */
	List<ChallengeTO> setListOfChallenges(PlayerTO player, PlayerTO opponent);

	/**
	 * @param challenge
	 * @return list of send challenges for sender
	 */
	List<ChallengeTO> setSendChallengesForSender(ChallengeTO challenge);

	/**
	 * @param challenge
	 * @return list of send challenges for receiver
	 */
	List<ChallengeTO> setSendChallengesForReceiver(ChallengeTO challenge);

	/**
	 * @param challenge
	 * @return list of accepted challenges
	 */
	List<ChallengeTO> setAcceptedChallenges(ChallengeTO challenge);
}
