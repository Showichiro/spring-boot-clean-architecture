package com.example.cleanarchitecture.core.domain;

import java.util.List;
import java.util.Optional;

public interface IGateway<S, ID> {
    S create(S product);

    S update(S product);

    void delete(ID id);

    Optional<S> findById(ID id);

    List<S> findAll();
}
