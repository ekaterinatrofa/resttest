package pl.edu.pjwst.jaz;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity

public class AppWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()


                .antMatchers("/auth0/**").permitAll()
                .antMatchers(HttpMethod.POST, "/register/**").permitAll()
                .antMatchers(HttpMethod.POST, "/addCategory/**").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/addSubCategory/**").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/updateSubCategory/**").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/updateCategory/**").hasAuthority("admin")
                .antMatchers(HttpMethod.POST, "/addAuction/**").permitAll()
                .antMatchers(HttpMethod.GET, "/listAuctions/**").permitAll()
                .antMatchers(HttpMethod.POST,"/login/**").permitAll()
                .antMatchers(HttpMethod.GET,"/listUsers/**").hasAuthority("admin")
                .antMatchers("/hello/**").hasAuthority("admin")
                .anyRequest().authenticated()

                .and().csrf().disable();   //Cross-Site Request Forgery
    }
}
