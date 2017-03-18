package edu.pl.pollub.config;

import edu.pl.pollub.handler.AuthenticationFailureHandler;
import edu.pl.pollub.handler.AuthenticationSuccessHandler;
import edu.pl.pollub.handler.HttpLogoutSuccessHandler;
import edu.pl.pollub.service.implementation.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestContextListener;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by Dell on 2017-03-15.
 */
@EnableWebSecurity
public class SecurityConfig {
    final DataSource dataSource;


    @Inject
    SecurityConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler();
    }

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        csrfTokenRepository.setHeaderName("X-XSRF-TOKEN");
        return new HttpSessionCsrfTokenRepository();
    }

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setCreateTableOnStartup(false);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
        ConcurrentSessionFilter concurrentSessionFilter = new ConcurrentSessionFilter(sessionRegistry(), "/session-expired.htm");
        return concurrentSessionFilter;
    }

    @Bean
    public SessionRegistryImpl sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeAuthenticationProvider() {
        return new PersistentTokenBasedRememberMeServices("myAppKey", userDetailsService(), jdbcTokenRepository());
    }

    @Bean
    public HttpAuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpAuthenticationEntryPoint();
    }

    @Bean
    public HttpLogoutSuccessHandler logoutSuccessHandler() {
        return new HttpLogoutSuccessHandler();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/login_check**").ignoringAntMatchers("/registration**").csrfTokenRepository(csrfTokenRepository())
                .and()
                .authenticationProvider(authenticationProvider())
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .httpBasic()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())

                .and()
                .rememberMe()
                .tokenRepository(jdbcTokenRepository())
                .tokenValiditySeconds(86400)


                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/root/**").hasRole("ROOT")
                .antMatchers("/user/**").hasAnyRole("ROOT", "TEACHER", "USER")
                .antMatchers("/teacher/**").hasAnyRole("ROOT", "TEACHER")

                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login_check")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())

                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/baned")
                .sessionRegistry(sessionRegistry());
    }
}
