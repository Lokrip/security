package com.boots.security.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

//@MappedSuperclass — это аннотация в JPA (Java Persistence API), 
//которая указывает, что класс является суперклассом для других сущностей, 
//но сам по себе не является сущностью и 
//не создает отдельную таблицу в базе данных
//1. Определяет базовый класс для нескольких сущностей.
//2. Не создает отдельную таблицу в базе данных.
//3. Наследуемые поля (аннотированные @Column, @Id и т. д.) будут включены в таблицы дочерних классов.
//4. Не поддерживает аннотацию @Entity, так как сам класс не является сущностью.
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
