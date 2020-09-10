package go.nvhieucs.notins.model.photo;


import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoByUserRepository extends CassandraRepository<PhotoByUser, PhotoByUserKey> {

}
