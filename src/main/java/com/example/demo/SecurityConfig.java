//package com.example.demo;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////@Configuration
////@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//		return new BCryptPasswordEncoder();
//				
//	}
//	
//	
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	
//	
//	private static final String  USER_SQL = "SELECT"
//			+ "  adminid,"
//			+ "  adminpassword,"
//			+ "  enabled"
//			+ "  FROM"
//			+ "  administrator"
//			+ "  WHERE"
//            + "  adminid=?";
//			
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception{
//		
//		  web.ignoring().antMatchers("/webjars/**","/css/**");
//	}
//	
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		
//		http
//		 .authorizeRequests()
//		   .antMatchers("/webjars/**").permitAll()
//		   .antMatchers("/css/**").permitAll()
//		   .antMatchers("/login").permitAll()
//		   .anyRequest().authenticated();
//		
//		http
//		 .formLogin()
//		  .loginProcessingUrl("/login")
//		  .loginPage("/login")
//		  .failureUrl("/login")
//		  .usernameParameter("mailAddress")
//		  .passwordParameter("password")
//		  .defaultSuccessUrl("/employeeList",true);
//		    
//		http.csrf().disable();
//		
//	}
//	
//	
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//
//		auth.jdbcAuthentication()
//		    .dataSource(dataSource)
//		    .usersByUsernameQuery(USER_SQL)
//		    .passwordEncoder(passwordEncoder());
//
//	}
//
//	
//	
//	
//	
//	
//}
package com.example.demo;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private DataSource dataSource;
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, adminpassword, enabled from administrator where username=?")
            .authoritiesByUsernameQuery("select username, role from administrator where username=?")
        ;
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated();
           
          http.formLogin()
            .loginProcessingUrl("/login")
            .loginPage("/login")
            .defaultSuccessUrl("/empList",true)
            .usernameParameter("mailAddress").passwordParameter("password")
            .failureUrl("/login?error=true")
            .permitAll();
           //.and()
            //.logout().permitAll()
            //.logoutSuccessUrl("/login");  
          
          //HttpSession session = New HttpSession();
        
        
    }
    
    @Override
	public void configure(WebSecurity web)throws Exception {

		web.ignoring().antMatchers("/webjars/**","/css/**");
	}
    
    
    
    
    
}