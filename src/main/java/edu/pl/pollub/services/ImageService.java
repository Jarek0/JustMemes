package edu.pl.pollub.services;

import edu.pl.pollub.entity.Image;
import edu.pl.pollub.entity.request.AddImageRequest;
import edu.pl.pollub.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Dell on 2017-01-26.
 */
@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findAllImages(){
        return imageRepository.findAll();
    }

    public void addImage(AddImageRequest addImageRequest){
        Image image=new Image();
        image.setPath(addImageRequest.getPath());
        imageRepository.save(image);
    }

}
