package edu.pl.pollub.service;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Dell on 2017-02-11.
 */
public interface MemService {
    List<Mem> findAllMemes();

    Page<Mem> showMemesFromPage(int pageNumber) throws PageNotExistException;

    Mem addMem(MultipartFile file, String memTitle);

    Resource getFileForMem(String fileName);

    int getPagesCount();
}
