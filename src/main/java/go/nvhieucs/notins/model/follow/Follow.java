package go.nvhieucs.notins.model.follow;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Data
@AllArgsConstructor
public class Follow {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID followerId;

    @PrimaryKeyColumn(ordinal = 0, ordering = Ordering.ASCENDING)
    private UUID followingId;

    private String followerName;
    private String followingName;

}
