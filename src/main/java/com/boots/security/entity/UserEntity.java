package com.boots.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "\"user\"")
public class UserEntity extends BaseEntity implements UserDetails {
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        //Описывает промежуточную таблицу users_roles, которая связывает User и Role.
        name = "users_roles",
        //joinColumns = @JoinColumn(...) — описывает столбец в этой таблице, который связан с User
        //name="user_id" — название столбца в users_roles, который хранит ID пользователя.
        //referencedColumnName = "id" — этот столбец (user_id) ссылается на id в таблице User.
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
        //inverseJoinColumns = @JoinColumn(...) — описывает столбец, который связан с Role
        //name="role_id" — название столбца в users_roles, который хранит ID роли.
        //referencedColumnName = "id" — этот столбец (role_id) ссылается на id в таблице Role.
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<BookEntity> books = new ArrayList<>();

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String getUsername() { // Метод из UserDetails, возвращает имя пользователя
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() { // Метод из UserDetails, возвращает пароль пользователя
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isAccountNonExpired() { // Проверяет, не истек ли срок действия аккаунта
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() { // Проверяет, не заблокирован ли аккаунт
        // TODO Auto-generated method stub
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() { // Проверяет, не истекли ли учетные данные (пароль)
        // TODO Auto-generated method stub
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() { // Проверяет, активирован ли аккаунт пользователя
        // TODO Auto-generated method stub
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращает роли пользователя, которые реализуют GrantedAuthority (необходимо для работы Spring Security)
        return getRoles();
    }

    public UserDetails orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
