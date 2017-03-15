package edu.pl.pollub.service.implementation;

import edu.pl.pollub.entity.Role;
import edu.pl.pollub.exception.ObjectNotFoundException;
import edu.pl.pollub.exception.TableIsEmptyException;
import edu.pl.pollub.repository.RoleRepository;
import edu.pl.pollub.repository.UserRepository;
import edu.pl.pollub.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Dell on 2017-03-15.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Inject
    public RoleServiceImpl(final RoleRepository roleRepository, final UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Role save(@NotNull @Valid final Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() throws TableIsEmptyException {
        List<Role> roles = roleRepository.findAll();
        if (roles.size() == 0)
            throw new TableIsEmptyException("role");
        return roles;
    }

    @Override
    @Transactional
    public Role update(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role delete(Role role) {
        roleRepository.delete(role);
        return role;
    }

    @Override
    @Transactional
    public Role getById(Long id) throws ObjectNotFoundException {
        Role role = roleRepository.findOne(id);
        if (role == null) throw new ObjectNotFoundException(id);
        return role;
    }

    @Override
    @Transactional
    public Role getByName(String rolename) throws ObjectNotFoundException {
        Role role = roleRepository.findByName(rolename);
        if (role == null) throw new ObjectNotFoundException(rolename);
        return role;
    }
}
