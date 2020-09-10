package go.nvhieucs.notins.model.follow;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface FollowRepository extends CassandraRepository<Follow, UUID> {
}
