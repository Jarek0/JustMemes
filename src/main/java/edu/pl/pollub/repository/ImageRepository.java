package edu.pl.pollub.repository;

import edu.pl.pollub.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dell on 2017-01-26.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image,Long>{
}
