package com.capgemini.chess.service;

import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserProfileTO;

import java.util.List;

public interface UserService {

	public UserProfileTO readUser(Long id);

	void createUser(int userId);

	List<PlayerTO> getUsers();

	PlayerTO getUser(int userId);

	List<PlayerTO> getOpponents(int userId);
}
