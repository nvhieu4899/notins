package go.nvhieucs.notins.model.photo;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface PhotoRepository extends CassandraRepository<Photo, UUID> {

}
