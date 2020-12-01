package pl.edu.pjwst.jaz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppAuthentication extends AbstractAuthenticationToken {

    public final User authenticatedUser;



    public AppAuthentication(User authenticatedUser) {
        super(toGrantedAuthorities(authenticatedUser.getRole()));
        this.authenticatedUser = authenticatedUser;
        setAuthenticated(true);
    }



    private static Collection<? extends GrantedAuthority> toGrantedAuthorities(Set<String> authenticatedUser) {
        return authenticatedUser.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }


    @Override
    public Object getCredentials() {
        return authenticatedUser.getPassword();
    }

    @Override
    public Object getPrincipal() {
        return authenticatedUser;
    }
}
