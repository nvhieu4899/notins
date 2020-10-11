package go.nvhieucs.notins.model.photo;


import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    @Id
    @PrimaryKeyColumn
    private UUID photoId;

    private String photoPath;

    private UUID userId;

    private Double photoLat;
    private Double photoLng;

    private Double userLat;
    private Double userLng;

    private Date creationDate;

    public Photo(String photoPath,UUID userId, Double photoLat, Double photoLng, Double userLat, Double userLng) {
        this.photoId = Uuids.timeBased();
        this.userId = userId;
        this.photoPath = photoPath;
        this.photoLat = photoLat;
        this.photoLng = photoLng;
        this.userLat = userLat;
        this.userLng = userLng;
        this.creationDate = Calendar.getInstance().getTime();
    }
}
