package go.nvhieucs.notins.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Table
public class ApplicationUser {

    @PrimaryKey
    @Id
    private UUID userId;

    @JsonIgnore
    private String password;
    private String username;

    private String email;
    private Date creationDate;
    private Date lastLogin;
    private Date dateOfBirth;

    public ApplicationUser( String name, String email, Date lastLogin, Date dateOfBirth) {
        this.username = name;
        this.email = email;
        this.lastLogin = lastLogin;
        this.dateOfBirth = dateOfBirth;
    }
}
