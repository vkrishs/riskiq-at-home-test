package com.riskiq.service;

import com.riskiq.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Simple service to demonstrate usage of UserRepository
 *
 * @author riskiq
 * @created 1/17/18
 */
@Service
public class DefaultUserService {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserService.class.getSimpleName());

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    void init() {
        log.info("User file path = {}", userRepository.getPath());
        log.info("User file size = {}", userRepository.size());
    }
}