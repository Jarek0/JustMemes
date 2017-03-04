package edu.pl.pollub.repository;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.entity.enums.Status;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
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

    @Query("SELECT m FROM Mem m WHERE m.status = ?1")
    Page<Mem> findAll(Status status,Pageable page);

    @Query("SELECT COUNT (m) FROM Mem m WHERE m.status = ?1")
    int count(Status status);

}
