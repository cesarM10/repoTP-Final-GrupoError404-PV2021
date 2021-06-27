
package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Usuario;
import ar.edu.unju.fi.tpfinal.repository.IUsuarioRepository;

@Service
public class LoginUsuarioServiceImp implements UserDetailsService{


	@Autowired
	IUsuarioRepository usurioReposity;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//cargara un usario en sesion que es de tipo UserDetails.
		Usuario usuarioEncontrado = usurioReposity.findByUsuario(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no existe en la BD"));//busca al usuario por nombre de usuario
		
		List<GrantedAuthority> tipos = new ArrayList<>();//ArrayList del perfil para ver los roles en este caso lo llamamos perfil.
		//indentificacion y autorizacion de un usuario.
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(usuarioEncontrado.getPerfil()); 
		tipos.add(grantedAuthority);
		//traeremos los datos del cliente y nos retornara con el user que es de tipo UserDetails.
		UserDetails user = (UserDetails) new User(username,usuarioEncontrado.getPassword(),tipos);
		return user;

	}

}
