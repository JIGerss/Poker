package com.steveny.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({"com.steveny.service", "com.steveny.mapper"})
@PropertySource("classpath:jdbc.properties")
@Import({JdbcConfig.class, MybatisConfig.class})
public class SpringConfig {
}
