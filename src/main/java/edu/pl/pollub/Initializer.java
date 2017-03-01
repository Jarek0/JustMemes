package edu.pl.pollub;

import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.service.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Dell on 2017-02-27.
 */
@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

    private final FileService storageService;
    private final MemRepository repository;

    @Inject
    Initializer(final FileService storageService,final MemRepository repository){
        this.repository=repository;
        this.storageService=storageService;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        storageService.deleteAll();
        repository.deleteAll();
        storageService.init();
    }

}
