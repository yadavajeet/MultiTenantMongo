package com.ongraph.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.management.relation.Role;

import com.ongraph.dao.User;
import com.ongraph.dao.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.....");
        User user = userRepository.getByLoginName(username);
        System.out.println("user : "+user);
        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));


        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), grantedAuthorities);
    }
}
