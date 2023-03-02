package com.security.API_App.user;

import com.security.API_App.token.Token;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data // getters, setters, constructor, equals, hashcode
@Builder // design pattern builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // exista deja o tabela user in DB
public class User implements UserDetails {
    @Id
    @GeneratedValue// default value is AUTO --> (strategy = GenerationType.AUTO) // autogenereaza id-ul
    private Integer id; // PK
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING) // takes a String values of enum
    private Role role;

    //@Column(columnDefinition = "boolean default false")
    private Boolean enabled;
    //@Column(columnDefinition = "boolean default false")
    private Boolean loggedin;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // List of ROLEs
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
