package edu.pl.pollub.services.implementation;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.exception.StorageException;
import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.services.FileService;
import edu.pl.pollub.services.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@Service
public class MemServiceImpl implements MemService{
    @Autowired
    private MemRepository memRepository;

    @Autowired
    private FileService fileService;

    @Override
    @Transactional
    public List<Mem> findAllMemes(){
        return memRepository.findAll();
    }

    @Override
    @Transactional
    public List<Mem> showMemesFromPage(int pageNumber) throws PageNotExistException {
        List<Mem> memes=memRepository.findAll();
        if(memes.size()<(pageNumber*7+pageNumber)){
            throw new PageNotExistException(pageNumber);
        }

        return memes.subList(pageNumber*7+pageNumber,(pageNumber+1)*7+pageNumber > memes.size() ? memes.size() : (pageNumber+1)*7+pageNumber);

    }

    @Override
    @Transactional
    public void addMem(MultipartFile file,String memTitle){
        String fileType=file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1);
        Mem mem=new Mem(memTitle,fileType);
        mem=memRepository.save(mem);
        fileService.store(file,String.valueOf(mem.getId()),fileType);
    }

    @Override
    @Transactional
    public Resource getFileForMem(String fileName){
        return fileService.loadAsResource(fileName);
    }
}
