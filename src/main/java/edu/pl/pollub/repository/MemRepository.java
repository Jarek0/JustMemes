package edu.pl.pollub.repository;

import edu.pl.pollub.entity.Mem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@Repository
public interface MemRepository extends JpaRepository<Mem,Long>{

    @Query("select m from Mem m order by m.createdDate desc")
    List<Mem> getMemesFromPage(Pageable pageable);
}
