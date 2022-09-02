package com.yunagile.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.yunagile.system.TestKVService;
import com.yunagile.system.utils.bussiness.KVService;

@Configuration
public class TestConfig {
    @Bean
    public KVService kvService() {
        return new TestKVService();
    }
}
