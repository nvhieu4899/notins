package go.nvhieucs.notins.repository;


import go.nvhieucs.notins.model.FollowerByFollowing;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FollowerByFollowingRepository extends CassandraRepository<FollowerByFollowing, UUID> {
    Optional<FollowerByFollowing> findOneByFollowingIdAndFollowerId(UUID following, UUID follower);
}
