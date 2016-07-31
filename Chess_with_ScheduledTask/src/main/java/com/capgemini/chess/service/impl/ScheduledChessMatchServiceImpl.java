package com.capgemini.chess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dao.ScheduledChessMatchDao;
import com.capgemini.chess.service.ScheduledChessMatchService;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * @Service annotation Implementation of interface ScheduledChessMatchService
 */
@Service
public class ScheduledChessMatchServiceImpl implements ScheduledChessMatchService {

	@Autowired
	private ScheduledChessMatchDao scheduledChessMatchDao;

	/*
	 * @param List<PlayerTO> acceptedChallenges - list of all send challenges
	 * @return acceptedChallenges - list of all current challenges
	 */
	@Override
	public final List<PlayerTO> checkChallenge(final List<PlayerTO> acceptedChallenges) {
		return scheduledChessMatchDao.checkChallenge(acceptedChallenges);
	}

}
