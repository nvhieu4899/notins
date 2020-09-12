package go.nvhieucs.notins.model.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.util.UUID;

@Data
@PrimaryKeyClass
@AllArgsConstructor
public class FollowerByFollowingKey {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID followingId;

    @PrimaryKeyColumn(ordinal = 0, ordering = Ordering.ASCENDING)
    private UUID followerId;
}
