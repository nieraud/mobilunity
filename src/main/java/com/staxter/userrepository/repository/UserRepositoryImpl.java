package com.staxter.userrepository.repository;

import com.staxter.userrepository.entity.User;
import com.staxter.userrepository.service.SecurityService;
import com.staxter.userrepository.utils.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final Map<String, User> userDataBase = new ConcurrentHashMap<>();

    private final SecurityService securityService;

    @Autowired
    public UserRepositoryImpl(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        if (ifUserAlreadyExist(user)) {
            throw new UserAlreadyExistsException();
        }

        String hashedPassword = securityService.hashPassword(user.getPlainTextPassword());
        user.setHashedPassword(hashedPassword);
        user.setPlainTextPassword(null);

        String id = UUID.randomUUID().toString();
        user.setId(id);
        userDataBase.put(id, user);
        return user;
    }

    private boolean ifUserAlreadyExist(User user) {
        return !userDataBase.isEmpty() && userDataBase.values().stream().anyMatch(u -> u.getUserName().equals(user.getUserName()));
    }

}
