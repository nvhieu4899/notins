package go.nvhieucs.notins.repository;

import go.nvhieucs.notins.model.ApplicationUser;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<ApplicationUser, UUID> {
    Optional<ApplicationUser> findOneByUsernameOrEmail(String user);
    Optional<ApplicationUser> findOneByUsername(String user);
    Optional<ApplicationUser> existsByUsername(String username);
}
