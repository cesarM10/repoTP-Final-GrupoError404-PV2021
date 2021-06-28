package ar.edu.unju.fi.tpfinal.service;

import java.util.List;


import ar.edu.unju.fi.tpfinal.model.Usuario;

public interface IUsuarioService {
	public void guardarUsuario(Usuario usuario);
	public Usuario getUsuario(String nombre);//busqueda por nombre
	//
	public void agregarUsuario(Usuario usuario);
	public List<Usuario> obtenerUsuarios();
	public Usuario getUser();

}
