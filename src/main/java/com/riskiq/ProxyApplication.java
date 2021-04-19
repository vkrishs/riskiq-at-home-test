package com.riskiq;

import com.google.common.io.Resources;
import com.riskiq.config.ProxyConfig;
import com.riskiq.repository.ServiceOwnerRepository;
import com.riskiq.repository.UserRepository;
import com.riskiq.util.RowCountsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProxyApplication {

    private ProxyConfig proxyConfig;
    private RowCountsHelper rowCountsHelper;

    @Autowired
    public void setProxyConfig(ProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    @Autowired
    public void setRowCountsHelper(RowCountsHelper rowCountsHelper) {
        this.rowCountsHelper = rowCountsHelper;
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepository(Resources.getResource(proxyConfig.getUserFile()), rowCountsHelper);
    }

    @Bean
    ServiceOwnerRepository serviceOwnerRepository() {
        return new ServiceOwnerRepository(Resources.getResource(proxyConfig.getUserFile()), rowCountsHelper);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }
}
