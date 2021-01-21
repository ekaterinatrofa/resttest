package pl.edu.pjwst.jaz;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;


public class AppAuthentication extends AbstractAuthenticationToken {

    public final User authenticatedUser;



    public AppAuthentication(User authenticatedUser) {
       // super(toGrantedAuthorities(authenticatedUser.getRole()));
        super(toGrantedAuthorities(authenticatedUser.getRole()));
        this.authenticatedUser = authenticatedUser;
        setAuthenticated(true);
    }



    private static Collection<? extends GrantedAuthority> toGrantedAuthorities(String authenticatedUser) {
       // return authenticatedUser.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return createAuthorityList(authenticatedUser);
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
