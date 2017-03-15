package edu.pl.pollub.service;

import edu.pl.pollub.entity.Role;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.TableIsEmptyException;

import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
public interface RoleService {

    Role save(Role role);

    List<Role> getAllRoles() throws TableIsEmptyException;

    Role update(Role role);

    Role delete(Role role);

    Role getById(Long id) throws ObjectNotFoundException;

    Role getByName(String rolename) throws ObjectNotFoundException;

}
