package com.riskiq.controller;

import com.riskiq.config.ProxyConfig;
import com.riskiq.repository.ServiceOwnerRepository;
import com.riskiq.repository.UserRepository;
import com.riskiq.service.MetricsService;
import com.riskiq.util.RowCountsHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MetricsController.class)
public class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricsService metricsService;
    @MockBean
    private ProxyConfig proxyConfig;
    @MockBean
    private RowCountsHelper rowCountsHelper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ServiceOwnerRepository serviceOwnerRepository;

    @Test
    public void allMetrics() throws Exception {

        final Map<String, Long> extractRowCounts = new HashMap<String, Long>() {{
            put("a", 1L);
            put("c", 2L);
        }};
        when(metricsService.extractRowCounts()).thenReturn(extractRowCounts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/metrics").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "[{\"repository\":\"a\",\"rows\":1},{\"repository\":\"c\",\"rows\":2}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void allMetricsEmpty() throws Exception {

        final Map<String, Long> extractRowCounts = new HashMap<String, Long>();
        when(metricsService.extractRowCounts()).thenReturn(extractRowCounts);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/metrics").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(404, result.getResponse().getStatus());
    }
}
