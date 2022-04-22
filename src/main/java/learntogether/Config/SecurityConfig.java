package learntogether.Config;

import learntogether.JWT.JwtAuthenticationFilter;
import learntogether.Security.CookieAuthenticationFilter;
import learntogether.Security.CustomSuccessHandler;
import learntogether.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
  Created by Luvbert
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private CookieAuthenticationFilter cookieAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CookieAuthenticationFilter cookieAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.cookieAuthenticationFilter = cookieAuthenticationFilter;
    }

    @Bean
    public UserDetailsService userDetailsService(){ return new UserDetailsServiceImpl();}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CustomSuccessHandler customSuccessHandler(){
        return new CustomSuccessHandler();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Get AuthenticationManager bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/welcome").anonymous()
                .antMatchers("/logout").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/register").anonymous()
                .antMatchers("/api/*").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/welcome?login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/spring_security_login")
                .successHandler(customSuccessHandler())
                .failureUrl("/welcome?login=failed&amp;incorrectAccount")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/welcome?login=failed&amp;accessDenied")
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID", "AuthenticationCookie");

                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                http.addFilterBefore(cookieAuthenticationFilter, JwtAuthenticationFilter.class);
    }
}
