package edu.evgen.shawarma.repository;

import edu.evgen.shawarma.entities.Shawarma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(exported = true)
public interface ShawarmaRepository extends JpaRepository<Shawarma,Long> {
    @Override
    @RestResource(exported = false)
    default <S extends Shawarma> S save(S entity) {
        return null;
    }

    @Override
    @RestResource(exported = false)
    default void deleteById(Long aLong) {

    }

    @Override
    @RestResource(exported = false)
    default void delete(Shawarma entity) {

    }
}
