package fr.miage.core.service.impl;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.Role;
import fr.miage.core.entity.User;
import fr.miage.core.repository.RoleRepository;
import fr.miage.core.repository.UserRepository;
import fr.miage.core.service.RoleService;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        final Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        }
        return null;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

}