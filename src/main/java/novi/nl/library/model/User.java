package novi.nl.library.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String password;

    private boolean enabled = true;

    private String email;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean hasAuthority(String s) {
        return authorities.stream().anyMatch(e -> e.getAuthority().equals(s));
    }

    public void addAuthority(String authorityString) {
        String s = standarizeAuthorityString(authorityString);
        if (!hasAuthority(s)) {
            this.authorities.add(new Authority(this.username, s));
        }
    }

    public void removeAuthority(String authorityString) {
        String s = standarizeAuthorityString(authorityString);
        this.authorities.removeIf(authority -> authority.getAuthority().equals(s));
    }

    private static String standarizeAuthorityString(String authorityString) {
        String s = authorityString.toUpperCase();
        if (!s.startsWith("ROLE_")) {
            s = "ROLE_" + s;
        }
        return s;
    }

}
