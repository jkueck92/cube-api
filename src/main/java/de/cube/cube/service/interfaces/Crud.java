package de.cube.cube.service.interfaces;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface Crud<R, C, U, D> {

    Optional<R> create(C create);

    Optional<R> update(U update);

    boolean delete(D delete);

    Optional<R> findById(long id);

    List<R> findAll(Sort sort);

}
