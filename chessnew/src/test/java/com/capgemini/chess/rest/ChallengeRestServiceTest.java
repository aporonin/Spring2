package com.capgemini.chess.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.chess.dao.ChallengeDao;
import com.capgemini.chess.dao.impl.ChallengeDaoImpl;
import com.capgemini.chess.service.ChallangeService;
import com.capgemini.chess.service.ListChallengeService;
import com.capgemini.chess.service.impl.ChallangeServiceImpl;
import com.capgemini.chess.service.impl.ListChallengeServiceImpl;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration // ( locations={
						// "classpath*:spring/applicationContext.xml"})
@WebAppConfiguration
public class ChallengeRestServiceTest {

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

		@Bean
		public ListChallengeService listChallenge() {
			return new ListChallengeServiceImpl();
		}
	}

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		Mockito.reset(challengeService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testCreateListOfOpponents() throws Exception {
		// given
		PlayerTO player = new PlayerTO(1, "player1", 1, 20, 3, 0);
		PlayerTO opponent = new PlayerTO(2, "player2", 2, 25, 3, 0);
		ChallengeTO challenge = new ChallengeTO(player, opponent, false);

		Mockito.when(challengeService.getOpponentsList(Mockito.anyObject())).thenReturn(Arrays.asList(challenge));
		// when
		ResultActions response = this.mockMvc.perform(get("challenge/createChallengeList/{userId}").param("userId","1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));
		
		response.andExpect(status().isOk())
				.andExpect(jsonPath("[0].player.userId").value(challenge.getOpponent().getPlayerID()));
	}

}
