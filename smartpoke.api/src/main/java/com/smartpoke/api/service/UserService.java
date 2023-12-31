package com.smartpoke.api.service;

import com.smartpoke.api.exceptions.ResourceNotFoundException;
import com.smartpoke.api.model.users.Location;
import com.smartpoke.api.model.users.User;
import com.smartpoke.api.model.users.Userinfo;
import com.smartpoke.api.repository.user.LocationRepository;
import com.smartpoke.api.repository.user.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartpoke.api.repository.user.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public Userinfo updateUserInfo(Long userId, Userinfo userInfo) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userInfo.setId(user.getUserinfo().getId());
        return userinfoRepository.save(userInfo);
    }

    @Override
    public Location updateLocation(Long userId, Location location) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        location.setId(user.getLocation().getId());
        return locationRepository.save(location);
    }

}
