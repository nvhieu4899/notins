package go.nvhieucs.notins.model.applicationUser;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<ApplicationUser, UUID> {
    @AllowFiltering
    ApplicationUser findOneByUsernameOrEmail(String user);

    @AllowFiltering
    ApplicationUser findOneByUsername(String user);

    @AllowFiltering
    boolean existsByUsername(String username);
}
