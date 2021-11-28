package com.rakuriku.rakuriku.infra;

import com.rakuriku.rakuriku.domain.model.auth.AdminsEntity;
import com.rakuriku.rakuriku.domain.repository.auth.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private final AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        AdminsEntity adminsEntity = adminRepository.findByAdminId(adminId);
        return new UserDetailsImpl(adminsEntity.getAdminId(), adminsEntity.getPassword());
    }
    
}
