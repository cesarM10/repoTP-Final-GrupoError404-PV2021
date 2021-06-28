
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

/**
 * 
 * @author Error404
 *
 */

@EnableWebSecurity//Para habilitar la seguridad web.
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//Hacemos la inyeccion.
	@Autowired
	AutSeccessHandler accesoHandler;
	
	//Accede a las carpeta de estilos atraves de un arreglo  String.
	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/image/**","/js/**","/layer/**","/webjars/**","/vi/**"
    };
	
	/**
	 * http seguridad.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers(resources).permitAll()//Peticion a todos los recursos.
		     .antMatchers("/", "/index","/registro").permitAll()  
		     .anyRequest().authenticated() 
		     .and()
	   .formLogin()
		    .loginPage("/login").permitAll()
		    .successHandler(accesoHandler)
		    .failureUrl("/login?error=true")
		    .usernameParameter("username")
		    .passwordParameter("password")
	        .and()
	    .logout()//.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		    .permitAll()
		    .logoutSuccessUrl("/index");
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


