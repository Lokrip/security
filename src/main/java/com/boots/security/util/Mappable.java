package com.boots.security.util;

import java.util.List;

public interface Mappable<T, D> {
    D toDTO(T entity);
    T toEntity(D dto);

    List<D> toDTO(List<T> entities);
    List<T> toEntity(List<D> dtos);
}
