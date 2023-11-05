package edu.school21.services;

import edu.school21.exceptions.AlwaysAuthenticatedException;
import edu.school21.models.User;
import edu.school21.repositories.UserRepository;

public class UserServiceImpl {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean authenticate(String login, String password){
        User user = userRepository.findByLogin(login);
        if(user.getAuthenticationStatus()){
            throw new AlwaysAuthenticatedException("User with login - " + user.getLogin() + " is already authenticated");
        }
        if(user.getPassword().equals(password)){
            user.setAuthenticationStatus(true);
            userRepository.update(user);
            return true;
        }
        return false;
    }
}
