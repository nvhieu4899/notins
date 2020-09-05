package go.nvhieucs.notins.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@Table
public class PhotoByUser {
    @PrimaryKey
    private PhotoByUserKey key;

    private String photoPath;

    private Double photoLat;
    private Double photoLng;

    private Double userLat;
    private Double userLng;

}
