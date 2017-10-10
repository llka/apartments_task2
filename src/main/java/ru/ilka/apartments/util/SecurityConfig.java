package ru.ilka.apartments.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan(value = "ru.ilka.apartments")
@PropertySource("classpath:application.properties")
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private static Logger logger = LogManager.getLogger(SecurityConfig.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;


    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery(usersQuery)
                    .authoritiesByUsernameQuery(rolesQuery)
                    .passwordEncoder(bCryptPasswordEncoder);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.
//                authorizeRequests()
//                    .antMatchers("/").permitAll()
//                    .antMatchers("/login").permitAll()
//                    .antMatchers("/register").permitAll()
//                    .antMatchers("/admin/**").hasAuthority("ADMIN")
//                    .anyRequest().authenticated()
//                    .and()
//                .csrf()
//                    .disable()
//                .formLogin()
//                    .loginPage("/login")
//                    .failureUrl("/login?error=true")
//                    .defaultSuccessUrl("/admin/home")
//                    .usernameParameter("email")
//                    .passwordParameter("password")
//                    .and()
//                .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/").and().exceptionHandling()
//                    .accessDeniedPage("/access-denied");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/start","/login","signup","/about").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/login?access=allow")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/denied");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }
}
