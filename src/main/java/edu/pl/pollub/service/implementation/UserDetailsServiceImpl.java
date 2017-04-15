package edu.pl.pollub.service.implementation;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.exception.ExtendedAuthException;
import edu.pl.pollub.repository.UserRepository;
import edu.pl.pollub.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Dell on 2017-03-15.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private LoginAttemptService loginAttemptService;


    @Autowired
    private HttpServletRequest request;


    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws ExtendedAuthException {

        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new ExtendedAuthException("You tried to login more than 5 times. So your possibility of login is blocked for 15 minutes");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ExtendedAuthException("Bad username or password");
        }

        boolean enabled = true;
        if (!user.getEnabled()) {
            enabled = false;
            throw new ExtendedAuthException("notVerified:"+user.getEmail());
        }

        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = !user.getBanned();
        if (!accountNonLocked) {
            throw new ExtendedAuthException("Your account is banned by administrator");
        }

        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName()));


        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
    }
}
