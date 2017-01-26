package edu.pl.pollub.controller;

import edu.pl.pollub.entity.Image;
import edu.pl.pollub.entity.request.AddImageRequest;
import edu.pl.pollub.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@RestController(value = "image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Image> findAllImages(){
        return imageService.findAllImages();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addImage(@RequestBody AddImageRequest addImageRequest){
       imageService.addImage(addImageRequest);
    }
}
