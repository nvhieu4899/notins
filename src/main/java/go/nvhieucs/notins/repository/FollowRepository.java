package go.nvhieucs.notins.repository;

import go.nvhieucs.notins.model.Follow;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowRepository extends CassandraRepository<Follow, UUID> {
}
