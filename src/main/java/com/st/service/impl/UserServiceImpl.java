package com.st.service.impl;

import com.st.entity.User;
import com.st.entity.UserRole;
import com.st.repository.UserRepository;
import com.st.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        UserDetails user = (UserDetails) repository.getByNick(nick);
        org.springframework.security.core.userdetails.User.UserBuilder builder;
        builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
        builder.roles("USER");

        return builder.build();
    }

    @Override
    public UserDetails create(User user) {
        user.setRole(UserRole.USER.getId());
        return (UserDetails) repository.create(user);
    }

    @Override
    public UserDetails getByNick(String nick) {
        return (UserDetails) repository.getByNick(nick);
    }

    @Override
    public UserDetails getById(Long id) {
        return (UserDetails) repository.getById(id);
    }

    @Override
    public Long searchUserIdByUserNick(String nick) {
        return repository.getByNick(nick).getId();
    }

}
