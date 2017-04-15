package edu.pl.pollub.initializer;

import edu.pl.pollub.entity.Role;
import edu.pl.pollub.entity.User;
import edu.pl.pollub.repository.MemRepository;
import edu.pl.pollub.repository.RoleRepository;
import edu.pl.pollub.repository.UserRepository;
import edu.pl.pollub.service.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Created by Dell on 2017-02-27.
 */
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final FileService storageService;
    private final MemRepository repository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    DataInitializer(final FileService storageService, final MemRepository repository, final UserRepository userRepository, final RoleRepository roleRepository, final BCryptPasswordEncoder bCryptPasswordEncoder){
        this.repository=repository;
        this.storageService=storageService;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        storageService.deleteAll();
        repository.deleteAll();
        storageService.init();
        if (roleRepository.findByName("ROOT") == null) {
            Role root = new Role("ROOT");
            roleRepository.save(root);

        }
        if (roleRepository.findByName("USER") == null) {
            Role role = new Role("USER");
            roleRepository.save(role);
        }
        if (userRepository.findByUsername("testUser") == null){
            User user = new User("testUser", bCryptPasswordEncoder.encode("poziomd123"), "jery2@o2.pl", true, false, false, null, roleRepository.findByName("ROOT"));
            userRepository.save(user);
        }
    }

}
