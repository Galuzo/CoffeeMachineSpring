package by.nc.training.dev3.coffee.security;

import by.nc.training.dev3.coffee.entities.Account;
import by.nc.training.dev3.coffee.exceptions.ServiceException;
import by.nc.training.dev3.coffee.interfaces.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Win on 30.05.2017.
 */
@Transactional
@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    AuthorizationService authorizationService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Account account ;
                try {
                    account = authorizationService.getByLogin(username);
                    return new User(account.getLogin(), account.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList(account.getRole().getTitle()));
                } catch (ServiceException e) {
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");                }

            }

        };
    }
}
