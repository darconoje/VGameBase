package com.vgamebase.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true).ignoring().antMatchers("/images").and().ignoring().antMatchers("/css").and().ignoring()
				.antMatchers("/js");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()

				.authorizeRequests()
					.antMatchers("/css/**").permitAll()
					.antMatchers("/js/**").permitAll()
					.antMatchers("/img/**").permitAll()
					.antMatchers("/registration").permitAll()
					.antMatchers("/captcha-image.jpg").permitAll()
					.antMatchers("/onlineusers").permitAll()
					.antMatchers("/update").access("hasAuthority('Admin')")
					.antMatchers("/admin").access("hasAuthority('Admin')")
					.antMatchers("/populate").access("hasAuthority('Admin')")
					
					.antMatchers("/login*").permitAll()
					
					.and().authorizeRequests()	
					.anyRequest().authenticated()
					
					
				
				.and().formLogin().loginPage("/login").permitAll().usernameParameter("username")
				.passwordParameter("password").defaultSuccessUrl("/games", true).permitAll().

				and().logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login")
				.deleteCookies("JSESSIONID");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
