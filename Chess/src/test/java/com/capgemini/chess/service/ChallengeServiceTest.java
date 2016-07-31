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
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Tests for ChallengeService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ChallengeServiceTest {

	@Autowired
	private ChallangeService challengeService;

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
	}

	@Test
	public final void testShouldCreateChallenge1() {
		// when
		PlayerTO player = new PlayerTO("player", 1, 20, 3, 0);
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO("player1", 2, 306, 7, 1));
		list.add(new PlayerTO("player2", 3, 690, 8, 2));
		list.add(new PlayerTO("player3", 1, 100, 2, 0));
		list.add(new PlayerTO("player4", 2, 340, 6, 1));
		List<PlayerTO> opponents = challengeService.createChallengeList(player, list);
		// then
		Assert.assertEquals(4, opponents.size());
	}

	@Test
	public final void testShouldCreateChallenge2() {
		// when
		PlayerTO player = new PlayerTO("player", 1, 20, 3, 0);
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO("player1", 2, 306, 7, 1));
		list.add(new PlayerTO("player2", 3, 690, 8, 2));
		list.add(new PlayerTO("player3", 1, 100, 2, 0));
		list.add(new PlayerTO("player4", 2, 340, 6, 1));
		list.add(new PlayerTO("player5", 1, 100, 2, 0));
		list.add(new PlayerTO("player6", 2, 340, 6, 1));
		List<PlayerTO> opponents = challengeService.createChallengeList(player, list);
		// then
		Assert.assertEquals(5, opponents.size());
	}

	@Test
	public final void testShouldCreateChallenge3() {
		// when
		PlayerTO player = new PlayerTO("player", 1, 20, 3, 0);
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO("player1", 3, 606, 27, 1));
		list.add(new PlayerTO("player2", 7, 9690, 200, 90));
		list.add(new PlayerTO("player3", 8, 20100, 290, 127));
		list.add(new PlayerTO("player4", 3, 640, 26, 1));
		list.add(new PlayerTO("player5", 10, 80100, 500, 440));
		list.add(new PlayerTO("player6", 4, 1340, 50, 30));
		List<PlayerTO> opponents = challengeService.createChallengeList(player, list);
		// then
		Assert.assertEquals(0, opponents.size());
	}

	@Test
	public final void testShouldSelectOpponentForChallenge() {
		// when
		List<PlayerTO> list = new ArrayList<>();
		list.add(new PlayerTO("player1", 2, 306, 7, 1));
		list.add(new PlayerTO("player2", 3, 690, 8, 2));
		list.add(new PlayerTO("player3", 1, 100, 2, 0));
		list.add(new PlayerTO("player4", 2, 340, 6, 1));
		list.add(new PlayerTO("player5", 1, 100, 2, 0));
		list.add(new PlayerTO("player6", 2, 340, 6, 1));
		PlayerTO selectedPlayer = new PlayerTO("player3", 1, 100, 2, 0);
		int numberOpponent = 3;
		// then
		Assert.assertEquals(selectedPlayer.getName(), challengeService.selectChallenge(list, numberOpponent).getName());
		Assert.assertEquals(selectedPlayer.getLevel(),
				challengeService.selectChallenge(list, numberOpponent).getLevel());
		Assert.assertEquals(selectedPlayer.getGames(),
				challengeService.selectChallenge(list, numberOpponent).getGames());
		Assert.assertEquals(selectedPlayer.getPoints(),
				challengeService.selectChallenge(list, numberOpponent).getPoints());
		Assert.assertEquals(selectedPlayer.getWons(), challengeService.selectChallenge(list, numberOpponent).getWons());
	}

	@Test
	public final void testShouldShowInfoForPlayer() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player3", 1, 100, 2, 0);
		String info = selectedPlayer.toString();
		// then
		Assert.assertEquals(info, challengeService.giveInfoPlayer(selectedPlayer));
	}

	@Test
	public final void testShouldCheckUpdateLevelOfPlayer1() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 1, 100, 2, 0);
		// then
		Assert.assertTrue(challengeService.updateChallenge(selectedPlayer));
	}

	@Test
	public final void testShouldCheckUpdateLevelOfPlayer2() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 2, 100, 2, 0);
		// then
		Assert.assertFalse(challengeService.updateChallenge(selectedPlayer));
	}

	@Test
	public final void testShouldSendChallenge() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 1, 100, 2, 0);

		selectedPlayer.setDateChallenge(Calendar.getInstance());
		// then
		Assert.assertEquals(1, challengeService.sendChallenge(selectedPlayer).size());
		Assert.assertEquals(Calendar.getInstance().getTime(),
				challengeService.sendChallenge(selectedPlayer).get(0).getDateChallenge().getTime());
	}

	@Test
	public final void testShouldSetAcceptedChallenge1() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 1, 100, 2, 0);
		List<PlayerTO> sendChallenge = new ArrayList<>();
		boolean state = true;
		selectedPlayer.setStateChallenge(state);
		sendChallenge.add(new PlayerTO("player1", 2, 306, 7, 1));
		sendChallenge.add(new PlayerTO("player2", 3, 690, 8, 2));
		sendChallenge.add(new PlayerTO("player3", 1, 100, 2, 0));
		sendChallenge.add(new PlayerTO("player4", 2, 340, 6, 1));
		sendChallenge.add(new PlayerTO("player5", 1, 100, 2, 0));
		List<PlayerTO> acceptedChallenge = new ArrayList<>();
		acceptedChallenge.add(selectedPlayer);
		// then
		Assert.assertEquals(acceptedChallenge.size(),
				challengeService.setAcceptedChallenge(selectedPlayer, sendChallenge).size());
	}

	@Test
	public final void testShouldSetAcceptedChallenge2() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 1, 100, 2, 0);
		boolean state = false;
		selectedPlayer.setStateChallenge(state);
		List<PlayerTO> sendChallenge = new ArrayList<>();
		sendChallenge.add(new PlayerTO("player1", 2, 306, 7, 1));
		sendChallenge.add(new PlayerTO("player2", 3, 690, 8, 2));
		sendChallenge.add(new PlayerTO("player3", 1, 100, 2, 0));
		sendChallenge.add(new PlayerTO("player4", 2, 340, 6, 1));
		sendChallenge.add(selectedPlayer);
		// then
		Assert.assertTrue(challengeService.setAcceptedChallenge(selectedPlayer, sendChallenge).isEmpty());
	}

	@Test
	public final void testShouldCheckStartMatchInCaseAcceptChallenge() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 1, 100, 2, 0);
		boolean state = true;
		selectedPlayer.setStateChallenge(state);
		// then
		Assert.assertTrue(challengeService.startMatch(selectedPlayer));
	}

	@Test
	public final void testShouldCheckStartMatchInCaseDeclineChallenge() {
		// when
		PlayerTO selectedPlayer = new PlayerTO("player5", 1, 100, 2, 0);
		boolean state = false;
		selectedPlayer.setStateChallenge(state);
		// then
		Assert.assertFalse(challengeService.startMatch(selectedPlayer));
	}

}
