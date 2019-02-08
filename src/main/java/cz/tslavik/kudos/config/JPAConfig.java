package cz.tslavik.kudos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
//@PropertySource({ "classpath:persistence-multiple-db.properties" })
@EnableJpaRepositories(
        basePackages = "cz.tslavik.Kudosservice.repository.sql",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
public class JPAConfig {
    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(
                new String[] { "cz.tslavik.Kudosservice.repository.sql" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("spring.jpa.hibernate.ddl-auto","update");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.format_sql","true");
        properties.put("hibernate.naming.physical-strategy","org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        //properties.put("spring.jpa.properties.hibernate.current_session_context_class","thread");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource userDataSource() {

        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new org.hsqldb.jdbc.JDBCDriver());
        dataSource.setUrl("jdbc:hsqldb:mem:mydb");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

//        DriverManagerDataSource dataSource
//                = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSource.setUrl("jdbc:postgresql://eva.postgres.database.azure.com:5432/Kudoss");
//        dataSource.setUsername("postgres@eva");
//        dataSource.setPassword("password1.");

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                userEntityManager().getObject());
        return transactionManager;
    }
}
