package com.back.funnote.users.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Qualifier("userDetailsServiceImpl")
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/login/**","/users/sign-up/**").permitAll()
				.antMatchers(HttpMethod.DELETE,"/notes/**,/contacts/**,/entreprises/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.POST,"/notes/**,/contacts/**,/entreprises/**").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT,"/notes/**,/contacts/**,/entreprises/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

/*	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests();
				//.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
               // .antMatchers("/login/**,/users/sign-up/**").permitAll()
               // .antMatchers("/funnote/login/**,/funnote/users/sign-up/**").permitAll()
               //.antMatchers(HttpMethod.OPTIONS,"/funnote/login/**,/funnote/users/sign-up/**,/funnote/notes/**,/funnote/contacts/**,/funnote/entreprises/**").permitAll()
                //.antMatchers(HttpMethod.DELETE,"/funnote/notes/**,/funnote/contacts/**,/funnote/entreprises/**").hasAuthority("ADMIN")
                //.antMatchers(HttpMethod.POST,"/funnote/notes/**,/funnote/contacts/**,/funnote/entreprises/**").hasAuthority("ADMIN")
                //.antMatchers(HttpMethod.POST,"/notes/**,/contacts/**,/entreprises/**").hasAuthority("ADMIN")
                //.antMatchers(HttpMethod.PUT,"/funnote/notes/**,/funnote/contacts/**,/funnote/entreprises/**").hasAuthority("ADMIN")
                //.anyRequest().authenticated()
				//.and()
				//.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				//.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}*/


	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setExposedHeaders(Arrays.asList("Authorization","Username","Access-Control-Allow-Origin"));

        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

