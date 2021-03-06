package go.nvhieucs.notins.model.applicationUser;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.repository.AllowFiltering;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Table
@NoArgsConstructor
public class ApplicationUser {

    @PrimaryKey
    @Id
    private UUID userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String username;

    private String email;
    private Date creationDate;
    private Date lastLogin;
    private Date dateOfBirth;

    public ApplicationUser(String name, String email, Date lastLogin, Date dateOfBirth) {
        this.username = name;
        this.email = email;
        this.lastLogin = lastLogin;
        this.dateOfBirth = dateOfBirth;
    }
}
