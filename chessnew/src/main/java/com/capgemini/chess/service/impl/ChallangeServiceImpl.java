package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.ChallangeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Implementation of interface ChallangeService
 */
@Service
public class ChallangeServiceImpl implements ChallangeService {

	@Autowired
	private ChallengeDao challengeDao;

	@Override
	public List<ChallengeTO> createChallengeList(PlayerTO player, List<PlayerTO> players) {
		return challengeDao.createChallengeList(player, players);
	}

	@Override
	public ChallengeTO selectChallenge(List<ChallengeTO> opponents, int numberOpponent) {
		return challengeDao.selectChallenge(opponents, numberOpponent);
	}

	@Override
	public String giveInfoPlayer(PlayerTO player) {
		return challengeDao.giveInfoPlayer(player);
	}

	@Override
	public ChallengeTO updateChallenge(PlayerTO player,ChallengeTO selectedChallenge) {
		return challengeDao.updateChallenge(player, selectedChallenge);
	}

	@Override
	public ChallengeTO sendChallenge(ChallengeTO challenge) {
		return challengeDao.sendChallenge(challenge);
	}

	@Override
	public List<ChallengeTO> setAcceptedChallenge(List<ChallengeTO> challengesForSender, List<ChallengeTO> challengesForReceiver) {
		return challengeDao.setAcceptedChallenge(challengesForSender, challengesForReceiver);
	}

	@Override
	public List<ChallengeTO> getOpponentsList(int userId) {
		return challengeDao.getOpponentsList(userId);
	}
	
}
