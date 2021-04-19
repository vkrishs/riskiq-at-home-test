package com.riskiq;

import com.riskiq.config.ProxyConfig;
import com.riskiq.repository.ServiceOwnerRepository;
import com.riskiq.repository.UserRepository;
import com.riskiq.service.MetricsService;
import com.riskiq.util.RowCountsHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyApplicationTests {

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
    public void contextLoads() {
    }

}
