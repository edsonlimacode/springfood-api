package com.springfood.core.security.authorizationserver;

import com.springfood.domain.model.User;
import com.springfood.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /*
    * Evita o erro -> Resolved [org.springframework.security.authentication.InternalAuthenticationServiceException: failed to lazily initialize a collection of role: com.foodapi.auth.domain.UserModel.groups, could not initialize proxy - no Session]
    * */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    public Collection<GrantedAuthority> getAuthorities(User user) {
        return user.getGroups().stream()
                .flatMap(groupModel -> groupModel.getPermissions().stream())
                .map(permissionModel -> new SimpleGrantedAuthority(permissionModel.getName().toUpperCase()))
                .collect(Collectors.toSet());
    }
}
