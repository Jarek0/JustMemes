package edu.pl.pollub.controller;

import edu.pl.pollub.entity.Mem;
import edu.pl.pollub.entity.enums.Status;
import edu.pl.pollub.exception.PageNotExistException;
import edu.pl.pollub.service.MemService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Mem addMem(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file,@RequestParam("memTitle") @Valid @NotNull @Size(min = 2, max = 35) String memTitle) {
        return memService.addMem(file,memTitle);
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
            return memService.showMemesFromPage(page, Status.MAIN_PAGE);
        }
        return memService.showMemesFromPage(1,Status.MAIN_PAGE);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/waiting/initialPage")
    public Page<Mem> showMemesWaitingRoom(HttpServletRequest request) throws PageNotExistException {
        Object pageObject=request.getSession().getAttribute("pageNumber");
        if(pageObject!=null) {
            int page = (int) pageObject;
            request.getSession().removeAttribute("pageNumber");
            return memService.showMemesFromPage(page, Status.ACCEPTED);
        }
        return memService.showMemesFromPage(1,Status.ACCEPTED);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/page/{pageNumber}")
    public Page<Mem> showMemesFromMainPage(@PathVariable int pageNumber) throws PageNotExistException {
        return memService.showMemesFromPage(pageNumber, Status.MAIN_PAGE);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/waiting/{pageNumber}")
    public Page<Mem> showMemesFromWaitingRoom(@PathVariable int pageNumber) throws PageNotExistException {
        return memService.showMemesFromPage(pageNumber, Status.ACCEPTED);
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
    public int getMainPagePagesCount(){
        return memService.getMainPagePagesCount(Status.MAIN_PAGE);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/waiting/getPagesCount")
    public int getWaitingRoomPagesCount(){
        return memService.getWaitingRoomPagesCount(Status.ACCEPTED);
    }

}
