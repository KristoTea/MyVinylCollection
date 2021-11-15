package hr.fer.progi.MyVinylCollection.service.impl;

import hr.fer.progi.MyVinylCollection.dao.UserRepository;
import hr.fer.progi.MyVinylCollection.domain.Genre;
import hr.fer.progi.MyVinylCollection.domain.User;
import hr.fer.progi.MyVinylCollection.rest.LoginUserDTO;
import hr.fer.progi.MyVinylCollection.rest.RegisterUserDTO;
import hr.fer.progi.MyVinylCollection.service.RequestDeniedException;
import hr.fer.progi.MyVinylCollection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceJpa implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> listAll() {
        return userRepo.findAll();
    }

    @Override
    public boolean checkUsernameUnique(RegisterUserDTO user) {
        if (userRepo.countByUsername(user.getUsername()) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public User registerUser(RegisterUserDTO user, List<Genre> userGenrePreference) {
        return userRepo.save(new User(user, userGenrePreference));
    }

    @Override
    public boolean checkUsernameExists(LoginUserDTO user) {
        if(userRepo.countByUsername(user.getUsername()) == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkPassword(LoginUserDTO user) {
        if(userRepo.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public String getUserContactEmail(Long userId) {
        if(userRepo.findById(userId).isEmpty())
            throw new RequestDeniedException("No user with id:" + userId);

        return userRepo.getById(userId).getContactEmail();
    }


    @Override
    public boolean updateUserStatus(Long userId, boolean status) {
        if(userRepo.findById(userId).isEmpty())
            throw new RequestDeniedException("No user with id:" + userId);

        if(userRepo.updateUserStatus(userId, status).getIsActive() == status)
                return true;
        return false;
    }
}
