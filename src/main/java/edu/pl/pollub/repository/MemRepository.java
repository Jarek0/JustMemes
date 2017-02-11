package edu.pl.pollub.repository;

import edu.pl.pollub.entity.Mem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dell on 2017-01-26.
 */
@Repository
public interface MemRepository extends JpaRepository<Mem,Long>{
}
