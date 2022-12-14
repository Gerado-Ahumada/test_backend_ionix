package cl.ionix.user.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
  @Bean
  public InMemoryUserDetailsManager detailsManager() throws Exception{
    List<UserDetails> users=List.of(
            User.withUsername("ionix")
                    .password("{noop}pw_ionix")
                    .authorities("ADMIN_IONIX")
                    .build()
    );
    return new InMemoryUserDetailsManager(users);

  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/v1/user").hasAnyRole("ADMIN_IONIX")
            .antMatchers(HttpMethod.GET, "/v1/user").hasAnyRole("ADMIN_IONIX")
            .and()
            .httpBasic();
    return http.build();

  }
}
