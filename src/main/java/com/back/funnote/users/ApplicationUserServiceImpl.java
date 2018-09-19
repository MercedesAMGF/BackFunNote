package com.back.funnote.users;

import com.back.funnote.role.Role;
import com.back.funnote.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserSequenceService userSequenceService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public ApplicationUserServiceImpl() {}

    @Override
    public List<ApplicationUser> getAllApplicationUser(){
       Sort listUserSorted = new Sort(Sort.Direction.DESC,"username");
        return this.applicationUserRepository.findAll(listUserSorted);
    }

    @Override
    public ApplicationUser saveApplicationUser(RegisterUser registerUser) {

        if (!registerUser.getPassword().equals(registerUser.getRepassword()))
            throw new RuntimeException("You must confirm your password");

        ApplicationUser usernameApplicationUserDB = applicationUserRepository.findByUsername(registerUser.getUsername());
        ApplicationUser emailApplicationUserDB = applicationUserRepository.findByEmail(registerUser.getEmail());

        if (usernameApplicationUserDB != null || emailApplicationUserDB != null)
            throw new RuntimeException("L'utilisateur existe déjà");

        ApplicationUser applicationUser = new ApplicationUser();

        int sequence = this.userSequenceService.getNextSequence();
        applicationUser.setSequence(sequence);
        applicationUser.setUsername(registerUser.getUsername());
        String hashPW = bCryptPasswordEncoder.encode(registerUser.getPassword());
        applicationUser.setPassword(hashPW);
        applicationUser.setEmail(registerUser.getEmail());
        Role role = roleRepository.findByRoleName(registerUser.getRole());
        applicationUser.getRoles().add(role);
        applicationUserRepository.save(applicationUser);

        return applicationUser;
    }

    @Override
    public ApplicationUser updateApplicationUser(RegisterUser updateUser){

       ApplicationUser applicationUserToUpdate = applicationUserRepository.findById(updateUser.getId()).get();

       applicationUserToUpdate.setUsername(updateUser.getUsername());
       String hashPW = bCryptPasswordEncoder.encode(updateUser.getPassword());
       applicationUserToUpdate.setPassword(hashPW);
       applicationUserToUpdate.setEmail(updateUser.getEmail());
       Role role = roleRepository.findByRoleName(updateUser.getRole());
       applicationUserToUpdate.getRoles().add(role);
       applicationUserRepository.save(applicationUserToUpdate);

       return applicationUserToUpdate;
    }


}
