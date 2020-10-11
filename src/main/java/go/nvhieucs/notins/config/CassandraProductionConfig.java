package go.nvhieucs.notins.config;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.metadata.EndPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.net.InetSocketAddress;
import java.nio.file.Path;
import java.nio.file.Paths;

@Profile("production")
@Configuration
@EnableCassandraRepositories(basePackages = "go.nvhieucs.notins.model")
public class CassandraProductionConfig extends AbstractCassandraConfiguration {
    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.contact-point}")
    private String contactPoint;

    @Value("${spring.data.cassandra.port}")
    private Integer port;

    @Value("${spring.data.cassandra.username}")
    private String username;

    @Value("${spring.data.cassandra.password}")
    private String password;

    @Value("${spring.data.cassandra.local-datacenter}")
    private String datacenter;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }


    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"go.nvhieucs.notins.model"};
    }

    @Override
    protected String getContactPoints() {
        return contactPoint;
    }

    @Override
    protected int getPort() {
        return port;
    }



    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }


    @Bean
    @Primary
    public CqlSession session() {
        return CqlSession.builder().withAuthCredentials(username, password)
                .withCloudSecureConnectBundle(Paths.get("secure-connect-notinstagram.zip"))
                .withLocalDatacenter("production",datacenter)
                .withKeyspace(keyspaceName).build();
    }
}
