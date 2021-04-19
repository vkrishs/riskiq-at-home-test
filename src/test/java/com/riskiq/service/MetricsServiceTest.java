package com.riskiq.service;

import com.google.common.io.Resources;
import com.riskiq.repository.ServiceOwnerRepository;
import com.riskiq.repository.UserRepository;
import com.riskiq.util.RowCountsHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

public class MetricsServiceTest {

    private static final URL RESOURCE = Resources.getResource("testGetSize.txt");

    private RowCountsHelper rowCountsHelper;
    private ServiceOwnerRepository serviceOwnerRepository;
    private UserRepository userRepository;

    MetricsService metricsService;

    @Before
    public void setUp() {
        rowCountsHelper = spy(new RowCountsHelper());
        serviceOwnerRepository = spy(new ServiceOwnerRepository(RESOURCE, rowCountsHelper));
        userRepository = spy(new UserRepository(RESOURCE, rowCountsHelper));
        metricsService = spy(new MetricsService(userRepository, serviceOwnerRepository));
    }

    @After
    public void tearDown() {
        rowCountsHelper = null;
        metricsService = null;
        serviceOwnerRepository = null;
        userRepository = null;
    }

    @Test
    public void testExtractRowCounts() {

        metricsService.scheduleRowCount();
        final Map<String, Long> numberOfRows = metricsService.extractRowCounts();
        final long actualUserRepoRowCount = numberOfRows.get(userRepository.getClass().getSimpleName());
        final long actualServiceOwnerRepoRowCount = numberOfRows.get(userRepository.getClass().getSimpleName());
        assertEquals(12L, actualUserRepoRowCount);
        assertEquals(12L, actualServiceOwnerRepoRowCount);
    }

    @Test
    public void testExtractRowCountsIOException() throws IOException, URISyntaxException {

        doThrow(new IOException("input")).when(rowCountsHelper).getRowCounts(RESOURCE);
        metricsService.scheduleRowCount();
        final Map<String, Long> numberOfRows = metricsService.extractRowCounts();
        final long actualUserRepoRowCount = numberOfRows.get(userRepository.getClass().getSimpleName());
        final long actualServiceOwnerRepoRowCount = numberOfRows.get(userRepository.getClass().getSimpleName());
        assertEquals(0, actualUserRepoRowCount);
        assertEquals(0, actualServiceOwnerRepoRowCount);
    }

    @Test
    public void testExtractRowCountsURISyntaxException() throws IOException, URISyntaxException {

        doThrow(new URISyntaxException("input", "reason")).when(rowCountsHelper).getRowCounts(RESOURCE);
        metricsService.scheduleRowCount();
        final Map<String, Long> numberOfRows = metricsService.extractRowCounts();
        final long actualUserRepoRowCount = numberOfRows.get(userRepository.getClass().getSimpleName());
        final long actualServiceOwnerRepoRowCount = numberOfRows.get(userRepository.getClass().getSimpleName());
        assertEquals(0, actualUserRepoRowCount);
        assertEquals(0, actualServiceOwnerRepoRowCount);
    }
}
