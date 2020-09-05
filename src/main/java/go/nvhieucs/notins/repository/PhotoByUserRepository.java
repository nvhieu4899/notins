package go.nvhieucs.notins.repository;


import go.nvhieucs.notins.model.PhotoByUser;
import go.nvhieucs.notins.model.PhotoByUserKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoByUserRepository extends CassandraRepository<PhotoByUser, PhotoByUserKey> {

}
