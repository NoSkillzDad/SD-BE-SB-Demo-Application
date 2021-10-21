package novi.nl.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityKey.class)
public class Authority {

    @Id
    private String username;

    @Id
    private String authority;

    Authority(){}

    Authority(String username, String authority){
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
