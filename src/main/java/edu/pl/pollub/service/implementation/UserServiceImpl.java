package edu.pl.pollub.service.implementation;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.entity.VerificationToken;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.TableIsEmptyException;
import edu.pl.pollub.repository.RoleRepository;
import edu.pl.pollub.repository.UserRepository;
import edu.pl.pollub.repository.VerificationTokenRepository;
import edu.pl.pollub.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final VerificationTokenRepository tokenRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Inject
    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository, final VerificationTokenRepository tokenRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void saveRegisteredUser(@NotNull final User user) {

        userRepository.save(user);
    }


    @Override
    @Transactional
    public User registerNewUserAccount(User accountDto) {
        accountDto.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        accountDto.setRole(roleRepository.findByName("USER"));
        accountDto.setEnabled(false);
        accountDto.setBanned(false);
        accountDto.setOnline(false);
        User savedUser = userRepository.save(accountDto);
        return savedUser;
    }

    @Transactional
    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);

        return user;
    }

    @Transactional
    @Override
    public User getByToken(String verificationToken) throws ObjectNotFoundException {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        if (user == null)
            throw new ObjectNotFoundException("there is not user with verification token: " + verificationToken);
        return user;
    }

    @Transactional
    @Override
    public User getByEmail(String email) {
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Transactional
    @Override
    public User getById(Long id) throws ObjectNotFoundException {
        User user = userRepository.findOne(id);
        if (user == null)
            throw new ObjectNotFoundException(id);
        return user;
    }

    @Transactional
    @Override
    public boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() throws TableIsEmptyException {
        List<User> users = userRepository.findAll();
        if (users.size() == 0)
            throw new TableIsEmptyException("user");
        return users;
    }

    @Transactional
    @Override
    public User delete(User user) {
        userRepository.delete(user);
        return user;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}
