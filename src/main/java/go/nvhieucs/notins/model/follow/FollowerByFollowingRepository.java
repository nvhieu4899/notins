package go.nvhieucs.notins.model.follow;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowerByFollowingRepository extends CassandraRepository<FollowerByFollowing, UUID> {
    Optional<FollowerByFollowing> findOneByFollowingIdAndFollowerId(UUID following, UUID follower);
}
