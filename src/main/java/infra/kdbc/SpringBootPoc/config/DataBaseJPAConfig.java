package infra.kdbc.SpringBootPoc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "infra.kdbc.SpringBootPoc.repository", //repository 관리 패키지
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
public class DataBaseJPAConfig {
    private static final String DEFAULT_NAMING_STRATEGY
            = "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy";

    @Bean
    @Primary
    public DataSource defaultDataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSourceConfig.setJdbcUrl("jdbc:log4jdbc:oracle:thin:@172.16.1.23:1521:xe");
        dataSourceConfig.setUsername("hjj");
        dataSourceConfig.setPassword("hjj");
        dataSourceConfig.setMaximumPoolSize(10);
        dataSourceConfig.setMinimumIdle(5);
        dataSourceConfig.setMaxLifetime(1200000);
        dataSourceConfig.setConnectionTimeout(20000);
        dataSourceConfig.setIdleTimeout(300000);

        return new HikariDataSource();
    }

    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, String> propertiesHashMap = new HashMap<>();
        propertiesHashMap.put("hibernate.physical_naming_strategy", DEFAULT_NAMING_STRATEGY);

        LocalContainerEntityManagerFactoryBean rep =
                builder.dataSource(defaultDataSource())
                        .packages("infra.kdbc.SpringBootPoc.domain")
                        //domain을 관리할 패키지 경로 명시 (domain = DO 파일)
                        .properties(propertiesHashMap)
                        .build();

        return rep;
    }

    @Primary
    @Bean(name = "transactionManager")
    PlatformTransactionManager transactionManager(
            EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory(builder).getObject()));
    }

}
