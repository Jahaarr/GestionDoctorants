package com.ensem.gestiondoctorants.service;

import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.repository.DoctorantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DoctorantRepository doctorantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Doctorant doctorant = doctorantRepository.findByCne(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Do not prefix ROLE_ here; Spring Security will do it automatically.
        return User.builder()
                .username(doctorant.getCne())
                .password(doctorant.getCin()) // Use {noop} for plain-text passwords
                .roles(doctorant.getRole()) // Ensure database has roles like "DOCTORANT" or "ADMIN"
                .build();
    }
}
