
package ar.edu.unju.fi.tpfinal;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AutSeccessHandler implements AuthenticationSuccessHandler{

	//Estrategia de redirección simple.
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//variables booleanas para indetificar el Perfil aque se le dara el permiso de acceder.
		boolean userAdmin = false;
		boolean userCliente  = false;
		
		//Permisos para los distintos Perfiles del cliente.
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for(GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("ADMIN")) {
				userAdmin = true;
				break;
		   }else {
			   if(grantedAuthority.getAuthority().equals("CLIENTE")) {
			    userCliente =true;
			    break;
			   }
		   }
		}
		//Paginas en las que pueden acceder los CLIENTES O ADMIN 
		if(userAdmin) {
			redirectStrategy.sendRedirect(request, response, "/home");//pagina si tienen permiso
		}else {
			if(userCliente) {
			redirectStrategy.sendRedirect(request, response, "/home");//pagina si tienen permiso
		}else {
			throw new IllegalStateException();
	       	}
		
    	}
	}
}

