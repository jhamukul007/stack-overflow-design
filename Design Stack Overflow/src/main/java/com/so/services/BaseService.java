package com.so.services;

import lombok.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {

    default T saveOrUpdate(T value){
        return value;
    }

    @Transactional
    default T saveOrUpdateTx(T value){
        return value;
    }

    Optional<T> findById(@NonNull Long id);

    default <S extends T> List<S> saveAll(List<S> entities){
        return entities;
    }

}
