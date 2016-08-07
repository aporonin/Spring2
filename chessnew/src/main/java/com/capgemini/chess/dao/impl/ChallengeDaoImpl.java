package com.capgemini.chess.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.service.ListChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Implementation of interface ChallengeDao
 */
@Repository
public class ChallengeDaoImpl implements ChallengeDao {

	@Autowired
	private ListChallengeService challenge;
	
	@Override
	public List<ChallengeTO> getOpponentsList(int userId) {
		PlayerTO player = new PlayerTO(userId, "aaa", 1, 5, 0, 0);
		List<PlayerTO> userList = new ArrayList<>();
		userList.add(new PlayerTO(1, "aaa", 1, 5, 0, 0));
		userList.add(new PlayerTO(2, "bbb", 1, 3, 0, 0));
		userList.add(new PlayerTO(3, "ccc", 2, 2, 0, 0));
		userList.add(new PlayerTO(4, "ddd", 3, 5, 0, 0));
		userList.add(new PlayerTO(5, "eee", 2, 3, 0, 0));
		userList.add(new PlayerTO(6, "fff", 1, 2, 0, 0));
		userList.add(new PlayerTO(7, "ggg", 2, 5, 0, 0));
		userList.add(new PlayerTO(8, "hhh", 3, 3, 0, 0));
		userList.add(new PlayerTO(9, "iii", 2, 2, 0, 0));
		for (PlayerTO p : userList) {
			if (player.getPlayerID() == p.getPlayerID()) {
				userList.remove(p);
				break;
			}
		}
		return createChallengeList(player, userList);
	}

	@Override
	public List<ChallengeTO> createChallengeList(PlayerTO player, List<PlayerTO> players) {
		List<ChallengeTO> opponents = new ArrayList<>();
		for (PlayerTO p : players) {
			if (Math.abs(player.getLevel() - p.getLevel()) <= 2 && Math.abs(player.getGames() - p.getGames()) <= 10) {
				opponents.addAll(challenge.setListOfChallenges(player, p));
				if (opponents.size() == 5) {
					break;
				}
			}
		}
		return opponents;
	}

	@Override
	public ChallengeTO selectChallenge(List<ChallengeTO> opponents, int number) {
		ChallengeTO opponent = opponents.get(number - 1);
		giveInfoPlayer(opponent.getOpponent());
		return opponent;
	}

	@Override
	public String giveInfoPlayer(PlayerTO player) {
		String level = String.valueOf(player.getLevel());
		String points = String.valueOf(player.getPoints());
		String games = String.valueOf(player.getGames());
		String wons = String.valueOf(player.getWons());
		String info = level + points + games + wons;
		return info;
	}

	@Override
	public ChallengeTO updateChallenge(PlayerTO player, ChallengeTO selectedChallenge) {
		if (player.getLevel() == (selectedChallenge.getPlayer().getLevel())) {
			selectedChallenge.getPlayer().setStatePlayer(true);
			selectedChallenge.getOpponent().setStatePlayer(true);
		} else if (player.getLevel() != (selectedChallenge.getPlayer().getLevel())) {
			selectedChallenge.getPlayer().setStatePlayer(false);
			selectedChallenge.getOpponent().setStatePlayer(false);
		}
		return selectedChallenge;
	}

	@Override
	public ChallengeTO sendChallenge(ChallengeTO selectedChallenge) {
		selectedChallenge.setDateChallenge(Calendar.getInstance());
		challenge.setSendChallengesForSender(selectedChallenge);
		challenge.setSendChallengesForReceiver(selectedChallenge);
		return selectedChallenge;
	}

	@Override
	public List<ChallengeTO> setAcceptedChallenge(List<ChallengeTO> challengesForSender,
			List<ChallengeTO> challengesForReceiver) {
		List<ChallengeTO> acceptedChallenge = new ArrayList<>();
		for (ChallengeTO chSender : challengesForSender) {
			for (ChallengeTO chReceiver : challengesForReceiver) {
				if (chSender.getPlayer().getPlayerID() == (chReceiver.getPlayer().getPlayerID())
						&& (chSender.getOpponent().getPlayerID() == (chReceiver.getOpponent().getPlayerID()))) {
					challenge.setAcceptedChallenges(chSender);
					acceptedChallenge.add(chSender);
				}
			}
		}
		return acceptedChallenge;
	}

}
