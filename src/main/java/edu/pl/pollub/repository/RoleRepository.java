package edu.pl.pollub.repository;

import edu.pl.pollub.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dell on 2017-03-15.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String rolename);
}
