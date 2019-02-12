package com.staxter.userrepository.repository;

import com.staxter.userrepository.entity.User;
import com.staxter.userrepository.utils.exception.UserAlreadyExistsException;

public interface UserRepository {
    User createUser(User user) throws UserAlreadyExistsException;
}
