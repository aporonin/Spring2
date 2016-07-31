package com.capgemini.chess.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.capgemini.chess.dao.ScheduledChessMatchDao;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Implementation of interface ScheduledChessmatchDao
 */
@Repository
public class ScheduledChessMatchDaoImpl implements ScheduledChessMatchDao {

	private static final int LIMITED_PERIOD = -7;

	/*
	 * @Scheduled annotation 
	 * @param List<PlayerTO> acceptedChallenges - list of all send challenges 
	 * @return acceptedChallenges - list of all current challenges
	 */
	@Scheduled(cron = "0 0 24 * * *")
	public final List<PlayerTO> checkChallenge(final List<PlayerTO> acceptedChallenges) {
		List<PlayerTO> afterDateChallenges = new ArrayList<>();
		for (PlayerTO p : acceptedChallenges) {
			Calendar currentDate = Calendar.getInstance();
			currentDate.add(Calendar.DATE, LIMITED_PERIOD);
			Calendar challengeDate = p.getDateChallenge();
			if (currentDate.after(challengeDate)) {
				afterDateChallenges.add(p);
			}
		}
		acceptedChallenges.removeAll(afterDateChallenges);
		return acceptedChallenges;
	}
}
