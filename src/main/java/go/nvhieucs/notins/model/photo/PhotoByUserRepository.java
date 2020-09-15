package go.nvhieucs.notins.model.photo;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface PhotoByUserRepository extends CassandraRepository<PhotoByUser, PhotoByUserKey> {
    Slice<PhotoByUser> findFirst100ByKeyUserIdIsInOrderByKeyCreationDateDesc(List<UUID> userIds, Pageable pageable);

    Slice<PhotoByUser> findByKeyUserId(UUID userId, Pageable pageable);
}
