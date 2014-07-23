package io.benbaxter.guestbook.web.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

	
	@Order(Ordered.HIGHEST_PRECEDENCE)
	@Configuration
	protected static class AuthenticationSecurity extends
			GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
					.withUser("admin")
					.password("admin")
					.roles("ADMIN", "USER")
				.and()
					.withUser("user")
					.password("user")
					.roles("USER");
		}
	}

//	@Bean
//	public ApplicationSecurity applicationSecurity() {
//		return new ApplicationSecurity();
//	}
//
//	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//
//		@Override
//		public void configure(WebSecurity web) throws Exception {
//			web.ignoring().antMatchers("/static/**");
//		}
//		
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//				.csrf().disable()
//				.authorizeRequests()
//				.antMatchers("/login")
//					.permitAll()
//				.antMatchers(HttpMethod.GET, "/people**")
//					.permitAll()
//				.anyRequest()
//					.fullyAuthenticated()
//				.and()
//					.formLogin()
//						.defaultSuccessUrl("/people.html")
//					.and()
//						.logout()
//						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//						.logoutSuccessUrl("/people.html")
//					.and()
//						.exceptionHandling()
//						.accessDeniedPage("/access?error")
//				.and()
//					.httpBasic();
//		}
//
//	}
	
}
