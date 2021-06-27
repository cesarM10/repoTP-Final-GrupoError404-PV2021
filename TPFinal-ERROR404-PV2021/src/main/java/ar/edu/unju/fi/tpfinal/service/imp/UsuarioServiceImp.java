package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import ar.edu.unju.fi.tpfinal.model.Usuario;
import ar.edu.unju.fi.tpfinal.repository.IUsuarioRepository;
import ar.edu.unju.fi.tpfinal.service.IUsuarioService;
@Service("empleadoService")//mmm
public class UsuarioServiceImp implements IUsuarioService{
	
	private List<Usuario> usuarioList = new ArrayList<Usuario>();
	

	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired //F1
	Usuario usuario;//F1

	@Override
	public void guardarUsuario(Usuario usuario) {
		// Encriptador
		
		String pw = usuario.getPassword();//aqui diferescias
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		usuario.setPassword(bCryptPasswordEncoder.encode(pw));//aqui diferescias
		
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario getUsuario(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
		usuarioList.add(usuario);
	}

	
	
	//no se
	@Override
	public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return usuarioList;
	}

	@Override
	public Usuario getUser() {
		// TODO Auto-generated method stub
		return usuario;
	}


	
}
