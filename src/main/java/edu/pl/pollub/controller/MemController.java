package edu.pl.pollub.controller;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.services.MemService;
import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@RestController
@RequestMapping(value = "/mem")
public class MemController {

    //fields

    private final MemService memService;

    //constructors

    @Inject
    MemController(final MemService memService){
        this.memService=memService;
    }

    //Standard GRUD methods

    @RequestMapping(method = RequestMethod.POST)
    public void addMem(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,@Valid @NotNull @NotBlank String memTitle) {
        memService.addMem(file,memTitle);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Mem> findAllMemes(){
        return memService.findAllMemes();
    }

    //Additional methods

    @RequestMapping(method = RequestMethod.GET,value = "/initialPage")
    public Page<Mem> showMemesFromPage(HttpServletRequest request) throws PageNotExistException {
        Object pageObject=request.getSession().getAttribute("pageNumber");
        if(pageObject!=null) {
            int page = (int) pageObject;
            request.getSession().removeAttribute("pageNumber");
            return memService.showMemesFromPage(page);
        }
        return memService.showMemesFromPage(1);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/page/{pageNumber}")
    public Page<Mem> showMemesFromPage(@PathVariable int pageNumber) throws PageNotExistException {
        return memService.showMemesFromPage(pageNumber);
    }

    @RequestMapping(value = "/getFile/{fileName}/{fileType}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, @PathVariable String fileType){
        Resource file = memService.getFileForMem(fileName+"."+fileType);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getPagesCount")
    public int getPagesCount(){
        return memService.getPagesCount();
    }

}
