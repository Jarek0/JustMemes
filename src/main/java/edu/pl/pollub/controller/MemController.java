package edu.pl.pollub.controller;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.services.MemService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    @RequestMapping(method = RequestMethod.POST)
    public void addMem(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,@Valid @NotNull @NotBlank String memTitle) {
        memService.addMem(file,memTitle);
    }

}
