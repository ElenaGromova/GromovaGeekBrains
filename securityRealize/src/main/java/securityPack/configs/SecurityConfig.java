package securityPack.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import securityPack.services.RoleService;
import securityPack.services.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/authenticated/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN", "MANAGER")
                .antMatchers("/manager/**").hasAnyRole("MANAGER")
                .antMatchers("/all/**").hasAnyRole("ADMIN")
                .antMatchers("/um/**").hasAnyRole("USER", "MANAGER")
                .antMatchers("/au/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/authorityAdmin/**").hasAuthority("SOMETHING_ADMIN")
                .antMatchers("/authorityUser/**").hasAuthority("SOMETHING_USER")
                .antMatchers("/authorityManager/**").hasAuthority("SOMETHING_MANAGER")
                .antMatchers("/authorityAll/**").hasAuthority("SOMETHING_ALL")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
