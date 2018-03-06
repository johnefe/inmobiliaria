/**
 * 
 */
package com.proinsalud.sistemas.ws.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


/**
 * @author usuario
 *
 */
@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

	public void configure(AuthenticationManagerBuilder auth)
			throws Exception  {
		auth.inMemoryAuthentication().withUser("user1").password("secret1").roles("USER").and()
		.withUser("admin1").password("secret1").roles("USER", "ADMIN");
	}
	
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/person/**")
		.hasRole(""
				+ "").antMatchers("/**").hasRole("ADMIN").and().csrf().disable().headers().frameOptions().disable();
	}
	
//	
//	 private static String REALM="MY_TEST_REALM";
//     
//	    @Autowired
//	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//	        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
//	        auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
//	    }
//	     
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	  
//	      http.csrf().disable()
//	        .authorizeRequests()
//	        .antMatchers("/user/**").hasRole("ADMIN")
//	        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
//	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
//	    }
//	     
//	    @Bean
//	    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
//	        return new CustomBasicAuthenticationEntryPoint();
//	    }
//	     
//	    /* To allow Pre-flight [OPTIONS] request from browser */
//	    @Override
//	    public void configure(WebSecurity web) throws Exception {
//	        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//	    }
}
