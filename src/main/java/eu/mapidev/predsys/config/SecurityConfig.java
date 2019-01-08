package eu.mapidev.predsys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    private final static String USERNAME = "admin";
    private final static String SECRET = "{bcrypt}$2a$10$TO5q3d0bSzR.SbNMXaU6R.qV9/NG.Yh4xbx7WOLs.hux9BObYPDry";
    private static final String[] AUTH_WHITELIST = {
	"/css/**",
	"/js/**",
	"/img/**"};

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	auth.inMemoryAuthentication().passwordEncoder(encoder).withUser(USERNAME).password(SECRET).roles("ADMIN");
	LOGGER.info("[Authentication USERNAME]:" + USERNAME);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
	httpSecurity.authorizeRequests()
		.antMatchers("/profile", "/console/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/draw/**").permitAll()
		.antMatchers(AUTH_WHITELIST).permitAll()
		.antMatchers("/").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().csrf().ignoringAntMatchers("/console/**", "/draw/**")
		.and().headers().frameOptions().sameOrigin();
    }

}
