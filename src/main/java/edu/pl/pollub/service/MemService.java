package edu.pl.pollub.service;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.entity.enums.Status;
import edu.pl.pollub.exception.ObjectNotFound;
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

    Page<Mem> showMemesFromPage(int pageNumber,Status status) throws PageNotExistException;

    Mem addMem(MultipartFile file, String memTitle);

    Mem findMem(long id) throws ObjectNotFound;

    Resource getFileForMem(String fileName);

    int getMainPagePagesCount(Status status);

    int getWaitingRoomPagesCount(Status status);
}
