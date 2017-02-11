package edu.pl.pollub.controller;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.services.FileService;
import edu.pl.pollub.services.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@RestController
@RequestMapping(value = "/mem")
public class MemController {

    @Autowired
    private MemService memService;



    @RequestMapping(method = RequestMethod.GET)
    public List<Mem> findAllMemes(){
        return memService.findAllMemes();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/page/{pageNumber}")
    public List<Mem> showMemesFromPage(@PathVariable int pageNumber) throws PageNotExistException {
        return memService.showMemesFromPage(pageNumber);
    }

    @RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getFileForMem(@PathVariable String id) {

        Resource file = memService.getFileForMem(id);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMem(@RequestParam("file") MultipartFile file, @Valid @RequestBody Mem mem) {
        memService.addMem(file,mem);
    }

}
