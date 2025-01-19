package com.ensem.gestiondoctorants.service;

import com.ensem.gestiondoctorants.model.Admin;
import com.ensem.gestiondoctorants.model.Doctorant;
import com.ensem.gestiondoctorants.repository.AdminRepository;
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
    private AdminRepository adminRepository;

    @Autowired
    private DoctorantRepository doctorantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("admin")) {
            // Fetch admin details
            Admin admin = adminRepository.findByUsername(username);
            if (admin == null) {
                throw new UsernameNotFoundException("Admin not found: " + username);
            }
            return User.builder()
                    .username(admin.getUsername())
                    .password(admin.getPassword()) // {noop} indicates no hashing for plain text
                    .roles(admin.getRole()) // Do NOT prefix ROLE_ here
                    .build();
        } else {
            // Fetch doctorant details
            Doctorant doctorant = doctorantRepository.findByCne(username);
            if (doctorant == null) {
                throw new UsernameNotFoundException("Doctorant not found: " + username);
            }
            return User.builder()
                    .username(doctorant.getCne())
                    .password(doctorant.getCin()) // {noop} for plain-text CIN password
                    .roles(doctorant.getRole()) // Do NOT prefix ROLE_ here
                    .build();
        }
    }
}
