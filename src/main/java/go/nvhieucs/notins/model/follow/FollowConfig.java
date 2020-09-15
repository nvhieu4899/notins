package go.nvhieucs.notins.model.follow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import java.util.UUID;

@Configuration
public class FollowConfig {
    @Bean
    public FollowRepositoryImpl followRepository(final CassandraTemplate cassandraTemplate) {
        final CassandraPersistentEntity<?> persistentEntity = cassandraTemplate.getConverter()
                .getMappingContext().getRequiredPersistentEntity(Follow.class);
        final CassandraEntityInformation<Follow, FollowKey> entityInformation
                = new MappingCassandraEntityInformation<Follow, FollowKey>(
                (CassandraPersistentEntity<Follow>) persistentEntity, cassandraTemplate.getConverter());
        return new FollowRepositoryImpl(entityInformation, cassandraTemplate);
    }
}
