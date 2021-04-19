package com.riskiq.repository;

import com.google.common.io.Resources;
import com.riskiq.util.RowCountsHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

/**
 * @author riskiq
 * @created 1/17/18
 */
public class UserRepositoryTest {

    private static final URL RESOURCE = Resources.getResource("testGetSize.txt");

    private RowCountsHelper rowCountsHelper;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        rowCountsHelper = spy(new RowCountsHelper());
        userRepository = spy(new UserRepository(RESOURCE, rowCountsHelper));
    }

    @After
    public void tearDown() {
        rowCountsHelper = null;
        userRepository = null;
    }

    @Test
    public void testGetSize() {

        long size = userRepository.size();
        assertEquals(size, 12);
    }

    @Test
    public void testGetSizeIOException() throws IOException, URISyntaxException {

        doThrow(new IOException("input")).when(rowCountsHelper).getRowCounts(RESOURCE);
        assertEquals(0, userRepository.size());
    }

    @Test
    public void testGetSizeURISyntaxException() throws IOException, URISyntaxException {

        doThrow(new URISyntaxException("input", "reason")).when(rowCountsHelper).getRowCounts(RESOURCE);
        assertEquals(0, userRepository.size());
    }
}