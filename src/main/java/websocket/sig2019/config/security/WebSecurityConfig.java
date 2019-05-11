package websocket.sig2019.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails lola = User.withDefaultPasswordEncoder().username("lola").password("password").roles("USER")
				.build();
		UserDetails hatim = User.withDefaultPasswordEncoder().username("hatim").password("password").roles("USER")
				.build();
		UserDetails jamil = User.withDefaultPasswordEncoder().username("jamil").password("password").roles("USER")
				.build();

		return new InMemoryUserDetailsManager(lola, hatim, jamil);
	}

}