package by.shalukho.service;

import by.shalukho.dto.UserDto;
import by.shalukho.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDto user = userService.getUser(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(), user.getPassword(), true, true, true,
                true, getAuthorities(RoleEnum.ADMIN));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            RoleEnum role) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        List<GrantedAuthority> authorities = Arrays.asList(simpleGrantedAuthority);
        return authorities;
    }

}
