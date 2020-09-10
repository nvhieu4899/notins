package go.nvhieucs.notins.model.photo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Data
@AllArgsConstructor
@PrimaryKeyClass
public class PhotoByUserKey implements Serializable {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID userId;
    @PrimaryKeyColumn(ordinal = 0, ordering = Ordering.DESCENDING)
    private Date creationDate;
    @PrimaryKeyColumn(ordinal = 1, ordering = Ordering.ASCENDING)
    private UUID photoId;
}
