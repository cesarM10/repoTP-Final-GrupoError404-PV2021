
package ar.edu.unju.fi.tpfinal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.tpfinal.service.imp.LoginUsuarioServiceImp;



@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	AutSeccessHandler accesoHandler;
	
	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/image/**","/js/**","/layer/**","/webjars/**","/vi/**"
    };
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(resources).permitAll()
		     .antMatchers("/", "/index","/registro").permitAll()  //paginas que se puede acceder.
		     .anyRequest().authenticated() //Cualquier otro tiene que estar autenticado.
		     .and()
	   .formLogin()
		    .loginPage("/login").permitAll()
		    .successHandler(accesoHandler)
		    .failureUrl("/login?error=true")
		    .usernameParameter("username")//lo que pasaremos al html
		    .passwordParameter("password")//lo que pasaremos al html
	        .and()
	    .logout()//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .permitAll()
		    .logoutSuccessUrl("/index");//cuando el usuario salga se le dirigira al index.html
		http.csrf().disable();
	}

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
		   BCryptPasswordEncoder	bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	        return bCryptPasswordEncoder;
	    }
	 
	   @Autowired
	    LoginUsuarioServiceImp userDetailsService;
	   
	   @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	
}


