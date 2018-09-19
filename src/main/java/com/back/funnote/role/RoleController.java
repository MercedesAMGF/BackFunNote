package com.back.funnote.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping
    public Role saveRole(@RequestBody Role role) {
        return this.roleRepository.save(role);
    }


    @GetMapping
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Role> getRole(@PathVariable("id") String id) {
        return this.roleRepository.findById(id);
    }

    @PutMapping("/{id}")
    public void editRole(@PathVariable String id, @RequestBody Role role) {
        Optional<Role> existingRole = roleRepository.findById(id);
        Assert.notNull(existingRole, "Role not found");
        existingRole.get().setRoleName(role.getRoleName());
        this.roleRepository.save(existingRole.get());
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") String id) {
        this.roleRepository.deleteById(id);
    }
}
