package go.nvhieucs.notins.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;

@Configuration
public class CassandraConfig  extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspaceName;

    @Value("${spring.data.cassandra.contact-point}")
    private String contactPoint;

    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @Override
    protected String getContactPoints() {
        return contactPoint;
    }

    @Override
    protected int getPort() {
        return 9042;
    }

    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }


    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE;
    }

}
