package ar.edu.unju.fi.tpfinal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{
	public Optional <Usuario> findByUsuario(String usuario); //usado para la busqueda del username

}
