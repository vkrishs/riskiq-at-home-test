package com.riskiq.util;

import com.google.common.io.Resources;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class RowCountsHelperTest {

    private static final URL RESOURCE = Resources.getResource("testGetSize.txt");

    private RowCountsHelper rowCountsHelper;

    @Before
    public void setUp() {
        rowCountsHelper = spy(new RowCountsHelper());
    }

    @After
    public void tearDown() {
        rowCountsHelper = null;
    }

    @Test
    public void testGetRowCounts() throws IOException, URISyntaxException {

        final long actualRowCounts = rowCountsHelper.getRowCounts(RESOURCE);
        assertEquals(12L, actualRowCounts);
    }
}
