package edu.pl.pollub.service.implementation;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.service.FileService;
import edu.pl.pollub.service.MemService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@Service
public class MemServiceImpl implements MemService{

    //fields

    private final MemRepository memRepository;


    private final FileService fileService;

    //constructors

    @Inject
    public MemServiceImpl(final MemRepository memRepository, final FileService fileService){
        this.fileService=fileService;
        this.memRepository=memRepository;
    }

    //standard GRUD methods

    @Override
    @Transactional
    public Mem addMem(MultipartFile file,String memTitle){
        Mem mem=new Mem(memTitle,file.getContentType(),new Timestamp(System.currentTimeMillis()));
        mem=memRepository.save(mem);
        fileService.store(file,String.valueOf(mem.getId()),mem.getFileType());
        return mem;
    }

    //additional methods

    @Override
    @Transactional
    public List<Mem> findAllMemes(){
        return memRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Mem> showMemesFromPage(int pageNumber) throws PageNotExistException {
        Page<Mem> page=memRepository.findAll(new PageRequest(pageNumber-1, 7, Sort.Direction.DESC,"createdDate"));
        if(page.hasContent())
        return page;

        throw new PageNotExistException(pageNumber);
    }

    @Override
    @Transactional
    public Resource getFileForMem(String fileName){
        return fileService.loadAsResource(fileName);
    }


    @Override
    @Transactional
    public int getPagesCount(){
        return (int) Math.ceil(((double)memRepository.count())/7);
    }
}
