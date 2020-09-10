package go.nvhieucs.notins.model.photo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
@NoArgsConstructor
public class PhotoByUser {
    @PrimaryKey
    private PhotoByUserKey key;

    private String photoPath;

    private Double photoLat;
    private Double photoLng;

    private Double userLat;
    private Double userLng;

}
