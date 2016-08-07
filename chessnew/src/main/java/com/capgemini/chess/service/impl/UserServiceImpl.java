package com.capgemini.chess.service.impl;

import com.capgemini.chess.service.UserService;
import com.capgemini.chess.service.to.PlayerTO;
import com.capgemini.chess.service.to.UserProfileTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private List<PlayerTO> userList = new ArrayList<>();
    Random random=new Random();
    
    @Override
    public UserProfileTO readUser(Long id) {
        return new UserProfileTO();
    }

    @Override
    public void createUser(int userId) {
        final PlayerTO newPlayer = new PlayerTO(userId, "aaa", random.nextInt(10), random.nextInt(100), 0, 0);
        userList.add(newPlayer);
    }

    @Override
    public List<PlayerTO> getUsers() {
        return userList;
    }

    @Override
    public PlayerTO getUser(int userId) {
        for (PlayerTO playerTO : userList) {
            if(playerTO.getPlayerID() == userId){
                return playerTO;
            }
        }
        return null;
    }

    @Override
    public List<PlayerTO> getOpponents(int userId) {
        final List<PlayerTO> result = new ArrayList<>();
        for (PlayerTO playerTO : userList) {
            if(playerTO.getPlayerID() != userId){
                result.add(playerTO);
            }
        }
        return result;
    }
}
