package go.nvhieucs.notins.repository;


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
public class PhotoConfig {

    @Bean
    public PhotoRepository photoRepository(final CassandraOperations cassandraTemplate) {
        final CassandraPersistentEntity<?> entity = cassandraTemplate.getConverter().getMappingContext().getPersistentEntity(Photo.class);
        final CassandraEntityInformation<Photo, UUID> entityInformation = new MappingCassandraEntityInformation<Photo, UUID>(
                (CassandraPersistentEntity <Photo >) entity, cassandraTemplate.getConverter());
        return new PhotoRepositoryImpl(entityInformation,cassandraTemplate);
    }

}
