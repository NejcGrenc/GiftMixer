package grenc.giftmixer.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import eu.grenc.secureeye.client.JwtValidationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UnauthorizedEntryPoint unauthorizedEntryPoint;

	@Autowired
	private JwtValidationFilter jwtValidationFilter;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// We don't need CSRF for now
				.csrf().disable()
				
				.cors().and()
				
				// Add a filter to validate the tokens with every request
				.addFilterBefore(jwtValidationFilter, UsernamePasswordAuthenticationFilter.class)
				
				.authorizeRequests().antMatchers("/participantByCode","/verifyUser","/saveWish","/loadWish").permitAll()
				.and()
				.authorizeRequests().anyRequest().authenticated()
				.and()
				
				.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint)
				
				.and()
				// Make sure we use stateless session - session won't be used to store user's state.
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
