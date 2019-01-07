package eu.mapidev.predsys.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig  {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Configuration
    @Profile(Profiles.DEVELOPMENT)
    public static class SecurityDevConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	    builder.inMemoryAuthentication()
		    .withUser("admin").password("admin").roles("ADMIN").and();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	    LOGGER.info("Run DEVELOPMENT Security Configuration");

	    httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
		    .authorizeRequests().antMatchers(HttpMethod.GET, "/draw").permitAll().and()
		    .authorizeRequests().antMatchers("/static/**").permitAll().and()
		    .authorizeRequests().antMatchers("/info/**").hasRole("ADMIN")
		    .anyRequest().authenticated().and()
		    .formLogin().loginPage("/login").permitAll().and()
		    .httpBasic();
	    httpSecurity.csrf().disable();
	    httpSecurity.headers().frameOptions().disable();
	}

    }

    @Configuration
    @Profile(Profiles.PRODUCTION)
    public static class SecurityProdConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
	    builder.inMemoryAuthentication()
		    .withUser("marfik007").password("adidas007").roles("ADMIN").and();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	    LOGGER.info("Run PRODUCTION Security Configuration");

	    httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
		    .authorizeRequests().antMatchers(HttpMethod.GET, "/draw").permitAll().and()
		    .authorizeRequests().antMatchers("/static/**").permitAll().and()
		    .authorizeRequests().antMatchers("/info/**").hasRole("ADMIN")
		    .anyRequest().authenticated().and()
		    .formLogin().loginPage("/login").permitAll().and()
		    .httpBasic();
	    httpSecurity.csrf().disable();
	}

    }
}
