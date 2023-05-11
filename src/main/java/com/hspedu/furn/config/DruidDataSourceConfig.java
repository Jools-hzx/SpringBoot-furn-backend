package com.hspedu.furn.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Zexi He.
 * @date 2023/5/11 10:53
 * @description:
 *      配置德鲁伊数据源
 */

@Configuration
@Slf4j
public class DruidDataSourceConfig {

    //配置/注入 DruidDataSource
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        log.info("数据源={}", dataSource.getClass());
        return dataSource;
    }
}
