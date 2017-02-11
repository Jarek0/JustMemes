package edu.pl.pollub.services;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Dell on 2017-02-11.
 */
public interface MemService {
    List<Mem> findAllMemes();

    List<Mem> showMemesFromPage(int pageNumber) throws PageNotExistException;

    void addMem(MultipartFile file, Mem mem);

    Resource getFileForMem(String fileName);
}
