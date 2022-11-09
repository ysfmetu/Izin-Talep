package com.ysf.izin_module.security.services;

import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    KullaniciRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KullaniciEntity user = userRepository.findByUsername(username);

        return UserDetailsImpl.build(user);
    }

}