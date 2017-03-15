package edu.pl.pollub.repository;

import edu.pl.pollub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dell on 2017-03-15.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
