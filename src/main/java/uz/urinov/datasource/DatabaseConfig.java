package uz.urinov.datasource;

import jakarta.persistence.EntityManagerFactory;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EntityScan(value = "uz.urinov")
public class DatabaseConfig {

    @Bean("readWrite")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource readWriteConfiguration() {
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.read")
    @Bean("readOnly")
    public DataSource readOnlyConfiguration() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary // Asosiy
    public DataSource routingDataSource(@Qualifier(value = "readOnly") DataSource read, @Qualifier(value = "readWrite") DataSource write) {

        DataSource masterAddLogger = loggingProxy("db1", write);

        DataSource slaveAddLogger = loggingProxy("db2", read);

        TransactionRoutingDataSource resultDataSource = new TransactionRoutingDataSource(masterAddLogger, slaveAddLogger);

        return resultDataSource;
    }

    private DataSource loggingProxy(String name, DataSource dataSource) {
        SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
        loggingListener.setLogLevel(SLF4JLogLevel.INFO);
        loggingListener.setLogger(name);
        loggingListener.setWriteConnectionId(false);
        return ProxyDataSourceBuilder.create(dataSource).name(name).listener(loggingListener).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder.dataSource(dataSource).packages("uz.urinov").build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(@Qualifier("jpaTxManager") PlatformTransactionManager wrapped) {
        return new ReplicaAwareTransactionManager(wrapped);
    }

    @Bean(name = "jpaTxManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
