package com.riskiq.service;

import com.riskiq.repository.ServiceOwnerRepository;
import com.riskiq.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple service to demonstrate usage of UserRepository and ServiceOwnerRepository
 */
@Service
public class MetricsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsService.class.getSimpleName());

    private static final Map<String, Long> numberOfRows = new HashMap<>();
    private final UserRepository userRepository;
    private final ServiceOwnerRepository serviceOwnerRepository;

    @Autowired
    public MetricsService(final UserRepository userRepository, final ServiceOwnerRepository serviceOwnerRepository) {
        this.userRepository = userRepository;
        this.serviceOwnerRepository = serviceOwnerRepository;
    }

    @Scheduled(fixedRate = 300000L)
    public void scheduleRowCount() {
        extractRowCounts();
    }

    public Map<String, Long> extractRowCounts() {
        numberOfRows.put(userRepository.getClass().getSimpleName(), userRepository.size());
        numberOfRows.put(serviceOwnerRepository.getClass().getSimpleName(), serviceOwnerRepository.size());
        return numberOfRows;
    }

    @PostConstruct
    void init() {
        LOGGER.info("User file path = {}", userRepository.getPath());
        LOGGER.info("User file size = {}", userRepository.size());
        LOGGER.info("Service owner file path = {}", serviceOwnerRepository.getPath());
        LOGGER.info("Service owner file size = {}", serviceOwnerRepository.size());
    }
}
