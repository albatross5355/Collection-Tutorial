    package com.example.HomeLoanApp.homeloansecurity.modal;

    import lombok.*;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;

    import javax.persistence.*;
    import java.time.LocalDateTime;
    import java.util.Collection;
    import java.util.List;

    @Entity
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "users")
    public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private int id;


        @Column(name = "user_name")
        private String  username;

        @Column(name = "password")
        private String  password;


        @Column(name = "register_date")
        private LocalDateTime date;


        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(unique = true)
        private String email;

        @Column(name = "status")
        private boolean isEnabled;

        @Enumerated(EnumType.STRING)
        private Role role;
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
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
    }