package go.nvhieucs.notins.model.follow;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface FollowRepository extends CassandraRepository<Follow, FollowKey> {
    List<UUID> findFollowingIdsByFollowerId(UUID followerId);
}
