package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.ChallangeService;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Implementation of interface ChallangeService
 */
@Service
public class ChallangeServiceImpl implements ChallangeService {

	@Autowired
	private ChallengeDao challengeDao;

	@Override
	public final List<PlayerTO> createChallengeList(PlayerTO player, List<PlayerTO> players) {
		return challengeDao.createChallengeList(player, players);
	}

	@Override
	public final PlayerTO selectChallenge(List<PlayerTO> opponents, int numberOpponent) {
		return challengeDao.selectChallenge(opponents, numberOpponent);
	}

	@Override
	public final String giveInfoPlayer(PlayerTO player) {
		return challengeDao.giveInfoPlayer(player);
	}

	@Override
	public final boolean updateChallenge(PlayerTO player) {
		return challengeDao.updateChallenge(player);
	}

	@Override
	public final List<PlayerTO> sendChallenge(PlayerTO opponent) {
		return challengeDao.sendChallenge(opponent);
	}

	@Override
	public final List<PlayerTO> setAcceptedChallenge(PlayerTO opponent, List<PlayerTO> sendChallenge) {
		return challengeDao.setAcceptedChallenge(opponent, sendChallenge);
	}

	@Override
	public final boolean startMatch(PlayerTO state) {
		return challengeDao.startMatch(state);
	}

}
