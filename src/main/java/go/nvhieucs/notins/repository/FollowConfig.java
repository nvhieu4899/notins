package go.nvhieucs.notins.repository;

import go.nvhieucs.notins.model.Follow;
import go.nvhieucs.notins.model.Photo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import java.util.UUID;

@Configuration
public class FollowConfig {
    @Bean
    public FollowRepository followRepository(final CassandraTemplate cassandraTemplate) {

        final CassandraPersistentEntity<?> persistentEntity = cassandraTemplate.getConverter().getMappingContext().getRequiredPersistentEntity(Follow.class);
        final CassandraEntityInformation<Follow, UUID> entityInformation = new MappingCassandraEntityInformation<Follow, UUID>((CassandraPersistentEntity<Follow>) persistentEntity, cassandraTemplate.getConverter());
        return new FollowRepositoryImpl(entityInformation , cassandraTemplate);
    }
}
