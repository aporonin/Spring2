package com.capgemini.chess.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.chess.service.ChallangeService;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Rest service for ChallengeService
 */
@RestController
@RequestMapping("/challenge")
public class ChallengeRestService {

	@Autowired
	private ChallangeService challengeService;

	/**
	 * @param userId
	 * @return list of challenges
	 */
	@RequestMapping(value = "/createChallengeList/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChallengeTO>> createChallengeList(@PathVariable("userId") int userId) {
		List<ChallengeTO> challengeList = challengeService.getOpponentsList(userId);
		return new ResponseEntity<List<ChallengeTO>>(challengeList, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param index of challenge
	 * @return selected opponents
	 */
	@RequestMapping(value = "/selectChallengeList/{userId}/{index}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChallengeTO> selectChallenge(@PathVariable("userId") int userId,
			@PathVariable("index") int index) {
		ChallengeTO selectedOpponent = challengeService.getOpponentsList(userId).get(index);
		return new ResponseEntity<ChallengeTO>(selectedOpponent, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param index of challenge
	 * @return info about player
	 */
	@RequestMapping(value = "/giveInfoPlayer/{userId}/{index}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> infoChallenge(@PathVariable("userId") int userId, @PathVariable("index") int index) {
		PlayerTO player = challengeService.getOpponentsList(userId).get(index).getOpponent();
		String infoPlayer = challengeService.giveInfoPlayer(player);
		return new ResponseEntity<String>(infoPlayer, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param index of challenge
	 * @return updated challenge
	 */
	@RequestMapping(value = "/updateChallenge/{userId}/{index}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChallengeTO> updateChallenge(@PathVariable("userId") int userId,
			@PathVariable("index") int index) {
		PlayerTO player = challengeService.getOpponentsList(userId).get(index).getPlayer();
		ChallengeTO selectedChallenge = challengeService.getOpponentsList(userId).get(index);
		ChallengeTO updatedChallenge = challengeService.updateChallenge(player, selectedChallenge);
		return new ResponseEntity<ChallengeTO>(updatedChallenge, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param index of challenge
	 * @return send challenge
	 */
	@RequestMapping(value = "/sendChallenge/{userId}/{index}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChallengeTO> sendChallenge(@PathVariable("userId") int userId,
			@PathVariable("index") int index) {
		ChallengeTO selectedChallenge = challengeService.getOpponentsList(userId).get(index);
		ChallengeTO sendChallenge = challengeService.sendChallenge(selectedChallenge);
		return new ResponseEntity<ChallengeTO>(sendChallenge, HttpStatus.OK);
	}

	/**
	 * @param userId
	 * @param index of challenge
	 * @return list of accepted challenge
	 */
	@RequestMapping(value = "/acceptedChallenge/{userId}/{index}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ChallengeTO>> setAcceptedChallenge(@PathVariable("userId") int userId,
			@PathVariable("index") int index) {
		List<ChallengeTO> senderChallenge = new ArrayList<>();
		List<ChallengeTO> receiverChallenge = new ArrayList<>();
		ChallengeTO selectedChallenge = challengeService.getOpponentsList(userId).get(index);
		senderChallenge.add(selectedChallenge);
		receiverChallenge.add(selectedChallenge);
		List<ChallengeTO> acceptedChallenge = challengeService.setAcceptedChallenge(senderChallenge, receiverChallenge);
		return new ResponseEntity<List<ChallengeTO>>(acceptedChallenge, HttpStatus.OK);
	}

}
