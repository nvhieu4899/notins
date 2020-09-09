package go.nvhieucs.notins.repository;

import go.nvhieucs.notins.model.Follow;
import go.nvhieucs.notins.model.FollowerByFollowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import java.util.UUID;

public class FollowRepositoryImpl extends SimpleCassandraRepository<Follow, UUID> implements FollowRepository {

    /**
     * Create a new {@link SimpleCassandraRepository} for the given {@link CassandraEntityInformation} and
     * {@link CassandraTemplate}.
     *
     * @param metadata   must not be {@literal null}.
     * @param operations must not be {@literal null}.
     */
    @Autowired
    private CassandraTemplate cassandraTemplate;
    @Autowired
    private FollowerByFollowingRepository byFollowingRepository;

    public FollowRepositoryImpl(CassandraEntityInformation<Follow, UUID> metadata, CassandraOperations operations) {
        super(metadata, operations);
    }

    @Override
    public <S extends Follow> S insert(S entity) {
        final CassandraBatchOperations batchOperations = cassandraTemplate.batchOps();
        insertByFollowing(entity, batchOperations);
        batchOperations.insert(entity);
        batchOperations.execute();
        return entity;
    }

    private void insertByFollowing(Follow follow, final CassandraBatchOperations batchOperations) {
        batchOperations.insert(new FollowerByFollowing(follow.getFollowingId(), follow.getFollowerId(), follow.getFollowerName(), follow.getFollowingName()));
    }

    @Override
    public void delete(Follow entity) {
        final CassandraBatchOperations batchOperations = cassandraTemplate.batchOps();
        deleteByFollowing(entity, batchOperations);
        batchOperations.delete(entity);
    }

    private void deleteByFollowing(Follow follow, final CassandraBatchOperations batchOperations) {
        batchOperations.delete(byFollowingRepository.findOneByFollowingIdAndFollowerId(follow.getFollowingId(), follow.getFollowerId()));

    }
}
