package de.cube.cube.database.repository;

import de.cube.cube.database.entity.Permission;
import de.cube.cube.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(final String name);

}
