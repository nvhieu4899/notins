package go.nvhieucs.notins.repository;

import go.nvhieucs.notins.model.Photo;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@NoRepositoryBean
public interface PhotoRepository extends CassandraRepository<Photo, UUID> {

}
