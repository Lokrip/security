package com.boots.security.entity;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity implements GrantedAuthority {
    // Реализует интерфейс GrantedAuthority, который требуется Spring Security для работы с ролями

    private String title;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<UserEntity> users;

    public RoleEntity() {
    }

    public RoleEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        // Метод из интерфейса GrantedAuthority, Spring Security использует его для проверки ролей пользователя
        return getTitle();
    }
}
