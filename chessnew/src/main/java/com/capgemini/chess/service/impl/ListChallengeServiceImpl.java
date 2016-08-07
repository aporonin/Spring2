package com.capgemini.chess.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.chess.service.ListChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Implementation of interface ListChallengeService
 */
@Service
public class ListChallengeServiceImpl implements ListChallengeService {

	@Override
	public List<ChallengeTO> setListOfChallenges(PlayerTO player, PlayerTO opponent) {
		List<ChallengeTO> challengeList = new ArrayList<>();
		ChallengeTO challenge = new ChallengeTO(player, opponent, false);
		challengeList.add(challenge);
		return challengeList;
	}

	@Override
	public List<ChallengeTO> setSendChallengesForSender(ChallengeTO challenge) {
		List<ChallengeTO> senderChallenge = new ArrayList<>();
		challenge.setDateChallenge(Calendar.getInstance());
		senderChallenge.add(challenge);
		return senderChallenge;
	}

	@Override
	public List<ChallengeTO> setSendChallengesForReceiver(ChallengeTO challenge) {
		List<ChallengeTO> receiverChallenge = new ArrayList<>();
		challenge.setDateChallenge(Calendar.getInstance());
		receiverChallenge.add(challenge);
		return receiverChallenge;
	}

	@Override
	public List<ChallengeTO> setAcceptedChallenges(ChallengeTO challenge) {
		List<ChallengeTO> acceptedChallenge = new ArrayList<>();
		challenge.setChallengeState(true);
		challenge.setDateChallenge(Calendar.getInstance());
		acceptedChallenge.add(challenge);
		return acceptedChallenge;
	}

}
