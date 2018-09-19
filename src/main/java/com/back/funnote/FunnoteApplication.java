package com.back.funnote;

import com.back.funnote.role.Role;
import com.back.funnote.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class FunnoteApplication extends SpringBootServletInitializer {
 //implements CommandLineRunner
    private RoleRepository roleRepository;

    FunnoteApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(FunnoteApplication.class, args);
    }

/*    @Override
    public void run(String... args) {
        roleRepository.save(new Role(null,"ADMIN"));
        roleRepository.save(new Role(null,"USER"));
    }*/
}
