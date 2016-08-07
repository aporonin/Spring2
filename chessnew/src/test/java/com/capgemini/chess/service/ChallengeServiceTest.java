package com.capgemini.chess.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.dao.impl.ChallengeDaoImpl;
import com.capgemini.chess.service.impl.ChallangeServiceImpl;
import com.capgemini.chess.service.impl.ListChallengeServiceImpl;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Tests for ChallengeService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ChallengeServiceTest {
	
	@Autowired
	private ChallangeService challengeService;

	@Autowired
	private ListChallengeService listService;

	@Configuration
	static class RankServiceTestContextConfiguration {

		@Bean
		public ChallangeService challangeService() {
			return new ChallangeServiceImpl();
		}

		@Bean
		public ChallengeDao challengeDao() {
			return new ChallengeDaoImpl();
		}

		@Bean
		public ListChallengeService listChallenge() {
			return new ListChallengeServiceImpl();
		}

	}

	@Test
	public final void testShouldCreateChallengeForFiveOpponents() {
		// when
		PlayerTO player = new PlayerTO(1, "player", 1, 20, 3, 0);
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO(2, "player1", 2, 306, 7, 1));
		list.add(new PlayerTO(3, "player2", 3, 690, 8, 2));
		list.add(new PlayerTO(4, "player3", 1, 100, 2, 0));
		list.add(new PlayerTO(5, "player4", 2, 340, 6, 1));
		List<ChallengeTO> opponents = challengeService.createChallengeList(player, list);
		// then
		Assert.assertEquals(4, opponents.size());
	}

	@Test
	public final void testShouldCreateChallengeForMaxFivePlayers() {
		// when
		PlayerTO player = new PlayerTO(1, "player", 1, 20, 3, 0);
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO(2, "player1", 2, 306, 7, 1));
		list.add(new PlayerTO(3, "player2", 3, 690, 8, 2));
		list.add(new PlayerTO(4, "player3", 1, 100, 2, 0));
		list.add(new PlayerTO(5, "player4", 2, 340, 6, 1));
		list.add(new PlayerTO(6, "player5", 1, 100, 2, 0));
		list.add(new PlayerTO(7, "player6", 2, 340, 6, 1));
		List<ChallengeTO> opponents = challengeService.createChallengeList(player, list);
		// then
		Assert.assertEquals(5, opponents.size());
	}

	@Test
	public final void testShouldCreateChallengeForAnyonePlayer() {
		// when
		PlayerTO player = new PlayerTO(1, "player", 1, 20, 3, 0);
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO(2, "player1", 3, 606, 27, 1));
		list.add(new PlayerTO(3, "player2", 7, 9690, 200, 90));
		list.add(new PlayerTO(4, "player3", 8, 20100, 290, 127));
		list.add(new PlayerTO(5, "player4", 3, 640, 26, 1));
		list.add(new PlayerTO(6, "player5", 10, 80100, 500, 440));
		list.add(new PlayerTO(7, "player6", 4, 1340, 50, 30));
		List<ChallengeTO> opponents = challengeService.createChallengeList(player, list);
		// then
		Assert.assertEquals(0, opponents.size());
	}

	@Test
	public final void testShouldSelectOpponentForChallenge() {
		// when
		List<ChallengeTO> listChallenge = new ArrayList<>();
		PlayerTO player = new PlayerTO(1, "player", 1, 20, 3, 0);
		PlayerTO opponent1 = new PlayerTO(1, "player1", 2, 306, 7, 1);
		PlayerTO opponent2 = new PlayerTO(2, "player2", 3, 690, 8, 2);
		PlayerTO opponent3 = new PlayerTO(3, "player3", 1, 100, 2, 0);
		PlayerTO opponent4 = new PlayerTO(4, "player4", 2, 340, 6, 1);
		PlayerTO opponent5 = new PlayerTO(5, "player5", 1, 100, 2, 0);
		PlayerTO opponent6 = new PlayerTO(6, "player6", 2, 340, 6, 1);
		listChallenge.add(new ChallengeTO(player, opponent1, false));
		listChallenge.add(new ChallengeTO(player, opponent2, false));
		listChallenge.add(new ChallengeTO(player, opponent3, false));
		listChallenge.add(new ChallengeTO(player, opponent4, false));
		listChallenge.add(new ChallengeTO(player, opponent5, false));
		listChallenge.add(new ChallengeTO(player, opponent6, false));
		int numberOpponent = 3;
		// then
		Assert.assertEquals(opponent3.getPlayerID(),
				challengeService.selectChallenge(listChallenge, numberOpponent).getOpponent().getPlayerID());
	}

	@Test
	public final void testShouldShowInfoForPlayer() {
		// when
		PlayerTO player = new PlayerTO(3, "player3", 1, 100, 2, 0);
		String level = String.valueOf(player.getLevel());
		String points = String.valueOf(player.getPoints());
		String games = String.valueOf(player.getGames());
		String wons = String.valueOf(player.getWons());
		String info = level + points + games + wons;
		// then
		Assert.assertEquals(info, challengeService.giveInfoPlayer(player));
	}

	@Test
	public final void testShouldCheckUpdateLevelOfPlayerForTheSameLevel() {
		// when
		PlayerTO player = new PlayerTO(5, "player5", 1, 100, 2, 0);
		PlayerTO opponent = new PlayerTO(2, "player2", 3, 690, 8, 2);
		ChallengeTO challenge = new ChallengeTO(player, opponent, false);
		// then
		Assert.assertTrue(challengeService.updateChallenge(player, challenge).getPlayer().isStatePlayer());
		Assert.assertEquals(player.getLevel(),
				challengeService.updateChallenge(player, challenge).getPlayer().getLevel());
	}

	@Test
	public final void testShouldCheckUpdateLevelOfPlayerNotTheSameLevel() {
		// when
		PlayerTO player = new PlayerTO(5, "player5", 1, 100, 2, 0);
		PlayerTO opponent = new PlayerTO(2, "player2", 3, 690, 8, 2);
		PlayerTO player1 = new PlayerTO(5, "player5", 2, 100, 2, 0);
		ChallengeTO challenge = new ChallengeTO(player1, opponent, false);
		// then
		Assert.assertFalse(challengeService.updateChallenge(player, challenge).getPlayer().isStatePlayer());
		Assert.assertNotEquals(player.getLevel(),
				challengeService.updateChallenge(player1, challenge).getPlayer().getLevel());
	}

	@Test
	public final void testShouldSendChallenge() {
		// when
		PlayerTO player = new PlayerTO(5, "player5", 1, 100, 2, 0);
		PlayerTO opponent = new PlayerTO(2, "player2", 3, 690, 8, 2);
		ChallengeTO challenge = new ChallengeTO(player, opponent, false);
		// then
		Assert.assertEquals(player, challengeService.sendChallenge(challenge).getPlayer());
		Assert.assertEquals(Calendar.getInstance().getTime(),
				listService.setSendChallengesForSender(challenge).get(0).getDateChallenge().getTime());
	}

	@Test
	public final void testShouldSetAcceptedChallenge() {
		// when
		List<ChallengeTO> listChallengeForSender = new ArrayList<>();
		List<ChallengeTO> listChallengeForReceiver = new ArrayList<>();
		List<ChallengeTO> listChallenge = new ArrayList<>();
		PlayerTO player = new PlayerTO(1, "player", 1, 20, 3, 0);
		PlayerTO opponent1 = new PlayerTO(1, "player1", 2, 306, 7, 1);
		ChallengeTO challenge1 = new ChallengeTO(player, opponent1, true);
		ChallengeTO challenge2 = new ChallengeTO(player, opponent1, true);
		listChallenge.add(challenge1);
		listChallengeForSender.add(challenge1);
		listChallengeForReceiver.add(challenge2);
		//then
		Assert.assertEquals(listChallenge.size(),
				challengeService.setAcceptedChallenge(listChallengeForSender, listChallengeForReceiver).size());
	}

}
