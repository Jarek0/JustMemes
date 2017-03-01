package edu.pl.pollub.repository;

import edu.pl.pollub.entity.Mem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * Created by Dell on 2017-02-27.
 */
@RunWith(SpringRunner.class)
@DataJpaTest

public class MemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private MemRepository memRepository;

    private static Validator validator;

    @Before
    public void storeTestDate(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        for(int i=0;i<17;i++){
            entityManager.persist(new Mem("test"+i,"image/jpeg", new Timestamp(i)));
            System.out.println(i);
        }

    }



    @Test
    public void getMemesFromPageTest(){
        Page<Mem> pages=memRepository.findAll(new PageRequest(1, 7, Sort.Direction.DESC,"createdDate"));
        int i=9;
        for (Mem mem:pages) {
            System.out.println(i+","+mem.getTitle());

            i--;
        }


    }

    @Test
    public void addMemeWithNullNameTest(){
        Mem mem=memRepository.save(new Mem(null,"image/jpeg",new Timestamp(1)));
        Set<ConstraintViolation<Mem>> constraintViolations =
                validator.validate( mem );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "may not be null",
                constraintViolations.iterator().next().getMessage()
        );

    }

    @Test
    public void addMemWithInvalidTitleTest(){
        Mem mem=memRepository.save(new Mem("a","image/jpeg",new Timestamp(1)));
        Set<ConstraintViolation<Mem>> constraintViolations =
                validator.validate( mem );

        assertEquals( 1, constraintViolations.size() );
        assertEquals(
                "size must be between 2 and 35",
                constraintViolations.iterator().next().getMessage()
        );
    }

    @Test
    public void addMemWithInvalidFileTypeTest(){
        Mem mem=memRepository.save(new Mem("testMem","image/jpegz",new Timestamp(1)));
        Set<ConstraintViolation<Mem>> constraintViolations =
                validator.validate( mem );
        ArrayList<ConstraintViolation<Mem>> constraintViolationsList=new ArrayList<>();
        constraintViolationsList.addAll(constraintViolations);

        assertEquals( 2, constraintViolations.size() );
        assertEquals(
                "invalid file type"
                ,
                constraintViolationsList.get(0).getMessage()
        );
        assertEquals(
                "size must be between 3 and 4",
                constraintViolationsList.get(1).getMessage()
        );
    }

}
