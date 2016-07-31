package com.capgemini.chess;

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

import com.capgemini.chess.dao.ScheduledChessMatchDao;
import com.capgemini.chess.dao.impl.ScheduledChessMatchDaoImpl;
import com.capgemini.chess.service.ScheduledChessMatchService;
import com.capgemini.chess.service.impl.ScheduledChessMatchServiceImpl;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Tests for class ScheduledChessmatchService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ScheduledChessMatchTest {

	@Autowired
	private ScheduledChessMatchService scheduler;

	@Configuration
	static class RankServiceTestContextConfiguration {

		@Bean
		public ScheduledChessMatchService scheduledChessMatchService() {
			return new ScheduledChessMatchServiceImpl();
		}

		@Bean
		public ScheduledChessMatchDao scheduledChessMatchDao() {
			return new ScheduledChessMatchDaoImpl();
		}
	}

	@Test
	public final void shouldCheckSchedulerChessWithOneOutOfDateChallenge() {
		List<PlayerTO> acceptedChallenges = new ArrayList<>();
		PlayerTO player1 = new PlayerTO("player1", 2, 306, 7, 1);
		PlayerTO player2 = new PlayerTO("player2", 3, 690, 8, 2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, -8);
		player1.setDateChallenge(cal1);
		player2.setDateChallenge(cal2);
		acceptedChallenges.add(player1);
		acceptedChallenges.add(player2);
		Assert.assertEquals(1, scheduler.checkChallenge(acceptedChallenges).size());
	}

	@Test
	public final void shouldCheckSchedulerChessWithOneOfTwoOutOfDateChallenge() {
		List<PlayerTO> acceptedChallenges = new ArrayList<>();
		PlayerTO player1 = new PlayerTO("player1", 2, 306, 7, 1);
		PlayerTO player2 = new PlayerTO("player2", 3, 690, 8, 2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -4);
		cal2.add(Calendar.DATE, -8);
		player1.setDateChallenge(cal1);
		player2.setDateChallenge(cal2);
		acceptedChallenges.add(player1);
		acceptedChallenges.add(player2);
		Assert.assertEquals(1, scheduler.checkChallenge(acceptedChallenges).size());
	}

	@Test
	public final void shouldCheckSchedulerChessWithAllCurrentChallenges() {
		List<PlayerTO> acceptedChallenges = new ArrayList<>();
		PlayerTO player1 = new PlayerTO("player1", 2, 306, 7, 1);
		PlayerTO player2 = new PlayerTO("player2", 3, 690, 8, 2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -3);
		cal2.add(Calendar.DATE, -1);
		player1.setDateChallenge(cal1);
		player2.setDateChallenge(cal2);
		acceptedChallenges.add(player1);
		acceptedChallenges.add(player2);
		Assert.assertEquals(2, scheduler.checkChallenge(acceptedChallenges).size());
	}

	@Test
	public final void shouldCheckSchedulerChessWithTwoOutOfdateChallenges() {
		List<PlayerTO> acceptedChallenges = new ArrayList<>();
		PlayerTO player1 = new PlayerTO("player1", 2, 306, 7, 1);
		PlayerTO player2 = new PlayerTO("player2", 3, 690, 8, 2);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.add(Calendar.DATE, -8);
		cal2.add(Calendar.DATE, -8);
		player1.setDateChallenge(cal1);
		player2.setDateChallenge(cal2);
		acceptedChallenges.add(player1);
		acceptedChallenges.add(player2);
		Assert.assertEquals(0, scheduler.checkChallenge(acceptedChallenges).size());
	}
}
