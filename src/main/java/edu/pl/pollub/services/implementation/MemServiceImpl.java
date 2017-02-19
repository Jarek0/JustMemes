package edu.pl.pollub.services.implementation;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.services.FileService;
import edu.pl.pollub.services.MemService;
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
    MemServiceImpl(final MemRepository memRepository, final FileService fileService){
        this.fileService=fileService;
        this.memRepository=memRepository;
    }

    //standard GRUD methods

    @Override
    @Transactional
    public void addMem(MultipartFile file,String memTitle){
        String contentType=file.getContentType();
        String fileType=contentType.substring(contentType.lastIndexOf("/") + 1);
        Mem mem=new Mem(memTitle,fileType,new Timestamp(System.currentTimeMillis()));
        mem=memRepository.save(mem);
        fileService.store(file,String.valueOf(mem.getId()),fileType);
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
        return memRepository.findAll(new PageRequest(pageNumber, 7, Sort.Direction.DESC,"createdDate"));
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
