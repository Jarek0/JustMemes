package edu.pl.pollub.service;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.TableIsEmptyException;

import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
public interface UserService {

    void saveRegisteredUser(User user);

    User registerNewUserAccount(User accountDto);

    User getByUsername(String username);

    User getByToken(String verificationToken) throws ObjectNotFoundException;

    User getByEmail(String email);

    User getById(Long id) throws ObjectNotFoundException;

    List<User> getAllUsers() throws TableIsEmptyException;

    User delete(User user);

    User update(User user);

    boolean emailExist(String email);
}
